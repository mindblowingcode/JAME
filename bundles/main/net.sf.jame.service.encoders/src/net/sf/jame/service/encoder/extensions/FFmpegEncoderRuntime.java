/*
 * JAME 6.1 
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2010 Andrea Medeghini
 * http://andreamedeghini.users.sourceforge.net
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
package net.sf.jame.service.encoder.extensions;

import java.io.File;

import net.sf.ffmpeg4java.AVCodec;
import net.sf.ffmpeg4java.AVCodecContext;
import net.sf.ffmpeg4java.AVFormatContext;
import net.sf.ffmpeg4java.AVFrame;
import net.sf.ffmpeg4java.AVOutputFormat;
import net.sf.ffmpeg4java.AVPacket;
import net.sf.ffmpeg4java.AVRational;
import net.sf.ffmpeg4java.AVStream;
import net.sf.ffmpeg4java.CodecID;
import net.sf.ffmpeg4java.CodecType;
import net.sf.ffmpeg4java.FFmpeg4Java;
import net.sf.ffmpeg4java.PixelFormat;
import net.sf.ffmpeg4java.SWIGTYPE_p_SwsContext;
import net.sf.ffmpeg4java.SWIGTYPE_p_uint8_t;
import net.sf.jame.service.encoder.EncoderContext;
import net.sf.jame.service.encoder.EncoderException;
import net.sf.jame.service.encoder.EncoderHook;
import net.sf.jame.service.encoder.extension.AbstractEncoderExtensionRuntime;
import net.sf.jame.service.encoder.extension.EncoderExtensionConfig;

import org.apache.log4j.Logger;

/**
 * @author Andrea Medeghini
 */
public abstract class FFmpegEncoderRuntime<T extends EncoderExtensionConfig> extends AbstractEncoderExtensionRuntime<T> {
	private static final Logger logger = Logger.getLogger(FFmpegEncoderRuntime.class);
	private static final int PKT_BIT_BUFFER_SIZE = 200000;
	private EncoderHook hook;
	static {
		FFmpeg4Java.avcodec_register_all();
		FFmpeg4Java.av_register_all();
	}

	/**
	 * @see net.sf.jame.service.encoder.extension.EncoderExtensionRuntime#setInterruptHook(net.sf.jame.service.encoder.EncoderHook)
	 */
	@Override
	public void setInterruptHook(final EncoderHook hook) {
		this.hook = hook;
	}

