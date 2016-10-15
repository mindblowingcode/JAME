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
package net.sf.jame.twister.layer;

import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.twister.image.ImageConfigElement;
import net.sf.jame.twister.image.ImageRuntimeElement;

/**
 * @author Andrea Medeghini
 */
public class ImageLayerRuntimeElement extends AbstractLayerRuntimeElement {
	private ImageRuntimeElement imageElement;
	private ImageElementListener imageListener;

	/**
	 * Constructs a new layer.
	 * 
	 * @param layerElement
	 */
	public ImageLayerRuntimeElement(final ImageLayerConfigElement layerElement) {
		super(layerElement);
		createImage(layerElement);
		imageListener = new ImageElementListener();
		layerElement.getImageSingleElement().addChangeListener(imageListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((getLayerElement() != null) && (imageListener != null)) {
			((ImageLayerConfigElement) getLayerElement()).getImageSingleElement().removeChangeListener(imageListener);
		}
		imageListener = null;
		if (imageElement != null) {
			imageElement.dispose();
			imageElement = null;
		}
		super.dispose();
	}

	private void createImage(final ImageLayerConfigElement layerElement) {
		final ImageConfigElement imageElement = layerElement.getImageConfigElement();
		if (imageElement != null) {
			final ImageRuntimeElement image = new ImageRuntimeElement(imageElement);
			setImage(image);
		}
	}

	/**
	 * Returns the image.
	 * 
	 * @return the image.
	 */
	public ImageRuntimeElement getImage() {
		return imageElement;
	}

	private void setImage(final ImageRuntimeElement imageElement) {
		if (this.imageElement != null) {
			this.imageElement.dispose();
		}
		this.imageElement = imageElement;
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean imageChanged = imageElement.isChanged();
		return super.isChanged() || imageChanged;
	}

	private class ImageElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setImage(new ImageRuntimeElement((ImageConfigElement) e.getParams()[0]));
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
