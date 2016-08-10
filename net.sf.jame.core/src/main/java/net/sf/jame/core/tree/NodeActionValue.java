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
package net.sf.jame.core.tree;

import java.io.Serializable;

/**
 * @author Andrea Medeghini
 */
public class NodeActionValue {
	private int actionType;
	private String actionId;
	private long timestamp;
	private boolean refreshRequired;
	private NodePath actionTarget;
	private Serializable[] actionParams;

	/**
	 * @return the actionTarget
	 */
	public NodePath getActionTarget() {
		return actionTarget;
	}

	/**
	 * @param actionTarget the actionTarget to set
	 */
	public void setActionTarget(final NodePath actionTarget) {
		this.actionTarget = actionTarget;
	}

	/**
	 * @return the actionType
	 */
	public int getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(final int actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the nodeId
	 */
	public String getActionId() {
		return actionId;
	}

	/**
	 * @param nodeId the nodeId to set
	 */
	public void setActionId(final String nodeId) {
		actionId = nodeId;
	}

	/**
	 * @return the params
	 */
	public Serializable[] getActionParams() {
		return actionParams;
	}

	/**
	 * @param params the params to set
	 */
	public void setActionParams(final Serializable[] params) {
		actionParams = params;
	}

	/**
	 * @param timestamp
	 */
	public void setTimestamp(final long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the refreshRequired
	 */
	public boolean isRefreshRequired() {
		return refreshRequired;
	}

	/**
	 * @param refreshRequired the refreshRequired to set
	 */
	public void setRefreshRequired(final boolean refreshRequired) {
		this.refreshRequired = refreshRequired;
	}
}
