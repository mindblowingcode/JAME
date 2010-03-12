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
package net.sf.jame.test.mandelbrot.fractal.transforming;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.extensions.fractal.transforming.YConfig;
import net.sf.jame.mandelbrot.extensions.fractal.transforming.ZConfig;
import net.sf.jame.mandelbrot.fractal.transforming.TransformingFormulaExtensionReferenceNodeValue;
import net.sf.jame.mandelbrot.fractal.transforming.extension.TransformingFormulaExtensionConfig;
import net.sf.jame.test.AbstractConfigurableExtensionConfigElementTest;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class TransformingFormulaElementTest extends AbstractConfigurableExtensionConfigElementTest<TransformingFormulaExtensionConfig> {
	@Override
	protected Node createElementNode() {
		return new ConfigurableExtensionReferenceElementNode<TransformingFormulaExtensionConfig>("reference", getConfigElement()) {
			@Override
			protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<TransformingFormulaExtensionConfig> value) {
				return new TransformingFormulaExtensionReferenceNodeValue(value);
			}
		};
	}

	@Override
	protected ConfigurableExtensionReferenceElement<TransformingFormulaExtensionConfig> createConfigElement(final ConfigurableExtensionReference<TransformingFormulaExtensionConfig> defaultValue) {
		final ConfigurableExtensionReferenceElement<TransformingFormulaExtensionConfig> configElement = new ConfigurableExtensionReferenceElement<TransformingFormulaExtensionConfig>();
		configElement.setReference(defaultValue);
		return configElement;
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLExporter<TransformingFormulaExtensionConfig> createXMLExporter() {
		return new ConfigurableExtensionReferenceElementXMLExporter<TransformingFormulaExtensionConfig>();
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLImporter<TransformingFormulaExtensionConfig> createXMLImporter() {
		return new ConfigurableExtensionReferenceElementXMLImporter<TransformingFormulaExtensionConfig>(MandelbrotRegistry.getInstance().getTransformingFormulaRegistry());
	}

	@Override
	protected ConfigurableExtensionReference<TransformingFormulaExtensionConfig> getFirstReference() {
		final ConfigurableExtensionReference<TransformingFormulaExtensionConfig> reference = new ConfigurableExtensionReference<TransformingFormulaExtensionConfig>("twister.mandelbrot.fractal.transforming.formula.z", "Z", new ZConfig());
		return reference;
	}

	@Override
	protected ConfigurableExtensionReference<TransformingFormulaExtensionConfig> getSecondReference() {
		final ConfigurableExtensionReference<TransformingFormulaExtensionConfig> reference = new ConfigurableExtensionReference<TransformingFormulaExtensionConfig>("twister.mandelbrot.fractal.transforming.formula.y", "Y", new YConfig());
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
