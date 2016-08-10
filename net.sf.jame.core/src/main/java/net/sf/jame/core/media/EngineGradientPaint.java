/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
package net.sf.jame.core.media;

import java.awt.*;
import java.awt.geom.Point2D;

public final class EngineGradientPaint extends EnginePaint {
	private GradientPaint paint;
	private final GradientPaint copy;
	private Color color1;
	private Color color2;
	private final Point2D point1;
	private final Point2D point2;
	private final int ARGB1;
	private final int ARGB2;
	private final int transparency1;
	private final int transparency2;

	public EngineGradientPaint(final GradientPaint paint) {
		this.paint = paint;
		copy = paint;
		color1 = paint.getColor1();
		color2 = paint.getColor2();
		point1 = paint.getPoint1();
		point2 = paint.getPoint2();
		ARGB1 = color1.getRGB();
		ARGB2 = color2.getRGB();
		transparency1 = color1.getTransparency();
		transparency2 = color2.getTransparency();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new EngineGradientPaint(copy);
	}

	@Override
	public void applyEffect(final Effect effect) {
		color1 = new Color(effect.filter(ARGB1, transparency1), true);
		color2 = new Color(effect.filter(ARGB2, transparency2), true);
		paint = new GradientPaint(point1, color1, point2, color2);
	}

	@Override
	public Paint getPaint() {
		return paint;
	}
}
