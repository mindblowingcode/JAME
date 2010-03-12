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
package net.sf.jame.test.mandelbrot.common;

import net.sf.jame.core.tree.Node;
import net.sf.jame.mandelbrot.common.IterationsElement;
import net.sf.jame.mandelbrot.common.IterationsElementNode;
import net.sf.jame.mandelbrot.common.IterationsElementXMLExporter;
import net.sf.jame.mandelbrot.common.IterationsElementXMLImporter;
import net.sf.jame.test.AbsractValueElementTest;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class IterationsElementTest extends AbsractValueElementTest<Integer, IterationsElement> {
	@Override
	protected Integer getFirstValue() {
		return new Integer(100);
	}

	@Override
	protected Integer getSecondValue() {
		return new Integer(1000);
	}

	@Override
	protected IterationsElement createConfigElement(final Integer defaultValue) {
		final IterationsElement configElement = new IterationsElement(defaultValue);
		configElement.setMaximum(100);
		configElement.setMinimum(1);
		configElement.setStep(1);
		return configElement;
	}

	@Override
	protected Node createElementNode() {
		return new IterationsElementNode("value", getConfigElement()) {
		};
	}

	@Override
	protected IterationsElementXMLImporter createXMLImporter() {
		return new IterationsElementXMLImporter();
	}

	@Override
	protected IterationsElementXMLExporter createXMLExporter() {
		return new IterationsElementXMLExporter();
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
