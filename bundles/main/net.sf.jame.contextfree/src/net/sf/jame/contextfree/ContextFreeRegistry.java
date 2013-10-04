/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree;

import net.sf.jame.contextfree.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.figure.extension.FigureExtensionRegistry;
import net.sf.jame.contextfree.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.pathAdjustment.extension.PathAdjustmentExtensionConfig;
import net.sf.jame.contextfree.pathAdjustment.extension.PathAdjustmentExtensionRegistry;
import net.sf.jame.contextfree.pathAdjustment.extension.PathAdjustmentExtensionRuntime;
import net.sf.jame.contextfree.pathReplacement.extension.PathReplacementExtensionConfig;
import net.sf.jame.contextfree.pathReplacement.extension.PathReplacementExtensionRegistry;
import net.sf.jame.contextfree.pathReplacement.extension.PathReplacementExtensionRuntime;
import net.sf.jame.contextfree.shapeAdjustment.extension.ShapeAdjustmentExtensionConfig;
import net.sf.jame.contextfree.shapeAdjustment.extension.ShapeAdjustmentExtensionRegistry;
import net.sf.jame.contextfree.shapeAdjustment.extension.ShapeAdjustmentExtensionRuntime;
import net.sf.jame.contextfree.shapeReplacement.extension.ShapeReplacementExtensionConfig;
import net.sf.jame.contextfree.shapeReplacement.extension.ShapeReplacementExtensionRegistry;
import net.sf.jame.contextfree.shapeReplacement.extension.ShapeReplacementExtensionRuntime;
import net.sf.jame.core.extension.ConfigurableExtension;
import net.sf.jame.core.extension.ConfigurableExtensionRegistry;
import net.sf.jame.core.extension.ExtensionNotFoundException;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeRegistry {
	private ConfigurableExtensionRegistry<FigureExtensionRuntime<?>, FigureExtensionConfig> figureRegistry;
	private ConfigurableExtensionRegistry<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> pathAdjustmentRegistry;
	private ConfigurableExtensionRegistry<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> pathReplacementRegistry;
	private ConfigurableExtensionRegistry<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> shapeAdjustmentRegistry;
	private ConfigurableExtensionRegistry<ShapeReplacementExtensionRuntime<?>, ShapeReplacementExtensionConfig> shapeReplacementRegistry;

	private static class RegistryHolder {
		private static final ContextFreeRegistry instance = new ContextFreeRegistry();
	}

	private ContextFreeRegistry() {
		setFigureRegistry(new FigureExtensionRegistry());
		setPathAdjustmentRegistry(new PathAdjustmentExtensionRegistry());
		setPathReplacementRegistry(new PathReplacementExtensionRegistry());
		setShapeAdjustmentRegistry(new ShapeAdjustmentExtensionRegistry());
		setShapeReplacementRegistry(new ShapeReplacementExtensionRegistry());
	}

	/**
	 * @return
	 */
	public static ContextFreeRegistry getInstance() {
		return RegistryHolder.instance;
	}
	
	private void setFigureRegistry(final ConfigurableExtensionRegistry<FigureExtensionRuntime<?>, FigureExtensionConfig> figureRegistry) {
		this.figureRegistry = figureRegistry;
	}
	private void setPathAdjustmentRegistry(final ConfigurableExtensionRegistry<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> pathAdjustmentRegistry) {
		this.pathAdjustmentRegistry = pathAdjustmentRegistry;
	}
	private void setPathReplacementRegistry(final ConfigurableExtensionRegistry<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> pathReplacementRegistry) {
		this.pathReplacementRegistry = pathReplacementRegistry;
	}
	private void setShapeAdjustmentRegistry(final ConfigurableExtensionRegistry<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> shapeAdjustmentRegistry) {
		this.shapeAdjustmentRegistry = shapeAdjustmentRegistry;
	}
	private void setShapeReplacementRegistry(final ConfigurableExtensionRegistry<ShapeReplacementExtensionRuntime<?>, ShapeReplacementExtensionConfig> shapeReplacementRegistry) {
		this.shapeReplacementRegistry = shapeReplacementRegistry;
	}
	
	/**
	 * Returns a figure extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public ConfigurableExtension<FigureExtensionRuntime<?>, FigureExtensionConfig> getFigureExtension(final String extensionId) throws ExtensionNotFoundException {
		return figureRegistry.getConfigurableExtension(extensionId);
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
	 * Returns a pathReplacement extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> getPathReplacementExtension(final String extensionId) throws ExtensionNotFoundException {
		return pathReplacementRegistry.getConfigurableExtension(extensionId);
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
	 * Returns a shapeReplacement extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public ConfigurableExtension<ShapeReplacementExtensionRuntime<?>, ShapeReplacementExtensionConfig> getShapeReplacementExtension(final String extensionId) throws ExtensionNotFoundException {
		return shapeReplacementRegistry.getConfigurableExtension(extensionId);
	}
	
	/**
	 * @return the figureRegistry
	 */
	public ConfigurableExtensionRegistry<FigureExtensionRuntime<?>, FigureExtensionConfig> getFigureRegistry() {
		return figureRegistry;
	}
	/**
	 * @return the pathAdjustmentRegistry
	 */
	public ConfigurableExtensionRegistry<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> getPathAdjustmentRegistry() {
		return pathAdjustmentRegistry;
	}
	/**
	 * @return the pathReplacementRegistry
	 */
	public ConfigurableExtensionRegistry<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> getPathReplacementRegistry() {
		return pathReplacementRegistry;
	}
	/**
	 * @return the shapeAdjustmentRegistry
	 */
	public ConfigurableExtensionRegistry<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> getShapeAdjustmentRegistry() {
		return shapeAdjustmentRegistry;
	}
	/**
	 * @return the shapeReplacementRegistry
	 */
	public ConfigurableExtensionRegistry<ShapeReplacementExtensionRuntime<?>, ShapeReplacementExtensionConfig> getShapeReplacementRegistry() {
		return shapeReplacementRegistry;
	}
}
