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
package net.sf.jame.contextfree.renderer.support;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import net.sf.jame.contextfree.cfdg.CFDGRuntimeElement;
import net.sf.jame.contextfree.renderer.ContextFreeArea;

import org.apache.log4j.Logger;

public class CDRenderer {
	private static final Logger logger = Logger.getLogger(CDRenderer.class);
	public static final float MIN_SIZE = 0.0001f;
	public static final float SHAPE_BORDER = 2.0f;
	public static final float FIXED_BORDER = 8.0f;
	private CFDGRuntimeElement runtime;
	private ArrayList<CFFinishedShape> finishedSet = new ArrayList<CFFinishedShape>();
	private ArrayList<CFShape> unfinishedSet = new ArrayList<CFShape>();
	private ArrayList<ShapeType> shapeTypes = new ArrayList<ShapeType>();
	private ArrayList<CFRule> rules = new ArrayList<CFRule>();
	private CFBounds bounds;
	private float totalArea;
	private float scaleArea;
	private float minArea;
	private float scale;
	private float currScale;
	private float fixedBorder;
	private float shapeBorder;
	private int width;
	private int height;
	private int variation;
	public static int SHAPETYPE_PATH = 1;
	public static int SHAPETYPE_RULE = 2;
	public static int SHAPETYPE_LOOP_START = 3;
	public static int SHAPETYPE_LOOP_END = 4;
	public static int COMMAND_MODIFICATION = 1;
	public static int COMMAND_LOOP_START = 2;
	public static int COMMAND_LOOP_END = 3;

	public CDRenderer(CFDGRuntimeElement runtime, int width, int height, float border, float minSize) {
		this.runtime = runtime;
		bounds = new CFBounds();
	    minSize = (minSize < 0.3f) ? 0.3f : minSize;
	    this.minArea = minSize * minSize;
	    this.width = width;
	    this.height = height;
	    this.fixedBorder = FIXED_BORDER * ((border <= 1) ? border : 1);
	    this.shapeBorder = SHAPE_BORDER * ((border <= 1) ? 0.5f : (border / 2f));
	    variation = String.valueOf(runtime.getVariation()).hashCode();
	    createShapeTypesAndRules(runtime);
	}
	
	private void createShapeTypesAndRules(CFDGRuntimeElement runtime) {
		// TODO Auto-generated method stub
		
	}

	public int getUnfinishedCount() {
		return unfinishedSet.size();
	}

	public int getFinishedCount() {
		return finishedSet.size();
	}

	public void render(Graphics2D g2d, ContextFreeArea area) {
//		for (CFFinishedShape shape : finishedSet) {
//			//TODO shape.render(g2d, area);
//			if (Thread.currentThread().isInterrupted()) {
//				break;
//			}
//		}
	}
	
	public void renderPartial(Graphics2D g2d, ContextFreeArea area) {
//		for (CFFinishedShape shape : createdSet) {
//			//TODO shape.render(g2d, area);
//			if (Thread.currentThread().isInterrupted()) {
//				break;
//			}
//		}
	}

	public void addUnfinishedShape(CFShape shape) {
		unfinishedSet.add(shape);
	}

	public void addFinishedShape(CFFinishedShape shape) {
		finishedSet.add(shape);
	}

	public void sortShapes() {
		Collections.sort(finishedSet, new CFFinishedShapeComparator());
	}

	public boolean executeShape() {
//		if (unfinishedSet.size() > 0) {
//			CFShape shape = unfinishedSet.remove(0);
//			if (Double.isInfinite(shape.area())) {
//				return false;
//			}
//			processShape(shape);
//		}
		return true;
	}

	public void processShape(CFShape shape) {
//		double area = shape.area();
//		ContextFreeRule rule = ruleMap.get(shape.getName());
//		if (rule != null) {
//			if (!bounds.isValid() || (area * scaleArea >= minArea)) {
//				if (logger.isTraceEnabled()) {
//					logger.trace("Rule " + shape.getName());
//				}
//				rule.process(this, shape);
//			}
//		} else {
//			ContextFreePath path = pathMap.get(shape.getName());
//			if (path != null) {
//				if (logger.isTraceEnabled()) {
//					logger.trace("Path " + shape.getName());
//				}
//				path.process(this, shape);
//			}
//		}
	}
}
