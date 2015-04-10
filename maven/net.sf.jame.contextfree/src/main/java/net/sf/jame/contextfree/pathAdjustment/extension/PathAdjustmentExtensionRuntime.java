/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.pathAdjustment.extension;

import net.sf.jame.contextfree.renderer.support.CFModification;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class PathAdjustmentExtensionRuntime<T extends PathAdjustmentExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param state
	 */
	public abstract void apply(CFModification mod);
}
