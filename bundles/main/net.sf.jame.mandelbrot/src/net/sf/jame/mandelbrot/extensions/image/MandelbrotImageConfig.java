/*
 * JAME 6.1 
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2010 Andrea Medeghini
 * http://andreamedeghini.users.sourceforge.net
 *
 * This file is part of JAME.
 *
 * JAME is an application for creating fractals and other graphics artifacts.
 *
 * JAME is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JAME is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAME.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.sf.jame.mandelbrot.extensions.image;

import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.mandelbrot.MandelbrotConfig;
import net.sf.jame.mandelbrot.MandelbrotConfigBuilder;
import net.sf.jame.twister.frame.layer.image.extension.ImageExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class MandelbrotImageConfig extends ImageExtensionConfig {
	public static final String CLASS_ID = "MandelbrotImageConfig";
	public static final int IMAGE_MODE_MANDELBROT = 0;
	public static final int IMAGE_MODE_JULIA = 1;
	public static final int INPUT_MODE_ZOOM = 0;
	public static final int INPUT_MODE_SELECT = 1;
	private static final long serialVersionUID = 1L;
	private MandelbrotConfig config;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		try {
			final MandelbrotConfigBuilder configBuilder = new MandelbrotConfigBuilder(this);
			config = configBuilder.createDefaultConfig();
		}
		catch (final ExtensionException e) {
			throw new Error(e);
		}
	}

	/**
	 * @return the config
	 */
	public MandelbrotConfig getMandelbrotConfig() {
		return config;
	}

	/**
	 * @param config
	 */
	protected void setMandelbrotConfig(final MandelbrotConfig config) {
		this.config = config;
	}

	/**
	 * @return
	 */
	@Override
	public MandelbrotImageConfig clone() {
		final MandelbrotImageConfig config = new MandelbrotImageConfig();
		config.setMandelbrotConfig(getMandelbrotConfig().clone());
		return config;
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		if (config != null) {
			config.setContext(getContext());
		}
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
		final MandelbrotImageConfig other = (MandelbrotImageConfig) obj;
		if (config == null) {
			if (other.config != null) {
				return false;
			}
		}
		else if (!config.equals(other.config)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 */
	@Override
	public void dispose() {
		if (config != null) {
			config.dispose();
			config = null;
		}
		super.dispose();
	}
}
