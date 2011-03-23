/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.pathAdjustment.extension.PathAdjustmentExtensionConfig;
import net.sf.jame.core.util.ConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class PathAdjustmentReferenceNodeActionXMLImporterRuntime extends ConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime<PathAdjustmentExtensionConfig> {
	/**
	 * 
	 */
	public PathAdjustmentReferenceNodeActionXMLImporterRuntime() {
		super(ContextFreeRegistry.getInstance().getPathAdjustmentRegistry());
	}
}
