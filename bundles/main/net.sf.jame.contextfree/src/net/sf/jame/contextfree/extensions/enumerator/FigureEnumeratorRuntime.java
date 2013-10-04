/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.enumerator;

import java.util.LinkedList;
import java.util.List;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.figure.extension.FigureExtensionRuntime;
import net.sf.jame.core.enumerator.extension.EnumeratorExtensionRuntime;
import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.scripting.JSException;
import net.sf.jame.core.scripting.JSExtension;

/**
 * @author Andrea Medeghini
 */
public class FigureEnumeratorRuntime extends EnumeratorExtensionRuntime {
	/**
	 * @see net.sf.jame.core.enumerator.extension.EnumeratorExtensionRuntime#listExtensions()
	 */
	@Override
	public List<String> listExtensions() throws JSException {
		List<Extension<FigureExtensionRuntime<?>>> extensions = ContextFreeRegistry.getInstance().getFigureRegistry().getExtensionList();
		List<String> references = new LinkedList<String>();
		for (Extension<FigureExtensionRuntime<?>> extension : extensions) {
			references.add(extension.getExtensionId());
		}
		return references;
	}

	/**
	 * @see net.sf.jame.core.enumerator.extension.EnumeratorExtensionRuntime#getExtension(java.lang.String)
	 */
	@Override
	public JSExtension getExtension(final String extensionId) throws JSException {
		try {
			return new JSExtension(ContextFreeRegistry.getInstance().getFigureExtension(extensionId));
		}
		catch (ExtensionNotFoundException e) {
			throw new JSException(e);
		}
	}
}
