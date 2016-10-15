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
package net.sf.jame.mandelbrot.swing.palette;

import java.util.ArrayList;

import net.sf.jame.core.swing.palette.PaletteChangeEvent;
import net.sf.jame.core.swing.palette.PaletteChangeListener;
import net.sf.jame.core.util.Palette;
import net.sf.jame.mandelbrot.util.RenderedPalette;

/**
 * @author Andrea Medeghini
 */
public class DefaultRenderedPaletteModel implements RenderedPaletteModel {
	private ArrayList<PaletteChangeListener> listeners = new ArrayList<PaletteChangeListener>();
	private RenderedPalette palette;

	/**
	 * @param palette
	 */
	public DefaultRenderedPaletteModel(final RenderedPalette palette) {
		if (palette == null) {
			throw new NullPointerException("palette == null");
		}
		this.palette = palette;
	}

	/**
	 * @see java.lang.Object#finalize()
	 */
	@Override
	public void finalize() throws Throwable {
		palette = null;
		listeners.clear();
		listeners = null;
		super.finalize();
	}

	/**
	 * @see net.sf.jame.core.swing.palette.PaletteFieldModel#addPaletteChangeListener(net.sf.jame.core.swing.palette.PaletteChangeListener)
	 */
	public void addPaletteChangeListener(final PaletteChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * @see net.sf.jame.core.swing.palette.PaletteFieldModel#removePaletteChangeListener(net.sf.jame.core.swing.palette.PaletteChangeListener)
	 */
	public void removePaletteChangeListener(final PaletteChangeListener listener) {
		listeners.remove(listener);
	}

	/**
	 * @param e
	 */
	protected void firePaletteChangeEvent(final PaletteChangeEvent e) {
		for (final PaletteChangeListener listener : listeners) {
			listener.paletteChanged(e);
		}
	}

	/**
	 * @see net.sf.jame.core.swing.palette.PaletteFieldModel#setPalette(net.sf.jame.core.util.Palette, boolean)
	 */
	public void setPalette(final Palette palette, final boolean isAdjusting) {
		if (palette instanceof RenderedPalette) {
			setRenderedPalette((RenderedPalette) palette, isAdjusting);
		}
	}

	/**
	 * @see net.sf.jame.core.swing.palette.PaletteFieldModel#getPalette()
	 */
	public Palette getPalette() {
		return palette;
	}

	/**
	 * @see net.sf.jame.mandelbrot.swing.palette.RenderedPaletteModel#setRenderedPalette(net.sf.jame.mandelbrot.colorRenderer.RenderedPalette, boolean)
	 */
	public void setRenderedPalette(final RenderedPalette palette, final boolean isAdjusting) {
		if (palette == null) {
			throw new NullPointerException("palette == null");
		}
		this.palette = palette;
		firePaletteChangeEvent(new PaletteChangeEvent(this, isAdjusting));
	}

	/**
	 * @see net.sf.jame.mandelbrot.swing.palette.RenderedPaletteModel#getRenderedPalette()
	 */
	public RenderedPalette getRenderedPalette() {
		return palette;
	}
}
