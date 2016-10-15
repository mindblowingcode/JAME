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
package net.sf.jame.twister;

import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.twister.effect.EffectConfigElement;
import net.sf.jame.twister.frame.FrameConfigElement;
import net.sf.jame.twister.image.ImageConfigElement;
import net.sf.jame.twister.layer.GroupLayerConfigElement;
import net.sf.jame.twister.layer.ImageLayerConfigElement;

/**
 * @author Andrea Medeghini
 */
public class TwisterConfigBuilder {
	private static final String DEFAULT_IMAGE_EXTENSION_ID = "twister.frame.layer.image.mandelbrot";

	/**
	 * Constructs a new builder.
	 */
	public TwisterConfigBuilder() {
	}

	/**
	 * Returns the default config.
	 * 
	 * @return the default config.
	 * @throws ExtensionException
	 * @throws ExtensionNotFoundException
	 */
	public TwisterConfig createDefaultConfig() throws ExtensionNotFoundException, ExtensionException {
		final TwisterConfig config = new TwisterConfig();
		final FrameConfigElement frameElement = new FrameConfigElement();
		final GroupLayerConfigElement groupLayerElement = new GroupLayerConfigElement();
		final ImageLayerConfigElement imageLayerElement = new ImageLayerConfigElement();
		final ImageConfigElement imageElement = new ImageConfigElement();
		final EffectConfigElement effectElement = new EffectConfigElement();
		config.setFrameConfigElement(frameElement);
		frameElement.appendLayerConfigElement(groupLayerElement);
		groupLayerElement.appendLayerConfigElement(imageLayerElement);
		imageLayerElement.setImageConfigElement(imageElement);
		imageElement.setReference(TwisterRegistry.getInstance().getImageExtension(TwisterConfigBuilder.DEFAULT_IMAGE_EXTENSION_ID).createConfigurableExtensionReference());
		config.setEffectConfigElement(effectElement);
		return config;
	}
}
