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
package net.sf.jame.twister.renderer;

/**
 * @author Andrea Medeghini
 */
public interface TwisterRenderingHints {
	public static final Object KEY_QUALITY = new Object();
	public static final Object KEY_MEMORY = new Object();
	public static final Object KEY_TYPE = new Object();
	public static final Object QUALITY_DEFAULT = new Object();
	public static final Object QUALITY_REALTIME = new Object();
	public static final Object MEMORY_DEFAULT = new Object();
	public static final Object MEMORY_LOW = new Object();
	public static final Object TYPE_DEFAULT = new Object();
	public static final Object TYPE_OVERLAY = new Object();
	public static final Object TYPE_PREVIEW = new Object();
}
