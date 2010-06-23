/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.shapeReplacement.extension;

import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeLimits;
import net.sf.jame.contextfree.renderer.ContextFreeNode;
import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class ShapeReplacementExtensionRuntime<T extends ShapeReplacementExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param context
	 * @param state
	 * @param limits
	 * @return
	 */
	public abstract ContextFreeNode buildNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits);
}
