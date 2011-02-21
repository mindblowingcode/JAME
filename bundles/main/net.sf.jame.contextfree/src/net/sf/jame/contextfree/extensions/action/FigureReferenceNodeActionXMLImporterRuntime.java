/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.core.util.ConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class FigureReferenceNodeActionXMLImporterRuntime extends ConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime<FigureExtensionConfig> {
	/**
	 * 
	 */
	public FigureReferenceNodeActionXMLImporterRuntime() {
		super(ContextFreeRegistry.getInstance().getFigureRegistry());
	}
}
