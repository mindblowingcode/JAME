/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.lang.Boolean;
import java.util.ArrayList;
import java.util.List;
import net.sf.jame.core.common.BooleanElement;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class ClosePolyPathReplacementConfig extends PathReplacementExtensionConfig {
	private static final long serialVersionUID = 1L;
	private BooleanElement alignElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		alignElement = new BooleanElement(false);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(alignElement);
		return elements;
	}

	/**
	 * @return
	 */
	public BooleanElement getAlignElement() {
		return alignElement;
	}
	
	/**
	 * @return
	 */
	public Boolean isAlign() {
		return alignElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setAlign(final Boolean value) {
		alignElement.setValue(value);
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
		final ClosePolyPathReplacementConfig other = (ClosePolyPathReplacementConfig) obj;
		if (alignElement == null) {
			if (other.alignElement != null) {
				return false;
			}
		}
		else if (!alignElement.equals(other.alignElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public ClosePolyPathReplacementConfig clone() {
		final ClosePolyPathReplacementConfig config = new ClosePolyPathReplacementConfig();
		config.setAlign(isAlign());
		return config;
	}

	@Override
	public void toCFDG(StringBuilder builder) {
		builder.append("CLOSEPOLY {");
		if (alignElement.getValue() != null) {
			builder.append(" ");
			builder.append(alignElement.getValue() ? "p align" : "");
		}
		builder.append(" }");
	}
}
