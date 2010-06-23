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
package net.sf.jame.mandelbrot.palette.formula;

import net.sf.jame.core.common.ExtensionReferenceElement;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.extension.ExtensionReference;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.palette.formula.extension.PaletteRendererFormulaExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class PaletteRendererFormulaRuntimeElement extends RuntimeElement {
	private PaletteRendererFormulaExtensionRuntime formulaRuntime;
	private PaletteRendererFormulaConfigElement formulaElement;
	private ExtensionListener extensionListener;

	/**
	 * Constructs a new formula element.
	 * 
	 * @param formulaElement
	 */
	public PaletteRendererFormulaRuntimeElement(final PaletteRendererFormulaConfigElement formulaElement) {
		if (formulaElement == null) {
			throw new IllegalArgumentException("formulaElement is null");
		}
		this.formulaElement = formulaElement;
		createRuntime(formulaElement.getReference());
		extensionListener = new ExtensionListener(this);
		formulaElement.getExtensionElement().addChangeListener(extensionListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((formulaElement != null) && (extensionListener != null)) {
			formulaElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if (formulaRuntime != null) {
			formulaRuntime.dispose();
			formulaRuntime = null;
		}
		formulaElement = null;
		super.dispose();
	}

	private void createRuntime(final ExtensionReference reference) {
		try {
			if (reference != null) {
				final PaletteRendererFormulaExtensionRuntime formulaRuntime = MandelbrotRegistry.getInstance().getPaletteRendererFormulaExtension(reference.getExtensionId()).createExtensionRuntime();
				setFormulaRuntime(formulaRuntime);
			}
		}
		catch (final ExtensionNotFoundException e) {
			e.printStackTrace();
		}
		catch (final ExtensionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the formulaRuntime
	 */
	public PaletteRendererFormulaExtensionRuntime getFormulaRuntime() {
		return formulaRuntime;
	}

	private void setFormulaRuntime(final PaletteRendererFormulaExtensionRuntime formulaRuntime) {
		if (this.formulaRuntime != null) {
			this.formulaRuntime.dispose();
		}
		this.formulaRuntime = formulaRuntime;
	}

	private class ExtensionListener implements ValueChangeListener {
		private final PaletteRendererFormulaRuntimeElement formula;

		/**
		 * Constructs a new formula listener.
		 * 
		 * @param formula the formula.
		 */
		public ExtensionListener(final PaletteRendererFormulaRuntimeElement formula) {
			this.formula = formula;
		}

		private void createRuntime(final ExtensionReference reference) {
			try {
				if (reference != null) {
					final PaletteRendererFormulaExtensionRuntime formulaRuntime = MandelbrotRegistry.getInstance().getPaletteRendererFormulaExtension(reference.getExtensionId()).createExtensionRuntime();
					formula.setFormulaRuntime(formulaRuntime);
				}
				else {
					setFormulaRuntime(null);
				}
			}
			catch (final ExtensionNotFoundException e) {
				e.printStackTrace();
			}
			catch (final ExtensionException e) {
				e.printStackTrace();
			}
		}

		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED: {
					createRuntime((ExtensionReference) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
}
