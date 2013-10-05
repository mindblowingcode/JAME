/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.shapeAdjustment.extension.ShapeAdjustmentExtensionConfig;
import net.sf.jame.core.util.AbstractConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class ShapeAdjustmentReferenceNodeActionXMLImporterRuntime extends AbstractConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime<ShapeAdjustmentExtensionConfig> {
	/**
	 * 
	 */
	public ShapeAdjustmentReferenceNodeActionXMLImporterRuntime() {
		super(ContextFreeRegistry.getInstance().getShapeAdjustmentRegistry());
	}
}
