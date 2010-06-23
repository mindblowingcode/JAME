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
package net.sf.jame.mandelbrot.extensions.fractal.orbittrap;

import net.sf.jame.core.common.DoubleElementXMLExporter;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime;
import net.sf.jame.mandelbrot.common.CriteriaElementXMLExporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class CircleTrapConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<CircleTrapConfig> createXMLExporter() {
		return new CircleTrapConfigXMLExporter();
	}

	private class CircleTrapConfigXMLExporter extends AbstractOrbitTrapConfigXMLExporter<CircleTrapConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractOrbitTrapConfigXMLExporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "CircleTrapConfig";
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractOrbitTrapConfigXMLExporter#exportProperties(net.sf.jame.mandelbrot.fractal.incolouring.extension.OrbitTrapExtensionConfig, org.w3c.dom.Element, net.sf.jame.core.xml.XMLNodeBuilder)
		 */
		@Override
		protected void exportProperties(final CircleTrapConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			exportThreshold(config, createProperty(builder, element, "threshold"), builder);
			exportSize(config, createProperty(builder, element, "size"), builder);
			exportCriteria(config, createProperty(builder, element, "criteria"), builder);
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportThreshold(final CircleTrapConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new DoubleElementXMLExporter().exportToElement(config.getThresholdElement(), builder));
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportSize(final CircleTrapConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new DoubleElementXMLExporter().exportToElement(config.getSizeElement(), builder));
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportCriteria(final CircleTrapConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new CriteriaElementXMLExporter().exportToElement(config.getCriteriaElement(), builder));
		}
	}
}
