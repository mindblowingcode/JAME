/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.pathReplacement.extension.PathReplacementExtensionConfig;
import net.sf.jame.core.util.AbstractConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class PathReplacementReferenceNodeActionXMLImporterRuntime extends AbstractConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime<PathReplacementExtensionConfig> {
	/**
	 * 
	 */
	public PathReplacementReferenceNodeActionXMLImporterRuntime() {
		super(ContextFreeRegistry.getInstance().getPathReplacementRegistry());
	}
}
