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
import java.awt.geom.AffineTransform;

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
		ContextFreeBounds size = new ShapeBounds(width, height);
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
		if (cfdgRuntime.getX() != null || cfdgRuntime.getY() != null) {
			
		}
		AffineTransform tmpTransform = g2d.getTransform();
		ContextFreeArea area = null;
		if (cfdgRuntime.isUseSize()) {
			float d = Math.max(cfdgRuntime.getWidth(), cfdgRuntime.getHeight()) / 2; 
			size.addPoint(-cfdgRuntime.getX() - d, -cfdgRuntime.getY() - d);
			size.addPoint(-cfdgRuntime.getX() + d, -cfdgRuntime.getY() + d);
			area = createArea(size, 1.0f);
		} else {
			size.addPoint(bounds.getMinX(), bounds.getMinY());
			size.addPoint(bounds.getMaxX(), bounds.getMaxY());
			area = createArea(size, 0.9f);
		}
		if (cfdgRuntime.isUseTile()) {
			float dx = 1;
			float dy = 1; 
			if (!cfdgRuntime.isUseSize()) {
				dx = cfdgRuntime.getTileWidth();
				dy = cfdgRuntime.getTileHeight(); 
			} else {
				dx = cfdgRuntime.getWidth();
				dy = cfdgRuntime.getHeight(); 
			}
			AffineTransform t = g2d.getTransform();
			renderShape(context, g2d, area, dx, dy,  0,  0);
			g2d.setTransform(t);
			if (bounds.getMaxX() > +dx / 2) {
				if (bounds.getMaxY() > +dy / 2) {
					renderShape(context, g2d, area, dx, dy, -1,  0);
					g2d.setTransform(t);
					renderShape(context, g2d, area, dx, dy, -1, -1);
					g2d.setTransform(t);
					renderShape(context, g2d, area, dx, dy,  0, -1);
					g2d.setTransform(t);
				}
				if (bounds.getMinY() < -dy / 2) {
					renderShape(context, g2d, area, dx, dy,  0, +1);
					g2d.setTransform(t);
					renderShape(context, g2d, area, dx, dy, -1, +1);
					g2d.setTransform(t);
					renderShape(context, g2d, area, dx, dy, -1,  0);
					g2d.setTransform(t);
				}
			}
			if (bounds.getMaxX() < -dx / 2) {
				if (bounds.getMaxY() > +dy / 2) {
					renderShape(context, g2d, area, dx, dy, +1,  0);
					g2d.setTransform(t);
					renderShape(context, g2d, area, dx, dy, +1, +1);
					g2d.setTransform(t);
					renderShape(context, g2d, area, dx, dy,  0, +1);
					g2d.setTransform(t);
				}
				if (bounds.getMinY() < -dy / 2) {
					renderShape(context, g2d, area, dx, dy,  0, -1);
					g2d.setTransform(t);
					renderShape(context, g2d, area, dx, dy, +1, -1);
					g2d.setTransform(t);
					renderShape(context, g2d, area, dx, dy, +1, 0);
					g2d.setTransform(t);
				}
			}
		} else {
			renderShape(context, g2d, area);
		}
		g2d.setTransform(tmpTransform);
		logger.debug("Render time " + (System.nanoTime() - time) / 1000000 + "ms");
		percent = 100;
	}

	private void renderShape(ContextFreeContext context, Graphics2D g2d, ContextFreeArea area) {
		float sx = area.getScaleX();
		float sy = area.getScaleY();
		float tx = area.getX();
		float ty = area.getY();
		g2d.translate(tx, ty);
		g2d.scale(sx, sy);
		context.renderShape(g2d, area);
	}

	private void renderShape(ContextFreeContext context, Graphics2D g2d, ContextFreeArea area, float dx, float dy, float qx, float qy) {
		float sx = area.getScaleX();
		float sy = area.getScaleY();
		float tx = area.getX();
		float ty = area.getY();
		float sw = 1.0f / dx;
		float sh = 1.0f / dy;
		g2d.translate(tx, ty);
		g2d.scale(sx, sy);
		g2d.translate(qx, qy);
		g2d.scale(sw, sh);
		context.renderShape(g2d, area);
	}

	private ContextFreeArea createArea(ContextFreeBounds bounds, float normalizedSize) {
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
		float scale = Math.min(tileWidth / Math.abs(maxX - minX), tileHeight / Math.abs(maxY - minY));
		float sx = +normalizedSize * scale;
		float sy = -normalizedSize * scale;
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
