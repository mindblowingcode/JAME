package net.sf.jame.contextfree.renderer.support;

import java.awt.BasicStroke;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class CFPathAttribute implements Cloneable {
	private CFPathCommand command = CFPathCommand.STROKE;
	private CFModification modification = new CFModification();
	private Point2D.Float centroid = new Point2D.Float();
	private int windRule = Path2D.WIND_EVEN_ODD;
	private int lineCap = BasicStroke.CAP_ROUND;
	private int lineJoin = BasicStroke.JOIN_MITER;
	private float lineWidth = 1f;
	private int count = 0;
	private BasicStroke stroke;

	public CFPathAttribute(CFPathCommand command) {
		this.command = command;
	}

	public CFPathAttribute(CFPathCommand command, String ruleParam) {
		this.command = command;
		this.windRule = getRule(ruleParam);
	}

	public CFPathAttribute(CFPathCommand command, String capParam, String joinParam, float lineWidth) {
		this.command = command;
		this.lineCap = getCap(capParam);
		this.lineJoin = getJoin(joinParam);
		this.lineWidth = lineWidth;
	}

	private int getRule(String rule) {
		if ("even-odd".equals(rule)) { 
			return Path2D.WIND_EVEN_ODD;
		} else if ("non-zero".equals(rule)) { 
			return Path2D.WIND_NON_ZERO;
		}
		throw new IllegalArgumentException("Rule not supported " + rule);
	}

	private int getCap(String cap) {
		if ("butt".equals(cap)) {
			return BasicStroke.CAP_BUTT;
		} else if ("round".equals(cap)) {
			return BasicStroke.CAP_ROUND;
		} else if ("square".equals(cap)) {
			return BasicStroke.CAP_SQUARE;
		}
		throw new IllegalArgumentException("Cap not supported " + cap);
	}

	private int getJoin(String join) {
		if ("miter".equals(join)) {
			return BasicStroke.JOIN_MITER;
		} else if ("round".equals(join)) {
			return BasicStroke.JOIN_ROUND;
		} else if ("bevel".equals(join)) {
			return BasicStroke.JOIN_BEVEL;
		}
		throw new IllegalArgumentException("Join not supported " + join);
	}

	public BasicStroke getStroke() {
		if (stroke == null) {
			stroke = new BasicStroke(lineWidth, lineCap, lineJoin);
		}
		return stroke;
	}

	public CFPathCommand getCommand() {
		return command;
	}

	public void setCommand(CFPathCommand command) {
		this.command = command;
	}

	public void setWindingRule(String ruleParam) {
		windRule = getRule(ruleParam);
	}

	public int getWindingRule() {
		return windRule;
	}
	
	public void setLineCap(String capParam) {
		lineCap = getCap(capParam);
		stroke = null;
	}

	public int getLineCap() {
		return lineCap;
	}
	
	public void setLineJoin(String joinParam) {
		lineJoin = getJoin(joinParam);
		stroke = null;
	}

	public int getLineJoin() {
		return lineJoin;
	}
	
	public void setLineWidth(float lineWidth) {
		this.lineWidth = lineWidth;
		stroke = null;
	}
	
	public float getLineWidth() {
		return lineWidth;
	}

	@Override
	public CFPathAttribute clone() {
		CFPathAttribute pa = new CFPathAttribute(command);
		pa.stroke = stroke;
		pa.windRule = windRule;
		pa.lineCap = lineCap;
		pa.lineJoin = lineJoin;
		pa.lineWidth = lineWidth;
		return pa;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public CFModification getModification() {
		return modification;
	}

	public void setModification(CFModification modification) {
		this.modification = modification;
	}

	public double area() {
		return modification.getTransform().getDeterminant();
	}

	public Point2D getCentroid() {
		return centroid;
	}

	@Override
	public String toString() {
		return "CFPathAttribute [command=" + command + ", count=" + count + ", modification=" + modification + ", centroid=" + centroid + ", lineCap=" + lineCap + ", lineJoin=" + lineJoin + ", lineWidth=" + lineWidth + ", windRule=" + windRule + "]";
	}
}
