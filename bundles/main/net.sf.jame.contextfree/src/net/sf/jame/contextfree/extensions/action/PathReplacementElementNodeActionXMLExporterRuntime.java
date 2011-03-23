/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElementXMLExporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class PathReplacementElementNodeActionXMLExporterRuntime extends ConfigElementNodeActionXMLExporterRuntime<PathReplacementConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected PathReplacementConfigElementXMLExporter createExporter() {
		return new PathReplacementConfigElementXMLExporter();
	}
}
