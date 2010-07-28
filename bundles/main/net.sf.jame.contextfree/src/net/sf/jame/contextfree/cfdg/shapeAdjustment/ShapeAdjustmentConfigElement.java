/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.shapeAdjustment;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionConfig;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.extension.ConfigurableExtensionReference;

/**
 * @author Andrea Medeghini
 */
public class ShapeAdjustmentConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "ShapeAdjustment";
	private final ConfigurableExtensionReferenceElement<ShapeAdjustmentExtensionConfig> extensionElement = new ConfigurableExtensionReferenceElement<ShapeAdjustmentExtensionConfig>();

	/**
	 * Constructs a new element.
	 */
	public ShapeAdjustmentConfigElement() {
		super(ShapeAdjustmentConfigElement.CLASS_ID);
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
	public ShapeAdjustmentConfigElement clone() {
		final ShapeAdjustmentConfigElement element = new ShapeAdjustmentConfigElement();
		if (getExtensionReference() != null) {
			element.setExtensionReference(getExtensionReference().clone());
		}
		return element;
	}
	
	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		ShapeAdjustmentConfigElement shapeAdjustmentElement = (ShapeAdjustmentConfigElement) source;
		if (shapeAdjustmentElement.getExtensionReference() != null) {
			setExtensionReference(shapeAdjustmentElement.getExtensionReference().clone());
		}
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReferenceElement<ShapeAdjustmentExtensionConfig> getExtensionElement() {
		return extensionElement;
	}
	
	/**
	 * @return
	 */
	public ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getExtensionReference() {
		return extensionElement.getReference();
	}

	/**
	 * @param reference
	 */
	public void setExtensionReference(final ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference) {
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
		final ShapeAdjustmentConfigElement other = (ShapeAdjustmentConfigElement) obj;
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
