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
package net.sf.jame.core.scripting;

import net.sf.jame.core.CoreRegistry;
import net.sf.jame.core.constructor.extension.ConstructorExtensionRuntime;
import net.sf.jame.core.enumerator.extension.EnumeratorExtensionRuntime;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;

/**
 * @author Andrea Medeghini
 */
public abstract class DefaultJSContext implements JSContext {
	/**
	 * @see net.sf.jame.core.scripting.JSContext#getConstructor(java.lang.String)
	 */
	public ConstructorExtensionRuntime getConstructor(final String elementClassId) throws JSException {
		try {
			return CoreRegistry.getInstance().getConstructorExtension(elementClassId).createExtensionRuntime();
		}
		catch (ExtensionNotFoundException e) {
			throw new JSException(e);
		}
		catch (ExtensionException e) {
			throw new JSException(e);
		}
	}

	/**
	 * @see net.sf.jame.core.scripting.JSContext#getEnumerator(java.lang.String)
	 */
	public EnumeratorExtensionRuntime getEnumerator(final String elementClassId) throws JSException {
		try {
			return CoreRegistry.getInstance().getEnumeratorExtension(elementClassId).createExtensionRuntime();
		}
		catch (ExtensionNotFoundException e) {
			throw new JSException(e);
		}
		catch (ExtensionException e) {
			throw new JSException(e);
		}
	}

	/**
	 * @see net.sf.jame.core.scripting.JSContext#println(java.lang.String)
	 */
	public void println(final String s) {
		System.out.println(s);
	}

	/**
	 * @see net.sf.jame.core.scripting.JSContext#sleep(long)
	 */
	public boolean sleep(long time) {
		try {
			if (Thread.interrupted()) {
				return true;
			}
			Thread.sleep(time);
			return false;
		}
		catch (InterruptedException e) {
		}
		return true;
	}

	/**
	 * @see net.sf.jame.core.scripting.JSContext#showMessage(java.lang.String, float, float, float, long, boolean)
	 */
	public void showMessage(String message, float size, float x, float y, long tim, boolean hasBackground) {
	}
}
