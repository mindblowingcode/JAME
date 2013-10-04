/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.common.FillRuleElement;
import net.sf.jame.contextfree.common.FillRuleElementXMLExporter;
import net.sf.jame.core.util.AbstractConfigElementNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class FillRuleElementNodeActionXMLExporterRuntime extends AbstractConfigElementNodeActionXMLExporterRuntime<FillRuleElement> {
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected FillRuleElementXMLExporter createExporter() {
		return new FillRuleElementXMLExporter();
	}
}
