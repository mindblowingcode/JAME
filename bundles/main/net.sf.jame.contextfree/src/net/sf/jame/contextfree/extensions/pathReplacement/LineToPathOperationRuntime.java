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
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeArea;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeLimits;
import net.sf.jame.contextfree.renderer.ContextFreeNode;
import net.sf.jame.contextfree.renderer.ContextFreeState;

/**
 * @author Andrea Medeghini
 *
 */
public class LineToPathOperationRuntime extends PathReplacementExtensionRuntime<LineToPathOperationConfig> {
	public ContextFreeNode buildNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
		return new OperationContextFreeNode(context, state, limits);
	}
	
	private class OperationContextFreeNode extends ContextFreeNode {
		private ContextFreeState state;
		
		public OperationContextFreeNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
			this.state = state;
		}

		@Override
		public void drawNode(Graphics2D g2d, ContextFreeArea area) {
			Color c = new Color((((int) Math.rint((255 * state.getCurrentAlpha()))) << 24) |  Color.HSBtoRGB(state.getCurrentHue(), state.getCurrentSaturation(), state.getCurrentBrightness()));
			g2d.setColor(c);
			float sx = area.getScaleX();
			float sy = area.getScaleY();
			float x = area.getX() + state.getX() * sx;
			float y = area.getY() + state.getY() * sy;
			g2d.draw(new Line2D.Float(x, y, x + getConfig().getX().floatValue() * sx, y + getConfig().getY().floatValue() * sy));
		}
	}
}
