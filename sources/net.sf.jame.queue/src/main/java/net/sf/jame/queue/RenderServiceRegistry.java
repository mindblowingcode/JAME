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
package net.sf.jame.queue;

import net.sf.jame.core.extension.ConfigurableExtension;
import net.sf.jame.core.extension.ConfigurableExtensionRegistry;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.queue.encoder.extension.EncoderExtensionConfig;
import net.sf.jame.queue.encoder.extension.EncoderExtensionRegistry;
import net.sf.jame.queue.encoder.extension.EncoderExtensionRuntime;
import net.sf.jame.queue.spool.extension.SpoolExtensionConfig;
import net.sf.jame.queue.spool.extension.SpoolExtensionRegistry;
import net.sf.jame.queue.spool.extension.SpoolExtensionRuntime;

/**
 * The service registry.
 * 
 * @author Andrea Medeghini
 */
public class RenderServiceRegistry {
	private ConfigurableExtensionRegistry<EncoderExtensionRuntime<?>, EncoderExtensionConfig> encoderRegistry;
	private ConfigurableExtensionRegistry<SpoolExtensionRuntime<?>, SpoolExtensionConfig> spoolRegistry;

	private static class RegistryHolder {
		private static final RenderServiceRegistry instance = new RenderServiceRegistry();
	}

	private RenderServiceRegistry() {
		setEncoderRegistry(new EncoderExtensionRegistry());
		setSpoolRegistry(new SpoolExtensionRegistry());
	}

	/**
	 * @return
	 */
	public static RenderServiceRegistry getInstance() {
		return RegistryHolder.instance;
	}

	/**
	 * Returns an encoder extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public ConfigurableExtension<EncoderExtensionRuntime<?>, EncoderExtensionConfig> getEncoderExtension(final String extensionId) throws ExtensionNotFoundException {
		return encoderRegistry.getConfigurableExtension(extensionId);
	}

	/**
	 * Returns a spool extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public ConfigurableExtension<SpoolExtensionRuntime<?>, SpoolExtensionConfig> getSpoolExtension(final String extensionId) throws ExtensionNotFoundException {
		return spoolRegistry.getConfigurableExtension(extensionId);
	}

	private void setEncoderRegistry(final ConfigurableExtensionRegistry<EncoderExtensionRuntime<?>, EncoderExtensionConfig> encoderRegistry) {
		this.encoderRegistry = encoderRegistry;
	}

	private void setSpoolRegistry(final ConfigurableExtensionRegistry<SpoolExtensionRuntime<?>, SpoolExtensionConfig> spoolRegistry) {
		this.spoolRegistry = spoolRegistry;
	}

	/**
	 * @return the encoderRegistry
	 */
	public ConfigurableExtensionRegistry<EncoderExtensionRuntime<?>, EncoderExtensionConfig> getEncoderRegistry() {
		return encoderRegistry;
	}

	/**
	 * @return the encoderRegistry
	 */
	public ConfigurableExtensionRegistry<SpoolExtensionRuntime<?>, SpoolExtensionConfig> getSpoolRegistry() {
		return spoolRegistry;
	}
}
