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
package net.sf.jame.core.swing.editor;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.sf.jame.core.extension.ConfigurableExtension;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.NullConfigurableExtension;
import net.sf.jame.core.swing.CoreSwingResources;
import net.sf.jame.core.swing.NodeEditorComponent;
import net.sf.jame.core.swing.editor.extension.EditorExtensionRuntime;
import net.sf.jame.core.swing.extension.ConfigurableExtensionComboBoxModel;
import net.sf.jame.core.swing.extension.ExtensionListCellRenderer;
import net.sf.jame.core.swing.util.GUIFactory;
import net.sf.jame.core.swing.util.StackLayout;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;

/**
 * @author Andrea Medeghini
 */
public abstract class ConfigurableReferenceElementEditorRuntime extends EditorExtensionRuntime {
	/**
	 * @see net.sf.jame.twister.swing.editor.extension.EditorExtensionRuntime#createEditor(net.sf.jame.core.tree.NodeEditor)
	 */
	@Override
	public NodeEditorComponent createEditor(final NodeEditor nodeEditor) {
		return new EditorComponent(nodeEditor);
	}

	/**
	 * @return
	 */
	protected abstract NodeValue<?> createChildValue();

	/**
	 * @param reference
	 * @return the node value.
	 */
	protected abstract NodeValue<?> createNodeValue(ConfigurableExtensionReference<?> reference);

	/**
	 * @return the model.
	 */
	protected abstract ConfigurableExtensionComboBoxModel createModel();

	/**
	 * @return
	 */
	protected abstract String getInsertBeforeLabel();

	/**
	 * @return
	 */
	protected abstract String getInsertAfterLabel();

	/**
	 * @return
	 */
	protected abstract String getRemoveLabel();

	/**
	 * @return
	 */
	protected abstract String getMoveUpLabel();

	/**
	 * @return
	 */
	protected abstract String getMoveDownLabel();

	/**
	 * @return
	 */
	protected abstract String getInsertBeforeTooltip();

	/**
	 * @return
	 */
	protected abstract String getInsertAfterTooltip();

	/**
	 * @return
	 */
	protected abstract String getRemoveTooltip();

	/**
	 * @return
	 */
	protected abstract String getMoveUpTooltip();

	/**
	 * @return
	 */
	protected abstract String getMoveDownTooltip();

	private class EditorComponent extends JPanel implements NodeEditorComponent {
		private static final long serialVersionUID = 1L;
		private final JComboBox combo = GUIFactory.createComboBox(createModel(), CoreSwingResources.getInstance().getString("tooltip.selectExtension"));

		/**
		 * @param nodeEditor
		 */
		public EditorComponent(final NodeEditor nodeEditor) {
			setLayout(new StackLayout());
			combo.setRenderer(new ExtensionListCellRenderer());
			if (nodeEditor.isParentMutable()) {
				final JButton insertBeforeButton = GUIFactory.createButton(new InsertBeforeAction(combo, nodeEditor), getInsertBeforeTooltip());
				final JButton insertAfterButton = GUIFactory.createButton(new InsertAfterAction(combo, nodeEditor), getInsertAfterTooltip());
				final JButton removeButton = GUIFactory.createButton(new RemoveAction(nodeEditor), getRemoveTooltip());
				final JButton moveUpButton = GUIFactory.createButton(new MoveUpAction(nodeEditor), getMoveUpTooltip());
				final JButton moveDownButton = GUIFactory.createButton(new MoveDownAction(nodeEditor), getMoveDownTooltip());
				this.add(GUIFactory.createLabel(CoreSwingResources.getInstance().getString("label.extension"), SwingConstants.CENTER));
				this.add(Box.createVerticalStrut(8));
				this.add(combo);
				this.add(Box.createVerticalStrut(8));
				this.add(insertBeforeButton);
				this.add(Box.createVerticalStrut(8));
				this.add(insertAfterButton);
				this.add(Box.createVerticalStrut(8));
				this.add(removeButton);
				this.add(Box.createVerticalStrut(8));
				this.add(moveUpButton);
				this.add(Box.createVerticalStrut(8));
				this.add(moveDownButton);
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
		public void reloadValue() {
		}

		/**
		 * @see net.sf.jame.twister.swing.NodeEditorComponent#dispose()
		 */
		public void dispose() {
		}
	}

	private class InsertAfterAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;
		private final JComboBox combo;

		/**
		 * @param combo
		 * @param nodeEditor
		 */
		public InsertAfterAction(final JComboBox combo, final NodeEditor nodeEditor) {
			super(getInsertAfterLabel());
			this.nodeEditor = nodeEditor;
			this.combo = combo;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent e) {
			try {
				final ConfigurableExtension<?, ?> extension = (ConfigurableExtension<?, ?>) combo.getSelectedItem();
				if (extension instanceof NullConfigurableExtension) {
					nodeEditor.getParentNodeEditor().insertChildNodeAfter(nodeEditor.getIndex(), createNodeValue(null));
				}
				else {
					final ConfigurableExtensionReference<?> reference = extension.createConfigurableExtensionReference();
					nodeEditor.getParentNodeEditor().insertChildNodeAfter(nodeEditor.getIndex(), createNodeValue(reference));
				}
			}
			catch (final ExtensionException x) {
				x.printStackTrace();
			}
		}
	}

	private class InsertBeforeAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;
		private final JComboBox combo;

		/**
		 * @param combo
		 * @param nodeEditor
		 */
		public InsertBeforeAction(final JComboBox combo, final NodeEditor nodeEditor) {
			super(getInsertBeforeLabel());
			this.nodeEditor = nodeEditor;
			this.combo = combo;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent e) {
			try {
				final ConfigurableExtension<?, ?> extension = (ConfigurableExtension<?, ?>) combo.getSelectedItem();
				if (extension instanceof NullConfigurableExtension) {
					nodeEditor.getParentNodeEditor().insertChildNodeBefore(nodeEditor.getIndex(), createNodeValue(null));
				}
				else {
					final ConfigurableExtensionReference<?> reference = extension.createConfigurableExtensionReference();
					nodeEditor.getParentNodeEditor().insertChildNodeBefore(nodeEditor.getIndex(), createNodeValue(reference));
				}
			}
			catch (final ExtensionException x) {
				x.printStackTrace();
			}
		}
	}

	private class RemoveAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;

		/**
		 * @param nodeEditor
		 */
		public RemoveAction(final NodeEditor nodeEditor) {
			super(getRemoveLabel());
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
			super(getMoveUpLabel());
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
			super(getMoveDownLabel());
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
