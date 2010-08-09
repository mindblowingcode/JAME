package net.sf.jame.contextfree.renderer;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public final class ComposedShape extends ContextFreeShape {
	private List<ContextFreeShape> shapeList = new ArrayList<ContextFreeShape>();
	private ContextFreeState state;
	private AlphaComposite a; 
	private Color c;

	public ComposedShape(ContextFreeState state) {
		super(state.getZ());
		float[] hsba = state.getHSBA();
		a = AlphaComposite.Src.derive(hsba[3]);
		c = Color.getHSBColor(hsba[0], hsba[1], hsba[2]);
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
		AffineTransform t = new AffineTransform();
		float sx = area.getScaleX();
		float sy = area.getScaleY();
		float tx = area.getX();
		float ty = area.getY();
		t.translate(tx, ty);
		t.scale(sx, sy);
		state.flush(g2d, t, a, c);
	}
}
