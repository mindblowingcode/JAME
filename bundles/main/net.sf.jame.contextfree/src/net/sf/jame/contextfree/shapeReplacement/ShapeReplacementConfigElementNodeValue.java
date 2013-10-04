/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.shapeReplacement;

import net.sf.jame.core.tree.NodeValue;

public class ShapeReplacementConfigElementNodeValue extends NodeValue<ShapeReplacementConfigElement> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public ShapeReplacementConfigElementNodeValue(final ShapeReplacementConfigElement value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public ShapeReplacementConfigElement getValueClone() {
		if (getValue() != null) {
			return getValue().clone();
		}
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public ShapeReplacementConfigElementNodeValue clone() {
		return new ShapeReplacementConfigElementNodeValue(getValueClone());
	}
}
