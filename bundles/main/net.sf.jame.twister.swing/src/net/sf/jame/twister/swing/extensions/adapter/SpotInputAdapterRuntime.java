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
package net.sf.jame.twister.swing.extensions.adapter;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.sf.jame.core.util.DoubleVector2D;
import net.sf.jame.twister.extensions.effect.SpotConfig;
import net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime;

/**
 * @author Andrea Medeghini
 */
public class SpotInputAdapterRuntime extends DefaultInputAdapterRuntime {
	/**
	 * 
	 */
	public SpotInputAdapterRuntime() {
	}

	/**
	 * @see net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime#refresh()
	 */
	@Override
	public void refresh() {
	}

	/**
	 * @see net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime#processKeyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void processKeyPressed(final KeyEvent e) {
	}

	/**
	 * @see net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime#processKeyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void processKeyReleased(final KeyEvent e) {
	}

	/**
	 * @see net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime#processKeyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void processKeyTyped(final KeyEvent e) {
	}

	/**
	 * @see net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime#processMouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void processMouseClicked(final MouseEvent e) {
	}

	/**
	 * @see net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime#processMouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void processMouseDragged(final MouseEvent e) {
		final SpotConfig config = (SpotConfig) getAdapterContext().getConfig();
		if (config != null) {
			final double w = getRenderContext().getImageSize().getX();
			final double h = getRenderContext().getImageSize().getY();
			final DoubleVector2D center = new DoubleVector2D(e.getX() / w, e.getY() / h);
			config.getContext().updateTimestamp();
			config.setCenter(center);
		}
	}

	/**
	 * @see net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime#processMouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void processMouseEntered(final MouseEvent e) {
	}

	/**
	 * @see net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime#processMouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void processMouseExited(final MouseEvent e) {
	}

	/**
	 * @see net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime#processMouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void processMouseMoved(final MouseEvent e) {
	}

	/**
	 * @see net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime#processMousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void processMousePressed(final MouseEvent e) {
	}

	/**
	 * @see net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime#processMouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void processMouseReleased(final MouseEvent e) {
		final SpotConfig config = (SpotConfig) getAdapterContext().getConfig();
		if (config != null) {
			final double w = getRenderContext().getImageSize().getX();
			final double h = getRenderContext().getImageSize().getY();
			final DoubleVector2D center = new DoubleVector2D(e.getX() / w, e.getY() / h);
			config.getContext().updateTimestamp();
			config.setCenter(center);
		}
	}
}
