package ${nodeActionXMLImporterPackageName};

<#list imports as import>
import ${import};
</#list>
/**
 * @author ${author}
 */
public class ${element.elementName?cap_first}ElementNodeActionXMLImporterRuntime extends ConfigElementNodeActionXMLImporterRuntime<${element.configElementClassName}> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected ${element.configElementClassName}XMLImporter createImporter() {
		return new ${element.configElementClassName}XMLImporter();
	}
}
