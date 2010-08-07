package net.sf.jame.contextfree.renderer;

public interface ContextFreeBounds {
	public double getMinX();

	public double getMinY();

	public double getMaxX();

	public double getMaxY();
	
	public void addPoint(double x, double y);

	public int getWidth();

	public int getHeight();

	public boolean isValid();
}
