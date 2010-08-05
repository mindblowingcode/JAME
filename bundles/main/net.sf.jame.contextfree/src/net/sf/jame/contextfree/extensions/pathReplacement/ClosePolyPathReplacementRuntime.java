/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.awt.Graphics2D;

import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeArea;
import net.sf.jame.contextfree.renderer.ContextFreeBounds;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeNode;
import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class ClosePolyPathReplacementRuntime extends PathReplacementExtensionRuntime<ClosePolyPathReplacementConfig> {
	private Boolean align;
	private AlignListener alignListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		setAlign(getConfig().isAlign());
		alignListener = new AlignListener();
		getConfig().getAlignElement().addChangeListener(alignListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (alignListener != null)) {
			getConfig().getAlignElement().removeChangeListener(alignListener);
		}
		alignListener = null;
		super.dispose();
	}
	
	/**
	 * @return the align.
	 */
	public Boolean isAlign() {
		return align;
	}

	private void setAlign(final Boolean align) {
		this.align = align;
	}
	
	private class AlignListener implements ValueChangeListener {
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

	public ContextFreeNode buildNode(ContextFreeContext context, ContextFreeState state, ContextFreeBounds bounds) {
		return new ReplacementContextFreeNode(context, state, bounds);
	}

	private class ReplacementContextFreeNode extends ContextFreeNode {
		public ReplacementContextFreeNode(ContextFreeContext context, ContextFreeState state, ContextFreeBounds bounds) {
			state.closePath(align);
		}

		@Override
		public void drawNode(Graphics2D g2d, ContextFreeArea area) {
		}
	}
}
