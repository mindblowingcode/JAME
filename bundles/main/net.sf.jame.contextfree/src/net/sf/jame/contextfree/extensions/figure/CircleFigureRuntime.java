/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeBounds;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeNode;
import net.sf.jame.contextfree.renderer.ContextFreePath;
import net.sf.jame.contextfree.renderer.ContextFreeState;
import net.sf.jame.contextfree.renderer.FigureContextFreeNode;

/**
 * @author Andrea Medeghini
 */
public class CircleFigureRuntime<T extends CircleFigureConfig> extends FigureExtensionRuntime<T> implements ContextFreePath {
	@Override
	public void register(ContextFreeContext contextFreeContext) {
		contextFreeContext.registerPath(this);
	}

	public String getName() {
		return "CIRCLE";
	}

	public ContextFreeNode buildNode(ContextFreeContext context, ContextFreeState state, ContextFreeBounds bounds) {
		int n = 50;
		float[] p = new float[n * 2];
		float[] q = new float[p.length];
		double e = 0;
		double d = 2 * Math.PI / n;
		for (int t = 0; t < n * 2; t += 2) {
			p[t + 0] = (float) (0.5 * Math.cos(e));
			p[t + 1] = (float) (0.5 * Math.sin(e));
			e += d;
		}
		state.transform(p, q);
		for (int i = 0; i < q.length; i += 2) {
			float x = q[i + 0];
			float y = q[i + 1];
			bounds.addPoint(x, y);
		}
		return new FigureContextFreeNode(state, q);
	}
}
