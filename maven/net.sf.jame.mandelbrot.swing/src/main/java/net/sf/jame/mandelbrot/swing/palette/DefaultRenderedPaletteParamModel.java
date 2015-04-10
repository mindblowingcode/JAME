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
package net.sf.jame.mandelbrot.swing.palette;

import java.util.ArrayList;

import net.sf.jame.core.swing.palette.PaletteChangeEvent;
import net.sf.jame.core.swing.palette.PaletteChangeListener;
import net.sf.jame.mandelbrot.util.DefaultRenderedPaletteParam;
import net.sf.jame.mandelbrot.util.RenderedPaletteParam;

/**
 * @author Andrea Medeghini
 */
public class DefaultRenderedPaletteParamModel implements RenderedPaletteParamModel {
	private ArrayList<PaletteChangeListener> listeners = new ArrayList<PaletteChangeListener>();
	private RenderedPaletteParam paletteParam;

	/**
	 * 
	 */
	public DefaultRenderedPaletteParamModel() {
		paletteParam = new DefaultRenderedPaletteParam(100);
	}

	/**
	 * @param paletteParam
	 */
	public DefaultRenderedPaletteParamModel(final RenderedPaletteParam paletteParam) {
		if (paletteParam == null) {
			throw new NullPointerException("paletteParam == null");
		}
		this.paletteParam = paletteParam;
	}

	/**
	 * @see java.lang.Object#finalize()
	 */
	@Override
	public void finalize() throws Throwable {
		paletteParam = null;
		listeners.clear();
		listeners = null;
		super.finalize();
	}

	/**
	 * @see net.sf.jame.mandelbrot.swing.palette.RenderedPaletteParamModel#addPaletteChangeListener(net.sf.jame.core.swing.palette.PaletteChangeListener)
	 */
	public void addPaletteChangeListener(final PaletteChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * @see net.sf.jame.mandelbrot.swing.palette.RenderedPaletteParamModel#removePaletteChangeListener(net.sf.jame.core.swing.palette.PaletteChangeListener)
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
	 * @see net.sf.jame.mandelbrot.swing.palette.RenderedPaletteParamModel#setPaletteParam(net.sf.jame.mandelbrot.colorRenderer.RenderedPaletteParam, boolean)
	 */
	public void setPaletteParam(final RenderedPaletteParam paletteParam, final boolean isAdjusting) {
		if (paletteParam == null) {
			throw new NullPointerException("paletteParam == null");
		}
		this.paletteParam = paletteParam;
		firePaletteChangeEvent(new PaletteChangeEvent(this, isAdjusting));
	}

	/**
	 * @see net.sf.jame.mandelbrot.swing.palette.RenderedPaletteParamModel#getPaletteParam()
	 */
	public RenderedPaletteParam getPaletteParam() {
		return paletteParam;
	}
}
