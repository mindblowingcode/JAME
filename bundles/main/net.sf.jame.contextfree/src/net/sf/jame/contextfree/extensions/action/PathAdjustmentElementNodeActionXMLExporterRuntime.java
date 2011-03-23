/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElementXMLExporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class PathAdjustmentElementNodeActionXMLExporterRuntime extends ConfigElementNodeActionXMLExporterRuntime<PathAdjustmentConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected PathAdjustmentConfigElementXMLExporter createExporter() {
		return new PathAdjustmentConfigElementXMLExporter();
	}
}
