/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.CFDGConfigElement;
import net.sf.jame.contextfree.cfdg.CFDGConfigElementXMLExporter;
import net.sf.jame.core.util.AbstractConfigElementNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class CFDGElementNodeActionXMLExporterRuntime extends AbstractConfigElementNodeActionXMLExporterRuntime<CFDGConfigElement> {
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected CFDGConfigElementXMLExporter createExporter() {
		return new CFDGConfigElementXMLExporter();
	}
}
