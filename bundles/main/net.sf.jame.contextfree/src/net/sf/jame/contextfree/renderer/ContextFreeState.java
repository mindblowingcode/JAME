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

public class ContextFreeState implements Cloneable {
	private float targetHue = 0;
	private float targetSaturation = 0.5f;
	private float targetBrightness = 0.5f;
	private float targetAlpha = 1;
	private float currentHue = 0;
	private float currentSaturation = 0.5f;
	private float currentBrightness = 0.5f;
	private float currentAlpha = 1;
	private float x = 0;
	private float y = 0;
	private float z = 0;
	private float sizeX = 1;
	private float sizeY = 1;
	private float flipX = 1;
	private float flipY = 1;
	private float skewX = 1;
	private float skewY = 1;
	private float rotation = 0;

	public float getTargetHue() {
		return targetHue;
	}

	public void setTargetHue(float targetHue) {
		this.targetHue = targetHue;
	}

	public float getTargetSaturation() {
		return targetSaturation;
	}

	public void setTargetSaturation(float targetSaturation) {
		this.targetSaturation = targetSaturation;
	}

	public float getTargetBrightness() {
		return targetBrightness;
	}

	public void setTargetBrightness(float targetBrightness) {
		this.targetBrightness = targetBrightness;
	}

	public float getTargetAlpha() {
		return targetAlpha;
	}

	public void setTargetAlpha(float targetAlpha) {
		this.targetAlpha = targetAlpha;
	}

	public float getCurrentHue() {
		return currentHue;
	}

	public void setCurrentHue(float currentHue) {
		this.currentHue = currentHue;
	}

	public float getCurrentSaturation() {
		return currentSaturation;
	}

	public void setCurrentSaturation(float currentSaturation) {
		this.currentSaturation = currentSaturation;
	}

	public float getCurrentBrightness() {
		return currentBrightness;
	}

	public void setCurrentBrightness(float currentBrightness) {
		this.currentBrightness = currentBrightness;
	}

	public float getCurrentAlpha() {
		return currentAlpha;
	}

	public void setCurrentAlpha(float currentAlpha) {
		this.currentAlpha = currentAlpha;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getSizeX() {
		return sizeX;
	}

	public void setSizeX(float sizeX) {
		this.sizeX = sizeX;
	}

	public float getSizeY() {
		return sizeY;
	}

	public void setSizeY(float sizeY) {
		this.sizeY = sizeY;
	}

	public float getFlipX() {
		return flipX;
	}

	public void setFlipX(float flipX) {
		this.flipX = flipX;
	}

	public float getFlipY() {
		return flipY;
	}

	public void setFlipY(float flipY) {
		this.flipY = flipY;
	}

	public float getSkewX() {
		return skewX;
	}

	public void setSkewX(float skewX) {
		this.skewX = skewX;
	}

	public float getSkewY() {
		return skewY;
	}

	public void setSkewY(float skewY) {
		this.skewY = skewY;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	@Override
	public ContextFreeState clone() {
		ContextFreeState state = new ContextFreeState();
		state.setTargetHue(getTargetHue());
		state.setTargetSaturation(getTargetSaturation());
		state.setTargetBrightness(getTargetBrightness());
		state.setTargetAlpha(getTargetAlpha());
		state.setCurrentHue(getCurrentHue());
		state.setCurrentSaturation(getCurrentSaturation());
		state.setCurrentBrightness(getCurrentBrightness());
		state.setCurrentAlpha(getCurrentAlpha());
		state.setX(getX());
		state.setY(getY());
		state.setZ(getZ());
		state.setSizeX(getSizeX());
		state.setSizeY(getSizeY());
		state.setFlipX(getFlipX());
		state.setFlipY(getFlipY());
		state.setSkewX(getSkewX());
		state.setSkewY(getSkewY());
		state.setRotation(getRotation());
		return state;
	}
}
