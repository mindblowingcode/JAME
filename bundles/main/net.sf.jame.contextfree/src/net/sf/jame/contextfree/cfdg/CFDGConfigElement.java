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

import net.sf.jame.core.common.ColorElement;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.util.Color32bit;

/**
 * @author Andrea Medeghini
 */
public class CFDGConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "CFDG";
	private final StringElement startshapeElement = new StringElement("");
	private final FloatElement xElement = new FloatElement(0f);
	private final FloatElement yElement = new FloatElement(0f);
	private final FloatElement widthElement = new FloatElement(0f);
	private final FloatElement heightElement = new FloatElement(0f);
	private final FloatElement tileWidthElement = new FloatElement(0f);
	private final FloatElement tileHeightElement = new FloatElement(0f);
	private final ColorElement backgroundElement = new ColorElement(new Color32bit(0xFF000000));
	private final ListConfigElement<FigureConfigElement> figureListElement = new ListConfigElement<FigureConfigElement>("figure");

	/**
	 * Constructs a new element.
	 */
	public CFDGConfigElement() {
		super(CFDGConfigElement.CLASS_ID);
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		startshapeElement.setContext(context);
		xElement.setContext(context);
		yElement.setContext(context);
		widthElement.setContext(context);
		heightElement.setContext(context);
		tileWidthElement.setContext(context);
		tileHeightElement.setContext(context);
		backgroundElement.setContext(context);
		figureListElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public CFDGConfigElement clone() {
		final CFDGConfigElement element = new CFDGConfigElement();
		element.setStartshape(getStartshape());
		element.setX(getX());
		element.setY(getY());
		element.setWidth(getWidth());
		element.setHeight(getHeight());
		element.setTileWidth(getTileWidth());
		element.setTileHeight(getTileHeight());
		element.setBackground(getBackground());
		element.figureListElement.copyFrom(getFigureListElement());
		return element;
	}

	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		CFDGConfigElement cfdgElement = (CFDGConfigElement) source;
		setStartshape(cfdgElement.getStartshape());
		setX(cfdgElement.getX());
		setY(cfdgElement.getY());
		setWidth(cfdgElement.getWidth());
		setHeight(cfdgElement.getHeight());
		setTileWidth(cfdgElement.getTileWidth());
		setTileHeight(cfdgElement.getTileHeight());
		setBackground(cfdgElement.getBackground());
		figureListElement.copyFrom(cfdgElement.getFigureListElement());
	}

	/**
	 * @return
	 */
	public StringElement getStartshapeElement() {
		return startshapeElement;
	}

	/**
	 * @return
	 */
	public String getStartshape() {
		return startshapeElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setStartshape(final String value) {
		startshapeElement.setValue(value);
	}

	/**
	 * @return
	 */
	public FloatElement getXElement() {
		return xElement;
	}

	/**
	 * @return
	 */
	public Float getX() {
		return xElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setX(final Float value) {
		xElement.setValue(value);
	}

	/**
	 * @return
	 */
	public FloatElement getYElement() {
		return yElement;
	}

	/**
	 * @return
	 */
	public Float getY() {
		return yElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setY(final Float value) {
		yElement.setValue(value);
	}

	/**
	 * @return
	 */
	public FloatElement getWidthElement() {
		return widthElement;
	}

	/**
	 * @return
	 */
	public Float getWidth() {
		return widthElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setWidth(final Float value) {
		widthElement.setValue(value);
	}

	/**
	 * @return
	 */
	public FloatElement getHeightElement() {
		return heightElement;
	}

	/**
	 * @return
	 */
	public Float getHeight() {
		return heightElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setHeight(final Float value) {
		heightElement.setValue(value);
	}

	/**
	 * @return
	 */
	public FloatElement getTileWidthElement() {
		return tileWidthElement;
	}

	/**
	 * @return
	 */
	public Float getTileWidth() {
		return tileWidthElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setTileWidth(final Float value) {
		tileWidthElement.setValue(value);
	}

	/**
	 * @return
	 */
	public FloatElement getTileHeightElement() {
		return tileHeightElement;
	}

	/**
	 * @return
	 */
	public Float getTileHeight() {
		return tileHeightElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setTileHeight(final Float value) {
		tileHeightElement.setValue(value);
	}

	/**
	 * @return
	 */
	public ColorElement getBackgroundElement() {
		return backgroundElement;
	}

	/**
	 * @return
	 */
	public Color32bit getBackground() {
		return backgroundElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setBackground(final Color32bit value) {
		backgroundElement.setValue(value);
	}

	/**
	 * @return
	 */
	public ListConfigElement<FigureConfigElement> getFigureListElement() {
		return figureListElement;
	}

	/**
	 * Returns a figure element.
	 * 
	 * @param index the figure index.
	 * @return the figure.
	 */
	public FigureConfigElement getFigureConfigElement(final int index) {
		return figureListElement.getElement(index);
	}

	/**
	 * Returns a figure element index.
	 * 
	 * @param figureElement the figure element.
	 * @return the index.
	 */
	public int indexOfFigureConfigElement(final FigureConfigElement figureElement) {
		return figureListElement.indexOfElement(figureElement);
	}

	/**
	 * Returns the number of figure elements.
	 * 
	 * @return the number of figure elements.
	 */
	public int getFigureConfigElementCount() {
		return figureListElement.getElementCount();
	}

	/**
	 * Adds a figure element.
	 * 
	 * @param figureElement the figure to add.
	 */
	public void appendFigureConfigElement(final FigureConfigElement figureElement) {
		figureListElement.appendElement(figureElement);
	}

	/**
	 * Adds a figure element.
	 * 
	 * @param index the index.
	 * @param figureElement the figure to add.
	 */
	public void insertFigureConfigElementAfter(final int index, final FigureConfigElement figureElement) {
		figureListElement.insertElementAfter(index, figureElement);
	}

	/**
	 * Adds a figure element.
	 * 
	 * @param index the index.
	 * @param figureElement the figure to add.
	 */
	public void insertFigureConfigElementBefore(final int index, final FigureConfigElement figureElement) {
		figureListElement.insertElementBefore(index, figureElement);
	}

	/**
	 * Removes a figure element.
	 * 
	 * @param index the element index to remove.
	 */
	public void removeFigureConfigElement(final int index) {
		figureListElement.removeElement(index);
	}

	/**
	 * Removes a figure element.
	 * 
	 * @param figureElement the figure to remove.
	 */
	public void removeFigureConfigElement(final FigureConfigElement figureElement) {
		figureListElement.removeElement(figureElement);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		final CFDGConfigElement other = (CFDGConfigElement) obj;
		if (startshapeElement == null) {
			if (other.startshapeElement != null) {
				return false;
			}
		}
		else if (!startshapeElement.equals(other.startshapeElement)) {
			return false;
		}
		if (xElement == null) {
			if (other.xElement != null) {
				return false;
			}
		}
		else if (!xElement.equals(other.xElement)) {
			return false;
		}
		if (yElement == null) {
			if (other.yElement != null) {
				return false;
			}
		}
		else if (!yElement.equals(other.yElement)) {
			return false;
		}
		if (widthElement == null) {
			if (other.widthElement != null) {
				return false;
			}
		}
		else if (!widthElement.equals(other.widthElement)) {
			return false;
		}
		if (heightElement == null) {
			if (other.heightElement != null) {
				return false;
			}
		}
		else if (!heightElement.equals(other.heightElement)) {
			return false;
		}
		if (tileWidthElement == null) {
			if (other.tileWidthElement != null) {
				return false;
			}
		}
		else if (!tileWidthElement.equals(other.tileWidthElement)) {
			return false;
		}
		if (tileHeightElement == null) {
			if (other.tileHeightElement != null) {
				return false;
			}
		}
		else if (!tileHeightElement.equals(other.tileHeightElement)) {
			return false;
		}
		if (backgroundElement == null) {
			if (other.backgroundElement != null) {
				return false;
			}
		}
		else if (!backgroundElement.equals(other.backgroundElement)) {
			return false;
		}
		if (figureListElement == null) {
			if (other.figureListElement != null) {
				return false;
			}
		}
		else if (!figureListElement.equals(other.figureListElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#dispose()
	 */
	@Override
	public void dispose() {
		startshapeElement.dispose();
		xElement.dispose();
		yElement.dispose();
		widthElement.dispose();
		heightElement.dispose();
		tileWidthElement.dispose();
		tileHeightElement.dispose();
		backgroundElement.dispose();
		figureListElement.dispose();
		super.dispose();
	}
}
