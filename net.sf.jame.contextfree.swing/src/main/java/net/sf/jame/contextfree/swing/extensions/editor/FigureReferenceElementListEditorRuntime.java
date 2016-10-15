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
package net.sf.jame.contextfree.swing.extensions.editor;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.figure.FigureConfigElement;
import net.sf.jame.contextfree.figure.FigureConfigElementNodeValue;
import net.sf.jame.contextfree.swing.extensions.ContextFreeSwingExtensionResources;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime;
import net.sf.jame.core.swing.extension.ConfigurableExtensionComboBoxModel;
import net.sf.jame.core.tree.NodeValue;
/**
 * @author Andrea Medeghini
 */
public class FigureReferenceElementListEditorRuntime extends ConfigurableReferenceElementListEditorRuntime {
	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#createModel()
	 */
	@Override
	protected ConfigurableExtensionComboBoxModel createModel() {
		return new ConfigurableExtensionComboBoxModel(ContextFreeRegistry.getInstance().getFigureRegistry(), true);
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected NodeValue createNodeValue(final ConfigurableExtensionReference reference) {
		final FigureConfigElement configElement = new FigureConfigElement();
		configElement.setExtensionReference(reference);
		return new FigureConfigElementNodeValue(configElement);
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#getAppendLabel()
	 */
	@Override
	protected String getAppendLabel() {
		return ContextFreeSwingExtensionResources.getInstance().getString("action.appendFigure");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#getRemoveAllLabel()
	 */
	@Override
	protected String getRemoveAllLabel() {
		return ContextFreeSwingExtensionResources.getInstance().getString("action.removeAllFigures");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#getAppendTooltip()
	 */
	@Override
	protected String getAppendTooltip() {
		return ContextFreeSwingExtensionResources.getInstance().getString("tooltip.appendFigure");
	}

	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#getRemoveAllTooltip()
	 */
	@Override
	protected String getRemoveAllTooltip() {
		return ContextFreeSwingExtensionResources.getInstance().getString("tooltip.removeAllFigures");
	}
} 
