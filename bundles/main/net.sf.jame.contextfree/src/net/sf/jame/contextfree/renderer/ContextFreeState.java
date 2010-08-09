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

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;

public class ContextFreeState implements Cloneable {
	private AffineTransform at = new AffineTransform();
	private float[] currentHSBA = new float[] { 0, 1, 0, 1 };
	private float[] targetHSBA = new float[] { 0, 1, 0, 1 };
	private ExtendedGeneralPath path;
	private boolean toFill = true;
	private boolean moveTo = false;
	private float x1 = 0;
	private float y1 = 0;
	private float x = 0;
	private float y = 0;
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
	
	@Override
	public ContextFreeState clone() {
		ContextFreeState state = new ContextFreeState();
		state.at.setTransform(at);
		state.currentHSBA[0] = currentHSBA[0];
		state.currentHSBA[1] = currentHSBA[1];
		state.currentHSBA[2] = currentHSBA[2];
		state.currentHSBA[3] = currentHSBA[3];
		state.targetHSBA[0] = targetHSBA[0];
		state.targetHSBA[1] = targetHSBA[1];
		state.targetHSBA[2] = targetHSBA[2];
		state.targetHSBA[3] = targetHSBA[3];
		state.z = z;
		return state;
	}
	
	private ExtendedGeneralPath generalPath() {
		if (!toFill) {
			path = null;
			toFill = true;
		}
		if (path == null) {
			path = new ExtendedGeneralPath();
			moveTo = false;
		}
		return path;
	}
	
	private void ensureMoveTo() {
		if (!moveTo) {
			moveTo = true;
			moveTo(0, 0);
		}
	}

	public void moveTo(float x, float y) {
		this.x = x;
		this.y = y;
		this.x1 = x;
		this.y1 = y;
		ExtendedGeneralPath path = generalPath();
		path.moveTo(x, y);
	}

	public void lineTo(float x, float y) {
		ensureMoveTo();
		this.x = x;
		this.y = y;
		this.x1 = x;
		this.y1 = y;
		ExtendedGeneralPath path = generalPath();
		path.lineTo(x, y);
	}

	public void arcTo(float x, float y, float rx, float ry, float angle, boolean largeArcFlag, boolean sweepFlag) {
		ensureMoveTo();
		this.x = x;
		this.y = y;
		this.x1 = x;
		this.y1 = y;
		ExtendedGeneralPath path = generalPath();
		path.arcTo(rx, ry, angle, largeArcFlag, sweepFlag, x, y);
	}
	
	public void quadTo(float x, float y, float x1, float y1) {
		ensureMoveTo();
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
		ExtendedGeneralPath path = generalPath();
		path.quadTo(x1, y1, x, y);
	}

	public void quadTo(float x, float y) {
		ensureMoveTo();
		this.x = x;
		this.y = y;
		this.x1 = x + x - x1;
		this.y1 = y + y - y1;
		ExtendedGeneralPath path = generalPath();
		path.quadTo(x1, y1, x, y);
	}
	
	public void curveTo(float x, float y, float x1, float y1, float x2, float y2) {
		ensureMoveTo();
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
		ExtendedGeneralPath path = generalPath();
		path.curveTo(x1, y1, x2, y2, x, y);
	}
	
	public void curveTo(float x, float y, float x2, float y2) {
		ensureMoveTo();
		this.x = x;
		this.y = y;
		this.x1 = x + x - x1;
		this.y1 = y + y - y1;
		ExtendedGeneralPath path = generalPath();
		path.curveTo(x1, y1, x2, y2, x, y);
	}
	
	public void moveRel(float x, float y) {
		ensureMoveTo();
		this.x += x;
		this.y += y;
		x = this.x;
		y = this.y;
		this.x1 = x;
		this.y1 = y;
		ExtendedGeneralPath path = generalPath();
		path.moveTo(x, y);
	}

	public void lineRel(float x, float y) {
		ensureMoveTo();
		this.x += x;
		this.y += y;
		x = this.x;
		y = this.y;
		this.x1 = x;
		this.y1 = y;
		ExtendedGeneralPath path = generalPath();
		path.lineTo(x, y);
	}
	
	public void arcRel(float x, float y, float rx, float ry, float angle, boolean largeArcFlag, boolean sweepFlag) {
		ensureMoveTo();
		this.x += x;
		this.y += y;
		x = this.x;
		y = this.y;
		this.x1 = x;
		this.y1 = y;
		ExtendedGeneralPath path = generalPath();
		path.arcTo(rx, ry, angle, largeArcFlag, sweepFlag, x, y);
	}
	
	public void quadRel(float x, float y, float x1, float y1) {
		ensureMoveTo();
		this.x += x;
		this.y += y;
		x = this.x;
		y = this.y;
		this.x1 = x1;
		this.y1 = y1;
		ExtendedGeneralPath path = generalPath();
		path.quadTo(x1, y1, x, y);
	}

	public void quadRel(float x, float y) {
		ensureMoveTo();
		this.x += x;
		this.y += y;
		x = this.x;
		y = this.y;
		this.x1 = x + x - x1;
		this.y1 = y + y - y1;
		ExtendedGeneralPath path = generalPath();
		path.quadTo(x1, y1, x, y);
	}

	public void curveRel(float x, float y, float x1, float y1, float x2, float y2) {
		ensureMoveTo();
		this.x += x;
		this.y += y;
		x = this.x;
		y = this.y;
		this.x1 = x1;
		this.y1 = y1;
		ExtendedGeneralPath path = generalPath();
		path.curveTo(x1, y1, x2, y2, x, y);
	}
	
	public void curveRel(float x, float y, float x2, float y2) {
		ensureMoveTo();
		this.x += x;
		this.y += y;
		x = this.x;
		y = this.y;
		this.x1 = x + x - x1;
		this.y1 = y + y - y1;
		ExtendedGeneralPath path = generalPath();
		path.curveTo(x1, y1, x2, y2, x, y);
	}
	
	public void circle() {
		ensureMoveTo();
		ExtendedGeneralPath path = generalPath();
		path.append(new Ellipse2D.Float(x - 0.5f, y - 0.5f, 1f, 1f), true);
	}

	public void closePath(Boolean align) {
		ExtendedGeneralPath path = generalPath();
		path.closePath();
	}

	public void bounds(ContextFreeBounds bounds) {
		ExtendedGeneralPath path = generalPath();
		Rectangle2D pathBounds = path.getBounds2D();
		bounds.addPoint(pathBounds.getMinX(), pathBounds.getMinY());
		bounds.addPoint(pathBounds.getMaxX(), pathBounds.getMaxY());
	}

	public void fill(Graphics2D g2d, AffineTransform t, AlphaComposite a, Color c, int rule) {
		if (path != null) {
			g2d.setComposite(a);
			g2d.setColor(c);
			t.concatenate(at);
			Shape shape = t.createTransformedShape(path);
			path.setWindingRule(rule);
			g2d.fill(shape);
		}
		toFill = false;
	}

	public void draw(Graphics2D g2d, AffineTransform t, AlphaComposite a, Color c, BasicStroke s) {
		if (path != null) {
			g2d.setComposite(a);
			g2d.setStroke(s);
			g2d.setColor(c);
			t.concatenate(at);
			Shape shape = t.createTransformedShape(path);
			g2d.draw(shape);
		}
		toFill = false;
	}
	
	public void flush(Graphics2D g2d, AffineTransform t, AlphaComposite a, Color c) {
		if (toFill) {
			fill(g2d, t, a, c, PathIterator.WIND_NON_ZERO);
		}
		path = null;
	}

	public float getZ() {
		return z;
	}
}
