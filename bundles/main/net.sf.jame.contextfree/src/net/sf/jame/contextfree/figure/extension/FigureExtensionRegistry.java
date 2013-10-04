/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.figure.extension;

import net.sf.jame.core.extension.osgi.OSGiConfigurableExtensionBuilder;
import net.sf.jame.core.extension.osgi.OSGiConfigurableExtensionRegistry;

/**
 * @author Andrea Medeghini
 */
public class FigureExtensionRegistry extends OSGiConfigurableExtensionRegistry<FigureExtensionRuntime<?>, FigureExtensionConfig> {
	/**
	 * the extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "net.sf.jame.contextfree.extensions";
	/**
	 * the configuration element name.
	 */
	public static final String CONFIGURATION_ELEMENT_NAME = "figure";

	/**
	 * Constructs a new registry.
	 */
	public FigureExtensionRegistry() {
		super(FigureExtensionRegistry.EXTENSION_POINT_NAME, new OSGiConfigurableExtensionBuilder<FigureExtensionRuntime<?>, FigureExtensionConfig>(FigureExtensionRegistry.CONFIGURATION_ELEMENT_NAME));
	}
}
