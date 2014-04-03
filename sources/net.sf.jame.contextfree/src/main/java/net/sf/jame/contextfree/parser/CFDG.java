package net.sf.jame.contextfree.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class CFDG {
	public List<ShapeType> shapeTypes = new ArrayList<ShapeType>();
	public Map<Integer, ASTDefine> functions = new HashMap<Integer, ASTDefine>();
	public Stack<ASTRule> rules = new Stack<ASTRule>();
	public Map<CFGParam, Integer> paramDepth = new HashMap<CFGParam, Integer>();
	public Map<CFGParam, ASTExpression> paramExp = new HashMap<CFGParam, ASTExpression>();
	private int parameters;
	private boolean usesColor;
	private boolean usesAlpha;
	private boolean uses16bitColor;
	private boolean usesTime;
	private boolean usesFrameTime;
	
	protected int encodeShapeName(String s) {
		int i = tryEncodeShapeName(s);
		if (i >= 0) return i;
		shapeTypes.add(new ShapeType(s));
		return shapeTypes.size() - 1;
	}
	
	protected int tryEncodeShapeName(String s) {
	    for (int i = 0; i < shapeTypes.size(); i++) {
	        if (s.equals(shapeTypes.get(i).getName())) {
	            return i;
	        }
	    }
	    return -1;
	}

	protected String decodeShapeName(int shape) {
	    if (shape < shapeTypes.size()) {
	        return shapeTypes.get(shape).getName();
	    } else {
	        return "**unnamed shape**";
	    }
	}

	protected ASTDefine findFunction(int index) {
		ASTDefine def = functions.get(index);
		if (def != null) {
			return def;
		}
		return null;
	}

	protected String setShapeParams(int nameIndex, ASTRepContainer p, int argSize, boolean isPath) {
		ShapeType type = shapeTypes.get(nameIndex);
		if (type.isShape()) {
			if (p.getParameters().isEmpty()) {
				return "Shape has already been declared. Parameter declaration must be on the first shape declaration only";
			}
			if (type.getShapeType() == ShapeTypeEnum.PathType && !isPath) {
				return "Shape name already in use by another rule or path";
			}
			if (isPath) {
				return "Path name already in use by another rule or path";
			}
			return null;
		}
		if (type.getShapeType() != ShapeTypeEnum.NewShape) {
			return "Shape name already in use by another rule or path";
		}
		type.getParameters().clear();
		type.getParameters().addAll(p.getParameters());
		type.setIsShape(true);
		type.setArgSize(argSize);
		type.setShapeType(isPath ? ShapeTypeEnum.PathType : ShapeTypeEnum.NewShape);
		return null;
	}
	
	protected boolean addRuleShape(ASTRule rule) {
		rules.push(rule);
		ShapeType type = shapeTypes.get(rule.getNameIndex());
		if (type.getShapeType() == ShapeTypeEnum.NewShape) {
			type.setShapeType(rule.isPath() ? ShapeTypeEnum.PathType : ShapeTypeEnum.RuleType);
		}
		if (!type.getParameters().isEmpty()) {
			rule.getRuleBody().getParameters().clear();
			rule.getRuleBody().getParameters().addAll(type.getParameters());
		}
		type.setHasRules(true);
		return type.isShape();
	}

	protected ShapeTypeEnum getShapeType(int nameIndex) {
		return shapeTypes.get(nameIndex).getShapeType();
	}
	
	protected ShapeTypeEnum getShapeType(String name) {
		for (int i = 0; i < shapeTypes.size(); i++) {
			if (shapeTypes.get(i).getName().equals(name)) {
				return shapeTypes.get(i).getShapeType();
			}
		}
		return ShapeTypeEnum.NewShape;
	}
	
	protected ASTDefine declareFunction(int nameIndex, ASTDefine def) {
		ASTDefine prev = findFunction(nameIndex);
		if (prev != null) {
			return prev;
		}
		functions.put(nameIndex, def);
		return def;
	}
	
	protected List<ASTParameter> getShapeParams(int nameIndex) {
	    if (nameIndex < 0 || nameIndex >= shapeTypes.size() || !shapeTypes.get(nameIndex).isShape()) {
	        return null;
	    }
	    return shapeTypes.get(nameIndex).getParameters();
	}
	
	protected ASTRule findRule(int nameIndex) {
	    for (ASTRule rule: rules) {
	        if (rule.getNameIndex() == nameIndex) {
	            return rule;
	        }
	    }
	    return null;
	}
	
	protected boolean hasParameter(CFGParam p, double[] value, RTI rti) {
		ASTExpression exp = hasParameter(p);
		if (exp == null || exp.getType() != ExpType.NumericType) {
			return false;
		}
		if (!exp.isConstant() && rti != null) {
			error("This expression must be constant");
			return false;
		} else {
			exp.evaluate(value, 1, rti);
		}
		return true;
	}

	protected boolean hasParameter(CFGParam p, Modification[] value, RTI rti) {
		ASTExpression exp = hasParameter(p);
		if (exp == null || exp.getType() != ExpType.ModificationType) {
			return false;
		}
		if (!exp.isConstant() && rti != null) {
			error("This expression must be constant");
			return false;
		} else {
			exp.evaluate(value, 1, rti);
		}
		return true;
	}

	protected boolean hasParameter(CFGParam p, ExpType type) {
		ASTExpression exp = hasParameter(p);
		if (exp == null || exp.getType() != type) {
			return false;
		}
		return true;
	}

	protected ASTExpression hasParameter(CFGParam p) {
		if (paramDepth.get(p).intValue() == -1) {
			return null;
		}
		return paramExp.get(p);
	}

	protected boolean addParameter(String name, ASTExpression exp, int depth) {
		CFGParam p = CFGParam.valueOf(name);
		if (p == null) {
			return false;
		}
		if (depth < paramDepth.get(p)) {
			paramDepth.put(p, depth);
			paramExp.put(p, exp);
		}
		return true;
	}

	protected void setShapeHasNoParam(int nameIndex, ASTExpression args) {
		if (nameIndex < shapeTypes.size() && args != null) {
			shapeTypes.get(nameIndex).setShouldHaveNoParams(true);
		}
	}

	protected void addParameter(Param param) {
		parameters |= param.getOrdinal();
	    usesColor = (parameters & Param.Color.getOrdinal()) != 0;
	    usesTime = (parameters & Param.Time.getOrdinal()) != 0;
	    usesFrameTime = (parameters & Param.FrameTime.getOrdinal()) != 0;
	}

	protected void error(String message) {
		System.err.println(message);
	}
}