	/**
	 * @see net.sf.jame.service.encoder.extension.EncoderExtensionRuntime#encode(net.sf.jame.service.encoder.EncoderContext, java.io.File)
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
			SWIGTYPE_p_uint8_t pkt_bit_buffer = null;
			SWIGTYPE_p_uint8_t rgb_bit_buffer = null;
			SWIGTYPE_p_uint8_t yuv_bit_buffer = null;
			SWIGTYPE_p_SwsContext sws_context = null;
			try {
				if (FFmpegEncoderRuntime.logger.isDebugEnabled()) {
					FFmpegEncoderRuntime.logger.debug("Starting encoding...");
				}
				long time = System.currentTimeMillis();
				int fps = context.getFrameRate();
				int frame_count = context.getFrameCount();
				int frame_width = context.getImageWidth();
				int frame_height = context.getImageHeight();
				format_context = FFmpeg4Java.avformat_alloc_context();
				if (format_context != null) {
					output_format = FFmpeg4Java.guess_format(getFormatName(), null, null);
					if (output_format != null && !output_format.getVideo_codec().equals(CodecID.CODEC_ID_NONE)) {
						if (FFmpegEncoderRuntime.logger.isDebugEnabled()) {
							FFmpegEncoderRuntime.logger.debug("Format is " + output_format.getLong_name());
						}
						format_context.setOformat(output_format);
						time_base = new AVRational();
						time_base.setNum(1);
						time_base.setDen(fps);
						stream = FFmpeg4Java.av_new_stream(format_context, 0);
						if (stream != null && format_context.getNb_streams() == 1) {
							codec_context = stream.getCodec();
							FFmpeg4Java.avcodec_get_context_defaults2(codec_context, CodecType.CODEC_TYPE_VIDEO);
							codec_context.setCodec_id(output_format.getVideo_codec());
							codec_context.setCodec_type(CodecType.CODEC_TYPE_VIDEO);
							codec_context.setPix_fmt(PixelFormat.PIX_FMT_YUV420P);
							codec_context.setGop_size(10);
							codec_context.setMax_b_frames(4);
							codec_context.setTime_base(time_base);
							codec_context.setWidth(frame_width);
							codec_context.setHeight(frame_height);
							codec_context.setBit_rate(200000);
							codec_context.setCompression_level(-2);
							codec_context.setB_quant_factor(0.1f);
							codec_context.setI_quant_factor(0.1f);
							codec = FFmpeg4Java.avcodec_find_encoder(stream.getCodec().getCodec_id());
							int pkt_bit_buffer_size = PKT_BIT_BUFFER_SIZE;
							pkt_bit_buffer = SWIGTYPE_p_uint8_t.asTypePointer(FFmpeg4Java.av_malloc(pkt_bit_buffer_size));
							if (pkt_bit_buffer != null) {
								if (codec != null && FFmpeg4Java.avcodec_open(codec_context, codec) == 0) {
									if (FFmpegEncoderRuntime.logger.isDebugEnabled()) {
										FFmpegEncoderRuntime.logger.debug("Codec is " + codec.getName());
									}
									if (FFmpeg4Java.url_fopen(format_context.get_byte_io_context_p_p(), path.getAbsolutePath(), FFmpeg4Java.URL_WRONLY) == 0) {
										FFmpeg4Java.url_setbufsize(format_context.getPb(), pkt_bit_buffer_size);
										sws_context = FFmpeg4Java.sws_getContext(codec_context.getWidth(), codec_context.getHeight(), PixelFormat.PIX_FMT_RGB24, codec_context.getWidth(), codec_context.getHeight(), PixelFormat.PIX_FMT_YUV420P, FFmpeg4Java.SWS_BILINEAR, null, null, null);
										if (sws_context != null) {
											FFmpeg4Java.av_write_header(format_context);
											rgb_frame = FFmpeg4Java.avcodec_alloc_frame();
											if (rgb_frame != null) {
												yuv_frame = FFmpeg4Java.avcodec_alloc_frame();
												if (yuv_frame != null) {
													int rgb_bit_buffer_size = FFmpeg4Java.avpicture_get_size(PixelFormat.PIX_FMT_RGB24, codec_context.getWidth(), codec_context.getHeight());
													rgb_bit_buffer = SWIGTYPE_p_uint8_t.asTypePointer(FFmpeg4Java.av_malloc(rgb_bit_buffer_size));
													if (rgb_bit_buffer != null) {
														int yuv_bit_buffer_size = FFmpeg4Java.avpicture_get_size(PixelFormat.PIX_FMT_YUV420P, codec_context.getWidth(), codec_context.getHeight());
														yuv_bit_buffer = SWIGTYPE_p_uint8_t.asTypePointer(FFmpeg4Java.av_malloc(yuv_bit_buffer_size));
														if (yuv_bit_buffer != null) {
															FFmpeg4Java.avpicture_fill(yuv_frame.asPicture(), yuv_bit_buffer, PixelFormat.PIX_FMT_YUV420P, codec_context.getWidth(), codec_context.getHeight());
															FFmpeg4Java.avpicture_fill(rgb_frame.asPicture(), rgb_bit_buffer, PixelFormat.PIX_FMT_RGB24, codec_context.getWidth(), codec_context.getHeight());
															for (int frame = 0; frame < frame_count; frame++) {
																if (FFmpegEncoderRuntime.logger.isDebugEnabled()) {
																	if (frame % 10 == 0) {
																		FFmpegEncoderRuntime.logger.debug(frame + " frames encoded...");
																	}
																}
																fireStateChanged(frame * 100 / (frame_count - 1));
																byte[] data = context.getTileAsByteArray(frame, 0, 0, context.getImageWidth(), context.getImageHeight(), 3);
																FFmpeg4Java.swig_set_bytes(rgb_bit_buffer, data);
																FFmpeg4Java.sws_scale(sws_context, rgb_frame.getData(), rgb_frame.getLinesize(), 0, codec_context.getHeight(), yuv_frame.getData(), yuv_frame.getLinesize());
																int ret = FFmpeg4Java.avcodec_encode_video(codec_context, pkt_bit_buffer, pkt_bit_buffer_size, yuv_frame);
																if (ret > 0) {
																	AVPacket packet = new AVPacket();
																	FFmpeg4Java.av_init_packet(packet);
																	packet.setStream_index(stream.getId());
																	packet.setData(pkt_bit_buffer);
																	packet.setSize(ret);
																	packet.setPts(FFmpeg4Java.av_rescale_q(codec_context.getCoded_frame().getPts(), codec_context.getTime_base(), stream.getTime_base()));
																	if (codec_context.getCoded_frame().getKey_frame() != 0) {
																		packet.setFlags(packet.getFlags() | FFmpeg4Java.PKT_FLAG_KEY);
																	}
																	FFmpeg4Java.av_write_frame(format_context, packet);
																	FFmpeg4Java.av_free_packet(packet);
																	packet.delete();
																}
																if (hook.isInterrupted()) {
																	break;
																}
															}
														}
													}
												}
											}
											FFmpeg4Java.av_write_trailer(format_context);
											FFmpeg4Java.sws_freeContext(sws_context);
										}
										FFmpeg4Java.url_fclose(format_context.getPb());
									}
									FFmpeg4Java.avcodec_close(codec_context);
								}
							}
						}
					}
				}
				if (!hook.isInterrupted()) {
					if (FFmpegEncoderRuntime.logger.isDebugEnabled()) {
						FFmpegEncoderRuntime.logger.debug(frame_count + " frames encoded.");
					}
					time = System.currentTimeMillis() - time;
					if (FFmpegEncoderRuntime.logger.isInfoEnabled()) {
						FFmpegEncoderRuntime.logger.info("Profile exported: elapsed time " + String.format("%3.2f", time / 1000.0d) + "s");
					}
					fireStateChanged(100);
				}
			}
			catch (final Exception e) {
				throw new EncoderException(e);
			}
			finally {
				if (yuv_frame != null) {
					FFmpeg4Java.avpicture_free(yuv_frame.asPicture());
					yuv_frame.delete();
					yuv_frame = null;
				}
				if (rgb_frame != null) {
					FFmpeg4Java.avpicture_free(rgb_frame.asPicture());
					rgb_frame.delete();
					rgb_frame = null;
				}
				if (pkt_bit_buffer != null) {
					FFmpeg4Java.av_free(SWIGTYPE_p_uint8_t.asVoidPointer(pkt_bit_buffer));
					pkt_bit_buffer = null;
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
