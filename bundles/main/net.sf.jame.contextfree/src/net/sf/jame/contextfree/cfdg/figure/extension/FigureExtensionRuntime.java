/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.figure.extension;

import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class FigureExtensionRuntime<T extends FigureExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param context 
	 */
	public abstract void register(ContextFreeContext context);
}
