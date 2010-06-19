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
public class ArcRelPathReplacementRuntime extends PathReplacementExtensionRuntime<ArcRelPathReplacementConfig> {
	private Float x;
	private XListener xListener;
	private Float y;
	private YListener yListener;
	private Float rx;
	private RxListener rxListener;
	private Float ry;
	private RyListener ryListener;
	private Float r;
	private RListener rListener;
	private String mode;
	private ModeListener modeListener;

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
		setRx(getConfig().getRx());
		rxListener = new RxListener();
		getConfig().getRxElement().addChangeListener(rxListener);
		setRy(getConfig().getRy());
		ryListener = new RyListener();
		getConfig().getRyElement().addChangeListener(ryListener);
		setR(getConfig().getR());
		rListener = new RListener();
		getConfig().getRElement().addChangeListener(rListener);
		setMode(getConfig().getMode());
		modeListener = new ModeListener();
		getConfig().getModeElement().addChangeListener(modeListener);
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
		if ((getConfig() != null) && (rxListener != null)) {
			getConfig().getRxElement().removeChangeListener(rxListener);
		}
		rxListener = null;
		if ((getConfig() != null) && (ryListener != null)) {
			getConfig().getRyElement().removeChangeListener(ryListener);
		}
		ryListener = null;
		if ((getConfig() != null) && (rListener != null)) {
			getConfig().getRElement().removeChangeListener(rListener);
		}
		rListener = null;
		if ((getConfig() != null) && (modeListener != null)) {
			getConfig().getModeElement().removeChangeListener(modeListener);
		}
		modeListener = null;
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
	 * @return the rx.
	 */
	public Float getRx() {
		return rx;
	}

	private void setRx(final Float rx) {
		this.rx = rx;
	}
	
	private class RxListener implements ValueChangeListener {
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
	 * @return the ry.
	 */
	public Float getRy() {
		return ry;
	}

	private void setRy(final Float ry) {
		this.ry = ry;
	}
	
	private class RyListener implements ValueChangeListener {
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
	 * @return the r.
	 */
	public Float getR() {
		return r;
	}

	private void setR(final Float r) {
		this.r = r;
	}
	
	private class RListener implements ValueChangeListener {
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
	 * @return the mode.
	 */
	public String getMode() {
		return mode;
	}

	private void setMode(final String mode) {
		this.mode = mode;
	}
	
	private class ModeListener implements ValueChangeListener {
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
		private ContextFreeState state;
		
		public OperationContextFreeNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
			this.state = state;
		}

		@Override
		public void drawNode(Graphics2D g2d, ContextFreeArea area) {
		}
	}
}
