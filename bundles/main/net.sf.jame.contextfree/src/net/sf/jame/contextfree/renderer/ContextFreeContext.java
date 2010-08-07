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

import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jame.contextfree.cfdg.CFDGRuntimeElement;
import net.sf.jame.contextfree.cfdg.figure.FigureRuntimeElement;

import org.apache.log4j.Logger;

public class ContextFreeContext {
	private static final Logger logger = Logger.getLogger(ContextFreeContext.class);
	public static final float MIN_SIZE = 5;
	public static final float MAX_SIZE = 1000;
	private CFDGRuntimeElement runtime;
	private RuleMap ruleMap = new RuleMap();
	private PathMap pathMap = new PathMap();
	private Set<ContextFreeShape> renderSet = new TreeSet<ContextFreeShape>(new ContextFreeShapeComparator());
	private Set<ContextFreeShape> expandSet = new HashSet<ContextFreeShape>();
	private Set<ContextFreeShape> createSet = new HashSet<ContextFreeShape>();
	private Set<ContextFreeShape> commitSet = new HashSet<ContextFreeShape>();
	private Set<String> buildSet = new LinkedHashSet<String>();

	public ContextFreeContext(CFDGRuntimeElement runtime) {
		this.runtime = runtime;
	}

	public int getShapesCount() {
		return renderSet.size();
	}

	public int getExpandCount() {
		return expandSet.size();
	}
	
	public void renderShape(Graphics2D g2d, ContextFreeArea area) {
		for (ContextFreeShape shape : renderSet) {
			shape.render(g2d, area);
		}
	}

	public boolean expandShapes() {
		boolean expand = false;
		for (Iterator<ContextFreeShape> shapes = expandSet.iterator(); shapes.hasNext();) {
			ContextFreeShape next = shapes.next();
			if (next.expand()) {
				expand |= true;
				commitShapes();
				shapes.remove();
			}
		}
		renderSet.addAll(expandSet);
		expandSet.clear();
		publishShapes();
		return expand;
	}
	
	public void registerFigures() {
		for (int i = 0; i < runtime.getFigureElementCount(); i++) {
			FigureRuntimeElement figure = runtime.getFigureElement(i);
			figure.register(this);
		}
	}

	public void registerPath(ContextFreePath path) {
		pathMap.add(path);
	}

	public void registerRule(ContextFreeRule rule) {
		ruleMap.add(rule);
	}

	public void unregisterPath(ContextFreePath path) {
		pathMap.remove(path);
	}

	public void unregisterRule(ContextFreeRule rule) {
		ruleMap.remove(rule);
	}

