/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.CFDGConfigElement;
import net.sf.jame.contextfree.cfdg.CFDGConfigElementXMLImporter;
import net.sf.jame.core.util.AbstractConfigElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class CFDGElementNodeActionXMLImporterRuntime extends AbstractConfigElementNodeActionXMLImporterRuntime<CFDGConfigElement> {
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected CFDGConfigElementXMLImporter createImporter() {
		return new CFDGConfigElementXMLImporter();
	}
}
