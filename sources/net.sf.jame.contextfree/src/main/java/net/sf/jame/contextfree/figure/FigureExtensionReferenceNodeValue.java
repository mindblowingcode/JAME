/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.figure;

import net.sf.jame.contextfree.figure.extension.FigureExtensionConfig;
import net.sf.jame.core.common.ExtensionReferenceElementNodeValue;
import net.sf.jame.core.extension.ConfigurableExtensionReference;

/**
 * @author Andrea Medeghini
 */
public class FigureExtensionReferenceNodeValue extends ExtensionReferenceElementNodeValue<ConfigurableExtensionReference<FigureExtensionConfig>> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public FigureExtensionReferenceNodeValue(final ConfigurableExtensionReference<FigureExtensionConfig> value) {
		super(value);
	}
}
