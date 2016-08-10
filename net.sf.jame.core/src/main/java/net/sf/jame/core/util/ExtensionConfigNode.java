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
package net.sf.jame.core.util;

import net.sf.jame.core.CoreResources;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.tree.DefaultNode;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;

/**
 * @author Andrea Medeghini
 */
public class ExtensionConfigNode<T extends ExtensionConfig> extends DefaultNode {
	public static final String NODE_CLASS = "node.class.ExtensionConfigElement";
	public static final String NODE_LABEL = CoreResources.getInstance().getString("node.label.ExtensionConfigElement");
	private final T config;

	/**
	 * @param config
	 */
	public ExtensionConfigNode(final T config) {
		super(config.getExtensionId());
		this.config = config;
		setNodeClass(ExtensionConfigNode.NODE_CLASS);
		setNodeLabel(ExtensionConfigNode.NODE_LABEL);
		setNodeValue(this.createNodeValue(config));
		this.createChildNodes();
	}

	/**
	 * @return the config
	 */
	public T getConfig() {
		return this.config;
	}

	/**
	 * 
	 */
	protected void createChildNodes() {
	}

	/**
	 * @param value
	 * @return
	 */
	protected NodeValue<?> createNodeValue(final T value) {
		return null;
	}

	/**
	 * @see net.sf.jame.core.tree.Node#isEditable()
	 */
	@Override
	public boolean isEditable() {
		return false;
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return null;
	}
}
