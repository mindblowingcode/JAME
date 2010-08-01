/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.common.StrokeCapElement;
import net.sf.jame.contextfree.common.StrokeCapElementXMLExporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class StrokeCapElementNodeActionXMLExporterRuntime extends ConfigElementNodeActionXMLExporterRuntime<StrokeCapElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected StrokeCapElementXMLExporter createExporter() {
		return new StrokeCapElementXMLExporter();
	}
}
