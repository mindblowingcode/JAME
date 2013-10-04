/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.swing.extensions.editor;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.shapeReplacement.ShapeReplacementConfigElementNodeValue;
import net.sf.jame.contextfree.swing.extensions.ContextFreeSwingExtensionResources;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime;
import net.sf.jame.core.swing.extension.ConfigurableExtensionComboBoxModel;
import net.sf.jame.core.tree.NodeValue;
/**
 * @author Andrea Medeghini
 */
public class ShapeReplacementReferenceElementEditorRuntime extends ConfigurableReferenceElementEditorRuntime {
	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#createModel()
	 */
	@Override
	protected ConfigurableExtensionComboBoxModel createModel() {
		return new ConfigurableExtensionComboBoxModel(ContextFreeRegistry.getInstance().getShapeReplacementRegistry(), true);
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected NodeValue createNodeValue(final ConfigurableExtensionReference reference) {
		final ShapeReplacementConfigElement configElement = new ShapeReplacementConfigElement();
		configElement.setExtensionReference(reference);
		return new ShapeReplacementConfigElementNodeValue(configElement);
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#createChildValue()
	 */
	@Override
	protected NodeValue<?> createChildValue() {
		return null;
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#getInsertBeforeLabel()
	 */
	@Override
	protected String getInsertBeforeLabel() {
		return ContextFreeSwingExtensionResources.getInstance().getString("action.insertShapeReplacementBefore");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#getInsertAfterLabel()
	 */
	@Override
	protected String getInsertAfterLabel() {
		return ContextFreeSwingExtensionResources.getInstance().getString("action.insertShapeReplacementAfter");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#getRemoveLabel()
	 */
	@Override
	protected String getRemoveLabel() {
		return ContextFreeSwingExtensionResources.getInstance().getString("action.removeShapeReplacement");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#getInsertAfterTooltip()
	 */
	@Override
	protected String getInsertAfterTooltip() {
		return ContextFreeSwingExtensionResources.getInstance().getString("tooltip.insertShapeReplacementAfter");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#getInsertBeforeTooltip()
	 */
	@Override
	protected String getInsertBeforeTooltip() {
		return ContextFreeSwingExtensionResources.getInstance().getString("tooltip.insertShapeReplacementBefore");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#getRemoveTooltip()
	 */
	@Override
	protected String getRemoveTooltip() {
		return ContextFreeSwingExtensionResources.getInstance().getString("tooltip.removeShapeReplacement");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#getMoveDownLabel()
	 */
	@Override
	protected String getMoveDownLabel() {
		return ContextFreeSwingExtensionResources.getInstance().getString("action.moveUpShapeReplacement");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#getMoveDownTooltip()
	 */
	@Override
	protected String getMoveDownTooltip() {
		return ContextFreeSwingExtensionResources.getInstance().getString("tooltip.moveDownShapeReplacement");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#getMoveUpLabel()
	 */
	@Override
	protected String getMoveUpLabel() {
		return ContextFreeSwingExtensionResources.getInstance().getString("action.moveUpShapeReplacement");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementEditorRuntime#getMoveUpTooltip()
	 */
	@Override
	protected String getMoveUpTooltip() {
		return ContextFreeSwingExtensionResources.getInstance().getString("tooltip.moveUpShapeReplacement");
	}
} 
