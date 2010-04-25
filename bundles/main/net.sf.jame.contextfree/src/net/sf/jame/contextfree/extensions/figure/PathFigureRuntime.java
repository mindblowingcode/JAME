/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.cfdg.pathOperation.PathOperationConfigElement;
import net.sf.jame.contextfree.cfdg.pathOperation.PathOperationRuntimeElement;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.config.ListRuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class PathFigureRuntime<T extends PathFigureConfig> extends FigureExtensionRuntime<T> {
	private String name;
	private NameListener nameListener;
	private ListRuntimeElement<PathOperationRuntimeElement> pathOperationListElement;
	private PathOperationListElementListener pathOperationListElementListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setName(getConfig().getName());
		nameListener = new NameListener();
		getConfig().getNameElement().addChangeListener(nameListener);
		pathOperationListElement = new ListRuntimeElement<PathOperationRuntimeElement>();
		for (int i = 0; i < getConfig().getPathOperationConfigElementCount(); i++) {
			pathOperationListElement.appendElement(new PathOperationRuntimeElement(getConfig().getPathOperationConfigElement(i)));
		}
		pathOperationListElementListener = new PathOperationListElementListener();
		getConfig().getPathOperationListElement().addChangeListener(pathOperationListElementListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (nameListener != null)) {
			getConfig().getNameElement().removeChangeListener(nameListener);
		}
		nameListener = null;
		if ((getConfig() != null) && (pathOperationListElementListener != null)) {
			getConfig().getPathOperationListElement().removeChangeListener(pathOperationListElementListener);
		}
		pathOperationListElementListener = null;
		super.dispose();
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
	/**
	 * Returns a pathOperation element.
	 * 
	 * @param index the pathOperation index.
	 * @return the pathOperation.
	 */
	public PathOperationRuntimeElement getPathOperationElement(final int index) {
		return pathOperationListElement.getElement(index);
	}

	/**
	 * Returns a pathOperation element index.
	 * 
	 * @param pathOperationElement the pathOperation element.
	 * @return the index.
	 */
	public int indexOfPathOperationElement(final PathOperationRuntimeElement pathOperationElement) {
		return pathOperationListElement.indexOfElement(pathOperationElement);
	}

	/**
	 * Returns the number of pathOperation elements.
	 * 
	 * @return the number of pathOperation elements.
	 */
	public int getPathOperationElementCount() {
		return pathOperationListElement.getElementCount();
	}

	private void setPathOperationElement(final int index, PathOperationRuntimeElement element) {
		pathOperationListElement.setElement(index, element);
	}

	private void appendPathOperationElement(final PathOperationRuntimeElement pathOperationElement) {
		pathOperationListElement.appendElement(pathOperationElement);
	}

	private void insertPathOperationElementAfter(final int index, final PathOperationRuntimeElement pathOperationElement) {
		pathOperationListElement.insertElementAfter(index, pathOperationElement);
	}

	private void insertPathOperationElementBefore(final int index, final PathOperationRuntimeElement pathOperationElement) {
		pathOperationListElement.insertElementBefore(index, pathOperationElement);
	}

	private void removePathOperationElement(final int index) {
		pathOperationListElement.removeElement(index);
	}

	private void moveUpPathOperationElement(final int index) {
		pathOperationListElement.moveElementUp(index);
	}

	private void moveDownPathOperationElement(final int index) {
		pathOperationListElement.moveElementDown(index);
	}
	
	private class PathOperationListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ListConfigElement.ELEMENT_ADDED: {
					appendPathOperationElement(new PathOperationRuntimeElement ((PathOperationConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_AFTER: {
					insertPathOperationElementAfter(((Integer) e.getParams()[1]).intValue(), new PathOperationRuntimeElement ((PathOperationConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_BEFORE: {
					insertPathOperationElementBefore(((Integer) e.getParams()[1]).intValue(), new PathOperationRuntimeElement ((PathOperationConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_REMOVED: {
					removePathOperationElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_UP: {
					moveUpPathOperationElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_DOWN: {
					moveDownPathOperationElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_CHANGED: {
					setPathOperationElement(((Integer) e.getParams()[1]).intValue(), new PathOperationRuntimeElement ((PathOperationConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	
	public void draw(ContextFreeContext contextFreeContext) {
		for (int i = 0; i < pathOperationListElement.getElementCount(); i++) {
			PathOperationRuntimeElement pathOperationRuntime = pathOperationListElement.getElement(i); 
			pathOperationRuntime.draw(contextFreeContext);
		}
	}
	
	public void prepare(ContextFreeContext contextFreeContext) {
		for (int i = 0; i < pathOperationListElement.getElementCount(); i++) {
			PathOperationRuntimeElement pathOperationRuntime = pathOperationListElement.getElement(i); 
			pathOperationRuntime.prepare(contextFreeContext);
		}
	}

	@Override
	public void registerFigure(ContextFreeContext contextFreeContext) {
		contextFreeContext.registerPath(this);
	}
}
