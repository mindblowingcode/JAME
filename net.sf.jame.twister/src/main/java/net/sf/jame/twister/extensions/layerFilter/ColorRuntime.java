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
package net.sf.jame.twister.extensions.layerFilter;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import net.sf.jame.core.util.Color32bit;
import net.sf.jame.core.util.Surface;
import net.sf.jame.core.util.Tile;
import net.sf.jame.twister.layerFilter.extension.LayerFilterExtensionRuntime;
import net.sf.jame.twister.util.Padding;

/**
 * @author Andrea Medeghini
 */
public class ColorRuntime extends LayerFilterExtensionRuntime<ColorConfig> {
	private final Padding padding = new Padding(0, 0, 0, 0);
	private Color color;

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public Color32bit getColor() {
		return getConfig().getColor();
	}

	/**
	 * @see net.sf.jame.twister.layerFilter.extension.LayerFilterExtensionRuntime#getPadding()
	 */
	@Override
	public Padding getPadding() {
		return padding;
	}

	/**
	 * @see net.sf.jame.twister.layerFilter.extension.LayerFilterExtensionRuntime#renderImage(net.sf.jame.core.util.Surface, net.sf.jame.core.util.Surface)
	 */
	@Override
	public void renderImage(final Surface src, final Surface dst) {
		final Graphics2D g2d = dst.getGraphics2D();
		g2d.setComposite(AlphaComposite.SrcOver);
		g2d.setColor(color);
		dst.getGraphics2D().drawImage(src.getImage(), 0, 0, null);
		g2d.fillRect(0, 0, dst.getWidth(), dst.getHeight());
		// g2d.dispose();
	}

	/**
	 * @see net.sf.jame.twister.layerFilter.extension.LayerFilterExtensionRuntime#setTile(net.sf.jame.core.util.Tile)
	 */
	@Override
	public void setTile(final Tile tile) {
	}

	/**
	 * @see net.sf.jame.twister.layerFilter.extension.LayerFilterExtensionRuntime#prepareFilter()
	 */
	@Override
	public void prepareFilter() {
		color = new Color(getColor().getARGB(), true);
	}
}
