/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import java.util.ArrayList;
import java.util.List;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class CircleFigureConfig extends FigureExtensionConfig {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		return elements;
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
		final CircleFigureConfig other = (CircleFigureConfig) obj;
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public CircleFigureConfig clone() {
		final CircleFigureConfig config = new CircleFigureConfig();
		return config;
	}

	@Override
	public void toCFDG(StringBuilder builder) {
		builder.append("/* CIRCLE */\n");
	}
}
