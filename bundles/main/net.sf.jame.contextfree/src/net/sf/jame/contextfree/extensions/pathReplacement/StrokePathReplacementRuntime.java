/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentRuntimeElement;
import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeArea;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeLimits;
import net.sf.jame.contextfree.renderer.ContextFreeNode;
import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.config.ListRuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class StrokePathReplacementRuntime extends PathReplacementExtensionRuntime<StrokePathReplacementConfig> {
	private Float width;
	private WidthListener widthListener;
	private String cup;
	private CupListener cupListener;
	private String join;
	private JoinListener joinListener;
	private ListRuntimeElement<PathAdjustmentRuntimeElement> pathAdjustmentListElement;
	private PathAdjustmentListElementListener pathAdjustmentListElementListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setWidth(getConfig().getWidth());
		widthListener = new WidthListener();
		getConfig().getWidthElement().addChangeListener(widthListener);
		setCup(getConfig().getCap());
		cupListener = new CupListener();
		getConfig().getCapElement().addChangeListener(cupListener);
		setJoin(getConfig().getJoin());
		joinListener = new JoinListener();
		getConfig().getJoinElement().addChangeListener(joinListener);
		pathAdjustmentListElement = new ListRuntimeElement<PathAdjustmentRuntimeElement>();
		for (int i = 0; i < getConfig().getPathAdjustmentConfigElementCount(); i++) {
			pathAdjustmentListElement.appendElement(new PathAdjustmentRuntimeElement(getConfig().getPathAdjustmentConfigElement(i)));
		}
		pathAdjustmentListElementListener = new PathAdjustmentListElementListener();
		getConfig().getPathAdjustmentListElement().addChangeListener(pathAdjustmentListElementListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (widthListener != null)) {
			getConfig().getWidthElement().removeChangeListener(widthListener);
		}
		widthListener = null;
		if ((getConfig() != null) && (cupListener != null)) {
			getConfig().getCapElement().removeChangeListener(cupListener);
		}
		cupListener = null;
		if ((getConfig() != null) && (joinListener != null)) {
			getConfig().getJoinElement().removeChangeListener(joinListener);
		}
		joinListener = null;
		if ((getConfig() != null) && (pathAdjustmentListElementListener != null)) {
			getConfig().getPathAdjustmentListElement().removeChangeListener(pathAdjustmentListElementListener);
		}
		pathAdjustmentListElementListener = null;
		super.dispose();
	}
	
	/**
	 * @return the width.
	 */
	public Float getWidth() {
		return width;
	}

	private void setWidth(final Float width) {
		this.width = width;
	}
	
	private class WidthListener implements ValueChangeListener {
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
	 * @return the cup.
	 */
	public String getCup() {
		return cup;
	}

	private void setCup(final String cup) {
		this.cup = cup;
	}
	
	private class CupListener implements ValueChangeListener {
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
	 * @return the join.
	 */
	public String getJoin() {
		return join;
	}

	private void setJoin(final String join) {
		this.join = join;
	}
	
	private class JoinListener implements ValueChangeListener {
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
	 * Returns a pathAdjustment element.
	 * 
	 * @param index the pathAdjustment index.
	 * @return the pathAdjustment.
	 */
	public PathAdjustmentRuntimeElement getPathAdjustmentElement(final int index) {
		return pathAdjustmentListElement.getElement(index);
	}

	/**
	 * Returns a pathAdjustment element index.
	 * 
	 * @param pathAdjustmentElement the pathAdjustment element.
	 * @return the index.
	 */
	public int indexOfPathAdjustmentElement(final PathAdjustmentRuntimeElement pathAdjustmentElement) {
		return pathAdjustmentListElement.indexOfElement(pathAdjustmentElement);
	}

	/**
	 * Returns the number of pathAdjustment elements.
	 * 
	 * @return the number of pathAdjustment elements.
	 */
	public int getPathAdjustmentElementCount() {
		return pathAdjustmentListElement.getElementCount();
	}

	private void setPathAdjustmentElement(final int index, PathAdjustmentRuntimeElement element) {
		pathAdjustmentListElement.setElement(index, element);
	}

	private void appendPathAdjustmentElement(final PathAdjustmentRuntimeElement pathAdjustmentElement) {
		pathAdjustmentListElement.appendElement(pathAdjustmentElement);
	}

	private void insertPathAdjustmentElementAfter(final int index, final PathAdjustmentRuntimeElement pathAdjustmentElement) {
		pathAdjustmentListElement.insertElementAfter(index, pathAdjustmentElement);
	}

	private void insertPathAdjustmentElementBefore(final int index, final PathAdjustmentRuntimeElement pathAdjustmentElement) {
		pathAdjustmentListElement.insertElementBefore(index, pathAdjustmentElement);
	}

	private void removePathAdjustmentElement(final int index) {
		pathAdjustmentListElement.removeElement(index);
	}

	private void moveUpPathAdjustmentElement(final int index) {
		pathAdjustmentListElement.moveElementUp(index);
	}

	private void moveDownPathAdjustmentElement(final int index) {
		pathAdjustmentListElement.moveElementDown(index);
	}
	
	private class PathAdjustmentListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ListConfigElement.ELEMENT_ADDED: {
					appendPathAdjustmentElement(new PathAdjustmentRuntimeElement ((PathAdjustmentConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_AFTER: {
					insertPathAdjustmentElementAfter(((Integer) e.getParams()[1]).intValue(), new PathAdjustmentRuntimeElement ((PathAdjustmentConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_BEFORE: {
					insertPathAdjustmentElementBefore(((Integer) e.getParams()[1]).intValue(), new PathAdjustmentRuntimeElement ((PathAdjustmentConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_REMOVED: {
					removePathAdjustmentElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_UP: {
					moveUpPathAdjustmentElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_DOWN: {
					moveDownPathAdjustmentElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_CHANGED: {
					setPathAdjustmentElement(((Integer) e.getParams()[1]).intValue(), new PathAdjustmentRuntimeElement ((PathAdjustmentConfigElement) e.getParams()[0]));
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
		private ContextFreeState state;
		private AlphaComposite a; 
		private BasicStroke s;
		private Color c;

		public ReplacementContextFreeNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
			float[] hsba = state.getHSBA();
			a = AlphaComposite.Src.derive(hsba[3]);
			c = Color.getHSBColor(hsba[0], hsba[1], hsba[2]);
			s = new BasicStroke(width, getCap(cup), getJoin(join));
			state.limits(limits);
		}

		private int getCap(String cap) {
			if ("buttcap".equals(cup)) {
				return BasicStroke.CAP_BUTT;
			} else if ("roundcap".equals(cup)) {
				return BasicStroke.CAP_ROUND;
			} else if ("squarecap".equals(cup)) {
				return BasicStroke.CAP_SQUARE;
			}
			throw new IllegalArgumentException("Cap not supported");
		}

		private int getJoin(String join) {
			if ("miterjoin".equals(join)) {
				return BasicStroke.JOIN_MITER;
			} else if ("roundjoin".equals(join)) {
				return BasicStroke.JOIN_ROUND;
			} else if ("beveljoin".equals(join)) {
				return BasicStroke.JOIN_BEVEL;
			}
			throw new IllegalArgumentException("Join not supported");
		}

		@Override
		public void drawNode(Graphics2D g2d, ContextFreeArea area) {
			AffineTransform t = new AffineTransform();
			float sx = area.getScaleX();
			float sy = area.getScaleY();
			float tx = area.getX();
			float ty = area.getY();
			t.translate(tx, ty);
			t.scale(sx, sy);
			state.draw(g2d, t, a, c, s);
		}
	}
}
