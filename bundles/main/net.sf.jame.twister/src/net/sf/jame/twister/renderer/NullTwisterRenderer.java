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

import net.sf.jame.core.util.Tile;
import net.sf.jame.core.util.Surface;
import net.sf.jame.twister.TwisterRuntime;

/**
 * @author Andrea Medeghini
 */
public class NullTwisterRenderer implements TwisterRenderer {
	private Tile tile;

	/**
	 * 
	 */
	public NullTwisterRenderer() {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#startRenderer()
	 */
	public void startRenderer() {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#stopRenderer()
	 */
	public void stopRenderer() {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#abortRenderer()
	 */
	public void abortRenderer() {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#joinRenderer()
	 */
	public void joinRenderer() {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#drawImage(java.awt.Graphics2D)
	 */
	public void drawImage(final Graphics2D g) {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#drawImage(java.awt.Graphics2D, int, int)
	 */
	public void drawImage(final Graphics2D g, final int x, final int y) {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#drawImage(java.awt.Graphics2D, int, int, int, int)
	 */
	public void drawImage(final Graphics2D g, final int x, final int y, final int w, final int h) {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#drawImage(java.awt.Graphics2D, int, int, int, int, int, int)
	 */
	public void drawImage(final Graphics2D g, final int x, final int y, final int w, final int h, final int bx, final int by) {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#getRuntime()
	 */
	public TwisterRuntime getRuntime() {
		return null;
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#setRenderingHints(java.util.Map)
	 */
	public void setRenderingHints(final Map<Object, Object> hints) {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#getTile()
	 */
	public Tile getTile() {
		return tile;
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#setTile(net.sf.jame.core.util.Tile)
	 */
	public void setTile(final Tile tile) {
		this.tile = tile;
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#render()
	 */
	public void render() {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#prepareImage(boolean)
	 */
	public void prepareImage(final boolean isDynamicRequired) {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#dispose()
	 */
	public void dispose() {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#drawSurface(java.awt.Graphics2D)
	 */
	public void drawSurface(final Graphics2D graphics2D) {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#drawSurface(java.awt.Graphics2D, int, int)
	 */
	public void drawSurface(Graphics2D graphics2D, int x, int y) {
	}

	/**
	 * @see net.sf.jame.twister.renderer.TwisterRenderer#loadSurface(net.sf.jame.core.util.Surface)
	 */
	public void loadSurface(final Surface surface) {
	}
}
