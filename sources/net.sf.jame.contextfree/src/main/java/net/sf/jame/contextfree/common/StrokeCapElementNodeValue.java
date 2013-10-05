/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.common;

import net.sf.jame.core.tree.NodeValue;

/**
 * @author Andrea Medeghini
 */
public class StrokeCapElementNodeValue extends NodeValue<String> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public StrokeCapElementNodeValue(final String value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public String getValueClone() {
		return getValue();
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public StrokeCapElementNodeValue clone() {
		return new StrokeCapElementNodeValue(getValueClone());
	}
}
