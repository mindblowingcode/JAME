/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.creator;

import net.sf.jame.contextfree.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.core.creator.extension.CreatorExtensionRuntime;
import net.sf.jame.core.scripting.JSException;

/**
 * @author Andrea Medeghini
 */
public class PathAdjustmentCreatorRuntime extends CreatorExtensionRuntime {
	/**
	 * @see net.sf.jame.core.creator.extension.element.CreatorExtensionRuntime#create(java.lang.Object[])
	 */
	@Override
	public Object create(final Object... args) throws JSException {
		if ((args != null) && (args.length > 0)) {
			throw new JSException("PathAdjustmentConfigElement creator requires no arguments");
		}
		return new PathAdjustmentConfigElement();
	}
}