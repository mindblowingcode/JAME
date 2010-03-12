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
package net.sf.jame.core.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author Andrea Medeghini
 */
public class SurfacePool {
	private static final Logger logger = Logger.getLogger(SurfacePool.class);
	private static final int MAX_POOL_SIZE = 6;
	private final List<Surface> surfacePool = new ArrayList<Surface>(SurfacePool.MAX_POOL_SIZE);
	private final List<Surface> surfaces = new ArrayList<Surface>(SurfacePool.MAX_POOL_SIZE);
	private final int width;
	private final int height;

	/**
	 * @param imagePool
	 */
	public SurfacePool(final int width, final int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * @return
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * 
	 */
	public void dispose() {
		surfacePool.addAll(surfaces);
		for (final Surface surface : surfacePool) {
			surface.dispose();
		}
		surfacePool.clear();
		surfaces.clear();
	}

	/**
	 * @return a surface
	 */
	public Surface getSurface() {
		Surface surface = null;
		if (surfacePool.size() == 0) {
			surface = new Surface(width, height);
		}
		else {
			surface = surfacePool.remove(0);
		}
		surfaces.add(0, surface);
		return surface;
	}

	/**
	 * @param surface
	 */
	public void putSurface(final Surface surface) {
		if (!surfaces.remove(surface)) {
			SurfacePool.logger.error("Surface isn't in the pool: " + surface);
		}
		surface.disposeGraphics();
		surfacePool.add(0, surface);
		// cleanup();
	}

	// private void cleanup()
	// {
	// if ((surfacePool.size() > 0) && (surfacePool.size() + surfaces.size() > SurfacePool.MAX_POOL_SIZE))
	// {
	// Surface surface = surfacePool.remove(0);
	//
	// SurfacePool.logger.debug("Surface destroyed: " + surface);
	//
	// surface.dispose();
	//
	// surface = null;
	// }
	// }
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Statistics: free = ");
		builder.append(surfacePool.size());
		builder.append(", used = ");
		builder.append(surfaces.size());
		builder.append(", total = ");
		builder.append(surfacePool.size() + surfaces.size());
		return builder.toString();
	}
}
