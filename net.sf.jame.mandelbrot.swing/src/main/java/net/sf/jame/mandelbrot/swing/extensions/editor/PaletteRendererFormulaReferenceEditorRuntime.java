/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
package net.sf.jame.mandelbrot.swing.extensions.editor;

import net.sf.jame.core.extension.ExtensionReference;
import net.sf.jame.core.swing.editor.ReferenceEditorRuntime;
import net.sf.jame.core.swing.extension.ExtensionComboBoxModel;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.paletteRendererFormula.PaletteRendererFormulaExtensionReferenceNodeValue;

/**
 * @author Andrea Medeghini
 */
public class PaletteRendererFormulaReferenceEditorRuntime extends ReferenceEditorRuntime {
	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceEditorRuntime#createModel()
	 */
	@Override
	protected ExtensionComboBoxModel createModel() {
		return new ExtensionComboBoxModel(MandelbrotRegistry.getInstance().getPaletteRendererFormulaRegistry(), true);
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ReferenceEditorRuntime#createChildValue()
	 */
	@Override
	protected NodeValue<?> createChildValue() {
		return null;
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ReferenceEditorRuntime#createNodeValue(net.sf.jame.core.extension.ExtensionReference)
	 */
	@Override
	protected NodeValue<?> createNodeValue(final ExtensionReference reference) {
		// return new PaletteRendererFormulaExtensionReferenceNodeValue(reference != null ? reference.clone() : null);
		return new PaletteRendererFormulaExtensionReferenceNodeValue(reference);
	}
}
