/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.pathAdjustment;

import net.sf.jame.core.tree.NodeValue;

public class PathAdjustmentConfigElementNodeValue extends NodeValue<PathAdjustmentConfigElement> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public PathAdjustmentConfigElementNodeValue(final PathAdjustmentConfigElement value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public PathAdjustmentConfigElement getValueClone() {
		if (getValue() != null) {
			return getValue().clone();
		}
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public PathAdjustmentConfigElementNodeValue clone() {
		return new PathAdjustmentConfigElementNodeValue(getValueClone());
	}
}
