/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathOperation;

import net.sf.jame.core.tree.NodeValue;

public class PathOperationConfigElementNodeValue extends NodeValue<PathOperationConfigElement> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public PathOperationConfigElementNodeValue(final PathOperationConfigElement value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public PathOperationConfigElement getValueClone() {
		if (getValue() != null) {
			return getValue().clone();
		}
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public PathOperationConfigElementNodeValue clone() {
		return new PathOperationConfigElementNodeValue(getValueClone());
	}
}
