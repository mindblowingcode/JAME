package net.sf.jame.contextfree.renderer;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public final class ComposedContextFreeShape extends DefaultContextFreeShape {
	private List<ContextFreeShape> shapeList = new ArrayList<ContextFreeShape>();
	
	public ComposedContextFreeShape(float z) {
		super(z);
	}
	
	public void addShape(ContextFreeShape shape) {
		shapeList.add(shape);
	}
	
	public void removeShape(ContextFreeShape shape) {
		shapeList.remove(shape);
	}
	
	public void render(Graphics2D g2d, ContextFreeArea area) {
		for (ContextFreeShape shape : shapeList) {
			shape.render(g2d, area);
		}
	}
}
