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
package net.sf.jame.mandelbrot.extensions.outcolouringFormula;

import java.util.List;

import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.colorRenderer.ColorRendererConfigElement;
import net.sf.jame.mandelbrot.colorRenderer.extension.ColorRendererExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class UniversalConfig extends AbstractOutcolouringPaletteConfig {
	private static final String DEFAULT_COLOR_RENDERER_EXTENSION_ID = "twister.mandelbrot.color.renderer.sin";
	private static final long serialVersionUID = 1L;
	private ColorRendererConfigElement colorRendererElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		super.createConfigElements();
		colorRendererElement = new ColorRendererConfigElement();
	}

	/**
	 * 
	 */
	@Override
	protected void initConfigElements() {
		super.initConfigElements();
		try {
			colorRendererElement.setReference(MandelbrotRegistry.getInstance().getColorRendererExtension(UniversalConfig.DEFAULT_COLOR_RENDERER_EXTENSION_ID).createConfigurableExtensionReference());
		}
		catch (final Exception e) {
			throw new Error(e);
		}
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = super.getConfigElements();
		elements.add(colorRendererElement);
		return elements;
	}

	/**
	 * @return the colorRenderer
	 */
	public ConfigurableExtensionReference<ColorRendererExtensionConfig> getColorRenderer() {
		return colorRendererElement.getReference();
	}

	/**
	 * @param colorRendererElement the colorRenderer to set
	 */
	public void setColorRenderer(final ConfigurableExtensionReference<ColorRendererExtensionConfig> reference) {
		colorRendererElement.setReference(reference);
	}

	/**
	 * @return
	 */
	public ColorRendererConfigElement getColorRendererElement() {
		return colorRendererElement;
	}

	/**
	 * @return
	 */
	@Override
	public UniversalConfig clone() {
		final UniversalConfig config = new UniversalConfig();
		config.setPaletteRenderer(getPaletteRenderer().clone());
		config.setColorRenderer(getColorRenderer().clone());
		return config;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		final UniversalConfig other = (UniversalConfig) obj;
		if (colorRendererElement == null) {
			if (other.colorRendererElement != null) {
				return false;
			}
		}
		else if (!colorRendererElement.equals(other.colorRendererElement)) {
			return false;
		}
		return true;
	}
}
