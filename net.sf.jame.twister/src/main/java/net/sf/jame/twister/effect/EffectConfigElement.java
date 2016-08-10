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
package net.sf.jame.twister.effect;

import net.sf.jame.core.common.BooleanElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.twister.effect.extension.EffectExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class EffectConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "Effect";
	private final ConfigurableExtensionReferenceElement<EffectExtensionConfig> extensionElement = new ConfigurableExtensionReferenceElement<EffectExtensionConfig>();
	private final BooleanElement enabledElement = new BooleanElement(true);
	private final BooleanElement lockedElement = new BooleanElement(false);

	/**
	 * 
	 */
	public EffectConfigElement() {
		super(EffectConfigElement.CLASS_ID);
	}

	/**
	 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		lockedElement.setContext(context);
		enabledElement.setContext(context);
		extensionElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public EffectConfigElement clone() {
		final EffectConfigElement element = new EffectConfigElement();
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
		EffectConfigElement element = (EffectConfigElement) source;
		element.setLocked(isLocked());
		element.setEnabled(isEnabled());
		if (element.getReference() != null) {
			setReference(element.getReference().clone());
		}
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReference<EffectExtensionConfig> getReference() {
		return extensionElement.getReference();
	}

	/**
	 * @param reference
	 */
	public void setReference(final ConfigurableExtensionReference<EffectExtensionConfig> reference) {
		extensionElement.setReference(reference);
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReferenceElement<EffectExtensionConfig> getExtensionElement() {
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
		final EffectConfigElement other = (EffectConfigElement) obj;
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
		super.dispose();
	}
}
