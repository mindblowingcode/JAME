/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeAdjustment;

import java.lang.Float;
import java.util.ArrayList;
import java.util.List;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class Size2ShapeAdjustmentConfig extends ShapeAdjustmentExtensionConfig {
	private static final long serialVersionUID = 1L;
	private FloatElement scaleXElement;
	private FloatElement scaleYElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		scaleXElement = new FloatElement(0f);
		scaleYElement = new FloatElement(0f);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(scaleXElement);
		elements.add(scaleYElement);
		return elements;
	}

	/**
	 * @return
	 */
	public FloatElement getScaleXElement() {
		return scaleXElement;
	}
	
	/**
	 * @return
	 */
	public Float getScaleX() {
		return scaleXElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setScaleX(final Float value) {
		scaleXElement.setValue(value);
	}
	/**
	 * @return
	 */
	public FloatElement getScaleYElement() {
		return scaleYElement;
	}
	
	/**
	 * @return
	 */
	public Float getScaleY() {
		return scaleYElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setScaleY(final Float value) {
		scaleYElement.setValue(value);
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
		final Size2ShapeAdjustmentConfig other = (Size2ShapeAdjustmentConfig) obj;
		if (scaleXElement == null) {
			if (other.scaleXElement != null) {
				return false;
			}
		}
		else if (!scaleXElement.equals(other.scaleXElement)) {
			return false;
		}
		if (scaleYElement == null) {
			if (other.scaleYElement != null) {
				return false;
			}
		}
		else if (!scaleYElement.equals(other.scaleYElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public Size2ShapeAdjustmentConfig clone() {
		final Size2ShapeAdjustmentConfig config = new Size2ShapeAdjustmentConfig();
		config.setScaleX(getScaleX());
		config.setScaleY(getScaleY());
		return config;
	}
}
