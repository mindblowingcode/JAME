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
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.awt.Graphics2D;

import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeArea;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeLimits;
import net.sf.jame.contextfree.renderer.ContextFreeNode;
import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 *
 */
public class LineToPathReplacementRuntime extends PathReplacementExtensionRuntime<LineToPathReplacementConfig> {
	private Float x;
	private XListener xListener;
	private Float y;
	private YListener yListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setX(getConfig().getX());
		xListener = new XListener();
		getConfig().getXElement().addChangeListener(xListener);
		setY(getConfig().getY());
		yListener = new YListener();
		getConfig().getYElement().addChangeListener(yListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (xListener != null)) {
			getConfig().getXElement().removeChangeListener(xListener);
		}
		xListener = null;
		if ((getConfig() != null) && (yListener != null)) {
			getConfig().getYElement().removeChangeListener(yListener);
		}
		yListener = null;
		super.dispose();
	}
	
	/**
	 * @return the x.
	 */
	public Float getX() {
		return x;
	}

	private void setX(final Float x) {
		this.x = x;
	}
	
	private class XListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	/**
	 * @return the y.
	 */
	public Float getY() {
		return y;
	}

	private void setY(final Float y) {
		this.y = y;
	}
	
	private class YListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	public ContextFreeNode buildNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
		return new OperationContextFreeNode(context, state, limits);
	}
	
	private class OperationContextFreeNode extends ContextFreeNode {
		private float[] p; 
		private float[] q;
		
		public OperationContextFreeNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
			p = new float[2];
			q = new float[p.length];
			p[0] = x;
			p[1] = y;
			state.transform(p, q);
			for (int i = 0; i < q.length; i += 2) {
				limits.addPoint(q[i + 0], q[i + 1]);
			}
			state.lineTo(q[0], q[1]);
		}

		@Override
		public void drawNode(Graphics2D g2d, ContextFreeArea area) {
		}
	}
}
