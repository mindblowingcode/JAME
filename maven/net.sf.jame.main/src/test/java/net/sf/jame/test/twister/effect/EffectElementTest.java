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
package net.sf.jame.test.twister.effect;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.test.AbstractConfigurableExtensionConfigElementTest;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.twister.TwisterRegistry;
import net.sf.jame.twister.effect.EffectExtensionReferenceNodeValue;
import net.sf.jame.twister.effect.extension.EffectExtensionConfig;
import net.sf.jame.twister.extensions.effect.FireConfig;
import net.sf.jame.twister.extensions.effect.WaterConfig;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class EffectElementTest extends AbstractConfigurableExtensionConfigElementTest<EffectExtensionConfig> {
	@Override
	protected Node createElementNode() {
		return new ConfigurableExtensionReferenceElementNode<EffectExtensionConfig>("reference", getConfigElement()) {
			@Override
			protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<EffectExtensionConfig> value) {
				return new EffectExtensionReferenceNodeValue(value);
			}
		};
	}

	@Override
	protected ConfigurableExtensionReferenceElement<EffectExtensionConfig> createConfigElement(final ConfigurableExtensionReference<EffectExtensionConfig> defaultValue) {
		final ConfigurableExtensionReferenceElement<EffectExtensionConfig> configElement = new ConfigurableExtensionReferenceElement<EffectExtensionConfig>();
		configElement.setReference(defaultValue);
		return configElement;
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLExporter<EffectExtensionConfig> createXMLExporter() {
		return new ConfigurableExtensionReferenceElementXMLExporter<EffectExtensionConfig>();
	}

	@Override
	protected ConfigurableExtensionReferenceElementXMLImporter<EffectExtensionConfig> createXMLImporter() {
		return new ConfigurableExtensionReferenceElementXMLImporter<EffectExtensionConfig>(TwisterRegistry.getInstance().getEffectRegistry());
	}

	@Override
	protected ConfigurableExtensionReference<EffectExtensionConfig> getFirstReference() {
		final ConfigurableExtensionReference<EffectExtensionConfig> reference = new ConfigurableExtensionReference<EffectExtensionConfig>("twister.effect.fire", "Fire", new FireConfig());
		return reference;
	}

	@Override
	protected ConfigurableExtensionReference<EffectExtensionConfig> getSecondReference() {
		final ConfigurableExtensionReference<EffectExtensionConfig> reference = new ConfigurableExtensionReference<EffectExtensionConfig>("twister.effect.water", "Water", new WaterConfig());
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
