/*
 * $Id:$
 *
 */
package ${packageName};

<#if configPackageName?exists && configClassName?exists>
import net.sf.jame.core.common.ExtensionReferenceElementNodeValue;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import ${configPackageName}.${configClassName};

public class ${elementName?cap_first}${subElementName?cap_first}ReferenceNodeValue extends ExtensionReferenceElementNodeValue<ConfigurableExtensionReference<${configClassName}>> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public ${elementName?cap_first}${subElementName?cap_first}ReferenceNodeValue(final ConfigurableExtensionReference<${configClassName}> value) {
		super(value);
	}
}
<#else>
import net.sf.jame.core.common.ExtensionReferenceElementNodeValue;
import net.sf.jame.core.extension.ExtensionReference;

public class ${elementName?cap_first}${subElementName?cap_first}ReferenceNodeValue extends ExtensionReferenceElementNodeValue<ExtensionReference> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public ${elementName?cap_first}${subElementName?cap_first}ReferenceNodeValue(final ExtensionReference value) {
		super(value);
	}
}
</#if>