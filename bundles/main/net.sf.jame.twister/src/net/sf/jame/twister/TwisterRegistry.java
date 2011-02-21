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
package net.sf.jame.twister;

import net.sf.jame.core.extension.ConfigurableExtension;
import net.sf.jame.core.extension.ConfigurableExtensionRegistry;
import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.extension.ExtensionRegistry;
import net.sf.jame.twister.converter.extension.ConverterExtensionRegistry;
import net.sf.jame.twister.converter.extension.ConverterExtensionRuntime;
import net.sf.jame.twister.effect.extension.EffectExtensionConfig;
import net.sf.jame.twister.effect.extension.EffectExtensionRegistry;
import net.sf.jame.twister.effect.extension.EffectExtensionRuntime;
import net.sf.jame.twister.frame.filter.extension.FrameFilterExtensionConfig;
import net.sf.jame.twister.frame.filter.extension.FrameFilterExtensionRegistry;
import net.sf.jame.twister.frame.filter.extension.FrameFilterExtensionRuntime;
import net.sf.jame.twister.frame.layer.filter.extension.LayerFilterExtensionConfig;
import net.sf.jame.twister.frame.layer.filter.extension.LayerFilterExtensionRegistry;
import net.sf.jame.twister.frame.layer.filter.extension.LayerFilterExtensionRuntime;
import net.sf.jame.twister.frame.layer.image.extension.ImageExtensionConfig;
import net.sf.jame.twister.frame.layer.image.extension.ImageExtensionRegistry;
import net.sf.jame.twister.frame.layer.image.extension.ImageExtensionRuntime;

/**
 * The twister registry.
 * 
 * @author Andrea Medeghini
 */
public class TwisterRegistry {
	private ConfigurableExtensionRegistry<FrameFilterExtensionRuntime<?>, FrameFilterExtensionConfig> frameFilterRegistry;
	private ConfigurableExtensionRegistry<LayerFilterExtensionRuntime<?>, LayerFilterExtensionConfig> imageFilterRegistry;
	private ConfigurableExtensionRegistry<ImageExtensionRuntime<?>, ImageExtensionConfig> imageRegistry;
	private ConfigurableExtensionRegistry<EffectExtensionRuntime<?>, EffectExtensionConfig> effectRegistry;
	private ExtensionRegistry<ConverterExtensionRuntime> converterRegistry;

	private static class RegistryHolder {
		private static final TwisterRegistry instance = new TwisterRegistry();
	}

	private TwisterRegistry() {
		setFrameFilterRegistry(new FrameFilterExtensionRegistry());
		setLayerFilterRegistry(new LayerFilterExtensionRegistry());
		setImageRegistry(new ImageExtensionRegistry());
		setEffectRegistry(new EffectExtensionRegistry());
		setConverterRegistry(new ConverterExtensionRegistry());
	}

	/**
	 * @return
	 */
	public static TwisterRegistry getInstance() {
		return RegistryHolder.instance;
	}

	/**
	 * Returns a filter extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public ConfigurableExtension<FrameFilterExtensionRuntime<?>, FrameFilterExtensionConfig> getFrameFilterExtension(final String extensionId) throws ExtensionNotFoundException {
		return frameFilterRegistry.getConfigurableExtension(extensionId);
	}

	/**
	 * Returns an image extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public ConfigurableExtension<ImageExtensionRuntime<?>, ImageExtensionConfig> getImageExtension(final String extensionId) throws ExtensionNotFoundException {
		return imageRegistry.getConfigurableExtension(extensionId);
	}

	/**
	 * Returns an image filter extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public ConfigurableExtension<LayerFilterExtensionRuntime<?>, LayerFilterExtensionConfig> getLayerFilterExtension(final String extensionId) throws ExtensionNotFoundException {
		return imageFilterRegistry.getConfigurableExtension(extensionId);
	}

	/**
	 * Returns an effect extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public ConfigurableExtension<EffectExtensionRuntime<?>, EffectExtensionConfig> getEffectExtension(final String extensionId) throws ExtensionNotFoundException {
		return effectRegistry.getConfigurableExtension(extensionId);
	}

	/**
	 * Returns a converter extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public Extension<ConverterExtensionRuntime> getConverterExtension(final String extensionId) throws ExtensionNotFoundException {
		return converterRegistry.getExtension(extensionId);
	}

	private void setFrameFilterRegistry(final ConfigurableExtensionRegistry<FrameFilterExtensionRuntime<?>, FrameFilterExtensionConfig> frameFilterRegistry) {
		this.frameFilterRegistry = frameFilterRegistry;
	}

	private void setLayerFilterRegistry(final ConfigurableExtensionRegistry<LayerFilterExtensionRuntime<?>, LayerFilterExtensionConfig> imageFilterRegistry) {
		this.imageFilterRegistry = imageFilterRegistry;
	}

	private void setEffectRegistry(final ConfigurableExtensionRegistry<EffectExtensionRuntime<?>, EffectExtensionConfig> effectRegistry) {
		this.effectRegistry = effectRegistry;
	}

	private void setImageRegistry(final ConfigurableExtensionRegistry<ImageExtensionRuntime<?>, ImageExtensionConfig> imageRegistry) {
		this.imageRegistry = imageRegistry;
	}

	private void setConverterRegistry(final ExtensionRegistry<ConverterExtensionRuntime> converterRegistry) {
		this.converterRegistry = converterRegistry;
	}

	/**
	 * @return the frameFilterRegistry
	 */
	public ConfigurableExtensionRegistry<FrameFilterExtensionRuntime<?>, FrameFilterExtensionConfig> getFrameFilterRegistry() {
		return frameFilterRegistry;
	}

	/**
	 * @return the imageFilterRegistry
	 */
	public ConfigurableExtensionRegistry<LayerFilterExtensionRuntime<?>, LayerFilterExtensionConfig> getLayerFilterRegistry() {
		return imageFilterRegistry;
	}

	/**
	 * @return the effectRegistry
	 */
	public ConfigurableExtensionRegistry<EffectExtensionRuntime<?>, EffectExtensionConfig> getEffectRegistry() {
		return effectRegistry;
	}

	/**
	 * @return the imageRegistry
	 */
	public ConfigurableExtensionRegistry<ImageExtensionRuntime<?>, ImageExtensionConfig> getImageRegistry() {
		return imageRegistry;
	}

	/**
	 * @return the converterRegistry
	 */
	public ExtensionRegistry<ConverterExtensionRuntime> getConverterRegistry() {
		return converterRegistry;
	}
}
