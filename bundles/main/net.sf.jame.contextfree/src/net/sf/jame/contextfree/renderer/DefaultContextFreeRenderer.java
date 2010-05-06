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
package net.sf.jame.contextfree.renderer;

import java.awt.Color;
import java.awt.Graphics2D;

import net.sf.jame.contextfree.cfdg.CFDGRuntimeElement;
import net.sf.jame.core.util.Color32bit;

/**
 * @author Andrea Medeghini
 */
public final class DefaultContextFreeRenderer extends AbstractContextFreeRenderer {
	/**
	 * 
	 */
	public DefaultContextFreeRenderer(final int threadPriority) {
		super(threadPriority);
	}

	@Override
	protected void doRender(boolean dynamicZoom) {
		CFDGRuntimeElement cfdgRuntime = getRuntime();
		Color32bit background = cfdgRuntime.getBackground();
		String startshape = cfdgRuntime.getStartshape();
		ContextFreeContext context = new ContextFreeContext(cfdgRuntime);
		ContextFreeLimits limits = new ContextFreeLimits(getBufferWidth(), getBufferHeight());
		ContextFreeState state = new ContextFreeState(); 
		context.registerFigures();
		getGraphics().setColor(new Color(background.getARGB()));
		getGraphics().fillRect(0, 0, getBufferWidth(), getBufferHeight());
		ContextFreeNode startNode = context.buildRuleNode(state, limits, startshape);
		Graphics2D g2d = getGraphics();
		startNode.draw(g2d, limits.getArea());
	}
}
