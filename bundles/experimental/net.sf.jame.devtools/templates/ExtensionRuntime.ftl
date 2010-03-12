/*
 * $Id:$
 *
 */
package ${extension.extensionRuntimePackageName};

<#list imports as import>
import ${import};
</#list>

<#if extension.extension>
/**
 * @author ${author}
 */
public abstract class ${extension.extensionRuntimeClassName} extends ExtensionRuntime {
}
<#elseif extension.configurableExtension>
/**
 * @author ${author}
 */
public abstract class ${extension.extensionRuntimeClassName}<T extends ${extension.extensionConfigClassName}> extends ConfigurableExtensionRuntime<T> {
}
</#if>
