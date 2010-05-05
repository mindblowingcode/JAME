/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathOperation;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentRuntimeElement;
import net.sf.jame.contextfree.cfdg.pathOperation.extension.PathOperationExtensionConfig;
import net.sf.jame.contextfree.cfdg.pathOperation.extension.PathOperationExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeLimits;
import net.sf.jame.contextfree.renderer.ContextFreeNode;
import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.core.common.ExtensionReferenceElement;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.config.ListRuntimeElement;
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
	private ListRuntimeElement<PathAdjustmentRuntimeElement> pathAdjustmentListElement;
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
		pathAdjustmentListElement = new ListRuntimeElement<PathAdjustmentRuntimeElement>();
		for (int i = 0; i < pathOperationElement.getPathAdjustmentConfigElementCount(); i++) {
			pathAdjustmentListElement.appendElement(new PathAdjustmentRuntimeElement(pathOperationElement.getPathAdjustmentConfigElement(i)));
		}
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
		pathAdjustmentListElement.dispose();
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
	 * @return the PathOperationExtensionRuntimeRuntime
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
	/**
	 * Returns a pathAdjustment element.
	 * 
	 * @param index the pathAdjustment index.
	 * @return the pathAdjustment.
	 */
	public PathAdjustmentRuntimeElement getPathAdjustmentElement(final int index) {
		return pathAdjustmentListElement.getElement(index);
	}

	/**
	 * Returns a pathAdjustment element index.
	 * 
	 * @param pathAdjustmentElement the pathAdjustment element.
	 * @return the index.
	 */
	public int indexOfPathAdjustmentElement(final PathAdjustmentRuntimeElement pathAdjustmentElement) {
		return pathAdjustmentListElement.indexOfElement(pathAdjustmentElement);
	}

	/**
	 * Returns the number of pathAdjustment elements.
	 * 
	 * @return the number of pathAdjustment elements.
	 */
	public int getPathAdjustmentElementCount() {
		return pathAdjustmentListElement.getElementCount();
	}

	private void setPathAdjustmentElement(final int index, PathAdjustmentRuntimeElement element) {
		pathAdjustmentListElement.setElement(index, element);
	}

	private void appendPathAdjustmentElement(final PathAdjustmentRuntimeElement pathAdjustmentElement) {
		pathAdjustmentListElement.appendElement(pathAdjustmentElement);
	}

	private void insertPathAdjustmentElementAfter(final int index, final PathAdjustmentRuntimeElement pathAdjustmentElement) {
		pathAdjustmentListElement.insertElementAfter(index, pathAdjustmentElement);
	}

	private void insertPathAdjustmentElementBefore(final int index, final PathAdjustmentRuntimeElement pathAdjustmentElement) {
		pathAdjustmentListElement.insertElementBefore(index, pathAdjustmentElement);
	}

	private void removePathAdjustmentElement(final int index) {
		pathAdjustmentListElement.removeElement(index);
	}

	private void moveUpPathAdjustmentElement(final int index) {
		pathAdjustmentListElement.moveElementUp(index);
	}

	private void moveDownPathAdjustmentElement(final int index) {
		pathAdjustmentListElement.moveElementDown(index);
	}
	
	private class PathAdjustmentListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ListConfigElement.ELEMENT_ADDED: {
					appendPathAdjustmentElement(new PathAdjustmentRuntimeElement ((PathAdjustmentConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_AFTER: {
					insertPathAdjustmentElementAfter(((Integer) e.getParams()[1]).intValue(), new PathAdjustmentRuntimeElement ((PathAdjustmentConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_BEFORE: {
					insertPathAdjustmentElementBefore(((Integer) e.getParams()[1]).intValue(), new PathAdjustmentRuntimeElement ((PathAdjustmentConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_REMOVED: {
					removePathAdjustmentElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_UP: {
					moveUpPathAdjustmentElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_DOWN: {
					moveDownPathAdjustmentElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_CHANGED: {
					setPathAdjustmentElement(((Integer) e.getParams()[1]).intValue(), new PathAdjustmentRuntimeElement ((PathAdjustmentConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	
	public ContextFreeNode buildNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
		ContextFreeState nodeState = state.clone(); 
		for (int i = 0; i < pathAdjustmentListElement.getElementCount(); i++) {
			PathAdjustmentRuntimeElement pathAdjustmentRuntime = pathAdjustmentListElement.getElement(i);
			pathAdjustmentRuntime.configureState(nodeState);
		}
		if (extensionRuntime != null) {
			return extensionRuntime.buildNode(context, nodeState, limits);
		}
		return null;
	}
}
