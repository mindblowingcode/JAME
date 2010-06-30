/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathAdjustment;

import java.lang.Boolean;
import java.lang.Float;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.extension.PathAdjustmentExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeState;

/**
 * @author Andrea Medeghini
 */
public class CurrentBrightnessPathAdjustmentRuntime extends PathAdjustmentExtensionRuntime<CurrentBrightnessPathAdjustmentConfig> {
	private Float value;
	private ValueListener valueListener;
	private Boolean target;
	private TargetListener targetListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setValue(getConfig().getValue());
		valueListener = new ValueListener();
		getConfig().getValueElement().addChangeListener(valueListener);
		setTarget(getConfig().isTarget());
		targetListener = new TargetListener();
		getConfig().getTargetElement().addChangeListener(targetListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (valueListener != null)) {
			getConfig().getValueElement().removeChangeListener(valueListener);
		}
		valueListener = null;
		if ((getConfig() != null) && (targetListener != null)) {
			getConfig().getTargetElement().removeChangeListener(targetListener);
		}
		targetListener = null;
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
	/**
	 * @return the target.
	 */
	public Boolean isTarget() {
		return target;
	}

	private void setTarget(final Boolean target) {
		this.target = target;
	}
	
	private class TargetListener implements ValueChangeListener {
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
	public void updateState(ContextFreeState state) {
		state.addBrightness(value, target);
	}
}
