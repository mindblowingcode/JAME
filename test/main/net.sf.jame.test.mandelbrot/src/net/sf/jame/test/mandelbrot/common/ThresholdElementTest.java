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
import net.sf.jame.mandelbrot.common.ThresholdElement;
import net.sf.jame.mandelbrot.common.ThresholdElementNode;
import net.sf.jame.mandelbrot.common.ThresholdElementXMLExporter;
import net.sf.jame.mandelbrot.common.ThresholdElementXMLImporter;
import net.sf.jame.test.AbsractValueElementTest;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class ThresholdElementTest extends AbsractValueElementTest<Double, ThresholdElement> {
	@Override
	protected Double getFirstValue() {
		return new Double(20);
	}

	@Override
	protected Double getSecondValue() {
		return new Double(40);
	}

	@Override
	protected ThresholdElement createConfigElement(final Double defaultValue) {
		final ThresholdElement configElement = new ThresholdElement(defaultValue);
		configElement.setMaximum(100d);
		configElement.setMinimum(1d);
		configElement.setStep(0.1);
		return configElement;
	}

	@Override
	protected Node createElementNode() {
		return new ThresholdElementNode("value", getConfigElement()) {
		};
	}

	@Override
	protected ThresholdElementXMLImporter createXMLImporter() {
		return new ThresholdElementXMLImporter();
	}

	@Override
	protected ThresholdElementXMLExporter createXMLExporter() {
		return new ThresholdElementXMLExporter();
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
