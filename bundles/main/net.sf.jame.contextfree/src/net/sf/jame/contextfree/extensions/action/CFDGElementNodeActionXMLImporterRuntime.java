/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.CFDGConfigElement;
import net.sf.jame.contextfree.cfdg.CFDGConfigElementXMLImporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class CFDGElementNodeActionXMLImporterRuntime extends ConfigElementNodeActionXMLImporterRuntime<CFDGConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected CFDGConfigElementXMLImporter createImporter() {
		return new CFDGConfigElementXMLImporter();
	}
}
