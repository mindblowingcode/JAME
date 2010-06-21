/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathAdjustment;

import java.lang.Float;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.extension.PathAdjustmentExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeState;

/**
 * @author Andrea Medeghini
 */
public class FlipPathAdjustmentRuntime extends PathAdjustmentExtensionRuntime<FlipPathAdjustmentConfig> {
	private Float flipX;
	private FlipXListener flipXListener;
	private Float flipY;
	private FlipYListener flipYListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setFlipX(getConfig().getFlipX());
		flipXListener = new FlipXListener();
		getConfig().getFlipXElement().addChangeListener(flipXListener);
		setFlipY(getConfig().getFlipY());
		flipYListener = new FlipYListener();
		getConfig().getFlipYElement().addChangeListener(flipYListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (flipXListener != null)) {
			getConfig().getFlipXElement().removeChangeListener(flipXListener);
		}
		flipXListener = null;
		if ((getConfig() != null) && (flipYListener != null)) {
			getConfig().getFlipYElement().removeChangeListener(flipYListener);
		}
		flipYListener = null;
		super.dispose();
	}
	
	/**
	 * @return the flipX.
	 */
	public Float getFlipX() {
		return flipX;
	}

	private void setFlipX(final Float flipX) {
		this.flipX = flipX;
	}
	
	private class FlipXListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
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
	 * @return the flipY.
	 */
	public Float getFlipY() {
		return flipY;
	}

	private void setFlipY(final Float flipY) {
		this.flipY = flipY;
	}
	
	private class FlipYListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
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
	public void configureState(ContextFreeState state, int times) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateState(ContextFreeState state, int time) {
		// TODO Auto-generated method stub
		
	}
}