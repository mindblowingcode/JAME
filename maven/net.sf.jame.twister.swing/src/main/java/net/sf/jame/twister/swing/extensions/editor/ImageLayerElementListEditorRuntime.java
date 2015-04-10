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
package net.sf.jame.twister.swing.extensions.editor;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.jame.core.swing.NodeEditorComponent;
import net.sf.jame.core.swing.editor.extension.EditorExtensionRuntime;
import net.sf.jame.core.swing.util.GUIFactory;
import net.sf.jame.core.swing.util.StackLayout;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.twister.image.ImageConfigElement;
import net.sf.jame.twister.layer.ImageLayerConfigElement;
import net.sf.jame.twister.layer.ImageLayerConfigElementNodeValue;
import net.sf.jame.twister.swing.extensions.TwisterSwingExtensionResources;

/**
 * @author Andrea Medeghini
 */
public class ImageLayerElementListEditorRuntime extends EditorExtensionRuntime {
	/**
	 * 
	 */
	public ImageLayerElementListEditorRuntime() {
	}

	/**
	 * @see net.sf.jame.core.swing.editor.extension.EditorExtensionRuntime#createEditor(net.sf.jame.core.tree.NodeEditor)
	 */
	@Override
	public NodeEditorComponent createEditor(final NodeEditor nodeEditor) {
		return new EditorComponent(nodeEditor);
	}

	private class AppendAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;

		/**
		 * @param nodeEditor
		 */
		public AppendAction(final NodeEditor nodeEditor) {
			super(TwisterSwingExtensionResources.getInstance().getString("action.appendImageLayer"));
			this.nodeEditor = nodeEditor;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent e) {
			final ImageLayerConfigElement element = new ImageLayerConfigElement();
			element.setImageConfigElement(new ImageConfigElement());
			nodeEditor.appendChildNode(new ImageLayerConfigElementNodeValue(element.clone()));
		}
	}

	private class RemoveAllAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;

		/**
		 * @param nodeEditor
		 */
		public RemoveAllAction(final NodeEditor nodeEditor) {
			super(TwisterSwingExtensionResources.getInstance().getString("action.removeAllImageLayers"));
			this.nodeEditor = nodeEditor;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent e) {
			nodeEditor.removeAllChildNodes();
		}
	}

	protected class EditorComponent extends JPanel implements NodeEditorComponent {
		private static final long serialVersionUID = 1L;

		/**
		 * @param nodeEditor
		 */
		public EditorComponent(final NodeEditor nodeEditor) {
			setLayout(new StackLayout());
			final JButton appendButton = GUIFactory.createButton(new AppendAction(nodeEditor), TwisterSwingExtensionResources.getInstance().getString("tooltip.appendImageLayer"));
			final JButton removeButton = GUIFactory.createButton(new RemoveAllAction(nodeEditor), TwisterSwingExtensionResources.getInstance().getString("tooltip.removeAllImageLayers"));
			this.add(appendButton);
			this.add(Box.createVerticalStrut(8));
			this.add(removeButton);
		}

		/**
		 * @see net.sf.jame.core.swing.NodeEditorComponent#getComponent()
		 */
		public JComponent getComponent() {
			return this;
		}

		/**
		 * @see net.sf.jame.core.swing.NodeEditorComponent#reloadValue()
		 */
		public void reloadValue() {
		}

		/**
		 * @see net.sf.jame.core.swing.NodeEditorComponent#dispose()
		 */
		public void dispose() {
		}
	}
}
