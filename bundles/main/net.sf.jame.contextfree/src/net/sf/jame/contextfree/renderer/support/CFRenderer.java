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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import org.apache.log4j.Logger;

public class CFRenderer {
	private static final Logger logger = Logger.getLogger(CFRenderer.class);
	public static final int MAX_SHAPES = 5000000;
	public static final float MIN_SIZE = 0.0001f;
	public static final float SHAPE_BORDER = 2.0f;
	public static final float FIXED_BORDER = 8.0f;
	private final Random random;
	private final ArrayList<CFFinishedShape> finishedSet = new ArrayList<CFFinishedShape>();
	private final ArrayList<CFShape> unfinishedSet = new ArrayList<CFShape>();
	private final CFContext context;
	private final CFBounds bounds;
	private float totalArea;
	private float scaleArea;
	private float minArea;
	private float scale;
	private float currScale;
	private float fixedBorder;
	private float shapeBorder;
	private int width;
	private int height;
	private boolean requestStop;
	private AffineTransform currTrans;
	private boolean fullOutput;

	public CFRenderer(CFContext context, int variation, int width, int height, float border, float minSize) {
		this.context = context;
		bounds = new CFBounds();
	    minSize = (minSize < 0.3f) ? 0.3f : minSize;
	    this.minArea = minSize * minSize;
	    this.scaleArea = 1;
	    this.width = width;
	    this.height = height;
	    this.fixedBorder = FIXED_BORDER * ((border <= 1) ? border : 1);
	    this.shapeBorder = SHAPE_BORDER * ((border <= 1) ? 0.5f : (border / 2f));
	    random = new Random(variation);
	    rescale(width, height, false);
	    if (context.isSized() || context.isTiled()) {
	    	fixedBorder = shapeBorder = 0.0f;
	    	bounds.setMinX(-context.getTileX() / 2.0);
	    	bounds.setMinY(-context.getTileY() / 2.0);
	    	bounds.setMaxX(+context.getTileX() / 2.0);
	    	bounds.setMaxY(+context.getTileY() / 2.0);
	        scaleArea = currScale * currScale;
	    }
	}
	
	public int getUnfinishedCount() {
		return unfinishedSet.size();
	}

	public int getFinishedCount() {
		return finishedSet.size();
	}

	public CFShape nextUnfinishedShape() {
		return unfinishedSet.remove(0);
	}

	public void render(Graphics2D g2d) {
		CFColor c = context.getBackground();
		Color color = Color.getHSBColor(c.getHue() / 360, c.getSaturation(), c.getBrightness());
		Composite composite = AlphaComposite.Src.derive(1f);
		AffineTransform tmpTransform = g2d.getTransform();
		Composite tmpComposite = g2d.getComposite();
		Color tmpColor = g2d.getColor();
		g2d.setComposite(composite);
		g2d.setColor(color);
		g2d.fillRect(-width / 2, -height / 2, width, height);
		double d = Math.max(bounds.getSizeX(), bounds.getSizeY());
		g2d.scale(1 / d, 1 / d);
		g2d.translate(-bounds.getCenterX(), -bounds.getCenterY());
		CFShapeRenderer render = null;
		if (context.isTiled()) {
			render = new TiledShapeRenderer(g2d, context, width, height);
		} else {
			render = new SimpleShapeRenderer(g2d, context, width, height);
		}
		for (CFFinishedShape shape : finishedSet) {
			shape.render(render);
			if (Thread.currentThread().isInterrupted()) {
				break;
			}
		}
		g2d.setTransform(tmpTransform);
		g2d.setComposite(tmpComposite);
		g2d.setColor(tmpColor);
	}
	
