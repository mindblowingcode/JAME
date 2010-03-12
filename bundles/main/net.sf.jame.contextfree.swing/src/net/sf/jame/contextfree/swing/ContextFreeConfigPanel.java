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
package net.sf.jame.contextfree.swing;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import net.sf.jame.contextfree.ContextFreeConfig;
import net.sf.jame.contextfree.cfdg.CFDGConfigElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.core.swing.ViewContext;
import net.sf.jame.core.tree.NodeSession;
import net.sf.jame.core.util.RenderContext;
import net.sf.jame.twister.swing.ViewPanel;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeConfigPanel extends ViewPanel {
	private static final long serialVersionUID = 1L;
	private ContextFreeFractalPanel fractalPanel;
	private final ViewContext viewContext;
	private final RenderContext context;
	private final NodeSession session;
	private final ValueChangeListener configListener;
	private final ContextFreeConfig config;
	private final Color oddColor;
	private final Color evenColor;

	/**
	 * @param config
	 * @param viewContext
	 * @param context
	 * @param session
	 */
	public ContextFreeConfigPanel(final ContextFreeConfig config, final ViewContext viewContext, final RenderContext context, final NodeSession session) {
		this.viewContext = viewContext;
		this.context = context;
		this.session = session;
		this.config = config;
		oddColor = getBackground().brighter();
		evenColor = getBackground().brighter();
		setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.DARK_GRAY));
		configListener = new ValueChangeListener() {
			public void valueChanged(final ValueChangeEvent e) {
				switch (e.getEventType()) {
					case ValueConfigElement.VALUE_CHANGED: {
						remove(fractalPanel);
						fractalPanel.dispose();
						fractalPanel = new ContextFreeFractalPanel(config, config.getContextFreeFractal());
						add(fractalPanel, BorderLayout.CENTER);
						viewContext.setComponent(ContextFreeConfigPanel.this);
						break;
					}
					default: {
						break;
					}
				}
			}
		};
		config.getFractalSingleElement().addChangeListener(configListener);
	}

	@Override
	public void dispose() {
		config.getFractalSingleElement().removeChangeListener(configListener);
	}

	private class ContextFreeFractalPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public ContextFreeFractalPanel(ContextFreeConfig config, CFDGConfigElement contextFreeFractalConfigElement) {
		}

		public void dispose() {
		}
	}
}
