/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathAdjustment;

import net.sf.jame.contextfree.cfdg.pathAdjustment.extension.PathAdjustmentExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeState;
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
	public void updateState(ContextFreeState state) {
		state.skew(shearX, shearY);
	}
}
