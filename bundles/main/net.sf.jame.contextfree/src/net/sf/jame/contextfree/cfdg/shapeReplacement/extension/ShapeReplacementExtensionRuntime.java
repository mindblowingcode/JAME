/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.shapeReplacement.extension;

import net.sf.jame.contextfree.renderer.support.CFBuilder;
import net.sf.jame.contextfree.renderer.support.CFRule;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class ShapeReplacementExtensionRuntime<T extends ShapeReplacementExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param context
	 */
	public abstract void process(CFBuilder context, CFRule rule);
}
