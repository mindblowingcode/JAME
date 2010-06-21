/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathAdjustment;

import java.lang.Float;
import java.util.ArrayList;
import java.util.List;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.extension.PathAdjustmentExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class FlipPathAdjustmentConfig extends PathAdjustmentExtensionConfig {
	private static final long serialVersionUID = 1L;
	private FloatElement flipXElement;
	private FloatElement flipYElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		flipXElement = new FloatElement(0f);
		flipYElement = new FloatElement(0f);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(flipXElement);
		elements.add(flipYElement);
		return elements;
	}

	/**
	 * @return
	 */
	public FloatElement getFlipXElement() {
		return flipXElement;
	}
	
	/**
	 * @return
	 */
	public Float getFlipX() {
		return flipXElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setFlipX(final Float value) {
		flipXElement.setValue(value);
	}
	/**
	 * @return
	 */
	public FloatElement getFlipYElement() {
		return flipYElement;
	}
	
	/**
	 * @return
	 */
	public Float getFlipY() {
		return flipYElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setFlipY(final Float value) {
		flipYElement.setValue(value);
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
		final FlipPathAdjustmentConfig other = (FlipPathAdjustmentConfig) obj;
		if (flipXElement == null) {
			if (other.flipXElement != null) {
				return false;
			}
		}
		else if (!flipXElement.equals(other.flipXElement)) {
			return false;
		}
		if (flipYElement == null) {
			if (other.flipYElement != null) {
				return false;
			}
		}
		else if (!flipYElement.equals(other.flipYElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public FlipPathAdjustmentConfig clone() {
		final FlipPathAdjustmentConfig config = new FlipPathAdjustmentConfig();
		config.setFlipX(getFlipX());
		config.setFlipY(getFlipY());
		return config;
	}
}
