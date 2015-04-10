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
package net.sf.jame.twister.effect;

import net.sf.jame.core.common.ExtensionReferenceElement;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.twister.TwisterRegistry;
import net.sf.jame.twister.effect.extension.EffectExtensionConfig;
import net.sf.jame.twister.effect.extension.EffectExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class EffectRuntimeElement extends RuntimeElement {
	private EffectExtensionRuntime<?> effectRuntime;
	private EffectConfigElement effectElement;
	private ExtensionListener extensionListener;
	private EnabledListener enabledListener;
	private boolean enabled;

	/**
	 * Constructs a new effect.
	 * 
	 * @param registry
	 * @param effectElement
	 */
	public EffectRuntimeElement(final EffectConfigElement effectElement) {
		if (effectElement == null) {
			throw new IllegalArgumentException("effectElement is null");
		}
		this.effectElement = effectElement;
		createRuntime(effectElement.getReference());
		extensionListener = new ExtensionListener();
		effectElement.getExtensionElement().addChangeListener(extensionListener);
		setEnabled(effectElement.isEnabled());
		enabledListener = new EnabledListener();
		effectElement.getEnabledElement().addChangeListener(enabledListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((effectElement != null) && (extensionListener != null)) {
			effectElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if ((effectElement != null) && (enabledListener != null)) {
			effectElement.getEnabledElement().removeChangeListener(enabledListener);
		}
		enabledListener = null;
		if (effectRuntime != null) {
			effectRuntime.dispose();
			effectRuntime = null;
		}
		effectElement = null;
		super.dispose();
	}

	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<EffectExtensionConfig> reference) {
		try {
			if (reference != null) {
				final EffectExtensionRuntime effectRuntime = TwisterRegistry.getInstance().getEffectExtension(reference.getExtensionId()).createExtensionRuntime();
				final EffectExtensionConfig effectConfig = reference.getExtensionConfig();
				effectRuntime.setConfig(effectConfig);
				setEffectRuntime(effectRuntime);
			}
			else {
				setEffectRuntime(null);
			}
		}
		catch (final ExtensionNotFoundException e) {
			e.printStackTrace();
		}
		catch (final ExtensionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean effectChanged = (effectRuntime != null) && effectRuntime.isChanged();
		return super.isChanged() || effectChanged;
	}

	/**
	 * @return the effectRuntime
	 */
	public EffectExtensionRuntime<?> getEffectRuntime() {
		return effectRuntime;
	}

	private void setEffectRuntime(final EffectExtensionRuntime<?> effectRuntime) {
		if (this.effectRuntime != null) {
			this.effectRuntime.dispose();
		}
		this.effectRuntime = effectRuntime;
	}

	/**
	 * @return the enabled.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	private void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	private class ExtensionListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		@SuppressWarnings("unchecked")
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED: {
					createRuntime((ConfigurableExtensionReference<EffectExtensionConfig>) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	private class EnabledListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setEnabled((Boolean) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
}
