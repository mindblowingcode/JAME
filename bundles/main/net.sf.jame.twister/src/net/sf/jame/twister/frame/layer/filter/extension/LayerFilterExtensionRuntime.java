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
package net.sf.jame.twister.frame.layer.filter.extension;

import net.sf.jame.core.extension.ConfigurableExtensionRuntime;
import net.sf.jame.core.util.Tile;
import net.sf.jame.core.util.Surface;
import net.sf.jame.twister.util.Padding;

/**
 * @author Andrea Medeghini
 */
public abstract class LayerFilterExtensionRuntime<T extends LayerFilterExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * 
	 */
	public abstract void prepareFilter();

	/**
	 * @param src the source image.
	 * @param dst the destination image.
	 */
	public abstract void renderImage(Surface src, Surface dst);

	/**
	 * @return the padding.
	 */
	public abstract Padding getPadding();

	/**
	 * @see net.sf.jame.core.extension.ExtensionRuntime#dispose()
	 */
	@Override
	public void dispose() {
	}

	/**
	 * @param tile
	 */
	public abstract void setTile(Tile tile);
}
