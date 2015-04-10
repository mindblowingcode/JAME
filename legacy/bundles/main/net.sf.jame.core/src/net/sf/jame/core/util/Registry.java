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
package net.sf.jame.core.util;

import java.util.Hashtable;

/**
 * Interface of registries.
 * 
 * @author Andrea Medeghini
 * @param <T> the registry elements type.
 */
public class Registry<T> {
	private final Hashtable<String, T> registry = new Hashtable<String, T>();

	/**
	 * Puts an object into the registry.
	 * 
	 * @param objectId the objectId.
	 * @param object the object.
	 */
	public void put(final String objectId, final T object) {
		this.registry.put(objectId, object);
	}

	/**
	 * Gets an object by its objectId.
	 * 
	 * @param objectId the objectId.
	 * @return the object.
	 */
	public T get(final String objectId) {
		return this.registry.get(objectId);
	}

	/**
	 * Checks if the registry contains a given objectId.
	 * 
	 * @param objectId the objectId.
	 * @return true if the registry contains the objectId.
	 */
	public boolean contains(final String objectId) {
		return this.registry.containsKey(objectId);
	}

	/**
	 * 
	 */
	public void clear() {
		this.registry.clear();
	}
}
