/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreePath;

/**
 * @author Andrea Medeghini
 */
public class TriangleFigureRuntime<T extends TriangleFigureConfig> extends FigureExtensionRuntime<T> implements ContextFreePath {
	private Shape shape = createShape();
	
	private Shape createShape() {
		GeneralPath path = new GeneralPath();
		path.append(new Line2D.Float(norm(0), norm(0), norm(1), norm(0)), false);
		path.append(new Line2D.Float(norm(1), norm(0), norm(0), norm(1)), false);
		path.append(new Line2D.Float(norm(0), norm(1), norm(0), norm(0)), true);
		return path;
	}
	
	private float norm(float i) {
		return i;
	}

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void draw(ContextFreeContext contextFreeContext) {
		contextFreeContext.drawShape(shape);
	}

	@Override
	public void prepare(ContextFreeContext contextFreeContext) {
	}

	@Override
	public void registerFigure(ContextFreeContext contextFreeContext) {
		contextFreeContext.registerPath(this);
	}

	public String getName() {
		return "triangle";
	}
}
