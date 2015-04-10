/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.shapeAdjustment.extension;

import net.sf.jame.contextfree.renderer.support.CFModification;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class ShapeAdjustmentExtensionRuntime<T extends ShapeAdjustmentExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param state
	 */
	public abstract void apply(CFModification mod);

	public boolean isSizeChange() {
		return false;
	}

	public float getSize() {
		return 1f;
	}
}
