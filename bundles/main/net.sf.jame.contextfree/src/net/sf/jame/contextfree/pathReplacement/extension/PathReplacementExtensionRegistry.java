/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.pathReplacement.extension;

import net.sf.jame.core.extension.osgi.OSGiConfigurableExtensionBuilder;
import net.sf.jame.core.extension.osgi.OSGiConfigurableExtensionRegistry;

/**
 * @author Andrea Medeghini
 */
public class PathReplacementExtensionRegistry extends OSGiConfigurableExtensionRegistry<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> {
	/**
	 * the extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "net.sf.jame.contextfree.extensions";
	/**
	 * the configuration element name.
	 */
	public static final String CONFIGURATION_ELEMENT_NAME = "pathReplacement";

	/**
	 * Constructs a new registry.
	 */
	public PathReplacementExtensionRegistry() {
		super(PathReplacementExtensionRegistry.EXTENSION_POINT_NAME, new OSGiConfigurableExtensionBuilder<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig>(PathReplacementExtensionRegistry.CONFIGURATION_ELEMENT_NAME));
	}
}
