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

import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Andrea Medeghini
 */
public class DefaultNodeEditor<T extends Serializable> extends NodeEditor {
	private final Class<? extends NodeValue<T>> nodeClass;

	/**
	 * @param node
	 * @param nodeClass
	 */
	public DefaultNodeEditor(final Node node, final Class<? extends NodeValue<T>> nodeClass) {
		super(node);
		if (nodeClass == null) {
			throw new IllegalArgumentException("nodeClass is null");
		}
		this.nodeClass = nodeClass;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeEditor#createChildNode(net.sf.jame.core.tree.NodeValue)
	 */
	@Override
	protected Node createChildNode(final NodeValue<?> value) {
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
	 */
	@Override
	public Class<? extends NodeValue<T>> getNodeValueType() {
		return nodeClass;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeEditor#createNodeValue(Object)
	 */
	@Override
	public NodeValue<T> createNodeValue(final Object value) {
		try {
			final Constructor<? extends NodeValue<T>> constructor = nodeClass.getConstructor(new Class[] { value.getClass() });
			return constructor.newInstance(value);
		}
		catch (final InstantiationException e) {
			e.printStackTrace();
		}
		catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (final SecurityException e) {
			e.printStackTrace();
		}
		catch (final NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (final IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (final InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
