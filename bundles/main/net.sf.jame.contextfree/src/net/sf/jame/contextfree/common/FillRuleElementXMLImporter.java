/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.common;

import java.lang.String;
import net.sf.jame.core.config.ValueConfigElementXMLImporter;

/**
 * @author Andrea Medeghini
 */
public class FillRuleElementXMLImporter extends ValueConfigElementXMLImporter<String, FillRuleElement> {
	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLImporter#parseValue(java.lang.String)
	 */
	@Override
	protected String parseValue(final String value) {
		return String.valueOf(value);
	}

	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLImporter#createDefaultConfigElement()
	 */
	@Override
	protected FillRuleElement createDefaultConfigElement() {
		return new FillRuleElement("non-zero");
	}
}
