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
package net.sf.jame.test.mandelbrot.paletteRenderer;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.extensions.paletteRenderer.DefaultRendererConfig;
import net.sf.jame.mandelbrot.extensions.paletteRenderer.GrayGradientRendererConfig;
import net.sf.jame.mandelbrot.paletteRenderer.PaletteRendererExtensionReferenceNodeValue;
import net.sf.jame.mandelbrot.paletteRenderer.extension.PaletteRendererExtensionConfig;
import net.sf.jame.test.AbstractConfigurableExtensionConfigElementTest;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class PaletteRendererElementTest extends AbstractConfigurableExtensionConfigElementTest<PaletteRendererExtensionConfig> {
	@Override
	protected Node createElementNode() {
		return new ConfigurableExtensionReferenceElementNode<PaletteRendererExtensionConfig>("reference", getConfigElement()) {
			@Override
			protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<PaletteRendererExtensionConfig> value) {
				return new PaletteRendererExtensionReferenceNodeValue(value);
			}
		};
	}

	@Override
	protected ConfigurableExtensionReferenceElement<PaletteRendererExtensionConfig> createConfigElement(final ConfigurableExtensionReference<PaletteRendererExtensionConfig> defaultValue) {
		final ConfigurableExtensionReferenceElement<PaletteRendererExtensionConfig> configElement = new ConfigurableExtensionReferenceElement<PaletteRendererExtensionConfig>();
		configElement.setReference(defaultValue);
		return configElement;
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLExporter<PaletteRendererExtensionConfig> createXMLExporter() {
		return new ConfigurableExtensionReferenceElementXMLExporter<PaletteRendererExtensionConfig>();
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLImporter<PaletteRendererExtensionConfig> createXMLImporter() {
		return new ConfigurableExtensionReferenceElementXMLImporter<PaletteRendererExtensionConfig>(MandelbrotRegistry.getInstance().getPaletteRendererRegistry());
	}

	@Override
	protected ConfigurableExtensionReference<PaletteRendererExtensionConfig> getFirstReference() {
		final ConfigurableExtensionReference<PaletteRendererExtensionConfig> reference = new ConfigurableExtensionReference<PaletteRendererExtensionConfig>("twister.mandelbrot.palette.renderer.default", "Default", new DefaultRendererConfig());
		return reference;
	}

	@Override
	protected ConfigurableExtensionReference<PaletteRendererExtensionConfig> getSecondReference() {
		final ConfigurableExtensionReference<PaletteRendererExtensionConfig> reference = new ConfigurableExtensionReference<PaletteRendererExtensionConfig>("twister.mandelbrot.palette.renderer.grayGradient", "Gray Gradient", new GrayGradientRendererConfig());
		return reference;
	}

	@Override
	@Test
	public void testSetReference() {
		super.testSetReference();
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
}
