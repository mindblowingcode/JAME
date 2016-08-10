/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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
package net.sf.jame.test.twister.layerFilter;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.test.core.AbstractConfigurableExtensionConfigElementTest;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.twister.TwisterRegistry;
import net.sf.jame.twister.extensions.layerFilter.BlackConfig;
import net.sf.jame.twister.extensions.layerFilter.ColorConfig;
import net.sf.jame.twister.layerFilter.LayerFilterExtensionReferenceNodeValue;
import net.sf.jame.twister.layerFilter.extension.LayerFilterExtensionConfig;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class LayerFilterElementTest extends AbstractConfigurableExtensionConfigElementTest<LayerFilterExtensionConfig> {
	@Override
	protected Node createElementNode() {
		return new ConfigurableExtensionReferenceElementNode<LayerFilterExtensionConfig>("reference", getConfigElement()) {
			@Override
			protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<LayerFilterExtensionConfig> value) {
				return new LayerFilterExtensionReferenceNodeValue(value);
			}
		};
	}

	@Override
	protected ConfigurableExtensionReferenceElement<LayerFilterExtensionConfig> createConfigElement(final ConfigurableExtensionReference<LayerFilterExtensionConfig> defaultValue) {
		final ConfigurableExtensionReferenceElement<LayerFilterExtensionConfig> configElement = new ConfigurableExtensionReferenceElement<LayerFilterExtensionConfig>();
		configElement.setReference(defaultValue);
		return configElement;
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLExporter<LayerFilterExtensionConfig> createXMLExporter() {
		return new ConfigurableExtensionReferenceElementXMLExporter<LayerFilterExtensionConfig>();
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLImporter<LayerFilterExtensionConfig> createXMLImporter() {
		return new ConfigurableExtensionReferenceElementXMLImporter<LayerFilterExtensionConfig>(TwisterRegistry.getInstance().getLayerFilterRegistry());
	}

	@Override
	protected ConfigurableExtensionReference<LayerFilterExtensionConfig> getFirstReference() {
		final ConfigurableExtensionReference<LayerFilterExtensionConfig> reference = new ConfigurableExtensionReference<LayerFilterExtensionConfig>("twister.frame.layer.filter.black", "Black", new BlackConfig());
		return reference;
	}

	@Override
	protected ConfigurableExtensionReference<LayerFilterExtensionConfig> getSecondReference() {
		final ConfigurableExtensionReference<LayerFilterExtensionConfig> reference = new ConfigurableExtensionReference<LayerFilterExtensionConfig>("twister.frame.layer.filter.color", "Color", new ColorConfig());
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
