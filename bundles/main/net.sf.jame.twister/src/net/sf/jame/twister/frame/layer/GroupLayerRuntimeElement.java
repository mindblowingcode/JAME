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
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;

/**
 * @author Andrea Medeghini
 */
public class GroupLayerRuntimeElement extends AbstractLayerRuntimeElement {
	private final ListRuntimeElement<ImageLayerRuntimeElement> layerListElement = new ListRuntimeElement<ImageLayerRuntimeElement>();
	private GroupLayerListElementListener layerListener;

	/**
	 * Constructs a new layer.
	 * 
	 * @param layerElement
	 */
	public GroupLayerRuntimeElement(final GroupLayerConfigElement layerElement) {
		super(layerElement);
		createLayers(layerElement);
		layerListener = new GroupLayerListElementListener();
		layerElement.getLayerListElement().addChangeListener(layerListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((getLayerElement() != null) && (layerListener != null)) {
			((GroupLayerConfigElement) getLayerElement()).getLayerListElement().removeChangeListener(layerListener);
		}
		layerListener = null;
		layerListElement.dispose();
		super.dispose();
	}

	private void createLayers(final GroupLayerConfigElement layerElement) {
		for (int i = 0; i < layerElement.getLayerConfigElementCount(); i++) {
			final ImageLayerConfigElement sublayerElement = layerElement.getLayerConfigElement(i);
			final ImageLayerRuntimeElement sublayer = new ImageLayerRuntimeElement(sublayerElement);
			appendLayer(sublayer);
		}
	}

	/**
	 * Returns a layer.
	 * 
	 * @param index the layer index.
	 * @return the layer.
	 */
	public ImageLayerRuntimeElement getLayer(final int index) {
		return layerListElement.getElement(index);
	}

	/**
	 * Returns the layer index.
	 * 
	 * @param layer the layer.
	 * @return the index.
	 */
	public int indexOfLayer(final ImageLayerRuntimeElement layer) {
		return layerListElement.indexOfElement(layer);
	}

	/**
	 * Returns the number of layers.
	 * 
	 * @return the number of layers.
	 */
	public int getLayerCount() {
		return layerListElement.getElementCount();
	}

	private void appendLayer(final ImageLayerRuntimeElement layer) {
		layerListElement.appendElement(layer);
	}

	private void insertLayerAfter(final int index, final ImageLayerRuntimeElement layer) {
		layerListElement.insertElementAfter(index, layer);
	}

	private void insertLayerBefore(final int index, final ImageLayerRuntimeElement layer) {
		layerListElement.insertElementBefore(index, layer);
	}

	private void removeLayer(final int index) {
		layerListElement.getElement(index).dispose();
		layerListElement.removeElement(index);
	}

	private void moveUpLayer(final int index) {
		layerListElement.moveElementUp(index);
	}

	private void moveDownLayer(final int index) {
		layerListElement.moveElementDown(index);
	}

	private void setLayer(final int index, final ImageLayerRuntimeElement layer) {
		layerListElement.setElement(index, layer);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean layerChanged = layerListElement.isChanged();
		return super.isChanged() || layerChanged;
	}

	private class GroupLayerListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ListConfigElement.ELEMENT_ADDED: {
					appendLayer(new ImageLayerRuntimeElement((ImageLayerConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_AFTER: {
					insertLayerAfter(((Integer) e.getParams()[1]).intValue(), new ImageLayerRuntimeElement((ImageLayerConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_BEFORE: {
					insertLayerBefore(((Integer) e.getParams()[1]).intValue(), new ImageLayerRuntimeElement((ImageLayerConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_REMOVED: {
					removeLayer(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_UP: {
					moveUpLayer(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_DOWN: {
					moveDownLayer(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_CHANGED: {
					setLayer(((Integer) e.getParams()[1]).intValue(), new ImageLayerRuntimeElement((ImageLayerConfigElement) e.getParams()[0]));
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
