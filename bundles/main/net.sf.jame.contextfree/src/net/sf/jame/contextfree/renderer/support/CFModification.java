package net.sf.jame.contextfree.renderer.support;

import java.awt.geom.AffineTransform;

public class CFModification implements Cloneable {
	private AffineTransform at = new AffineTransform();
	private float[] currentHSBA = new float[] { 0, 0, 0, 1 };
	private float[] targetHSBA = new float[] { 0, 0, 0, 1 };
	private float z = 0;

	public void translate(float tx, float ty, float tz) {
		at.translate(tx, ty);
		z += tz;
	}

	public void skew(float sx, float sy) {
		at.shear(sx, sy);
	}

	public void scale(float sx, float sy, float sz) {
		at.scale(sx, sy);
	}

	public void flip(float a) {
		at.rotate(-a);
		at.scale(1, -1);
		at.rotate(+a);
	}

	public void rotate(float a) {
		at.rotate(a);
	}
	
	public void transform(float[] p, float[] q) {
		at.transform(p, 0, q, 0, p.length / 2);
	}

	public void addHue(float value, boolean target) {
		if (target) {
			currentHSBA[0] += (targetHSBA[0] - currentHSBA[0]) * value;
		} else {
			currentHSBA[0] += value;
		}
		if (currentHSBA[0] < 0) currentHSBA[0] = 0;
		if (currentHSBA[0] > 1) currentHSBA[0] = 1;
	}
	
	public void addSaturation(float value, boolean target) {
		if (target) {
			currentHSBA[1] += (targetHSBA[1] - currentHSBA[1]) * value;
		} else {
			currentHSBA[1] += value;
		}
		if (currentHSBA[1] < 0) currentHSBA[1] = 0;
		if (currentHSBA[1] > 1) currentHSBA[1] = 1;
	}

	public void addBrightness(float value, boolean target) {
		if (target) {
			currentHSBA[2] += (targetHSBA[2] - currentHSBA[2]) * value;
		} else {
			currentHSBA[2] += value;
		}
		if (currentHSBA[2] < 0) currentHSBA[2] = 0;
		if (currentHSBA[2] > 1) currentHSBA[2] = 1;
	}

	public void addAlpha(float value, boolean target) {
		if (target) {
			currentHSBA[3] += (targetHSBA[3] - currentHSBA[3]) * value;
		} else {
			currentHSBA[3] += value;
		}
		if (currentHSBA[3] < 0) currentHSBA[3] = 0;
		if (currentHSBA[3] > 1) currentHSBA[3] = 1;
	}

	public void addTargetHue(float value) {
		targetHSBA[0] += value;
		if (targetHSBA[0] < 0) targetHSBA[0] = 0;
		if (targetHSBA[0] > 1) targetHSBA[0] = 1;
	}
	
	public void addTargetSaturation(float value) {
		targetHSBA[1] += value;
		if (targetHSBA[1] < 0) targetHSBA[1] = 0;
		if (targetHSBA[1] > 1) targetHSBA[1] = 1;
	}

	public void addTargetBrightness(float value) {
		targetHSBA[2] += value;
		if (targetHSBA[2] < 0) targetHSBA[2] = 0;
		if (targetHSBA[2] > 1) targetHSBA[2] = 1;
	}

	public void addTargetAlpha(float value) {
		targetHSBA[3] += value;
		if (targetHSBA[3] < 0) targetHSBA[3] = 0;
		if (targetHSBA[3] > 1) targetHSBA[3] = 1;
	}
	
	public float[] getHSBA() {
		return currentHSBA;
	}
	
	public AffineTransform getTransform() {
		return at;
	}

	public float getZ() {
		return z;
	}

	@Override
	public CFModification clone() {
		CFModification mod = new CFModification();
		mod.at.setTransform(at);
		mod.currentHSBA[0] = currentHSBA[0];
		mod.currentHSBA[1] = currentHSBA[1];
		mod.currentHSBA[2] = currentHSBA[2];
		mod.currentHSBA[3] = currentHSBA[3];
		mod.targetHSBA[0] = targetHSBA[0];
		mod.targetHSBA[1] = targetHSBA[1];
		mod.targetHSBA[2] = targetHSBA[2];
		mod.targetHSBA[3] = targetHSBA[3];
		return mod;
	}
}
