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
package net.sf.jame.media.swing;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.geom.Point2D;

import net.sf.jame.media.Movie;
import net.sf.jame.media.swing.renderer.RenderingCanvas;

/**
 * @author Andrea Medeghini
 */
public abstract class MovieApplet extends Applet {
	private static final long serialVersionUID = 1L;
	private RenderingCanvas canvas;
	private Movie movie;
	private Color color = Color.white;
	private boolean debug = false;
	private boolean loop = false;

	@Override
	public final void init() {
		if (canvas == null) {
			final String par_color = getParameter("color");
			final String par_debug = getParameter("debug");
			final String par_loop = getParameter("loop");
			if (par_debug != null) {
				if (par_debug.toLowerCase().equals("true")) {
					debug = true;
				}
			}
			if (par_loop != null) {
				if (par_loop.toLowerCase().equals("true")) {
					loop = true;
				}
			}
			if (par_color != null) {
				try {
					color = new Color(Integer.parseInt(par_color.substring(1), 16));
				}
				catch (final NumberFormatException e) {
				}
			}
			movie = createMovie();
			setBackground(color);
			setForeground(color);
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			DefaultMovieContext movieContext = new DefaultMovieContext();
			RenderingCanvas canvas = new RenderingCanvas(movieContext, movie);
			movieContext.setColor(getBackground());
			movieContext.setDebug(debug);
			movieContext.setLoop(loop);
			movieContext.setCanvas(canvas);
			setLayout(new BorderLayout());
			add(canvas, BorderLayout.CENTER);
			canvas.requestFocus();
		}
	}

	@Override
	public final void start() {
		if (canvas == null) {
			return;
		}
		movie.setSize(canvas.getSize());
		movie.setCenter(new Point2D.Double(-canvas.getSize().getWidth() / 2, -canvas.getSize().getHeight() / 2));
		canvas.getMovieRenderer().init();
	}

	@Override
	public final void stop() {
		if (canvas == null) {
			return;
		}
		canvas.getMovieRenderer().dispose();
	}

	@Override
	public final void destroy() {
	}

	/**
	 * @see net.sf.jame.media.MovieContext#createMovie()
	 */
	public abstract Movie createMovie();
}
