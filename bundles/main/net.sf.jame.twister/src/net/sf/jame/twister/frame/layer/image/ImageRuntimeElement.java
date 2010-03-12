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
package net.sf.jame.twister.frame.layer.image;

import net.sf.jame.core.common.ExtensionReferenceElement;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.twister.TwisterRegistry;
import net.sf.jame.twister.frame.layer.image.extension.ImageExtensionConfig;
import net.sf.jame.twister.frame.layer.image.extension.ImageExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class ImageRuntimeElement extends RuntimeElement {
	private ImageExtensionRuntime<?> imageRuntime;
	private ImageConfigElement imageElement;
	private ExtensionListener extensionListener;

	/**
	 * Constructs a new image.
	 * 
	 * @param imageElement
	 */
	public ImageRuntimeElement(final ImageConfigElement imageElement) {
		if (imageElement == null) {
			throw new IllegalArgumentException("imageElement is null");
		}
		this.imageElement = imageElement;
		createRuntime(imageElement.getReference());
		extensionListener = new ExtensionListener();
		imageElement.getExtensionElement().addChangeListener(extensionListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((imageElement != null) && (extensionListener != null)) {
			imageElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if (imageRuntime != null) {
			imageRuntime.dispose();
			imageRuntime = null;
		}
		imageElement = null;
		super.dispose();
	}

	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<ImageExtensionConfig> reference) {
		try {
			if (reference != null) {
				final ImageExtensionRuntime imageRuntime = TwisterRegistry.getInstance().getImageExtension(reference.getExtensionId()).createExtensionRuntime();
				final ImageExtensionConfig imageConfig = reference.getExtensionConfig();
				imageRuntime.setConfig(imageConfig);
				setImageRuntime(imageRuntime);
			}
			else {
				setImageRuntime(null);
			}
		}
		catch (final ExtensionNotFoundException e) {
			e.printStackTrace();
		}
		catch (final ExtensionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean imageChanged = (imageRuntime != null) && imageRuntime.isChanged();
		return super.isChanged() || imageChanged;
	}

	/**
	 * @return the imageRuntime
	 */
	public ImageExtensionRuntime<?> getImageRuntime() {
		return imageRuntime;
	}

	private void setImageRuntime(final ImageExtensionRuntime<?> imageRuntime) {
		if (this.imageRuntime != null) {
			this.imageRuntime.dispose();
		}
		this.imageRuntime = imageRuntime;
	}

	private class ExtensionListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		@SuppressWarnings("unchecked")
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED: {
					createRuntime((ConfigurableExtensionReference<ImageExtensionConfig>) e.getParams()[0]);
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
