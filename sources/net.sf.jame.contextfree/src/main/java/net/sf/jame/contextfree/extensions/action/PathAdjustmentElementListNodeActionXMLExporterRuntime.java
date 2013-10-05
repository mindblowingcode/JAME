/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.pathAdjustment.PathAdjustmentConfigElementXMLExporter;
import net.sf.jame.core.util.AbstractConfigElementListNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class PathAdjustmentElementListNodeActionXMLExporterRuntime extends AbstractConfigElementListNodeActionXMLExporterRuntime<PathAdjustmentConfigElement> {
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementListNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected PathAdjustmentConfigElementXMLExporter createExporter() {
		return new PathAdjustmentConfigElementXMLExporter();
	}
}
