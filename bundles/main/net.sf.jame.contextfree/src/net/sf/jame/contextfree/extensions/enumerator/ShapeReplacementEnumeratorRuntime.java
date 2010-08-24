/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.enumerator;

import java.util.LinkedList;
import java.util.List;
import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionRuntime;
import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.scripting.JSException;
import net.sf.jame.core.scripting.JSExtension;
import net.sf.jame.core.scripting.extension.EnumeratorExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class ShapeReplacementEnumeratorRuntime extends EnumeratorExtensionRuntime {
	/**
	 * @see net.sf.jame.core.scripting.extension.EnumeratorExtensionRuntime#listExtensions()
	 */
	@Override
	public List<String> listExtensions() throws JSException {
		List<Extension<ShapeReplacementExtensionRuntime<?>>> extensions = ContextFreeRegistry.getInstance().getShapeReplacementRegistry().getExtensionList();
		List<String> references = new LinkedList<String>();
		for (Extension<ShapeReplacementExtensionRuntime<?>> extension : extensions) {
			references.add(extension.getExtensionId());
		}
		return references;
	}

	/**
	 * @see net.sf.jame.core.scripting.extension.EnumeratorExtensionRuntime#getExtension(java.lang.String)
	 */
	@Override
	public JSExtension getExtension(final String extensionId) throws JSException {
		try {
			return new JSExtension(ContextFreeRegistry.getInstance().getShapeReplacementExtension(extensionId));
		}
		catch (ExtensionNotFoundException e) {
			throw new JSException(e);
		}
	}
}
