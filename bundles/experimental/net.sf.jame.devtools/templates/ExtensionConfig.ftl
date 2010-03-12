/*
 * $Id:$
 *
 */
package ${extension.extensionConfigPackageName};

<#list imports as import>
import ${import};
</#list>

<#if extension.configurableExtension>
/**
 * @author ${author}
 */
public abstract class ${extension.extensionConfigClassName} extends ExtensionConfig {
	private static final long serialVersionUID = 1L;
}
</#if>
