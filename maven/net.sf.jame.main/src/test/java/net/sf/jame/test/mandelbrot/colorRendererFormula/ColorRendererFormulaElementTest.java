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
package net.sf.jame.test.mandelbrot.colorRendererFormula;

import net.sf.jame.core.common.ExtensionReferenceElement;
import net.sf.jame.core.common.ExtensionReferenceElementNode;
import net.sf.jame.core.common.ExtensionReferenceElementXMLExporter;
import net.sf.jame.core.common.ExtensionReferenceElementXMLImporter;
import net.sf.jame.core.extension.ExtensionReference;
import net.sf.jame.core.test.AbstractExtensionConfigElementTest;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.colorRendererFormula.ColorRendererFormulaExtensionReferenceNodeValue;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class ColorRendererFormulaElementTest extends AbstractExtensionConfigElementTest {
	@Override
	protected Node createElementNode() {
		return new ExtensionReferenceElementNode("reference", getConfigElement()) {
			@Override
			protected NodeValue<?> createNodeValue(final ExtensionReference value) {
				return new ColorRendererFormulaExtensionReferenceNodeValue(value);
			}
		};
	}

	@Override
	protected ExtensionReferenceElement createConfigElement(final ExtensionReference defaultValue) {
		final ExtensionReferenceElement configElement = new ExtensionReferenceElement();
		configElement.setReference(defaultValue);
		return configElement;
	}

	@Override
	protected ExtensionReferenceElementXMLExporter createXMLExporter() {
		return new ExtensionReferenceElementXMLExporter();
	}

	@Override
	protected ExtensionReferenceElementXMLImporter createXMLImporter() {
		return new ExtensionReferenceElementXMLImporter(MandelbrotRegistry.getInstance().getColorRendererFormulaRegistry());
	}

	@Override
	protected ExtensionReference getFirstReference() {
		final ExtensionReference reference = new ExtensionReference("twister.mandelbrot.color.renderer.formula.phase", "atan2(ZI, ZR)");
		return reference;
	}

	@Override
	protected ExtensionReference getSecondReference() {
		final ExtensionReference reference = new ExtensionReference("twister.mandelbrot.color.renderer.formula.modulus", "sqrt(ZR^2 + ZI^2)");
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
