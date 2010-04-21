/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.extension;

import net.sf.jame.core.extension.osgi.OSGiExtensionBuilder;
import net.sf.jame.core.extension.osgi.OSGiExtensionRegistry;

/**
 * @author Andrea Medeghini
 */
public class PrimitiveExtensionRegistry extends OSGiExtensionRegistry<PrimitiveExtensionRuntime> {
	/**
	 * the extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "net.sf.jame.contextfree.extensions";
	/**
	 * the configuration element name.
	 */
	public static final String CONFIGURATION_ELEMENT_NAME = "primitive";

	/**
	 * Constructs a new registry.
	 */
	public PrimitiveExtensionRegistry() {
		super(PrimitiveExtensionRegistry.EXTENSION_POINT_NAME, new OSGiExtensionBuilder<PrimitiveExtensionRuntime>(PrimitiveExtensionRegistry.CONFIGURATION_ELEMENT_NAME));
	}
}
