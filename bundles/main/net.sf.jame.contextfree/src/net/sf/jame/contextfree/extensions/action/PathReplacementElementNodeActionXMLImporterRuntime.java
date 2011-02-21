/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElementXMLImporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class PathReplacementElementNodeActionXMLImporterRuntime extends ConfigElementNodeActionXMLImporterRuntime<PathReplacementConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected PathReplacementConfigElementXMLImporter createImporter() {
		return new PathReplacementConfigElementXMLImporter();
	}
}
