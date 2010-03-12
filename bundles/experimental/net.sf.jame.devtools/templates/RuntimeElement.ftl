/*
 * $Id:$
 *
 */
package ${element.runtimeElementPackageName};

<#list imports as import>
import ${import};
</#list>

/**
 * @author ${author}
 */
 public class ${element.runtimeElementClassName} extends RuntimeElement {
	private ${element.configElementClassName} ${element.elementName?uncap_first}Element;
	<#list subelements as subelement>
	<#if subelement.extensionElement>
	private ${subelement.extensionRuntimeClassName} ${subelement.elementName?uncap_first}Runtime;
	private ${subelement.elementName?cap_first}Listener ${subelement.elementName?uncap_first}Listener;
	<#elseif subelement.configurableExtensionElement>
	private ${subelement.extensionRuntimeClassName}<?> ${subelement.elementName?uncap_first}Runtime;
	private ${subelement.elementName?cap_first}Listener ${subelement.elementName?uncap_first}Listener;
	<#elseif subelement.complexElement>
	private ${subelement.elementName?cap_first}${subelement.fieldNameSuffix}Listener ${subelement.elementName?uncap_first}${subelement.fieldNameSuffix}Listener;
	<#elseif subelement.simpleElement>
	private ${subelement.valueClassName} ${subelement.elementName?uncap_first};
	private ${subelement.elementName?cap_first}Listener ${subelement.elementName?uncap_first}Listener;
	</#if>
	</#list>

	/**
	 * Constructs a new ${element.runtimeElementClassName}.
	 * 
	 * @param registry
	 * @param ${element.runtimeElementClassName}Element
	 */
	public ${element.runtimeElementClassName}(final ${element.configElementClassName} ${element.elementName?uncap_first}Element) {
		if (${element.elementName?uncap_first}Element == null) {
			throw new IllegalArgumentException("${element.elementName?uncap_first}Element is null");
		}
		this.${element.elementName?uncap_first}Element = ${element.elementName?uncap_first}Element;
		<#list subelements as subelement>
		<#if subelement.extensionElement || subelement.configurableExtensionElement>
		createRuntime(${element.elementName?uncap_first}Element.get${subelement.elementName?cap_first}Reference());
		${subelement.elementName?uncap_first}Listener = new ${subelement.elementName?cap_first}Listener();
		${element.elementName?uncap_first}Element.get${subelement.elementName?cap_first}Element().addChangeListener(${subelement.elementName?uncap_first}Listener);
		<#elseif subelement.complexElement>
		${subelement.elementName?uncap_first}${subelement.fieldNameSuffix}Listener = new ${subelement.elementName?cap_first}${subelement.fieldNameSuffix}Listener();
		${element.elementName?uncap_first}Element.get${subelement.elementName?cap_first}${subelement.fieldNameSuffix}().addChangeListener(${subelement.elementName?uncap_first}${subelement.fieldNameSuffix}Listener);
		<#elseif subelement.simpleElement>
		${subelement.setMethodPrefix}${subelement.elementName?cap_first}(${element.elementName?uncap_first}Element.${subelement.getMethodPrefix}${subelement.elementName?cap_first}());
		${subelement.elementName?uncap_first}Listener = new ${subelement.elementName?cap_first}Listener();
		${element.elementName?uncap_first}Element.get${subelement.elementName?cap_first}Element().addChangeListener(${subelement.elementName?uncap_first}Listener);
		</#if>
		</#list>
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		<#list subelements as subelement>
		<#if subelement.extensionElement || subelement.configurableExtensionElement>
		if ((${element.elementName?uncap_first}Element != null) && (${subelement.elementName?uncap_first}Listener != null)) {
			${element.elementName?uncap_first}Element.get${subelement.elementName?cap_first}Element().removeChangeListener(${subelement.elementName?uncap_first}Listener);
		}
		${subelement.elementName?uncap_first}Listener = null;
		<#elseif subelement.complexElement>
		if ((${element.elementName?uncap_first}Element != null) && (${subelement.elementName?uncap_first}${subelement.fieldNameSuffix}Listener != null)) {
			${element.elementName?uncap_first}Element.get${subelement.elementName?cap_first}${subelement.fieldNameSuffix}().removeChangeListener(${subelement.elementName?uncap_first}${subelement.fieldNameSuffix}Listener);
		}
		${subelement.elementName?uncap_first}${subelement.fieldNameSuffix}Listener = null;
		<#elseif subelement.simpleElement>
		if ((${element.elementName?uncap_first}Element != null) && (${subelement.elementName?uncap_first}Listener != null)) {
			${element.elementName?uncap_first}Element.get${subelement.elementName?cap_first}Element().removeChangeListener(${subelement.elementName?uncap_first}Listener);
		}
		${subelement.elementName?uncap_first}Listener = null;
		</#if>
		</#list>
		<#list subelements as subelement>
		<#if subelement.extensionElement || subelement.configurableExtensionElement>
		if (${subelement.elementName?uncap_first}Runtime != null) {
			${subelement.elementName?uncap_first}Runtime.dispose();
			${subelement.elementName?uncap_first}Runtime = null;
		}
		</#if>
		</#list>
		${element.elementName?uncap_first}Element = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean ${element.elementName?uncap_first}Changed = false;
		<#list subelements as subelement>
		<#if subelement.extensionElement || subelement.configurableExtensionElement>
		${element.elementName?uncap_first}Changed |= (${subelement.elementName?uncap_first}Runtime != null) && ${subelement.elementName?uncap_first}Runtime.isChanged();
		</#if>
		</#list>
		return super.isChanged() || ${element.elementName?uncap_first}Changed;
	}

	<#list subelements as subelement>
	<#if subelement.extensionElement || subelement.configurableExtensionElement>
	<#if subelement.extensionElement>
	@SuppressWarnings("unchecked")
	private void createRuntime(final ExtensionReference reference) {
		try {
			if (reference != null) {
				final ${subelement.extensionRuntimeClassName} ${subelement.elementName?uncap_first}Runtime = ${subelement.registryClassName}.getInstance().get${subelement.elementType?cap_first}Extension(reference.getExtensionId()).createExtensionRuntime();
				set${subelement.elementName?cap_first}Runtime(${subelement.elementName?uncap_first}Runtime);
			}
			else {
				set${subelement.elementName?cap_first}Runtime(null);
			}
		}
		catch (final ExtensionNotFoundException e) {
			e.printStackTrace();
		}
		catch (final ExtensionException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * @return the ${element.runtimeElementClassName}Runtime
	 */
	public ${subelement.extensionRuntimeClassName} get${subelement.elementName?cap_first}Runtime() {
		return ${subelement.elementName?uncap_first}Runtime;
	}

	private void set${subelement.elementName?cap_first}Runtime(final ${subelement.extensionRuntimeClassName} ${subelement.elementName?uncap_first}Runtime) {
		if (this.${subelement.elementName?uncap_first}Runtime != null) {
			this.${subelement.elementName?uncap_first}Runtime.dispose();
		}
		this.${subelement.elementName?uncap_first}Runtime = ${subelement.elementName?uncap_first}Runtime;
	}
	
	private class ${subelement.elementName?cap_first}Listener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		@SuppressWarnings("unchecked")
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED: {
					createRuntime((ExtensionReference) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	<#elseif subelement.configurableExtensionElement>
	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<${subelement.extensionConfigClassName}> reference) {
		try {
			if (reference != null) {
				final ${subelement.extensionRuntimeClassName} ${subelement.elementName?uncap_first}Runtime = ${subelement.registryClassName}.getInstance().get${subelement.elementType?cap_first}Extension(reference.getExtensionId()).createExtensionRuntime();
				final ${subelement.extensionConfigClassName} ${subelement.elementName?uncap_first}Config = reference.getExtensionConfig();
				${subelement.elementName?uncap_first}Runtime.setConfig(${subelement.elementName?uncap_first}Config);
				set${subelement.elementName?cap_first}Runtime(${subelement.elementName?uncap_first}Runtime);
			}
			else {
				set${subelement.elementName?cap_first}Runtime(null);
			}
		}
		catch (final ExtensionNotFoundException e) {
			e.printStackTrace();
		}
		catch (final ExtensionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the ${element.runtimeElementClassName}Runtime
	 */
	public ${subelement.extensionRuntimeClassName}<?> get${subelement.elementName?cap_first}Runtime() {
		return ${subelement.elementName?uncap_first}Runtime;
	}

	private void set${subelement.elementName?cap_first}Runtime(final ${subelement.extensionRuntimeClassName}<?> ${subelement.elementName?uncap_first}Runtime) {
		if (this.${subelement.elementName?uncap_first}Runtime != null) {
			this.${subelement.elementName?uncap_first}Runtime.dispose();
		}
		this.${subelement.elementName?uncap_first}Runtime = ${subelement.elementName?uncap_first}Runtime;
	}
	
	private class ${subelement.elementName?cap_first}Listener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		@SuppressWarnings("unchecked")
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED: {
					createRuntime((ConfigurableExtensionReference<${subelement.extensionConfigClassName}>) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	</#if>
	<#elseif subelement.complexElement>
	private class ${subelement.elementName?cap_first}${subelement.fieldNameSuffix}Listener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}
	<#elseif subelement.simpleElement>
	/**
	 * @return the ${subelement.elementName?uncap_first}.
	 */
	public ${subelement.valueClassName} ${subelement.getMethodPrefix}${subelement.elementName?cap_first}() {
		return ${subelement.elementName?uncap_first};
	}

	private void ${subelement.setMethodPrefix}${subelement.elementName?cap_first}(final ${subelement.valueClassName} ${subelement.elementName?uncap_first}) {
		this.${subelement.elementName?uncap_first} = ${subelement.elementName?uncap_first};
	}
	
	private class ${subelement.elementName?cap_first}Listener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	</#if>
	</#list>
}
