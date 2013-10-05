/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.pathReplacement.PathReplacementConfigElementXMLExporter;
import net.sf.jame.core.util.AbstractConfigElementNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class PathReplacementElementNodeActionXMLExporterRuntime extends AbstractConfigElementNodeActionXMLExporterRuntime<PathReplacementConfigElement> {
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected PathReplacementConfigElementXMLExporter createExporter() {
		return new PathReplacementConfigElementXMLExporter();
	}
}
