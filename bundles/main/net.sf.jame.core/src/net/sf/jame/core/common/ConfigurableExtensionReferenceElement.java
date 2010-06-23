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
package net.sf.jame.core.common;

import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.extension.ExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class ConfigurableExtensionReferenceElement<T extends ExtensionConfig> extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	private ConfigurableExtensionReference<T> reference;
	public static final String CLASS_ID = "ConfigurableExtensionReferenceElement";

	/**
	 * 
	 */
	public ConfigurableExtensionReferenceElement() {
		super(ConfigurableExtensionReferenceElement.CLASS_ID);
	}

	/**
	 * Returns the image reference.
	 * 
	 * @return the image reference.
	 */
	public ConfigurableExtensionReference<T> getReference() {
		return this.reference;
	}

	/**
	 * Sets the image reference.
	 * 
	 * @param reference the image reference to set.
	 */
	public void setReference(final ConfigurableExtensionReference<T> reference) {
		final ConfigurableExtensionReference<T> prevReference = this.getReference();
		this.reference = reference;
		if (checkContext()) {
			if ((reference != null) && (reference.getExtensionConfig() != null)) {
				reference.getExtensionConfig().setContext(getContext());
			}
			fireConfigChanged(new ValueChangeEvent(ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED, getContext().getTimestamp(), reference, prevReference));
		}
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		if ((this.reference != null) && (this.reference.getExtensionConfig() != null)) {
			this.reference.getExtensionConfig().setContext(getContext());
		}
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(final Object obj) {
		return ((reference == null) && (((ConfigurableExtensionReferenceElement<T>) obj).reference == null)) || ((reference != null) && reference.equals(((ConfigurableExtensionReferenceElement<T>) obj).reference));
	}

	/**
	 * @see java.lang.Object#clone()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ConfigurableExtensionReferenceElement<T> clone() {
		final ConfigurableExtensionReferenceElement element = new ConfigurableExtensionReferenceElement();
		if (getReference() != null) {
			element.setReference(getReference());
		}
		return element;
	}

	/**
	 * @see net.sf.jame.core.config.ConfigElement#copyFrom(net.sf.jame.core.config.ConfigElement)
	 */
	@SuppressWarnings("unchecked")
	public void copyFrom(ConfigElement source) {
		final ConfigurableExtensionReferenceElement<T> element = (ConfigurableExtensionReferenceElement<T>) source;
		if (element.getReference() != null) {
			setReference(element.getReference().clone());
		}
	}
}
