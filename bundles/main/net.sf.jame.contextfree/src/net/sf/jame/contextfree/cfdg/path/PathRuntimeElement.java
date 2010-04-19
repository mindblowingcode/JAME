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
package net.sf.jame.contextfree.cfdg.path;

import net.sf.jame.contextfree.cfdg.FigureRuntimeElement;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;

/**
 * @author Andrea Medeghini
 */
public class PathRuntimeElement extends FigureRuntimeElement {
	private PathConfigElement pathElement;
	private PathOperationListElementListener pathOperationListElementListener;

	/**
	 * Constructs a new PathRuntimeElement.
	 * 
	 * @param registry
	 * @param PathRuntimeElementElement
	 */
	public PathRuntimeElement(final PathConfigElement pathElement) {
		super(pathElement);
		this.pathElement = pathElement;
		pathOperationListElementListener = new PathOperationListElementListener();
		pathElement.getPathOperationListElement().addChangeListener(pathOperationListElementListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((pathElement != null) && (pathOperationListElementListener != null)) {
			pathElement.getPathOperationListElement().removeChangeListener(pathOperationListElementListener);
		}
		pathOperationListElementListener = null;
		pathElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean pathChanged = false;
		return super.isChanged() || pathChanged;
	}

	private class PathOperationListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}

	/**
	 * @param contextFreeContext
	 */
	public void draw(ContextFreeContext contextFreeContext) {
	}
}
