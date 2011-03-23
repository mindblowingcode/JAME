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
package net.sf.jame.mandelbrot.extensions.fractal.rendering;

import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class Z4ConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<Z4Config> createXMLImporter() {
		return new Z4ConfigXMLImporter();
	}

	private class Z4ConfigXMLImporter extends AbstractRenderingFormulaConfigXMLImporter<Z4Config> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.rendering.AbstractRenderingFormulaConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected Z4Config createExtensionConfig() {
			return new Z4Config();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.rendering.AbstractRenderingFormulaConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "Z4Config";
		}
	}
}
