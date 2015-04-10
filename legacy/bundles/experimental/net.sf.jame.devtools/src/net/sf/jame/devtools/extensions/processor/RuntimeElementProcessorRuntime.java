/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
 *
 * This file is part of JAME.
 *
 * JAME is an application for creating fractals and other graphics artifacts.
 *
 * JAME is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JAME is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAME.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.sf.jame.devtools.extensions.processor;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jame.devtools.DevToolsException;
import net.sf.jame.devtools.ProcessorCardinality;
import net.sf.jame.devtools.ProcessorDescriptor;
import net.sf.jame.devtools.ProcessorParameters;
import net.sf.jame.devtools.ProcessorTemplateLoader;
import net.sf.jame.devtools.processor.extension.ProcessorExtensionRuntime;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class RuntimeElementProcessorRuntime extends ProcessorExtensionRuntime {
	/**
	 * @see net.sf.jame.devtools.processor.extension.ProcessorExtensionRuntime#process(java.io.File, net.sf.jame.devtools.ProcessorDescriptor, java.util.List, java.util.Map)
	 */
	@Override
	public void process(File path, ProcessorParameters parameters, Map<String, String> variables) throws DevToolsException {
		try {
			ProcessorDescriptor element = parameters.getElement();
			List<ProcessorDescriptor> elements = parameters.getElements();
			HashMap<String, Object> map = new HashMap<String, Object>();
			Set<String> imports = new HashSet<String>();
			prepare(imports, element);
			prepare(imports, elements);
			List<String> sortedImports = new LinkedList<String>(imports);
			Collections.sort(sortedImports);
			map.putAll(variables);
			map.put("imports", sortedImports);
			map.put("element", element);
			map.put("subelements", elements);
			if (element.isComplexElement()) {
				File packagePath = new File(path, element.getRuntimeElementPackageName().replace('.', '/'));
				packagePath.mkdirs();
				Configuration config = new Configuration();
				config.setTemplateLoader(new ProcessorTemplateLoader());
				Template template = config.getTemplate("templates/RuntimeElement.ftl");
				template.process(map, new PrintWriter(new File(packagePath, element.getRuntimeElementClassName() + ".java")));
			}
		}
		catch (Exception e) {
			throw new DevToolsException(e);
		}
	}

	/**
	 * @see net.sf.jame.devtools.processor.extension.ProcessorExtensionRuntime#getName()
	 */
	@Override
	public String getName() {
		return "RuntimeElement";
	}

	private void prepare(Set<String> imports, ProcessorDescriptor descriptor) {
		imports.add("net.sf.jame.core.config.ValueChangeEvent");
		imports.add("net.sf.jame.core.config.ValueChangeListener");
		imports.add("net.sf.jame.core.config.RuntimeElement");
	}

	private void prepare(Set<String> imports, List<ProcessorDescriptor> descriptors) {
		for (ProcessorDescriptor descriptor : descriptors) {
			if (descriptor.isExtensionElement()) {
				imports.add("net.sf.jame.core.config.ValueConfigElement");
				imports.add("net.sf.jame.core.common.ExtensionReferenceElement");
				imports.add("net.sf.jame.core.extension.ExtensionException");
				imports.add("net.sf.jame.core.extension.ExtensionNotFoundException");
				imports.add("net.sf.jame.core.extension.ExtensionReference");
				imports.add(descriptor.getExtensionRuntimePackageName() + "." + descriptor.getExtensionRuntimeClassName());
				imports.add(descriptor.getRegistryPackageName() + "." + descriptor.getRegistryClassName());
			}
			else if (descriptor.isConfigurableExtensionElement()) {
				imports.add("net.sf.jame.core.config.ValueConfigElement");
				imports.add("net.sf.jame.core.common.ExtensionReferenceElement");
				imports.add("net.sf.jame.core.extension.ExtensionException");
				imports.add("net.sf.jame.core.extension.ExtensionNotFoundException");
				imports.add("net.sf.jame.core.extension.ConfigurableExtensionReference");
				imports.add(descriptor.getExtensionConfigPackageName() + "." + descriptor.getExtensionConfigClassName());
				imports.add(descriptor.getExtensionRuntimePackageName() + "." + descriptor.getExtensionRuntimeClassName());
				imports.add(descriptor.getRegistryPackageName() + "." + descriptor.getRegistryClassName());
			}
			else if (descriptor.isComplexElement()) {
				if (descriptor.getCardinality() == ProcessorCardinality.ONE) {
					imports.add("net.sf.jame.core.config.SingleConfigElement");
					imports.add("net.sf.jame.core.config.SingleRuntimeElement");
					imports.add(descriptor.getConfigElementPackageName() + "." + descriptor.getConfigElementClassName());
				}
				if (descriptor.getCardinality() == ProcessorCardinality.MANY) {
					imports.add("net.sf.jame.core.config.ListConfigElement");
					imports.add("net.sf.jame.core.config.ListRuntimeElement");
					imports.add(descriptor.getConfigElementPackageName() + "." + descriptor.getConfigElementClassName());
				}
				imports.add(descriptor.getRuntimeElementPackageName() + "." + descriptor.getRuntimeElementClassName());
			}
			else if (descriptor.isSimpleElement()) {
				imports.add("net.sf.jame.core.config.ValueConfigElement");
				imports.add(descriptor.getValuePackageName() + "." + descriptor.getValueClassName());
			}
		}
	}
}
