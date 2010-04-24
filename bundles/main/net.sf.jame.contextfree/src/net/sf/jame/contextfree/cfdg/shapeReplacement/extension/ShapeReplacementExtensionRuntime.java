/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.shapeReplacement.extension;

import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class ShapeReplacementExtensionRuntime<T extends ShapeReplacementExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param contextFreeContext
	 */
	public abstract void draw(ContextFreeContext contextFreeContext);

	/**
	 * @param contextFreeContext
	 */
	public abstract void prepare(ContextFreeContext contextFreeContext);
}
