package net.sf.jame.contextfree.renderer;

public class ShapeBounds implements ContextFreeBounds {
	private double minX = Double.MAX_VALUE;
	private double minY = Double.MAX_VALUE;
	private double maxX = Double.MIN_VALUE;
	private double maxY = Double.MIN_VALUE;
	private int width;
	private int height;

	public ShapeBounds(int width, int height) {
		this.width = width;
		this.height = height;
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
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isValid() {
		return (maxX - minX) > 0 || (maxY - minY) > 0;
	}

	public double getSizeX() {
		return maxX - minX;
	}

	public double getSizeY() {
		return maxY - minY;
	}
}
