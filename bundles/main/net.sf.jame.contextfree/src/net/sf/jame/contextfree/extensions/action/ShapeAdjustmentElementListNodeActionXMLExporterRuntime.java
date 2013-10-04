/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.shapeAdjustment.ShapeAdjustmentConfigElementXMLExporter;
import net.sf.jame.core.util.AbstractConfigElementListNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class ShapeAdjustmentElementListNodeActionXMLExporterRuntime extends AbstractConfigElementListNodeActionXMLExporterRuntime<ShapeAdjustmentConfigElement> {
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementListNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected ShapeAdjustmentConfigElementXMLExporter createExporter() {
		return new ShapeAdjustmentConfigElementXMLExporter();
	}
}
