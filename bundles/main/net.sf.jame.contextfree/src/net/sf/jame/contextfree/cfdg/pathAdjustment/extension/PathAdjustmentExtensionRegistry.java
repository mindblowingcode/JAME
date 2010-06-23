/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathAdjustment.extension;

import net.sf.jame.core.extension.osgi.OSGiConfigurableExtensionBuilder;
import net.sf.jame.core.extension.osgi.OSGiConfigurableExtensionRegistry;

/**
 * @author Andrea Medeghini
 */
public class PathAdjustmentExtensionRegistry extends OSGiConfigurableExtensionRegistry<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> {
	/**
	 * the extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "net.sf.jame.contextfree.extensions";
	/**
	 * the configuration element name.
	 */
	public static final String CONFIGURATION_ELEMENT_NAME = "pathAdjustment";

	/**
	 * Constructs a new registry.
	 */
	public PathAdjustmentExtensionRegistry() {
		super(PathAdjustmentExtensionRegistry.EXTENSION_POINT_NAME, new OSGiConfigurableExtensionBuilder<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig>(PathAdjustmentExtensionRegistry.CONFIGURATION_ELEMENT_NAME));
	}
}
