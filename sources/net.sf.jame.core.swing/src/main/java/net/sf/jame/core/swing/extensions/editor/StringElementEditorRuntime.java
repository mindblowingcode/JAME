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
package net.sf.jame.core.swing.extensions.editor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.sf.jame.core.common.StringElementNodeValue;
import net.sf.jame.core.swing.NodeEditorComponent;
import net.sf.jame.core.swing.editor.extension.EditorExtensionRuntime;
import net.sf.jame.core.swing.util.GUIFactory;
import net.sf.jame.core.tree.NodeEditor;

/**
 * @author Andrea Medeghini
 */
public class StringElementEditorRuntime extends EditorExtensionRuntime {
	/**
	 * @see net.sf.jame.core.swing.editor.extension.EditorExtensionRuntime#createEditor(net.sf.jame.core.tree.NodeEditor)
	 */
	@Override
	public NodeEditorComponent createEditor(final NodeEditor nodeEditor) {
		return new EditorComponent(nodeEditor);
	}

	private class EditorComponent extends JPanel implements NodeEditorComponent {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;
		private final JTextField textField;

		/**
		 * @param nodeEditor
		 */
		public EditorComponent(final NodeEditor nodeEditor) {
			this.nodeEditor = nodeEditor;
			setLayout(new FlowLayout(FlowLayout.CENTER));
			textField = GUIFactory.createTextField(((StringElementNodeValue) nodeEditor.getNodeValue()).getValue(), null);
			textField.setPreferredSize(new Dimension(300, 20));
			textField.addActionListener(new NodeEditorActionListener(nodeEditor));
			textField.addFocusListener(new NodeEditorActionListener(nodeEditor));
			this.add(GUIFactory.createLabel(nodeEditor.getNodeLabel(), SwingConstants.LEFT));
			this.add(textField);
			textField.setCaretPosition(0);
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
			if (nodeEditor.getNodeValue() != null) {
				textField.setText(((StringElementNodeValue) nodeEditor.getNodeValue()).getValue());
				textField.setCaretPosition(0);
			}
		}

		/**
		 * @see net.sf.jame.core.swing.NodeEditorComponent#dispose()
		 */
		public void dispose() {
		}
	}

	private class NodeEditorActionListener implements ActionListener, FocusListener {
		private final NodeEditor nodeEditor;

		/**
		 * @param nodeEditor
		 */
		public NodeEditorActionListener(final NodeEditor nodeEditor) {
			this.nodeEditor = nodeEditor;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent e) {
			String value = ((JTextField) e.getSource()).getText();
			if (!nodeEditor.getNodeValue().getValue().equals(value)) {
				nodeEditor.setNodeValue(new StringElementNodeValue(value));
			}
		}

		/**
		 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
		 */
		public void focusGained(final FocusEvent e) {
		}

		/**
		 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
		 */
		public void focusLost(final FocusEvent e) {
			String value = ((JTextField) e.getSource()).getText();
			if (!nodeEditor.getNodeValue().getValue().equals(value)) {
				nodeEditor.setNodeValue(new StringElementNodeValue(value));
			}
		}
	}
}
