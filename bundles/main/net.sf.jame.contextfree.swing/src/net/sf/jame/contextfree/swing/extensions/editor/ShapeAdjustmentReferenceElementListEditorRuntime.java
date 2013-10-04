/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.swing.extensions.editor;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.shapeAdjustment.ShapeAdjustmentConfigElementNodeValue;
import net.sf.jame.contextfree.swing.extensions.ContextFreeSwingExtensionResources;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime;
import net.sf.jame.core.swing.extension.ConfigurableExtensionComboBoxModel;
import net.sf.jame.core.tree.NodeValue;
/**
 * @author Andrea Medeghini
 */
public class ShapeAdjustmentReferenceElementListEditorRuntime extends ConfigurableReferenceElementListEditorRuntime {
	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#createModel()
	 */
	@Override
	protected ConfigurableExtensionComboBoxModel createModel() {
		return new ConfigurableExtensionComboBoxModel(ContextFreeRegistry.getInstance().getShapeAdjustmentRegistry(), true);
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected NodeValue createNodeValue(final ConfigurableExtensionReference reference) {
		final ShapeAdjustmentConfigElement configElement = new ShapeAdjustmentConfigElement();
		configElement.setExtensionReference(reference);
		return new ShapeAdjustmentConfigElementNodeValue(configElement);
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#getAppendLabel()
	 */
	@Override
	protected String getAppendLabel() {
		return ContextFreeSwingExtensionResources.getInstance().getString("action.appendShapeAdjustment");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#getRemoveAllLabel()
	 */
	@Override
	protected String getRemoveAllLabel() {
		return ContextFreeSwingExtensionResources.getInstance().getString("action.removeAllShapeAdjustments");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#getAppendTooltip()
	 */
	@Override
	protected String getAppendTooltip() {
		return ContextFreeSwingExtensionResources.getInstance().getString("tooltip.appendShapeAdjustment");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#getRemoveAllTooltip()
	 */
	@Override
	protected String getRemoveAllTooltip() {
		return ContextFreeSwingExtensionResources.getInstance().getString("tooltip.removeAllShapeAdjustments");
	}
} 
