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
package net.sf.jame.core.swing.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.sf.jame.core.common.ExtensionReferenceElementNodeValue;
import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionReference;
import net.sf.jame.core.extension.NullConfigurableExtension;
import net.sf.jame.core.extension.NullExtension;
import net.sf.jame.core.swing.CoreSwingResources;
import net.sf.jame.core.swing.NodeEditorComponent;
import net.sf.jame.core.swing.editor.extension.EditorExtensionRuntime;
import net.sf.jame.core.swing.extension.ExtensionComboBoxModel;
import net.sf.jame.core.swing.extension.ExtensionListCellRenderer;
import net.sf.jame.core.swing.util.GUIFactory;
import net.sf.jame.core.swing.util.StackLayout;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;

/**
 * @author Andrea Medeghini
 */
public abstract class ReferenceEditorRuntime extends EditorExtensionRuntime {
	/**
	 * @see net.sf.jame.twister.swing.editor.extension.EditorExtensionRuntime#createEditor(net.sf.jame.core.tree.NodeEditor)
	 */
	@Override
	public NodeEditorComponent createEditor(final NodeEditor nodeEditor) {
		return new EditorComponent(nodeEditor);
	}

	/**
	 * @return the
	 */
	protected abstract NodeValue<?> createChildValue();

	/**
	 * @param reference
	 * @return the node value.
	 */
	protected abstract NodeValue<?> createNodeValue(ExtensionReference reference);

	/**
	 * @return the model.
	 */
	protected abstract ExtensionComboBoxModel createModel();

	private class EditorComponent extends JPanel implements NodeEditorComponent {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;
		private final JComboBox combo = GUIFactory.createComboBox(createModel(), CoreSwingResources.getInstance().getString("tooltip.extension"));
		private final JButton clearButton = GUIFactory.createButton(new ClearAction(), CoreSwingResources.getInstance().getString("tooltip.clearReference"));
		private final ReferenceSelectionListener referenceSelectionListener;

		/**
		 * @param nodeEditor
		 */
		@SuppressWarnings("unchecked")
		public EditorComponent(final NodeEditor nodeEditor) {
			setLayout(new StackLayout());
			this.nodeEditor = nodeEditor;
			if (nodeEditor.getNodeValue() != null) {
				final ExtensionReference value = ((ExtensionReferenceElementNodeValue<ExtensionReference>) nodeEditor.getNodeValue()).getValue();
				if (value != null) {
					((ExtensionComboBoxModel) combo.getModel()).setSelectedItemByExtensionId(value.getExtensionId());
				}
			}
			combo.setRenderer(new ExtensionListCellRenderer());
			referenceSelectionListener = new ReferenceSelectionListener(nodeEditor);
			combo.addActionListener(referenceSelectionListener);
			this.add(GUIFactory.createLabel(CoreSwingResources.getInstance().getString("label.extension"), SwingConstants.CENTER));
			this.add(Box.createVerticalStrut(8));
			this.add(combo);
			this.add(Box.createVerticalStrut(8));
			this.add(clearButton);
			updateButtons();
		}

		private void updateButtons() {
			clearButton.setEnabled(!isNullExtension());
		}

		private boolean isNullExtension() {
			return combo.getSelectedItem() instanceof NullConfigurableExtension;
		}

		private class ReferenceSelectionListener implements ActionListener {
			private final NodeEditor nodeEditor;

			/**
			 * @param nodeEditor
			 */
			public ReferenceSelectionListener(final NodeEditor nodeEditor) {
				this.nodeEditor = nodeEditor;
			}

			/**
			 * @param e
			 */
			public void actionPerformed(final ActionEvent e) {
				final Extension<?> extension = (Extension<?>) ((JComboBox) e.getSource()).getSelectedItem();
				if (extension instanceof NullExtension) {
					if (nodeEditor.getNodeValue().getValue() != null) {
						nodeEditor.setNodeValue(createNodeValue(null));
					}
				}
				else {
					final ExtensionReference reference = extension.getExtensionReference();
					if (!extension.equals(nodeEditor.getNodeValue().getValue())) {
						nodeEditor.setNodeValue(createNodeValue(reference));
					}
				}
				updateButtons();
			}
		}

		private class ClearAction extends AbstractAction {
			private static final long serialVersionUID = 1L;

			/**
			 * 
			 */
			public ClearAction() {
				super(CoreSwingResources.getInstance().getString("action.clearReference"));
			}

			/**
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(final ActionEvent e) {
				combo.setSelectedIndex(0);
			}
		}

		/**
		 * @see net.sf.jame.twister.swing.NodeEditorComponent#getComponent()
		 */
		public JComponent getComponent() {
			return this;
		}

		/**
		 * @see net.sf.jame.twister.swing.NodeEditorComponent#reloadValue()
		 */
		/**
		 * @see net.sf.jame.twister.swing.NodeEditorComponent#reloadValue()
		 */
		@SuppressWarnings("unchecked")
		public void reloadValue() {
			combo.removeActionListener(referenceSelectionListener);
			if (nodeEditor.getNodeValue() != null) {
				final ExtensionReference value = ((ExtensionReferenceElementNodeValue<ExtensionReference>) nodeEditor.getNodeValue()).getValue();
				if (value != null) {
					((ExtensionComboBoxModel) combo.getModel()).setSelectedItemByExtensionId(value.getExtensionId());
				}
				else {
					((ExtensionComboBoxModel) combo.getModel()).setSelectedItem(combo.getModel().getElementAt(0));
				}
			}
			combo.addActionListener(referenceSelectionListener);
		}

		/**
		 * @see net.sf.jame.twister.swing.NodeEditorComponent#dispose()
		 */
		public void dispose() {
		}
	}
}
