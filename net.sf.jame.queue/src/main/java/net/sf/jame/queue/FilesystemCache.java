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
package net.sf.jame.queue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.sf.jame.queue.io.ChunkedRandomAccessFile;

/**
 * @author Andrea Medeghini
 */
public interface FilesystemCache {
	/**
	 * @return
	 */
	public File[] list();

	/**
	 * @param id
	 * @throws IOException
	 */
	public void create(final int id) throws IOException;

	/**
	 * @param id
	 * @throws IOException
	 */
	public void delete(final int id) throws IOException;

	/**
	 * @param id
	 * @return
	 */
	public boolean exists(final int id);

	/**
	 * 
	 */
	public void deleteAll();

	/**
	 * @param id
	 * @return
	 * @throws IOException
	 */
	public InputStream getInputStream(final int id) throws IOException;

	/**
	 * @param id
	 * @return
	 * @throws IOException
	 */
	public OutputStream getOutputStream(final int id) throws IOException;

	/**
	 * @param id
	 * @return
	 * @throws IOException
	 */
	public ChunkedRandomAccessFile getRandomAccessFile(final int id) throws IOException;
}
