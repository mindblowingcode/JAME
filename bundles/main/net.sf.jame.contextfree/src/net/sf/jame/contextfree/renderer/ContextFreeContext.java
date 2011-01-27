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
import net.sf.jame.contextfree.renderer.support.ShapeComparator;
import net.sf.jame.contextfree.renderer.support.UnfinishedShape;

import org.apache.log4j.Logger;

public class ContextFreeContext {
	private static final Logger logger = Logger.getLogger(ContextFreeContext.class);
	public static final float MIN_AREA = 1f;
	private CFDGRuntimeElement runtime;
	private RuleMap ruleMap = new RuleMap();
	private PathMap pathMap = new PathMap();
	private Set<String> buildSet = new LinkedHashSet<String>();
	private Set<ContextFreeShape> expandSet = new LinkedHashSet<ContextFreeShape>();
	private Set<ContextFreeShape> createdSet = new LinkedHashSet<ContextFreeShape>();
	private Set<ContextFreeShape> unfinishedSet = new LinkedHashSet<ContextFreeShape>();
	private Set<ContextFreeShape> finishedSet = new TreeSet<ContextFreeShape>(new ShapeComparator());

	public ContextFreeContext(CFDGRuntimeElement runtime) {
		this.runtime = runtime;
	}

	public int getRenderCount() {
		return finishedSet.size();
	}

	public int getExpandCount() {
		return expandSet.size();
	}
	
	public void render(Graphics2D g2d, ContextFreeArea area) {
		for (ContextFreeShape shape : finishedSet) {
			shape.render(g2d, area);
		}
	}
	
	public void renderPartial(Graphics2D g2d, ContextFreeArea area) {
		for (ContextFreeShape shape : createdSet) {
			shape.render(g2d, area);
		}
	}

	public void commitShapes() {
		finishedSet.addAll(createdSet);
		createdSet.clear();
	}

	public boolean expandShapes() {
		expandSet.addAll(unfinishedSet);
		unfinishedSet.clear();
		for (Iterator<ContextFreeShape> shapes = expandSet.iterator(); shapes.hasNext();) {
			ContextFreeShape next = shapes.next();
			next.expand();
		}
		if (logger.isTraceEnabled()) {
			logger.trace("Expand shapes [" + unfinishedSet.size() + "/" + finishedSet.size() + "]");
		}
		int expanded = expandSet.size();
		expandSet.clear();
		return expanded > 0;
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

	public void buildPathOrRule(ContextFreeState state, ContextFreeBounds globalBounds, ContextFreeBounds shapeBounds, String shapeName) {
		ContextFreePath path = pathMap.get(shapeName);
		if (path != null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Path " + shapeName);
			}
			ContextFreeShape shape = path.createShape(this, state, globalBounds, shapeBounds);
			if (shape != null) {
				createdSet.add(shape);
			}
		} else {
			createShapes(state, globalBounds, shapeBounds, shapeName);
		}
	}
	
	private void createShapes(ContextFreeState state, ContextFreeBounds globalBounds, ContextFreeBounds shapeBounds, String shapeName) {
		if (!buildSet.contains(shapeName)) {
			if (logger.isTraceEnabled()) {
				logger.trace("Rule " + shapeName);
			}
			buildSet.add(shapeName);
			ContextFreeRule rule = ruleMap.get(shapeName);
			if (rule != null) {
				rule.createShapes(this, state, globalBounds, shapeBounds);
			}
			buildSet.remove(shapeName);
		} else {
			unfinishedSet.add(new UnfinishedShape(this, state, globalBounds, shapeBounds, shapeName));
		}
	}
	
	public void expandRule(ContextFreeShape oldShape, ContextFreeState state, ContextFreeBounds globalBounds, ContextFreeBounds prevShapeBounds, String shapeName) {
		if (logger.isTraceEnabled()) {
			logger.trace("Expanding " + shapeName);
		}
		ContextFreeBounds shapeBounds = new ContextFreeBounds(globalBounds.getWidth(), globalBounds.getHeight());
		createShapes(state, globalBounds, shapeBounds, shapeName);
		if (shapeBounds.isValid()) {
			double sx = (shapeBounds.getSizeX() / globalBounds.getSizeX()) * globalBounds.getWidth();
			double sy = (shapeBounds.getSizeY() / globalBounds.getSizeY()) * globalBounds.getHeight();
			double area = sx * sy;
			if (logger.isTraceEnabled()) {
				logger.trace("area = " + area);
			}
			if (Double.isNaN(area) || Double.isInfinite(area) || area < MIN_AREA) {
				if (logger.isTraceEnabled()) {
					logger.trace("Stop recursion for shape " + shapeName);
				}
				unfinishedSet.clear();
			}
		}
	}
	
	private class RuleMap {
		private Map<String, RuleEntry> map = new LinkedHashMap<String, RuleEntry>();

		public void add(ContextFreeRule rule) {
			RuleEntry entry = map.get(rule.getName());
			if (entry == null) {
				entry = new RuleEntry(rule.getName());
				map.put(entry.getName(), entry);
			}
			entry.add(rule);
		}
		
		public void remove(ContextFreeRule rule) {
			RuleEntry entry = map.get(rule.getName());
			if (entry != null) {
				entry.remove(rule);
				if (entry.rules.size() == 0) {
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
		private List<ContextFreeRule> rules = new LinkedList<ContextFreeRule>();
		private float total = 0;
		private String rule;
		
		public RuleEntry(String rule) {
			this.rule = rule;
		}
		
		public String getName() {
			return rule;
		}

		public void add(ContextFreeRule rule) {
			rules.add(rule);
			if (rule.getProbability() != null && rule.getProbability() > 0) {
				total += rule.getProbability();
			} else {
				total += 1;
			}
		}
		
		public void remove(ContextFreeRule rule) {
			rules.remove(rule);
			if (rule.getProbability() != null && rule.getProbability() > 0) {
				total -= rule.getProbability();
			} else {
				total -= 1;
			}
		}

		public ContextFreeRule get() {
			double number = runtime.random() * total;
			double offset = 0;
			for (ContextFreeRule rule : rules) {
				offset += rule.getProbability() > 0 ? rule.getProbability() : 1f;
				if (number <= offset) {
					return rule;
				}
			}
			return null;
		}
	}

	public int getCreatedShapes() {
		return createdSet.size();
	}
}
