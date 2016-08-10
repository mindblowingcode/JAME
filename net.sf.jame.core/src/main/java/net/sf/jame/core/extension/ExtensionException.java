/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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
package net.sf.jame.core.extension;

/**
 * Root class of all extension exceptions.
 * 
 * @author Andrea Medeghini
 */
public class ExtensionException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception.
	 */
	public ExtensionException() {
	}

	/**
	 * Constructs a new exception.
	 * 
	 * @param message the exception message.
	 * @param e the exception cause.
	 */
	public ExtensionException(final String message, final Throwable e) {
		super(message, e);
	}

	/**
	 * Constructs a new exception.
	 * 
	 * @param message the exception message.
	 */
	public ExtensionException(final String message) {
		super(message);
	}

	/**
	 * Constructs a new exception.
	 * 
	 * @param e the exception cause.
	 */
	public ExtensionException(final Throwable e) {
		super(e);
	}
}
