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
package net.sf.jame.core.swing.osgi;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;

/**
 * A cell renderer for extension points trees.
 * 
 * @author Andrea Medeghini
 */
public class IExtensionPointTreeCellRenderer extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = 1L;

	/**
	 * @see javax.swing.tree.TreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
	 */
	@Override
	public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean selected, final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
		final DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		if (node.getUserObject() != null) {
			if (node.getUserObject() instanceof IExtensionPoint) {
				return super.getTreeCellRendererComponent(tree, ((IExtensionPoint) node.getUserObject()).getLabel() + " (" + ((IExtensionPoint) node.getUserObject()).getUniqueIdentifier() + ")", selected, expanded, leaf, row, hasFocus);
			}
			else if (node.getUserObject() instanceof IExtension) {
				return super.getTreeCellRendererComponent(tree, ((IExtension) node.getUserObject()).getLabel() + " (" + ((IExtension) node.getUserObject()).getUniqueIdentifier() + ")", selected, expanded, leaf, row, hasFocus);
			}
			else if (node.getUserObject() instanceof IConfigurationElement) {
				return super.getTreeCellRendererComponent(tree, ((IConfigurationElement) node.getUserObject()).getName(), selected, expanded, leaf, row, hasFocus);
			}
			else if (node.getUserObject() != null) {
				return super.getTreeCellRendererComponent(tree, node.getUserObject().toString(), selected, expanded, leaf, row, hasFocus);
			}
		}
		return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
	}
}
