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
package net.sf.jame.contextfree.extensions.pathReplacement;

import net.sf.jame.contextfree.pathReplacement.extension.PathReplacementExtensionRuntime;
import net.sf.jame.contextfree.renderer.support.CFRule;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class ArcToPathReplacementRuntime extends PathReplacementExtensionRuntime<ArcToPathReplacementConfig> {
	private Float x;
	private XListener xListener;
	private Float y;
	private YListener yListener;
	private Float rx;
	private RxListener rxListener;
	private Float ry;
	private RyListener ryListener;
	private Float r;
	private RListener rListener;
	private Boolean sweep;
	private SweepListener sweepListener;
	private Boolean large;
	private LargeListener largeListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setX(getConfig().getX());
		xListener = new XListener();
		getConfig().getXElement().addChangeListener(xListener);
		setY(getConfig().getY());
		yListener = new YListener();
		getConfig().getYElement().addChangeListener(yListener);
		setRx(getConfig().getRx());
		rxListener = new RxListener();
		getConfig().getRxElement().addChangeListener(rxListener);
		setRy(getConfig().getRy());
		ryListener = new RyListener();
		getConfig().getRyElement().addChangeListener(ryListener);
		setR(getConfig().getR());
		rListener = new RListener();
		getConfig().getRElement().addChangeListener(rListener);
		setSweep(getConfig().isSweep());
		sweepListener = new SweepListener();
		getConfig().getSweepElement().addChangeListener(sweepListener);
		setLarge(getConfig().isLarge());
		largeListener = new LargeListener();
		getConfig().getLargeElement().addChangeListener(largeListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (xListener != null)) {
			getConfig().getXElement().removeChangeListener(xListener);
		}
		xListener = null;
		if ((getConfig() != null) && (yListener != null)) {
			getConfig().getYElement().removeChangeListener(yListener);
		}
		yListener = null;
		if ((getConfig() != null) && (rxListener != null)) {
			getConfig().getRxElement().removeChangeListener(rxListener);
		}
		rxListener = null;
		if ((getConfig() != null) && (ryListener != null)) {
			getConfig().getRyElement().removeChangeListener(ryListener);
		}
		ryListener = null;
		if ((getConfig() != null) && (rListener != null)) {
			getConfig().getRElement().removeChangeListener(rListener);
		}
		rListener = null;
		if ((getConfig() != null) && (sweepListener != null)) {
			getConfig().getSweepElement().removeChangeListener(sweepListener);
		}
		sweepListener = null;
		if ((getConfig() != null) && (largeListener != null)) {
			getConfig().getLargeElement().removeChangeListener(largeListener);
		}
		largeListener = null;
		super.dispose();
	}
	
	/**
	 * @return the x.
	 */
	public Float getX() {
		return x;
	}

	private void setX(final Float x) {
		this.x = x;
	}
	
	private class XListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setX((Float) e.getParams()[0]);
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
	 * @return the y.
	 */
	public Float getY() {
		return y;
	}

	private void setY(final Float y) {
		this.y = y;
	}
	
	private class YListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setY((Float) e.getParams()[0]);
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
	 * @return the rx.
	 */
	public Float getRx() {
		return rx;
	}

	private void setRx(final Float rx) {
		this.rx = rx;
	}
	
	private class RxListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setRx((Float) e.getParams()[0]);
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
	 * @return the ry.
	 */
	public Float getRy() {
		return ry;
	}

	private void setRy(final Float ry) {
		this.ry = ry;
	}
	
	private class RyListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setRy((Float) e.getParams()[0]);
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
	 * @return the r.
	 */
	public Float getR() {
		return r;
	}

	private void setR(final Float r) {
		this.r = r;
	}
	
	private class RListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setR((Float) e.getParams()[0]);
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
	 * @return the sweep.
	 */
	public Boolean isSweep() {
		return sweep;
	}

	private void setSweep(final Boolean sweep) {
		this.sweep = sweep;
	}
	
	private class SweepListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setSweep((Boolean) e.getParams()[0]);
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
	 * @return the large.
	 */
	public Boolean isLarge() {
		return large;
	}

	private void setLarge(final Boolean large) {
		this.large = large;
	}
	
	private class LargeListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setLarge((Boolean) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	public void process(CFRule rule) {
		rule.getPath().arcTo(x, y, rx, ry, r, large, sweep);
	}
}

