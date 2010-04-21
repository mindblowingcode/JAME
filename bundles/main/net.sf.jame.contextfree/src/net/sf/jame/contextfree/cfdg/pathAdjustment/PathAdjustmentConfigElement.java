/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathAdjustment;

import net.sf.jame.contextfree.cfdg.pathAdjustment.extension.PathAdjustmentExtensionConfig;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.extension.ConfigurableExtensionReference;

/**
 * @author Andrea Medeghini
 */
public class PathAdjustmentConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "PathAdjustment";
	private final ConfigurableExtensionReferenceElement<PathAdjustmentExtensionConfig> extensionElement = new ConfigurableExtensionReferenceElement<PathAdjustmentExtensionConfig>();

	/**
	 * Constructs a new element.
	 */
	public PathAdjustmentConfigElement() {
		super(PathAdjustmentConfigElement.CLASS_ID);
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
	public PathAdjustmentConfigElement clone() {
		final PathAdjustmentConfigElement element = new PathAdjustmentConfigElement();
		if (getExtensionReference() != null) {
			element.setExtensionReference(getExtensionReference().clone());
		}
		return element;
	}
	
	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		PathAdjustmentConfigElement pathAdjustmentElement = (PathAdjustmentConfigElement) source;
		if (pathAdjustmentElement.getExtensionReference() != null) {
			setExtensionReference(pathAdjustmentElement.getExtensionReference().clone());
		}
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReferenceElement<PathAdjustmentExtensionConfig> getExtensionElement() {
		return extensionElement;
	}
	
	/**
	 * @return
	 */
	public ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getExtensionReference() {
		return extensionElement.getReference();
	}

	/**
	 * @param reference
	 */
	public void setExtensionReference(final ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference) {
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
		final PathAdjustmentConfigElement other = (PathAdjustmentConfigElement) obj;
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
