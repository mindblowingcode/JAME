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
package net.sf.jame.twister.layerFilter;

import net.sf.jame.core.common.BooleanElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.twister.layerFilter.extension.LayerFilterExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class LayerFilterConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "LayerFilter";
	private final ConfigurableExtensionReferenceElement<LayerFilterExtensionConfig> extensionElement = new ConfigurableExtensionReferenceElement<LayerFilterExtensionConfig>();
	private final BooleanElement enabledElement = new BooleanElement(true);
	private final BooleanElement lockedElement = new BooleanElement(false);
	private final StringElement labelElement = new StringElement("New Filter");

	/**
	 * 
	 */
	public LayerFilterConfigElement() {
		super(LayerFilterConfigElement.CLASS_ID);
	}

	/**
	 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		labelElement.setContext(context);
		lockedElement.setContext(context);
		enabledElement.setContext(context);
		extensionElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public LayerFilterConfigElement clone() {
		final LayerFilterConfigElement element = new LayerFilterConfigElement();
		element.setLabel(getLabel());
		element.setLocked(isLocked());
		element.setEnabled(isEnabled());
		if (getReference() != null) {
			element.setReference(getReference().clone());
		}
		return element;
	}

	/**
	 * @see net.sf.jame.core.config.ConfigElement#copyFrom(net.sf.jame.core.config.ConfigElement)
	 */
	public void copyFrom(ConfigElement source) {
		LayerFilterConfigElement element = (LayerFilterConfigElement) source;
		setLabel(element.getLabel());
		setLocked(element.isLocked());
		setEnabled(element.isEnabled());
		if (element.getReference() != null) {
			setReference(element.getReference().clone());
		}
	}

	/**
	 * @param label
	 */
	public void setLabel(final String label) {
		labelElement.setValue(label);
	}

	/**
	 * @return
	 */
	public String getLabel() {
		return labelElement.getValue();
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReference<LayerFilterExtensionConfig> getReference() {
		return extensionElement.getReference();
	}

	/**
	 * @param reference
	 */
	public void setReference(final ConfigurableExtensionReference<LayerFilterExtensionConfig> reference) {
		extensionElement.setReference(reference);
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReferenceElement<LayerFilterExtensionConfig> getExtensionElement() {
		return extensionElement;
	}

	/**
	 * @return
	 */
	public boolean isLocked() {
		return lockedElement.getValue();
	}

	/**
	 * @param locked
	 */
	public void setLocked(final boolean locked) {
		lockedElement.setValue(locked);
	}

	/**
	 * @return
	 */
	public BooleanElement getLockedElement() {
		return lockedElement;
	}

	/**
	 * @return
	 */
	public boolean isEnabled() {
		return enabledElement.getValue();
	}

	/**
	 * @param enabled
	 */
	public void setEnabled(final boolean enabled) {
		enabledElement.setValue(enabled);
	}

	/**
	 * @return
	 */
	public BooleanElement getEnabledElement() {
		return enabledElement;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		final LayerFilterConfigElement other = (LayerFilterConfigElement) obj;
		if (enabledElement == null) {
			if (other.enabledElement != null) {
				return false;
			}
		}
		else if (!enabledElement.equals(other.enabledElement)) {
			return false;
		}
		if (extensionElement == null) {
			if (other.extensionElement != null) {
				return false;
			}
		}
		else if (!extensionElement.equals(other.extensionElement)) {
			return false;
		}
		if (lockedElement == null) {
			if (other.lockedElement != null) {
				return false;
			}
		}
		else if (!lockedElement.equals(other.lockedElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#dispose()
	 */
	@Override
	public void dispose() {
		extensionElement.dispose();
		enabledElement.dispose();
		lockedElement.dispose();
		labelElement.dispose();
		super.dispose();
	}

	/**
	 * @return
	 */
	public StringElement getLabelElement() {
		return labelElement;
	}
}
