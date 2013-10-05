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
package net.sf.jame.queue.encoder.extension;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.core.util.ProgressListener;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractEncoderExtensionRuntime<T extends EncoderExtensionConfig> extends EncoderExtensionRuntime<T> {
	private final List<ProgressListener> listeners = new ArrayList<ProgressListener>();

	/**
	 * @see net.sf.jame.queue.encoder.extension.EncoderExtensionRuntime#addProgressListener(net.sf.jame.queue.ProgressListener)
	 */
	@Override
	public void addProgressListener(final ProgressListener listener) {
		this.listeners.add(listener);
	}

	/**
	 * @see net.sf.jame.queue.encoder.extension.EncoderExtensionRuntime#removeProgressListener(net.sf.jame.queue.ProgressListener)
	 */
	@Override
	public void removeProgressListener(final ProgressListener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * @param percent
	 */
	protected void fireStateChanged(final int percent) {
		for (final ProgressListener listener : listeners) {
			listener.stateChanged("Encoding...", percent);
		}
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionRuntime#dispose()
	 */
	@Override
	public void dispose() {
	}
}
