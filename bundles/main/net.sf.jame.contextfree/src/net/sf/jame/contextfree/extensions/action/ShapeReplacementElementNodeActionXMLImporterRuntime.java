/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElementXMLImporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class ShapeReplacementElementNodeActionXMLImporterRuntime extends ConfigElementNodeActionXMLImporterRuntime<ShapeReplacementConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected ShapeReplacementConfigElementXMLImporter createImporter() {
		return new ShapeReplacementConfigElementXMLImporter();
	}
}
