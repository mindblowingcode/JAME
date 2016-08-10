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
package net.sf.jame.contextfree.shapeReplacement;


import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.renderer.support.CFBuilder;
import net.sf.jame.contextfree.renderer.support.CFRule;
import net.sf.jame.contextfree.shapeReplacement.extension.ShapeReplacementExtensionConfig;
import net.sf.jame.contextfree.shapeReplacement.extension.ShapeReplacementExtensionRuntime;
import net.sf.jame.core.common.ExtensionReferenceElement;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;

/**
 * @author Andrea Medeghini
 */
 public class ShapeReplacementRuntimeElement extends RuntimeElement {
	private ShapeReplacementConfigElement shapeReplacementElement;
	private ShapeReplacementExtensionRuntime<?> extensionRuntime;
	private ExtensionListener extensionListener;

	/**
	 * Constructs a new ShapeReplacementRuntimeElement.
	 * 
	 * @param registry
	 * @param ShapeReplacementRuntimeElementElement
	 */
	public ShapeReplacementRuntimeElement(final ShapeReplacementConfigElement shapeReplacementElement) {
		if (shapeReplacementElement == null) {
			throw new IllegalArgumentException("shapeReplacementElement is null");
		}
		this.shapeReplacementElement = shapeReplacementElement;
		createRuntime(shapeReplacementElement.getExtensionReference());
		extensionListener = new ExtensionListener();
		shapeReplacementElement.getExtensionElement().addChangeListener(extensionListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((shapeReplacementElement != null) && (extensionListener != null)) {
			shapeReplacementElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if (extensionRuntime != null) {
			extensionRuntime.dispose();
			extensionRuntime = null;
		}
		shapeReplacementElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean shapeReplacementChanged = false;
		shapeReplacementChanged |= (extensionRuntime != null) && extensionRuntime.isChanged();
		return super.isChanged() || shapeReplacementChanged;
	}

	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<ShapeReplacementExtensionConfig> reference) {
		try {
			if (reference != null) {
				final ShapeReplacementExtensionRuntime extensionRuntime = ContextFreeRegistry.getInstance().getShapeReplacementExtension(reference.getExtensionId()).createExtensionRuntime();
				final ShapeReplacementExtensionConfig extensionConfig = reference.getExtensionConfig();
				extensionRuntime.setConfig(extensionConfig);
				setExtensionRuntime(extensionRuntime);
			}
			else {
				setExtensionRuntime(null);
			}
		}
		catch (final ExtensionNotFoundException e) {
			e.printStackTrace();
		}
		catch (final ExtensionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the ShapeReplacementExtensionRuntimeRuntime
	 */
	public ShapeReplacementExtensionRuntime<?> getExtensionRuntime() {
		return extensionRuntime;
	}

	private void setExtensionRuntime(final ShapeReplacementExtensionRuntime<?> extensionRuntime) {
		if (this.extensionRuntime != null) {
			this.extensionRuntime.dispose();
		}
		this.extensionRuntime = extensionRuntime;
	}
	
	private class ExtensionListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		@SuppressWarnings("unchecked")
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED: {
					createRuntime((ConfigurableExtensionReference<ShapeReplacementExtensionConfig>) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	public void process(CFBuilder context, CFRule rule) {
		if (extensionRuntime != null) {
			extensionRuntime.process(context, rule);
		}
	}
}
