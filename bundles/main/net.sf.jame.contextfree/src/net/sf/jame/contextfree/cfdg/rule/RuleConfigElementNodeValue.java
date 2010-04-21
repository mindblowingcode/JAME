/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.rule;

import net.sf.jame.core.tree.NodeValue;

public class RuleConfigElementNodeValue extends NodeValue<RuleConfigElement> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public RuleConfigElementNodeValue(final RuleConfigElement value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public RuleConfigElement getValueClone() {
		if (getValue() != null) {
			return getValue().clone();
		}
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public RuleConfigElementNodeValue clone() {
		return new RuleConfigElementNodeValue(getValueClone());
	}
}
