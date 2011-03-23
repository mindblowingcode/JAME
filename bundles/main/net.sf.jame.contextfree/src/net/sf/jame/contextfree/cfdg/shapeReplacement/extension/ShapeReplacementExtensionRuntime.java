/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.shapeReplacement.extension;

import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.support.CFShape;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class ShapeReplacementExtensionRuntime<T extends ShapeReplacementExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param context
	 */
	public abstract void process(ContextFreeContext context, CFShape shape);
}
