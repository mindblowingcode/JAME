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

import org.apache.log4j.Logger;

/**
 * @author Andrea Medeghini
 */
public final class DefaultContextFreeRenderer extends AbstractContextFreeRenderer {
	private static final Logger logger = Logger.getLogger(DefaultContextFreeRenderer.class);
	
	/**
	 * 
	 */
	public DefaultContextFreeRenderer(final int threadPriority) {
		super(threadPriority);
	}

	@Override
	protected void doRender(boolean dynamicZoom) {
		long time = System.nanoTime();
		updateTransform();
		int width = getTile().getTileSize().getX();
		int height = getTile().getTileSize().getY();
		Color32bit background = cfdgRuntime.getBackground();
		String startshape = cfdgRuntime.getStartshape();
		cfdgRuntime.resetRandom();
		ContextFreeContext context = new ContextFreeContext(cfdgRuntime);
		ContextFreeBounds bounds = new ShapeBounds(width, height);
		ContextFreeState state = new ContextFreeState(); 
		context.registerFigures();
		Graphics2D g2d = getGraphics();
		configure(g2d);
		g2d.setColor(new Color(background.getARGB()));
		g2d.fillRect(0, 0, getBufferWidth(), getBufferHeight());
		context.buildPathOrRule(state, bounds, startshape);
		logger.debug("Create time " + (System.nanoTime() - time) / 1000000 + "ms");
		percent = 30;
		time = System.nanoTime();
		while (context.expandShapes()) {
		}
		logger.debug("Expand time " + (System.nanoTime() - time) / 1000000 + "ms");
		percent = 70;
		if (logger.isDebugEnabled()) {
			logger.debug("Total shapes " + context.getRenderCount());
		}
		time = System.nanoTime();
		context.renderShape(g2d, creatreArea(bounds, 8));
		logger.debug("Render time " + (System.nanoTime() - time) / 1000000 + "ms");
		percent = 100;
	}

	private ContextFreeArea creatreArea(ContextFreeBounds bounds, int border) {
		int borderWidth = getTile().getTileBorder().getX();
		int borderHeight = getTile().getTileBorder().getY();
		int tileWidth = getTile().getTileSize().getX();
		int tileHeight = getTile().getTileSize().getY();
		int x = getBufferWidth() / 2 + borderWidth;
		int y = getBufferHeight() / 2 + borderHeight;
		float maxX = (float) bounds.getMaxX();
		float maxY = (float) bounds.getMaxY();
		float minX = (float) bounds.getMinX();
		float minY = (float) bounds.getMinY();
		float scale = Math.min((tileWidth - 2 * border) / Math.abs(maxX - minX), (tileHeight - 2 * border) / Math.abs(maxY - minY));
		float sx = +1.0f * scale;
		float sy = -1.0f * scale;
		return new ContextFreeArea(x - (maxX + minX) * sx / 2, y - (maxY + minY) * sy / 2, tileWidth, tileHeight, sx, sy);
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
