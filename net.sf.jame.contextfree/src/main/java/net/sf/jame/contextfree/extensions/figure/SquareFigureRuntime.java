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
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.renderer.support.CFBuilder;
import net.sf.jame.contextfree.renderer.support.CFPath;
import net.sf.jame.contextfree.renderer.support.CFRule;

/**
 * @author Andrea Medeghini
 */
public class SquareFigureRuntime extends FigureExtensionRuntime<SquareFigureConfig> {
	public String getName() {
		return "SQUARE";
	}

	public void process(CFBuilder builder) {
		int shapeType = builder.encodeShapeName(getName());
		CFRule rule = new CFRule(shapeType, 1);
		CFPath path = new CFPath();
		path.moveRel(-0.5f, -0.5f);
		path.lineRel(+1f, +0f);
		path.lineRel(+0f, +1f);
		path.lineRel(-1f, +0f);
		path.closePath(false);
		rule.setPath(path);
		builder.addRule(rule);
	}
}
