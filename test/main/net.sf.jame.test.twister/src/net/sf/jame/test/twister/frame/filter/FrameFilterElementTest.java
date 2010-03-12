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
package net.sf.jame.test.twister.frame.filter;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.test.AbstractConfigurableExtensionConfigElementTest;
import net.sf.jame.twister.TwisterRegistry;
import net.sf.jame.twister.extensions.frame.filter.BlackConfig;
import net.sf.jame.twister.extensions.frame.filter.ColorConfig;
import net.sf.jame.twister.frame.filter.FrameFilterExtensionReferenceNodeValue;
import net.sf.jame.twister.frame.filter.extension.FrameFilterExtensionConfig;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class FrameFilterElementTest extends AbstractConfigurableExtensionConfigElementTest<FrameFilterExtensionConfig> {
	@Override
	protected Node createElementNode() {
		return new ConfigurableExtensionReferenceElementNode<FrameFilterExtensionConfig>("reference", getConfigElement()) {
			@Override
			protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<FrameFilterExtensionConfig> value) {
				return new FrameFilterExtensionReferenceNodeValue(value);
			}
		};
	}

	@Override
	protected ConfigurableExtensionReferenceElement<FrameFilterExtensionConfig> createConfigElement(final ConfigurableExtensionReference<FrameFilterExtensionConfig> defaultValue) {
		final ConfigurableExtensionReferenceElement<FrameFilterExtensionConfig> configElement = new ConfigurableExtensionReferenceElement<FrameFilterExtensionConfig>();
		configElement.setReference(defaultValue);
		return configElement;
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLExporter<FrameFilterExtensionConfig> createXMLExporter() {
		return new ConfigurableExtensionReferenceElementXMLExporter<FrameFilterExtensionConfig>();
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLImporter<FrameFilterExtensionConfig> createXMLImporter() {
		return new ConfigurableExtensionReferenceElementXMLImporter<FrameFilterExtensionConfig>(TwisterRegistry.getInstance().getFrameFilterRegistry());
	}

	@Override
	protected ConfigurableExtensionReference<FrameFilterExtensionConfig> getFirstReference() {
		final ConfigurableExtensionReference<FrameFilterExtensionConfig> reference = new ConfigurableExtensionReference<FrameFilterExtensionConfig>("twister.frame.filter.black", "Black", new BlackConfig());
		return reference;
	}

	@Override
	protected ConfigurableExtensionReference<FrameFilterExtensionConfig> getSecondReference() {
		final ConfigurableExtensionReference<FrameFilterExtensionConfig> reference = new ConfigurableExtensionReference<FrameFilterExtensionConfig>("twister.frame.filter.color", "Color", new ColorConfig());
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
