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
package net.sf.jame.test.twister.common;

import net.sf.jame.core.common.ComplexElement;
import net.sf.jame.core.common.ComplexElementNode;
import net.sf.jame.core.common.ComplexElementXMLExporter;
import net.sf.jame.core.common.ComplexElementXMLImporter;
import net.sf.jame.test.core.AbsractValueElementTest;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.util.DoubleVector2D;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class ComplexElementTest extends AbsractValueElementTest<DoubleVector2D, ComplexElement> {
	@Override
	protected DoubleVector2D getFirstValue() {
		return new DoubleVector2D(0, 0);
	}

	@Override
	protected DoubleVector2D getSecondValue() {
		return new DoubleVector2D(1, 1);
	}

	@Override
	protected ComplexElement createConfigElement(final DoubleVector2D defaultValue) {
		final ComplexElement configElement = new ComplexElement(defaultValue);
		return configElement;
	}

	@Override
	protected Node createElementNode() {
		return new ComplexElementNode("value", getConfigElement()) {
		};
	}

	@Override
	protected ComplexElementXMLImporter createXMLImporter() {
		return new ComplexElementXMLImporter();
	}

	@Override
	protected ComplexElementXMLExporter createXMLExporter() {
		return new ComplexElementXMLExporter();
	}

	@Override
	@Test
	public void testSetValue() {
		super.testSetValue();
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

	@Override
	@Test
	public void testActions() {
		super.testActions();
	}
}
