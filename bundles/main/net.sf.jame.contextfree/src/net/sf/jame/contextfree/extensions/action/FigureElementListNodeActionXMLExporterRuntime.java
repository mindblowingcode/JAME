/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.figure.FigureConfigElement;
import net.sf.jame.contextfree.cfdg.figure.FigureConfigElementXMLExporter;
import net.sf.jame.core.util.ConfigElementListNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class FigureElementListNodeActionXMLExporterRuntime extends ConfigElementListNodeActionXMLExporterRuntime<FigureConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementListNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected FigureConfigElementXMLExporter createExporter() {
		return new FigureConfigElementXMLExporter();
	}
}
