/*
 * JAME 6.1 
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2010 Andrea Medeghini
 * http://andreamedeghini.users.sourceforge.net
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
package net.sf.jame.contextfree;

import net.sf.jame.contextfree.cfdg.CFDGConfigElement;
import net.sf.jame.contextfree.cfdg.rule.RuleConfigElement;
import net.sf.jame.contextfree.cfdg.shape.ShapeConfigElement;
import net.sf.jame.contextfree.cfdg.shape.adjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shape.replacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.extensions.image.ContextFreeImageConfig;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeConfigBuilder {
	/**
	 * Constructs a new builder.
	 * 
	 * @param config
	 *        the config.
	 */
	public ContextFreeConfigBuilder(final ContextFreeImageConfig config) {
	}

	/**
	 * @param context
	 * @return
	 * @throws ExtensionNotFoundException
	 * @throws ExtensionException
	 */
	public ContextFreeConfig createDefaultConfig() throws ExtensionNotFoundException, ExtensionException {
		final ContextFreeConfig config = new ContextFreeConfig();
		CFDGConfigElement cfdgElement = new CFDGConfigElement();
		cfdgElement.setStartshape("square");
		config.setCFDG(cfdgElement);
		RuleConfigElement ruleElement = new RuleConfigElement();
		cfdgElement.appendFigureConfigElement(ruleElement);
		ShapeConfigElement shapeElement = new ShapeConfigElement();
		ruleElement.setShapeConfigElement(shapeElement);
		ruleElement.setName("square");
		ShapeReplacementConfigElement shapeReplacementElement = new ShapeReplacementConfigElement();
		shapeElement.appendReplacementConfigElement(shapeReplacementElement);
		ShapeAdjustmentConfigElement shapeAdjustmentElement = new ShapeAdjustmentConfigElement();
		shapeReplacementElement.setName("square");
		shapeReplacementElement.appendShapeAdjustmentConfigElement(shapeAdjustmentElement);
		shapeAdjustmentElement.setExtensionReference(ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.hue").createConfigurableExtensionReference());
		return config;
	}
}
