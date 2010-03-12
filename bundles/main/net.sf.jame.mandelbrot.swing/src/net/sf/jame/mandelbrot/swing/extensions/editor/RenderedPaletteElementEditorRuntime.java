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
package net.sf.jame.mandelbrot.swing.extensions.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.sf.jame.core.swing.NodeEditorComponent;
import net.sf.jame.core.swing.editor.extension.EditorExtensionRuntime;
import net.sf.jame.core.swing.palette.PaletteChangeEvent;
import net.sf.jame.core.swing.palette.PaletteChangeListener;
import net.sf.jame.core.swing.palette.PaletteField;
import net.sf.jame.core.swing.palette.PaletteFieldModel;
import net.sf.jame.core.swing.util.GUIFactory;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.util.Palette;
import net.sf.jame.mandelbrot.common.RenderedPaletteElementNodeValue;
import net.sf.jame.mandelbrot.swing.extensions.MandelbrotSwingExtensionResources;
import net.sf.jame.mandelbrot.swing.palette.DefaultRenderedPaletteModel;
import net.sf.jame.mandelbrot.swing.palette.RenderedPaletteEditor;
import net.sf.jame.mandelbrot.swing.palette.RenderedPaletteModel;
import net.sf.jame.mandelbrot.util.RenderedPalette;

/**
 * @author Andrea Medeghini
 */
public class RenderedPaletteElementEditorRuntime extends EditorExtensionRuntime {
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
		private final PaletteField field;
		private final DefaultPaletteChangeListener listener;

		/**
		 * @param nodeEditor
		 */
		public EditorComponent(final NodeEditor nodeEditor) {
			this.nodeEditor = nodeEditor;
			listener = new DefaultPaletteChangeListener();
			setLayout(new FlowLayout(FlowLayout.CENTER));
			final PaletteFieldModel model = new DefaultRenderedPaletteModel(((RenderedPaletteElementNodeValue) nodeEditor.getNodeValue()).getValue());
			field = new PaletteField(model);
			field.setDropEnabled(nodeEditor.isNodeEditable());
			field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			final Dimension size = new Dimension(200, 40);
			field.setMinimumSize(size);
			field.setMaximumSize(size);
			field.setPreferredSize(size);
			field.addMouseListener(new FieldMouseListener(field, nodeEditor));
			field.getModel().addPaletteChangeListener(listener);
			add(GUIFactory.createLabel(nodeEditor.getNodeLabel(), SwingConstants.CENTER));
			this.add(field);
			if (nodeEditor.isNodeEditable()) {
				final JButton button = GUIFactory.createButton(new EditActon(field, nodeEditor), MandelbrotSwingExtensionResources.getInstance().getString("tooltip.editPalette"));
				this.add(button);
			}
		}

		private class DefaultPaletteChangeListener implements PaletteChangeListener {
			/**
			 * @see net.sf.jame.core.swing.palette.PaletteChangeListener#paletteChanged(net.sf.jame.core.swing.palette.PaletteChangeEvent)
			 */
			public void paletteChanged(final PaletteChangeEvent e) {
				nodeEditor.setNodeValue(new RenderedPaletteElementNodeValue((RenderedPalette) ((RenderedPaletteModel) e.getSource()).getPalette()));
			}
		}

		private class FieldMouseListener extends MouseAdapter {
			private final PaletteField field;
			private final NodeEditor nodeEditor;

			public FieldMouseListener(final PaletteField field, final NodeEditor nodeEditor) {
				this.field = field;
				this.nodeEditor = nodeEditor;
			}

			@Override
			public void mouseClicked(final MouseEvent e) {
				final Palette palette = RenderedPaletteEditor.showRenderedPaletteEditor(field, nodeEditor.getNodeLabel(), (RenderedPalette) field.getPalette());
				if (palette != null) {
					field.getModel().removePaletteChangeListener(listener);
					field.getModel().setPalette(palette, false);
					nodeEditor.setNodeValue(new RenderedPaletteElementNodeValue((RenderedPalette) palette));
					field.getModel().addPaletteChangeListener(listener);
				}
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
			if (nodeEditor.getNodeValue() != null) {
				field.getModel().removePaletteChangeListener(listener);
				field.getModel().setPalette(((RenderedPaletteElementNodeValue) nodeEditor.getNodeValue()).getValue(), false);
				field.getModel().addPaletteChangeListener(listener);
			}
		}

		/**
		 * @see net.sf.jame.core.swing.NodeEditorComponent#dispose()
		 */
		public void dispose() {
		}

		private class EditActon extends AbstractAction {
			private static final long serialVersionUID = 1L;
			private final PaletteField field;
			private final NodeEditor nodeEditor;

			/**
			 * @param field
			 * @param nodeEditor
			 */
			public EditActon(final PaletteField field, final NodeEditor nodeEditor) {
				super(MandelbrotSwingExtensionResources.getInstance().getString("action.edit"));
				this.field = field;
				this.nodeEditor = nodeEditor;
			}

			public void actionPerformed(final ActionEvent e) {
				final Palette palette = RenderedPaletteEditor.showRenderedPaletteEditor(field, nodeEditor.getNodeLabel(), (RenderedPalette) field.getPalette());
				if (palette != null) {
					field.getModel().removePaletteChangeListener(listener);
					field.getModel().setPalette(palette, false);
					nodeEditor.setNodeValue(new RenderedPaletteElementNodeValue((RenderedPalette) palette));
					field.getModel().addPaletteChangeListener(listener);
				}
			}
		}
	}
}
