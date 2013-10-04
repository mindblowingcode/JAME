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
package net.sf.jame.mandelbrot.colorRendererFormula.extension;

import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.extension.osgi.OSGiExtensionBuilder;
import net.sf.jame.core.extension.osgi.OSGiExtensionRegistry;

/**
 * @author Andrea Medeghini
 */
public class ColorRendererFormulaExtensionRegistry extends OSGiExtensionRegistry<ColorRendererFormulaExtensionRuntime> {
	/**
	 * the extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "net.sf.jame.mandelbrot.extensions";
	/**
	 * the configuration element name.
	 */
	public static final String CONFIGURATION_ELEMENT_NAME = "colorRendererFormula";

	/**
	 * Constructs a new registry.
	 */
	public ColorRendererFormulaExtensionRegistry() {
		super(ColorRendererFormulaExtensionRegistry.EXTENSION_POINT_NAME, new OSGiExtensionBuilder<ColorRendererFormulaExtensionRuntime>(ColorRendererFormulaExtensionRegistry.CONFIGURATION_ELEMENT_NAME));
	}

	/**
	 * @see net.sf.jame.core.extension.osgi.OSGiExtensionRegistry#getExtension(java.lang.String)
	 */
	@Override
	public Extension<ColorRendererFormulaExtensionRuntime> getExtension(final String extensionId) throws ExtensionNotFoundException {
		if (extensionId.equals("twister.mandelbrot.color.renderer.formula.time")) {
			return super.getExtension("twister.mandelbrot.color.renderer.formula.zero");
		}
		return super.getExtension(extensionId);
	}
}
