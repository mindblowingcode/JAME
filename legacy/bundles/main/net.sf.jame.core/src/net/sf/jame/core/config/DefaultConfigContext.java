/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
package net.sf.jame.core.config;

/**
 * Default implementation of a configuration context.
 * 
 * @author Andrea Medeghini
 */
public class DefaultConfigContext implements ConfigContext {
	private ConfigContext context;
	private long timestamp;

	/**
	 * Constructs a new context.
	 */
	public DefaultConfigContext() {
		updateTimestamp();
	}

	/**
	 * @see net.sf.jame.core.config.ConfigContext#getTimestamp()
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @see net.sf.jame.core.config.ConfigContext#updateTimestamp()
	 */
	public void updateTimestamp() {
		timestamp = System.currentTimeMillis();
		if (context != null) {
			context.updateTimestamp();
		}
	}

	/**
	 * @see net.sf.jame.core.config.ConfigContext#setParentConfigContext(net.sf.jame.core.config.ConfigContext)
	 */
	public void setParentConfigContext(final ConfigContext context) {
		this.context = context;
	}
}
