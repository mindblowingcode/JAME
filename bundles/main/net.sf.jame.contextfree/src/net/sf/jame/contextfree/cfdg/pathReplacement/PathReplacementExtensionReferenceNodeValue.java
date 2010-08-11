/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathReplacement;

import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionConfig;
import net.sf.jame.core.common.ExtensionReferenceElementNodeValue;
import net.sf.jame.core.extension.ConfigurableExtensionReference;

/**
 * @author Andrea Medeghini
 */
public class PathReplacementExtensionReferenceNodeValue extends ExtensionReferenceElementNodeValue<ConfigurableExtensionReference<PathReplacementExtensionConfig>> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public PathReplacementExtensionReferenceNodeValue(final ConfigurableExtensionReference<PathReplacementExtensionConfig> value) {
		super(value);
	}
}
