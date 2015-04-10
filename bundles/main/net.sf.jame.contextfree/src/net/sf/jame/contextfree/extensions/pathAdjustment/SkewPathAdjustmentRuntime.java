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
package net.sf.jame.contextfree.extensions.pathAdjustment;

import net.sf.jame.contextfree.pathAdjustment.extension.PathAdjustmentExtensionRuntime;
import net.sf.jame.contextfree.renderer.support.CFModification;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class SkewPathAdjustmentRuntime extends PathAdjustmentExtensionRuntime<SkewPathAdjustmentConfig> {
	private Float shearX;
	private ShearXListener shearXListener;
	private Float shearY;
	private ShearYListener shearYListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setShearX(getConfig().getShearX());
		shearXListener = new ShearXListener();
		getConfig().getShearXElement().addChangeListener(shearXListener);
		setShearY(getConfig().getShearY());
		shearYListener = new ShearYListener();
		getConfig().getShearYElement().addChangeListener(shearYListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (shearXListener != null)) {
			getConfig().getShearXElement().removeChangeListener(shearXListener);
		}
		shearXListener = null;
		if ((getConfig() != null) && (shearYListener != null)) {
			getConfig().getShearYElement().removeChangeListener(shearYListener);
		}
		shearYListener = null;
		super.dispose();
	}
	
	/**
	 * @return the shearX.
	 */
	public Float getShearX() {
		return shearX;
	}

	private void setShearX(final Float shearX) {
		this.shearX = shearX;
	}
	
	private class ShearXListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setShearX((Float) e.getParams()[0]);
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
	 * @return the shearY.
	 */
	public Float getShearY() {
		return shearY;
	}

	private void setShearY(final Float shearY) {
		this.shearY = shearY;
	}
	
	private class ShearYListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setShearY((Float) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	@Override
	public void apply(CFModification mod) {
		mod.skew(shearX, shearY);
	}
}
