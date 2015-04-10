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
package net.sf.jame.test.mandelbrot.outcolouringFormula;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.test.AbstractConfigurableExtensionConfigElementTest;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.extensions.outcolouringFormula.ModulusConfig;
import net.sf.jame.mandelbrot.extensions.outcolouringFormula.PhaseConfig;
import net.sf.jame.mandelbrot.outcolouringFormula.OutcolouringFormulaExtensionReferenceNodeValue;
import net.sf.jame.mandelbrot.outcolouringFormula.extension.OutcolouringFormulaExtensionConfig;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class OutcolouringFormulaElementTest extends AbstractConfigurableExtensionConfigElementTest<OutcolouringFormulaExtensionConfig> {
	@Override
	protected Node createElementNode() {
		return new ConfigurableExtensionReferenceElementNode<OutcolouringFormulaExtensionConfig>("reference", getConfigElement()) {
			@Override
			protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<OutcolouringFormulaExtensionConfig> value) {
				return new OutcolouringFormulaExtensionReferenceNodeValue(value);
			}
		};
	}

	@Override
	protected ConfigurableExtensionReferenceElement<OutcolouringFormulaExtensionConfig> createConfigElement(final ConfigurableExtensionReference<OutcolouringFormulaExtensionConfig> defaultValue) {
		final ConfigurableExtensionReferenceElement<OutcolouringFormulaExtensionConfig> configElement = new ConfigurableExtensionReferenceElement<OutcolouringFormulaExtensionConfig>();
		configElement.setReference(defaultValue);
		return configElement;
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLExporter<OutcolouringFormulaExtensionConfig> createXMLExporter() {
		return new ConfigurableExtensionReferenceElementXMLExporter<OutcolouringFormulaExtensionConfig>();
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLImporter<OutcolouringFormulaExtensionConfig> createXMLImporter() {
		return new ConfigurableExtensionReferenceElementXMLImporter<OutcolouringFormulaExtensionConfig>(MandelbrotRegistry.getInstance().getOutcolouringFormulaRegistry());
	}

	@Override
	protected ConfigurableExtensionReference<OutcolouringFormulaExtensionConfig> getFirstReference() {
		final ConfigurableExtensionReference<OutcolouringFormulaExtensionConfig> reference = new ConfigurableExtensionReference<OutcolouringFormulaExtensionConfig>("twister.mandelbrot.fractal.outcolouring.formula.phase", "Z Phase", new PhaseConfig());
		return reference;
	}

	@Override
	protected ConfigurableExtensionReference<OutcolouringFormulaExtensionConfig> getSecondReference() {
		final ConfigurableExtensionReference<OutcolouringFormulaExtensionConfig> reference = new ConfigurableExtensionReference<OutcolouringFormulaExtensionConfig>("twister.mandelbrot.fractal.outcolouring.formula.modulus", "Z Modulus", new ModulusConfig());
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
