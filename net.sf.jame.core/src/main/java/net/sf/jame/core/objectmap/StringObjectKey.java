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
package net.sf.jame.core.objectmap;

/**
 * @author Andrea Medeghini
 */
public class StringObjectKey implements ObjectKey, Comparable<StringObjectKey> {
	private static final long serialVersionUID = 1L;
	private final String keyText;

	/**
	 * @param field
	 */
	public StringObjectKey(final String keyText) {
		if (keyText == null) {
			throw new IllegalArgumentException("keyText == null");
		}
		this.keyText = keyText;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object key) {
		if (key == null) {
			return false;
		}
		if (key == this) {
			return true;
		}
		return keyText.equals(((StringObjectKey) key).keyText);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return keyText.hashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return keyText;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(final StringObjectKey key) {
		return keyText.compareTo(key.keyText);
	}

	/**
	 * @return the keyText
	 */
	public String getKeyText() {
		return keyText;
	}
}
