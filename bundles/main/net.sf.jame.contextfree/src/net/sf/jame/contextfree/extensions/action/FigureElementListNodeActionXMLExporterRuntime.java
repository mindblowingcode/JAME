/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.figure.FigureConfigElement;
import net.sf.jame.contextfree.figure.FigureConfigElementXMLExporter;
import net.sf.jame.core.util.AbstractConfigElementListNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class FigureElementListNodeActionXMLExporterRuntime extends AbstractConfigElementListNodeActionXMLExporterRuntime<FigureConfigElement> {
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementListNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected FigureConfigElementXMLExporter createExporter() {
		return new FigureConfigElementXMLExporter();
	}
}
