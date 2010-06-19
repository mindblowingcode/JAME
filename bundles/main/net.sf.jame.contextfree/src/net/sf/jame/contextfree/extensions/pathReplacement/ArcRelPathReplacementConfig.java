/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionConfig;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 */
public class ArcRelPathReplacementConfig extends PathReplacementExtensionConfig {
	private static final long serialVersionUID = 1L;
	private FloatElement xElement;
	private FloatElement yElement;
	private FloatElement rxElement;
	private FloatElement ryElement;
	private FloatElement rElement;
	private StringElement modeElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		xElement = new FloatElement(0f);
		yElement = new FloatElement(0f);
		rxElement = new FloatElement(0f);
		ryElement = new FloatElement(0f);
		rElement = new FloatElement(0f);
		modeElement = new StringElement("cw");
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(xElement);
		elements.add(yElement);
		elements.add(rxElement);
		elements.add(ryElement);
		elements.add(rElement);
		elements.add(modeElement);
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
	 * @return
	 */
	public FloatElement getRxElement() {
		return rxElement;
	}
	
	/**
	 * @return
	 */
	public Float getRx() {
		return rxElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setRx(final Float value) {
		rxElement.setValue(value);
	}
	/**
	 * @return
	 */
	public FloatElement getRyElement() {
		return ryElement;
	}
	
	/**
	 * @return
	 */
	public Float getRy() {
		return ryElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setRy(final Float value) {
		ryElement.setValue(value);
	}
	/**
	 * @return
	 */
	public FloatElement getRElement() {
		return rElement;
	}
	
	/**
	 * @return
	 */
	public Float getR() {
		return rElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setR(final Float value) {
		rElement.setValue(value);
	}
	/**
	 * @return
	 */
	public StringElement getModeElement() {
		return modeElement;
	}
	
	/**
	 * @return
	 */
	public String getMode() {
		return modeElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setMode(final String value) {
		modeElement.setValue(value);
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
		final ArcRelPathReplacementConfig other = (ArcRelPathReplacementConfig) obj;
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
		if (rxElement == null) {
			if (other.rxElement != null) {
				return false;
			}
		}
		else if (!rxElement.equals(other.rxElement)) {
			return false;
		}
		if (ryElement == null) {
			if (other.ryElement != null) {
				return false;
			}
		}
		else if (!ryElement.equals(other.ryElement)) {
			return false;
		}
		if (rElement == null) {
			if (other.rElement != null) {
				return false;
			}
		}
		else if (!rElement.equals(other.rElement)) {
			return false;
		}
		if (modeElement == null) {
			if (other.modeElement != null) {
				return false;
			}
		}
		else if (!modeElement.equals(other.modeElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public ArcRelPathReplacementConfig clone() {
		final ArcRelPathReplacementConfig config = new ArcRelPathReplacementConfig();
		config.setX(getX());
		config.setY(getY());
		config.setRx(getRx());
		config.setRy(getRy());
		config.setR(getR());
		config.setMode(getMode());
		return config;
	}
}
