/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.replacement;

import net.sf.jame.core.tree.NodeValue;

public class MultiReplacementConfigElementNodeValue extends NodeValue<MultiReplacementConfigElement> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public MultiReplacementConfigElementNodeValue(final MultiReplacementConfigElement value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public MultiReplacementConfigElement getValueClone() {
		if (getValue() != null) {
			return getValue().clone();
		}
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public MultiReplacementConfigElementNodeValue clone() {
		return new MultiReplacementConfigElementNodeValue(getValueClone());
	}
}
