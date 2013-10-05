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

import net.sf.jame.core.common.ShortElement;
import net.sf.jame.core.common.ShortElementNode;
import net.sf.jame.core.common.ShortElementXMLExporter;
import net.sf.jame.core.common.ShortElementXMLImporter;
import net.sf.jame.core.test.AbsractValueElementTest;
import net.sf.jame.core.tree.Node;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class ShortElementTest extends AbsractValueElementTest<Short, ShortElement> {
	@Override
	protected Short getFirstValue() {
		return new Short((short) 0);
	}

	@Override
	protected Short getSecondValue() {
		return new Short((short) 1);
	}

	@Override
	protected ShortElement createConfigElement(final Short defaultValue) {
		final ShortElement configElement = new ShortElement(defaultValue);
		return configElement;
	}

	@Override
	protected Node createElementNode() {
		return new ShortElementNode("value", getConfigElement()) {
		};
	}

	@Override
	protected ShortElementXMLImporter createXMLImporter() {
		return new ShortElementXMLImporter();
	}

	@Override
	protected ShortElementXMLExporter createXMLExporter() {
		return new ShortElementXMLExporter();
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
