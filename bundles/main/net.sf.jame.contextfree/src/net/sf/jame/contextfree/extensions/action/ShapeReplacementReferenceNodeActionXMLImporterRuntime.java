/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionConfig;
import net.sf.jame.core.util.ConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class ShapeReplacementReferenceNodeActionXMLImporterRuntime extends ConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime<ShapeReplacementExtensionConfig> {
	/**
	 * 
	 */
	public ShapeReplacementReferenceNodeActionXMLImporterRuntime() {
		super(ContextFreeRegistry.getInstance().getShapeReplacementRegistry());
	}
}
