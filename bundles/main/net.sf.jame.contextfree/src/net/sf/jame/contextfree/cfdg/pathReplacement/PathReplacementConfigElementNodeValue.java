/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathReplacement;

import net.sf.jame.core.tree.NodeValue;

public class PathReplacementConfigElementNodeValue extends NodeValue<PathReplacementConfigElement> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public PathReplacementConfigElementNodeValue(final PathReplacementConfigElement value) {
		super(value);
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#getValueClone()
	 */
	@Override
	public PathReplacementConfigElement getValueClone() {
		if (getValue() != null) {
			return getValue().clone();
		}
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.NodeValue#clone()
	 */
	@Override
	public PathReplacementConfigElementNodeValue clone() {
		return new PathReplacementConfigElementNodeValue(getValueClone());
	}
}
