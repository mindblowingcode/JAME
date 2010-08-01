/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.common.StrokeWidthElement;
import net.sf.jame.contextfree.common.StrokeWidthElementXMLExporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class StrokeWidthElementNodeActionXMLExporterRuntime extends ConfigElementNodeActionXMLExporterRuntime<StrokeWidthElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected StrokeWidthElementXMLExporter createExporter() {
		return new StrokeWidthElementXMLExporter();
	}
}
