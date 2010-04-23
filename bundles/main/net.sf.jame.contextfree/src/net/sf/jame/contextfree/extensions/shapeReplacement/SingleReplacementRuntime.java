/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeReplacement;

import net.sf.jame.contextfree.cfdg.replacement.SingleReplacementRuntimeElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionRuntime;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;

/**
 * @author Andrea Medeghini
 */
public class SingleReplacementRuntime<T extends SingleReplacementConfig> extends ShapeReplacementExtensionRuntime<T> {
	private SingleReplacementRuntimeElement singleReplacementElement;
	private SingleReplacementElementListener singleReplacementElementListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		singleReplacementElement = new SingleReplacementRuntimeElement(getConfig().getSingleReplacementElement());
		singleReplacementElementListener = new SingleReplacementElementListener();
		getConfig().getSingleReplacementElement().addChangeListener(singleReplacementElementListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (singleReplacementElementListener != null)) {
			getConfig().getSingleReplacementElement().removeChangeListener(singleReplacementElementListener);
		}
		singleReplacementElementListener = null;
		super.dispose();
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
