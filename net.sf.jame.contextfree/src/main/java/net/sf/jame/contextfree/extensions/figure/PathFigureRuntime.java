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
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.pathReplacement.PathReplacementRuntimeElement;
import net.sf.jame.contextfree.renderer.support.CFBuilder;
import net.sf.jame.contextfree.renderer.support.CFPath;
import net.sf.jame.contextfree.renderer.support.CFRule;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.config.ListRuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class PathFigureRuntime extends FigureExtensionRuntime<PathFigureConfig> {
	private String name;
	private NameListener nameListener;
	private ListRuntimeElement<PathReplacementRuntimeElement> pathReplacementListElement;
	private PathReplacementListElementListener pathReplacementListElementListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setName(getConfig().getName());
		nameListener = new NameListener();
		getConfig().getNameElement().addChangeListener(nameListener);
		pathReplacementListElement = new ListRuntimeElement<PathReplacementRuntimeElement>();
		for (int i = 0; i < getConfig().getPathReplacementConfigElementCount(); i++) {
			pathReplacementListElement.appendElement(new PathReplacementRuntimeElement(getConfig().getPathReplacementConfigElement(i)));
		}
		pathReplacementListElementListener = new PathReplacementListElementListener();
		getConfig().getPathReplacementListElement().addChangeListener(pathReplacementListElementListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (nameListener != null)) {
			getConfig().getNameElement().removeChangeListener(nameListener);
		}
		nameListener = null;
		if ((getConfig() != null) && (pathReplacementListElementListener != null)) {
			getConfig().getPathReplacementListElement().removeChangeListener(pathReplacementListElementListener);
		}
		pathReplacementListElementListener = null;
		super.dispose();
	}
	
	/**
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	private void setName(final String name) {
		this.name = name;
	}
	
	private class NameListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setName((String) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	/**
	 * Returns a pathReplacement element.
	 * 
	 * @param index the pathReplacement index.
	 * @return the pathReplacement.
	 */
	public PathReplacementRuntimeElement getPathReplacementElement(final int index) {
		return pathReplacementListElement.getElement(index);
	}

	/**
	 * Returns a pathReplacement element index.
	 * 
	 * @param pathReplacementElement the pathReplacement element.
	 * @return the index.
	 */
	public int indexOfPathReplacementElement(final PathReplacementRuntimeElement pathReplacementElement) {
		return pathReplacementListElement.indexOfElement(pathReplacementElement);
	}

	/**
	 * Returns the number of pathReplacement elements.
	 * 
	 * @return the number of pathReplacement elements.
	 */
	public int getPathReplacementElementCount() {
		return pathReplacementListElement.getElementCount();
	}

	private void setPathReplacementElement(final int index, PathReplacementRuntimeElement element) {
		pathReplacementListElement.setElement(index, element);
	}

	private void appendPathReplacementElement(final PathReplacementRuntimeElement pathReplacementElement) {
		pathReplacementListElement.appendElement(pathReplacementElement);
	}

	private void insertPathReplacementElementAfter(final int index, final PathReplacementRuntimeElement pathReplacementElement) {
		pathReplacementListElement.insertElementAfter(index, pathReplacementElement);
	}

	private void insertPathReplacementElementBefore(final int index, final PathReplacementRuntimeElement pathReplacementElement) {
		pathReplacementListElement.insertElementBefore(index, pathReplacementElement);
	}

	private void removePathReplacementElement(final int index) {
		pathReplacementListElement.removeElement(index);
	}

	private void moveUpPathReplacementElement(final int index) {
		pathReplacementListElement.moveElementUp(index);
	}

	private void moveDownPathReplacementElement(final int index) {
		pathReplacementListElement.moveElementDown(index);
	}
	
	private class PathReplacementListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ListConfigElement.ELEMENT_ADDED: {
					appendPathReplacementElement(new PathReplacementRuntimeElement ((PathReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_AFTER: {
					insertPathReplacementElementAfter(((Integer) e.getParams()[1]).intValue(), new PathReplacementRuntimeElement ((PathReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_BEFORE: {
					insertPathReplacementElementBefore(((Integer) e.getParams()[1]).intValue(), new PathReplacementRuntimeElement ((PathReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_REMOVED: {
					removePathReplacementElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_UP: {
					moveUpPathReplacementElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_DOWN: {
					moveDownPathReplacementElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_CHANGED: {
					setPathReplacementElement(((Integer) e.getParams()[1]).intValue(), new PathReplacementRuntimeElement ((PathReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	public void process(CFBuilder builder) {
		int shapeType = builder.encodeShapeName(name);
		CFRule rule = new CFRule(shapeType, 1);
		rule.setPath(new CFPath());
		for (int i = 0; i < pathReplacementListElement.getElementCount(); i++) {
			PathReplacementRuntimeElement pathReplacementRuntime = pathReplacementListElement.getElement(i);
			pathReplacementRuntime.process(rule);
		}
		builder.addRule(rule);
	}
}
