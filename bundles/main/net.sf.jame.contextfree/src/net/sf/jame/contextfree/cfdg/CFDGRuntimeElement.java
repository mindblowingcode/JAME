/*
 * JAME 6.1 
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2010 Andrea Medeghini
 * http://andreamedeghini.users.sourceforge.net
 *
 * This file is part of JAME.
 *
 * JAME is an application for creating fractals and other graphics artifacts.
 *
 * JAME is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JAME is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAME.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.sf.jame.contextfree.cfdg;

import net.sf.jame.contextfree.cfdg.path.PathConfigElement;
import net.sf.jame.contextfree.cfdg.path.PathRuntimeElement;
import net.sf.jame.contextfree.cfdg.rule.RuleConfigElement;
import net.sf.jame.contextfree.cfdg.rule.RuleRuntimeElement;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.config.ListRuntimeElement;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.core.util.Color32bit;

/**
 * @author Andrea Medeghini
 */
public class CFDGRuntimeElement extends RuntimeElement {
	private CFDGConfigElement cfdgElement;
	private String startshape;
	private StartshapeListener startshapeListener;
	private Float x;
	private XListener xListener;
	private Float y;
	private YListener yListener;
	private Float width;
	private WidthListener widthListener;
	private Float height;
	private HeightListener heightListener;
	private Float tileWidth;
	private TileWidthListener tileWidthListener;
	private Float tileHeight;
	private TileHeightListener tileHeightListener;
	private Color32bit background;
	private BackgroundListener backgroundListener;
	private FigureListElementListener figureListElementListener;
	private final ListRuntimeElement<FigureRuntimeElement> figureListElement = new ListRuntimeElement<FigureRuntimeElement>(0);

