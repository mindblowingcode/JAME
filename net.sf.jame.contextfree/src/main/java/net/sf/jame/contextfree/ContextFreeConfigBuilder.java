/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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
import net.sf.jame.contextfree.extensions.figure.RuleFigureConfig;
import net.sf.jame.contextfree.extensions.image.ContextFreeImageConfig;
import net.sf.jame.contextfree.extensions.shapeReplacement.SingleShapeReplacementConfig;
import net.sf.jame.contextfree.figure.FigureConfigElement;
import net.sf.jame.contextfree.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.shapeReplacement.extension.ShapeReplacementExtensionConfig;
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
		FigureConfigElement triangleFigureElement = new FigureConfigElement();
		cfdgElement.appendFigureConfigElement(triangleFigureElement);
		ConfigurableExtensionReference<FigureExtensionConfig> triangleReference = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.triangle").createConfigurableExtensionReference();
		triangleFigureElement.setExtensionReference(triangleReference);
		FigureConfigElement squareFigureElement = new FigureConfigElement();
		cfdgElement.appendFigureConfigElement(squareFigureElement);
		ConfigurableExtensionReference<FigureExtensionConfig> squareReference = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.square").createConfigurableExtensionReference();
		squareFigureElement.setExtensionReference(squareReference);
		FigureConfigElement circleFigureElement = new FigureConfigElement();
		cfdgElement.appendFigureConfigElement(circleFigureElement);
		ConfigurableExtensionReference<FigureExtensionConfig> circleReference = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.circle").createConfigurableExtensionReference();
		circleFigureElement.setExtensionReference(circleReference);
		FigureConfigElement ruleFigureElement = new FigureConfigElement();
		cfdgElement.appendFigureConfigElement(ruleFigureElement);
		ConfigurableExtensionReference<FigureExtensionConfig> ruleReference = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.rule").createConfigurableExtensionReference();
		ruleFigureElement.setExtensionReference(ruleReference);
		ShapeReplacementConfigElement replacementElement = new ShapeReplacementConfigElement();
		ConfigurableExtensionReference<ShapeReplacementExtensionConfig> replacementReference = ContextFreeRegistry.getInstance().getShapeReplacementExtension("contextfree.shape.replacement.single").createConfigurableExtensionReference();
		replacementElement.setExtensionReference(replacementReference);
		((RuleFigureConfig) ruleReference.getExtensionConfig()).setName("square");
		((RuleFigureConfig) ruleReference.getExtensionConfig()).appendShapeReplacementConfigElement(replacementElement);
		((SingleShapeReplacementConfig) replacementReference.getExtensionConfig()).setShape("SQUARE");
		return config;
	}
}
