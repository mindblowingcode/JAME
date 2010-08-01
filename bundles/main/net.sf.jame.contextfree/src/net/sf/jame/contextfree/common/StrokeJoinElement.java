/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.common;

import java.lang.String;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class StrokeJoinElement extends ValueConfigElement<String> {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "StrokeJoin";

	/**
	 * Constructs a new element.
	 */
	public StrokeJoinElement(String defaultValue) {
		super(StrokeJoinElement.CLASS_ID, defaultValue);
	}

	/**
	 * @return
	 */
	@Override
	public StrokeJoinElement clone() {
		return new StrokeJoinElement(getValue());
	}

	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		StrokeJoinElement element = (StrokeJoinElement) source;
		setValue(element.getValue());
	}
}
