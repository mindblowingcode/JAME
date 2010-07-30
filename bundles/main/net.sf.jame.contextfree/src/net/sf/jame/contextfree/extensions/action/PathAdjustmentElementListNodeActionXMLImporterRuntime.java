/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElementXMLImporter;
import net.sf.jame.core.util.ConfigElementListNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class PathAdjustmentElementListNodeActionXMLImporterRuntime extends ConfigElementListNodeActionXMLImporterRuntime<PathAdjustmentConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementListNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected PathAdjustmentConfigElementXMLImporter createImporter() {
		return new PathAdjustmentConfigElementXMLImporter();
	}
}