	/**
	 * Constructs a new CFDGRuntimeElement.
	 * 
	 * @param registry
	 * @param CFDGRuntimeElementElement
	 */
	public CFDGRuntimeElement(final CFDGConfigElement cfdgElement) {
		if (cfdgElement == null) {
			throw new IllegalArgumentException("cfdgElement is null");
		}
		this.cfdgElement = cfdgElement;
		setStartshape(cfdgElement.getStartshape());
		startshapeListener = new StartshapeListener();
		cfdgElement.getStartshapeElement().addChangeListener(startshapeListener);
		setX(cfdgElement.getX());
		xListener = new XListener();
		cfdgElement.getXElement().addChangeListener(xListener);
		setY(cfdgElement.getY());
		yListener = new YListener();
		cfdgElement.getYElement().addChangeListener(yListener);
		setWidth(cfdgElement.getWidth());
		widthListener = new WidthListener();
		cfdgElement.getWidthElement().addChangeListener(widthListener);
		setHeight(cfdgElement.getHeight());
		heightListener = new HeightListener();
		cfdgElement.getHeightElement().addChangeListener(heightListener);
		setTileWidth(cfdgElement.getTileWidth());
		tileWidthListener = new TileWidthListener();
		cfdgElement.getTileWidthElement().addChangeListener(tileWidthListener);
		setTileHeight(cfdgElement.getTileHeight());
		tileHeightListener = new TileHeightListener();
		cfdgElement.getTileHeightElement().addChangeListener(tileHeightListener);
		setBackground(cfdgElement.getBackground());
		backgroundListener = new BackgroundListener();
		cfdgElement.getBackgroundElement().addChangeListener(backgroundListener);
		createFigures(cfdgElement);
		figureListElementListener = new FigureListElementListener();
		cfdgElement.getFigureListElement().addChangeListener(figureListElementListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((cfdgElement != null) && (startshapeListener != null)) {
			cfdgElement.getStartshapeElement().removeChangeListener(startshapeListener);
		}
		startshapeListener = null;
		if ((cfdgElement != null) && (xListener != null)) {
			cfdgElement.getXElement().removeChangeListener(xListener);
		}
		xListener = null;
		if ((cfdgElement != null) && (yListener != null)) {
			cfdgElement.getYElement().removeChangeListener(yListener);
		}
		yListener = null;
		if ((cfdgElement != null) && (widthListener != null)) {
			cfdgElement.getWidthElement().removeChangeListener(widthListener);
		}
		widthListener = null;
		if ((cfdgElement != null) && (heightListener != null)) {
			cfdgElement.getHeightElement().removeChangeListener(heightListener);
		}
		heightListener = null;
		if ((cfdgElement != null) && (tileWidthListener != null)) {
			cfdgElement.getTileWidthElement().removeChangeListener(tileWidthListener);
		}
		tileWidthListener = null;
		if ((cfdgElement != null) && (tileHeightListener != null)) {
			cfdgElement.getTileHeightElement().removeChangeListener(tileHeightListener);
		}
		tileHeightListener = null;
		if ((cfdgElement != null) && (backgroundListener != null)) {
			cfdgElement.getBackgroundElement().removeChangeListener(backgroundListener);
		}
		backgroundListener = null;
		if ((cfdgElement != null) && (figureListElementListener != null)) {
			cfdgElement.getFigureListElement().removeChangeListener(figureListElementListener);
		}
		figureListElementListener = null;
		cfdgElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean cfdgChanged = false;
		return super.isChanged() || cfdgChanged;
	}

	/**
	 * @return the startshape.
	 */
	public String getStartshape() {
		return startshape;
	}

	private void setStartshape(final String startshape) {
		this.startshape = startshape;
	}

	private class StartshapeListener implements ValueChangeListener {
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
	 * @return the x.
	 */
	public Float getX() {
		return x;
	}

	private void setX(final Float x) {
		this.x = x;
	}

	private class XListener implements ValueChangeListener {
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
	 * @return the y.
	 */
	public Float getY() {
		return y;
	}

	private void setY(final Float y) {
		this.y = y;
	}

	private class YListener implements ValueChangeListener {
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
	 * @return the width.
	 */
	public Float getWidth() {
		return width;
	}

	private void setWidth(final Float width) {
		this.width = width;
	}

	private class WidthListener implements ValueChangeListener {
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
	 * @return the height.
	 */
	public Float getHeight() {
		return height;
	}

	private void setHeight(final Float height) {
		this.height = height;
	}

	private class HeightListener implements ValueChangeListener {
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
	 * @return the tileWidth.
	 */
	public Float getTileWidth() {
		return tileWidth;
	}

	private void setTileWidth(final Float tileWidth) {
		this.tileWidth = tileWidth;
	}

	private class TileWidthListener implements ValueChangeListener {
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
	 * @return the tileHeight.
	 */
	public Float getTileHeight() {
		return tileHeight;
	}

	private void setTileHeight(final Float tileHeight) {
		this.tileHeight = tileHeight;
	}

	private class TileHeightListener implements ValueChangeListener {
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
	 * @return the background.
	 */
	public Color32bit getBackground() {
		return background;
	}

	private void setBackground(final Color32bit background) {
		this.background = background;
	}

	private class BackgroundListener implements ValueChangeListener {
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

	private void createFigures(final CFDGConfigElement cfdgElement) {
		for (int i = 0; i < cfdgElement.getFigureConfigElementCount(); i++) {
			final FigureConfigElement figureElement = cfdgElement.getFigureConfigElement(i);
			if (figureElement.getClassId().equals(PathConfigElement.CLASS_ID)) {
				final FigureRuntimeElement figure = new PathRuntimeElement((PathConfigElement) figureElement);
				appendFigure(figure);
			} else if (figureElement.getClassId().equals(RuleConfigElement.CLASS_ID)) {
				final FigureRuntimeElement figure = new RuleRuntimeElement((RuleConfigElement) figureElement);
				appendFigure(figure);
			}
		}
	}

	/**
	 * Returns a figure.
	 * 
	 * @param index the figure index.
	 * @return the figure.
	 */
	public FigureRuntimeElement getFigure(final int index) {
		return figureListElement.getElement(index);
	}

	/**
	 * Returns the figure index.
	 * 
	 * @param figure the figure.
	 * @return the index.
	 */
	public int indexOfFigure(final FigureRuntimeElement figure) {
		return figureListElement.indexOfElement(figure);
	}

	/**
	 * Returns the number of figures.
	 * 
	 * @return the number of figures.
	 */
	public int getFigureCount() {
		return figureListElement.getElementCount();
	}

	private void appendFigure(final FigureRuntimeElement figure) {
		figureListElement.appendElement(figure);
	}

	private void insertFigureAfter(final int index, final FigureRuntimeElement figure) {
		figureListElement.insertElementAfter(index, figure);
	}

	private void insertFigureBefore(final int index, final FigureRuntimeElement figure) {
		figureListElement.insertElementBefore(index, figure);
	}

	private void removeFigure(final int index) {
		figureListElement.getElement(index).dispose();
		figureListElement.removeElement(index);
	}

	private void moveUpFigure(final int index) {
		figureListElement.moveElementUp(index);
	}

	private void moveDownFigure(final int index) {
		figureListElement.moveElementDown(index);
	}

	private void setFigure(final int index, final FigureRuntimeElement figure) {
		figureListElement.setElement(index, figure);
	}

	private class FigureListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ListConfigElement.ELEMENT_ADDED: {
					if (((FigureConfigElement) e.getParams()[0]).getClassId().equals(PathConfigElement.CLASS_ID)) {
						appendFigure(new PathRuntimeElement((PathConfigElement) e.getParams()[0]));
					} else if (((FigureConfigElement) e.getParams()[0]).getClassId().equals(RuleConfigElement.CLASS_ID)) {
						appendFigure(new RuleRuntimeElement((RuleConfigElement) e.getParams()[0]));
					}
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_AFTER: {
					if (((FigureConfigElement) e.getParams()[0]).getClassId().equals(PathConfigElement.CLASS_ID)) {
						insertFigureAfter(((Integer) e.getParams()[1]).intValue(), new PathRuntimeElement((PathConfigElement) e.getParams()[0]));
					} else if (((FigureConfigElement) e.getParams()[0]).getClassId().equals(RuleConfigElement.CLASS_ID)) {
						insertFigureAfter(((Integer) e.getParams()[1]).intValue(), new RuleRuntimeElement((RuleConfigElement) e.getParams()[0]));
					}
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_BEFORE: {
					if (((FigureConfigElement) e.getParams()[0]).getClassId().equals(PathConfigElement.CLASS_ID)) {
						insertFigureBefore(((Integer) e.getParams()[1]).intValue(), new PathRuntimeElement((PathConfigElement) e.getParams()[0]));
					} else if (((FigureConfigElement) e.getParams()[0]).getClassId().equals(RuleConfigElement.CLASS_ID)) {
						insertFigureBefore(((Integer) e.getParams()[1]).intValue(), new RuleRuntimeElement((RuleConfigElement) e.getParams()[0]));
					}
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_REMOVED: {
					removeFigure(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_UP: {
					moveUpFigure(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_DOWN: {
					moveDownFigure(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_CHANGED: {
					if (((FigureConfigElement) e.getParams()[0]).getClassId().equals(PathConfigElement.CLASS_ID)) {
						setFigure(((Integer) e.getParams()[1]).intValue(), new PathRuntimeElement((PathConfigElement) e.getParams()[0]));
					} else if (((FigureConfigElement) e.getParams()[0]).getClassId().equals(RuleConfigElement.CLASS_ID)) {
						setFigure(((Integer) e.getParams()[1]).intValue(), new RuleRuntimeElement((RuleConfigElement) e.getParams()[0]));
					}
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
