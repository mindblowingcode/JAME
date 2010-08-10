/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.figure;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.extension.ConfigurableExtensionReference;

/**
 * @author Andrea Medeghini
 */
public class FigureConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "Figure";
	private final ConfigurableExtensionReferenceElement<FigureExtensionConfig> extensionElement = new ConfigurableExtensionReferenceElement<FigureExtensionConfig>();

	/**
	 * Constructs a new element.
	 */
	public FigureConfigElement() {
		super(FigureConfigElement.CLASS_ID);
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		extensionElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public FigureConfigElement clone() {
		final FigureConfigElement element = new FigureConfigElement();
		if (getExtensionReference() != null) {
			element.setExtensionReference(getExtensionReference().clone());
		}
		return element;
	}
	
	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		FigureConfigElement figureElement = (FigureConfigElement) source;
		if (figureElement.getExtensionReference() != null) {
			setExtensionReference(figureElement.getExtensionReference().clone());
		}
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReferenceElement<FigureExtensionConfig> getExtensionElement() {
		return extensionElement;
	}
	
	/**
	 * @return
	 */
	public ConfigurableExtensionReference<FigureExtensionConfig> getExtensionReference() {
		return extensionElement.getReference();
	}

	/**
	 * @param reference
	 */
	public void setExtensionReference(final ConfigurableExtensionReference<FigureExtensionConfig> reference) {
		extensionElement.setReference(reference);
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
		final FigureConfigElement other = (FigureConfigElement) obj;
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

	public void toCFDG(StringBuilder builder) {
		if (extensionElement.getReference() != null) {
			if (extensionElement.getReference().getExtensionConfig() != null) {
				extensionElement.getReference().getExtensionConfig().toCFDG(builder);
			}
		}
	}
}
