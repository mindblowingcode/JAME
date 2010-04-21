/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeReplacement;

import net.sf.jame.contextfree.cfdg.replacement.SingleReplacementRuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class SingleReplacementRuntime<T extends SingleReplacementConfig> extends ConfigurableExtensionRuntime<T> {
	private SingleReplacementRuntimeElement singleReplacementElement;
	private SingleReplacementElementListener singleReplacementElementListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		singleReplacementElementListener = new SingleReplacementElementListener();
		getConfig().getReplacementElement().addChangeListener(singleReplacementElementListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (singleReplacementElementListener != null)) {
			getConfig().getReplacementElement().removeChangeListener(singleReplacementElementListener);
		}
		singleReplacementElementListener = null;
		super.dispose();
	}
	
	/**
	 * @return
	 */
	public SingleReplacementRuntimeElement getSingleReplacement() {
		return singleReplacementElement;
	}
	
	private class SingleReplacementElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}
}
