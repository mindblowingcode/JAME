package net.sf.jame.contextfree.devtools;
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


import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.devtools.DevToolsRegistry;
import net.sf.jame.devtools.ProcessorCardinality;
import net.sf.jame.devtools.ProcessorDescriptor;
import net.sf.jame.devtools.ProcessorParameters;
import net.sf.jame.devtools.extension.DescriptorExtensionRuntime;
import net.sf.jame.devtools.extension.ProcessorExtensionRuntime;

import org.junit.Test;

public class GenerateContextFree {
	private ProcessorParameters createCFDGParameters(Map<String, DescriptorExtensionRuntime> descriptorExtensionMap) {
		List<ProcessorDescriptor> descriptors = new LinkedList<ProcessorDescriptor>();
		descriptors.add(descriptorExtensionMap.get("String").createDescriptor("startshape", "\"\"", ProcessorCardinality.NONE));
		descriptors.add(descriptorExtensionMap.get("Float").createDescriptor("x", "0f", ProcessorCardinality.NONE));
		descriptors.add(descriptorExtensionMap.get("Float").createDescriptor("y", "0f", ProcessorCardinality.NONE));
		descriptors.add(descriptorExtensionMap.get("Float").createDescriptor("width", "0f", ProcessorCardinality.NONE));
		descriptors.add(descriptorExtensionMap.get("Float").createDescriptor("height", "0f", ProcessorCardinality.NONE));
		descriptors.add(descriptorExtensionMap.get("Float").createDescriptor("tileWidth", "0f", ProcessorCardinality.NONE));
		descriptors.add(descriptorExtensionMap.get("Float").createDescriptor("tileHeight", "0f", ProcessorCardinality.NONE));
		descriptors.add(descriptorExtensionMap.get("Color").createDescriptor("background", "new Color32bit(0xFF000000)", ProcessorCardinality.NONE));
		descriptors.add(new ProcessorDescriptor("figure", "Figure", "Figure", "net.sf.jame.contextfree.cfdg.figure", "FigureConfigElement", "net.sf.jame.contextfree.cfdg.figure", "FigureRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.MANY));
		return new ProcessorParameters(new ProcessorDescriptor("cfdg", "CFDG", "CFDG", "net.sf.jame.contextfree.cfdg", "CFDGConfigElement", "net.sf.jame.contextfree.cfdg", "CFDGRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.NONE), descriptors);
	}

	private ProcessorParameters createFigureParameters(Map<String, DescriptorExtensionRuntime> descriptorExtensionMap) {
		List<ProcessorDescriptor> descriptors = new LinkedList<ProcessorDescriptor>();
		descriptors.add(new ProcessorDescriptor("extension", "Figure", "Figure", "net.sf.jame.core.common", "ConfigurableExtensionReferenceElement", null, null, "net.sf.jame.contextfree.cfdg.figure.extension", "FigureExtensionConfig", "net.sf.jame.contextfree.cfdg.figure.extension", "FigureExtensionRuntime", null, null, "net.sf.jame.contextfree", "ContextFreeRegistry", null, null, null, null, null, "get", "set", ProcessorCardinality.NONE));
		return new ProcessorParameters(new ProcessorDescriptor("figure", "Figure", "Figure", "net.sf.jame.contextfree.cfdg.figure", "FigureConfigElement", "net.sf.jame.contextfree.cfdg.figure", "FigureRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.NONE), descriptors);
	}
	
	private ProcessorParameters createShapeAdjustmentParameters(Map<String, DescriptorExtensionRuntime> descriptorExtensionMap) {
		List<ProcessorDescriptor> descriptors = new LinkedList<ProcessorDescriptor>();
		descriptors.add(new ProcessorDescriptor("extension", "ShapeAdjustment", "ShapeAdjustment", "net.sf.jame.core.common", "ConfigurableExtensionReferenceElement", null, null, "net.sf.jame.contextfree.cfdg.shapeAdjustment.extension", "ShapeAdjustmentExtensionConfig", "net.sf.jame.contextfree.cfdg.shapeAdjustment.extension", "ShapeAdjustmentExtensionRuntime", null, null, "net.sf.jame.contextfree", "ContextFreeRegistry", null, null, null, null, null, "get", "set", ProcessorCardinality.NONE));
		return new ProcessorParameters(new ProcessorDescriptor("shapeAdjustment", "ShapeAdjustment", "ShapeAdjustment", "net.sf.jame.contextfree.cfdg.shapeAdjustment", "ShapeAdjustmentConfigElement", "net.sf.jame.contextfree.cfdg.shapeAdjustment", "ShapeAdjustmentRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.NONE), descriptors);
	}

	private ProcessorParameters createShapeReplacementParameters(Map<String, DescriptorExtensionRuntime> descriptorExtensionMap) {
		List<ProcessorDescriptor> descriptors = new LinkedList<ProcessorDescriptor>();
		descriptors.add(new ProcessorDescriptor("extension", "ShapeReplacement", "ShapeReplacement", "net.sf.jame.core.common", "ConfigurableExtensionReferenceElement", null, null, "net.sf.jame.contextfree.cfdg.shapeReplacement.extension", "ShapeReplacementExtensionConfig", "net.sf.jame.contextfree.cfdg.shapeReplacement.extension", "ShapeReplacementExtensionRuntime", null, null, "net.sf.jame.contextfree", "ContextFreeRegistry", null, null, null, null, null, "get", "set", ProcessorCardinality.NONE));
		return new ProcessorParameters(new ProcessorDescriptor("shapeReplacement", "ShapeReplacement", "ShapeReplacement", "net.sf.jame.contextfree.cfdg.shapeReplacement", "ShapeReplacementConfigElement", "net.sf.jame.contextfree.cfdg.shapeReplacement", "ShapeReplacementRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.NONE), descriptors);
	}

	private ProcessorParameters createPathAdjustmentParameters(Map<String, DescriptorExtensionRuntime> descriptorExtensionMap) {
		List<ProcessorDescriptor> descriptors = new LinkedList<ProcessorDescriptor>();
		descriptors.add(new ProcessorDescriptor("extension", "PathAdjustment", "PathAdjustment", "net.sf.jame.core.common", "ConfigurableExtensionReferenceElement", null, null, "net.sf.jame.contextfree.cfdg.pathAdjustment.extension", "PathAdjustmentExtensionConfig", "net.sf.jame.contextfree.cfdg.pathAdjustment.extension", "PathAdjustmentExtensionRuntime", null, null, "net.sf.jame.contextfree", "ContextFreeRegistry", null, null, null, null, null, "get", "set", ProcessorCardinality.NONE));
		return new ProcessorParameters(new ProcessorDescriptor("pathAdjustment", "PathAdjustment", "PathAdjustment", "net.sf.jame.contextfree.cfdg.pathAdjustment", "PathAdjustmentConfigElement", "net.sf.jame.contextfree.cfdg.pathAdjustment", "PathAdjustmentRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.NONE), descriptors);
	}
	
	private ProcessorParameters createPathOperationParameters(Map<String, DescriptorExtensionRuntime> descriptorExtensionMap) {
		List<ProcessorDescriptor> descriptors = new LinkedList<ProcessorDescriptor>();
		descriptors.add(new ProcessorDescriptor("extension", "PathOperation", "PathOperation", "net.sf.jame.core.common", "ConfigurableExtensionReferenceElement", null, null, "net.sf.jame.contextfree.cfdg.pathOperation.extension", "PathOperationExtensionConfig", "net.sf.jame.contextfree.cfdg.pathOperation.extension", "PathOperationExtensionRuntime", null, null, "net.sf.jame.contextfree", "ContextFreeRegistry", null, null, null, null, null, "get", "set", ProcessorCardinality.NONE));
		descriptors.add(new ProcessorDescriptor("pathAdjustment", "PathAdjustment", "PathAdjustment", "net.sf.jame.contextfree.cfdg.pathAdjustment", "PathAdjustmentConfigElement", "net.sf.jame.contextfree.cfdg.pathAdjustment", "PathAdjustmentRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.MANY));
		return new ProcessorParameters(new ProcessorDescriptor("pathOperation", "PathOperation", "PathOperation", "net.sf.jame.contextfree.cfdg.pathOperation", "PathOperationConfigElement", "net.sf.jame.contextfree.cfdg.pathOperation", "PathOperationRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.NONE), descriptors);
	}

	private ProcessorParameters createFigureExtensionParameters() {
		return new ProcessorParameters(new ProcessorDescriptor("figure", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.cfdg.figure.extension", "FigureExtensionConfig", "net.sf.jame.contextfree.cfdg.figure.extension", "FigureExtensionRuntime", "net.sf.jame.contextfree.cfdg.figure.extension", "FigureExtensionRegistry", null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE), new LinkedList<ProcessorDescriptor>());
	}

	private ProcessorParameters createPathAdjustmentExtensionParameters() {
		return new ProcessorParameters(new ProcessorDescriptor("pathAdjustment", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.cfdg.pathAdjustment.extension", "PathAdjustmentExtensionConfig", "net.sf.jame.contextfree.cfdg.pathAdjustment.extension", "PathAdjustmentExtensionRuntime", "net.sf.jame.contextfree.cfdg.pathAdjustment.extension", "PathAdjustmentExtensionRegistry", null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE), new LinkedList<ProcessorDescriptor>());
	}

	private ProcessorParameters createPathOperationExtensionParameters() {
		return new ProcessorParameters(new ProcessorDescriptor("pathOperation", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.cfdg.pathOperation.extension", "PathOperationExtensionConfig", "net.sf.jame.contextfree.cfdg.pathOperation.extension", "PathOperationExtensionRuntime", "net.sf.jame.contextfree.cfdg.pathOperation.extension", "PathOperationExtensionRegistry", null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE), new LinkedList<ProcessorDescriptor>());
	}
	
	private ProcessorParameters createShapeAdjustmentExtensionParameters() {
		return new ProcessorParameters(new ProcessorDescriptor("shapeAdjustment", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.cfdg.shapeAdjustment.extension", "ShapeAdjustmentExtensionConfig", "net.sf.jame.contextfree.cfdg.shapeAdjustment.extension", "ShapeAdjustmentExtensionRuntime", "net.sf.jame.contextfree.cfdg.shapeAdjustment.extension", "ShapeAdjustmentExtensionRegistry", null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE), new LinkedList<ProcessorDescriptor>());
	}
	
	private ProcessorParameters createShapeReplacementExtensionParameters() {
		return new ProcessorParameters(new ProcessorDescriptor("shapeReplacement", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.cfdg.shapeReplacement.extension", "ShapeReplacementExtensionConfig", "net.sf.jame.contextfree.cfdg.shapeReplacement.extension", "ShapeReplacementExtensionRuntime", "net.sf.jame.contextfree.cfdg.shapeReplacement.extension", "ShapeReplacementExtensionRegistry", null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE), new LinkedList<ProcessorDescriptor>());
	}

	private ProcessorParameters createRuleExtensionParameters(Map<String, DescriptorExtensionRuntime> descriptorExtensionMap) {
		List<ProcessorDescriptor> descriptors = new LinkedList<ProcessorDescriptor>();
		descriptors.add(descriptorExtensionMap.get("String").createDescriptor("name", "\"\"", ProcessorCardinality.NONE));
		descriptors.add(descriptorExtensionMap.get("Float").createDescriptor("propability", "1f", ProcessorCardinality.NONE));
		descriptors.add(new ProcessorDescriptor("shapeReplacement", "ShapeReplacement", "ShapeReplacement", "net.sf.jame.contextfree.cfdg.shapeReplacement", "ShapeReplacementConfigElement", "net.sf.jame.contextfree.cfdg.shapeReplacement", "ShapeReplacementRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.MANY));
		return new ProcessorParameters(new ProcessorDescriptor("rule", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.extensions.figure", "RuleFigureConfig", "net.sf.jame.contextfree.extensions.figure", "RuleFigureRuntime", null, null, null, null,"net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE), descriptors);
	}

	private ProcessorParameters createPathExtensionParameters(Map<String, DescriptorExtensionRuntime> descriptorExtensionMap) {
		List<ProcessorDescriptor> descriptors = new LinkedList<ProcessorDescriptor>();
		descriptors.add(descriptorExtensionMap.get("String").createDescriptor("name", "\"\"", ProcessorCardinality.NONE));
		descriptors.add(new ProcessorDescriptor("pathOperation", "PathOperation", "PathOperation", "net.sf.jame.contextfree.cfdg.pathOperation", "PathOperationConfigElement", "net.sf.jame.contextfree.cfdg.pathOperation", "PathOperationRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.MANY));
		return new ProcessorParameters(new ProcessorDescriptor("path", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.extensions.figure", "PathFigureConfig", "net.sf.jame.contextfree.extensions.figure", "PathFigureRuntime", null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE), descriptors);
	}

	private ProcessorParameters createSingleReplacementExtensionParameters(Map<String, DescriptorExtensionRuntime> descriptorExtensionMap) {
		List<ProcessorDescriptor> descriptors = new LinkedList<ProcessorDescriptor>();
		descriptors.add(descriptorExtensionMap.get("String").createDescriptor("shape", "\"\"", ProcessorCardinality.NONE));
		descriptors.add(new ProcessorDescriptor("shapeAdjustment", "ShapeAdjustment", "ShapeAdjustment", "net.sf.jame.contextfree.cfdg.shapeAdjustment", "ShapeAdjustmentConfigElement", "net.sf.jame.contextfree.cfdg.shapeAdjustment", "ShapeAdjustmentRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.MANY));
		return new ProcessorParameters(new ProcessorDescriptor("singleReplacement", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.extensions.shapeReplacement", "SingleReplacementConfig", "net.sf.jame.contextfree.extensions.shapeReplacement", "SingleReplacementRuntime", null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE), descriptors);
	}

	private ProcessorParameters createMultiReplacementExtensionParameters(Map<String, DescriptorExtensionRuntime> descriptorExtensionMap) {
		List<ProcessorDescriptor> descriptors = new LinkedList<ProcessorDescriptor>();
		descriptors.add(descriptorExtensionMap.get("Integer").createDescriptor("times", "1", ProcessorCardinality.NONE));
		descriptors.add(new ProcessorDescriptor("shapeReplacement", "ShapeReplacement", "ShapeReplacement", "net.sf.jame.contextfree.cfdg.shapeReplacement", "ShapeReplacementConfigElement", "net.sf.jame.contextfree.cfdg.shapeReplacement", "ShapeReplacementRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.MANY));
		descriptors.add(new ProcessorDescriptor("shapeAdjustment", "ShapeAdjustment", "ShapeAdjustment", "net.sf.jame.contextfree.cfdg.shapeAdjustment", "ShapeAdjustmentConfigElement", "net.sf.jame.contextfree.cfdg.shapeAdjustment", "ShapeAdjustmentRuntimeElement", null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, "get", "set", ProcessorCardinality.MANY));
		return new ProcessorParameters(new ProcessorDescriptor("multiReplacement", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.extensions.shapeReplacement", "MultiReplacementConfig", "net.sf.jame.contextfree.extensions.shapeReplacement", "MultiReplacementRuntime", null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE), descriptors);
	}
	
	private ProcessorParameters createRegistryParameters() {
		List<ProcessorDescriptor> descriptors = new LinkedList<ProcessorDescriptor>();
		descriptors.add(new ProcessorDescriptor("figure", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.cfdg.figure.extension", "FigureExtensionConfig", "net.sf.jame.contextfree.cfdg.figure.extension", "FigureExtensionRuntime", "net.sf.jame.contextfree.cfdg.figure.extension", "FigureExtensionRegistry", null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE));
		descriptors.add(new ProcessorDescriptor("pathAdjustment", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.cfdg.pathAdjustment.extension", "PathAdjustmentExtensionConfig", "net.sf.jame.contextfree.cfdg.pathAdjustment.extension", "PathAdjustmentExtensionRuntime", "net.sf.jame.contextfree.cfdg.pathAdjustment.extension", "PathAdjustmentExtensionRegistry", null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE));
		descriptors.add(new ProcessorDescriptor("pathOperation", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.cfdg.pathOperation.extension", "PathOperationExtensionConfig", "net.sf.jame.contextfree.cfdg.pathOperation.extension", "PathOperationExtensionRuntime", "net.sf.jame.contextfree.cfdg.pathOperation.extension", "PathOperationExtensionRegistry", null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE));
		descriptors.add(new ProcessorDescriptor("shapeAdjustment", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.cfdg.shapeAdjustment.extension", "ShapeAdjustmentExtensionConfig", "net.sf.jame.contextfree.cfdg.shapeAdjustment.extension", "ShapeAdjustmentExtensionRuntime", "net.sf.jame.contextfree.cfdg.shapeAdjustment.extension", "ShapeAdjustmentExtensionRegistry", null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE));
		descriptors.add(new ProcessorDescriptor("shapeReplacement", "Extension", null, null, null, null, null, "net.sf.jame.contextfree.cfdg.shapeReplacement.extension", "ShapeReplacementExtensionConfig", "net.sf.jame.contextfree.cfdg.shapeReplacement.extension", "ShapeReplacementExtensionRuntime", "net.sf.jame.contextfree.cfdg.shapeReplacement.extension", "ShapeReplacementExtensionRegistry", null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE));
		return new ProcessorParameters(new ProcessorDescriptor("registry", "Registry", null, null, null, null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeRegistry", null, null, null, null, null, null, null, ProcessorCardinality.NONE), descriptors);
	}
	
	private ProcessorParameters createResourcesParameters() {
		return new ProcessorParameters(new ProcessorDescriptor("resources", "Resources", null, null, null, null, null, null, null, null, null, null, null, null, null, "net.sf.jame.contextfree", "ContextFreeResources", null, null, null, null, null, ProcessorCardinality.NONE), new LinkedList<ProcessorDescriptor>());
	}

	private void populateProcessorExtensionMap(Map<String, ProcessorExtensionRuntime> map) {
		List<Extension<ProcessorExtensionRuntime>> extensions = DevToolsRegistry.getInstance().getProcessorRegistry().getExtensionList();
		for (Extension<ProcessorExtensionRuntime> extension : extensions) {
			try {
				ProcessorExtensionRuntime runtime = extension.createExtensionRuntime();
				map.put(runtime.getName(), runtime);
			}
			catch (ExtensionException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void populateDescriptorExtensionMap(Map<String, DescriptorExtensionRuntime> map) {
		List<Extension<DescriptorExtensionRuntime>> extensions = DevToolsRegistry.getInstance().getDescriptorRegistry().getExtensionList();
		for (Extension<DescriptorExtensionRuntime> extension : extensions) {
			try {
				DescriptorExtensionRuntime runtime = extension.createExtensionRuntime();
				map.put(runtime.getClassId(), runtime);
			}
			catch (ExtensionException e) {
				e.printStackTrace();
			}
		}
	}

	private Map<String, String> createVariables() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("author", "Andrea Medeghini");
		map.put("extensionPointId", "net.sf.jame.contextfree.extensions");
		return map;
	}

	@Test
	public void generateCFDG() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createCFDGParameters(descriptorExtensionMap);
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateFigure() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createFigureParameters(descriptorExtensionMap);
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateFigureExtension() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			variables.put("generateAbstractClass", "yes");
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createFigureExtensionParameters();
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generatePathExtension() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createPathExtensionParameters(descriptorExtensionMap);
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateRuleExtension() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createRuleExtensionParameters(descriptorExtensionMap);
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generatePathOperation() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createPathOperationParameters(descriptorExtensionMap);
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generatePathOperationExtension() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			variables.put("generateAbstractClass", "yes");
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createPathOperationExtensionParameters();
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generatePathAdjustment() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createPathAdjustmentParameters(descriptorExtensionMap);
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generatePathAdjustmentExtension() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			variables.put("generateAbstractClass", "yes");
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createPathAdjustmentExtensionParameters();
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateShapeReplacement() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createShapeReplacementParameters(descriptorExtensionMap);
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateShapeReplacementExtension() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			variables.put("generateAbstractClass", "yes");
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createShapeReplacementExtensionParameters();
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateSingleReplacementExtension() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createSingleReplacementExtensionParameters(descriptorExtensionMap);
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateMultiReplacementExtension() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createMultiReplacementExtensionParameters(descriptorExtensionMap);
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateShapeAdjustment() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createShapeAdjustmentParameters(descriptorExtensionMap);
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateShapeAdjustmentExtension() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			variables.put("generateAbstractClass", "yes");
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createShapeAdjustmentExtensionParameters();
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateRegistry() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createRegistryParameters();
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateResources() {
		try {
			File path = new File("build");
			Map<String, ProcessorExtensionRuntime> processorExtensionMap = new HashMap<String, ProcessorExtensionRuntime>();
			populateProcessorExtensionMap(processorExtensionMap);
			Map<String, DescriptorExtensionRuntime> descriptorExtensionMap = new HashMap<String, DescriptorExtensionRuntime>();
			Map<String, String> variables = createVariables();
			populateDescriptorExtensionMap(descriptorExtensionMap);
			ProcessorParameters parameters = createResourcesParameters();
			for (ProcessorExtensionRuntime processorRuntime : processorExtensionMap.values()) {
				processorRuntime.process(path, parameters, variables);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
