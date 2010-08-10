/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.lang.Float;
import java.util.ArrayList;
import java.util.List;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class SmoothQuadToPathReplacementConfig extends PathReplacementExtensionConfig {
	private static final long serialVersionUID = 1L;
	private FloatElement xElement;
	private FloatElement yElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		xElement = new FloatElement(0f);
		yElement = new FloatElement(0f);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(xElement);
		elements.add(yElement);
		return elements;
	}

	/**
	 * @return
	 */
	public FloatElement getXElement() {
		return xElement;
	}
	
	/**
	 * @return
	 */
	public Float getX() {
		return xElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setX(final Float value) {
		xElement.setValue(value);
	}
	/**
	 * @return
	 */
	public FloatElement getYElement() {
		return yElement;
	}
	
	/**
	 * @return
	 */
	public Float getY() {
		return yElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setY(final Float value) {
		yElement.setValue(value);
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
		final SmoothQuadToPathReplacementConfig other = (SmoothQuadToPathReplacementConfig) obj;
		if (xElement == null) {
			if (other.xElement != null) {
				return false;
			}
		}
		else if (!xElement.equals(other.xElement)) {
			return false;
		}
		if (yElement == null) {
			if (other.yElement != null) {
				return false;
			}
		}
		else if (!yElement.equals(other.yElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public SmoothQuadToPathReplacementConfig clone() {
		final SmoothQuadToPathReplacementConfig config = new SmoothQuadToPathReplacementConfig();
		config.setX(getX());
		config.setY(getY());
		return config;
	}

	@Override
	public void toCFDG(StringBuilder builder) {
		builder.append("QUADTO {");
		if (xElement.getValue() != null) {
			builder.append(" x ");
			builder.append(xElement.getValue());
		}
		if (yElement.getValue() != null) {
			builder.append(" y ");
			builder.append(yElement.getValue());
		}
		builder.append(" }");
	}
}
