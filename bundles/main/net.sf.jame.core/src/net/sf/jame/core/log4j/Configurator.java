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
package net.sf.jame.core.log4j;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 * @author Andrea Medeghini
 */
public class Configurator {
	/**
	 * 
	 */
	public static void configure() {
		Properties log4jProperties = new Properties();
		log4jProperties.put("log4j.rootLogger", "INFO, console");
		log4jProperties.put("log4j.appender.console", "org.apache.log4j.ConsoleAppender");
		log4jProperties.put("log4j.appender.console.layout", "org.apache.log4j.PatternLayout");
		log4jProperties.put("log4j.appender.console.layout.ConversionPattern", "%d{HH:mm:ss,SSS} %-5p %c - %m%n");
		log4jProperties.put("log4j.logger.net.sf.jame", System.getProperty("debugLevel", "INFO"));
		log4jProperties.put("log4j.logger.org.apache.derby", "INFO");
		PropertyConfigurator.configure(log4jProperties);
	}

	/**
	 * @param workspacePath
	 */
	public static void configure(final File workspacePath) {
		Properties log4jProperties = new Properties();
		log4jProperties.put("log4j.rootLogger", "INFO, " + System.getProperty("appenders", "file"));
		log4jProperties.put("log4j.appender.console", "org.apache.log4j.ConsoleAppender");
		log4jProperties.put("log4j.appender.console.layout", "org.apache.log4j.PatternLayout");
		log4jProperties.put("log4j.appender.console.layout.ConversionPattern", "%d{HH:mm:ss,SSS} %-5p %c - %m%n");
		log4jProperties.put("log4j.appender.file", "org.apache.log4j.FileAppender");
		log4jProperties.put("log4j.appender.file.file", workspacePath.getAbsolutePath() + "/JAME.log");
		log4jProperties.put("log4j.appender.file.layout", "org.apache.log4j.PatternLayout");
		log4jProperties.put("log4j.appender.file.layout.ConversionPattern", "%d{HH:mm:ss,SSS} %-5p - %m%n");
		log4jProperties.put("log4j.logger.net.sf.jame", System.getProperty("debugLevel", "INFO"));
		log4jProperties.put("log4j.logger.org.apache.derby", "INFO");
		PropertyConfigurator.configure(log4jProperties);
		// System.setProperty("derby.stream.error.file", workspace.getAbsolutePath() + "/derby.log");
	}

	/**
	 * @param workspacePath
	 * @param logFileName
	 */
	public static void configure(final File workspacePath, final String logFileName) {
		Properties log4jProperties = new Properties();
		log4jProperties.put("log4j.rootLogger", "INFO, " + System.getProperty("appenders", "file"));
		log4jProperties.put("log4j.appender.console", "org.apache.log4j.ConsoleAppender");
		log4jProperties.put("log4j.appender.console.layout", "org.apache.log4j.PatternLayout");
		log4jProperties.put("log4j.appender.console.layout.ConversionPattern", "%d{HH:mm:ss,SSS} %-5p %c - %m%n");
		log4jProperties.put("log4j.appender.file", "org.apache.log4j.FileAppender");
		log4jProperties.put("log4j.appender.file.file", workspacePath.getAbsolutePath() + "/" + logFileName);
		log4jProperties.put("log4j.appender.file.layout", "org.apache.log4j.PatternLayout");
		log4jProperties.put("log4j.appender.file.layout.ConversionPattern", "%d{HH:mm:ss,SSS} %-5p - %m%n");
		log4jProperties.put("log4j.logger.net.sf.jame", System.getProperty("debugLevel", "INFO"));
		log4jProperties.put("log4j.logger.org.apache.derby", "INFO");
		PropertyConfigurator.configure(log4jProperties);
		// System.setProperty("derby.stream.error.file", workspace.getAbsolutePath() + "/derby.log");
	}
}
