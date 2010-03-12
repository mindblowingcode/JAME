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

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author andrea
 *
 */
public class Activator implements BundleActivator {
	public static String driverClass;
	public static String shutdownUrl;
	public static String createUrl;

	public void start(BundleContext context) throws Exception {
		ResourceBundle bundle = ResourceBundle.getBundle("net.sf.jame.service.derby");
		driverClass = bundle.getString("driverClass");
		shutdownUrl = bundle.getString("shutdownUrl");
		createUrl = bundle.getString("createUrl");
		try {
			Class.forName(driverClass);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void stop(BundleContext context) throws Exception {
		try {
			DriverManager.getConnection(shutdownUrl);
		} catch (SQLException e) {
		}
	}
}
