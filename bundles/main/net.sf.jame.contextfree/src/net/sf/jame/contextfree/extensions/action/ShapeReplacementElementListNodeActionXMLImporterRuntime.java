/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.shapeReplacement.ShapeReplacementConfigElementXMLImporter;
import net.sf.jame.core.util.AbstractConfigElementListNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class ShapeReplacementElementListNodeActionXMLImporterRuntime extends AbstractConfigElementListNodeActionXMLImporterRuntime<ShapeReplacementConfigElement> {
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementListNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected ShapeReplacementConfigElementXMLImporter createImporter() {
		return new ShapeReplacementConfigElementXMLImporter();
	}
}
