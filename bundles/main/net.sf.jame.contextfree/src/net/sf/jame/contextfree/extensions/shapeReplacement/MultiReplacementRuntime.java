/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeReplacement;

import net.sf.jame.contextfree.cfdg.replacement.MultiReplacementRuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class MultiReplacementRuntime<T extends MultiReplacementConfig> extends ConfigurableExtensionRuntime<T> {
	private MultiReplacementRuntimeElement multiReplacementElement;
	private MultiReplacementElementListener multiReplacementElementListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		multiReplacementElementListener = new MultiReplacementElementListener();
		getConfig().getReplacementElement().addChangeListener(multiReplacementElementListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (multiReplacementElementListener != null)) {
			getConfig().getReplacementElement().removeChangeListener(multiReplacementElementListener);
		}
		multiReplacementElementListener = null;
		super.dispose();
	}
	
	/**
	 * @return
	 */
	public MultiReplacementRuntimeElement getMultiReplacement() {
		return multiReplacementElement;
	}
	
	private class MultiReplacementElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}
}
