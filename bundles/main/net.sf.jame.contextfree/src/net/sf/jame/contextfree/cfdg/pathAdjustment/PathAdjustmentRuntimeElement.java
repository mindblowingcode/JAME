/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathAdjustment;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.pathAdjustment.extension.PathAdjustmentExtensionConfig;
import net.sf.jame.contextfree.cfdg.pathAdjustment.extension.PathAdjustmentExtensionRuntime;
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
 public class PathAdjustmentRuntimeElement extends RuntimeElement {
	private PathAdjustmentConfigElement pathAdjustmentElement;
	private PathAdjustmentExtensionRuntime<?> extensionRuntime;
	private ExtensionListener extensionListener;

	/**
	 * Constructs a new PathAdjustmentRuntimeElement.
	 * 
	 * @param registry
	 * @param PathAdjustmentRuntimeElementElement
	 */
	public PathAdjustmentRuntimeElement(final PathAdjustmentConfigElement pathAdjustmentElement) {
		if (pathAdjustmentElement == null) {
			throw new IllegalArgumentException("pathAdjustmentElement is null");
		}
		this.pathAdjustmentElement = pathAdjustmentElement;
		createRuntime(pathAdjustmentElement.getExtensionReference());
		extensionListener = new ExtensionListener();
		pathAdjustmentElement.getExtensionElement().addChangeListener(extensionListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((pathAdjustmentElement != null) && (extensionListener != null)) {
			pathAdjustmentElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if (extensionRuntime != null) {
			extensionRuntime.dispose();
			extensionRuntime = null;
		}
		pathAdjustmentElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean pathAdjustmentChanged = false;
		pathAdjustmentChanged |= (extensionRuntime != null) && extensionRuntime.isChanged();
		return super.isChanged() || pathAdjustmentChanged;
	}

	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference) {
		try {
			if (reference != null) {
				final PathAdjustmentExtensionRuntime extensionRuntime = ContextFreeRegistry.getInstance().getPathAdjustmentExtension(reference.getExtensionId()).createExtensionRuntime();
				final PathAdjustmentExtensionConfig extensionConfig = reference.getExtensionConfig();
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
	 * @return the PathAdjustmentRuntimeElementRuntime
	 */
	public PathAdjustmentExtensionRuntime<?> getExtensionRuntime() {
		return extensionRuntime;
	}

	private void setExtensionRuntime(final PathAdjustmentExtensionRuntime<?> extensionRuntime) {
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
					createRuntime((ConfigurableExtensionReference<PathAdjustmentExtensionConfig>) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
}
