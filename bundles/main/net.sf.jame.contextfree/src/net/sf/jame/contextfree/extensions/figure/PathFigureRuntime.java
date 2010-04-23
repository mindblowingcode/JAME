/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.cfdg.path.PathRuntimeElement;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;

/**
 * @author Andrea Medeghini
 */
public class PathFigureRuntime<T extends PathFigureConfig> extends FigureExtensionRuntime<T> {
	private PathRuntimeElement pathElement;
	private PathElementListener pathElementListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		pathElement = new PathRuntimeElement(getConfig().getPathElement());
		pathElementListener = new PathElementListener();
		getConfig().getPathElement().addChangeListener(pathElementListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (pathElementListener != null)) {
			getConfig().getPathElement().removeChangeListener(pathElementListener);
		}
		pathElementListener = null;
		super.dispose();
	}
	
	private class PathElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}

	@Override
	public void registerFigure(ContextFreeContext contextFreeContext) {
		contextFreeContext.registerPath(pathElement);
	}
}
