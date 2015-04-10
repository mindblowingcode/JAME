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

import net.sf.jame.core.tree.Node;
import net.sf.jame.core.util.DoubleVector4D;
import net.sf.jame.core.util.IntegerVector4D;
import net.sf.jame.test.AbsractValueElementTest;
import net.sf.jame.twister.common.ViewElement;
import net.sf.jame.twister.common.ViewElementNode;
import net.sf.jame.twister.common.ViewElementXMLExporter;
import net.sf.jame.twister.common.ViewElementXMLImporter;
import net.sf.jame.twister.util.View;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class ViewElementTest extends AbsractValueElementTest<View, ViewElement> {
	@Override
	protected View getFirstValue() {
		return new View(new IntegerVector4D(0, 0, 0, 0), new DoubleVector4D(0, 0, 1, 0), new DoubleVector4D(0, 0, 0, 0));
	}

	@Override
	protected View getSecondValue() {
		return new View(new IntegerVector4D(0, 0, 0, 0), new DoubleVector4D(0, 0, 2, 0), new DoubleVector4D(0, 0, 0, 0));
	}

	@Override
	protected ViewElement createConfigElement(final View defaultValue) {
		final ViewElement configElement = new ViewElement(defaultValue);
		return configElement;
	}

	@Override
	protected Node createElementNode() {
		return new ViewElementNode("value", getConfigElement()) {
		};
	}

	@Override
	protected ViewElementXMLImporter createXMLImporter() {
		return new ViewElementXMLImporter();
	}

	@Override
	protected ViewElementXMLExporter createXMLExporter() {
		return new ViewElementXMLExporter();
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
