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
package net.sf.jame.core.util;

import net.sf.jame.core.creator.extension.CreatorExtensionRuntime;
import net.sf.jame.core.extension.ConfigurableExtension;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.scripting.JSException;
import net.sf.jame.core.scripting.JSExtension;

/**
 * @author Andrea Medeghini
 */
public class ReferenceCreatorRuntime extends CreatorExtensionRuntime {
	/**
	 * @see net.sf.jame.core.creator.extension.CreatorExtensionRuntime#create(java.lang.Object[])
	 */
	@Override
	public Object create(final Object... args) throws JSException {
		if ((args != null) && (args.length != 1) && !(args[0] instanceof JSExtension)) {
			throw new JSException("Reference creator requires one argument");
		}
		if (((JSExtension)args[0]).getExtension() instanceof ConfigurableExtension) {
			try {
				return (((ConfigurableExtension<?, ?>)((JSExtension)args[0]).getExtension())).createConfigurableExtensionReference();
			} catch (ExtensionException e) {
				throw new JSException("Cannot create reference", e);
			}
		} else {
			return ((JSExtension)args[0]).getExtension().getExtensionReference();
		}
	}
}
