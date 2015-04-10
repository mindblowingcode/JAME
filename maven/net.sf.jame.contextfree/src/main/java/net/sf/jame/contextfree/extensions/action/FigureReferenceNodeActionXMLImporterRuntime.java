/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.figure.extension.FigureExtensionConfig;
import net.sf.jame.core.util.AbstractConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class FigureReferenceNodeActionXMLImporterRuntime extends AbstractConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime<FigureExtensionConfig> {
	/**
	 * 
	 */
	public FigureReferenceNodeActionXMLImporterRuntime() {
		super(ContextFreeRegistry.getInstance().getFigureRegistry());
	}
}
