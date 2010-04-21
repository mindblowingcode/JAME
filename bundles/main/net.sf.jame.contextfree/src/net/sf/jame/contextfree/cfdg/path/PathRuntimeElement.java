/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.path;

import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
 public class PathRuntimeElement extends RuntimeElement {
	private PathConfigElement pathElement;
	private String name;
	private NameListener nameListener;
	private PathOperationListElementListener pathOperationListElementListener;

	/**
	 * Constructs a new PathRuntimeElement.
	 * 
	 * @param registry
	 * @param PathRuntimeElementElement
	 */
	public PathRuntimeElement(final PathConfigElement pathElement) {
		if (pathElement == null) {
			throw new IllegalArgumentException("pathElement is null");
		}
		this.pathElement = pathElement;
		setName(pathElement.getName());
		nameListener = new NameListener();
		pathElement.getNameElement().addChangeListener(nameListener);
		pathOperationListElementListener = new PathOperationListElementListener();
		pathElement.getPathOperationListElement().addChangeListener(pathOperationListElementListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((pathElement != null) && (nameListener != null)) {
			pathElement.getNameElement().removeChangeListener(nameListener);
		}
		nameListener = null;
		if ((pathElement != null) && (pathOperationListElementListener != null)) {
			pathElement.getPathOperationListElement().removeChangeListener(pathOperationListElementListener);
		}
		pathOperationListElementListener = null;
		pathElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean pathChanged = false;
		return super.isChanged() || pathChanged;
	}

	/**
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	private void setName(final String name) {
		this.name = name;
	}
	
	private class NameListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	private class PathOperationListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}
	
	public void draw(ContextFreeContext contextFreeContext) {
		// TODO Auto-generated method stub
		
	}
}
