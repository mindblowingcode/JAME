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
package net.sf.jame.contextfree.extensions.pathReplacement;

import net.sf.jame.contextfree.pathReplacement.extension.PathReplacementExtensionRuntime;
import net.sf.jame.contextfree.renderer.support.CFRule;
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
					setAlign((Boolean) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	public void process(CFRule rule) {
		rule.getPath().closePath(align);
	}
}
