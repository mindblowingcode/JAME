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
package net.sf.jame.core.swing;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import net.sf.jame.core.tree.TransferableNodeValue;

/**
 * @author Andrea Medeghini
 */
public class TransferableNodeValueAdapter implements Transferable {
	/**
	 * 
	 */
	public static final DataFlavor NODE_VALUE_FLAVOR = new DataFlavor(TransferableNodeValue.class, "JAME.Twister.NodeValue");
	private static final DataFlavor[] flavors = new DataFlavor[] { TransferableNodeValueAdapter.NODE_VALUE_FLAVOR };
	private TransferableNodeValue value;

	/**
	 * @param value
	 */
	public TransferableNodeValueAdapter(final TransferableNodeValue value) {
		this.value = value;
	}

	/**
	 * @see java.lang.Object#finalize()
	 */
	@Override
	public void finalize() throws Throwable {
		value = null;
		super.finalize();
	}

	/**
	 * @see java.awt.datatransfer.Transferable#getTransferDataFlavors()
	 */
	public DataFlavor[] getTransferDataFlavors() {
		return TransferableNodeValueAdapter.flavors;
	}

	/**
	 * @see java.awt.datatransfer.Transferable#isDataFlavorSupported(java.awt.datatransfer.DataFlavor)
	 */
	public boolean isDataFlavorSupported(final DataFlavor flavor) {
		for (final DataFlavor validFlavor : TransferableNodeValueAdapter.flavors) {
			if (validFlavor.equals(flavor)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see java.awt.datatransfer.Transferable#getTransferData(java.awt.datatransfer.DataFlavor)
	 */
	public Object getTransferData(final DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (isDataFlavorSupported(flavor)) {
			return value;
		}
		throw new UnsupportedFlavorException(flavor);
	}
}
