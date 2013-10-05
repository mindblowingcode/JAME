/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions;

import java.util.ResourceBundle;

import net.sf.jame.core.util.Resources;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeExtensionResources extends Resources {
	private static final ContextFreeExtensionResources instance = new ContextFreeExtensionResources();

	private ContextFreeExtensionResources() {
		super(ResourceBundle.getBundle("contextfree_extension_resources"));
	}

	/**
	 * Returns the instance.
	 * 
	 * @return the instance.
	 */
	public static ContextFreeExtensionResources getInstance() {
		return instance;
	}
}
