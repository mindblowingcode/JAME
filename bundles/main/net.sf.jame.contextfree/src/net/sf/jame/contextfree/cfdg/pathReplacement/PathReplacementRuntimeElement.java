/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathReplacement;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionConfig;
import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeBounds;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeShape;
import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.core.common.ExtensionReferenceElement;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;

/**
 * @author Andrea Medeghini
 */
 public class PathReplacementRuntimeElement extends RuntimeElement {
	private PathReplacementConfigElement pathReplacementElement;
	private PathReplacementExtensionRuntime<?> extensionRuntime;
	private ExtensionListener extensionListener;

	/**
	 * Constructs a new PathReplacementRuntimeElement.
	 * 
	 * @param registry
	 * @param PathReplacementRuntimeElementElement
	 */
	public PathReplacementRuntimeElement(final PathReplacementConfigElement pathReplacementElement) {
		if (pathReplacementElement == null) {
			throw new IllegalArgumentException("pathReplacementElement is null");
		}
		this.pathReplacementElement = pathReplacementElement;
		createRuntime(pathReplacementElement.getExtensionReference());
		extensionListener = new ExtensionListener();
		pathReplacementElement.getExtensionElement().addChangeListener(extensionListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((pathReplacementElement != null) && (extensionListener != null)) {
			pathReplacementElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if (extensionRuntime != null) {
			extensionRuntime.dispose();
			extensionRuntime = null;
		}
		pathReplacementElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean pathReplacementChanged = false;
		pathReplacementChanged |= (extensionRuntime != null) && extensionRuntime.isChanged();
		return super.isChanged() || pathReplacementChanged;
	}

	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<PathReplacementExtensionConfig> reference) {
		try {
			if (reference != null) {
				final PathReplacementExtensionRuntime extensionRuntime = ContextFreeRegistry.getInstance().getPathReplacementExtension(reference.getExtensionId()).createExtensionRuntime();
				final PathReplacementExtensionConfig extensionConfig = reference.getExtensionConfig();
				extensionRuntime.setConfig(extensionConfig);
				setExtensionRuntime(extensionRuntime);
			}
			else {
				setExtensionRuntime(null);
			}
		}
		catch (final ExtensionNotFoundException e) {
			e.printStackTrace();
		}
		catch (final ExtensionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the PathReplacementExtensionRuntimeRuntime
	 */
	public PathReplacementExtensionRuntime<?> getExtensionRuntime() {
		return extensionRuntime;
	}

	private void setExtensionRuntime(final PathReplacementExtensionRuntime<?> extensionRuntime) {
		if (this.extensionRuntime != null) {
			this.extensionRuntime.dispose();
		}
		this.extensionRuntime = extensionRuntime;
	}
	
	private class ExtensionListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		@SuppressWarnings("unchecked")
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED: {
					createRuntime((ConfigurableExtensionReference<PathReplacementExtensionConfig>) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	public ContextFreeShape createShape(ContextFreeContext context, ContextFreeState state, ContextFreeBounds globalBounds, ContextFreeBounds shapeBounds) {
		if (extensionRuntime != null) {
			return extensionRuntime.createShape(context, state, globalBounds, shapeBounds);
		}
		return null;
	}
}
