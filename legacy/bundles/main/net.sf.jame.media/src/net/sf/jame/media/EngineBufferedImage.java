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
package net.sf.jame.media;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

class EngineBufferedImage implements EngineImage {
	BufferedImage image;
	BufferedImage copy;

	public EngineBufferedImage(final URL url) {
		try {
			final BufferedImage sorgente = ImageIO.read(url);
			copy = new BufferedImage(sorgente.getWidth(), sorgente.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = copy.createGraphics();
			g2d.drawImage(sorgente, 0, 0, null);
			g2d.dispose();
			image = new BufferedImage(sorgente.getWidth(), sorgente.getHeight(), BufferedImage.TYPE_INT_ARGB);
			g2d = image.createGraphics();
			g2d.drawImage(sorgente, 0, 0, null);
			g2d.dispose();
			sorgente.flush();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public EngineBufferedImage(final BufferedImage sorgente) {
		copy = new BufferedImage(sorgente.getWidth(), sorgente.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = copy.createGraphics();
		g2d.drawImage(sorgente, 0, 0, null);
		g2d.dispose();
		image = new BufferedImage(sorgente.getWidth(), sorgente.getHeight(), BufferedImage.TYPE_INT_ARGB);
		g2d = image.createGraphics();
		g2d.drawImage(sorgente, 0, 0, null);
		g2d.dispose();
	}

	public EngineBufferedImage(int width, int height) {
		copy = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	/**
	 * @see net.sf.jame.media.EngineImage#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new EngineBufferedImage(copy);
	}

	/**
	 * @see net.sf.jame.media.EngineImage#applyEffect(net.sf.jame.media.Effect)
	 */
	public void applyEffect(final Effect effect) {
		effect.filterImage(copy, image);
	}

	/**
	 * @see net.sf.jame.media.EngineImage#drawImage(java.awt.Graphics2D)
	 */
	public void drawImage(Graphics2D g2d) {
		g2d.drawImage(image, 0, 0, null);
	}

	/**
	 * @see net.sf.jame.media.EngineImage#getWidth()
	 */
	public int getWidth() {
		return image.getWidth();
	}

	/**
	 * @see net.sf.jame.media.EngineImage#getHeight()
	 */
	public int getHeight() {
		return image.getHeight();
	}

	/**
	 * @see net.sf.jame.media.EngineImage#update()
	 */
	public void update() {
		// int[] src = ((DataBufferInt) copy.getRaster().getDataBuffer()).getData();
		// int[] dst = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		// Lowlevel.copy(src, dst, copy.getWidth(), copy.getHeight());
	}

	@Override
	public void finalize() throws Throwable {
		if (copy != null) {
			copy.flush();
			copy = null;
		}
		if (image != null) {
			image.flush();
			image = null;
		}
		super.finalize();
	}
}
