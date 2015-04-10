/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.figure.FigureConfigElement;
import net.sf.jame.contextfree.figure.FigureConfigElementXMLExporter;
import net.sf.jame.core.util.AbstractConfigElementNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class FigureElementNodeActionXMLExporterRuntime extends AbstractConfigElementNodeActionXMLExporterRuntime<FigureConfigElement> {
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected FigureConfigElementXMLExporter createExporter() {
		return new FigureConfigElementXMLExporter();
	}
}
