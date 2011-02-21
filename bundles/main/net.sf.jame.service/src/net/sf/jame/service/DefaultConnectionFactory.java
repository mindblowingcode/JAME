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
package net.sf.jame.service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.jame.core.util.ConnectionFactory;

import org.apache.log4j.Logger;

/**
 * @author Andrea Medeghini
 */
public class DefaultConnectionFactory implements ConnectionFactory {
	private static final Logger logger = Logger.getLogger(DefaultConnectionFactory.class);
	private File workspace;

	/**
	 * @param workspace
	 */
	public DefaultConnectionFactory(final File workspace) {
		this.workspace = workspace;
	}

	/**
	 * @see net.sf.jame.core.util.ConnectionFactory#createConnection()
	 */
	public Connection createConnection() throws SQLException {
		if (DefaultConnectionFactory.logger.isDebugEnabled()) {
			DefaultConnectionFactory.logger.debug("Create a connection");
		}
		return DriverManager.getConnection(Activator.createUrl.replace("${workspace}", workspace.getAbsolutePath()));
	}
}
