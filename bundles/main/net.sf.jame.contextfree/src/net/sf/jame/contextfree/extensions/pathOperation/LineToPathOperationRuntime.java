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
package net.sf.jame.contextfree.extensions.pathOperation;

import java.awt.geom.Line2D;

import net.sf.jame.contextfree.cfdg.pathOperation.extension.PathOperationExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeContext;

/**
 * @author Andrea Medeghini
 *
 */
public class LineToPathOperationRuntime extends PathOperationExtensionRuntime<LineToPathOperationConfig> {
	/**
	 * @see net.sf.jame.contextfree.cfdg.pathOperation.extension.PathOperationExtensionRuntime#draw(net.sf.jame.contextfree.renderer.ContextFreeContext)
	 */
	@Override
	public void draw(ContextFreeContext contextFreeContext) {
		// TODO Auto-generated method stub
		Line2D.Float line = new Line2D.Float(contextFreeContext.getX(), contextFreeContext.getY(), getConfig().getX().floatValue(), getConfig().getY().floatValue());
		contextFreeContext.drawShape(line);
	}

	/**
	 * @see net.sf.jame.contextfree.cfdg.pathOperation.extension.PathOperationExtensionRuntime#prepare(net.sf.jame.contextfree.renderer.ContextFreeContext)
	 */
	@Override
	public void prepare(ContextFreeContext contextFreeContext) {
		// TODO Auto-generated method stub
		
	}
}
