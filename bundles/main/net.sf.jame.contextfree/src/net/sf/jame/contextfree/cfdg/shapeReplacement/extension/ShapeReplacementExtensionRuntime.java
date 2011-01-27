/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.shapeReplacement.extension;

import net.sf.jame.contextfree.renderer.ContextFreeBounds;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class ShapeReplacementExtensionRuntime<T extends ShapeReplacementExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param context
	 * @param state
	 * @param globalBounds
	 * @param shapeBounds
	 */
	public abstract void createShapes(ContextFreeContext context, ContextFreeState state, ContextFreeBounds globalBounds, ContextFreeBounds shapeBounds);
}
