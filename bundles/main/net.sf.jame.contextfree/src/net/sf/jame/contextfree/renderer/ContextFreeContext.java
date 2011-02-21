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
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jame.contextfree.cfdg.CFDGRuntimeElement;
import net.sf.jame.contextfree.cfdg.figure.FigureRuntimeElement;
import net.sf.jame.contextfree.renderer.support.CFModification;
import net.sf.jame.contextfree.renderer.support.CFShape;
import net.sf.jame.contextfree.renderer.support.CFShapeComparator;

import org.apache.log4j.Logger;

public class ContextFreeContext {
	private static final Logger logger = Logger.getLogger(ContextFreeContext.class);
	public static final float MIN_SIZE = 0.0001f;
	public static final float SHAPE_BORDER = 2.0f;
	public static final float FIXED_BORDER = 8.0f;
	private CFDGRuntimeElement runtime;
	private RuleMap ruleMap = new RuleMap();
	private PathMap pathMap = new PathMap();
	private ArrayList<CFShape> createdSet = new ArrayList<CFShape>();
	private ArrayList<CFShape> finishedSet = new ArrayList<CFShape>();
	private ArrayList<CFShape> unfinishedSet = new ArrayList<CFShape>();
	private float totalArea;
	private float scaleArea;
	private float minArea;
	private float scale;
	private float currScale;
	private float fixedBorder;
	private float shapeBorder;
	private int width;
	private int height;

	public ContextFreeContext(CFDGRuntimeElement runtime, int width, int height, float border, float minSize) {
		this.runtime = runtime;
	    minSize = (minSize < 0.3f) ? 0.3f : minSize;
	    this.minArea = minSize * minSize;
	    this.width = width;
	    this.height = height;
	    this.fixedBorder = FIXED_BORDER * ((border <= 1) ? border : 1);
	    this.shapeBorder = SHAPE_BORDER * ((border <= 1) ? 0.5f : (border / 2f));
	}
	
	public int getUnfinishedCount() {
		return unfinishedSet.size();
	}

	public int getFinishedCount() {
		return finishedSet.size();
	}

	public int getToDoCount() {
		return createdSet.size();
	}

	public void render(Graphics2D g2d, ContextFreeArea area) {
		for (CFShape shape : finishedSet) {
			//TODO shape.render(g2d, area);
			if (Thread.currentThread().isInterrupted()) {
				break;
			}
		}
	}
	
	public void renderPartial(Graphics2D g2d, ContextFreeArea area) {
		for (CFShape shape : createdSet) {
			//TODO shape.render(g2d, area);
			if (Thread.currentThread().isInterrupted()) {
				break;
			}
		}
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

	public void addShape(CFShape shape) {
		if (shape != null) {
			createdSet.add(shape);
		}
	}

	public void commitShapes() {
		finishedSet.addAll(createdSet);
		createdSet.clear();
		if (logger.isTraceEnabled()) {
			logger.trace("Commit shapes [" + unfinishedSet.size() + "/" + finishedSet.size() + "]");
		}
	}

	public void sortShapes() {
		Collections.sort(finishedSet, new CFShapeComparator());
	}

	public boolean executeShape(CFModification mod) {
		if (unfinishedSet.size() > 0) {
			CFShape unfinishedShape = unfinishedSet.remove(0);
			if (Double.isInfinite(unfinishedShape.area())) {
				return false;
			}
			String shapeName = unfinishedShape.getName();
			processShape(mod, shapeName);
		}
		return true;
	}

	public void processShape(CFModification mod, String shapeName) {
		ContextFreePath path = pathMap.get(shapeName);
		if (path != null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Path " + shapeName);
			}
			path.process(this);
		} else {
			ContextFreeRule rule = ruleMap.get(shapeName);
			if (rule != null) {
				if (logger.isTraceEnabled()) {
					logger.trace("Rule " + shapeName);
				}
				rule.process(this);
			}
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
}
