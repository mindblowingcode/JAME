package net.sf.jame.contextfree.renderer.support;

import java.awt.BasicStroke;
import java.awt.geom.Path2D;

public class CFPathAttributes implements Cloneable {
	private BasicStroke stroke = new BasicStroke(1);
	private int rule = Path2D.WIND_EVEN_ODD;
	private boolean fill;

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

	public void setStroke(String capParam, String joinParam, Float width) {
		stroke = new BasicStroke(width, getCap(capParam), getJoin(joinParam));
	}

	public BasicStroke getStroke() {
		return stroke;
	}
	
	public void setWindingRule(String ruleParam) {
		rule = getRule(ruleParam);
	}

	public int getWindingRule() {
		return rule;
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	@Override
	public CFPathAttributes clone() {
		CFPathAttributes pa = new CFPathAttributes();
		pa.stroke = stroke;
		pa.rule = rule;
		pa.fill = fill;
		return pa;
	}
}
