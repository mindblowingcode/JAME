package ${packageName};

import net.sf.jame.core.extension.sl.SLExtensionBuilder;
import net.sf.jame.core.extension.sl.SLExtensionRegistry;
import ${runtimePackage}.${runtimeType};

/**
 * @author Andrea Medeghini
 */
public class ${elementName}ExtensionRegistry extends SLExtensionRegistry<${runtimeType}> {
	/**
	 * the extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "${extensionPointId}";
	/**
	 * the configuration element name.
	 */
	public static final String CONFIGURATION_ELEMENT_NAME = "${elementName}";
	/**
	 * the extension descriptor class.
	 */
	public static final Class<? extends ${elementName}ExtensionDescriptor> EXTENSION_DESCRIPTOR_CLASS = ${elementName}ExtensionDescriptor.class;

	/**
	 * Constructs a new registry.
	 */
	public ${elementName}ExtensionRegistry() {
		super(${elementName}ExtensionRegistry.EXTENSION_DESCRIPTOR_CLASS, ${elementName}ExtensionRegistry.EXTENSION_POINT_NAME, new SLExtensionBuilder<${runtimeType}>(${elementName}ExtensionRegistry.CONFIGURATION_ELEMENT_NAME));
	}
}
