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

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jame.contextfree.cfdg.CFDGRuntimeElement;
import net.sf.jame.contextfree.cfdg.figure.FigureRuntimeElement;

public class ContextFreeContext {
	private CFDGRuntimeElement runtime;
	private RuleMap ruleMap = new RuleMap();
	private PathMap pathMap = new PathMap();

	public ContextFreeContext(CFDGRuntimeElement runtime) {
		this.runtime = runtime;
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

	public ContextFreeNode buildPathOrRuleNode(ContextFreeState state, ContextFreeBounds bounds, String figureName) {
		ContextFreePath path = pathMap.get(figureName);
		if (path != null) {
			return path.buildNode(this, state, bounds);
		} else {
			ContextFreeRule rule = ruleMap.get(figureName);
			if (rule != null) {
				return rule.buildNode(this, state, bounds);
			}
		}
		return null;
	}

	public ContextFreeNode buildRuleNode(ContextFreeState state, ContextFreeBounds bounds, String figureName) {
		ContextFreeRule rule = ruleMap.get(figureName);
		if (rule != null) {
			return rule.buildNode(this, state, bounds);
		}
		return null;
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
