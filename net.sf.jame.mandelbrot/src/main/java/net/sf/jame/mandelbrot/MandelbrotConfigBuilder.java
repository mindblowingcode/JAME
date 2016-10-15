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
package net.sf.jame.mandelbrot;

import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.mandelbrot.extensions.image.MandelbrotImageConfig;
import net.sf.jame.mandelbrot.fractal.MandelbrotFractalConfigElement;
import net.sf.jame.mandelbrot.incolouringFormula.IncolouringFormulaConfigElement;
import net.sf.jame.mandelbrot.orbitTrap.OrbitTrapConfigElement;
import net.sf.jame.mandelbrot.outcolouringFormula.OutcolouringFormulaConfigElement;
import net.sf.jame.mandelbrot.processingFormula.ProcessingFormulaConfigElement;
import net.sf.jame.mandelbrot.renderingFormula.RenderingFormulaConfigElement;
import net.sf.jame.mandelbrot.transformingFormula.TransformingFormulaConfigElement;

/**
 * @author Andrea Medeghini
 */
public class MandelbrotConfigBuilder {
	public static final String DEFAULT_RENDERING_FORMULA_EXTENSION_ID = "twister.mandelbrot.fractal.rendering.formula.z2";
	public static final String DEFAULT_TRANSFORMING_FORMULA_EXTENSION_ID = "twister.mandelbrot.fractal.transforming.formula.z";
	public static final String DEFAULT_INCOLOURING_FORMULA_EXTENSION_ID = "twister.mandelbrot.fractal.incolouring.formula.color";
	public static final String DEFAULT_OUTCOLOURING_FORMULA_EXTENSION_ID = "twister.mandelbrot.fractal.outcolouring.formula.continuousPotential";

	/**
	 * Constructs a new builder.
	 * 
	 * @param config the config.
	 */
	public MandelbrotConfigBuilder(final MandelbrotImageConfig config) {
	}

	/**
	 * @param context
	 * @return
	 * @throws ExtensionNotFoundException
	 * @throws ExtensionException
	 */
	public MandelbrotConfig createDefaultConfig() throws ExtensionNotFoundException, ExtensionException {
		final MandelbrotConfig config = new MandelbrotConfig();
		final MandelbrotFractalConfigElement fractalElement = new MandelbrotFractalConfigElement();
		final RenderingFormulaConfigElement renderingFormulaElement = new RenderingFormulaConfigElement();
		final TransformingFormulaConfigElement transformingFormulaElement = new TransformingFormulaConfigElement();
		final ProcessingFormulaConfigElement processingFormulaElement = new ProcessingFormulaConfigElement();
		final IncolouringFormulaConfigElement incolouringFormulaElement = new IncolouringFormulaConfigElement();
		final OutcolouringFormulaConfigElement outcolouringFormulaElement = new OutcolouringFormulaConfigElement();
		final OrbitTrapConfigElement orbitTrapElement = new OrbitTrapConfigElement();
		config.setMandelbrotFractal(fractalElement);
		fractalElement.setRenderingFormulaConfigElement(renderingFormulaElement);
		renderingFormulaElement.setReference(MandelbrotRegistry.getInstance().getRenderingFormulaExtension(MandelbrotConfigBuilder.DEFAULT_RENDERING_FORMULA_EXTENSION_ID).createConfigurableExtensionReference());
		fractalElement.setTransformingFormulaConfigElement(transformingFormulaElement);
		transformingFormulaElement.setReference(MandelbrotRegistry.getInstance().getTransformingFormulaExtension(MandelbrotConfigBuilder.DEFAULT_TRANSFORMING_FORMULA_EXTENSION_ID).createConfigurableExtensionReference());
		fractalElement.setProcessingFormulaConfigElement(processingFormulaElement);
		fractalElement.setOrbitTrapConfigElement(orbitTrapElement);
		fractalElement.appendIncolouringFormulaConfigElement(incolouringFormulaElement);
		incolouringFormulaElement.setReference(MandelbrotRegistry.getInstance().getIncolouringFormulaExtension(MandelbrotConfigBuilder.DEFAULT_INCOLOURING_FORMULA_EXTENSION_ID).createConfigurableExtensionReference());
		fractalElement.appendOutcolouringFormulaConfigElement(outcolouringFormulaElement);
		outcolouringFormulaElement.setReference(MandelbrotRegistry.getInstance().getOutcolouringFormulaExtension(MandelbrotConfigBuilder.DEFAULT_OUTCOLOURING_FORMULA_EXTENSION_ID).createConfigurableExtensionReference());
		return config;
	}
}
