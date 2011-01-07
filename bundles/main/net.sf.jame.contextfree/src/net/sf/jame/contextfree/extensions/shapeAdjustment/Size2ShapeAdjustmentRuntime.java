/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeAdjustment;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class Size2ShapeAdjustmentRuntime extends ShapeAdjustmentExtensionRuntime<Size2ShapeAdjustmentConfig> {
	private Float scaleX;
	private ScaleXListener scaleXListener;
	private Float scaleY;
	private ScaleYListener scaleYListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setScaleX(getConfig().getScaleX());
		scaleXListener = new ScaleXListener();
		getConfig().getScaleXElement().addChangeListener(scaleXListener);
		setScaleY(getConfig().getScaleY());
		scaleYListener = new ScaleYListener();
		getConfig().getScaleYElement().addChangeListener(scaleYListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (scaleXListener != null)) {
			getConfig().getScaleXElement().removeChangeListener(scaleXListener);
		}
		scaleXListener = null;
		if ((getConfig() != null) && (scaleYListener != null)) {
			getConfig().getScaleYElement().removeChangeListener(scaleYListener);
		}
		scaleYListener = null;
		super.dispose();
	}
	
	/**
	 * @return the scaleX.
	 */
	public Float getScaleX() {
		return scaleX;
	}

	private void setScaleX(final Float scaleX) {
		this.scaleX = scaleX;
	}
	
	private class ScaleXListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setScaleX((Float) e.getParams()[0]);
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
	 * @return the scaleY.
	 */
	public Float getScaleY() {
		return scaleY;
	}

	private void setScaleY(final Float scaleY) {
		this.scaleY = scaleY;
	}
	
	private class ScaleYListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setScaleY((Float) e.getParams()[0]);
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
	public void updateState(ContextFreeState state) {
		state.scale(scaleX, scaleY, 1);
	}

	public boolean isSizeChange() {
		return true;
	}

	public float getSize() {
		return Math.max(scaleX, scaleY);
	}
}
