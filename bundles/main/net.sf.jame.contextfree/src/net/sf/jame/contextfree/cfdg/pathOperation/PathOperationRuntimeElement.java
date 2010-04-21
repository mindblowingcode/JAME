/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathOperation;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.pathOperation.extension.PathOperationExtensionConfig;
import net.sf.jame.contextfree.cfdg.pathOperation.extension.PathOperationExtensionRuntime;
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
 public class PathOperationRuntimeElement extends RuntimeElement {
	private PathOperationConfigElement pathOperationElement;
	private PathOperationExtensionRuntime<?> extensionRuntime;
	private ExtensionListener extensionListener;
	private PathAdjustmentListElementListener pathAdjustmentListElementListener;

	/**
	 * Constructs a new PathOperationRuntimeElement.
	 * 
	 * @param registry
	 * @param PathOperationRuntimeElementElement
	 */
	public PathOperationRuntimeElement(final PathOperationConfigElement pathOperationElement) {
		if (pathOperationElement == null) {
			throw new IllegalArgumentException("pathOperationElement is null");
		}
		this.pathOperationElement = pathOperationElement;
		createRuntime(pathOperationElement.getExtensionReference());
		extensionListener = new ExtensionListener();
		pathOperationElement.getExtensionElement().addChangeListener(extensionListener);
		pathAdjustmentListElementListener = new PathAdjustmentListElementListener();
		pathOperationElement.getPathAdjustmentListElement().addChangeListener(pathAdjustmentListElementListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((pathOperationElement != null) && (extensionListener != null)) {
			pathOperationElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if ((pathOperationElement != null) && (pathAdjustmentListElementListener != null)) {
			pathOperationElement.getPathAdjustmentListElement().removeChangeListener(pathAdjustmentListElementListener);
		}
		pathAdjustmentListElementListener = null;
		if (extensionRuntime != null) {
			extensionRuntime.dispose();
			extensionRuntime = null;
		}
		pathOperationElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean pathOperationChanged = false;
		pathOperationChanged |= (extensionRuntime != null) && extensionRuntime.isChanged();
		return super.isChanged() || pathOperationChanged;
	}

	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<PathOperationExtensionConfig> reference) {
		try {
			if (reference != null) {
				final PathOperationExtensionRuntime extensionRuntime = ContextFreeRegistry.getInstance().getPathOperationExtension(reference.getExtensionId()).createExtensionRuntime();
				final PathOperationExtensionConfig extensionConfig = reference.getExtensionConfig();
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
	 * @return the PathOperationRuntimeElementRuntime
	 */
	public PathOperationExtensionRuntime<?> getExtensionRuntime() {
		return extensionRuntime;
	}

	private void setExtensionRuntime(final PathOperationExtensionRuntime<?> extensionRuntime) {
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
					createRuntime((ConfigurableExtensionReference<PathOperationExtensionConfig>) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	private class PathAdjustmentListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}
}
