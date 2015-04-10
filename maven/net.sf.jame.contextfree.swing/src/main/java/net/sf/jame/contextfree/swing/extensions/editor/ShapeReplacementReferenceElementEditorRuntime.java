/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
