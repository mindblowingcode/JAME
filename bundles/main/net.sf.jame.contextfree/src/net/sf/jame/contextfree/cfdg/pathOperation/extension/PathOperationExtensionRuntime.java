/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathOperation.extension;

import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class PathOperationExtensionRuntime<T extends PathOperationExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param contextFreeContext
	 */
	public abstract void draw(ContextFreeContext contextFreeContext);

	/**
	 * @param contextFreeContext
	 */
	public abstract void prepare(ContextFreeContext contextFreeContext);
}
