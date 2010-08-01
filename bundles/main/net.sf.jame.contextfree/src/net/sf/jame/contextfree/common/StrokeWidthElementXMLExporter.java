/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.common;

import java.lang.Float;
import net.sf.jame.core.config.ValueConfigElementXMLExporter;

/**
 * @author Andrea Medeghini
 */
public class StrokeWidthElementXMLExporter extends ValueConfigElementXMLExporter<Float, StrokeWidthElement> {
	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLExporter#formatValue(java.io.Serializable)
	 */
	@Override
	protected String formatValue(final Float value) {
		return Float.toString(value);
	}
}
