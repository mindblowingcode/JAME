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
public class CircleFigureRuntime extends FigureExtensionRuntime<CircleFigureConfig> {
	public String getName() {
		return "CIRCLE";
	}

	public void process(CFBuilder builder) {
		int shapeType = builder.encodeShapeName(getName());
		CFRule rule = new CFRule(shapeType, 1);
		CFPath path = new CFPath();
		path.circle();
		rule.setPath(path);
		builder.addRule(rule);
	}
}
