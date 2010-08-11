/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.common;

import net.sf.jame.core.config.ValueConfigElementXMLImporter;

/**
 * @author Andrea Medeghini
 */
public class StrokeCapElementXMLImporter extends ValueConfigElementXMLImporter<String, StrokeCapElement> {
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
	protected StrokeCapElement createDefaultConfigElement() {
		return new StrokeCapElement("miter");
	}
}
