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
package net.sf.jame.test.twister.common;

import net.sf.jame.core.common.IntegerElement;
import net.sf.jame.core.common.IntegerElementNode;
import net.sf.jame.core.common.IntegerElementXMLExporter;
import net.sf.jame.core.common.IntegerElementXMLImporter;
import net.sf.jame.test.core.AbsractValueElementTest;
import net.sf.jame.core.tree.Node;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class IntegerElementTest extends AbsractValueElementTest<Integer, IntegerElement> {
	@Override
	protected Integer getFirstValue() {
		return new Integer(0);
	}

	@Override
	protected Integer getSecondValue() {
		return new Integer(1);
	}

	@Override
	protected IntegerElement createConfigElement(final Integer defaultValue) {
		final IntegerElement configElement = new IntegerElement(defaultValue);
		return configElement;
	}

	@Override
	protected Node createElementNode() {
		return new IntegerElementNode("value", getConfigElement()) {
		};
	}

	@Override
	protected IntegerElementXMLImporter createXMLImporter() {
		return new IntegerElementXMLImporter();
	}

	@Override
	protected IntegerElementXMLExporter createXMLExporter() {
		return new IntegerElementXMLExporter();
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
