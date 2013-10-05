/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.renderer.support.CFBuilder;
import net.sf.jame.contextfree.renderer.support.CFPath;
import net.sf.jame.contextfree.renderer.support.CFRule;

/**
 * @author Andrea Medeghini
 */
public class SquareFigureRuntime extends FigureExtensionRuntime<SquareFigureConfig> {
	public String getName() {
		return "SQUARE";
	}

	public void process(CFBuilder builder) {
		int shapeType = builder.encodeShapeName(getName());
		CFRule rule = new CFRule(shapeType, 1);
		CFPath path = new CFPath();
		path.moveRel(-0.5f, -0.5f);
		path.lineRel(+1f, +0f);
		path.lineRel(+0f, +1f);
		path.lineRel(-1f, +0f);
		path.closePath(false);
		rule.setPath(path);
		builder.addRule(rule);
	}
}
