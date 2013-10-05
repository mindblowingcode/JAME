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

import net.sf.jame.core.swing.NodeEditorComponent;
import net.sf.jame.core.swing.editor.extension.EditorExtensionRuntime;
import net.sf.jame.core.swing.util.GUIFactory;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.tree.NumberNodeEditor;

/**
 * @author Andrea Medeghini
 */
public abstract class NumberEditorRuntime extends EditorExtensionRuntime {
	/**
	 * @see net.sf.jame.twister.swing.editor.extension.EditorExtensionRuntime#createEditor(net.sf.jame.core.tree.NodeEditor)
	 */
	@Override
	public NodeEditorComponent createEditor(final NodeEditor nodeEditor) {
		return new EditorComponent((NumberNodeEditor) nodeEditor);
	}

	private class EditorComponent extends JPanel implements NodeEditorComponent {
		private static final long serialVersionUID = 1L;
		private final NumberNodeEditor nodeEditor;
		private final JTextField textfield;

		/**
		 * @param nodeEditor
		 */
		public EditorComponent(final NumberNodeEditor nodeEditor) {
			this.nodeEditor = nodeEditor;
			setLayout(new FlowLayout(FlowLayout.CENTER));
			textfield = GUIFactory.createTextField(nodeEditor.getNodeValueAsString(), null);
			textfield.setMinimumSize(new Dimension(80, GUIFactory.DEFAULT_HEIGHT));
			textfield.setMaximumSize(new Dimension(80, GUIFactory.DEFAULT_HEIGHT));
			textfield.setPreferredSize(new Dimension(80, GUIFactory.DEFAULT_HEIGHT));
			this.add(GUIFactory.createLabel(nodeEditor.getNodeLabel(), SwingConstants.CENTER));
			this.add(textfield);
			this.add(GUIFactory.createLabel("[" + nodeEditor.getMinimum() + ", " + nodeEditor.getMaximum() + "]", SwingConstants.CENTER));
			NodeEditorChangeListener listener = new NodeEditorChangeListener(textfield, nodeEditor);
			textfield.addActionListener(listener);
			textfield.addFocusListener(listener);
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
			if (nodeEditor.getNodeValue() != null) {
				textfield.setText(nodeEditor.getNodeValueAsString());
			}
		}

		/**
		 * @see net.sf.jame.twister.swing.NodeEditorComponent#dispose()
		 */
		public void dispose() {
		}
	}

	private class NodeEditorChangeListener implements ActionListener, FocusListener {
		private final NumberNodeEditor nodeEditor;
		private final JTextField textfield;

		/**
		 * @param textfield
		 * @param nodeEditor
		 */
		public NodeEditorChangeListener(final JTextField textfield, final NumberNodeEditor nodeEditor) {
			this.nodeEditor = nodeEditor;
			this.textfield = textfield;
		}

		public void actionPerformed(final ActionEvent e) {
			Number value = parseValue(textfield.getText());
			if ((value.doubleValue() >= nodeEditor.getMinimum().doubleValue()) && (value.doubleValue() <= nodeEditor.getMaximum().doubleValue())) {
				if (!nodeEditor.getNodeValue().getValue().equals(value)) {
					nodeEditor.setNodeValue(createNodeValue(value));
				}
			}
			else {
				textfield.setText(nodeEditor.getNodeValueAsString());
			}
		}

		public void focusGained(final FocusEvent e) {
		}

		public void focusLost(final FocusEvent e) {
			Number value = parseValue(textfield.getText());
			if ((value.doubleValue() >= nodeEditor.getMinimum().doubleValue()) && (value.doubleValue() <= nodeEditor.getMaximum().doubleValue())) {
				if (!nodeEditor.getNodeValue().getValue().equals(value)) {
					nodeEditor.setNodeValue(createNodeValue(value));
				}
			}
			else {
				textfield.setText(nodeEditor.getNodeValueAsString());
			}
		}
	}

	protected abstract Number parseValue(String text);

	protected abstract NodeValue<?> createNodeValue(Number value);
}
