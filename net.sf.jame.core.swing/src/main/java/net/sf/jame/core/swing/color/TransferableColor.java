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
package net.sf.jame.core.swing.color;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * @author Andrea Medeghini
 */
public class TransferableColor implements Transferable {
	/**
	 * 
	 */
	public static final DataFlavor COLOR_FLAVOR = new DataFlavor(Color.class, "color");
	private static final DataFlavor[] flavors = new DataFlavor[] { TransferableColor.COLOR_FLAVOR };
	private Color color;

	/**
	 * @param color
	 */
	public TransferableColor(final Color color) {
		this.color = color;
	}

	/**
	 * @see java.lang.Object#finalize()
	 */
	@Override
	public void finalize() throws Throwable {
		color = null;
		super.finalize();
	}

	/**
	 * @see java.awt.datatransfer.Transferable#getTransferDataFlavors()
	 */
	public DataFlavor[] getTransferDataFlavors() {
		return TransferableColor.flavors;
	}

	/**
	 * @see java.awt.datatransfer.Transferable#isDataFlavorSupported(java.awt.datatransfer.DataFlavor)
	 */
	public boolean isDataFlavorSupported(final DataFlavor flavor) {
		for (final DataFlavor validFlavor : getTransferDataFlavors()) {
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
			return color;
		}
		throw new UnsupportedFlavorException(flavor);
	}
}
