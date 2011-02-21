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
package net.sf.jame.test.mandelbrot.color;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.color.ColorRendererExtensionReferenceNodeValue;
import net.sf.jame.mandelbrot.color.extension.ColorRendererExtensionConfig;
import net.sf.jame.mandelbrot.extensions.color.COSConfig;
import net.sf.jame.mandelbrot.extensions.color.SINConfig;
import net.sf.jame.test.AbstractConfigurableExtensionConfigElementTest;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class ColorRendererElementTest extends AbstractConfigurableExtensionConfigElementTest<ColorRendererExtensionConfig> {
	@Override
	protected Node createElementNode() {
		return new ConfigurableExtensionReferenceElementNode<ColorRendererExtensionConfig>("reference", getConfigElement()) {
			@Override
			protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<ColorRendererExtensionConfig> value) {
				return new ColorRendererExtensionReferenceNodeValue(value);
			}
		};
	}

	@Override
	protected ConfigurableExtensionReferenceElement<ColorRendererExtensionConfig> createConfigElement(final ConfigurableExtensionReference<ColorRendererExtensionConfig> defaultValue) {
		final ConfigurableExtensionReferenceElement<ColorRendererExtensionConfig> configElement = new ConfigurableExtensionReferenceElement<ColorRendererExtensionConfig>();
		configElement.setReference(defaultValue);
		return configElement;
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLExporter<ColorRendererExtensionConfig> createXMLExporter() {
		return new ConfigurableExtensionReferenceElementXMLExporter<ColorRendererExtensionConfig>();
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLImporter<ColorRendererExtensionConfig> createXMLImporter() {
		return new ConfigurableExtensionReferenceElementXMLImporter<ColorRendererExtensionConfig>(MandelbrotRegistry.getInstance().getColorRendererRegistry());
	}

	@Override
	protected ConfigurableExtensionReference<ColorRendererExtensionConfig> getFirstReference() {
		final ConfigurableExtensionReference<ColorRendererExtensionConfig> reference = new ConfigurableExtensionReference<ColorRendererExtensionConfig>("twister.mandelbrot.color.renderer.sin", "SIN", new SINConfig());
		return reference;
	}

	@Override
	protected ConfigurableExtensionReference<ColorRendererExtensionConfig> getSecondReference() {
		final ConfigurableExtensionReference<ColorRendererExtensionConfig> reference = new ConfigurableExtensionReference<ColorRendererExtensionConfig>("twister.mandelbrot.color.renderer.cos", "COS", new COSConfig());
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
