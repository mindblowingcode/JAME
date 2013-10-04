/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.shapeReplacement.extension;

import net.sf.jame.core.extension.osgi.OSGiConfigurableExtensionBuilder;
import net.sf.jame.core.extension.osgi.OSGiConfigurableExtensionRegistry;

/**
 * @author Andrea Medeghini
 */
public class ShapeReplacementExtensionRegistry extends OSGiConfigurableExtensionRegistry<ShapeReplacementExtensionRuntime<?>, ShapeReplacementExtensionConfig> {
	/**
	 * the extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "net.sf.jame.contextfree.extensions";
	/**
	 * the configuration element name.
	 */
	public static final String CONFIGURATION_ELEMENT_NAME = "shapeReplacement";

	/**
	 * Constructs a new registry.
	 */
	public ShapeReplacementExtensionRegistry() {
		super(ShapeReplacementExtensionRegistry.EXTENSION_POINT_NAME, new OSGiConfigurableExtensionBuilder<ShapeReplacementExtensionRuntime<?>, ShapeReplacementExtensionConfig>(ShapeReplacementExtensionRegistry.CONFIGURATION_ELEMENT_NAME));
	}
}
