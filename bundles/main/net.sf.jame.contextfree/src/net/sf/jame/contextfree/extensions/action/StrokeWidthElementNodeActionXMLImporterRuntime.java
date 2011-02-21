/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.common.StrokeWidthElement;
import net.sf.jame.contextfree.common.StrokeWidthElementXMLImporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class StrokeWidthElementNodeActionXMLImporterRuntime extends ConfigElementNodeActionXMLImporterRuntime<StrokeWidthElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected StrokeWidthElementXMLImporter createImporter() {
		return new StrokeWidthElementXMLImporter();
	}
}
