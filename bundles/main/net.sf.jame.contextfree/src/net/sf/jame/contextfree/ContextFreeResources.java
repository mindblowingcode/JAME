/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree;

import java.util.ResourceBundle;

import net.sf.jame.core.util.Resources;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeResources extends Resources {
	private static final ContextFreeResources instance = new ContextFreeResources();

	private ContextFreeResources() {
		super(ResourceBundle.getBundle(ContextFreeResources.class.getPackage().getName() + ".resources"));
	}

	/**
	 * Returns the instance.
	 * 
	 * @return the instance.
	 */
	public static ContextFreeResources getInstance() {
		return instance;
	}
}
