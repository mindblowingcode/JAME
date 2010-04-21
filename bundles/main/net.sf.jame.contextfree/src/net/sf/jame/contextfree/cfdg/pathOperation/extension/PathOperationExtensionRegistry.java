/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathOperation.extension;

import net.sf.jame.core.extension.osgi.OSGiConfigurableExtensionBuilder;
import net.sf.jame.core.extension.osgi.OSGiConfigurableExtensionRegistry;

/**
 * @author Andrea Medeghini
 */
public class PathOperationExtensionRegistry extends OSGiConfigurableExtensionRegistry<PathOperationExtensionRuntime<?>, PathOperationExtensionConfig> {
	/**
	 * the extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "net.sf.jame.contextfree.extensions";
	/**
	 * the configuration element name.
	 */
	public static final String CONFIGURATION_ELEMENT_NAME = "pathOperation";

	/**
	 * Constructs a new registry.
	 */
	public PathOperationExtensionRegistry() {
		super(PathOperationExtensionRegistry.EXTENSION_POINT_NAME, new OSGiConfigurableExtensionBuilder<PathOperationExtensionRuntime<?>, PathOperationExtensionConfig>(PathOperationExtensionRegistry.CONFIGURATION_ELEMENT_NAME));
	}
}
