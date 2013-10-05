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
package net.sf.jame.queue.spool.job;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import net.sf.jame.core.xml.XML;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.queue.spool.JobData;
import net.sf.jame.twister.TwisterClip;
import net.sf.jame.twister.TwisterClipXMLExporter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class DistributedJobEncoder {
	private final JobData jobData;
	private final TwisterClip clip;
	private final byte[] frameData;

	/**
	 * @param clip
	 * @param jobDataRow
	 * @param jobData
	 */
	public DistributedJobEncoder(final TwisterClip clip, final JobData jobDataRow, final byte[] jobData) {
		this.clip = clip;
		this.jobData = jobDataRow;
		frameData = jobData;
		if (jobDataRow == null) {
			throw new IllegalArgumentException("jobDataRow is null");
		}
		if (clip == null) {
			throw new IllegalArgumentException("clip is null");
		}
	}

	/**
	 * @return the clip
	 */
	public TwisterClip getClip() {
		return clip;
	}

	/**
	 * @return the jobData
	 */
	public JobData getJobData() {
		return jobData;
	}

	/**
	 * @return the frameData
	 */
	public byte[] getFrameData() {
		return frameData;
	}

	private void writeClip(final OutputStream os, final TwisterClip clip) throws IOException {
		try {
			final TwisterClipXMLExporter exporter = new TwisterClipXMLExporter();
			final Document doc = XML.createDocument();
			final XMLNodeBuilder builder = XML.createDefaultXMLNodeBuilder(doc);
			final Element element = exporter.exportToElement(clip, builder);
			doc.appendChild(element);
			XML.saveDocument(os, "twister-clip.xml", doc);
			os.close();
		}
		catch (final Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public byte[] getBytes() throws IOException {
		try {
			final ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			writeClip(baos2, clip);
			baos2.close();
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final ObjectOutputStream oos = new ObjectOutputStream(baos);
			byte[] clipData = baos2.toByteArray();
			oos.writeObject(clipData);
			oos.writeObject(jobData);
			oos.writeObject(frameData);
			oos.close();
			baos.close();
			final byte[] data = baos.toByteArray();
			return data;
		}
		catch (final Exception e) {
			throw new IOException("An error has happened marshalling the data: " + e.getMessage());
		}
	}
}
