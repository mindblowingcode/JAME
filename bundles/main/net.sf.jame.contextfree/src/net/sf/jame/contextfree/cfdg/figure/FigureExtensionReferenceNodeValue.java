/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.figure;

import net.sf.jame.core.common.ExtensionReferenceElementNodeValue;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;

public class FigureExtensionReferenceNodeValue extends ExtensionReferenceElementNodeValue<ConfigurableExtensionReference<FigureExtensionConfig>> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public FigureExtensionReferenceNodeValue(final ConfigurableExtensionReference<FigureExtensionConfig> value) {
		super(value);
	}
}
