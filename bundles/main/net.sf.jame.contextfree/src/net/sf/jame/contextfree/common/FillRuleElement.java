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
public class FillRuleElement extends ValueConfigElement<String> {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "FillRule";

	/**
	 * Constructs a new element.
	 */
	public FillRuleElement(String defaultValue) {
		super(FillRuleElement.CLASS_ID, defaultValue);
	}

	/**
	 * @return
	 */
	@Override
	public FillRuleElement clone() {
		return new FillRuleElement(getValue());
	}

	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		FillRuleElement element = (FillRuleElement) source;
		setValue(element.getValue());
	}
}
