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
package net.sf.jame.test.mandelbrot.fractal.rendering;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.extensions.fractal.rendering.Z2Config;
import net.sf.jame.mandelbrot.extensions.fractal.rendering.Z3Config;
import net.sf.jame.mandelbrot.fractal.rendering.RenderingFormulaExtensionReferenceNodeValue;
import net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig;
import net.sf.jame.test.AbstractConfigurableExtensionConfigElementTest;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class RenderingFormulaElementTest extends AbstractConfigurableExtensionConfigElementTest<RenderingFormulaExtensionConfig> {
	@Override
	protected Node createElementNode() {
		return new ConfigurableExtensionReferenceElementNode<RenderingFormulaExtensionConfig>("reference", getConfigElement()) {
			@Override
			protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<RenderingFormulaExtensionConfig> value) {
				return new RenderingFormulaExtensionReferenceNodeValue(value);
			}
		};
	}

	@Override
	protected ConfigurableExtensionReferenceElement<RenderingFormulaExtensionConfig> createConfigElement(final ConfigurableExtensionReference<RenderingFormulaExtensionConfig> defaultValue) {
		final ConfigurableExtensionReferenceElement<RenderingFormulaExtensionConfig> configElement = new ConfigurableExtensionReferenceElement<RenderingFormulaExtensionConfig>();
		configElement.setReference(defaultValue);
		return configElement;
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLExporter<RenderingFormulaExtensionConfig> createXMLExporter() {
		return new ConfigurableExtensionReferenceElementXMLExporter<RenderingFormulaExtensionConfig>();
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLImporter<RenderingFormulaExtensionConfig> createXMLImporter() {
		return new ConfigurableExtensionReferenceElementXMLImporter<RenderingFormulaExtensionConfig>(MandelbrotRegistry.getInstance().getRenderingFormulaRegistry());
	}

	@Override
	protected ConfigurableExtensionReference<RenderingFormulaExtensionConfig> getFirstReference() {
		final ConfigurableExtensionReference<RenderingFormulaExtensionConfig> reference = new ConfigurableExtensionReference<RenderingFormulaExtensionConfig>("twister.mandelbrot.fractal.rendering.formula.z2", "Z2", new Z2Config());
		return reference;
	}

	@Override
	protected ConfigurableExtensionReference<RenderingFormulaExtensionConfig> getSecondReference() {
		final ConfigurableExtensionReference<RenderingFormulaExtensionConfig> reference = new ConfigurableExtensionReference<RenderingFormulaExtensionConfig>("twister.mandelbrot.fractal.rendering.formula.z3", "Z3", new Z3Config());
		return reference;
	}

	@Override
	@Test
	public void testSetReference() {
		super.testSetReference();
	}

	@Override
	@Test
	public void testNode() {
		super.testNode();
	}

	@Override
	@Test
	public void testClone() {
		super.testClone();
	}

	@Override
	@Test
	public void testSerialization() {
		super.testSerialization();
	}

	@Override
	@Test
	public void testXML() {
		super.testXML();
	}
}
