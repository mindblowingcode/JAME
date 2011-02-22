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

import net.sf.jame.contextfree.renderer.support.CFModification;
import net.sf.jame.contextfree.renderer.support.CFShape;
import net.sf.jame.core.util.Color32bit;

import org.apache.log4j.Logger;

/**
 * @author Andrea Medeghini
 */
public class DefaultContextFreeRenderer extends AbstractContextFreeRenderer {
	private static final Logger logger = Logger.getLogger(DefaultContextFreeRenderer.class);
	private static final int MAX_SHAPES = 5000000;
	
	/**
	 * 
	 */
	public DefaultContextFreeRenderer(final int threadPriority) {
		super(threadPriority);
	}

	@Override
	protected void doRender(boolean dynamicZoom) {
		long totalTime = System.nanoTime();
		long time = totalTime;
		updateTransform();
		float offsetX = getBufferWidth() / 2 + getTile().getImageSize().getX() / 2 - getTile().getTileOffset().getX() - getTile().getTileSize().getX() / 2;
		float offsetY = getBufferHeight() / 2 + getTile().getImageSize().getY() / 2 - getTile().getTileOffset().getY() - getTile().getTileSize().getY() / 2;
		int width = getTile().getImageSize().getX();
		int height = getTile().getImageSize().getY();
		Color32bit background = cfdgRuntime.getBackground();
		String startshape = cfdgRuntime.getStartshape();
		cfdgRuntime.resetRandom();

		ContextFreeContext context = new ContextFreeContext(cfdgRuntime, width, height, 1f, 0.3f);
		context.registerFigures();

		CFModification worldState = new CFModification();
		context.processShape(new CFShape(startshape, worldState));
		
		int reportAt = 250;
		boolean partialDraw = true;
		
		for (;;) {
			if (isInterrupted()) {
				break;
			}
		    if (context.getUnfinishedCount() == 0) {
		    	break;
		    }
		    if ((context.getFinishedCount() + context.getToDoCount()) > MAX_SHAPES) {
		        break;
		    }
		    if (!context.executeShape()) {
		    	break;
		    }
			if (context.getFinishedCount() > reportAt) {
				if (partialDraw) {
					copyOldBuffer();
//					renderImage(context, globalBounds, offsetX, offsetY, background, true);
					context.commitShapes();
	            }
	            //outputStats();
	            reportAt = 2 * context.getFinishedCount();
	        }
		}
		
		if (!isInterrupted()) {
			context.commitShapes();
	        context.sortShapes();
//			renderImage(context, globalBounds, offsetX, offsetY, background, false);
	    }

		percent = 100;
		if (logger.isDebugEnabled()) {
			logger.debug("Total time " + (System.nanoTime() - totalTime) / 1000000 + "ms");
		}
	}

	protected void renderImage(ContextFreeContext context, ContextFreeBounds globalBounds, float offsetX, float offsetY, Color32bit background, boolean partial) {
		Graphics2D g2d;
		g2d = getGraphics();
		configure(g2d);
		g2d.setColor(new Color(background.getARGB()));
		g2d.fillRect(0, 0, getBufferWidth(), getBufferHeight());
		renderShapes(context, g2d, partial, globalBounds, offsetX, offsetY);
		swapImages();
	}

	protected void renderShapes(ContextFreeContext context, Graphics2D g2d, boolean partial, ContextFreeBounds globalBounds, float offsetX, float offsetY) {
		ContextFreeBounds bounds = new ContextFreeBounds(globalBounds.getWidth(), globalBounds.getHeight());
		ContextFreeArea area = new ContextFreeArea();
		AffineTransform tmpTransform = g2d.getTransform();
		float normalization = 1.0f;
		if (cfdgRuntime.isUseSize()) {
			float dx = cfdgRuntime.getWidth() / 2; 
			float dy = cfdgRuntime.getHeight() / 2;
			float d = Math.max(dx, dy);
			bounds.addPoint(-cfdgRuntime.getX() - d, -cfdgRuntime.getY() - d);
			bounds.addPoint(-cfdgRuntime.getX() + d, -cfdgRuntime.getY() + d);
		} else {
			if (cfdgRuntime.isUseTile()) {
				float dx = cfdgRuntime.getTileWidth() / 2; 
				float dy = cfdgRuntime.getTileHeight() / 2;
				bounds.addPoint(-cfdgRuntime.getX() - dx, -cfdgRuntime.getY() - dx);
				bounds.addPoint(-cfdgRuntime.getX() + dy, -cfdgRuntime.getY() + dy);
			} else {
				bounds.addPoint(globalBounds.getMinX(), globalBounds.getMinY());
				bounds.addPoint(globalBounds.getMaxX(), globalBounds.getMaxY());
			}
			normalization = 0.9f;
		}
		updateArea(area, bounds, offsetX, offsetY, normalization);
		if (cfdgRuntime.isUseTile()) {
			double dx = cfdgRuntime.getTileWidth();
			double dy = cfdgRuntime.getTileHeight();
			if (cfdgRuntime.isUseSize()) {
				dx = cfdgRuntime.getWidth();
				dy = cfdgRuntime.getHeight();
			}
			int nx = 0;
			while (nx * dx >= bounds.getMinX()) {
				nx -= 1;
			}
			int ny = 0;
			while (ny * dy >= bounds.getMinY()) {
				ny -= 1;
			}
			int mx = 0;
			while (mx * dx <= bounds.getMaxX()) {
				mx += 1;
			}
			int my = 0;
			while (my * dy <= bounds.getMaxY()) {
				my += 1;
			}
			AffineTransform t = g2d.getTransform();
			double qx = 0;
			double qy = 0;
			for (int ix = nx; ix <= mx; ix++) {
				qx = dx * ix;
				for (int iy = ny; iy <= my; iy++) {
					qy = dy * iy;
					renderShapes(context, g2d, partial, area, qx, qy);
					g2d.setTransform(t);
				}
			}
		} else {
			renderShapes(context, g2d, partial, area, 0, 0);
		}
		g2d.setTransform(tmpTransform);
	}

	protected void renderShapes(ContextFreeContext context, Graphics2D g2d, boolean partial, ContextFreeArea area, double qx, double qy) {
		float sx = area.getScaleX();
		float sy = area.getScaleY();
		float tx = area.getX();
		float ty = area.getY();
		g2d.translate(tx, ty);
		g2d.scale(sx, sy);
		g2d.translate(qx, qy);
		if (partial) {
			context.renderPartial(g2d, area);
		} else {
			context.render(g2d, area);
		}
	}

	protected void updateArea(ContextFreeArea area, ContextFreeBounds bounds, float tx, float ty, float normalization) {
		float maxX = (float) bounds.getMaxX();
		float maxY = (float) bounds.getMaxY();
		float minX = (float) bounds.getMinX();
		float minY = (float) bounds.getMinY();
		float scale = Math.min(bounds.getWidth() / Math.abs(maxX - minX), bounds.getHeight() / Math.abs(maxY - minY));
		float sx = +normalization * scale;
		float sy = -normalization * scale;
		float px = tx - (maxX + minX) * sx / 2;
		float py = ty - (maxY + minY) * sy / 2;
		area.update(px, py, sx, sy);
	}

	protected void configure(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	}
}
