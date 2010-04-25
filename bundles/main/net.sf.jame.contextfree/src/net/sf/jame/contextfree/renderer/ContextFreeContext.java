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
import java.awt.Shape;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import net.sf.jame.contextfree.cfdg.CFDGRuntimeElement;
import net.sf.jame.contextfree.cfdg.figure.FigureRuntimeElement;
import net.sf.jame.contextfree.extensions.figure.PathFigureConfig;
import net.sf.jame.contextfree.extensions.figure.PathFigureRuntime;
import net.sf.jame.contextfree.extensions.figure.RuleFigureConfig;
import net.sf.jame.contextfree.extensions.figure.RuleFigureRuntime;

public class ContextFreeContext {
	private Stack<ContextFreeState> stateStack = new Stack<ContextFreeState>();
	private ContextFreeState state = new ContextFreeState();
	private RuleMap ruleMap = new RuleMap();
	private PathMap pathMap = new PathMap();
	private Graphics2D g2d;

	public ContextFreeContext(Graphics2D g2d, CFDGRuntimeElement runtime) {
		this.g2d = g2d;
		for (int i = 0; i < runtime.getFigureElementCount(); i++) {
			FigureRuntimeElement figure = runtime.getFigureElement(i);
			figure.registerFigure(this);
		}
	}

	public void registerPath(PathFigureRuntime<? extends PathFigureConfig> pathRuntime) {
		pathMap.add(pathRuntime);
	}

	public void registerRule(RuleFigureRuntime<? extends RuleFigureConfig> ruleRuntime) {
		ruleMap.add(ruleRuntime);
	}

	public void drawShape(Shape shape) {
		g2d.draw(shape);
	}

	public void fillShape(Shape shape) {
		g2d.fill(shape);
	}

	public ContextFreeState getState() {
		return state;
	}
	
	public void pushState() {
		stateStack.push(state);
		state = state.clone();
	}

	public void popState() {
		state = stateStack.pop();
	}

	public float getCurrentAlpha() {
		return state.getCurrentAlpha();
	}

	public float getCurrentBrightness() {
		return state.getCurrentBrightness();
	}

	public float getCurrentHue() {
		return state.getCurrentHue();
	}

	public float getCurrentSaturation() {
		return state.getCurrentSaturation();
	}

	public float getFlipX() {
		return state.getFlipX();
	}

	public float getFlipY() {
		return state.getFlipY();
	}

	public float getRotation() {
		return state.getRotation();
	}

	public float getSizeX() {
		return state.getSizeX();
	}

	public float getSizeY() {
		return state.getSizeY();
	}

	public float getSkewX() {
		return state.getSkewX();
	}

	public float getSkewY() {
		return state.getSkewY();
	}

	public float getTargetAlpha() {
		return state.getTargetAlpha();
	}

	public float getTargetBrightness() {
		return state.getTargetBrightness();
	}

	public float getTargetHue() {
		return state.getTargetHue();
	}

	public float getTargetSaturation() {
		return state.getTargetSaturation();
	}

	public float getX() {
		return state.getX();
	}

	public float getY() {
		return state.getY();
	}

	public float getZ() {
		return state.getZ();
	}

//	public void setCurrentAlpha(float currentAlpha) {
//		state.setCurrentAlpha(currentAlpha);
//	}
//
//	public void setCurrentBrightness(float currentBrightness) {
//		state.setCurrentBrightness(currentBrightness);
//	}
//
//	public void setCurrentHue(float currentHue) {
//		state.setCurrentHue(currentHue);
//	}
//
//	public void setCurrentSaturation(float currentSaturation) {
//		state.setCurrentSaturation(currentSaturation);
//	}
//
//	public void setFlipX(float flipX) {
//		state.setFlipX(flipX);
//	}
//
//	public void setFlipY(float flipY) {
//		state.setFlipY(flipY);
//	}
//
//	public void setRotation(float rotation) {
//		state.setRotation(rotation);
//	}
//
//	public void setSizeX(float sizeX) {
//		state.setSizeX(sizeX);
//	}
//
//	public void setSizeY(float sizeY) {
//		state.setSizeY(sizeY);
//	}
//
//	public void setSkewX(float skewX) {
//		state.setSkewX(skewX);
//	}
//
//	public void setSkewY(float skewY) {
//		state.setSkewY(skewY);
//	}
//
//	public void setTargetAlpha(float targetAlpha) {
//		state.setTargetAlpha(targetAlpha);
//	}
//
//	public void setTargetBrightness(float targetBrightness) {
//		state.setTargetBrightness(targetBrightness);
//	}
//
//	public void setTargetHue(float targetHue) {
//		state.setTargetHue(targetHue);
//	}
//
//	public void setTargetSaturation(float targetSaturation) {
//		state.setTargetSaturation(targetSaturation);
//	}
//
//	public void setX(float x) {
//		state.setX(x);
//	}
//
//	public void setY(float y) {
//		state.setY(y);
//	}
//
//	public void setZ(float z) {
//		state.setZ(z);
//	}

