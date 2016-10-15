/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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
package net.sf.jame.queue.test;

import java.io.File;
import java.io.IOException;

import net.sf.jame.queue.io.ChunkedFileOutputStream;

import org.junit.Assert;
import org.junit.Test;

public class TestChunkedFileOutputStream {
	@Test
	public void test() {
		try {
			ChunkedFileOutputStream os = new ChunkedFileOutputStream(new File("c:"), "test", ".bin", 2048);
			for (int i = 0; i < 1024; i++) {
				os.write(i % 16);
			}
			for (int i = 0; i < 1024; i++) {
				os.write(i % 32);
			}
			for (int i = 0; i < 1024; i++) {
				os.write(i % 64);
			}
			for (int i = 0; i < 1024; i++) {
				os.write(i % 128);
			}
			os.close();
		}
		catch (IOException e) {
			Assert.fail();
		}
	}
}
