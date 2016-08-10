/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
package net.sf.jame.mandelbrot.extensions.incolouringFormula;

import net.sf.jame.mandelbrot.colorRenderer.ColorRendererRuntimeElement;
import net.sf.jame.mandelbrot.renderer.RenderedPoint;
import net.sf.jame.mandelbrot.renderingFormula.extension.RenderingFormulaExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class UniversalRuntime extends AbstractIncolouringPaletteRuntime<UniversalConfig> {
	private ColorRendererRuntimeElement rendererElement;

	/**
	 * @see net.sf.jame.mandelbrot.extensions.incolouringFormula.AbstractIncolouringFormulaRuntime#isSolidGuessAllowed()
	 */
	@Override
	public boolean isSolidGuessAllowed() {
		return true;
	}

	/**
	 * @see net.sf.jame.mandelbrot.extensions.outcolouringFormula.AbstractOutcolouringPaletteRuntime#prepareForRendering(int)
	 */
	@Override
	public void prepareForRendering(final RenderingFormulaExtensionRuntime<?> formulaRuntime, final int maxColors) {
		super.prepareForRendering(formulaRuntime, maxColors);
		if (rendererElement.getRendererRuntime() != null) {
			rendererElement.getRendererRuntime().prepareForRendering();
		}
	}

	/**
	 * @see net.sf.jame.mandelbrot.extensions.incolouringFormula.AbstractIncolouringPaletteRuntime#renderIndex(net.sf.jame.mandelbrot.renderer.RenderedPoint)
	 */
	@Override
	protected int renderIndex(final RenderedPoint cp) {
		if (rendererElement.getRendererRuntime() != null) {
			return (int) Math.rint(rendererElement.getRendererRuntime().renderColor(cp) * (colorTable.length - 1));
		}
		else {
			return 0;
		}
	}

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean rendererChanged = (rendererElement != null) && rendererElement.isChanged();
		return super.isChanged() || rendererChanged;
	}

	/**
	 * @see net.sf.jame.mandelbrot.extensions.incolouringFormula.AbstractIncolouringPaletteRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		super.configReloaded();
		rendererElement = new ColorRendererRuntimeElement(getConfig().getColorRendererElement());
	}
}
