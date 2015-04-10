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
package net.sf.jame.test.service;

import java.io.File;
import java.io.IOException;

import net.sf.jame.service.io.ChunkedFileInputStream;

import org.junit.Assert;
import org.junit.Test;

public class TestChunkedFileInputStream {
	@Test
	public void test() {
		try {
			ChunkedFileInputStream is = new ChunkedFileInputStream(new File("c:"), "test", ".bin", 2048);
			byte[] buffer = new byte[1024];
			is.read(buffer);
			for (int i = 0; i < 1024; i++) {
				Assert.assertEquals(i % 16, buffer[i]);
			}
			is.read(buffer);
			for (int i = 0; i < 1024; i++) {
				Assert.assertEquals(i % 32, buffer[i]);
			}
			is.read(buffer);
			for (int i = 0; i < 1024; i++) {
				Assert.assertEquals(i % 64, buffer[i]);
			}
			is.read(buffer);
			for (int i = 0; i < 1024; i++) {
				Assert.assertEquals(i % 128, buffer[i]);
			}
			is.close();
		}
		catch (IOException e) {
			Assert.fail();
		}
	}
}
