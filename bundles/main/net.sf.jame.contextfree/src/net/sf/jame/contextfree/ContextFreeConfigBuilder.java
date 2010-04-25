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
import net.sf.jame.contextfree.cfdg.figure.FigureConfigElement;
import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionConfig;
import net.sf.jame.contextfree.extensions.figure.RuleFigureConfig;
import net.sf.jame.contextfree.extensions.image.ContextFreeImageConfig;
import net.sf.jame.contextfree.extensions.shapeReplacement.SingleReplacementConfig;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.util.Color32bit;

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
		cfdgElement.setBackground(new Color32bit(0xFFFFFFFF));
		cfdgElement.setStartshape("square");
		config.setCFDG(cfdgElement);
		FigureConfigElement figureElement = new FigureConfigElement();
		cfdgElement.appendFigureConfigElement(figureElement);
		ConfigurableExtensionReference<FigureExtensionConfig> ruleReference = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.rule").createConfigurableExtensionReference();
		figureElement.setExtensionReference(ruleReference);
		ShapeReplacementConfigElement replacementElement = new ShapeReplacementConfigElement();
		ConfigurableExtensionReference<ShapeReplacementExtensionConfig> replacementReference = ContextFreeRegistry.getInstance().getShapeReplacementExtension("contextfree.shape.replacement.single").createConfigurableExtensionReference();
		replacementElement.setExtensionReference(replacementReference);
		((RuleFigureConfig) ruleReference.getExtensionConfig()).setName("square");
		((RuleFigureConfig) ruleReference.getExtensionConfig()).appendShapeReplacementConfigElement(replacementElement);
		ShapeAdjustmentConfigElement shapeAdjustmentElement = new ShapeAdjustmentConfigElement();
		((SingleReplacementConfig) replacementReference.getExtensionConfig()).setShape("triangle");
		((SingleReplacementConfig) replacementReference.getExtensionConfig()).appendShapeAdjustmentConfigElement(shapeAdjustmentElement);
		shapeAdjustmentElement.setExtensionReference(ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.hue").createConfigurableExtensionReference());
		return config;
	}
}
