/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg;

import net.sf.jame.core.tree.NodeValue;

public class CFDGConfigElementNodeValue extends NodeValue<CFDGConfigElement> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public CFDGConfigElementNodeValue(final CFDGConfigElement value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public CFDGConfigElement getValueClone() {
		if (getValue() != null) {
			return getValue().clone();
		}
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public CFDGConfigElementNodeValue clone() {
		return new CFDGConfigElementNodeValue(getValueClone());
	}
}
