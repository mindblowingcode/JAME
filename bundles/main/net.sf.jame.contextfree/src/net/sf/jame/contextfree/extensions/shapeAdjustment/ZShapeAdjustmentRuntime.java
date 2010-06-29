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
public class ZShapeAdjustmentRuntime extends ShapeAdjustmentExtensionRuntime<ZShapeAdjustmentConfig> {
	private Float value;
	private ValueListener valueListener;
	private float delta;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setValue(getConfig().getValue());
		valueListener = new ValueListener();
		getConfig().getValueElement().addChangeListener(valueListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (valueListener != null)) {
			getConfig().getValueElement().removeChangeListener(valueListener);
		}
		valueListener = null;
		super.dispose();
	}
	
	/**
	 * @return the value.
	 */
	public Float getValue() {
		return value;
	}

	private void setValue(final Float value) {
		this.value = value;
	}
	
	private class ValueListener implements ValueChangeListener {
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
		if (times == 0) {
			state.setZ(value);
			return;
		}
		delta = (state.getZ() - value) / times;
	}

	@Override
	public void updateState(ContextFreeState state, int time) {
		// TODO Auto-generated method stub
		state.setZ(delta * time);
	}
}
