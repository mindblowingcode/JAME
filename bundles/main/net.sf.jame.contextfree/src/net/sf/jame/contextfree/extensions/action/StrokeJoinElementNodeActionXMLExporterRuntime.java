/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.common.StrokeJoinElement;
import net.sf.jame.contextfree.common.StrokeJoinElementXMLExporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class StrokeJoinElementNodeActionXMLExporterRuntime extends ConfigElementNodeActionXMLExporterRuntime<StrokeJoinElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected StrokeJoinElementXMLExporter createExporter() {
		return new StrokeJoinElementXMLExporter();
	}
}
