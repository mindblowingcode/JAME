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
package net.sf.jame.twister;

import net.sf.jame.core.common.ColorElementXMLExporter;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.twister.effect.EffectConfigElement;
import net.sf.jame.twister.effect.EffectConfigElementXMLExporter;
import net.sf.jame.twister.frame.FrameConfigElement;
import net.sf.jame.twister.frame.FrameConfigElementXMLExporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class TwisterConfigXMLExporter extends XMLExporter<TwisterConfig> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final TwisterConfig config, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, TwisterConfig.CLASS_ID);
		exportProperties(config, element, builder);
		return element;
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportProperties(final TwisterConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		exportBackground(config, createProperty(builder, element, "background"), builder);
		exportFrame(config, createProperty(builder, element, "frame"), builder);
		exportEffect(config, createProperty(builder, element, "effect"), builder);
	}

	/**
	 * @param configElement
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportBackground(final TwisterConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ColorElementXMLExporter().exportToElement(config.getBackgroundElement(), builder));
	}

	/**
	 * @param config
	 * @param builder
	 * @param element
	 * @throws XMLExportException
	 */
	protected void exportFrame(final TwisterConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final FrameConfigElementXMLExporter frameExporter = new FrameConfigElementXMLExporter();
		final FrameConfigElement frame = config.getFrameConfigElement();
		element.appendChild(frameExporter.exportToElement(frame, builder));
	}

	/**
	 * @param config
	 * @param builder
	 * @param element
	 * @throws XMLExportException
	 */
	protected void exportEffect(final TwisterConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final EffectConfigElementXMLExporter effectExporter = new EffectConfigElementXMLExporter();
		final EffectConfigElement effect = config.getEffectConfigElement();
		element.appendChild(effectExporter.exportToElement(effect, builder));
	}
}
