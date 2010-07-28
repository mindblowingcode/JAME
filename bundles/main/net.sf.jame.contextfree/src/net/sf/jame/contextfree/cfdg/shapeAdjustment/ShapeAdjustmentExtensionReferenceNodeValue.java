/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.shapeAdjustment;

import net.sf.jame.core.common.ExtensionReferenceElementNodeValue;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class ShapeAdjustmentExtensionReferenceNodeValue extends ExtensionReferenceElementNodeValue<ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig>> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public ShapeAdjustmentExtensionReferenceNodeValue(final ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> value) {
		super(value);
	}
}
