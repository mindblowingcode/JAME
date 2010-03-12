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
package net.sf.jame.contextfree;

import net.sf.jame.contextfree.cfdg.extension.PrimitiveExtensionRegistry;
import net.sf.jame.contextfree.cfdg.extension.PrimitiveExtensionRuntime;
import net.sf.jame.contextfree.cfdg.path.adjustment.extension.PathAdjustmentExtensionConfig;
import net.sf.jame.contextfree.cfdg.path.adjustment.extension.PathAdjustmentExtensionRegistry;
import net.sf.jame.contextfree.cfdg.path.adjustment.extension.PathAdjustmentExtensionRuntime;
import net.sf.jame.contextfree.cfdg.path.operation.extension.PathOperationExtensionConfig;
import net.sf.jame.contextfree.cfdg.path.operation.extension.PathOperationExtensionRegistry;
import net.sf.jame.contextfree.cfdg.path.operation.extension.PathOperationExtensionRuntime;
import net.sf.jame.contextfree.cfdg.shape.adjustment.extension.ShapeAdjustmentExtensionConfig;
import net.sf.jame.contextfree.cfdg.shape.adjustment.extension.ShapeAdjustmentExtensionRegistry;
import net.sf.jame.contextfree.cfdg.shape.adjustment.extension.ShapeAdjustmentExtensionRuntime;
import net.sf.jame.core.extension.ConfigurableExtension;
import net.sf.jame.core.extension.ConfigurableExtensionRegistry;
import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.extension.ExtensionRegistry;

public class ContextFreeRegistry {
	private ConfigurableExtensionRegistry<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> pathAdjustmentRegistry;
	private ConfigurableExtensionRegistry<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> shapeAdjustmentRegistry;
	private ConfigurableExtensionRegistry<PathOperationExtensionRuntime<?>, PathOperationExtensionConfig> pathOperationRegistry;
	private ExtensionRegistry<PrimitiveExtensionRuntime> primitiveRegistry;

	private static class RegistryHolder {
		private static final ContextFreeRegistry instance = new ContextFreeRegistry();
	}

	private ContextFreeRegistry() {
		setPathAdjustmentRegistry(new PathAdjustmentExtensionRegistry());
		setShapeAdjustmentRegistry(new ShapeAdjustmentExtensionRegistry());
		setPathOperationRegistry(new PathOperationExtensionRegistry());
		setPrimitiveRegistry(new PrimitiveExtensionRegistry());
	}

	/**
	 * @return
	 */
	public static ContextFreeRegistry getInstance() {
		return RegistryHolder.instance;
	}
	
	private void setPathAdjustmentRegistry(final ConfigurableExtensionRegistry<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> pathAdjustmentRegistry) {
		this.pathAdjustmentRegistry = pathAdjustmentRegistry;
	}
	private void setShapeAdjustmentRegistry(final ConfigurableExtensionRegistry<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> shapeAdjustmentRegistry) {
		this.shapeAdjustmentRegistry = shapeAdjustmentRegistry;
	}
	private void setPathOperationRegistry(final ConfigurableExtensionRegistry<PathOperationExtensionRuntime<?>, PathOperationExtensionConfig> pathOperationRegistry) {
		this.pathOperationRegistry = pathOperationRegistry;
	}
	private void setPrimitiveRegistry(final ExtensionRegistry<PrimitiveExtensionRuntime> primitiveRegistry) {
		this.primitiveRegistry = primitiveRegistry;
	}
	
	/**
	 * Returns a pathAdjustment extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> getPathAdjustmentExtension(final String extensionId) throws ExtensionNotFoundException {
		return pathAdjustmentRegistry.getConfigurableExtension(extensionId);
	}
	/**
	 * Returns a shapeAdjustment extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtension(final String extensionId) throws ExtensionNotFoundException {
		return shapeAdjustmentRegistry.getConfigurableExtension(extensionId);
	}
	/**
	 * Returns a pathOperation extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public ConfigurableExtension<PathOperationExtensionRuntime<?>, PathOperationExtensionConfig> getPathOperationExtension(final String extensionId) throws ExtensionNotFoundException {
		return pathOperationRegistry.getConfigurableExtension(extensionId);
	}
	/**
	 * Returns a primitive extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public Extension<PrimitiveExtensionRuntime> getPrimitiveExtension(final String extensionId) throws ExtensionNotFoundException {
		return primitiveRegistry.getExtension(extensionId);
	}
	
	/**
	 * @return the pathAdjustmentRegistry
	 */
	public ConfigurableExtensionRegistry<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> getPathAdjustmentRegistry() {
		return pathAdjustmentRegistry;
	}
	/**
	 * @return the shapeAdjustmentRegistry
	 */
	public ConfigurableExtensionRegistry<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> getShapeAdjustmentRegistry() {
		return shapeAdjustmentRegistry;
	}
	/**
	 * @return the pathOperationRegistry
	 */
	public ConfigurableExtensionRegistry<PathOperationExtensionRuntime<?>, PathOperationExtensionConfig> getPathOperationRegistry() {
		return pathOperationRegistry;
	}
	/**
	 * @return the primitiveRegistry
	 */
	public ExtensionRegistry<PrimitiveExtensionRuntime> getPrimitiveRegistry() {
		return primitiveRegistry;
	}
}
