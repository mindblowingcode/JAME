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
package net.sf.jame.twister.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.jame.core.extension.ExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class DefaultAdapterContext implements AdapterContext {
	private static final long serialVersionUID = 1L;
	private final Map<String, Object> attributes = new HashMap<String, Object>();
	private final ExtensionConfig config;

	/**
	 * @param config
	 */
	public DefaultAdapterContext(final ExtensionConfig config) {
		this.config = config;
	}

	/**
	 * @see net.sf.jame.twister.util.AdapterContext.InputAdapterContext#getFinalConfig()
	 */
	public ExtensionConfig getConfig() {
		return config;
	}

	/**
	 * @see net.sf.jame.twister.util.AdapterContext.InputAdapterContext#setAttribute(java.lang.String, java.lang.Object)
	 */
	public void setAttribute(final String name, final Object value) {
		attributes.put(name, value);
	}

	/**
	 * @see net.sf.jame.twister.util.AdapterContext.InputAdapterContext#getAttribute(java.lang.String)
	 */
	public Object getAttribute(final String name) {
		return attributes.get(name);
	}

	/**
	 * @see net.sf.jame.twister.util.AdapterContext#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(final String name) {
		attributes.remove(name);
	}
}
