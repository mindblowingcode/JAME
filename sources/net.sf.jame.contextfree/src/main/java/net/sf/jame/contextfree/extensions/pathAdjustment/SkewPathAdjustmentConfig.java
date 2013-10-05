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
public class SkewPathAdjustmentConfig extends PathAdjustmentExtensionConfig {
	private static final long serialVersionUID = 1L;
	private FloatElement shearXElement;
	private FloatElement shearYElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		shearXElement = new FloatElement(0f);
		shearYElement = new FloatElement(0f);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(shearXElement);
		elements.add(shearYElement);
		return elements;
	}

	/**
	 * @return
	 */
	public FloatElement getShearXElement() {
		return shearXElement;
	}
	
	/**
	 * @return
	 */
	public Float getShearX() {
		return shearXElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setShearX(final Float value) {
		shearXElement.setValue(value);
	}
	/**
	 * @return
	 */
	public FloatElement getShearYElement() {
		return shearYElement;
	}
	
	/**
	 * @return
	 */
	public Float getShearY() {
		return shearYElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setShearY(final Float value) {
		shearYElement.setValue(value);
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
		final SkewPathAdjustmentConfig other = (SkewPathAdjustmentConfig) obj;
		if (shearXElement == null) {
			if (other.shearXElement != null) {
				return false;
			}
		}
		else if (!shearXElement.equals(other.shearXElement)) {
			return false;
		}
		if (shearYElement == null) {
			if (other.shearYElement != null) {
				return false;
			}
		}
		else if (!shearYElement.equals(other.shearYElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public SkewPathAdjustmentConfig clone() {
		final SkewPathAdjustmentConfig config = new SkewPathAdjustmentConfig();
		config.setShearX(getShearX());
		config.setShearY(getShearY());
		return config;
	}

	@Override
	public void toCFDG(CFDGBuilder builder) {
		if (shearXElement.getValue() != null && shearYElement.getValue() != null) {
			builder.append("skew ");
			builder.append(shearXElement.getValue() * 180f / Math.PI);
			builder.append(" ");
			builder.append(shearYElement.getValue() * 180f / Math.PI);
		}
	}
}