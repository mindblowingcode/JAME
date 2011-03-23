/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.shapeAdjustment.extension;

import net.sf.jame.core.extension.osgi.OSGiConfigurableExtensionBuilder;
import net.sf.jame.core.extension.osgi.OSGiConfigurableExtensionRegistry;

/**
 * @author Andrea Medeghini
 */
public class ShapeAdjustmentExtensionRegistry extends OSGiConfigurableExtensionRegistry<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> {
	/**
	 * the extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "net.sf.jame.contextfree.extensions";
	/**
	 * the configuration element name.
	 */
	public static final String CONFIGURATION_ELEMENT_NAME = "shapeAdjustment";

	/**
	 * Constructs a new registry.
	 */
	public ShapeAdjustmentExtensionRegistry() {
		super(ShapeAdjustmentExtensionRegistry.EXTENSION_POINT_NAME, new OSGiConfigurableExtensionBuilder<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig>(ShapeAdjustmentExtensionRegistry.CONFIGURATION_ELEMENT_NAME));
	}
}
