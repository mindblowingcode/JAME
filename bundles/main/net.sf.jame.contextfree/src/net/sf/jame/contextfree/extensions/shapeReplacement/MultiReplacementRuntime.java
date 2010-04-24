/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeReplacement;

import net.sf.jame.contextfree.cfdg.replacement.MultiReplacementRuntimeElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;

/**
 * @author Andrea Medeghini
 */
public class MultiReplacementRuntime<T extends MultiReplacementConfig> extends ShapeReplacementExtensionRuntime<T> {
	private MultiReplacementRuntimeElement multiReplacementElement;
	private MultiReplacementElementListener multiReplacementElementListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		multiReplacementElement = new MultiReplacementRuntimeElement(getConfig().getMultiReplacementElement());
		multiReplacementElementListener = new MultiReplacementElementListener();
		getConfig().getMultiReplacementElement().addChangeListener(multiReplacementElementListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (multiReplacementElementListener != null)) {
			getConfig().getMultiReplacementElement().removeChangeListener(multiReplacementElementListener);
		}
		multiReplacementElementListener = null;
		super.dispose();
	}
	
	private class MultiReplacementElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}

	/**
	 * @see net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionRuntime#draw(net.sf.jame.contextfree.renderer.ContextFreeContext)
	 */
	@Override
	public void draw(ContextFreeContext contextFreeContext) {
		multiReplacementElement.draw(contextFreeContext);
	}

	/**
	 * @see net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionRuntime#prepare(net.sf.jame.contextfree.renderer.ContextFreeContext)
	 */
	@Override
	public void prepare(ContextFreeContext contextFreeContext) {
		multiReplacementElement.prepare(contextFreeContext);
	}
}
