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
package net.sf.jame.twister.extensions.effect;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.DataBufferInt;

import net.sf.jame.core.media.gfx.EffectFactory;
import net.sf.jame.core.media.gfx.FireData;
import net.sf.jame.core.media.gfx.Lowlevel;
import net.sf.jame.core.util.IntegerVector2D;
import net.sf.jame.core.util.Surface;
import net.sf.jame.twister.effect.extension.EffectExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class FireRuntime extends EffectExtensionRuntime<FireConfig> {
	private FireData fireData;
	private IntegerVector2D size;
	private byte[] fireBuffer;
	private Surface fireSurface;
	private int intensityMax = 0;
	private int intensity = 0;

	/**
	 * @see net.sf.jame.twister.effect.extension.EffectExtensionRuntime#renderImage(net.sf.jame.core.util.Surface)
	 */
	@Override
	public void renderImage(final Surface dst) {
		final int[] buffer = ((DataBufferInt) (fireSurface.getImage().getRaster().getDataBuffer())).getData();
		Lowlevel.copy(fireBuffer, buffer, fireData.getColorMap().getRed(), fireData.getColorMap().getGreen(), fireData.getColorMap().getBlue(), fireData.getColorMap().getAlpha(), fireSurface.getWidth(), fireSurface.getHeight());
		final Graphics2D g2d = dst.getGraphics2D();
		g2d.drawImage(fireSurface.getImage(), 0, 0, size.getX(), size.getY(), null);
	}

	/**
	 * @see net.sf.jame.twister.effect.extension.EffectExtensionRuntime#prepareEffect()
	 */
	@Override
	public void prepareEffect() {
		intensityMax = (255 * getConfig().getIntensity().intValue()) / 100;
		if (intensity <= intensityMax) {
			intensity = intensity + 1;
		}
		if (intensity > intensityMax) {
			intensity = intensity - 1;
		}
		EffectFactory.fireFX(fireData, fireBuffer, intensity, fireSurface.getWidth(), fireSurface.getHeight() + 4);
		fireChanged();
	}

	/**
	 * @see net.sf.jame.twister.effect.extension.EffectExtensionRuntime#setSize(net.sf.jame.core.util.IntegerVector2D)
	 */
	@Override
	public void setSize(final IntegerVector2D size) {
		if (this.size != size) {
			this.size = size;
			fireData = new FireData();
			fireSurface = new Surface(size.getX(), Math.min(size.getY(), 256));
			fireBuffer = new byte[fireSurface.getWidth() * (fireSurface.getHeight() + 4)];
			final Graphics2D g2d = fireSurface.getGraphics2D();
			g2d.setColor(new Color(0xFF, 0xFF, 0xFF, 0));
			g2d.fillRect(0, 0, fireSurface.getWidth(), fireSurface.getHeight());
		}
	}

	/**
	 * @see net.sf.jame.twister.effect.extension.EffectExtensionRuntime#reset()
	 */
	@Override
	public void reset() {
		if (size != null) {
			setSize(new IntegerVector2D(size.getX(), size.getY()));
		}
		intensity = 0;
	}
}