	public void buildPathOrRule(ContextFreeState state, ContextFreeBounds bounds, String shape) {
		ContextFreePath path = pathMap.get(shape);
		if (path != null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Path " + shape);
			}
			ContextFreeShape pathShape = path.createShape(this, state, bounds);
			if (pathShape != null) {
				createSet.add(pathShape);
			}
		} else {
			createShapes(state, bounds, shape);
		}
	}

	public void buildRule(ContextFreeState state, ContextFreeBounds bounds, String shape) {
		createShapes(state, bounds, shape);
		commitShapes();
		publishShapes();
	}
	
	private void createShapes(ContextFreeState state, ContextFreeBounds bounds,	String shape) {
		if (!buildSet.contains(shape)) {
			if (logger.isTraceEnabled()) {
				logger.trace("Rule " + shape);
			}
			buildSet.add(shape);
			ContextFreeRule rule = ruleMap.get(shape);
			if (rule != null) {
				rule.createShapes(this, state, bounds);
			}
			buildSet.remove(shape);
		} else {
			createSet.add(new RecursiveContextFreeShape(this, state, bounds, shape));
		}
	}
	
	public void expandRule(ContextFreeShape oldShape, ContextFreeState state, ContextFreeBounds bounds, String shape) {
		ContextFreeBounds nodeBounds = new ProxyContextFreeBounds(bounds);
		if (logger.isTraceEnabled()) {
			logger.trace("Expanding " + shape);
		}
		createShapes(state, nodeBounds, shape);
		if (createSet.size() > 0 && nodeBounds.isValid()) {
			if (bounds.isValid()) {
				double maxX = nodeBounds.getMaxX();
				double maxY = nodeBounds.getMaxY();
				double minX = nodeBounds.getMinX();
				double minY = nodeBounds.getMinY();
				double bMaxX = bounds.getMaxX();
				double bMinX = bounds.getMinX();
				double bMaxY = bounds.getMaxY();
				double bMinY = bounds.getMinY();
				int bWidth = bounds.getWidth();
				int bHeight = bounds.getHeight();
				double sx = ((maxX - minX) * bWidth) / (bMaxX - bMinX);
				double sy = ((maxY - minY) * bHeight) / (bMaxY - bMinY);
				if ((sx < MIN_SIZE && sy < MIN_SIZE) || (sx > MAX_SIZE && sy > MAX_SIZE) || (Math.abs(sx - bWidth) < 0.0001 && Math.abs(sy - bHeight) < 0.0001)) {
					if (logger.isTraceEnabled()) {
						logger.trace("Shape too small " + shape);
					}
					clearShapes();
				}
			}
		} else {
			clearShapes();
		}
	}

	private void clearShapes() {
		createSet.clear();
	}

	private void publishShapes() {
		expandSet.addAll(commitSet);
		commitSet.clear();
	}

	private boolean commitShapes() {
		boolean size = createSet.size() > 0;
		commitSet.addAll(createSet);
		if (logger.isTraceEnabled()) {
			logger.trace("Commit shapes [" + createSet.size() + "/" + commitSet.size() + "]");
		}
		clearShapes();
		return size;
	}
	
	private class RuleMap {
		private Map<String, RuleEntry> map = new LinkedHashMap<String, RuleEntry>();

		public void add(ContextFreeRule rule) {
			RuleEntry entry = map.get(rule.getName());
			if (entry == null) {
				entry = new RuleEntry();
				map.put(rule.getName(), entry);
			}
			entry.add(rule);
		}
		
		public void remove(ContextFreeRule rule) {
			RuleEntry entry = map.get(rule.getName());
			if (entry != null) {
				entry.remove(rule);
				if (entry.runtimes.size() == 0) {
					map.remove(rule.getName());
				}
			}
		}

		public ContextFreeRule get(String ruleName) {
			RuleEntry entry = map.get(ruleName);
			if (entry != null) {
				return entry.get();
			}
			return null;
		} 
	}
	
	private class PathMap {
		private Map<String, ContextFreePath> map = new LinkedHashMap<String, ContextFreePath>();

		public void add(ContextFreePath path) {
			map.put(path.getName(), path);
		}

		public void remove(ContextFreePath path) {
			map.remove(path);
		}

		public ContextFreePath get(String pathName) {
			return map.get(pathName);
		} 
	}
	
	private class RuleEntry {
		private List<ContextFreeRule> runtimes = new LinkedList<ContextFreeRule>();
		private float total = 0;
		
		public void add(ContextFreeRule rule) {
			runtimes.add(rule);
			if (rule.getProbability() > 0) {
				total += rule.getProbability();
			} else {
				total += 1;
			}
		}
		
		public void remove(ContextFreeRule rule) {
			runtimes.remove(rule);
			if (rule.getProbability() > 0) {
				total -= rule.getProbability();
			} else {
				total -= 1;
			}
		}

		public ContextFreeRule get() {
			float number = (float) Math.random() * total;
			float offset = 0;
			for (ContextFreeRule runtime : runtimes) {
				offset += (runtime.getProbability() > 0) ? runtime.getProbability() : 1f;
				if (number <= offset) {
					return runtime;
				}
			}
			return null;
		}
	}
}
