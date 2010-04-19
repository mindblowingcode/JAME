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

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.CFDGRuntimeElement;
import net.sf.jame.contextfree.cfdg.FigureRuntimeElement;
import net.sf.jame.contextfree.cfdg.extension.PrimitiveExtensionRuntime;
import net.sf.jame.contextfree.cfdg.path.PathConfigElement;
import net.sf.jame.contextfree.cfdg.path.PathRuntimeElement;
import net.sf.jame.contextfree.cfdg.rule.RuleConfigElement;
import net.sf.jame.contextfree.cfdg.rule.RuleRuntimeElement;
import net.sf.jame.core.extension.Extension;

public class ContextFreeContext {
	private Stack<ContextFreeState> stateStack = new Stack<ContextFreeState>();
	private ContextFreeState state = new ContextFreeState();
	private PrimitiveMap primitiveMap = new PrimitiveMap();
	private RuleMap ruleMap = new RuleMap();
	private PathMap pathMap = new PathMap();
	//private CFDGRuntimeElement runtime;
	private Graphics2D g2d;

	public ContextFreeContext(Graphics2D g2d, CFDGRuntimeElement runtime) {
		this.g2d = g2d;
		//this.runtime = runtime;
		for (Extension<PrimitiveExtensionRuntime> extension : ContextFreeRegistry.getInstance().getPrimitiveRegistry().getExtensionList()) {
			try {
				PrimitiveExtensionRuntime primitiveRuntime = extension.createExtensionRuntime();
				primitiveMap.add(primitiveRuntime.getName(), primitiveRuntime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < runtime.getFigureCount(); i++) {
			FigureRuntimeElement figureElement = runtime.getFigure(i);
			if (figureElement.getFigureElement().getClassId().equals(PathConfigElement.CLASS_ID)) {
				pathMap.add((PathRuntimeElement) figureElement);
			} else if (figureElement.getFigureElement().getClassId().equals(RuleConfigElement.CLASS_ID)) {
				ruleMap.add((RuleRuntimeElement) figureElement);
			}
		}
	}

	public void drawShape(Shape shape) {
		g2d.draw(shape);
	}

	public void fillShape(Shape shape) {
		g2d.fill(shape);
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

	public void setCurrentAlpha(float currentAlpha) {
		state.setCurrentAlpha(currentAlpha);
	}

	public void setCurrentBrightness(float currentBrightness) {
		state.setCurrentBrightness(currentBrightness);
	}

	public void setCurrentHue(float currentHue) {
		state.setCurrentHue(currentHue);
	}

	public void setCurrentSaturation(float currentSaturation) {
		state.setCurrentSaturation(currentSaturation);
	}

	public void setFlipX(float flipX) {
		state.setFlipX(flipX);
	}

	public void setFlipY(float flipY) {
		state.setFlipY(flipY);
	}

	public void setRotation(float rotation) {
		state.setRotation(rotation);
	}

	public void setSizeX(float sizeX) {
		state.setSizeX(sizeX);
	}

	public void setSizeY(float sizeY) {
		state.setSizeY(sizeY);
	}

	public void setSkewX(float skewX) {
		state.setSkewX(skewX);
	}

	public void setSkewY(float skewY) {
		state.setSkewY(skewY);
	}

	public void setTargetAlpha(float targetAlpha) {
		state.setTargetAlpha(targetAlpha);
	}

	public void setTargetBrightness(float targetBrightness) {
		state.setTargetBrightness(targetBrightness);
	}

	public void setTargetHue(float targetHue) {
		state.setTargetHue(targetHue);
	}

	public void setTargetSaturation(float targetSaturation) {
		state.setTargetSaturation(targetSaturation);
	}

	public void setX(float x) {
		state.setX(x);
	}

	public void setY(float y) {
		state.setY(y);
	}

	public void setZ(float z) {
		state.setZ(z);
	}

	public void drawPathOrRule(String figureName) {
		PathRuntimeElement pathRuntime = pathMap.get(figureName);
		if (pathRuntime != null) {
			pathRuntime.draw(this);
		} else {
			PrimitiveExtensionRuntime primitiveRuntime = primitiveMap.get(figureName);
			if (primitiveRuntime != null) {
				primitiveRuntime.draw(this);
			} else {
				RuleRuntimeElement ruleRuntime = ruleMap.get(figureName);
				if (ruleRuntime != null) {
					ruleRuntime.draw(this);
				}
			}
		}
	}

	public void drawPath(String figureName) {
		PathRuntimeElement pathRuntime = pathMap.get(figureName);
		if (pathRuntime != null) {
			pathRuntime.draw(this);
		} else {
			PrimitiveExtensionRuntime primitiveRuntime = primitiveMap.get(figureName);
			if (primitiveRuntime != null) {
				primitiveRuntime.draw(this);
			}
		}
	}

	public void drawRule(String figureName) {
		RuleRuntimeElement ruleElement = ruleMap.get(figureName);
		if (ruleElement != null) {
			ruleElement.draw(this);
		}
	}
	
	private class RuleMap {
		private Map<String, RuleEntry> map = new LinkedHashMap<String, RuleEntry>();

		public void add(RuleRuntimeElement ruleElement) {
			RuleEntry entry = map.get(ruleElement.getName());
			if (entry == null) {
				entry = new RuleEntry();
				map.put(ruleElement.getName(), entry);
			}
			entry.add(ruleElement);
		}
		
		public RuleRuntimeElement get(String ruleName) {
			RuleEntry entry = map.get(ruleName);
			if (entry != null) {
				return entry.get();
			}
			return null;
		} 
	}
	
	private class PrimitiveMap {
		private Map<String, PrimitiveExtensionRuntime> map = new LinkedHashMap<String, PrimitiveExtensionRuntime>();

		public void add(String name, PrimitiveExtensionRuntime primitiveElement) {
			map.put(name, primitiveElement);
		}

		public PrimitiveExtensionRuntime get(String primitiveName) {
			return map.get(primitiveName);
		} 
	}
	
	private class PathMap {
		private Map<String, PathRuntimeElement> map = new LinkedHashMap<String, PathRuntimeElement>();

		public void add(PathRuntimeElement pathElement) {
			map.put(pathElement.getName(), pathElement);
		}

		public PathRuntimeElement get(String pathName) {
			return map.get(pathName);
		} 
	}
	
	private class RuleEntry {
		private List<RuleRuntimeElement> elements = new LinkedList<RuleRuntimeElement>();
		private float total = 0;
		
		public void add(RuleRuntimeElement ruleElement) {
			elements.add(ruleElement);
			if (ruleElement.getPropability() > 0) {
				total += ruleElement.getPropability();
			} else {
				total += 1;
			}
		}
		
		public RuleRuntimeElement get() {
			float number = (float) Math.random() * total;
			float offset = 0;
			for (RuleRuntimeElement element : elements) {
				offset += (element.getPropability() > 0) ? element.getPropability() : 1f;
				if (number <= offset) {
					return element;
				}
			}
			return null;
		}
	}
}
