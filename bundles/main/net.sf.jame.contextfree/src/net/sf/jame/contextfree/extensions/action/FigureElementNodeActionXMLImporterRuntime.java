/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.figure.FigureConfigElement;
import net.sf.jame.contextfree.cfdg.figure.FigureConfigElementXMLImporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class FigureElementNodeActionXMLImporterRuntime extends ConfigElementNodeActionXMLImporterRuntime<FigureConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected FigureConfigElementXMLImporter createImporter() {
		return new FigureConfigElementXMLImporter();
	}
}
