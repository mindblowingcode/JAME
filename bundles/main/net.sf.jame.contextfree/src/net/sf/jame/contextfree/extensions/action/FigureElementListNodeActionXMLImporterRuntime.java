/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.figure.FigureConfigElement;
import net.sf.jame.contextfree.cfdg.figure.FigureConfigElementXMLImporter;
import net.sf.jame.core.util.ConfigElementListNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class FigureElementListNodeActionXMLImporterRuntime extends ConfigElementListNodeActionXMLImporterRuntime<FigureConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementListNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected FigureConfigElementXMLImporter createImporter() {
		return new FigureConfigElementXMLImporter();
	}
}
