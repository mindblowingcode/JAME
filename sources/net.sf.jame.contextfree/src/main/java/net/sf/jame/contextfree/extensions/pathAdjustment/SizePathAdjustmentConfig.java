/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathAdjustment;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.CFDGBuilder;
import net.sf.jame.contextfree.pathAdjustment.extension.PathAdjustmentExtensionConfig;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 */
public class SizePathAdjustmentConfig extends PathAdjustmentExtensionConfig {
	private static final long serialVersionUID = 1L;
	private FloatElement scaleElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		scaleElement = new FloatElement(0f);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(scaleElement);
		return elements;
	}

	/**
	 * @return
	 */
	public FloatElement getScaleElement() {
		return scaleElement;
	}
	
	/**
	 * @return
	 */
	public Float getScale() {
		return scaleElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setScale(final Float value) {
		scaleElement.setValue(value);
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
		final SizePathAdjustmentConfig other = (SizePathAdjustmentConfig) obj;
		if (scaleElement == null) {
			if (other.scaleElement != null) {
				return false;
			}
		}
		else if (!scaleElement.equals(other.scaleElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public SizePathAdjustmentConfig clone() {
		final SizePathAdjustmentConfig config = new SizePathAdjustmentConfig();
		config.setScale(getScale());
		return config;
	}

	@Override
	public void toCFDG(CFDGBuilder builder) {
		if (scaleElement.getValue() != null) {
			builder.append("s ");
			builder.append(scaleElement.getValue());
		}
	}
}
