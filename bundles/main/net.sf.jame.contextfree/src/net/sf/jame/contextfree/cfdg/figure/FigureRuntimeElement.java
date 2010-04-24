/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.figure;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
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
 public class FigureRuntimeElement extends RuntimeElement {
	private FigureConfigElement figureElement;
	private FigureExtensionRuntime<?> extensionRuntime;
	private ExtensionListener extensionListener;

	/**
	 * Constructs a new FigureRuntimeElement.
	 * 
	 * @param registry
	 * @param FigureRuntimeElementElement
	 */
	public FigureRuntimeElement(final FigureConfigElement figureElement) {
		if (figureElement == null) {
			throw new IllegalArgumentException("figureElement is null");
		}
		this.figureElement = figureElement;
		createRuntime(figureElement.getExtensionReference());
		extensionListener = new ExtensionListener();
		figureElement.getExtensionElement().addChangeListener(extensionListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((figureElement != null) && (extensionListener != null)) {
			figureElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if (extensionRuntime != null) {
			extensionRuntime.dispose();
			extensionRuntime = null;
		}
		figureElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean figureChanged = false;
		figureChanged |= (extensionRuntime != null) && extensionRuntime.isChanged();
		return super.isChanged() || figureChanged;
	}

	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<FigureExtensionConfig> reference) {
		try {
			if (reference != null) {
				final FigureExtensionRuntime extensionRuntime = ContextFreeRegistry.getInstance().getFigureExtension(reference.getExtensionId()).createExtensionRuntime();
				final FigureExtensionConfig extensionConfig = reference.getExtensionConfig();
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
	 * @return the FigureExtensionRuntimeRuntime
	 */
	public FigureExtensionRuntime<?> getExtensionRuntime() {
		return extensionRuntime;
	}

	private void setExtensionRuntime(final FigureExtensionRuntime<?> extensionRuntime) {
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
					createRuntime((ConfigurableExtensionReference<FigureExtensionConfig>) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	public void registerFigure(ContextFreeContext contextFreeContext) {
		if (extensionRuntime != null) {
			extensionRuntime.registerFigure(contextFreeContext);
		}
	}
}
