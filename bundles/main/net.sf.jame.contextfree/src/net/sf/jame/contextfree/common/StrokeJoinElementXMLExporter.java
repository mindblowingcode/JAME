/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.common;

import java.lang.String;
import net.sf.jame.core.config.ValueConfigElementXMLExporter;

/**
 * @author Andrea Medeghini
 */
public class StrokeJoinElementXMLExporter extends ValueConfigElementXMLExporter<String, StrokeJoinElement> {
	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLExporter#formatValue(java.io.Serializable)
	 */
	@Override
	protected String formatValue(final String value) {
		return value;
	}
}
