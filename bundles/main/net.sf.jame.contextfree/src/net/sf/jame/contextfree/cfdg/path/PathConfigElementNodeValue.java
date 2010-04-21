/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.path;

import net.sf.jame.core.tree.NodeValue;

public class PathConfigElementNodeValue extends NodeValue<PathConfigElement> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public PathConfigElementNodeValue(final PathConfigElement value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public PathConfigElement getValueClone() {
		if (getValue() != null) {
			return getValue().clone();
		}
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public PathConfigElementNodeValue clone() {
		return new PathConfigElementNodeValue(getValueClone());
	}
}
