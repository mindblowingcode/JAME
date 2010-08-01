/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.common;

import java.lang.Float;
import net.sf.jame.core.config.ValueConfigElementXMLImporter;

/**
 * @author Andrea Medeghini
 */
public class StrokeWidthElementXMLImporter extends ValueConfigElementXMLImporter<Float, StrokeWidthElement> {
	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLImporter#parseValue(java.lang.String)
	 */
	@Override
	protected Float parseValue(final String value) {
		return Float.valueOf(value);
	}

	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLImporter#createDefaultConfigElement()
	 */
	@Override
	protected StrokeWidthElement createDefaultConfigElement() {
		return new StrokeWidthElement(1f);
	}
}
