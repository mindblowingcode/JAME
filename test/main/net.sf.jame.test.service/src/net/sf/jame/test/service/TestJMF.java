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
package net.sf.jame.test.service;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;
import net.sf.jame.core.util.ProgressListener;
import net.sf.jame.service.encoder.EncoderContext;
import net.sf.jame.service.encoder.RAFEncoderContext;
import net.sf.jame.service.encoder.extensions.MOVEncoderRuntime;
import net.sf.jame.service.io.ChunkedRandomAccessFile;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestJMF {
	private static final Logger logger = Logger.getLogger(TestJMF.class);

	// @Test
	public void testRAM() {
		try {
			final int w = 640;
			final int h = 480;
			final EncoderContext context = new TestEncoderContext(w, h, 10, 100);
			final MOVEncoderRuntime encoder = new MOVEncoderRuntime();
			encoder.addProgressListener(new ProgressListener() {
				public void done() {
				}

				public void failed(final Throwable e) {
				}

				public void stateChanged(final String message, final int percentage) {
					TestJMF.logger.info(percentage + "%");
				}

				public void stateChanged(final String message) {
				}
			});
			encoder.encode(context, new File("testRAM.mov"));
		}
		catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testRAF() {
		try {
			final int w = 640;
			final int h = 480;
			final ChunkedRandomAccessFile raf = new ChunkedRandomAccessFile(new File("test"), "test", ".raw", 1024 * 10);
			createRawFile(w, h, 100, raf);
			final EncoderContext context = new RAFEncoderContext(raf, w, h, 10, 100);
			final MOVEncoderRuntime encoder = new MOVEncoderRuntime();
			encoder.addProgressListener(new ProgressListener() {
				public void done() {
				}

				public void failed(final Throwable e) {
				}

				public void stateChanged(final String message, final int percentage) {
					TestJMF.logger.info(percentage + "%");
				}

				public void stateChanged(final String message) {
				}
			});
			encoder.encode(context, new File("testRAF.mov"));
		}
		catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * @param w
	 * @param h
	 * @param raf
	 * @throws IOException
	 */
	private void createRawFile(final int w, final int h, final int n, final ChunkedRandomAccessFile raf) throws IOException {
		final int length = w * h * 4;
		final byte[] data = new byte[length];
		for (int k = 0; k < n; k++) {
			TestJMF.logger.info("create frame " + k);
			for (int i = 0; i < length; i += 4) {
				final byte v = (byte) Math.rint(Math.random() * 255);
				data[i + 0] = v;
				data[i + 1] = v;
				data[i + 2] = v;
				data[i + 3] = (byte) 255;
			}
			raf.write(data);
		}
	}
}
