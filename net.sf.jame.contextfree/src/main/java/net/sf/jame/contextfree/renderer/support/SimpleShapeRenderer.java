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
package net.sf.jame.contextfree.renderer.support;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class SimpleShapeRenderer implements CFShapeRenderer {
	private Graphics2D g2d;
	
	public SimpleShapeRenderer(Graphics2D g2d, CFContext context) {
		this.g2d = g2d;
	}

	public void render(CFPath path, CFPathAttribute attribute) {
		AffineTransform tmpTransform = g2d.getTransform();
		Composite tmpComposite = g2d.getComposite();
		Color tmpColor = g2d.getColor();
		path.render(g2d, attribute, null);
		g2d.setTransform(tmpTransform);
		g2d.setComposite(tmpComposite);
		g2d.setColor(tmpColor);
	}
}
