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
package net.sf.jame.mandelbrot.outcolouringFormula.extension;

import net.sf.jame.core.extension.sl.SLConfigurableExtensionBuilder;
import net.sf.jame.core.extension.sl.SLConfigurableExtensionRegistry;
import net.sf.jame.mandelbrot.outcolouringFormula.extension.OutcolouringFormulaExtensionRuntime;
import net.sf.jame.mandelbrot.outcolouringFormula.extension.OutcolouringFormulaExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class OutcolouringFormulaExtensionRegistry extends SLConfigurableExtensionRegistry<OutcolouringFormulaExtensionRuntime<? extends OutcolouringFormulaExtensionConfig>, OutcolouringFormulaExtensionConfig> {
	/**
	 * the extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "net.sf.jame.mandelbrot.extensions";
	/**
	 * the configuration element name.
	 */
	public static final String CONFIGURATION_ELEMENT_NAME = "OutcolouringFormula";
	/**
	 * the extension descriptor class.
	 */
	public static final Class<? extends OutcolouringFormulaExtensionDescriptor> EXTENSION_DESCRIPTOR_CLASS = OutcolouringFormulaExtensionDescriptor.class;

	/**
	 * Constructs a new registry.
	 */
	public OutcolouringFormulaExtensionRegistry() {
		super(OutcolouringFormulaExtensionRegistry.EXTENSION_DESCRIPTOR_CLASS, OutcolouringFormulaExtensionRegistry.EXTENSION_POINT_NAME, new SLConfigurableExtensionBuilder<OutcolouringFormulaExtensionRuntime<? extends OutcolouringFormulaExtensionConfig>, OutcolouringFormulaExtensionConfig>(OutcolouringFormulaExtensionRegistry.CONFIGURATION_ELEMENT_NAME));
	}
}
