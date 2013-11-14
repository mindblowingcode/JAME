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
package net.sf.jame.twister.renderer;

import java.awt.Graphics2D;
import java.util.Map;

import net.sf.jame.core.util.Surface;
import net.sf.jame.core.util.Tile;
import net.sf.jame.twister.TwisterRuntime;

/**
 * @author Andrea Medeghini
 */
public interface TwisterRenderer {
	/**
	 * 
	 */
	public static final int STATUS_RENDERING = 0x01;
	/**
	 * 
	 */
	public static final int STATUS_TERMINATED = 0x02;
	/**
	 * 
	 */
	public static final int STATUS_ABORTED = 0x03;

	/**
	 * @param g
	 * @param x
	 * @param y
	 */
	public void drawImage(Graphics2D g);

	/**
	 * @param g
	 * @param x
	 * @param y
	 */
	public void drawImage(final Graphics2D g, int x, int y);

	/**
	 * @param g
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawImage(Graphics2D g, int x, int y, int w, int h);

	/**
	 * @param g
	 * @param x
	 * @param i
	 * @param w
	 * @param h
	 * @param bx
	 * @param by
	 */
	public void drawImage(Graphics2D g, int x, int i, int w, int h, int bx, int by);

	/**
	 * 
	 */
	public void startRenderer();

	/**
	 * 
	 */
	public void stopRenderer();

	/**
	 * 
	 */
	public void abortRenderer();

	/**
	 * 
	 */
	public void joinRenderer();

	/**
	 * @throws InterruptedException
	 */
	public void render() throws InterruptedException;

	/**
	 * @return the tile
	 */
	public Tile getTile();

	/**
	 * @param tile
	 */
	public void setTile(Tile tile);

	/**
	 * @param hints
	 */
	public void setRenderingHints(Map<Object, Object> hints);

	/**
	 * @return
	 */
	public TwisterRuntime getRuntime();

	/**
	 * @param isDynamic
	 */
	public void prepareImage(boolean isDynamic);

	/**
	 * 
	 */
	public void dispose();

	/**
	 * @param g
	 */
	public void drawSurface(Graphics2D g);

	/**
	 * @param g
	 * @param x
	 * @param y
	 */
	public void drawSurface(Graphics2D g, int x, int y);

	/**
	 * @param surface
	 */
	public void loadSurface(Surface surface);
}
