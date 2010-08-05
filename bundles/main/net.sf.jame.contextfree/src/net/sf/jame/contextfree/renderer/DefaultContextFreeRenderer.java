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
import java.awt.RenderingHints;

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
		updateTransform();
		Color32bit background = cfdgRuntime.getBackground();
		String startshape = cfdgRuntime.getStartshape();
		ContextFreeContext context = new ContextFreeContext(cfdgRuntime);
		ContextFreeBounds bounds = new ContextFreeBounds();
		ContextFreeState state = new ContextFreeState(); 
		context.registerFigures();
		Graphics2D g2d = getGraphics();
		configure(g2d);
		g2d.setColor(new Color(background.getARGB()));
		g2d.fillRect(0, 0, getBufferWidth(), getBufferHeight());
		ContextFreeNode startNode = context.buildRuleNode(state, bounds, startshape);
		if (startNode != null) {
			startNode.draw(g2d, creatreArea(bounds, 8));
		}
		percent = 100;
	}

	private ContextFreeArea creatreArea(ContextFreeBounds bounds, int border) {
		int width = getTile().getTileSize().getX();
		int height = getTile().getTileSize().getY();
		int x = (getBufferWidth() - width) / 2 + getTile().getTileBorder().getX();
		int y = (getBufferHeight() - height) / 2 + getTile().getTileBorder().getY();
		float maxX = bounds.getMaxX();
		float maxY = bounds.getMaxY();
		float minX = bounds.getMinX();
		float minY = bounds.getMinY();
		float scale = Math.min((width - 2 * border) / Math.abs(maxX - minX), (height - 2 * border) / Math.abs(maxY - minY));
		return new ContextFreeArea(x + (width - (minX + maxX) * scale) / 2, y + (height + (minY + maxY) * scale) / 2, width, height, +scale, -scale);
	}

	protected void configure(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DEFAULT);
	}
}
