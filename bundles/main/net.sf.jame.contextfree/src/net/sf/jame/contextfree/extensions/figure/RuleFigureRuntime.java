/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.cfdg.rule.RuleRuntimeElement;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;

/**
 * @author Andrea Medeghini
 */
public class RuleFigureRuntime<T extends RuleFigureConfig> extends FigureExtensionRuntime<T> {
	private RuleRuntimeElement ruleElement;
	private RuleElementListener ruleElementListener;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		ruleElement = new RuleRuntimeElement(getConfig().getRuleElement());
		ruleElementListener = new RuleElementListener();
		getConfig().getRuleElement().addChangeListener(ruleElementListener);
	}

	@Override
	public void dispose() {
		if ((getConfig() != null) && (ruleElementListener != null)) {
			getConfig().getRuleElement().removeChangeListener(ruleElementListener);
		}
		ruleElementListener = null;
		super.dispose();
	}
	
	private class RuleElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}

	@Override
	public void registerFigure(ContextFreeContext contextFreeContext) {
		contextFreeContext.registerRule(ruleElement);
	}
}
