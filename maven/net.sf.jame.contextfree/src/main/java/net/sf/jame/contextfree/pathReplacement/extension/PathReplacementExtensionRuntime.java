/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.pathReplacement.extension;

import net.sf.jame.contextfree.renderer.support.CFRule;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class PathReplacementExtensionRuntime<T extends PathReplacementExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param rule 
	 */
	public abstract void process(CFRule rule);
}