	public void drawPathOrRule(String figureName) {
		PathFigureRuntime<? extends PathFigureConfig> pathRuntime = pathMap.get(figureName);
		if (pathRuntime != null) {
			pathRuntime.draw(this);
		} else {
			RuleFigureRuntime<? extends RuleFigureConfig> ruleRuntime = ruleMap.get(figureName);
			if (ruleRuntime != null) {
				ruleRuntime.draw(this);
			}
		}
	}

	public void preparePathOrRule(String figureName) {
		PathFigureRuntime<? extends PathFigureConfig> pathRuntime = pathMap.get(figureName);
		if (pathRuntime != null) {
			pathRuntime.prepare(this);
		} else {
			RuleFigureRuntime<? extends RuleFigureConfig> ruleRuntime = ruleMap.get(figureName);
			if (ruleRuntime != null) {
				ruleRuntime.prepare(this);
			}
		}
	}

	public void drawRule(String figureName) {
		RuleFigureRuntime<? extends RuleFigureConfig> ruleRuntime = ruleMap.get(figureName);
		if (ruleRuntime != null) {
			ruleRuntime.draw(this);
		}
	}

	public void prepareRule(String figureName) {
		RuleFigureRuntime<? extends RuleFigureConfig> ruleRuntime = ruleMap.get(figureName);
		if (ruleRuntime != null) {
			ruleRuntime.prepare(this);
		}
	}
	
	private class RuleMap {
		private Map<String, RuleEntry> map = new LinkedHashMap<String, RuleEntry>();

		public void add(RuleFigureRuntime<? extends RuleFigureConfig> ruleRuntime) {
			RuleEntry entry = map.get(ruleRuntime.getName());
			if (entry == null) {
				entry = new RuleEntry();
				map.put(ruleRuntime.getName(), entry);
			}
			entry.add(ruleRuntime);
		}
		
		public RuleFigureRuntime<? extends RuleFigureConfig> get(String ruleName) {
			RuleEntry entry = map.get(ruleName);
			if (entry != null) {
				return entry.get();
			}
			return null;
		} 
	}
	
	private class PathMap {
		private Map<String, PathFigureRuntime<? extends PathFigureConfig>> map = new LinkedHashMap<String, PathFigureRuntime<? extends PathFigureConfig>>();

		public void add(PathFigureRuntime<? extends PathFigureConfig> pathRuntime) {
			map.put(pathRuntime.getName(), pathRuntime);
		}

		public PathFigureRuntime<? extends PathFigureConfig> get(String pathName) {
			return map.get(pathName);
		} 
	}
	
	private class RuleEntry {
		private List<RuleFigureRuntime<? extends RuleFigureConfig>> runtimes = new LinkedList<RuleFigureRuntime<? extends RuleFigureConfig>>();
		private float total = 0;
		
		public void add(RuleFigureRuntime<? extends RuleFigureConfig> ruleRuntime) {
			runtimes.add(ruleRuntime);
			if (ruleRuntime.getPropability() > 0) {
				total += ruleRuntime.getPropability();
			} else {
				total += 1;
			}
		}
		
		public RuleFigureRuntime<? extends RuleFigureConfig> get() {
			float number = (float) Math.random() * total;
			float offset = 0;
			for (RuleFigureRuntime<? extends RuleFigureConfig> runtime : runtimes) {
				offset += (runtime.getPropability() > 0) ? runtime.getPropability() : 1f;
				if (number <= offset) {
					return runtime;
				}
			}
			return null;
		}
	}
}
