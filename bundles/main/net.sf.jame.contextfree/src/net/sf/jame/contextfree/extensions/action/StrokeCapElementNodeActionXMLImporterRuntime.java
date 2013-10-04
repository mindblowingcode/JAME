/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.common.StrokeCapElement;
import net.sf.jame.contextfree.common.StrokeCapElementXMLImporter;
import net.sf.jame.core.util.AbstractConfigElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class StrokeCapElementNodeActionXMLImporterRuntime extends AbstractConfigElementNodeActionXMLImporterRuntime<StrokeCapElement> {
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected StrokeCapElementXMLImporter createImporter() {
		return new StrokeCapElementXMLImporter();
	}
}
