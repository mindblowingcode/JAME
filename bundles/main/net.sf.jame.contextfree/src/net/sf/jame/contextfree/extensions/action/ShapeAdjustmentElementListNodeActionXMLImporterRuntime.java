/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementXMLImporter;
import net.sf.jame.core.util.ConfigElementListNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class ShapeAdjustmentElementListNodeActionXMLImporterRuntime extends ConfigElementListNodeActionXMLImporterRuntime<ShapeAdjustmentConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementListNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected ShapeAdjustmentConfigElementXMLImporter createImporter() {
		return new ShapeAdjustmentConfigElementXMLImporter();
	}
}
