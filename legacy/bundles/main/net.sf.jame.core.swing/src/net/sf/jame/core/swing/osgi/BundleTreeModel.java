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
import java.util.Dictionary;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;

/**
 * A model for bundles tree.
 * 
 * @author Andrea Medeghini
 */
public class BundleTreeModel extends DefaultTreeModel {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new model.
	 * 
	 * @param root the root node.
	 */
	public BundleTreeModel(final DefaultMutableTreeNode root) {
		super(root);
		final Bundle[] bundles = Activator.getBundles();
		if (bundles != null) {
			Arrays.sort(bundles, new BundleComparator());
			for (final Bundle bundle : bundles) {
				root.add(createBundleNode(bundle));
			}
		}
	}

	/**
	 * Creates a new bundle node.
	 * 
	 * @param bundle the bundle.
	 * @return a new bundle node.
	 */
	@SuppressWarnings("unchecked")
	protected DefaultMutableTreeNode createBundleNode(final Bundle bundle) {
		final DefaultMutableTreeNode bundleNode = new DefaultMutableTreeNode(bundle);
		final Dictionary<String, String> headers = bundle.getHeaders();
		bundleNode.add(createHeaderNode(headers, "name", "Bundle-Name"));
		bundleNode.add(createHeaderNode(headers, "vendor", "Bundle-Vendor"));
		bundleNode.add(createHeaderNode(headers, "version", "Bundle-Version"));
		final ServiceReference[] registeredServices = bundle.getRegisteredServices();
		if ((registeredServices != null) && (registeredServices.length > 0)) {
			bundleNode.add(createServicesNode(registeredServices, "Registered services"));
		}
		final ServiceReference[] servicesInUse = bundle.getServicesInUse();
		if ((servicesInUse != null) && (servicesInUse.length > 0)) {
			bundleNode.add(createServicesNode(servicesInUse, "Services in use"));
		}
		return bundleNode;
	}

	/**
	 * Creates a new service references node.
	 * 
	 * @param serviceReferences the service references.
	 * @param name the node name.
	 * @return a new service references node.
	 */
	protected DefaultMutableTreeNode createServicesNode(final ServiceReference[] serviceReferences, final String name) {
		final DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);
		if (serviceReferences != null) {
			for (final ServiceReference serviceReference : serviceReferences) {
				node.add(createServiceReferenceNode(serviceReference));
			}
		}
		return node;
	}

	/**
	 * Creates a new service reference node.
	 * 
	 * @param serviceReference the service reference.
	 * @return a new service reference node.
	 */
	protected DefaultMutableTreeNode createServiceReferenceNode(final ServiceReference serviceReference) {
		return new DefaultMutableTreeNode(serviceReference);
	}

	private DefaultMutableTreeNode createHeaderNode(final Dictionary<String, String> headers, final String label, final String header) {
		final String value = headers.get(header);
		return new DefaultMutableTreeNode(label + " = " + ((value != null) ? value : ""));
	}
}
