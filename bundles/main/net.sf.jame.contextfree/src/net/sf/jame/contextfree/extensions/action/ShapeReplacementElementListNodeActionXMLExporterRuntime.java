/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElementXMLExporter;
import net.sf.jame.core.util.ConfigElementListNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class ShapeReplacementElementListNodeActionXMLExporterRuntime extends ConfigElementListNodeActionXMLExporterRuntime<ShapeReplacementConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementListNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected ShapeReplacementConfigElementXMLExporter createExporter() {
		return new ShapeReplacementConfigElementXMLExporter();
	}
}
