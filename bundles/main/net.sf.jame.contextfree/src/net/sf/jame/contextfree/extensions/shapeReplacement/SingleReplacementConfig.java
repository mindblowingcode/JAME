/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeReplacement;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.cfdg.replacement.SingleReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionConfig;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 */
public class SingleReplacementConfig extends ShapeReplacementExtensionConfig {
	private static final long serialVersionUID = 1L;
	private SingleReplacementConfigElement singleReplacementElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		singleReplacementElement = new SingleReplacementConfigElement();
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(singleReplacementElement);
		return elements;
	}

	/**
	 * @return
	 */
	public SingleReplacementConfigElement getSingleReplacementElement() {
		return singleReplacementElement;
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
		final SingleReplacementConfig other = (SingleReplacementConfig) obj;
		if (singleReplacementElement == null) {
			if (other.singleReplacementElement != null) {
				return false;
			}
		}
		else if (!singleReplacementElement.equals(other.singleReplacementElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public SingleReplacementConfig clone() {
		final SingleReplacementConfig config = new SingleReplacementConfig();
		config.singleReplacementElement.copyFrom(getSingleReplacementElement());
		return config;
	}
}
