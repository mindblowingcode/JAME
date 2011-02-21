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

import net.sf.jame.contextfree.renderer.support.CFModification;
import net.sf.jame.core.util.Color32bit;

import org.apache.log4j.Logger;

/**
 * @author Andrea Medeghini
 */
public final class SimpleContextFreeRenderer extends DefaultContextFreeRenderer {
	private static final Logger logger = Logger.getLogger(SimpleContextFreeRenderer.class);
	
	/**
	 * 
	 */
	public SimpleContextFreeRenderer(final int threadPriority) {
		super(threadPriority);
	}

	@Override
	protected void doRender(boolean dynamicZoom) {
		long totalTime = System.nanoTime();
		long time = System.nanoTime();
		updateTransform();
		float offsetX = getBufferWidth() / 2 + getTile().getImageSize().getX() / 2 - getTile().getTileOffset().getX() - getTile().getTileSize().getX() / 2;
		float offsetY = getBufferHeight() / 2 + getTile().getImageSize().getY() / 2 - getTile().getTileOffset().getY() - getTile().getTileSize().getY() / 2;
		int width = getTile().getImageSize().getX();
		int height = getTile().getImageSize().getY();
		Color32bit background = cfdgRuntime.getBackground();
		String startshape = cfdgRuntime.getStartshape();
		cfdgRuntime.resetRandom();
		ContextFreeContext context = new ContextFreeContext(cfdgRuntime, width, height, 1f, 0.3f);
//		ContextFreeBounds globalBounds = new ContextFreeBounds(width, height);
//		ContextFreeBounds shapeBounds = new ContextFreeBounds(width, height);
		CFModification mod = new CFModification(); 
		context.registerFigures();
		context.processShape(mod, startshape);
//		ContextFreeBounds bounds = new ContextFreeBounds(globalBounds.getWidth(), globalBounds.getHeight());
//		bounds.addPoint(globalBounds.getMinX(), globalBounds.getMinY());
//		bounds.addPoint(globalBounds.getMaxX(), globalBounds.getMaxY());
		if (logger.isDebugEnabled()) {
			long elapsed = (System.nanoTime() - time) / 1000000;
			logger.debug("Build time " + elapsed + "ms");
		}
		percent = 30;
//		while (context.expandShapes()) {
//			if (isInterrupted()) {
//				break;
//			}
//		}
		percent = 70;
		if (logger.isDebugEnabled()) {
			logger.debug("Total shapes " + context.getFinishedCount());
		}
		time = System.nanoTime();
		context.commitShapes();
//		renderImage(context, globalBounds, offsetX, offsetY, background, false);
		if (logger.isDebugEnabled()) {
			long elapsed = (System.nanoTime() - time) / 1000000;
			logger.debug("Render time " + elapsed + "ms");
		}
		percent = 100;
		if (logger.isDebugEnabled()) {
			logger.debug("Total time " + (System.nanoTime() - totalTime) / 1000000 + "ms");
		}
	}
}
