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
package net.sf.jame.core.swing.osgi;

import java.util.Arrays;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.eclipse.core.runtime.IBundleGroup;
import org.eclipse.core.runtime.IBundleGroupProvider;
import org.eclipse.core.runtime.Platform;

/**
 * A model for bundle group providers trees.
 * 
 * @author Andrea Medeghini
 */
public class BundleGroupProviderTreeModel extends DefaultTreeModel {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new model.
	 * 
	 * @param root the root node.
	 */
	public BundleGroupProviderTreeModel(final DefaultMutableTreeNode root) {
		super(root);
		final IBundleGroupProvider[] bundleGroupProviders = Platform.getBundleGroupProviders();
		if (bundleGroupProviders != null) {
			Arrays.sort(bundleGroupProviders, new BundleGroupProviderComparator());
			for (final IBundleGroupProvider bundleGroupProvider : bundleGroupProviders) {
				root.add(createBundleGroupProviderNode(bundleGroupProvider));
			}
		}
	}

	/**
	 * Creates a new bundle group provider node.
	 * 
	 * @param bundleGroupProvider the bundle group provider.
	 * @return a new bundle group provider.
	 */
	protected DefaultMutableTreeNode createBundleGroupProviderNode(final IBundleGroupProvider bundleGroupProvider) {
		final DefaultMutableTreeNode bundleGroupProviderNode = new DefaultMutableTreeNode(bundleGroupProvider);
		final IBundleGroup[] bundleGroups = bundleGroupProvider.getBundleGroups();
		if (bundleGroups != null) {
			Arrays.sort(bundleGroups, new BundleGroupComparator());
			for (final IBundleGroup bundleGroup : bundleGroups) {
				bundleGroupProviderNode.add(createBundleGroupNode(bundleGroup));
			}
		}
		return bundleGroupProviderNode;
	}

	/**
	 * Creates a new bundle group node.
	 * 
	 * @param bundleGroup the bundle group.
	 * @return a new bundle group node.
	 */
	protected DefaultMutableTreeNode createBundleGroupNode(final IBundleGroup bundleGroup) {
		final DefaultMutableTreeNode bundleGroupNode = new DefaultMutableTreeNode(bundleGroup);
		bundleGroupNode.add(new DefaultMutableTreeNode(bundleGroup.getProviderName()));
		bundleGroupNode.add(new DefaultMutableTreeNode(bundleGroup.getDescription()));
		bundleGroupNode.add(new DefaultMutableTreeNode(bundleGroup.getVersion()));
		return bundleGroupNode;
	}
}
