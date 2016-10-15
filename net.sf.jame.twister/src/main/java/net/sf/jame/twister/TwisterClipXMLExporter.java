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
package net.sf.jame.twister;

import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class TwisterClipXMLExporter extends XMLExporter<TwisterClip> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final TwisterClip clip, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, "clip");
		exportProperties(clip, element, builder);
		return element;
	}

	/**
	 * @param clip
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportProperties(final TwisterClip clip, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		exportSequenceList(clip, createProperty(builder, element, "sequenceList"), builder);
	}

	/**
	 * @param clip
	 * @param builder
	 * @param element
	 * @throws XMLExportException
	 */
	protected void exportSequenceList(final TwisterClip clip, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final TwisterSequenceXMLExporter sequenceExporter = new TwisterSequenceXMLExporter();
		for (int i = 0; i < clip.getSequenceCount(); i++) {
			final TwisterSequence sequence = clip.getSequence(i);
			element.appendChild(sequenceExporter.exportToElement(sequence, builder));
		}
	}
}
