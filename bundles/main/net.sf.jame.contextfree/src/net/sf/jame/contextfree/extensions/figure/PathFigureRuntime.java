/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementRuntimeElement;
import net.sf.jame.contextfree.renderer.ComposedShape;
import net.sf.jame.contextfree.renderer.ContextFreeBounds;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreePath;
import net.sf.jame.contextfree.renderer.ContextFreeShape;
import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.config.ListRuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class PathFigureRuntime<T extends PathFigureConfig> extends FigureExtensionRuntime<T> implements ContextFreePath {
	private String name;
	private NameListener nameListener;
	private ListRuntimeElement<PathReplacementRuntimeElement> pathReplacementListElement;
	private PathReplacementListElementListener pathReplacementListElementListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setName(getConfig().getName());
		nameListener = new NameListener();
		getConfig().getNameElement().addChangeListener(nameListener);
		pathReplacementListElement = new ListRuntimeElement<PathReplacementRuntimeElement>();
		for (int i = 0; i < getConfig().getPathReplacementConfigElementCount(); i++) {
			pathReplacementListElement.appendElement(new PathReplacementRuntimeElement(getConfig().getPathReplacementConfigElement(i)));
		}
		pathReplacementListElementListener = new PathReplacementListElementListener();
		getConfig().getPathReplacementListElement().addChangeListener(pathReplacementListElementListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (nameListener != null)) {
			getConfig().getNameElement().removeChangeListener(nameListener);
		}
		nameListener = null;
		if ((getConfig() != null) && (pathReplacementListElementListener != null)) {
			getConfig().getPathReplacementListElement().removeChangeListener(pathReplacementListElementListener);
		}
		pathReplacementListElementListener = null;
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
	 * Returns a pathReplacement element.
	 * 
	 * @param index the pathReplacement index.
	 * @return the pathReplacement.
	 */
	public PathReplacementRuntimeElement getPathReplacementElement(final int index) {
		return pathReplacementListElement.getElement(index);
	}

	/**
	 * Returns a pathReplacement element index.
	 * 
	 * @param pathReplacementElement the pathReplacement element.
	 * @return the index.
	 */
	public int indexOfPathReplacementElement(final PathReplacementRuntimeElement pathReplacementElement) {
		return pathReplacementListElement.indexOfElement(pathReplacementElement);
	}

	/**
	 * Returns the number of pathReplacement elements.
	 * 
	 * @return the number of pathReplacement elements.
	 */
	public int getPathReplacementElementCount() {
		return pathReplacementListElement.getElementCount();
	}

	private void setPathReplacementElement(final int index, PathReplacementRuntimeElement element) {
		pathReplacementListElement.setElement(index, element);
	}

	private void appendPathReplacementElement(final PathReplacementRuntimeElement pathReplacementElement) {
		pathReplacementListElement.appendElement(pathReplacementElement);
	}

	private void insertPathReplacementElementAfter(final int index, final PathReplacementRuntimeElement pathReplacementElement) {
		pathReplacementListElement.insertElementAfter(index, pathReplacementElement);
	}

	private void insertPathReplacementElementBefore(final int index, final PathReplacementRuntimeElement pathReplacementElement) {
		pathReplacementListElement.insertElementBefore(index, pathReplacementElement);
	}

	private void removePathReplacementElement(final int index) {
		pathReplacementListElement.removeElement(index);
	}

	private void moveUpPathReplacementElement(final int index) {
		pathReplacementListElement.moveElementUp(index);
	}

	private void moveDownPathReplacementElement(final int index) {
		pathReplacementListElement.moveElementDown(index);
	}
	
	private class PathReplacementListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ListConfigElement.ELEMENT_ADDED: {
					appendPathReplacementElement(new PathReplacementRuntimeElement ((PathReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_AFTER: {
					insertPathReplacementElementAfter(((Integer) e.getParams()[1]).intValue(), new PathReplacementRuntimeElement ((PathReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_BEFORE: {
					insertPathReplacementElementBefore(((Integer) e.getParams()[1]).intValue(), new PathReplacementRuntimeElement ((PathReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_REMOVED: {
					removePathReplacementElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_UP: {
					moveUpPathReplacementElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_DOWN: {
					moveDownPathReplacementElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_CHANGED: {
					setPathReplacementElement(((Integer) e.getParams()[1]).intValue(), new PathReplacementRuntimeElement ((PathReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	public void register(ContextFreeContext context) {
		context.registerPath(this);
	}

	public ContextFreeShape createShape(ContextFreeContext context, ContextFreeState state, ContextFreeBounds bounds) {
		ComposedShape pathShape = new ComposedShape(state); 
		for (int i = 0; i < pathReplacementListElement.getElementCount(); i++) {
			PathReplacementRuntimeElement pathReplacementRuntime = pathReplacementListElement.getElement(i);
			ContextFreeState newState = state.clone();
			ContextFreeShape shape = pathReplacementRuntime.createShape(context, newState, bounds);
			if (shape != null) {
				pathShape.addShape(shape);
			}
		}
		return pathShape;
	}
}
