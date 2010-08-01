/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.common.StrokeJoinElement;
import net.sf.jame.contextfree.common.StrokeJoinElementXMLImporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class StrokeJoinElementNodeActionXMLImporterRuntime extends ConfigElementNodeActionXMLImporterRuntime<StrokeJoinElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected StrokeJoinElementXMLImporter createImporter() {
		return new StrokeJoinElementXMLImporter();
	}
}
