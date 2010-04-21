/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.replacement;

import net.sf.jame.core.tree.NodeValue;

public class SingleReplacementConfigElementNodeValue extends NodeValue<SingleReplacementConfigElement> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public SingleReplacementConfigElementNodeValue(final SingleReplacementConfigElement value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public SingleReplacementConfigElement getValueClone() {
		if (getValue() != null) {
			return getValue().clone();
		}
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public SingleReplacementConfigElementNodeValue clone() {
		return new SingleReplacementConfigElementNodeValue(getValueClone());
	}
}
