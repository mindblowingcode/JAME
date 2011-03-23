/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.shapeAdjustment;

import net.sf.jame.core.tree.NodeValue;

public class ShapeAdjustmentConfigElementNodeValue extends NodeValue<ShapeAdjustmentConfigElement> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public ShapeAdjustmentConfigElementNodeValue(final ShapeAdjustmentConfigElement value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public ShapeAdjustmentConfigElement getValueClone() {
		if (getValue() != null) {
			return getValue().clone();
		}
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public ShapeAdjustmentConfigElementNodeValue clone() {
		return new ShapeAdjustmentConfigElementNodeValue(getValueClone());
	}
}
