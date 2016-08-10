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
package net.sf.jame.twister;

import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.core.util.Color32bit;
import net.sf.jame.twister.effect.EffectConfigElement;
import net.sf.jame.twister.effect.EffectRuntimeElement;
import net.sf.jame.twister.frame.FrameConfigElement;
import net.sf.jame.twister.frame.FrameRuntimeElement;

/**
 * The twister runtime.
 * 
 * @author Andrea Medeghini
 */
public class TwisterRuntime extends RuntimeElement {
	private Color32bit background = Color32bit.BLACK;
	private BackgroundElementListener backgroundListener;
	private FrameElementListener frameListener;
	private EffectElementListener effectListener;
	private FrameRuntimeElement frameElement;
	private EffectRuntimeElement effectElement;
	private TwisterConfig config;

	/**
	 * @return the config
	 */
	public TwisterConfig getConfig() {
		return config;
	}

	/**
	 * @param config
	 */
	public TwisterRuntime(final TwisterConfig config) {
		this.config = config;
		setBackground(config.getBackground());
		backgroundListener = new BackgroundElementListener();
		config.getBackgroundElement().addChangeListener(backgroundListener);
		frameElement = new FrameRuntimeElement(config.getFrameConfigElement());
		frameListener = new FrameElementListener();
		config.getFrameSingleElement().addChangeListener(frameListener);
		effectElement = new EffectRuntimeElement(config.getEffectConfigElement());
		effectListener = new EffectElementListener();
		config.getEffectSingleElement().addChangeListener(effectListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((config != null) && (backgroundListener != null)) {
			config.getBackgroundElement().removeChangeListener(backgroundListener);
		}
		backgroundListener = null;
		if ((config != null) && (frameListener != null)) {
			config.getFrameSingleElement().removeChangeListener(frameListener);
		}
		frameListener = null;
		if ((config != null) && (effectListener != null)) {
			config.getEffectSingleElement().removeChangeListener(effectListener);
		}
		effectListener = null;
		if (frameElement != null) {
			frameElement.dispose();
			frameElement = null;
		}
		if (effectElement != null) {
			effectElement.dispose();
			effectElement = null;
		}
		config = null;
		super.dispose();
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public Color32bit getBackground() {
		return background;
	}

	private void setBackground(final Color32bit background) {
		this.background = background;
	}

	/**
	 * Returns the frame.
	 * 
	 * @return the frame.
	 */
	public FrameRuntimeElement getFrameElement() {
		return frameElement;
	}

	private void setFrameElement(final FrameRuntimeElement frameElement) {
		if (this.frameElement != null) {
			this.frameElement.dispose();
		}
		this.frameElement = frameElement;
	}

	/**
	 * Returns the effect.
	 * 
	 * @return the effect.
	 */
	public EffectRuntimeElement getEffectElement() {
		return effectElement;
	}

	private void setEffectElement(final EffectRuntimeElement effectElement) {
		if (this.effectElement != null) {
			this.effectElement.dispose();
		}
		this.effectElement = effectElement;
	}

	private class FrameElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			if (e.getEventType() == ValueConfigElement.VALUE_CHANGED) {
				setFrameElement(new FrameRuntimeElement((FrameConfigElement) e.getParams()[0]));
				fireChanged();
			}
		}
	}

	private class EffectElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			if (e.getEventType() == ValueConfigElement.VALUE_CHANGED) {
				setEffectElement(new EffectRuntimeElement((EffectConfigElement) e.getParams()[0]));
				fireChanged();
			}
		}
	}

	private class BackgroundElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setBackground((Color32bit) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean frameChanged = (frameElement != null) && frameElement.isChanged();
		final boolean effectChanged = (effectElement != null) && effectElement.isChanged();
		return super.isChanged() || frameChanged || effectChanged;
	}
}
