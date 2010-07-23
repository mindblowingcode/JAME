/*
 * $Id:$
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
 */
public class QuadToPathReplacementRuntime extends PathReplacementExtensionRuntime<QuadToPathReplacementConfig> {
	private Float x;
	private XListener xListener;
	private Float y;
	private YListener yListener;
	private Float x1;
	private X1Listener x1Listener;
	private Float y1;
	private Y1Listener y1Listener;

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
		setX1(getConfig().getX1());
		x1Listener = new X1Listener();
		getConfig().getX1Element().addChangeListener(x1Listener);
		setY1(getConfig().getY1());
		y1Listener = new Y1Listener();
		getConfig().getY1Element().addChangeListener(y1Listener);
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
		if ((getConfig() != null) && (x1Listener != null)) {
			getConfig().getX1Element().removeChangeListener(x1Listener);
		}
		x1Listener = null;
		if ((getConfig() != null) && (y1Listener != null)) {
			getConfig().getY1Element().removeChangeListener(y1Listener);
		}
		y1Listener = null;
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
	/**
	 * @return the x1.
	 */
	public Float getX1() {
		return x1;
	}

	private void setX1(final Float x1) {
		this.x1 = x1;
	}
	
	private class X1Listener implements ValueChangeListener {
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
	 * @return the y1.
	 */
	public Float getY1() {
		return y1;
	}

	private void setY1(final Float y1) {
		this.y1 = y1;
	}
	
	private class Y1Listener implements ValueChangeListener {
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
		return new ReplacementContextFreeNode(context, state, limits);
	}
	
	private class ReplacementContextFreeNode extends ContextFreeNode {
		public ReplacementContextFreeNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
			state.quadTo(x, y, x1, y1);
		}

		@Override
		public void drawNode(Graphics2D g2d, ContextFreeArea area) {
		}
	}
}
