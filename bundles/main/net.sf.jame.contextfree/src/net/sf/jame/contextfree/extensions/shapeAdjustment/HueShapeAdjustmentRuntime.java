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
package net.sf.jame.contextfree.extensions.shapeAdjustment;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeState;

/**
 * @author Andrea Medeghini
 *
 */
public class HueShapeAdjustmentRuntime extends ShapeAdjustmentExtensionRuntime<HueShapeAdjustmentConfig> {
	private float delta = 0;
	
	/**
	 * @see net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionRuntime#load(net.sf.jame.contextfree.renderer.ContextFreeState, int)
	 */
	@Override
	public void load(ContextFreeState state, int times) {
		state.setTargetHue(getConfig().getValue().floatValue());
		delta = (state.getCurrentHue() - state.getTargetHue()) / times;
	}

	/**
	 * @see net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionRuntime#update(net.sf.jame.contextfree.renderer.ContextFreeState)
	 */
	@Override
	public void update(ContextFreeState state) {
		state.setCurrentHue(state.getCurrentHue() + delta);
	}
}
