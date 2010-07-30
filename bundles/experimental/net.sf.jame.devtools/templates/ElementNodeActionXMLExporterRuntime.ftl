/*
 * $Id:$
 *
 */
package ${nodeActionXMLExporterPackageName};

<#list imports as import>
import ${import};
</#list>
/**
 * @author ${author}
 */
public class ${element.elementName?cap_first}ElementNodeActionXMLExporterRuntime extends ConfigElementNodeActionXMLExporterRuntime<${element.elementName?cap_first}ConfigElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected ${element.elementName?cap_first}ConfigElementXMLExporter createExporter() {
		return new ${element.elementName?cap_first}ConfigElementXMLExporter();
	}
}
 