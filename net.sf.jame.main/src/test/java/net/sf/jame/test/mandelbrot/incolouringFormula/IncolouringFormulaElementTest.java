/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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
package net.sf.jame.test.mandelbrot.incolouringFormula;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.test.core.AbstractConfigurableExtensionConfigElementTest;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.extensions.incolouringFormula.ModulusConfig;
import net.sf.jame.mandelbrot.extensions.incolouringFormula.PhaseConfig;
import net.sf.jame.mandelbrot.incolouringFormula.IncolouringFormulaExtensionReferenceNodeValue;
import net.sf.jame.mandelbrot.incolouringFormula.extension.IncolouringFormulaExtensionConfig;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class IncolouringFormulaElementTest extends AbstractConfigurableExtensionConfigElementTest<IncolouringFormulaExtensionConfig> {
	@Override
	protected Node createElementNode() {
		return new ConfigurableExtensionReferenceElementNode<IncolouringFormulaExtensionConfig>("reference", getConfigElement()) {
			@Override
			protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<IncolouringFormulaExtensionConfig> value) {
				return new IncolouringFormulaExtensionReferenceNodeValue(value);
			}
		};
	}

	@Override
	protected ConfigurableExtensionReferenceElement<IncolouringFormulaExtensionConfig> createConfigElement(final ConfigurableExtensionReference<IncolouringFormulaExtensionConfig> defaultValue) {
		final ConfigurableExtensionReferenceElement<IncolouringFormulaExtensionConfig> configElement = new ConfigurableExtensionReferenceElement<IncolouringFormulaExtensionConfig>();
		configElement.setReference(defaultValue);
		return configElement;
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLExporter<IncolouringFormulaExtensionConfig> createXMLExporter() {
		return new ConfigurableExtensionReferenceElementXMLExporter<IncolouringFormulaExtensionConfig>();
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLImporter<IncolouringFormulaExtensionConfig> createXMLImporter() {
		return new ConfigurableExtensionReferenceElementXMLImporter<IncolouringFormulaExtensionConfig>(MandelbrotRegistry.getInstance().getIncolouringFormulaRegistry());
	}

	@Override
	protected ConfigurableExtensionReference<IncolouringFormulaExtensionConfig> getFirstReference() {
		final ConfigurableExtensionReference<IncolouringFormulaExtensionConfig> reference = new ConfigurableExtensionReference<IncolouringFormulaExtensionConfig>("twister.mandelbrot.fractal.incolouring.formula.phase", "Z Phase", new PhaseConfig());
		return reference;
	}

	@Override
	protected ConfigurableExtensionReference<IncolouringFormulaExtensionConfig> getSecondReference() {
		final ConfigurableExtensionReference<IncolouringFormulaExtensionConfig> reference = new ConfigurableExtensionReference<IncolouringFormulaExtensionConfig>("twister.mandelbrot.fractal.incolouring.formula.modulus", "Z Modulus", new ModulusConfig());
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
