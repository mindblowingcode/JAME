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
package net.sf.jame.twister.extensions.effect;

import net.sf.jame.core.common.ComplexElementXMLExporter;
import net.sf.jame.core.extensionConfigXMLExporter.extension.ExtensionConfigXMLExporterExtensionRuntime;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.twister.common.PercentageElementXMLExporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class SpotConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLExporter.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<SpotConfig> createXMLExporter() {
		return new SpotConfigXMLExporter();
	}

	private class SpotConfigXMLExporter extends AbstractEffectConfigXMLExporter<SpotConfig> {
		/**
		 * @see net.sf.jame.twister.extensions.layerFilter.AbstractEffectConfigXMLExporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "SpotConfig";
		}

		/**
		 * @see net.sf.jame.twister.extensions.layerFilter.AbstractEffectConfigXMLExporter#exportToElement(net.sf.jame.twister.layerFilter.extension.EffectExtensionConfig, net.sf.jame.core.xml.XMLNodeBuilder)
		 */
		@Override
		public Element exportToElement(final SpotConfig config, final XMLNodeBuilder builder) throws XMLExportException {
			final Element element = super.exportToElement(config, builder);
			exportProperties(config, element, builder);
			return element;
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportProperties(final SpotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			exportIntensity(config, createProperty(builder, element, "intensity"), builder);
			exportCenter(config, createProperty(builder, element, "center"), builder);
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportIntensity(final SpotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new PercentageElementXMLExporter().exportToElement(config.getIntensityElement(), builder));
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportCenter(final SpotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new ComplexElementXMLExporter().exportToElement(config.getCenterElement(), builder));
		}
	}
}
