package net.sf.jame.contextfree.renderer.support;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class TiledShapeRenderer implements CFShapeRenderer {
	private List<Point2D.Double> tiles = new ArrayList<Point2D.Double>();
	private Graphics2D g2d;
	private Rectangle2D.Double canvas;
	private AffineTransform scale;
	private AffineTransform tess;
	private Point2D.Double ptSrc = new Point2D.Double(0, 0);
	private Point2D.Double ptDst = new Point2D.Double(0, 0);

	public TiledShapeRenderer(Graphics2D g2d, CFContext context) {
		this.g2d = g2d;
		tess = AffineTransform.getScaleInstance(context.getTileTransform().getScaleX(), context.getTileTransform().getScaleY());
		scale = AffineTransform.getScaleInstance(1 / context.getTileTransform().getScaleX(), 1 / context.getTileTransform().getScaleY());
		canvas = new Rectangle2D.Double(-0.5 * context.getTileTransform().getScaleX(), -0.5 * context.getTileTransform().getScaleY(), context.getTileTransform().getScaleX(), context.getTileTransform().getScaleY());
	}

	private void transform(Rectangle2D bounds, double px, double py) {
		tiles.clear();
        tiles.add(new Point2D.Double(px, py));
//        System.out.println("[] " + bounds);
        if (px + bounds.getMinX() > canvas.getMinX() && py + bounds.getMinY() > canvas.getMinY() && px + bounds.getMaxX() < canvas.getMaxX() && py + bounds.getMaxY() < canvas.getMaxY()) {
        	return;
        }
	    for (int ring = 1; ; ring++) {
	        boolean hit = false;
	        for (int y = -ring; y <= ring; y++) {
	            for (int x = -ring; x <= ring; x++) {
	                if (Math.abs(x) < ring && Math.abs(y) < ring) continue;
	                ptSrc.setLocation(x, y);
					tess.transform(ptSrc, ptDst);
	                Rectangle2D.Double tile = new Rectangle2D.Double(ptDst.getX() + bounds.getMinX() + px, ptDst.getY() + bounds.getMinY() + py, bounds.getWidth(), bounds.getHeight());
	                if (tile.intersects(canvas)) {
//	                    System.out.println("  " + canvas + " <> " + tile);
	                    Point2D.Double point = new Point2D.Double(ptDst.getX() + px, ptDst.getY() + py);
						tiles.add(point);
						hit = true;
	                }
	            }
	        }
	        if (!hit) break;
	    }
	}

	public void render(CFPath path, CFPathAttribute attribute) {
		AffineTransform tmpTransform = g2d.getTransform();
		Composite tmpComposite = g2d.getComposite();
		Color tmpColor = g2d.getColor();
		g2d.transform(scale);
		AffineTransform t = attribute.getModification().getTransform();
		double tx = t.getTranslateX() * scale.getScaleX();
		double ty = t.getTranslateY() * scale.getScaleY();
		double px = tx - Math.floor(tx); 
		double py = ty - Math.floor(ty);
		if (px < 0) px += 1;
		if (py < 0) py += 1;
		px = (px - tx) / scale.getScaleX();
		py = (py - ty) / scale.getScaleY();
		transform(path.getBounds(t, 1), px, py);
		g2d.setClip(canvas);
		for (Point2D.Double point : tiles) {
//            System.out.println(point);
			path.render(g2d, attribute, point);
		}
		g2d.setTransform(tmpTransform);
		g2d.setComposite(tmpComposite);
		g2d.setColor(tmpColor);
	}
}
