/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathAdjustment.extension;

import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class PathAdjustmentExtensionRuntime<T extends PathAdjustmentExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param state
	 * @param times 
	 */
	public abstract void configureState(ContextFreeState state, int times);

	/**
	 * @param state
	 * @param time
	 */
	public abstract void updateState(ContextFreeState state, int time);
}
