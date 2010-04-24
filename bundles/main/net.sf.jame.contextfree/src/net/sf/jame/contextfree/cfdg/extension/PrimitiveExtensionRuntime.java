/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.extension;

import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.extension.ExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class PrimitiveExtensionRuntime extends ExtensionRuntime {
	/**
	 * @param contextFreeContext
	 */
	public abstract void draw(ContextFreeContext contextFreeContext);

	/**
	 * @param contextFreeContext
	 */
	public abstract void prepare(ContextFreeContext contextFreeContext);
	
	/**
	 * @return
	 */
	public abstract String getName();
	
	/**
	 * @param value
	 * @return
	 */
	protected float norm(float value) {
		return value;
	}
}
