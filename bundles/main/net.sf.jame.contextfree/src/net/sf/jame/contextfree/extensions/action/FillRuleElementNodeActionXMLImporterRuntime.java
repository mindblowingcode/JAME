/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.action;

import net.sf.jame.contextfree.common.FillRuleElement;
import net.sf.jame.contextfree.common.FillRuleElementXMLImporter;
import net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class FillRuleElementNodeActionXMLImporterRuntime extends ConfigElementNodeActionXMLImporterRuntime<FillRuleElement> {
	/**
	 * @see net.sf.jame.core.util.ConfigElementNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected FillRuleElementXMLImporter createImporter() {
		return new FillRuleElementXMLImporter();
	}
}
