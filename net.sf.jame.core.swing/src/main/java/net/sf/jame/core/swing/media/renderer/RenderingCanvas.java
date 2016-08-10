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
package net.sf.jame.core.swing.media.renderer;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.Semaphore;

import net.sf.jame.core.media.EngineMouseEvent;
import net.sf.jame.core.swing.media.animator.MovieAnimatorContext;

/**
 * @author Andrea Medeghini
 */
public class RenderingCanvas extends Canvas implements MovieAnimatorContext {
	private static final long serialVersionUID = 1L;
	private final EngineKeyAdapter keyadapter = new EngineKeyAdapter();
	private final EngineMouseAdapter mouseadapter = new EngineMouseAdapter();
	private final EngineMouseMotionAdapter mousemotionadapter = new EngineMouseMotionAdapter();
	private BufferedImage[] bufferedImage = new BufferedImage[2];
	private Semaphore swapSemaphore = new Semaphore(1);
	private final MovieRenderer renderer;

	/**
	 * @param renderer
	 */
	public RenderingCanvas(final MovieRenderer renderer) {
		this.renderer = renderer;
		addKeyListener(keyadapter);
		addMouseListener(mouseadapter);
		addMouseMotionListener(mousemotionadapter);
	}

	/**
	 * @return
	 */
	public MovieRenderer getRenderer() {
		return renderer;
	}

	/**
	 * @see java.awt.Canvas#update(java.awt.Graphics)
	 */
	@Override
	public void update(final Graphics g) {
		paint(g);
	}

	/**
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		if ((getWidth() > 0) && (getHeight() > 0)) {
			try {
				swapSemaphore.acquire();
				if (bufferedImage[0] == null) {
					bufferedImage[0] = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
				}
				g.drawImage(bufferedImage[0], 0, 0, this);
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			finally {
				swapSemaphore.release();
			}
		}
	}

	private Graphics2D getOffscreenGraphics() {
		if ((getWidth() > 0) && (getHeight() > 0)) {
			try {
				swapSemaphore.acquire();
				if (bufferedImage[1] == null) {
					bufferedImage[1] = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
				}
				return bufferedImage[1].createGraphics();
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			finally {
				swapSemaphore.release();
			}
		}
		return null;
	}

	private void swap() {
		try {
			swapSemaphore.acquire();
			BufferedImage tmpBufferedImage = bufferedImage[0];
			bufferedImage[0] = bufferedImage[1];
			bufferedImage[1] = tmpBufferedImage;
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		finally {
			swapSemaphore.release();
		}
	}

	/**
	 * @see net.sf.jame.core.swing.media.animator.MovieAnimatorContext#draw()
	 */
	public void draw() {
		final Graphics2D g2d = getOffscreenGraphics();
		if (g2d != null) {
			renderer.draw(g2d);
			g2d.dispose();
			swap();
		}
		repaint();
		renderer.nextFrame();
	}

	private class EngineKeyAdapter extends KeyAdapter {
		public EngineKeyAdapter() {
		}

		@Override
		public void keyPressed(final KeyEvent e) {
		}
	}

	private class EngineMouseAdapter extends MouseAdapter {
		public EngineMouseAdapter() {
		}

		@Override
		public void mousePressed(final MouseEvent e) {
			renderer.enqueueEvent(new EngineMouseEvent(EngineMouseEvent.PRESSED, new Point2D.Float(e.getX(), e.getY())));
		}

		@Override
		public void mouseReleased(final MouseEvent e) {
			renderer.enqueueEvent(new EngineMouseEvent(EngineMouseEvent.RELEASED, new Point2D.Float(e.getX(), e.getY())));
		}
	}

	private class EngineMouseMotionAdapter extends MouseMotionAdapter {
		public EngineMouseMotionAdapter() {
		}

		@Override
		public void mouseMoved(final MouseEvent e) {
			renderer.enqueueEvent(new EngineMouseEvent(EngineMouseEvent.MOVED, new Point2D.Float(e.getX(), e.getY())));
		}

		@Override
		public void mouseDragged(final MouseEvent e) {
			renderer.enqueueEvent(new EngineMouseEvent(EngineMouseEvent.DRAGGED, new Point2D.Float(e.getX(), e.getY())));
		}
	}
}
