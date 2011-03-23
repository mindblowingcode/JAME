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
package net.sf.jame.test.twister.frame.layer.image;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.test.AbstractConfigurableExtensionConfigElementTest;
import net.sf.jame.twister.TwisterRegistry;
import net.sf.jame.twister.extensions.frame.layer.image.BlackConfig;
import net.sf.jame.twister.extensions.frame.layer.image.ColorConfig;
import net.sf.jame.twister.frame.layer.image.ImageExtensionReferenceNodeValue;
import net.sf.jame.twister.frame.layer.image.extension.ImageExtensionConfig;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class ImageElementTest extends AbstractConfigurableExtensionConfigElementTest<ImageExtensionConfig> {
	@Override
	protected Node createElementNode() {
		return new ConfigurableExtensionReferenceElementNode<ImageExtensionConfig>("reference", getConfigElement()) {
			@Override
			protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<ImageExtensionConfig> value) {
				return new ImageExtensionReferenceNodeValue(value);
			}
		};
	}

	@Override
	protected ConfigurableExtensionReferenceElement<ImageExtensionConfig> createConfigElement(final ConfigurableExtensionReference<ImageExtensionConfig> defaultValue) {
		final ConfigurableExtensionReferenceElement<ImageExtensionConfig> configElement = new ConfigurableExtensionReferenceElement<ImageExtensionConfig>();
		configElement.setReference(defaultValue);
		return configElement;
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLExporter<ImageExtensionConfig> createXMLExporter() {
		return new ConfigurableExtensionReferenceElementXMLExporter<ImageExtensionConfig>();
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLImporter<ImageExtensionConfig> createXMLImporter() {
		return new ConfigurableExtensionReferenceElementXMLImporter<ImageExtensionConfig>(TwisterRegistry.getInstance().getImageRegistry());
	}

	@Override
	protected ConfigurableExtensionReference<ImageExtensionConfig> getFirstReference() {
		final ConfigurableExtensionReference<ImageExtensionConfig> reference = new ConfigurableExtensionReference<ImageExtensionConfig>("twister.frame.layer.image.black", "Black", new BlackConfig());
		return reference;
	}

	@Override
	protected ConfigurableExtensionReference<ImageExtensionConfig> getSecondReference() {
		final ConfigurableExtensionReference<ImageExtensionConfig> reference = new ConfigurableExtensionReference<ImageExtensionConfig>("twister.frame.layer.image.color", "Color", new ColorConfig());
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
