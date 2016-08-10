/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
 *
 * This file is part of JAME.
 *
 * JAME is an application for creating fractals and other graphics artifacts.
 *
 * JAME is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JAME is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAME.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.sf.jame.queue.extensions.encoder;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.nextbreakpoint.ffmpeg4java.*;
import net.sf.jame.queue.encoder.EncoderContext;
import net.sf.jame.queue.encoder.EncoderException;
import net.sf.jame.queue.encoder.EncoderHook;
import net.sf.jame.queue.encoder.extension.AbstractEncoderExtensionRuntime;
import net.sf.jame.queue.encoder.extension.EncoderExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public abstract class FFmpegEncoderRuntime<T extends EncoderExtensionConfig> extends AbstractEncoderExtensionRuntime<T> {
	private static final Logger logger = Logger.getLogger(FFmpegEncoderRuntime.class.getName());
	private static final int PKT_BIT_BUFFER_SIZE = 200000;
	private EncoderHook hook;

	static {
		FFmpeg4Java.avcodec_register_all();
		FFmpeg4Java.av_register_all();
	}

	/**
	 * @see net.sf.jame.queue.encoder.extension.EncoderExtensionRuntime#setInterruptHook(net.sf.jame.queue.encoder.EncoderHook)
	 */
	@Override
	public void setInterruptHook(final EncoderHook hook) {
		this.hook = hook;
	}

	/**
	 * @see net.sf.jame.queue.encoder.extension.EncoderExtensionRuntime#encode(net.sf.jame.queue.encoder.EncoderContext, java.io.File)
	 */
	@Override
	public void encode(final EncoderContext context, final File path) throws EncoderException {
		fireStateChanged(0);
		if (isMovieSupported()) {
			AVFormatContext format_context = null;
			AVOutputFormat output_format = null;
			AVCodecContext codec_context = null;
			AVRational time_base = null;
			AVStream stream = null;
			AVCodec codec = null;
			AVFrame rgb_frame = null;
			AVFrame yuv_frame = null;
			SWIGTYPE_p_uint8_t rgb_bit_buffer = null;
			SWIGTYPE_p_uint8_t yuv_bit_buffer = null;
			SWIGTYPE_p_SwsContext sws_context = null;
			try {
				if (FFmpegEncoderRuntime.logger.isLoggable(Level.FINE)) {
					FFmpegEncoderRuntime.logger.fine("Starting encoding...");
				}
				long time = System.currentTimeMillis();
				int fps = context.getFrameRate();
				int frame_count = context.getFrameCount();
				int frame_width = context.getImageWidth();
				int frame_height = context.getImageHeight();
				format_context = FFmpeg4Java.avformat_alloc_context();
				output_format = FFmpeg4Java.av_guess_format(getFormatName(), null, null);
				if (format_context != null && output_format != null && !output_format.getVideo_codec().equals(AVCodecID.AV_CODEC_ID_NONE)) {
					if (FFmpegEncoderRuntime.logger.isLoggable(Level.FINE)) {
						FFmpegEncoderRuntime.logger.fine("Format is " + output_format.getLong_name());
					}
					format_context.setOformat(output_format);
					time_base = new AVRational();
					time_base.setNum(1);
					time_base.setDen(fps);
					codec = FFmpeg4Java.avcodec_find_encoder(output_format.getVideo_codec());
					codec_context = FFmpeg4Java.avcodec_alloc_context3(codec);
					if (codec != null && codec_context != null) {
						codec_context.setCodec_id(output_format.getVideo_codec());
						codec_context.setCodec_type(AVMediaType.AVMEDIA_TYPE_VIDEO);
						codec_context.setPix_fmt(AVPixelFormat.AV_PIX_FMT_YUV420P);
						codec_context.setWidth(frame_width);
						codec_context.setHeight(frame_height);
						codec_context.setTime_base(time_base);
						codec_context.setGop_size(12);
						codec_context.setBit_rate(40 * 1024);
						codec_context.setMax_b_frames(2);
						codec_context.setB_quant_factor(0.1f);
						codec_context.setI_quant_factor(0.1f);
						if (codec_context.getCodec_id() == AVCodecID.AV_CODEC_ID_MPEG2VIDEO) {
							codec_context.setMax_b_frames(2);
						}
						if (codec_context.getCodec_id() == AVCodecID.AV_CODEC_ID_MPEG1VIDEO) {
							codec_context.setMb_decision(2);
						}
						if (codec_context.getCodec_id() == AVCodecID.AV_CODEC_ID_H264) {
							codec_context.setQmax(69);
							codec_context.setQmin(0);
							codec_context.setQcompress(0.6f);
							codec_context.setMax_qdiff(4);
							codec_context.setRefs(3);
							codec_context.setMe_cmp(FFmpeg4Java.FF_CMP_CHROMA);
							codec_context.setMe_range(16);
							codec_context.setMe_subpel_quality(7);
							codec_context.setTrellis(1);
							codec_context.setKeyint_min(25);
							codec_context.setGop_size(250);
							codec_context.setThread_count(0);
							//codec_context.setPartitions(FFmpeg4Java.X264_PART_I4X4 | FFmpeg4Java.X264_PART_I8X8 | FFmpeg4Java.X264_PART_P8X8 | FFmpeg4Java.X264_PART_B8X8);
							codec_context.setFlags(FFmpeg4Java.AV_CODEC_FLAG_LOOP_FILTER);
							codec_context.setProfile(FFmpeg4Java.FF_PROFILE_H264_BASELINE);
						}
						if (codec_context.getCodec_id() == AVCodecID.AV_CODEC_ID_MPEG2VIDEO) {
							codec_context.setProfile(FFmpeg4Java.FF_PROFILE_MPEG2_HIGH);
						}
						if (codec_context.getCodec_id() == AVCodecID.AV_CODEC_ID_MPEG2VIDEO || codec_context.getCodec_id() == AVCodecID.AV_CODEC_ID_MPEG1VIDEO) {
							codec_context.setStrict_std_compliance(codec_context.getStrict_std_compliance() | FFmpeg4Java.FF_COMPLIANCE_VERY_STRICT);
						}
						codec_context.setFlags(codec_context.getFlags() | FFmpeg4Java.AV_CODEC_FLAG_GLOBAL_HEADER);
						stream = FFmpeg4Java.avformat_new_stream(format_context, codec);
						if (stream != null && format_context.getNb_streams() == 1) {
							AVCodecParameters params = new AVCodecParameters();
							params.setCodec_id(output_format.getVideo_codec());
							params.setCodec_type(AVMediaType.AVMEDIA_TYPE_VIDEO);
							params.setWidth(frame_width);
							params.setHeight(frame_height);
							stream.setTime_base(time_base);
							stream.setCodecpar(params);
							SWIGTYPE_p_uint8_t side_data = FFmpeg4Java.av_stream_new_side_data(stream, AVPacketSideDataType.AV_PKT_DATA_CPB_PROPERTIES, new AVCPBProperties().size_of());
							AVCPBProperties props = AVCPBProperties.asTypePointer(SWIGTYPE_p_uint8_t.asVoidPointer(side_data));
							props.setBuffer_size(frame_width * frame_height * 3 * 2);
							side_data = FFmpeg4Java.av_stream_get_side_data(stream, AVPacketSideDataType.AV_PKT_DATA_CPB_PROPERTIES, null);
							props = AVCPBProperties.asTypePointer(SWIGTYPE_p_uint8_t.asVoidPointer(side_data));
							System.out.println("Buffer size " + props.getBuffer_size());
							if (FFmpeg4Java.avcodec_open2(codec_context, codec, null) == 0) {
								if (FFmpegEncoderRuntime.logger.isLoggable(Level.FINE)) {
									FFmpegEncoderRuntime.logger.fine("Codec is " + codec.getName());
								}
								SWIGTYPE_p_p_void void_p_p = FFmpeg4Java.swig_from_p_to_p_p(AVIOContext.asVoidPointer(format_context.getPb()));
								SWIGTYPE_p_p_AVIOContext avio_context_p_p = SWIGTYPE_p_p_AVIOContext.asTypePointer(SWIGTYPE_p_p_void.asVoidPointer(void_p_p));
								if (FFmpeg4Java.avio_open2(avio_context_p_p, path.getAbsolutePath(), FFmpeg4Java.AVIO_FLAG_WRITE, null, null) >= 0) {
									format_context.setPb(AVIOContext.asTypePointer(FFmpeg4Java.swig_from_p_p_to_p(SWIGTYPE_p_p_void.asTypePointer(SWIGTYPE_p_p_AVIOContext.asVoidPointer(avio_context_p_p)))));
									sws_context = FFmpeg4Java.sws_getCachedContext(null, codec_context.getWidth(), codec_context.getHeight(), AVPixelFormat.AV_PIX_FMT_RGB24, codec_context.getWidth(), codec_context.getHeight(), AVPixelFormat.AV_PIX_FMT_YUV420P, FFmpeg4Java.SWS_BILINEAR, null, null, null);
									if (sws_context != null) {
										rgb_frame = FFmpeg4Java.av_frame_alloc();
										yuv_frame = FFmpeg4Java.av_frame_alloc();
										if (rgb_frame != null && yuv_frame != null) {
											rgb_frame.setWidth(codec_context.getWidth());
											rgb_frame.setHeight(codec_context.getHeight());
											rgb_frame.setFormat(AVPixelFormat.AV_PIX_FMT_RGB24.swigValue());
											yuv_frame.setWidth(codec_context.getWidth());
											yuv_frame.setHeight(codec_context.getHeight());
											yuv_frame.setFormat(AVPixelFormat.AV_PIX_FMT_YUV420P.swigValue());
											FFmpeg4Java.av_image_alloc(rgb_frame.getData(), rgb_frame.getLinesize(), codec_context.getWidth(), codec_context.getHeight(), AVPixelFormat.AV_PIX_FMT_RGB24, 1);
											FFmpeg4Java.av_image_alloc(yuv_frame.getData(), yuv_frame.getLinesize(), codec_context.getWidth(), codec_context.getHeight(), AVPixelFormat.AV_PIX_FMT_YUV420P, 1);
											int rgb_bit_buffer_size = FFmpeg4Java.av_image_get_buffer_size(AVPixelFormat.AV_PIX_FMT_RGB24, codec_context.getWidth(), codec_context.getHeight(), 1);
											int yuv_bit_buffer_size = FFmpeg4Java.av_image_get_buffer_size(AVPixelFormat.AV_PIX_FMT_YUV420P, codec_context.getWidth(), codec_context.getHeight(), 1);
											rgb_bit_buffer = SWIGTYPE_p_uint8_t.asTypePointer(FFmpeg4Java.av_mallocz(rgb_bit_buffer_size));
											yuv_bit_buffer = SWIGTYPE_p_uint8_t.asTypePointer(FFmpeg4Java.av_mallocz(yuv_bit_buffer_size));
											if (rgb_bit_buffer != null && yuv_bit_buffer != null) {
												FFmpeg4Java.av_image_fill_arrays(rgb_frame.getData(), rgb_frame.getLinesize(), rgb_bit_buffer, AVPixelFormat.AV_PIX_FMT_RGB24, codec_context.getWidth(), codec_context.getHeight(), 1);
												FFmpeg4Java.av_image_fill_arrays(yuv_frame.getData(), yuv_frame.getLinesize(), yuv_bit_buffer, AVPixelFormat.AV_PIX_FMT_YUV420P, codec_context.getWidth(), codec_context.getHeight(), 1);
												AVPacket packet = FFmpeg4Java.av_packet_alloc();
												if (packet != null) {
													packet.setStream_index(stream.getIndex());
													FFmpeg4Java.avformat_write_header(format_context, null);
													for (int frame = 0; frame < frame_count; frame++) {
														if (FFmpegEncoderRuntime.logger.isLoggable(Level.FINE)) {
															if (frame % 10 == 0) {
																FFmpegEncoderRuntime.logger.fine(frame + " frames encoded...");
															}
														}
														fireStateChanged(frame * 100 / (frame_count - 1));
														byte[] data = context.getTileAsByteArray(frame, 0, 0, context.getImageWidth(), context.getImageHeight(), 3);
														FFmpeg4Java.swig_set_bytes(rgb_bit_buffer, data);
														FFmpeg4Java.sws_scale(sws_context, rgb_frame.getData(), rgb_frame.getLinesize(), 0, codec_context.getHeight(), yuv_frame.getData(), yuv_frame.getLinesize());
														if (FFmpeg4Java.avcodec_send_frame(codec_context, yuv_frame) == 0) {
															while (FFmpeg4Java.avcodec_receive_packet(codec_context, packet) == 0) {
																FFmpeg4Java.av_packet_rescale_ts(packet, codec_context.getTime_base(), stream.getTime_base());
																FFmpeg4Java.av_interleaved_write_frame(format_context, packet);
															}
														}
														if (hook.isInterrupted()) {
															break;
														}
													}
													if (FFmpeg4Java.avcodec_send_frame(codec_context, null) == 0) {
														while (FFmpeg4Java.avcodec_receive_packet(codec_context, packet) == 0) {
															FFmpeg4Java.av_packet_rescale_ts(packet, codec_context.getTime_base(), stream.getTime_base());
															FFmpeg4Java.av_write_frame(format_context, packet);
														}
													}
													FFmpeg4Java.av_write_trailer(format_context);
													FFmpeg4Java.av_freep(AVPacket.asVoidPointer(packet));
													packet.delete();
												}
											}
										}
										FFmpeg4Java.sws_freeContext(sws_context);
									}
									FFmpeg4Java.avio_close(format_context.getPb());
								}
								FFmpeg4Java.avcodec_close(codec_context);
							}
						}
					}
				}
				if (!hook.isInterrupted()) {
					if (FFmpegEncoderRuntime.logger.isLoggable(Level.FINE)) {
						FFmpegEncoderRuntime.logger.fine(frame_count + " frames encoded.");
					}
					time = System.currentTimeMillis() - time;
					if (FFmpegEncoderRuntime.logger.isLoggable(Level.INFO)) {
						FFmpegEncoderRuntime.logger.info("Profile exported: elapsed time " + String.format("%3.2f", time / 1000.0d) + "s");
					}
					fireStateChanged(100);
				}
			}
			catch (final Exception e) {
				throw new EncoderException(e);
			}
			finally {
				if (rgb_frame != null) {
					FFmpeg4Java.av_freep(AVFrame.asVoidPointer(rgb_frame));
					rgb_frame.delete();
					rgb_frame = null;
				}
				if (yuv_frame != null) {
					FFmpeg4Java.av_freep(AVFrame.asVoidPointer(yuv_frame));
					yuv_frame.delete();
					yuv_frame = null;
				}
				if (codec != null) {
					codec.delete();
					codec = null;
				}
				if (stream != null) {
					stream.delete();
					stream = null;
				}
				if (codec_context != null) {
					codec_context.delete();
					codec_context = null;
				}
				if (output_format != null) {
					output_format.delete();
					output_format = null;
				}
				if (format_context != null) {
					format_context.delete();
					format_context = null;
				}
			}
		}
		else {
			throw new EncoderException("Can't encode the movie");
		}
	}

	/**
	 * @return
	 */
	protected abstract String getFormatName();
}
