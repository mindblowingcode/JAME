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
public class SizePathAdjustmentRuntime extends PathAdjustmentExtensionRuntime<SizePathAdjustmentConfig> {
	private Float scale;
	private ScaleListener scaleListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setScale(getConfig().getScale());
		scaleListener = new ScaleListener();
		getConfig().getScaleElement().addChangeListener(scaleListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (scaleListener != null)) {
			getConfig().getScaleElement().removeChangeListener(scaleListener);
		}
		scaleListener = null;
		super.dispose();
	}
	
	/**
	 * @return the scale.
	 */
	public Float getScale() {
		return scale;
	}

	private void setScale(final Float scale) {
		this.scale = scale;
	}
	
	private class ScaleListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setScale((Float) e.getParams()[0]);
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
		state.scale(scale, scale, 1);
	}
}
