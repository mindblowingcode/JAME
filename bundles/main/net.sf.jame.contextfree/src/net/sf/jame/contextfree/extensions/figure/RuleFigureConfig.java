/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.cfdg.rule.RuleConfigElement;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 */
public class RuleFigureConfig extends FigureExtensionConfig {
	private static final long serialVersionUID = 1L;
	private RuleConfigElement ruleElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		ruleElement = new RuleConfigElement();
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(ruleElement);
		return elements;
	}

	/**
	 * @return
	 */
	public RuleConfigElement getRuleElement() {
		return ruleElement;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		final RuleFigureConfig other = (RuleFigureConfig) obj;
		if (ruleElement == null) {
			if (other.ruleElement != null) {
				return false;
			}
		}
		else if (!ruleElement.equals(other.ruleElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public RuleFigureConfig clone() {
		final RuleFigureConfig config = new RuleFigureConfig();
		config.ruleElement.copyFrom(getRuleElement());
		return config;
	}
}
