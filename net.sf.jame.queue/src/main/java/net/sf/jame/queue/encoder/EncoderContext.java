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
package net.sf.jame.queue.encoder;

import java.io.IOException;

/**
 * @author Andrea Medeghini
 */
public interface EncoderContext {
	/**
	 * @param n
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public byte[] getTileAsByteArray(int n, int x, int y, int w, int h, int s) throws IOException;

	/**
	 * @param n
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public int[] getTileAsIntArray(int n, int x, int y, int w, int h, int s) throws IOException;

	/**
	 * @return
	 */
	public int getImageHeight();

	/**
	 * @return
	 */
	public int getImageWidth();

	/**
	 * @return
	 */
	public int getFrameRate();

	/**
	 * @return
	 */
	public int getFrameCount();
}
