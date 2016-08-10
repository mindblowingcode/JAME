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
package net.sf.jame.twister.swing.inputAdapter.extension;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.extension.ExtensionRuntime;
import net.sf.jame.core.util.RenderContext;
import net.sf.jame.twister.util.AdapterContext;

/**
 * @author Andrea Medeghini
 */
public abstract class InputAdapterExtensionRuntime extends ExtensionRuntime {
	/**
	 * @return the renderContext
	 */
	public abstract RenderContext getRenderContext();

	/**
	 * @return the adapterContext
	 */
	public abstract AdapterContext getAdapterContext();

	/**
	 * @param handler
	 * @param e
	 */
	public abstract void processMousePressed(MouseEvent e);

	/**
	 * @param handler
	 * @param e
	 */
	public abstract void processMouseReleased(MouseEvent e);

	/**
	 * @param handler
	 * @param e
	 */
	public abstract void processMouseClicked(MouseEvent e);

	/**
	 * @param handler
	 * @param e
	 */
	public abstract void processMouseEntered(MouseEvent e);

	/**
	 * @param handler
	 * @param e
	 */
	public abstract void processMouseExited(MouseEvent e);

	/**
	 * @param handler
	 * @param e
	 */
	public abstract void processMouseMoved(MouseEvent e);

	/**
	 * @param handler
	 * @param e
	 */
	public abstract void processMouseDragged(MouseEvent e);

	/**
	 * @param handler
	 * @param e
	 */
	public abstract void processKeyPressed(KeyEvent e);

	/**
	 * @param handler
	 * @param e
	 */
	public abstract void processKeyReleased(KeyEvent e);

	/**
	 * @param handler
	 * @param e
	 */
	public abstract void processKeyTyped(KeyEvent e);

	/**
	 * 
	 */
	public abstract void refresh();

	/**
	 * @see net.sf.jame.core.extension.ExtensionRuntime#dispose()
	 */
	@Override
	public void dispose() {
	}

	/**
	 * @param renderContext
	 * @param config
	 */
	public void init(final RenderContext renderContext, final ExtensionConfig config) {
	}
}
