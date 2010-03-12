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
package net.sf.jame.test.twister.common;

import net.sf.jame.core.common.DoubleElement;
import net.sf.jame.core.common.DoubleElementNode;
import net.sf.jame.core.common.DoubleElementXMLExporter;
import net.sf.jame.core.common.DoubleElementXMLImporter;
import net.sf.jame.core.tree.Node;
import net.sf.jame.test.AbsractValueElementTest;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class DoubleElementTest extends AbsractValueElementTest<Double, DoubleElement> {
	@Override
	protected Double getFirstValue() {
		return new Double(0);
	}

	@Override
	protected Double getSecondValue() {
		return new Double(1);
	}

	@Override
	protected DoubleElement createConfigElement(final Double defaultValue) {
		final DoubleElement configElement = new DoubleElement(defaultValue);
		return configElement;
	}

	@Override
	protected Node createElementNode() {
		return new DoubleElementNode("value", getConfigElement()) {
		};
	}

	@Override
	protected DoubleElementXMLImporter createXMLImporter() {
		return new DoubleElementXMLImporter();
	}

	@Override
	protected DoubleElementXMLExporter createXMLExporter() {
		return new DoubleElementXMLExporter();
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
