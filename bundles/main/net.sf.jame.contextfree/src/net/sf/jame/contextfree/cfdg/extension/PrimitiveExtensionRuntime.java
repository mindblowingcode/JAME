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
	public abstract void draw(ContextFreeContext contextFreeContext);

	public abstract String getName();
	
	protected float norm(float value) {
		return value;
	}
}
