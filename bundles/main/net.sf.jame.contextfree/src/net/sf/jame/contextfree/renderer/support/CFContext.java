package net.sf.jame.contextfree.renderer.support;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CFContext {
	private ArrayList<ShapeType> shapeTypes = new ArrayList<ShapeType>();
	private ArrayList<CFRule> rules = new ArrayList<CFRule>();
	private CFColor background = new CFColor(0, 0, 1, 1);
	private AffineTransform tileTransform;
	private float tileOffsetX;
	private float tileOffsetY;
	private float tileX;
	private float tileY;
	private CFShape initialShape;
	private boolean sized;
	private boolean tiled;
	private int loopStartShapeType;
	private int loopEndShapeType;

	public CFContext() {
		loopStartShapeType = encodeShapeName("~~LOOPSTART~~");
		loopEndShapeType = encodeShapeName("~~LOOPEND~~");
	}
	
	public CFColor getBackground() {
		return background;
	}

	public void setBackground(CFColor background) {
		if (this.background == null) {
			this.background = background;
		}
	}

	public CFShape getInitialShape() {
		initialShape.getModification().getTransform().setToTranslation(tileOffsetX, tileOffsetY);
		return initialShape;
	}

	public void setInitialShape(CFShape initialShape) {
		if (this.initialShape == null) {
			this.initialShape = initialShape;
		}
	}
	
	public void addRule(CFRule rule) {
		if (rule.getInitialShapeType() < shapeTypes.size()) {
			rules.add(rule);
			int type = rule.hasPath() ? ShapeType.TYPE_PATH : ShapeType.TYPE_RULE;
			shapeTypes.get(rule.getInitialShapeType()).setHasRules(true);
			shapeTypes.get(rule.getInitialShapeType()).setType(type);
		}
	}
	
	public CFRule findRule(int shapeType, double random) {
		if (random >= 0 && random <= 1) {
			CFRuleSpecifier rs = new CFRuleSpecifier(shapeType, random);
			CFRule rule = lowerBound(rules, 0, rules.size() - 1, rs);
			return rule;
		} else {
			return null;
		}
	}
	
	private CFRule lowerBound(List<CFRule> list, int begin, int end, CFRuleSpecifier element) {
		if (begin == end) {
			CFRule rule = rules.get(begin);
			if (element.getInitialShapeType() == rule.getInitialShapeType()) {
				return rule;
			} else {
				return null;
			}
		}
		int mid = (begin + end) / 2;
		CFRule rule = rules.get(mid);
		if (rule.compareTo(element) > 0) {
			return lowerBound(rules, begin, mid, element);
		} else {
			return lowerBound(rules, mid + 1, end, element);
		}
	}
	
	public void reloadRules() {
		double[] unitweightsums = new double[shapeTypes.size()];
		double[] weightsums = new double[shapeTypes.size()];
		int[] rulescount = new int[shapeTypes.size()];
		for (int i = 0; i < rules.size(); i++) {
			CFRule rule = rules.get(i);
			weightsums[rule.getInitialShapeType()] += rule.getWeight();
			rulescount[rule.getInitialShapeType()] += 1;
		}
		for (int i = 0; i < rules.size(); i++) {
			CFRule rule = rules.get(i);
			double weight = rule.getWeight() / weightsums[rule.getInitialShapeType()];
			unitweightsums[rule.getInitialShapeType()] += weight;
			rulescount[rule.getInitialShapeType()] -= 1;
			if (rulescount[rule.getInitialShapeType()] != 0) {
				rule.setWeight(unitweightsums[rule.getInitialShapeType()]);
			} else {
				rule.setWeight(1);
			}
		}
		Collections.sort(rules);
	}
	
	public int getRuleCount() {
		return rules.size();
	}

	public boolean isSized() {
		return sized;
	}

	public boolean isTiled() {
		return tiled;
	}
	
	public void setSized(AffineTransform tileTransform) {
		this.tileTransform = tileTransform;
		tileOffsetX = (float) tileTransform.getTranslateX();
		tileOffsetY = (float) tileTransform.getTranslateY();
		tileTransform.translate(-tileOffsetX, -tileOffsetY);
		tileX = (float) tileTransform.getScaleX();
		tileY = (float) tileTransform.getScaleY();
		sized = true;
	}
	
	public void setTiled(AffineTransform tileTransform, float tileX, float tileY) {
		if (tiled) return; 
		this.tileTransform = tileTransform;
		tileOffsetX = (float) tileTransform.getTranslateX();
		tileOffsetY = (float) tileTransform.getTranslateY();
		tileTransform.translate(-tileOffsetX, -tileOffsetY);
		this.tileX = tileX;
		this.tileY = tileY;
		tiled = true;
	}

	public AffineTransform getTileTransform() {
		return tileTransform;
	}

	public float getTileOffsetX() {
		return tileOffsetX;
	}

	public float getTileOffsetY() {
		return tileOffsetY;
	}

	public float getTileX() {
		return tileX;
	}

	public float getTileY() {
		return tileY;
	}
	
	public String decodeShapeName(int shapeType) {
		if (shapeType < shapeTypes.size()) {
			return shapeTypes.get(shapeType).getName();
		}
		return "Unnamed shape";
	}
	
	public int encodeShapeName(String name) {
		for (int i = 0; i < shapeTypes.size(); i++) {
			if (name.equals(shapeTypes.get(i).getName())) {
				return i;
			}
		}
		shapeTypes.add(new ShapeType(name));
		return shapeTypes.size() - 1;
	}
	
	public int getShapeType(String name) {
		for (int i = 0; i < shapeTypes.size(); i++) {
			if (name.equals(shapeTypes.get(i).getName())) {
				return shapeTypes.get(i).getType();
			}
		}
		return ShapeType.TYPE_NEW;
	}
	
	public int getShapeType(int shapeType) {
		if (shapeType < shapeTypes.size()) {
			return shapeTypes.get(shapeType).getType();
		}
		return ShapeType.TYPE_NEW;
	}
	
	public boolean shapeHasRule(int shapeType) {
		if (shapeType < shapeTypes.size()) {
			return shapeTypes.get(shapeType).hasRules();
		}
		return false;
	}
	
	public ShapeType shapeType(int shapeType) {
		if (shapeType < shapeTypes.size()) {
			return shapeTypes.get(shapeType);
		}
		return null;
	}

	public int getLoopStartShapeType() {
		return loopStartShapeType;
	}

	public int getLoopEndShapeType() {
		return loopEndShapeType;
	}
}
