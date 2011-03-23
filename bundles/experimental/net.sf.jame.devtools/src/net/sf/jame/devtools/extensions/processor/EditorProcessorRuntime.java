/*
 * JAME 6.1 
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2010 Andrea Medeghini
 * http://andreamedeghini.users.sourceforge.net
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
import net.sf.jame.devtools.ProcessorDescriptor;
import net.sf.jame.devtools.ProcessorParameters;
import net.sf.jame.devtools.extension.ProcessorExtensionRuntime;
import net.sf.jame.devtools.extensions.ProcessorTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class EditorProcessorRuntime extends ProcessorExtensionRuntime {
	/**
	 * @see net.sf.jame.devtools.extension.ProcessorExtensionRuntime#process(java.io.File, net.sf.jame.devtools.ProcessorDescriptor, java.util.List, java.util.Map)
	 */
	@Override
	public void process(File path, ProcessorParameters parameters, Map<String, String> variables) throws DevToolsException {
		try {
			ProcessorDescriptor element = parameters.getElement();
			if (element.isExtension() || element.isConfigurableExtension()) {
				if (variables.containsKey("generateReferenceEditor")) {
					File packagePath = new File(path, variables.get("editorPackageName").replace('.', '/'));
					packagePath.mkdirs();
					Configuration config = new Configuration();
					config.setTemplateLoader(new ProcessorTemplateLoader());
					HashMap<String, Object> map = new HashMap<String, Object>();
					Set<String> imports = new HashSet<String>();
					prepare(imports, element);
					map.putAll(variables);
					prepareReferenceEditor(imports, element, variables);
					List<String> sortedImports = new LinkedList<String>(imports);
					Collections.sort(sortedImports);
					map.put("imports", sortedImports);
					map.put("extension", element);
					Template template = config.getTemplate("templates/ReferenceEditorRuntime.ftl");
					template.process(map, new PrintWriter(new File(packagePath, capitalize(element.getElementName()) + "ReferenceEditorRuntime.java")));
				}
				if (variables.containsKey("generateReferenceElementEditor")) {
					File packagePath = new File(path, variables.get("editorPackageName").replace('.', '/'));
					packagePath.mkdirs();
					Configuration config = new Configuration();
					config.setTemplateLoader(new ProcessorTemplateLoader());
					HashMap<String, Object> map = new HashMap<String, Object>();
					Set<String> imports = new HashSet<String>();
					prepare(imports, element);
					map.putAll(variables);
					prepareReferenceElementEditor(imports, element, variables);
					List<String> sortedImports = new LinkedList<String>(imports);
					Collections.sort(sortedImports);
					map.put("imports", sortedImports);
					map.put("extension", element);
					Template template = config.getTemplate("templates/ReferenceElementEditorRuntime.ftl");
					template.process(map, new PrintWriter(new File(packagePath, capitalize(element.getElementName()) + "ReferenceElementEditorRuntime.java")));
				} 
				if (variables.containsKey("generateReferenceElementListEditor")) {
					File packagePath = new File(path, variables.get("editorPackageName").replace('.', '/'));
					packagePath.mkdirs();
					Configuration config = new Configuration();
					config.setTemplateLoader(new ProcessorTemplateLoader());
					HashMap<String, Object> map = new HashMap<String, Object>();
					Set<String> imports = new HashSet<String>();
					prepare(imports, element);
					map.putAll(variables);
					prepareReferenceElementListEditor(imports, element, variables);
					List<String> sortedImports = new LinkedList<String>(imports);
					Collections.sort(sortedImports);
					map.put("imports", sortedImports);
					map.put("extension", element);
					Template template = config.getTemplate("templates/ReferenceElementListEditorRuntime.ftl");
					template.process(map, new PrintWriter(new File(packagePath, capitalize(element.getElementName()) + "ReferenceElementListEditorRuntime.java")));
				}
			}
			else if (element.isComplexElement() || element.isSimpleElement()) {
				if (variables.containsKey("generateElementEditor")) {
					File packagePath = new File(path, variables.get("editorPackageName").replace('.', '/'));
					packagePath.mkdirs();
					Configuration config = new Configuration();
					config.setTemplateLoader(new ProcessorTemplateLoader());
					HashMap<String, Object> map = new HashMap<String, Object>();
					Set<String> imports = new HashSet<String>();
					prepare(imports, element);
					map.putAll(variables);
					prepareElementEditor(imports, element);
					List<String> sortedImports = new LinkedList<String>(imports);
					Collections.sort(sortedImports);
					map.put("imports", sortedImports);
					map.put("element", element);
					Template template = config.getTemplate("templates/ElementEditorRuntime.ftl");
					template.process(map, new PrintWriter(new File(packagePath, capitalize(element.getElementName()) + "EditorRuntime.java")));
				}
			}
		}
		catch (Exception e) {
			throw new DevToolsException(e);
		}
	}

	/**
	 * @see net.sf.jame.devtools.extension.ProcessorExtensionRuntime#getName()
	 */
	@Override
	public String getName() {
		return "EditorRuntime";
	}

	private void prepare(Set<String> imports, ProcessorDescriptor descriptor) {
	}

	private void prepareElementEditor(Set<String> imports, ProcessorDescriptor descriptor) {
		imports.add("javax.swing.JComponent");
		imports.add("javax.swing.JPanel");
		imports.add("java.awt.FlowLayout");
		imports.add("net.sf.jame.core.tree.NodeEditor");
		imports.add("net.sf.jame.core.swing.NodeEditorComponent");
		imports.add("net.sf.jame.core.swing.editor.extension.EditorExtensionRuntime");
	}

	private void prepareReferenceEditor(Set<String> imports, ProcessorDescriptor descriptor, Map<String, String> variables) {
		if (descriptor.isExtension()) {
			imports.add("net.sf.jame.core.extension.ExtensionReference");
			imports.add("net.sf.jame.core.swing.editor.ReferenceEditorRuntime");
			imports.add("net.sf.jame.core.swing.extension.ExtensionComboBoxModel");
		} else {
			imports.add("net.sf.jame.core.extension.ConfigurableExtensionReference");
			imports.add("net.sf.jame.core.swing.editor.ConfigurableReferenceEditorRuntime");
			imports.add("net.sf.jame.core.swing.extension.ConfigurableExtensionComboBoxModel");
		}
		imports.add("net.sf.jame.core.tree.NodeValue");
		imports.add(descriptor.getRegistryPackageName() + "." + descriptor.getRegistryClassName());
		imports.add(variables.get("configElementPackageName") + "." + capitalize(descriptor.getElementName()) + "ExtensionReferenceNodeValue");
	}

	private void prepareReferenceElementEditor(Set<String> imports, ProcessorDescriptor descriptor, Map<String, String> variables) {
		if (descriptor.isExtension()) {
			imports.add("net.sf.jame.core.extension.ExtensionReference");
			imports.add("net.sf.jame.core.swing.editor.ReferenceElementEditorRuntime");
			imports.add("net.sf.jame.core.swing.extension.ExtensionComboBoxModel");
		} else {
			imports.add("net.sf.jame.core.extension.ConfigurableExtensionReference");
			imports.add("net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime");
			imports.add("net.sf.jame.core.swing.extension.ConfigurableExtensionComboBoxModel");
		}
		imports.add("net.sf.jame.core.tree.NodeValue");
		imports.add(descriptor.getRegistryPackageName() + "." + descriptor.getRegistryClassName());
		imports.add(variables.get("resourcesPackageName") + "." + variables.get("resourcesClassName"));
		imports.add(variables.get("configElementPackageName") + "." + variables.get("configElementClassName"));
		imports.add(variables.get("configElementPackageName") + "." + variables.get("configElementClassName") + "NodeValue");
	}

	private void prepareReferenceElementListEditor(Set<String> imports, ProcessorDescriptor descriptor, Map<String, String> variables) {
		if (descriptor.isExtension()) {
			imports.add("net.sf.jame.core.extension.ExtensionReference");
			imports.add("net.sf.jame.core.swing.editor.ReferenceElementListEditorRuntime");
			imports.add("net.sf.jame.core.swing.extension.ExtensionComboBoxModel");
		} else {
			imports.add("net.sf.jame.core.extension.ConfigurableExtensionReference");
			imports.add("net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime");
			imports.add("net.sf.jame.core.swing.extension.ConfigurableExtensionComboBoxModel");
		}
		imports.add("net.sf.jame.core.tree.NodeValue");
		imports.add(descriptor.getRegistryPackageName() + "." + descriptor.getRegistryClassName());
		imports.add(variables.get("resourcesPackageName") + "." + variables.get("resourcesClassName"));
		imports.add(variables.get("configElementPackageName") + "." + variables.get("configElementClassName"));
		imports.add(variables.get("configElementPackageName") + "." + variables.get("configElementClassName") + "NodeValue");
	}

	private static String capitalize(String word) {
        word = word.substring(0, 1).toUpperCase() + word.substring(1);
        return word;
    }
}
