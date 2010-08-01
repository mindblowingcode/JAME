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
public class StrokeCapElement extends ValueConfigElement<String> {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "StrokeCap";

	/**
	 * Constructs a new element.
	 */
	public StrokeCapElement(String defaultValue) {
		super(StrokeCapElement.CLASS_ID, defaultValue);
	}

	/**
	 * @return
	 */
	@Override
	public StrokeCapElement clone() {
		return new StrokeCapElement(getValue());
	}

	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		StrokeCapElement element = (StrokeCapElement) source;
		setValue(element.getValue());
	}
}
