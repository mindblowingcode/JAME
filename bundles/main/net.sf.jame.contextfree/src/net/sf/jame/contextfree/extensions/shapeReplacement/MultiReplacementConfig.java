/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeReplacement;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.cfdg.replacement.MultiReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionConfig;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 */
public class MultiReplacementConfig extends ShapeReplacementExtensionConfig {
	private static final long serialVersionUID = 1L;
	private MultiReplacementConfigElement multiReplacementElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		multiReplacementElement = new MultiReplacementConfigElement();
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(multiReplacementElement);
		return elements;
	}

	/**
	 * @return
	 */
	public MultiReplacementConfigElement getReplacementElement() {
		return multiReplacementElement;
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
		final MultiReplacementConfig other = (MultiReplacementConfig) obj;
		if (multiReplacementElement == null) {
			if (other.multiReplacementElement != null) {
				return false;
			}
		}
		else if (!multiReplacementElement.equals(other.multiReplacementElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public MultiReplacementConfig clone() {
		final MultiReplacementConfig config = new MultiReplacementConfig();
		config.multiReplacementElement.copyFrom(getReplacementElement());
		return config;
	}
}
