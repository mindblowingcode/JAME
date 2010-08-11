/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.CFDGBuilder;
import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 */
public class SquareFigureConfig extends FigureExtensionConfig {
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
		final SquareFigureConfig other = (SquareFigureConfig) obj;
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public SquareFigureConfig clone() {
		final SquareFigureConfig config = new SquareFigureConfig();
		return config;
	}

	@Override
	public void toCFDG(CFDGBuilder builder) {
		builder.append("/* SQUARE */\n");
	}
}
