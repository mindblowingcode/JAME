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
package net.sf.jame.test;

import org.apache.log4j.Logger;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractTest {
	protected static Logger logger = Logger.getLogger("test");

	/**
	 * @param message
	 */
	public void info(final String message) {
		AbstractTest.logger.info(message);
	}

	/**
	 * @param message
	 */
	public void error(final String message) {
		AbstractTest.logger.error(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public void error(final String message, final Exception e) {
		AbstractTest.logger.error(message, e);
	}
}
