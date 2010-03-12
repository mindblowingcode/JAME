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

import net.sf.jame.core.util.Color32bit;

/**
 * @author Andrea Medeghini
 */
public final class DefaultContextFreeFractalRenderer extends AbstractContextFreeFractalRenderer {
	/**
	 * 
	 */
	public DefaultContextFreeFractalRenderer(final int threadPriority) {
		super(threadPriority);
	}

	@Override
	protected void doFractal(boolean dynamicZoom) {
		ContextFreeContext contextFreeContext = new ContextFreeContext(getGraphics(), getFractal());
		Color32bit background = getFractal().getBackground();
		getGraphics().setColor(new Color(background.getARGB()));
		getGraphics().fillRect(0,0, getBufferWidth(), getBufferHeight());
		String startshape = getFractal().getStartshape();
		contextFreeContext.draw(startshape);
		
		// TODO Auto-generated method stub
	}
}
