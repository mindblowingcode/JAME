package net.sf.jame.contextfree.renderer.support;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class SimpleShapeRenderer implements CFShapeRenderer {
	private Graphics2D g2d;
	
	public SimpleShapeRenderer(Graphics2D g2d, CFContext context, int width, int height) {
		this.g2d = g2d;
	}

	public void render(ExtendedGeneralPath path, CFPathAttribute attribute) {
		if (attribute.getCommand().equals(CFPathCommand.FILL)) {
			fill(path, attribute.getModification(), attribute.getWindingRule());
		} else {
			draw(path, attribute.getModification(), attribute.getStroke());
		}
	}

	private void fill(ExtendedGeneralPath path, CFModification modification, int rule) {
		CFColor c = modification.getColor();
		Color color = Color.getHSBColor(c.getHue() / 360, c.getSaturation(), c.getBrightness());
		Composite composite = AlphaComposite.Src.derive(c.getAlpha());
		AffineTransform tmpTransform = g2d.getTransform();
		Composite tmpComposite = g2d.getComposite();
		Color tmpColor = g2d.getColor();
		g2d.setComposite(composite);
		g2d.setColor(color);
		g2d.transform(modification.getTransform());
		path.setWindingRule(rule);
		g2d.fill(path);
		g2d.setTransform(tmpTransform);
		g2d.setComposite(tmpComposite);
		g2d.setColor(tmpColor);
	}

	private void draw(ExtendedGeneralPath path, CFModification modification, BasicStroke stroke) {
		CFColor c = modification.getColor();
		Color color = Color.getHSBColor(c.getHue() / 360, c.getSaturation(), c.getBrightness());
		Composite composite = AlphaComposite.Src.derive(c.getAlpha());
		AffineTransform tmpTransform = g2d.getTransform();
		Composite tmpComposite = g2d.getComposite();
		Stroke tmpStroke = g2d.getStroke();
		Color tmpColor = g2d.getColor();
		g2d.setComposite(composite);
		g2d.setStroke(stroke);
		g2d.setColor(color);
		g2d.transform(modification.getTransform());
		g2d.draw(path);
		g2d.setTransform(tmpTransform);
		g2d.setComposite(tmpComposite);
		g2d.setStroke(tmpStroke);
		g2d.setColor(tmpColor);
	}

	public void render(CFPath path, CFPathAttribute attribute) {
		AffineTransform tmpTransform = g2d.getTransform();
		Composite tmpComposite = g2d.getComposite();
		Color tmpColor = g2d.getColor();
		path.render(g2d, attribute, null);
		g2d.setTransform(tmpTransform);
		g2d.setComposite(tmpComposite);
		g2d.setColor(tmpColor);
	}
}
