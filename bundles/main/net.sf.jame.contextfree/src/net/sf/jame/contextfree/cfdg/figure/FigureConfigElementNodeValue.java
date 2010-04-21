/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.figure;

import net.sf.jame.core.tree.NodeValue;

public class FigureConfigElementNodeValue extends NodeValue<FigureConfigElement> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public FigureConfigElementNodeValue(final FigureConfigElement value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public FigureConfigElement getValueClone() {
		if (getValue() != null) {
			return getValue().clone();
		}
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public FigureConfigElementNodeValue clone() {
		return new FigureConfigElementNodeValue(getValueClone());
	}
}
