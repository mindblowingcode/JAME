/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.common;

import net.sf.jame.core.tree.NodeValue;

/**
 * @author Andrea Medeghini
 */
public class StrokeWidthElementNodeValue extends NodeValue<Float> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public StrokeWidthElementNodeValue(final Float value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public Float getValueClone() {
		return getValue();
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public StrokeWidthElementNodeValue clone() {
		return new StrokeWidthElementNodeValue(getValueClone());
	}
}
