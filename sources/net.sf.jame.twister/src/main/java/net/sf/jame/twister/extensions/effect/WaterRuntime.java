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

import java.awt.image.DataBufferInt;

import net.sf.jame.core.media.gfx.EffectFactory;
import net.sf.jame.core.media.gfx.Lowlevel;
import net.sf.jame.core.media.gfx.WaterData;
import net.sf.jame.core.util.IntegerVector2D;
import net.sf.jame.core.util.Surface;
import net.sf.jame.twister.effect.extension.EffectExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class WaterRuntime extends EffectExtensionRuntime<WaterConfig> {
	private WaterData waterData;
	private IntegerVector2D size;
	private Surface surface1;
	private Surface surface2;
	private int height1;
	private int height2;
	private int frame;
	private int intensity;

	/**
	 * @see net.sf.jame.twister.effect.extension.EffectExtensionRuntime#renderImage(net.sf.jame.core.util.Surface)
	 */
	@Override
	public void renderImage(final Surface dst) {
		final int[] buffer0 = ((DataBufferInt) (dst.getImage().getRaster().getDataBuffer())).getData();
		final int[] buffer1 = ((DataBufferInt) (surface1.getImage().getRaster().getDataBuffer())).getData();
		final int[] buffer2 = ((DataBufferInt) (surface2.getImage().getRaster().getDataBuffer())).getData();
		Lowlevel.copy(buffer0, buffer2, size.getX(), height1);
		Lowlevel.flip_vertical(buffer2, buffer1, size.getX(), height1);
		EffectFactory.waterFX(waterData, buffer1, buffer2, frame, size.getX(), height1);
		Lowlevel.copy_area(buffer2, buffer0, 0, 0, size.getX(), height2, 0, height1, size.getX(), height1, size.getX(), size.getY());
	}

	/**
	 * @see net.sf.jame.twister.effect.extension.EffectExtensionRuntime#prepareEffect()
	 */
	@Override
	public void prepareEffect() {
		frame = (frame + ((intensity * 9 + 100) / 100)) % 64;
		intensity = getConfig().getIntensity().intValue();
		fireChanged();
	}

	/**
	 * @see net.sf.jame.twister.effect.extension.EffectExtensionRuntime#setTile(net.sf.jame.twister.ImageTile)
	 */
	@Override
	public void setSize(final IntegerVector2D size) {
		if (this.size != size) {
			this.size = size;
			height1 = size.getY() / 2 + 10;
			height2 = size.getY() / 2 - 10;
			waterData = new WaterData(size.getX(), height1);
			surface1 = new Surface(size.getX(), height1);
			surface2 = new Surface(size.getX(), height1);
		}
	}

	/**
	 * @see net.sf.jame.twister.effect.extension.EffectExtensionRuntime#reset()
	 */
	@Override
	public void reset() {
		frame = 0;
	}
}
