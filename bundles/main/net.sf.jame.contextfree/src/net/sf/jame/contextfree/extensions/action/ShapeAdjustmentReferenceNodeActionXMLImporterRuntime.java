/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionConfig;
import net.sf.jame.core.util.ConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class ShapeAdjustmentReferenceNodeActionXMLImporterRuntime extends ConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime<ShapeAdjustmentExtensionConfig> {
	/**
	 * 
	 */
	public ShapeAdjustmentReferenceNodeActionXMLImporterRuntime() {
		super(ContextFreeRegistry.getInstance().getShapeAdjustmentRegistry());
	}
}
