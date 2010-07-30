/*
 * $Id:$
 *
 */
package ${nodeActionXMLImporterPackageName};

<#list imports as import>
import ${import};
</#list>
/**
 * @author ${author}
 */
public class ${element.elementName?cap_first}ElementNodeActionXMLImporterRuntime extends ConfigElementNodeActionXMLImporterRuntime<${element.elementName?cap_first}ConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected ${element.elementName?cap_first}ConfigElementXMLImporter createImporter() {
		return new ${element.elementName?cap_first}ConfigElementXMLImporter();
	}
}
