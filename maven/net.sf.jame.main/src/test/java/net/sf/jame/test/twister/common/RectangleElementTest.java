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

import net.sf.jame.core.common.RectangleElement;
import net.sf.jame.core.common.RectangleElementNode;
import net.sf.jame.core.common.RectangleElementXMLExporter;
import net.sf.jame.core.common.RectangleElementXMLImporter;
import net.sf.jame.core.test.AbsractValueElementTest;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.util.Rectangle;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class RectangleElementTest extends AbsractValueElementTest<Rectangle, RectangleElement> {
	@Override
	protected Rectangle getFirstValue() {
		return new Rectangle(0, 0, 100, 100);
	}

	@Override
	protected Rectangle getSecondValue() {
		return new Rectangle(0, 0, 200, 200);
	}

	@Override
	protected RectangleElement createConfigElement(final Rectangle defaultValue) {
		final RectangleElement configElement = new RectangleElement(defaultValue);
		return configElement;
	}

	@Override
	protected Node createElementNode() {
		return new RectangleElementNode("value", getConfigElement()) {
		};
	}

	@Override
	protected RectangleElementXMLImporter createXMLImporter() {
		return new RectangleElementXMLImporter();
	}

	@Override
	protected RectangleElementXMLExporter createXMLExporter() {
		return new RectangleElementXMLExporter();
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
