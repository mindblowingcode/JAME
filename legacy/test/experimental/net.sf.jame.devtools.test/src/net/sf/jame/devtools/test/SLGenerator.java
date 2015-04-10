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
package net.sf.jame.devtools.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.junit.Test;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class SLGenerator {
	@Test
	public void testRegistry() {
		try {
			final IExtensionPointTreeModel treeModel = new IExtensionPointTreeModel(new DefaultMutableTreeNode("TEST"));
			export(treeModel);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Map<String, ExtensionPoint> extensionPoints = new HashMap<String, ExtensionPoint>();
	
	public static void export(IExtensionPointTreeModel treeModel) {
		dump((DefaultMutableTreeNode)treeModel.getRoot());
		PrintStream out = null;
		try {
			Configuration config = new Configuration();
			config.setTemplateLoader(new ProcessorTemplateLoader());
			File folder = new File("build");
			folder.mkdirs();
			out = new PrintStream(new File(folder, "extensions.txt"));
			out.println("-----------------------------------------------------------");
			for (Map.Entry<String, ExtensionPoint> entry : extensionPoints.entrySet()) {
				String extensionPointId = entry.getKey();
				if (extensionPointId.startsWith("net.sf.jame")) {
					StringBuilder builder = new StringBuilder();
					builder.append("Extension point ");
					builder.append(extensionPointId);
					builder.append("\n");
					builder.append("project ");
					String projectName = extensionPointId.substring(0, extensionPointId.lastIndexOf("."));
					builder.append(projectName);
					builder.append("\n");
					ExtensionPoint extensionPoint = entry.getValue();
					for (ExtensionDescriptor descriptor : extensionPoint.getDescriptors()) {
						if (descriptor.getExtensionRuntimeClass() == null) {
							continue;
						}
						builder.append("package ");
						String extensionPackage = projectName + "." + descriptor.getElementName() + ".extension";
						String extensionType = descriptor.getExtensionRuntimeClass().replace("ExtensionRuntime", "ExtensionDescriptor").substring(descriptor.getExtensionRuntimeClass().lastIndexOf(".") + 1);
						String registryType = descriptor.getExtensionRuntimeClass().replace("ExtensionRuntime", "ExtensionRegistry").substring(descriptor.getExtensionRuntimeClass().lastIndexOf(".") + 1);
						String elementName = descriptor.getElementName().toUpperCase().substring(0, 1) + descriptor.getElementName().substring(1);
						builder.append("extensionPackage ");
						builder.append(extensionPackage);
						builder.append("extensionType ");
						builder.append(extensionType);
						builder.append("\n");
						builder.append("elementName ");
						builder.append(elementName);
						builder.append("\n");
						Map<String, String> map = new HashMap<String, String>();
						map.put("extensionPointId", extensionPointId);
						map.put("packageName", extensionPackage);
						map.put("elementName", elementName);
						map.put("runtimePackage", descriptor.getExtensionRuntimeClass().substring(0, descriptor.getExtensionRuntimeClass().lastIndexOf(".")));
						map.put("runtimeType", descriptor.getExtensionRuntimeClass().substring(descriptor.getExtensionRuntimeClass().lastIndexOf(".") + 1));
						File projectDir = new File(folder, projectName);
						projectDir.mkdirs();
						File classDir = new File(projectDir, "src/" + extensionPackage.replace(".", "/"));
						classDir.mkdirs();
						if (descriptor.getExtensionConfigClass() != null) {
							map.put("configPackage", descriptor.getExtensionConfigClass().substring(0, descriptor.getExtensionConfigClass().lastIndexOf(".")));
							map.put("configType", descriptor.getExtensionConfigClass().substring(descriptor.getExtensionConfigClass().lastIndexOf(".") + 1));
							Template template = config.getTemplate("templates/ConfigurableExtensionClass.ftl");
							template.process(map, new PrintWriter(new File(classDir, extensionType + ".java")));
							Template template2 = config.getTemplate("templates/ConfigurableExtensionRegistryImplClass.ftl");
							template2.process(map, new PrintWriter(new File(classDir, registryType + ".java")));
						} else {
							Template template = config.getTemplate("templates/ExtensionClass.ftl");
							template.process(map, new PrintWriter(new File(classDir, extensionType + ".java")));
							Template template2 = config.getTemplate("templates/ExtensionRegistryImplClass.ftl");
							template2.process(map, new PrintWriter(new File(classDir, registryType + ".java")));
						}
					}
					for (Entry<String, ExtensionPointProvider> entryProvider : extensionPoint.getExtensionProviders().entrySet()) {
						ExtensionPointProvider provider = entryProvider.getValue();
						for (Entry<String, Extension> entryExtension : provider.getExtensions().entrySet()) {
							Extension extension = entryExtension.getValue();
							ExtensionDescriptor descriptor = extension.getDescriptor();
							if (descriptor.getExtensionRuntimeClass() == null) {
								continue;
							}
							String extensionPackage = projectName + "." + descriptor.getElementName() + ".extension";
							String extensionType = descriptor.getExtensionRuntimeClass().replace("ExtensionRuntime", "ExtensionDescriptor").substring(descriptor.getExtensionRuntimeClass().lastIndexOf(".") + 1);
							String elementName = descriptor.getElementName().toUpperCase().substring(0, 1) + descriptor.getElementName().substring(1);
							String projectNameProvider = entryProvider.getKey().substring(0, entryProvider.getKey().lastIndexOf("."));
							String packageNameExtension = extension.getRuntimeClass().substring(0, extension.getRuntimeClass().lastIndexOf("."));
							String classNameExtension = extension.getRuntimeClass().replace("Runtime", "Descriptor").substring(extension.getRuntimeClass().lastIndexOf(".") + 1);
							Map<String, String> mapProvider = new HashMap<String, String>();
							mapProvider.put("extensionPointId", extensionPointId);
							mapProvider.put("extensionPackage", extensionPackage);
							mapProvider.put("extensionType", extensionType);
							mapProvider.put("elementName", elementName);
							mapProvider.put("className", classNameExtension);
							mapProvider.put("extensionId", extension.getId());
							mapProvider.put("extensionName", extension.getName()) ;
							mapProvider.put("runtimePackage", extension.getRuntimeClass().substring(0, extension.getRuntimeClass().lastIndexOf(".")));
							mapProvider.put("runtimeType", extension.getRuntimeClass().substring(extension.getRuntimeClass().lastIndexOf(".") + 1));
							mapProvider.put("packageName", packageNameExtension);
							File projectDir = new File(folder, projectNameProvider);
							projectDir.mkdirs();
							File classDir = new File(projectDir, "src/" + packageNameExtension.replace(".", "/"));
							classDir.mkdirs();
							File metaDir = new File(projectDir, "META-INF/services");
							metaDir.mkdirs();
							File serviceFile = new File(metaDir, extensionPackage + "." + extensionType);
							FileWriter writer = new FileWriter(serviceFile, true);
							writer.write(packageNameExtension + "." + classNameExtension + "\n");
							writer.close();
							System.out.println(packageNameExtension + "." + classNameExtension);
							if (extension.getConfigClass() != null) {
								mapProvider.put("configPackage", extension.getConfigClass().substring(0, extension.getConfigClass().lastIndexOf(".")));
								mapProvider.put("configType", extension.getConfigClass().substring(extension.getConfigClass().lastIndexOf(".") + 1));
								Template template = config.getTemplate("templates/ConfigurableExtensionImplClass.ftl");
								template.process(mapProvider, new PrintWriter(new File(classDir, classNameExtension + ".java")));
							} else {
								Template template = config.getTemplate("templates/ExtensionImplClass.ftl");
								template.process(mapProvider, new PrintWriter(new File(classDir, classNameExtension + ".java")));
							}
						}
					}
					builder.append("\n");
					builder.append(extensionPoint);
					out.println(builder.toString());
				}
			}
			out.println("-----------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	private static void dump(DefaultMutableTreeNode parentNode) {
		int count = parentNode.getChildCount();
		for (int i = 0; i < count; i++) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) parentNode.getChildAt(i);
			dumpAsString(node);
		}
		for (int i = 0; i < count; i++) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) parentNode.getChildAt(i);
			dump(node);
		}
	}

	private static void dumpAsString(DefaultMutableTreeNode node) {
		if (node.getUserObject() != null) {
			if (node.getUserObject() instanceof IExtensionPoint) {
				System.out.println(((IExtensionPoint) node.getUserObject()).getLabel() + " (" + ((IExtensionPoint) node.getUserObject()).getUniqueIdentifier() + ")");
			}
			else if (node.getUserObject() instanceof IExtension) {
				System.out.println(((IExtension) node.getUserObject()).getLabel() + " (" + ((IExtension) node.getUserObject()).getUniqueIdentifier() + ")");
			}
			else if (node.getUserObject() instanceof IConfigurationElement) {
				System.out.println(((IConfigurationElement) node.getUserObject()).getName());
				String identifier = ((IExtensionPoint) ((DefaultMutableTreeNode)node.getParent().getParent()).getUserObject()).getUniqueIdentifier();
				ExtensionPoint extensionPoint = extensionPoints.get(identifier);
				if (extensionPoint == null) {
					extensionPoint = new ExtensionPoint();
					extensionPoints.put(identifier, extensionPoint);
				}
				if (extensionPoint != null) {
					String id = ((IExtension) ((DefaultMutableTreeNode)node.getParent()).getUserObject()).getUniqueIdentifier();
					if (id == null) {
						return;
					}
					ExtensionPointProvider extensionProvider = extensionPoint.getExtensionProvider(id);
					if (extensionProvider == null) {
						extensionProvider = new ExtensionPointProvider();
						extensionPoint.putExtensionProvider(id, extensionProvider);
					}
					if (extensionProvider != null) {
						ExtensionDescriptor descriptor = new ExtensionDescriptor();
						descriptor.setElementName(((IConfigurationElement) node.getUserObject()).getName());
						String extensionId = ((IConfigurationElement) node.getUserObject()).getAttribute("id");
						if (extensionId != null) {
							Extension extension = extensionProvider.getExtension(extensionId + descriptor.getElementName());
							if (extension == null) {
								extension = new Extension();
								extensionProvider.putExtension(extensionId + descriptor.getElementName(), extension);
							}
							extension.setId(((IConfigurationElement) node.getUserObject()).getAttribute("id"));
							extension.setName(((IConfigurationElement) node.getUserObject()).getAttribute("name"));
							extension.setRuntimeClass(((IConfigurationElement) node.getUserObject()).getAttribute("runtimeClass"));
							extension.setConfigClass(((IConfigurationElement) node.getUserObject()).getAttribute("configClass"));
							extension.setElementName(((IConfigurationElement) node.getUserObject()).getName());
							extension.setDescriptor(descriptor);
							try {
								if (extension.getRuntimeClass() != null) {
									Class<?> klass = Class.forName(extension.getRuntimeClass());
									Class<?> superKlass = klass.getSuperclass();
									String name = superKlass.getCanonicalName();
									while (!name.equals("java.lang.Object")) {
										if (name.contains("ExtensionRuntime") && !name.contains("Abstract")) {
											System.out.println("Found class " + name);
											descriptor.setExtensionRuntimeClass(name);
											if (extension.getConfigClass() != null) {
												name = name.replace("Runtime", "Config");
												descriptor.setExtensionConfigClass(name);
											}
											break;
										}
										superKlass = superKlass.getSuperclass();
										name = superKlass.getCanonicalName();
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							System.out.println("ExtensionId " + extension.getId());
							System.out.println("ExtensionRuntime " + extension.getRuntimeClass());
							System.out.println("ExtensionClass " + extension.getDescriptor().getExtensionRuntimeClass());
						}
						extensionPoint.addDescriptor(descriptor);
						extensionProvider.addDescriptor(descriptor);
					}
				}
			}
			else if (node.getUserObject() != null) {
				System.out.println(node.getUserObject().toString());
			}
		}
	}
	
	private static class ExtensionPoint {
		private Set<ExtensionDescriptor> descriptors = new LinkedHashSet<ExtensionDescriptor>();
		private Map<String, ExtensionPointProvider> extensionProviders = new HashMap<String, ExtensionPointProvider>();
		
		public void putExtensionProvider(String id, ExtensionPointProvider extension) {
			extensionProviders.put(id, extension); 
		}
		
		public Map<String, ExtensionPointProvider> getExtensionProviders() {
			return extensionProviders;
		}

		public ExtensionPointProvider getExtensionProvider(String id) {
			return extensionProviders.get(id); 
		}

		public boolean addDescriptor(ExtensionDescriptor e) {
			return descriptors.add(e);
		}
		
		public boolean removeDescriptor(ExtensionDescriptor o) {
			return descriptors.remove(o);
		}
		
		public Set<ExtensionDescriptor> getDescriptors() {
			return descriptors;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			for (ExtensionDescriptor descriptor : getDescriptors()) {
				builder.append("ElementName ");
				builder.append(descriptor.getElementName());
				builder.append("\n");
				builder.append("ExtensionRuntime ");
				builder.append(descriptor.getExtensionRuntimeClass());
				builder.append("\n");
				builder.append("ExtensionConfig ");
				builder.append(descriptor.getExtensionConfigClass());
				builder.append("\n");
			}
			for (Map.Entry<String, ExtensionPointProvider> entry : extensionProviders.entrySet()) {
				builder.append("Extension point provider ");
				builder.append(entry.getKey());
				builder.append(" (project ");
				builder.append(entry.getKey().substring(0, entry.getKey().lastIndexOf(".")));
				builder.append(")\n");
				builder.append(entry.getValue());
				builder.append("\n");
			}
			return builder.toString();
		}
	}
	
	private static class ExtensionDescriptor {
		private String elementName;
		private String extensionRuntimeClass;
		private String extensionConfigClass;

		public String getElementName() {
			return elementName;
		}

		public void setElementName(String elementName) {
			this.elementName = elementName;
		}

		public String getExtensionRuntimeClass() {
			return extensionRuntimeClass;
		}

		public void setExtensionRuntimeClass(String extensionRuntimeClass) {
			this.extensionRuntimeClass = extensionRuntimeClass;
		}

		public String getExtensionConfigClass() {
			return extensionConfigClass;
		}

		public void setExtensionConfigClass(String extensionConfigClass) {
			this.extensionConfigClass = extensionConfigClass;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((elementName == null) ? 0 : elementName.hashCode());
			result = prime
					* result
					+ ((extensionConfigClass == null) ? 0
							: extensionConfigClass.hashCode());
			result = prime
					* result
					+ ((extensionRuntimeClass == null) ? 0
							: extensionRuntimeClass.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ExtensionDescriptor other = (ExtensionDescriptor) obj;
			if (elementName == null) {
				if (other.elementName != null)
					return false;
			} else if (!elementName.equals(other.elementName))
				return false;
			if (extensionConfigClass == null) {
				if (other.extensionConfigClass != null)
					return false;
			} else if (!extensionConfigClass.equals(other.extensionConfigClass))
				return false;
			if (extensionRuntimeClass == null) {
				if (other.extensionRuntimeClass != null)
					return false;
			} else if (!extensionRuntimeClass
					.equals(other.extensionRuntimeClass))
				return false;
			return true;
		}
	}
	
	private static class ExtensionPointProvider {
		private Set<ExtensionDescriptor> descriptors = new LinkedHashSet<ExtensionDescriptor>();
		private Map<String, Extension> extensions = new HashMap<String, Extension>();
		
		public void putExtension(String id, Extension extension) {
			extensions.put(id, extension); 
		}
		
		public Map<String, Extension> getExtensions() {
			return extensions;
		}

		public Extension getExtension(String id) {
			return extensions.get(id); 
		}

		public boolean addDescriptor(ExtensionDescriptor e) {
			return descriptors.add(e);
		}
		
		public boolean removeDescriptor(ExtensionDescriptor o) {
			return descriptors.remove(o);
		}
		
		public Set<ExtensionDescriptor> getDescriptors() {
			return descriptors;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			for (ExtensionDescriptor descriptor : getDescriptors()) {
				builder.append(descriptor.getElementName());
				builder.append(", ");
				builder.append(descriptor.getExtensionRuntimeClass());
				builder.append(", ");
				builder.append(descriptor.getExtensionConfigClass());
				builder.append("\n");
			}
			for (Map.Entry<String, Extension> entry : extensions.entrySet()) {
				builder.append("package ");
				builder.append(entry.getValue().getRuntimeClass().substring(0, entry.getValue().getRuntimeClass().lastIndexOf(".")));
				builder.append("\n");
				builder.append(entry.getValue());
				builder.append("\n");
			}
			return builder.toString();
		}
	}

	private static class Extension {
		private String id;
		private String name;
		private String runtimeClass;
		private String configClass;
		private String elementName;
		private ExtensionDescriptor descriptor;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getRuntimeClass() {
			return runtimeClass;
		}

		public void setRuntimeClass(String runtimeClass) {
			this.runtimeClass = runtimeClass;
		}

		public String getConfigClass() {
			return configClass;
		}

		public void setConfigClass(String configClass) {
			this.configClass = configClass;
		}

		public String getElementName() {
			return elementName;
		}

		public void setElementName(String elementName) {
			this.elementName = elementName;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Extension ");
			builder.append(getId());
			builder.append(", ");
			builder.append(getName());
			builder.append(", ");
			builder.append(getRuntimeClass());
			builder.append(", ");
			builder.append(getConfigClass());
			builder.append(", ");
			builder.append(getElementName());
			return builder.toString();
		}

		public ExtensionDescriptor getDescriptor() {
			return descriptor;
		}

		public void setDescriptor(ExtensionDescriptor descriptor) {
			this.descriptor = descriptor;
		}
	}

	public static class ProcessorTemplateLoader implements TemplateLoader {
		public void closeTemplateSource(Object source) throws IOException {
			((InputStream) source).close();
		}

		public Object findTemplateSource(String name) throws IOException {
			return getClass().getResourceAsStream("/" + name.substring(0, name.indexOf("_")) + ".ftl");
		}

		public long getLastModified(Object source) {
			return 0;
		}

		public Reader getReader(Object source, String encoding) throws IOException {
			return new InputStreamReader((InputStream) source, encoding);
		}
	}
}
