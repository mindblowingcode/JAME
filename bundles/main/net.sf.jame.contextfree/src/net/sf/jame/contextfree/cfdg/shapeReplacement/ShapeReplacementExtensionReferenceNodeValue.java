/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.shapeReplacement;

import net.sf.jame.core.common.ExtensionReferenceElementNodeValue;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class ShapeReplacementExtensionReferenceNodeValue extends ExtensionReferenceElementNodeValue<ConfigurableExtensionReference<ShapeReplacementExtensionConfig>> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public ShapeReplacementExtensionReferenceNodeValue(final ConfigurableExtensionReference<ShapeReplacementExtensionConfig> value) {
		super(value);
	}
}
