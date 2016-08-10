/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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
package net.sf.jame.mandelbrot.swing.extensions.view;

import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.swing.ViewContext;
import net.sf.jame.core.tree.NodeSession;
import net.sf.jame.core.util.RenderContext;
import net.sf.jame.mandelbrot.extensions.image.MandelbrotImageConfig;
import net.sf.jame.mandelbrot.swing.MandelbrotConfigPanel;
import net.sf.jame.twister.swing.ViewPanel;
import net.sf.jame.twister.swing.view.extension.ViewExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class MandelbrotImageConfigViewRuntime extends ViewExtensionRuntime {
	/**
	 * @see net.sf.jame.twister.swing.view.extension.ViewExtensionRuntime#createView(net.sf.jame.core.extension.ExtensionConfig, net.sf.jame.core.swing.ViewContext, net.sf.jame.core.util.RenderContext, net.sf.jame.core.tree.NodeSession)
	 */
	@Override
	public ViewPanel createView(final ExtensionConfig config, final ViewContext viewContext, final RenderContext context, final NodeSession session) {
		return new MandelbrotConfigPanel(((MandelbrotImageConfig) config).getMandelbrotConfig(), viewContext, context, session);
	}
}
