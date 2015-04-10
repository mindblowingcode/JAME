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
package net.sf.jame.service.network;

import java.io.Serializable;

/**
 * @author Andrea Medeghini
 */
public class EventMessage extends ServiceMessage {
	private static final long serialVersionUID = 1L;
	private Serializable userData;

	/**
	 * 
	 */
	public EventMessage() {
		super(MessageIDFactory.newMessageId(), MESSAGE_TYPE_EVENT);
	}

	/**
	 * @return the userData
	 */
	public Serializable getUserData() {
		return userData;
	}

	/**
	 * @param userData the userData to set
	 */
	public void setUserData(final Serializable userData) {
		this.userData = userData;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append("messageId = ");
		builder.append(getMessageId());
		builder.append(", messageType = ");
		builder.append(decodeMessageType(getMessageType()));
		builder.append(", userData = ");
		builder.append("[");
		builder.append(getUserData());
		builder.append("]");
		builder.append("]");
		return builder.toString();
	}
}
