/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.common.StrokeCapElement;
import net.sf.jame.contextfree.common.StrokeCapElementXMLImporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class StrokeCapElementNodeActionXMLImporterRuntime extends ConfigElementNodeActionXMLImporterRuntime<StrokeCapElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected StrokeCapElementXMLImporter createImporter() {
		return new StrokeCapElementXMLImporter();
	}
}
