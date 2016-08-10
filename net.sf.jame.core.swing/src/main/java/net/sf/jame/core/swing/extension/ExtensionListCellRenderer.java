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
package net.sf.jame.core.swing.extension;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import net.sf.jame.core.extension.ConfigurableExtension;
import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.NullConfigurableExtension;
import net.sf.jame.core.extension.NullExtension;

/**
 * A cell renderer for extensions lists.
 * 
 * @author Andrea Medeghini
 */
public class ExtensionListCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;

	/**
	 * @see javax.swing.DefaultListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
	 */
	@Override
	public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
		if ((value != null) && (value instanceof NullConfigurableExtension)) {
			return super.getListCellRendererComponent(list, "-", index, isSelected, cellHasFocus);
		}
		else if ((value != null) && (value instanceof NullExtension)) {
			return super.getListCellRendererComponent(list, "-", index, isSelected, cellHasFocus);
		}
		else if ((value != null) && (value instanceof ConfigurableExtension<?, ?>)) {
			return super.getListCellRendererComponent(list, ((Extension<?>) value).getExtensionName(), index, isSelected, cellHasFocus);
		}
		else if ((value != null) && (value instanceof Extension<?>)) {
			return super.getListCellRendererComponent(list, ((Extension<?>) value).getExtensionName(), index, isSelected, cellHasFocus);
		}
		else {
			return super.getListCellRendererComponent(list, "-", index, isSelected, cellHasFocus);
		}
	}
}
