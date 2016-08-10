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
package net.sf.jame.mandelbrot.paletteRenderer;

import net.sf.jame.core.common.ExtensionReferenceElement;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.paletteRenderer.extension.PaletteRendererExtensionConfig;
import net.sf.jame.mandelbrot.paletteRenderer.extension.PaletteRendererExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class PaletteRendererRuntimeElement extends RuntimeElement {
	private PaletteRendererExtensionRuntime<?> rendererRuntime;
	private PaletteRendererConfigElement rendererElement;
	private ExtensionListener extensionListener;

	/**
	 * Constructs a new renderer element.
	 * 
	 * @param rendererElement
	 */
	public PaletteRendererRuntimeElement(final PaletteRendererConfigElement rendererElement) {
		if (rendererElement == null) {
			throw new IllegalArgumentException("rendererElement is null");
		}
		this.rendererElement = rendererElement;
		createRuntime(rendererElement.getReference());
		extensionListener = new ExtensionListener();
		rendererElement.getExtensionElement().addChangeListener(extensionListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((rendererElement != null) && (extensionListener != null)) {
			rendererElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if (rendererRuntime != null) {
			rendererRuntime.dispose();
			rendererRuntime = null;
		}
		rendererElement = null;
		super.dispose();
	}

	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<PaletteRendererExtensionConfig> reference) {
		try {
			if (reference != null) {
				final PaletteRendererExtensionRuntime rendererRuntime = MandelbrotRegistry.getInstance().getPaletteRendererExtension(reference.getExtensionId()).createExtensionRuntime();
				final PaletteRendererExtensionConfig rendererConfig = reference.getExtensionConfig();
				rendererRuntime.setConfig(rendererConfig);
				setRendererRuntime(rendererRuntime);
			}
			else {
				setRendererRuntime(null);
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
	 * @return the rendererRuntime
	 */
	public PaletteRendererExtensionRuntime<?> getRendererRuntime() {
		return rendererRuntime;
	}

	private void setRendererRuntime(final PaletteRendererExtensionRuntime<?> rendererRuntime) {
		if (this.rendererRuntime != null) {
			this.rendererRuntime.dispose();
		}
		this.rendererRuntime = rendererRuntime;
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean rendererChanged = (rendererRuntime != null) && rendererRuntime.isChanged();
		return super.isChanged() || rendererChanged;
	}

	private class ExtensionListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		@SuppressWarnings("unchecked")
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED: {
					createRuntime((ConfigurableExtensionReference<PaletteRendererExtensionConfig>) e.getParams()[0]);
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
