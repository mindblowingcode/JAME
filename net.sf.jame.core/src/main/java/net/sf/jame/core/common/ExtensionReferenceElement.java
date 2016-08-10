/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
package net.sf.jame.core.common;

import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.extension.ExtensionReference;

/**
 * @author Andrea Medeghini
 */
public class ExtensionReferenceElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	private ExtensionReference reference;
	public static final String CLASS_ID = "ExtensionReferenceElement";
	public static final int EXTENSION_REFERENCE_CHANGED = 10;

	/**
	 * 
	 */
	public ExtensionReferenceElement() {
		super(ExtensionReferenceElement.CLASS_ID);
	}

	/**
	 * Returns the image reference.
	 * 
	 * @return the image reference.
	 */
	public ExtensionReference getReference() {
		return reference;
	}

	/**
	 * Sets the image reference.
	 * 
	 * @param reference the image reference to set.
	 */
	public void setReference(final ExtensionReference reference) {
		final ExtensionReference prevReference = getReference();
		this.reference = reference;
		if (checkContext()) {
			fireConfigChanged(new ValueChangeEvent(ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED, getContext().getTimestamp(), reference, prevReference));
		}
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		return ((reference == null) && (((ExtensionReferenceElement) obj).reference == null)) || reference.equals(((ExtensionReferenceElement) obj).reference);
	}

	/**
	 * @see java.lang.Object#clone()
	 */
	@Override
	public ExtensionReferenceElement clone() {
		final ExtensionReferenceElement element = new ExtensionReferenceElement();
		if (getReference() != null) {
			element.setReference(getReference());
		}
		return element;
	}

	/**
	 * @see net.sf.jame.core.config.ConfigElement#copyFrom(net.sf.jame.core.config.ConfigElement)
	 */
	public void copyFrom(ConfigElement source) {
		final ExtensionReferenceElement element = (ExtensionReferenceElement) source;
		if (element.getReference() != null) {
			setReference(element.getReference().clone());
		}
	}
}
