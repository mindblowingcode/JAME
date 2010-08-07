/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathReplacement.extension;

import net.sf.jame.contextfree.renderer.ContextFreeBounds;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeShape;
import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class PathReplacementExtensionRuntime<T extends PathReplacementExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param context
	 * @param state
	 * @param bounds
	 * @return
	 */
	public abstract ContextFreeShape createShape(ContextFreeContext context, ContextFreeState state, ContextFreeBounds bounds);
}
