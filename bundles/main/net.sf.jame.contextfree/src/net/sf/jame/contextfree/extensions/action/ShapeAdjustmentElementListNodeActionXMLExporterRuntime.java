/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementXMLExporter;
import net.sf.jame.core.util.ConfigElementListNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class ShapeAdjustmentElementListNodeActionXMLExporterRuntime extends ConfigElementListNodeActionXMLExporterRuntime<ShapeAdjustmentConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementListNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected ShapeAdjustmentConfigElementXMLExporter createExporter() {
		return new ShapeAdjustmentConfigElementXMLExporter();
	}
}
