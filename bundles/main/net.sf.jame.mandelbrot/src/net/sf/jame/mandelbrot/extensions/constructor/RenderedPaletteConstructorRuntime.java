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
package net.sf.jame.mandelbrot.extensions.constructor;

import net.sf.jame.core.scripting.JSException;
import net.sf.jame.core.scripting.extension.ConstructorExtensionRuntime;
import net.sf.jame.mandelbrot.util.RenderedPalette;
import net.sf.jame.mandelbrot.util.RenderedPaletteParam;

/**
 * @author Andrea Medeghini
 */
public class RenderedPaletteConstructorRuntime extends ConstructorExtensionRuntime {
	/**
	 * @see net.sf.jame.core.scripting.extension.ConstructorExtensionRuntime#create(java.lang.Object[])
	 */
	@Override
	public Object create(final Object... args) throws JSException {
		try {
			RenderedPaletteParam[] params = new RenderedPaletteParam[args.length];
			for (int i = 0; i < args.length; i++) {
				params[i] = (RenderedPaletteParam) args[i];
			}
			return new RenderedPalette(params);
		}
		catch (Exception e) {
			throw new JSException("RenderedPalette constructor requires one or more arguments of type RenderedPaletteParam", e);
		}
	}
}
