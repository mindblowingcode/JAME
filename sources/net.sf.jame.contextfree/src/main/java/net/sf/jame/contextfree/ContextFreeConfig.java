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
package net.sf.jame.contextfree;

import net.sf.jame.contextfree.cfdg.CFDGConfigElement;
import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.SingleConfigElement;
import net.sf.jame.core.util.DoubleVector4D;
import net.sf.jame.core.util.IntegerVector4D;
import net.sf.jame.twister.common.SpeedElement;
import net.sf.jame.twister.common.ViewElement;
import net.sf.jame.twister.util.Speed;
import net.sf.jame.twister.util.View;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeConfig extends AbstractConfigElement {
	public static final String CLASS_ID = "ContextFreeConfig";
	private static final long serialVersionUID = 1L;
	private final SingleConfigElement<CFDGConfigElement> cfdgSingleElement = new SingleConfigElement<CFDGConfigElement>("CFDGSingleElement");
	private final ViewElement viewElement = new ViewElement(new View(new IntegerVector4D(0, 0, 0, 0), new DoubleVector4D(0, 0, 1, 0), new DoubleVector4D(0, 0, 0, 0)));
	private final SpeedElement speedElement = new SpeedElement(new Speed(new DoubleVector4D(0, 0, 50, 1), new DoubleVector4D(0, 0, 50, 0)));

	/**
	 * 
	 */
	public ContextFreeConfig() {
		super(ContextFreeConfig.CLASS_ID);
	}

	/**
	 * @param value
	 */
	public void setView(final View value) {
		viewElement.setValue(value);
	}

	/**
	 * @return
	 */
	public View getView() {
		return viewElement.getValue();
	}

	/**
	 * @return
	 */
	public Speed getSpeed() {
		return speedElement.getValue();
	}

	/**
	 * @param speed
	 */
	public void setSpeed(final Speed speed) {
		speedElement.setValue(speed);
	}

	/**
	 * @return
	 */
	@Override
	public ContextFreeConfig clone() {
		final ContextFreeConfig config = new ContextFreeConfig();
		config.setView(getView());
		config.setSpeed(getSpeed());
		config.setCFDG(getCFDG() != null ? getCFDG().clone() : null);
		return config;
	}

	/**
	 * @see net.sf.jame.core.config.ConfigElement#copyFrom(net.sf.jame.core.config.ConfigElement)
	 */
	public void copyFrom(ConfigElement source) {
		final ContextFreeConfig config = (ContextFreeConfig) source;
		setView(config.getView());
		setSpeed(config.getSpeed());
		getCFDGSingleElement().copyFrom(config.getCFDGSingleElement());
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		viewElement.setContext(getContext());
		speedElement.setContext(getContext());
		cfdgSingleElement.setContext(getContext());
	}

	/**
	 * @return
	 */
	public ViewElement getViewElement() {
		return viewElement;
	}

	/**
	 * @return
	 */
	public SpeedElement getSpeedElement() {
		return speedElement;
	}

	/**
	 * @return
	 */
	public CFDGConfigElement getCFDG() {
		return cfdgSingleElement.getValue();
	}

	/**
	 * @param cfdgElement
	 */
	public void setCFDG(CFDGConfigElement cfdgElement) {
		cfdgSingleElement.setValue(cfdgElement);
	}

	/**
	 * @return
	 */
	public SingleConfigElement<CFDGConfigElement> getCFDGSingleElement() {
		return cfdgSingleElement;
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
		final ContextFreeConfig other = (ContextFreeConfig) obj;
		if (cfdgSingleElement == null) {
			if (other.cfdgSingleElement != null) {
				return false;
			}
		}
		else if (!cfdgSingleElement.equals(other.cfdgSingleElement)) {
			return false;
		}
		if (viewElement == null) {
			if (other.viewElement != null) {
				return false;
			}
		}
		else if (!viewElement.equals(other.viewElement)) {
			return false;
		}
		if (speedElement == null) {
			if (other.speedElement != null) {
				return false;
			}
		}
		else if (!speedElement.equals(other.speedElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#dispose()
	 */
	@Override
	public void dispose() {
		viewElement.dispose();
		speedElement.dispose();
		cfdgSingleElement.dispose();
		super.dispose();
	}
}
