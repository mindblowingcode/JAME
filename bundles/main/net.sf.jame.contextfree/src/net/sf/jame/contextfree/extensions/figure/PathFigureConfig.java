/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.cfdg.path.PathConfigElement;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 */
public class PathFigureConfig extends FigureExtensionConfig {
	private static final long serialVersionUID = 1L;
	private PathConfigElement pathElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		pathElement = new PathConfigElement();
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(pathElement);
		return elements;
	}

	/**
	 * @return
	 */
	public PathConfigElement getPathElement() {
		return pathElement;
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
		final PathFigureConfig other = (PathFigureConfig) obj;
		if (pathElement == null) {
			if (other.pathElement != null) {
				return false;
			}
		}
		else if (!pathElement.equals(other.pathElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public PathFigureConfig clone() {
		final PathFigureConfig config = new PathFigureConfig();
		config.pathElement.copyFrom(getPathElement());
		return config;
	}
}
