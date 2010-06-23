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
package net.sf.jame.twister.frame.layer;

import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.config.ListRuntimeElement;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.twister.frame.layer.filter.LayerFilterConfigElement;
import net.sf.jame.twister.frame.layer.filter.LayerFilterRuntimeElement;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractLayerRuntimeElement extends RuntimeElement implements LayerRuntimeElement {
	private final ListRuntimeElement<LayerFilterRuntimeElement> filterListElement = new ListRuntimeElement<LayerFilterRuntimeElement>();
	private FilterListElementListener layerListener;
	private LockedElementListener lockedListener;
	private VisibleElementListener visibleListener;
	private OpacityElementListener opacityListener;
	private LayerConfigElement layerElement;
	private boolean locked = false;
	private boolean visible = true;
	private float opacity = 1.0f;

	/**
	 * Constructs a new layer.
	 * 
	 * @param layerElement
	 */
	public AbstractLayerRuntimeElement(final LayerConfigElement layerElement) {
		if (layerElement == null) {
			throw new IllegalArgumentException("layerElement is null");
		}
		this.layerElement = layerElement;
		setLocked(layerElement.isLocked());
		lockedListener = new LockedElementListener();
		layerElement.getLockedElement().addChangeListener(lockedListener);
		setOpacity(layerElement.getOpacity().intValue() / 100f);
		opacityListener = new OpacityElementListener();
		layerElement.getOpacityElement().addChangeListener(opacityListener);
		setVisible(layerElement.isVisible());
		visibleListener = new VisibleElementListener();
		layerElement.getVisibleElement().addChangeListener(visibleListener);
		createFilters(layerElement);
		layerListener = new FilterListElementListener();
		layerElement.getFilterListElement().addChangeListener(layerListener);
	}

	/**
	 * @return the layerElement
	 */
	protected LayerConfigElement getLayerElement() {
		return layerElement;
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((layerElement != null) && (visibleListener != null)) {
			layerElement.getVisibleElement().removeChangeListener(visibleListener);
		}
		visibleListener = null;
		if ((layerElement != null) && (opacityListener != null)) {
			layerElement.getOpacityElement().removeChangeListener(opacityListener);
		}
		opacityListener = null;
		if ((layerElement != null) && (lockedListener != null)) {
			layerElement.getLockedElement().removeChangeListener(lockedListener);
		}
		lockedListener = null;
		if ((layerElement != null) && (layerListener != null)) {
			layerElement.getFilterListElement().removeChangeListener(layerListener);
		}
		layerListener = null;
		layerElement = null;
		filterListElement.dispose();
		super.dispose();
	}

	protected void createFilters(final LayerConfigElement layerElement) {
		for (int i = 0; i < layerElement.getFilterConfigElementCount(); i++) {
			final LayerFilterConfigElement filterElement = layerElement.getFilterConfigElement(i);
			final LayerFilterRuntimeElement filter = new LayerFilterRuntimeElement(filterElement);
			appendFilter(filter);
		}
	}

	/**
	 * Returns the layer opacity.
	 * 
	 * @return the layer opacity.
	 */
	public float getOpacity() {
		return opacity;
	}

	private void setOpacity(final float opacity) {
		this.opacity = opacity;
	}

	/**
	 * Returns true if the layer is locked.
	 * 
	 * @return true if the layer is locked.
	 */
	public boolean isLocked() {
		return locked;
	}

	private void setLocked(final boolean locked) {
		this.locked = locked;
	}

	/**
	 * Returns true if the layer is visible.
	 * 
	 * @return true if the layer is visible.
	 */
	public boolean isVisible() {
		return visible;
	}

	private void setVisible(final boolean visible) {
		this.visible = visible;
	}

	/**
	 * Returns a filter.
	 * 
	 * @param index the filter index.
	 * @return the filter.
	 */
	public LayerFilterRuntimeElement getFilter(final int index) {
		return filterListElement.getElement(index);
	}

	/**
	 * Returns the filter index.
	 * 
	 * @param filter the filter.
	 * @return the index.
	 */
	public int indexOfFilter(final LayerFilterRuntimeElement filter) {
		return filterListElement.indexOfElement(filter);
	}

	/**
	 * Returns the number of filters.
	 * 
	 * @return the number of filters.
	 */
	public int getFilterCount() {
		return filterListElement.getElementCount();
	}

	protected void appendFilter(final LayerFilterRuntimeElement filter) {
		filterListElement.appendElement(filter);
	}

	protected void insertFilterAfter(final int index, final LayerFilterRuntimeElement filter) {
		filterListElement.insertElementAfter(index, filter);
	}

	protected void insertFilterBefore(final int index, final LayerFilterRuntimeElement filter) {
		filterListElement.insertElementBefore(index, filter);
	}

	protected void removeFilter(final int index) {
		filterListElement.getElement(index).dispose();
		filterListElement.removeElement(index);
	}

	private void moveUpFilter(final int index) {
		filterListElement.moveElementUp(index);
	}

	private void moveDownFilter(final int index) {
		filterListElement.moveElementDown(index);
	}

	private void setFilter(final int index, final LayerFilterRuntimeElement filter) {
		filterListElement.setElement(index, filter);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean filterChanged = filterListElement.isChanged();
		return super.isChanged() || filterChanged;
	}

	protected class FilterListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ListConfigElement.ELEMENT_ADDED: {
					appendFilter(new LayerFilterRuntimeElement((LayerFilterConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_AFTER: {
					insertFilterAfter(((Integer) e.getParams()[1]).intValue(), new LayerFilterRuntimeElement((LayerFilterConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_BEFORE: {
					insertFilterBefore(((Integer) e.getParams()[1]).intValue(), new LayerFilterRuntimeElement((LayerFilterConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_REMOVED: {
					removeFilter(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_UP: {
					moveUpFilter(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_DOWN: {
					moveDownFilter(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_CHANGED: {
					setFilter(((Integer) e.getParams()[1]).intValue(), new LayerFilterRuntimeElement((LayerFilterConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	private class LockedElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setLocked((Boolean) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	private class VisibleElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setVisible((Boolean) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	private class OpacityElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setOpacity(((Integer) e.getParams()[0]) / 100f);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
}
