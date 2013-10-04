/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.figure.FigureConfigElement;
import net.sf.jame.contextfree.figure.FigureConfigElementXMLImporter;
import net.sf.jame.core.util.AbstractConfigElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class FigureElementNodeActionXMLImporterRuntime extends AbstractConfigElementNodeActionXMLImporterRuntime<FigureConfigElement> {
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected FigureConfigElementXMLImporter createImporter() {
		return new FigureConfigElementXMLImporter();
	}
}
