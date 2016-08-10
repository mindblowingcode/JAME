/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.renderer.support.CFBuilder;
import net.sf.jame.contextfree.renderer.support.CFRule;
import net.sf.jame.contextfree.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.shapeReplacement.ShapeReplacementRuntimeElement;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.config.ListRuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class RuleFigureRuntime extends FigureExtensionRuntime<RuleFigureConfig> {
	private String name;
	private NameListener nameListener;
	private Float probability;
	private ProbabilityListener probabilityListener;
	private ListRuntimeElement<ShapeReplacementRuntimeElement> shapeReplacementListElement;
	private ShapeReplacementListElementListener shapeReplacementListElementListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setName(getConfig().getName());
		nameListener = new NameListener();
		getConfig().getNameElement().addChangeListener(nameListener);
		setProbability(getConfig().getProbability());
		probabilityListener = new ProbabilityListener();
		getConfig().getProbabilityElement().addChangeListener(probabilityListener);
		shapeReplacementListElement = new ListRuntimeElement<ShapeReplacementRuntimeElement>();
		for (int i = 0; i < getConfig().getShapeReplacementConfigElementCount(); i++) {
			shapeReplacementListElement.appendElement(new ShapeReplacementRuntimeElement(getConfig().getShapeReplacementConfigElement(i)));
		}
		shapeReplacementListElementListener = new ShapeReplacementListElementListener();
		getConfig().getShapeReplacementListElement().addChangeListener(shapeReplacementListElementListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (nameListener != null)) {
			getConfig().getNameElement().removeChangeListener(nameListener);
		}
		nameListener = null;
		if ((getConfig() != null) && (probabilityListener != null)) {
			getConfig().getProbabilityElement().removeChangeListener(probabilityListener);
		}
		probabilityListener = null;
		if ((getConfig() != null) && (shapeReplacementListElementListener != null)) {
			getConfig().getShapeReplacementListElement().removeChangeListener(shapeReplacementListElementListener);
		}
		shapeReplacementListElementListener = null;
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
					setName((String) e.getParams()[0]);
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
	 * @return the probability.
	 */
	public Float getProbability() {
		return probability;
	}

	private void setProbability(final Float probability) {
		this.probability = probability;
	}
	
	private class ProbabilityListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setProbability((Float) e.getParams()[0]);
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
	 * Returns a shapeReplacement element.
	 * 
	 * @param index the shapeReplacement index.
	 * @return the shapeReplacement.
	 */
	public ShapeReplacementRuntimeElement getShapeReplacementElement(final int index) {
		return shapeReplacementListElement.getElement(index);
	}

	/**
	 * Returns a shapeReplacement element index.
	 * 
	 * @param shapeReplacementElement the shapeReplacement element.
	 * @return the index.
	 */
	public int indexOfShapeReplacementElement(final ShapeReplacementRuntimeElement shapeReplacementElement) {
		return shapeReplacementListElement.indexOfElement(shapeReplacementElement);
	}

	/**
	 * Returns the number of shapeReplacement elements.
	 * 
	 * @return the number of shapeReplacement elements.
	 */
	public int getShapeReplacementElementCount() {
		return shapeReplacementListElement.getElementCount();
	}

	private void setShapeReplacementElement(final int index, ShapeReplacementRuntimeElement element) {
		shapeReplacementListElement.setElement(index, element);
	}

	private void appendShapeReplacementElement(final ShapeReplacementRuntimeElement shapeReplacementElement) {
		shapeReplacementListElement.appendElement(shapeReplacementElement);
	}

	private void insertShapeReplacementElementAfter(final int index, final ShapeReplacementRuntimeElement shapeReplacementElement) {
		shapeReplacementListElement.insertElementAfter(index, shapeReplacementElement);
	}

	private void insertShapeReplacementElementBefore(final int index, final ShapeReplacementRuntimeElement shapeReplacementElement) {
		shapeReplacementListElement.insertElementBefore(index, shapeReplacementElement);
	}

	private void removeShapeReplacementElement(final int index) {
		shapeReplacementListElement.removeElement(index);
	}

	private void moveUpShapeReplacementElement(final int index) {
		shapeReplacementListElement.moveElementUp(index);
	}

	private void moveDownShapeReplacementElement(final int index) {
		shapeReplacementListElement.moveElementDown(index);
	}
	
	private class ShapeReplacementListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ListConfigElement.ELEMENT_ADDED: {
					appendShapeReplacementElement(new ShapeReplacementRuntimeElement ((ShapeReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_AFTER: {
					insertShapeReplacementElementAfter(((Integer) e.getParams()[1]).intValue(), new ShapeReplacementRuntimeElement ((ShapeReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_BEFORE: {
					insertShapeReplacementElementBefore(((Integer) e.getParams()[1]).intValue(), new ShapeReplacementRuntimeElement ((ShapeReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_REMOVED: {
					removeShapeReplacementElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_UP: {
					moveUpShapeReplacementElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_DOWN: {
					moveDownShapeReplacementElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_CHANGED: {
					setShapeReplacementElement(((Integer) e.getParams()[1]).intValue(), new ShapeReplacementRuntimeElement ((ShapeReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	public void process(CFBuilder builder) {
		int shapeType = builder.encodeShapeName(name);
		CFRule rule = new CFRule(shapeType, probability);
		for (int i = 0; i < shapeReplacementListElement.getElementCount(); i++) {
			ShapeReplacementRuntimeElement shapeReplacementRuntime = shapeReplacementListElement.getElement(i);
			shapeReplacementRuntime.process(builder, rule);
		}
		builder.addRule(rule);
	}
}
