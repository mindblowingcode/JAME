/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeBounds;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeShape;
import net.sf.jame.contextfree.renderer.ContextFreePath;
import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.contextfree.renderer.SolidPathShape;

/**
 * @author Andrea Medeghini
 */
public class TriangleFigureRuntime<T extends TriangleFigureConfig> extends FigureExtensionRuntime<T> implements ContextFreePath {
	@Override
	public void register(ContextFreeContext contextFreeContext) {
		contextFreeContext.registerPath(this);
	}

	public String getName() {
		return "TRIANGLE";
	}

	public ContextFreeShape createShape(ContextFreeContext context, ContextFreeState state, ContextFreeBounds bounds) {
		float a = (float) (0.5 * Math.tan(Math.PI / 6.0));
		float b = (float) (0.5 * Math.tan(Math.PI / 3.0));
		float[] p = new float[] { -0.5f, -a, +0.5f, -a, 0, b - a };
		float[] q = new float[p.length];
		state.transform(p, q);
		for (int i = 0; i < q.length; i += 2) {
			float x = q[i + 0];
			float y = q[i + 1];
			bounds.addPoint(x, y);
		}
		state.moveRel(-0.5f, -a);
		state.lineRel(+1f, +0f);
		state.lineRel(-0.5f, b);
		state.closePath(false);
		return new SolidPathShape(state, "even-odd");
	}
}
