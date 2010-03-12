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
package net.sf.jame.contextfree.cfdg.rule;

import net.sf.jame.contextfree.cfdg.FigureConfigElement;
import net.sf.jame.contextfree.cfdg.shape.ShapeConfigElement;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.SingleConfigElement;

/**
 * @author Andrea Medeghini
 */
public class RuleConfigElement extends FigureConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "Rule";
	private final FloatElement propabilityElement = new FloatElement(1f);
	private final SingleConfigElement<ShapeConfigElement> shapeSingleElement = new SingleConfigElement<ShapeConfigElement>("shape");

	/**
	 * Constructs a new element.
	 */
	public RuleConfigElement() {
		super(RuleConfigElement.CLASS_ID);
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		nameElement.setContext(context);
		propabilityElement.setContext(context);
		shapeSingleElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public RuleConfigElement clone() {
		final RuleConfigElement element = new RuleConfigElement();
		element.setName(getName());
		element.setPropability(getPropability());
		element.shapeSingleElement.copyFrom(getShapeSingleElement());
		return element;
	}

	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		RuleConfigElement ruleElement = (RuleConfigElement) source;
		setName(ruleElement.getName());
		setPropability(ruleElement.getPropability());
		shapeSingleElement.copyFrom(ruleElement.getShapeSingleElement());
	}

	/**
	 * @return
	 */
	public FloatElement getPropabilityElement() {
		return propabilityElement;
	}

	/**
	 * @return
	 */
	public Float getPropability() {
		return propabilityElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setPropability(final Float value) {
		propabilityElement.setValue(value);
	}

	/**
	 * @return
	 */
	public SingleConfigElement<ShapeConfigElement> getShapeSingleElement() {
		return shapeSingleElement;
	}

	/**
	 * @return
	 */
	public ShapeConfigElement getShapeConfigElement() {
		return shapeSingleElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setShapeConfigElement(ShapeConfigElement element) {
		shapeSingleElement.setValue(element);
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
		final RuleConfigElement other = (RuleConfigElement) obj;
		if (nameElement == null) {
			if (other.nameElement != null) {
				return false;
			}
		}
		else if (!nameElement.equals(other.nameElement)) {
			return false;
		}
		if (propabilityElement == null) {
			if (other.propabilityElement != null) {
				return false;
			}
		}
		else if (!propabilityElement.equals(other.propabilityElement)) {
			return false;
		}
		if (shapeSingleElement == null) {
			if (other.shapeSingleElement != null) {
				return false;
			}
		}
		else if (!shapeSingleElement.equals(other.shapeSingleElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#dispose()
	 */
	@Override
	public void dispose() {
		nameElement.dispose();
		propabilityElement.dispose();
		shapeSingleElement.dispose();
		super.dispose();
	}
}
