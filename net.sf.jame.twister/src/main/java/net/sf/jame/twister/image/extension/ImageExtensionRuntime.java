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
package net.sf.jame.twister.image.extension;

import java.awt.Graphics2D;
import java.util.Map;

import net.sf.jame.core.extension.ConfigurableExtensionRuntime;
import net.sf.jame.core.util.IntegerVector2D;
import net.sf.jame.core.util.Tile;

/**
 * @author Andrea Medeghini
 */
public abstract class ImageExtensionRuntime<T extends ImageExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * 
	 */
	public abstract void startRenderer();

	/**
	 * 
	 */
	public abstract void abortRenderer();

	/**
	 * @throws InterruptedException
	 */
	public abstract void joinRenderer() throws InterruptedException;

	/**
	 * @param tile
	 */
	public abstract void setTile(Tile tile);

	/**
	 * @return the rendering status.
	 */
	public abstract int getRenderingStatus();

	/**
	 * @return
	 */
	public abstract boolean isDynamic();

	/**
	 * @param isDynamicRequired
	 */
	public abstract void prepareImage(boolean isDynamicRequired);

	/**
	 * @param g2d
	 */
	public abstract void drawImage(Graphics2D g2d);

	/**
	 * @param g2d
	 * @param x
	 * @param y
	 */
	public abstract void drawImage(Graphics2D g2d, int x, int y);

	/**
	 * @param g2d
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public abstract void drawImage(Graphics2D g2d, int x, int y, int w, int h);

	/**
	 * @return
	 */
	public abstract IntegerVector2D getImageSize();

	/**
	 * @param hints
	 */
	public abstract void setRenderingHints(Map<Object, Object> hints);

	/**
	 * @see net.sf.jame.core.extension.ExtensionRuntime#dispose()
	 */
	@Override
	public void dispose() {
	}
}
