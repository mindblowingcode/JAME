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
package net.sf.jame.service.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Andrea Medeghini
 */
public class ChunkedFileOutputStream extends OutputStream {
	private final int chunkLength;
	private final String prefix;
	private final String suffix;
	private final File path;
	private int chunk;
	private long length;
	private FileOutputStream os;

	/**
	 * @param path
	 * @param prefix
	 * @param suffix
	 * @param chunkLength
	 * @throws IOException
	 */
	public ChunkedFileOutputStream(final File path, final String prefix, final String suffix, final int chunkLength) throws IOException {
		this.path = path;
		this.prefix = prefix;
		this.suffix = suffix;
		this.chunkLength = chunkLength;
	}

	/**
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(final int b) throws IOException {
		if (os == null) {
			os = new FileOutputStream(new File(path, prefix + chunk + suffix));
		}
		if (os != null) {
			os.write(b);
			length += 1;
			if ((length % chunkLength) == 0) {
				chunk += 1;
				os.close();
				os = null;
			}
		}
	}

	/**
	 * @see java.io.OutputStream#close()
	 */
	@Override
	public void close() throws IOException {
		if (os != null) {
			os.close();
			os = null;
		}
		super.close();
	}

	/**
	 * @see java.io.OutputStream#flush()
	 */
	@Override
	public void flush() throws IOException {
		if (os != null) {
			os.flush();
		}
		super.flush();
	}
}
