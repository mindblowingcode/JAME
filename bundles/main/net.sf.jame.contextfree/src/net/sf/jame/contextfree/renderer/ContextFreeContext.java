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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import net.sf.jame.contextfree.cfdg.CFDGRuntimeElement;
import net.sf.jame.contextfree.cfdg.FigureRuntimeElement;
import net.sf.jame.contextfree.cfdg.path.PathConfigElement;
import net.sf.jame.contextfree.cfdg.rule.RuleConfigElement;

public class ContextFreeContext {
	private Stack<ContextFreeState> stateStack = new Stack<ContextFreeState>();
	private ContextFreeState state = new ContextFreeState();
	private FigureMap figureMap = new FigureMap();
	private CFDGRuntimeElement runtime;
	private Graphics2D g2d;

	public ContextFreeContext(Graphics2D g2d, CFDGRuntimeElement runtime) {
		this.g2d = g2d;
		this.runtime = runtime;
		for (int i = 0; i < runtime.getFigureCount(); i++) {
			FigureRuntimeElement figure = runtime.getFigure(i);
			figureMap.add(figure);
		}
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

	public void draw(String figureName) {
		// TODO Auto-generated method stub
		FigureRuntimeElement figure = figureMap.get(figureName);
		if (figure != null) {
			figure.draw(this);
		}
	}
	
	private class FigureMap {
		private Map<String, List<FigureRuntimeElement>> map = new LinkedHashMap<String, List<FigureRuntimeElement>>();

		public void add(FigureRuntimeElement figureElement) {
			// TODO Auto-generated method stub
			List<FigureRuntimeElement> entry = map.get(figureElement.getName());
			if (entry == null) {
				entry = new LinkedList<FigureRuntimeElement>();
				map.put(figureElement.getName(), entry);
			}
			if (figureElement.getFigureElement().getClassId().equals(PathConfigElement.CLASS_ID)) {
				entry.clear();
				entry.add(figureElement);
			} else if (figureElement.getFigureElement().getClassId().equals(RuleConfigElement.CLASS_ID)) {
				RuleConfigElement ruleElement = (RuleConfigElement) figureElement.getFigureElement();
				if (ruleElement.getPropability() == 0) {
					for (FigureRuntimeElement entryElement : entry) {
						
					}
				}
			}
		}

		public FigureRuntimeElement get(String figureName) {
			// TODO Auto-generated method stub
			return null;//map.get(figureName);
		} 
	}
}
