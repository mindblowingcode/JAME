/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathAdjustment;

import net.sf.jame.core.common.ExtensionReferenceElementNodeValue;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.contextfree.cfdg.pathAdjustment.extension.PathAdjustmentExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class PathAdjustmentExtensionReferenceNodeValue extends ExtensionReferenceElementNodeValue<ConfigurableExtensionReference<PathAdjustmentExtensionConfig>> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public PathAdjustmentExtensionReferenceNodeValue(final ConfigurableExtensionReference<PathAdjustmentExtensionConfig> value) {
		super(value);
	}
}
