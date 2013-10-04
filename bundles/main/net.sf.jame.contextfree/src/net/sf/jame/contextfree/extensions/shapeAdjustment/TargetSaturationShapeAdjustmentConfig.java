/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeAdjustment;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.CFDGBuilder;
import net.sf.jame.contextfree.shapeAdjustment.extension.ShapeAdjustmentExtensionConfig;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 */
public class TargetSaturationShapeAdjustmentConfig extends ShapeAdjustmentExtensionConfig {
	private static final long serialVersionUID = 1L;
	private FloatElement valueElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		valueElement = new FloatElement(0f);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(valueElement);
		return elements;
	}

	/**
	 * @return
	 */
	public FloatElement getValueElement() {
		return valueElement;
	}
	
	/**
	 * @return
	 */
	public Float getValue() {
		return valueElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setValue(final Float value) {
		valueElement.setValue(value);
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
		final TargetSaturationShapeAdjustmentConfig other = (TargetSaturationShapeAdjustmentConfig) obj;
		if (valueElement == null) {
			if (other.valueElement != null) {
				return false;
			}
		}
		else if (!valueElement.equals(other.valueElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public TargetSaturationShapeAdjustmentConfig clone() {
		final TargetSaturationShapeAdjustmentConfig config = new TargetSaturationShapeAdjustmentConfig();
		config.setValue(getValue());
		return config;
	}

	@Override
	public void toCFDG(CFDGBuilder builder) {
		if (valueElement.getValue() != null) {
			builder.append("|sat ");
			builder.append(valueElement.getValue());
		}
	}
}

