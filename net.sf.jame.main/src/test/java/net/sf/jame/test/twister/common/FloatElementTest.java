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

import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.common.FloatElementNode;
import net.sf.jame.core.common.FloatElementXMLExporter;
import net.sf.jame.core.common.FloatElementXMLImporter;
import net.sf.jame.test.core.AbsractValueElementTest;
import net.sf.jame.core.tree.Node;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class FloatElementTest extends AbsractValueElementTest<Float, FloatElement> {
	@Override
	protected Float getFirstValue() {
		return new Float(0);
	}

	@Override
	protected Float getSecondValue() {
		return new Float(1);
	}

	@Override
	protected FloatElement createConfigElement(final Float defaultValue) {
		final FloatElement configElement = new FloatElement(defaultValue);
		return configElement;
	}

	@Override
	protected Node createElementNode() {
		return new FloatElementNode("value", getConfigElement()) {
		};
	}

	@Override
	protected FloatElementXMLImporter createXMLImporter() {
		return new FloatElementXMLImporter();
	}

	@Override
	protected FloatElementXMLExporter createXMLExporter() {
		return new FloatElementXMLExporter();
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
