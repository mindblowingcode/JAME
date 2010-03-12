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

import java.util.List;

import net.sf.jame.core.common.DoubleElement;
import net.sf.jame.core.common.DoubleElementXMLImporter;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime;
import net.sf.jame.mandelbrot.common.CriteriaElement;
import net.sf.jame.mandelbrot.common.CriteriaElementXMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class CircleTrapConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<CircleTrapConfig> createXMLImporter() {
		return new CircleTrapConfigXMLImporter();
	}

	private class CircleTrapConfigXMLImporter extends AbstractOrbitTrapConfigXMLImporter<CircleTrapConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractOrbitTrapConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected CircleTrapConfig createExtensionConfig() {
			return new CircleTrapConfig();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractOrbitTrapConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "CircleTrapConfig";
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractOrbitTrapConfigXMLImporter#getPropertiesSize()
		 */
		@Override
		protected int getPropertiesSize() {
			return 3;
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractOrbitTrapConfigXMLImporter#importProperties(net.sf.jame.mandelbrot.fractal.incolouring.extension.OrbitTrapExtensionConfig, java.util.List)
		 */
		@Override
		protected void importProperties(final CircleTrapConfig config, final List<Element> propertyElements) throws XMLImportException {
			importThreshold(config, propertyElements.get(0));
			importSize(config, propertyElements.get(1));
			importCriteria(config, propertyElements.get(2));
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importThreshold(final CircleTrapConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, DoubleElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getThresholdElement().setValue(new DoubleElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importSize(final CircleTrapConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, DoubleElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getSizeElement().setValue(new DoubleElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importCriteria(final CircleTrapConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, CriteriaElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getCriteriaElement().setValue(new CriteriaElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}
	}
}
