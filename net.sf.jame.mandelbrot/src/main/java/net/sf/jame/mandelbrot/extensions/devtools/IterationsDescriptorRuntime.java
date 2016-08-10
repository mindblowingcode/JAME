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
package net.sf.jame.mandelbrot.extensions.devtools;

import net.sf.jame.devtools.ProcessorCardinality;
import net.sf.jame.devtools.ProcessorDescriptor;
import net.sf.jame.devtools.descriptor.extension.DescriptorExtensionRuntime;
import net.sf.jame.mandelbrot.common.IterationsElement;

public class IterationsDescriptorRuntime extends DescriptorExtensionRuntime {
	/**
	 * @see net.sf.jame.devtools.descriptor.extension.DescriptorExtensionRuntime#createDescriptor()
	 */
	@Override
	public ProcessorDescriptor createDescriptor(String elementName, String defaultValue, ProcessorCardinality cardinality) {
		return new ProcessorDescriptor(elementName, "Iterations", IterationsElement.CLASS_ID, "net.sf.jame.mandelbrot.common", "IterationsElement", null, null, null, null, null, null, null, null, null, null, null, null, "java.lang", "Integer", defaultValue, "get", "set", cardinality);
	}

	/**
	 * @see net.sf.jame.devtools.descriptor.extension.DescriptorExtensionRuntime#getClassId()
	 */
	@Override
	public String getClassId() {
		return IterationsElement.CLASS_ID;
	}
}