	private void rescale(int width, int height, boolean last) {
		AffineTransform transform = new AffineTransform();
	    float scale = (float) bounds.computeScale(width, height, fixedBorder, transform, context.isTiled() || context.isSized());
	    if (last || currScale == 0.0 || (currScale * 0.90) > scale) {
	        currScale = scale;
	        currTrans = transform;
	        fullOutput = true;
	    }
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

	public void executeShape(CFShape shape) {
		Stack<CFShape> shapeStack = new Stack<CFShape>();
		Stack<Integer> counterStack = new Stack<Integer>();
		Stack<Integer> replacementStack = new Stack<Integer>();
		CFRule rule = context.findRule(shape.getInitialShapeType(), random.nextDouble());
		for (int i = 0; i < rule.getReplacementCount(); i++) {
			if (requestStop) {
				shapeStack.clear();
				counterStack.clear();
				replacementStack.clear();
				return;
			}
			if (rule.getReplacement(i).getShapeType() == ShapeType.TYPE_LOOP_START) {
				shapeStack.push(shape);
				counterStack.push(0);
				replacementStack.push(i);
				continue;
			}
			if (rule.getReplacement(i).getShapeType() == ShapeType.TYPE_LOOP_END) {
				if (replacementStack.get(0) == i - 2) {
					CFShape s = shape.clone();
					shape = shapeStack.get(0);
					for (int c = 1; c < rule.getReplacement(i).getLoopCount(); c++) {
						s.concatenate(rule.getReplacement(i));
						CFShape t = s.clone();
						t.concatenate(rule.getReplacement(i - 1));
						processShape(t);
						if (requestStop) {
							break;
						}
					}
					shapeStack.pop();
					counterStack.pop();
					replacementStack.pop();
				} else {
					counterStack.set(0, counterStack.get(0) + 1);
					if (counterStack.get(0) >= rule.getReplacement(i).getLoopCount()) {
						shape = shapeStack.pop(); 
						counterStack.pop();
						replacementStack.pop();
					} else {
						shape.concatenate(rule.getReplacement(i));
						i = replacementStack.get(0);
					}
					continue;
				}
			}
			CFShape t = shape.clone();
			t.concatenate(rule.getReplacement(i));
			processShape(t);
		}
	}

	public void processShape(CFShape shape) {
		double area = shape.area();
		if (Double.isInfinite(area)) {
			requestStop = true;
			return;
		}
		ShapeType shapeType = context.shapeType(shape.getInitialShapeType());
		if (shapeType.getType() == ShapeType.TYPE_RULE && shapeType.hasRules()) {
			if (!bounds.isValid() || area * scaleArea >= minArea) {
				unfinishedSet.add(shape);
			}
		} else if (shapeType.getType() == ShapeType.TYPE_PATH) {
			CFShape tmpShape = shape.clone();
			CFRule rule = context.findRule(tmpShape.getInitialShapeType(), 0);
			CFPath path = rule.getPath();
			CFPathAttributes attributes = rule.getPathAttributes();
			if (attributes == null) {
				attributes = new CFPathAttributes();
				attributes.addAttribute(new CFPathAttribute(CFPathCommand.FILL));
			}
			Stack<CFShape> shapeStack = new Stack<CFShape>();
			Stack<Integer> counterStack = new Stack<Integer>();
			Stack<CFPathAttribute> attributeStack = new Stack<CFPathAttribute>();
			for (int i = 0; i < attributes.getAttributeCount(); i++) {
				CFPathAttribute attribute = attributes.getAttribute(i);
				if (attribute.getCommand() == CFPathCommand.LOOP_START) {
					shapeStack.push(tmpShape);
					counterStack.push(0);
					attributeStack.push(attribute);
					continue;
				}
				if (attribute.getCommand() == CFPathCommand.LOOP_END) {
					counterStack.set(0, counterStack.get(0) + 1);
					if (counterStack.get(0) >= attribute.getCount()) {
						tmpShape = shapeStack.pop();
						counterStack.pop();
						attributeStack.pop();
					} else {
						tmpShape.getModification().concatenate(attribute.getModification());
						attribute = attributeStack.get(0);
					}
					continue;
				}
				CFShape finalShape = tmpShape.clone();
				finalShape.getModification().concatenate(attribute.getModification());
				totalArea += finalShape.area() * attribute.area();
				CFPathAttribute finalAttribute = attribute.clone();
				finalAttribute.setModification(finalShape.getModification());
				if (Double.isInfinite(finalAttribute.area())) {
					requestStop = true;
					return;
				}
				finishedSet.add(new CFFinishedShape(path, finalAttribute, totalArea));
				if (!context.isTiled() && !context.isSized()) {
					if (scale == 0) {
						scale = (float) ((width + height) / Math.sqrt(finalAttribute.area()));
					}
					bounds.update(path, finalAttribute, scale);
					scale = bounds.computeScale(width, height, fixedBorder, false);
					scaleArea = scale * scale;
				}
			}
		} else {
			logger.error("Shape with no rule: " + context.decodeShapeName(shape.getInitialShapeType()));
		}
	}

	public void dump() {
		for (CFFinishedShape shape : finishedSet) {
			System.out.println(shape);
		}	
	}
}
