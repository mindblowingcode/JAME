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
package net.sf.jame.test.mandelbrot.common;

import net.sf.jame.core.tree.Node;
import net.sf.jame.mandelbrot.common.RenderedPaletteElement;
import net.sf.jame.mandelbrot.common.RenderedPaletteElementNode;
import net.sf.jame.mandelbrot.common.RenderedPaletteElementXMLExporter;
import net.sf.jame.mandelbrot.common.RenderedPaletteElementXMLImporter;
import net.sf.jame.mandelbrot.util.DefaultRenderedPaletteParam;
import net.sf.jame.mandelbrot.util.RenderedPalette;
import net.sf.jame.mandelbrot.util.RenderedPaletteParam;
import net.sf.jame.test.AbsractValueElementTest;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class RenderedPaletteElementTest extends AbsractValueElementTest<RenderedPalette, RenderedPaletteElement> {
	@Override
	protected RenderedPalette getFirstValue() {
		return new RenderedPalette(new RenderedPaletteParam[] { new DefaultRenderedPaletteParam(new int[] { 0xFF000000, 0xFFFFFFFF }, 100) });
	}

	@Override
	protected RenderedPalette getSecondValue() {
		return new RenderedPalette(new RenderedPaletteParam[] { new DefaultRenderedPaletteParam(new int[] { 0xFF000000, 0xFFFFFFFF }, 100) });
	}

	@Override
	protected RenderedPaletteElement createConfigElement(final RenderedPalette defaultValue) {
		final RenderedPaletteElement configElement = new RenderedPaletteElement(defaultValue);
		return configElement;
	}

	@Override
	protected Node createElementNode() {
		return new RenderedPaletteElementNode("value", getConfigElement()) {
		};
	}

	@Override
	protected RenderedPaletteElementXMLImporter createXMLImporter() {
		return new RenderedPaletteElementXMLImporter();
	}

	@Override
	protected RenderedPaletteElementXMLExporter createXMLExporter() {
		return new RenderedPaletteElementXMLExporter();
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
