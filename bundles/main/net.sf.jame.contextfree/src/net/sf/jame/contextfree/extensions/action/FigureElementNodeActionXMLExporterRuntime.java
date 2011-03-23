/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.figure.FigureConfigElement;
import net.sf.jame.contextfree.cfdg.figure.FigureConfigElementXMLExporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class FigureElementNodeActionXMLExporterRuntime extends ConfigElementNodeActionXMLExporterRuntime<FigureConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected FigureConfigElementXMLExporter createExporter() {
		return new FigureConfigElementXMLExporter();
	}
}
