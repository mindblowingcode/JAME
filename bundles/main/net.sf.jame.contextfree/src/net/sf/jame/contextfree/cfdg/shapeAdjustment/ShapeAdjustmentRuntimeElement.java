/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.shapeAdjustment;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionConfig;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionRuntime;
import net.sf.jame.core.common.ExtensionReferenceElement;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;

/**
 * @author Andrea Medeghini
 */
 public class ShapeAdjustmentRuntimeElement extends RuntimeElement {
	private ShapeAdjustmentConfigElement shapeAdjustmentElement;
	private ShapeAdjustmentExtensionRuntime<?> extensionRuntime;
	private ExtensionListener extensionListener;

	/**
	 * Constructs a new ShapeAdjustmentRuntimeElement.
	 * 
	 * @param registry
	 * @param ShapeAdjustmentRuntimeElementElement
	 */
	public ShapeAdjustmentRuntimeElement(final ShapeAdjustmentConfigElement shapeAdjustmentElement) {
		if (shapeAdjustmentElement == null) {
			throw new IllegalArgumentException("shapeAdjustmentElement is null");
		}
		this.shapeAdjustmentElement = shapeAdjustmentElement;
		createRuntime(shapeAdjustmentElement.getExtensionReference());
		extensionListener = new ExtensionListener();
		shapeAdjustmentElement.getExtensionElement().addChangeListener(extensionListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((shapeAdjustmentElement != null) && (extensionListener != null)) {
			shapeAdjustmentElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if (extensionRuntime != null) {
			extensionRuntime.dispose();
			extensionRuntime = null;
		}
		shapeAdjustmentElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean shapeAdjustmentChanged = false;
		shapeAdjustmentChanged |= (extensionRuntime != null) && extensionRuntime.isChanged();
		return super.isChanged() || shapeAdjustmentChanged;
	}

	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference) {
		try {
			if (reference != null) {
				final ShapeAdjustmentExtensionRuntime extensionRuntime = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension(reference.getExtensionId()).createExtensionRuntime();
				final ShapeAdjustmentExtensionConfig extensionConfig = reference.getExtensionConfig();
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
	 * @return the ShapeAdjustmentRuntimeElementRuntime
	 */
	public ShapeAdjustmentExtensionRuntime<?> getExtensionRuntime() {
		return extensionRuntime;
	}

	private void setExtensionRuntime(final ShapeAdjustmentExtensionRuntime<?> extensionRuntime) {
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
					createRuntime((ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig>) e.getParams()[0]);
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
