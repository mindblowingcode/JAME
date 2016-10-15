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
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.util.List;

import net.sf.jame.contextfree.common.FillRuleElement;
import net.sf.jame.contextfree.common.FillRuleElementXMLImporter;
import net.sf.jame.contextfree.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.pathAdjustment.PathAdjustmentConfigElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class FillPathReplacementConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<FillPathReplacementConfig> createXMLImporter() {
		return new FillPathReplacementConfigXMLImporter();
	}

	private class FillPathReplacementConfigXMLImporter extends XMLImporter<FillPathReplacementConfig> {
		protected FillPathReplacementConfig createExtensionConfig() {
			return new FillPathReplacementConfig();
		}

		protected String getConfigElementClassId() {
			return "FillPathReplacementConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public FillPathReplacementConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final FillPathReplacementConfig extensionConfig = this.createExtensionConfig();
			final List<Element> propertyElements = getProperties(element);
			if (propertyElements.size() == 2) {
				try {
					importProperties(extensionConfig, propertyElements);
				}
				catch (final ExtensionException e) {
					throw new XMLImportException(e);
				}
			}
			return extensionConfig;
		}
	
		/**
		 * @param extensionConfig
		 * @param propertyElements
		 * @throws ExtensionException
		 * @throws XMLImportException
		 */
		protected void importProperties(final FillPathReplacementConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importRule(extensionConfig, propertyElements.get(0));
			importPathAdjustmentListElement(extensionConfig, propertyElements.get(1));
		}
	
		private void importRule(final FillPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> ruleElements = this.getElements(element, FillRuleElement.CLASS_ID);
			if (ruleElements.size() == 1) {
				extensionConfig.setRule(new FillRuleElementXMLImporter().importFromElement(ruleElements.get(0)).getValue());
			}
		}
		private void importPathAdjustmentListElement(final FillPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final PathAdjustmentConfigElementXMLImporter pathAdjustmentImporter = new PathAdjustmentConfigElementXMLImporter();
			final List<Element> pathAdjustmentElements = this.getElements(element, PathAdjustmentConfigElement.CLASS_ID);
			for (int i = 0; i < pathAdjustmentElements.size(); i++) {
				extensionConfig.appendPathAdjustmentConfigElement(pathAdjustmentImporter.importFromElement(pathAdjustmentElements.get(i)));
			}
		}
	}
}
