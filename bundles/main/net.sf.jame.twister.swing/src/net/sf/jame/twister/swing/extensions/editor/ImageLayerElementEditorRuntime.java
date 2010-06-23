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
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.twister.frame.layer.ImageLayerConfigElement;
import net.sf.jame.twister.frame.layer.ImageLayerConfigElementNodeValue;
import net.sf.jame.twister.frame.layer.image.ImageConfigElement;
import net.sf.jame.twister.swing.extensions.TwisterSwingExtensionResources;

/**
 * @author Andrea Medeghini
 */
public class ImageLayerElementEditorRuntime extends EditorExtensionRuntime {
	/**
	 * @see net.sf.jame.core.swing.editor.ConfigurableReferenceElementListEditorRuntime#createChildValue()
	 */
	protected NodeValue<?> createChildValue() {
		return null;
	}

	/**
	 * @see net.sf.jame.core.swing.editor.extension.EditorExtensionRuntime#createEditor(net.sf.jame.core.tree.NodeEditor)
	 */
	@Override
	public NodeEditorComponent createEditor(final NodeEditor nodeEditor) {
		return new EditorComponent(nodeEditor);
	}

	private class EditorComponent extends JPanel implements NodeEditorComponent {
		private static final long serialVersionUID = 1L;

		/**
		 * @param nodeEditor
		 */
		public EditorComponent(final NodeEditor nodeEditor) {
			setLayout(new StackLayout());
			if (nodeEditor.isParentMutable()) {
				final JButton insertBeforeButton = GUIFactory.createButton(new InsertBeforeAction(nodeEditor), TwisterSwingExtensionResources.getInstance().getString("tooltip.insertImageLayerBefore"));
				final JButton insertAfterButton = GUIFactory.createButton(new InsertAfterAction(nodeEditor), TwisterSwingExtensionResources.getInstance().getString("tooltip.insertImageLayerAfter"));
				final JButton removeButton = GUIFactory.createButton(new RemoveAction(nodeEditor), TwisterSwingExtensionResources.getInstance().getString("tooltip.removeImageLayer"));
				final JButton moveUpButton = GUIFactory.createButton(new MoveUpAction(nodeEditor), TwisterSwingExtensionResources.getInstance().getString("tooltip.moveUpImageLayer"));
				final JButton moveDownButton = GUIFactory.createButton(new MoveDownAction(nodeEditor), TwisterSwingExtensionResources.getInstance().getString("tooltip.moveDownImageLayer"));
				add(insertBeforeButton);
				this.add(Box.createVerticalStrut(8));
				add(insertAfterButton);
				this.add(Box.createVerticalStrut(8));
				add(removeButton);
				this.add(Box.createVerticalStrut(8));
				add(moveUpButton);
				this.add(Box.createVerticalStrut(8));
				add(moveDownButton);
			}
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

	private class InsertAfterAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;

		/**
		 * @param nodeEditor
		 */
		public InsertAfterAction(final NodeEditor nodeEditor) {
			super(TwisterSwingExtensionResources.getInstance().getString("action.insertImageLayerAfter"));
			this.nodeEditor = nodeEditor;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent e) {
			final ImageLayerConfigElement element = new ImageLayerConfigElement();
			element.setImageConfigElement(new ImageConfigElement());
			nodeEditor.getParentNodeEditor().insertChildNodeAfter(nodeEditor.getIndex(), new ImageLayerConfigElementNodeValue(element.clone()));
		}
	}

	private class InsertBeforeAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;

		/**
		 * @param nodeEditor
		 */
		public InsertBeforeAction(final NodeEditor nodeEditor) {
			super(TwisterSwingExtensionResources.getInstance().getString("action.insertImageLayerBefore"));
			this.nodeEditor = nodeEditor;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent e) {
			final ImageLayerConfigElement element = new ImageLayerConfigElement();
			element.setImageConfigElement(new ImageConfigElement());
			nodeEditor.getParentNodeEditor().insertChildNodeBefore(nodeEditor.getIndex(), new ImageLayerConfigElementNodeValue(element.clone()));
		}
	}

	private class RemoveAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;

		/**
		 * @param nodeEditor
		 */
		public RemoveAction(final NodeEditor nodeEditor) {
			super(TwisterSwingExtensionResources.getInstance().getString("action.removeImageLayer"));
			this.nodeEditor = nodeEditor;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent e) {
			nodeEditor.getParentNodeEditor().removeChildNode(nodeEditor.getIndex());
		}
	}

	private class MoveUpAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;

		/**
		 * @param nodeEditor
		 */
		public MoveUpAction(final NodeEditor nodeEditor) {
			super(TwisterSwingExtensionResources.getInstance().getString("action.moveUpImageLayer"));
			this.nodeEditor = nodeEditor;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent e) {
			nodeEditor.getParentNodeEditor().moveUpChildNode(nodeEditor.getIndex());
		}
	}

	private class MoveDownAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;

		/**
		 * @param nodeEditor
		 */
		public MoveDownAction(final NodeEditor nodeEditor) {
			super(TwisterSwingExtensionResources.getInstance().getString("action.moveDownImageLayer"));
			this.nodeEditor = nodeEditor;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent e) {
			nodeEditor.getParentNodeEditor().moveDownChildNode(nodeEditor.getIndex());
		}
	}
}
