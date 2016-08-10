/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
package net.sf.jame.contextfree.extensions.shapeAdjustment;

import net.sf.jame.contextfree.shapeAdjustment.extension.ShapeAdjustmentExtensionDescriptor;
import net.sf.jame.contextfree.extensions.shapeAdjustment.CurrentSaturationShapeAdjustmentRuntime;
import net.sf.jame.contextfree.extensions.shapeAdjustment.CurrentSaturationShapeAdjustmentConfig;

/**
 * @author Andrea Medeghini
 */
public class CurrentSaturationShapeAdjustmentDescriptor extends ShapeAdjustmentExtensionDescriptor {
	/**
	 * Returns the extensionId.
	 * 
	 * @return the extensionId.
	 */
	public String getExtensionId() {
		return "contextfree.shape.adjustment.color.currentSaturation";
	}

	/**
	 * Returns the extensionName.
	 * 
	 * @return the extensionName.
	 */
	public String getExtensionName() {
		return "CurrentSaturation Adjustment";
	}

	/**
	 * Returns the extensionRuntimeClass.
	 * 
	 * @return the extensionRuntimeClass.
	 */
	public CurrentSaturationShapeAdjustmentRuntime getExtensionRuntime() {
		return new CurrentSaturationShapeAdjustmentRuntime();
	}

	/**
	 * Returns the extensionConfigClass.
	 * 
	 * @return the extensionConfigClass.
	 */
	public CurrentSaturationShapeAdjustmentConfig getExtensionConfig() {
		return new CurrentSaturationShapeAdjustmentConfig();
	}
}
