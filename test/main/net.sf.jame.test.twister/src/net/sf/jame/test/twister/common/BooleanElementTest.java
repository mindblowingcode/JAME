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

import net.sf.jame.core.common.BooleanElement;
import net.sf.jame.core.common.BooleanElementNode;
import net.sf.jame.core.common.BooleanElementXMLExporter;
import net.sf.jame.core.common.BooleanElementXMLImporter;
import net.sf.jame.core.tree.Node;
import net.sf.jame.test.AbsractValueElementTest;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class BooleanElementTest extends AbsractValueElementTest<Boolean, BooleanElement> {
	@Override
	protected Boolean getFirstValue() {
		return Boolean.FALSE;
	}

	@Override
	protected Boolean getSecondValue() {
		return Boolean.TRUE;
	}

	@Override
	protected BooleanElement createConfigElement(final Boolean defaultValue) {
		final BooleanElement configElement = new BooleanElement(defaultValue);
		return configElement;
	}

	@Override
	protected Node createElementNode() {
		return new BooleanElementNode("value", getConfigElement()) {
		};
	}

	@Override
	protected BooleanElementXMLImporter createXMLImporter() {
		return new BooleanElementXMLImporter();
	}

	@Override
	protected BooleanElementXMLExporter createXMLExporter() {
		return new BooleanElementXMLExporter();
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
