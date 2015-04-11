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
package net.sf.jame.core.creator.extension;

import net.sf.jame.core.extension.ExtensionRuntime;
import net.sf.jame.core.scripting.JSException;

/**
 * @author Andrea Medeghini
 */
public abstract class CreatorExtensionRuntime extends ExtensionRuntime {
	/**
	 * @param args
	 * @return
	 * @throws JSException
	 */
	public abstract Object create(Object... args) throws JSException;

	protected Double toDouble(Object argument) {
		if (argument instanceof Double) {
			return (Double) argument;
		} else if (argument instanceof Integer) {
			return new Double((Integer) argument);
		}
		throw new RuntimeException();
	}

	protected Float toFloat(Object argument) {
		if (argument instanceof Double) {
			return new Float(((Double) argument).floatValue());
		} else if (argument instanceof Integer) {
			return new Float((Integer) argument);
		}
		throw new RuntimeException();
	}

	protected Integer toInteger(Object argument) {
		if (argument instanceof Integer) {
			return (Integer) argument;
		} else if (argument instanceof Double) {
			return new Integer(((Double) argument).intValue());
		}
		throw new RuntimeException();
	}

	protected Long toLong(Object argument) {
		if (argument instanceof Integer) {
			return new Long((Integer) argument);
		} else if (argument instanceof Double) {
			return new Long(((Double) argument).longValue());
		}
		throw new RuntimeException();
	}
}
