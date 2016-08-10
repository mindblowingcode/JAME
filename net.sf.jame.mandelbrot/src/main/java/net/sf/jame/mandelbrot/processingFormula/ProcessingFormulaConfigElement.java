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
package net.sf.jame.mandelbrot.processingFormula;

import net.sf.jame.core.common.ExtensionReferenceElement;
import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.extension.ExtensionReference;
import net.sf.jame.core.extension.ReferenceElement;

/**
 * @author Andrea Medeghini
 */
public class ProcessingFormulaConfigElement extends AbstractConfigElement implements ReferenceElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "ProcessingFormula";
	private final ExtensionReferenceElement extensionElement = new ExtensionReferenceElement();

	/**
	 * Constructs a new element.
	 */
	public ProcessingFormulaConfigElement() {
		super(ProcessingFormulaConfigElement.CLASS_ID);
	}

	/**
	 * @return
	 */
	@Override
	public ProcessingFormulaConfigElement clone() {
		final ProcessingFormulaConfigElement element = new ProcessingFormulaConfigElement();
		if (getReference() != null) {
			element.setReference(getReference().clone());
		}
		return element;
	}

	/**
	 * @see net.sf.jame.core.config.ConfigElement#copyFrom(net.sf.jame.core.config.ConfigElement)
	 */
	public void copyFrom(ConfigElement source) {
		ProcessingFormulaConfigElement element = (ProcessingFormulaConfigElement) source;
		if (element.getReference() != null) {
			setReference(element.getReference().clone());
		}
	}

	/**
	 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		extensionElement.setContext(context);
	}

	/**
	 * @return
	 */
	public ExtensionReference getReference() {
		return extensionElement.getReference();
	}

	/**
	 * @param reference
	 */
	public void setReference(final ExtensionReference reference) {
		extensionElement.setReference(reference);
	}

	/**
	 * @return
	 */
	public ExtensionReferenceElement getExtensionElement() {
		return extensionElement;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		final ProcessingFormulaConfigElement other = (ProcessingFormulaConfigElement) obj;
		if (extensionElement == null) {
			if (other.extensionElement != null) {
				return false;
			}
		}
		else if (!extensionElement.equals(other.extensionElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#dispose()
	 */
	@Override
	public void dispose() {
		extensionElement.dispose();
		super.dispose();
	}
}
