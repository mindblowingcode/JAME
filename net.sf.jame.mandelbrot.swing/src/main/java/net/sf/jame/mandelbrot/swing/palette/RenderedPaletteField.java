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

import javax.swing.DefaultSingleSelectionModel;
import javax.swing.JComponent;
import javax.swing.SingleSelectionModel;

import net.sf.jame.core.swing.palette.PaletteChangeListener;
import net.sf.jame.mandelbrot.util.RenderedPalette;

/**
 * @author Andrea Medeghini
 */
public class RenderedPaletteField extends JComponent {
	private static final long serialVersionUID = 1L;
	private SingleSelectionModel selectionModel;
	private RenderedPaletteModel model;
	private boolean dropEnabled = true;
	private boolean dragEnabled = true;

	/**
	 * @param model
	 */
	public RenderedPaletteField(final RenderedPaletteModel model) {
		setSelectionModel(new DefaultSingleSelectionModel());
		setModel(model);
		setUI(new RenderedPaletteFieldUI());
	}

	/**
	 * @see java.lang.Object#finalize()
	 */
	@Override
	public void finalize() throws Throwable {
		model = null;
		super.finalize();
	}

	/**
	 * @return the palette.
	 */
	public RenderedPalette getPalette() {
		return model.getRenderedPalette();
	}

	/**
	 * @return the model.
	 */
	public RenderedPaletteModel getModel() {
		return model;
	}

	/**
	 * @param model
	 */
	public void setModel(final RenderedPaletteModel model) {
		if (model == null) {
			throw new NullPointerException("model == null");
		}
		this.model = model;
	}

	/**
	 * @return true if drag is enabled.
	 */
	public boolean isDragEnabled() {
		return dragEnabled;
	}

	/**
	 * @param dragEnabled
	 */
	public void setDragEnabled(final boolean dragEnabled) {
		this.dragEnabled = dragEnabled;
	}

	/**
	 * @return true if drop is enabled.
	 */
	public boolean isDropEnabled() {
		return dropEnabled;
	}

	/**
	 * @param dropEnabled
	 */
	public void setDropEnabled(final boolean dropEnabled) {
		this.dropEnabled = dropEnabled;
	}

	/**
	 * @param listener
	 */
	public void addPaletteChangeListener(final PaletteChangeListener listener) {
		model.addPaletteChangeListener(listener);
	}

	/**
	 * @param listener
	 */
	public void removePaletteChangeListener(final PaletteChangeListener listener) {
		model.removePaletteChangeListener(listener);
	}

	/**
	 * @return the selection model.
	 */
	public SingleSelectionModel getSelectionModel() {
		return selectionModel;
	}

	/**
	 * @param selectionModel
	 */
	public void setSelectionModel(final SingleSelectionModel selectionModel) {
		this.selectionModel = selectionModel;
	}
}
