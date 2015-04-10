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
package net.sf.jame.media.swing;

import java.awt.Color;

import net.sf.jame.media.MovieContext;
import net.sf.jame.media.swing.renderer.RenderingCanvas;

/**
 * @author Andrea Medeghini
 */
public class DefaultMovieContext implements MovieContext {
	private boolean stopped = false;
	private boolean debug = false;
	private boolean loop = false;
	private int frameRate = 25;
	private Color color = Color.BLACK;
	private RenderingCanvas canvas;

	/**
	 * @return the canvas
	 */
	public RenderingCanvas getCanvas() {
		return canvas;
	}

	/**
	 * @param canvas the canvas to set
	 */
	public void setCanvas(RenderingCanvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * @param debug the debug to set
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	/**
	 * @return the loop
	 */
	public boolean isLoop() {
		return loop;
	}

	/**
	 * @param loop the loop to set
	 */
	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public boolean debug() {
		return debug;
	}

	public boolean loop() {
		return loop;
	}

	public void println(final String s) {
		System.out.println(s);
	}

	public void print(final String s) {
		System.out.print(s);
	}

	public void exit(final int code) {
		System.exit(0);
	}

	public void draw() {
		canvas.repaint();
	}

	public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	public int getFrameRate() {
		return frameRate;
	}

	public void setFrameRate(int frameRate) {
		this.frameRate = frameRate;
	}
}
