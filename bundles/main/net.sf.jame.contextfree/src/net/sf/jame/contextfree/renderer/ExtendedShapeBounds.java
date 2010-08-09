package net.sf.jame.contextfree.renderer;

public class ExtendedShapeBounds implements ContextFreeBounds {
	private double minX = Double.MAX_VALUE;
	private double minY = Double.MAX_VALUE;
	private double maxX = Double.MIN_VALUE;
	private double maxY = Double.MIN_VALUE;
	private ContextFreeBounds bounds;

	public ExtendedShapeBounds(ContextFreeBounds bounds) {
		this.bounds = bounds;
	}

	public double getMinX() {
		return minX;
	}

	public double getMinY() {
		return minY;
	}

	public double getMaxX() {
		return maxX;
	}

	public double getMaxY() {
		return maxY;
	}
	
	public void addPoint(double x, double y) {
		minX = Math.min(x, minX); 
		minY = Math.min(y, minY); 
		maxX = Math.max(x, maxX); 
		maxY = Math.max(y, maxY); 
		bounds.addPoint(x, y);
	}

	public int getWidth() {
		return bounds.getWidth();
	}

	public int getHeight() {
		return bounds.getHeight();
	}

	public boolean isValid() {
		return (maxX - minX) > 0 && (maxY - minY) > 0;
	}
}
