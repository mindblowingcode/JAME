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
package net.sf.jame.core.common;

import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.tree.NodeValue;

/**
 * @author Andrea Medeghini
 * @param <T>
 */
public class ConfigurableExtensionReferenceElementNodeValue<V extends ExtensionConfig, T extends ConfigurableExtensionReference<V>> extends NodeValue<T> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public ConfigurableExtensionReferenceElementNodeValue(final T value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getValueClone() {
		if (getValue() != null) {
			return (T) getValue().clone();
		}
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public ConfigurableExtensionReferenceElementNodeValue<V, T> clone() {
		return new ConfigurableExtensionReferenceElementNodeValue<V, T>(getValueClone());
	}
}
