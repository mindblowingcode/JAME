/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.pathReplacement.PathReplacementConfigElementXMLImporter;
import net.sf.jame.core.util.AbstractConfigElementListNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class PathReplacementElementListNodeActionXMLImporterRuntime extends AbstractConfigElementListNodeActionXMLImporterRuntime<PathReplacementConfigElement> {
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementListNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected PathReplacementConfigElementXMLImporter createImporter() {
		return new PathReplacementConfigElementXMLImporter();
	}
}
