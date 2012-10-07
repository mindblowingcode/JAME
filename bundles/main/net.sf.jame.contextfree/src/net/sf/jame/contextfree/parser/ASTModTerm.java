package net.sf.jame.contextfree.parser;

import java.awt.geom.AffineTransform;
import java.util.List;


class ASTModTerm extends ASTExpression {
    	private String entropy;
    	private ModTypeEnum modType;
    	private ASTExpression arguments;
    	
    	public ASTModTerm(ModTypeEnum modType, ASTExpression arguments) {
    		super(true, ExpType.ModificationType);
    		
    		this.entropy = null;
    		this.modType = modType;
    		this.arguments = arguments;
    		
            if (arguments.type == ExpType.RuleType)
            	throw new RuntimeException("Illegal expression in shape adjustment");
            
            if (arguments.type == ExpType.ModificationType) {
                if (modType != ModTypeEnum.transform)
                	throw new RuntimeException("Cannot accept a transform expression here");

                modType = ModTypeEnum.modification;
            }
    	}
    	
    	public ASTModTerm(ModTypeEnum modType, ASTExpression arguments, String entropy) {
    		super(true, ExpType.ModificationType);
    		
    		this.entropy = entropy;
    		this.modType = modType;
    		this.arguments = arguments;
    		
            if (arguments.type == ExpType.RuleType)
            	throw new RuntimeException("Illegal expression in shape adjustment");
            
            if (arguments.type == ExpType.ModificationType) {
                if (modType != ModTypeEnum.transform)
                	throw new RuntimeException("Cannot accept a transform expression here");

                modType = ModTypeEnum.modification;
            }
    	}

    	@Override
		public int flatten(List<ASTExpression> dest) { 
            // This code merges adjacent x and y adjustments into a single xy adjustment
            ASTModTerm last = null;
            if (dest.size() > 0)
                last = (ASTModTerm) dest.get(0);
            if (last == null) {
                dest.add(this);
                return 1;
            }
            
            if (modType == ModTypeEnum.x && last.modType == ModTypeEnum.y && arguments.evaluate(null, 0, 0, null) == 1) {
                last.modType = ModTypeEnum.x;
                last.arguments = new ASTCons(arguments, last.arguments);
                arguments = null;
                last.entropy += entropy;
                return 0;
            } else if (modType == ModTypeEnum.y && last.modType == ModTypeEnum.x && last.arguments.evaluate(null, 0, 0, null) == 1) {
                last.arguments = new ASTCons(last.arguments, arguments);
                arguments = null;
                last.entropy += entropy;
                return 0;
            }
            
            dest.add(this);
            return 1;
    	}
    	
    	@Override
		public void entropy(StringBuilder e) {
            if (arguments != null) arguments.entropy(e);
            e.append(modType.entropy);
    	}
    	
    	@Override
		public int evaluate(double[] result, int offset, int length, RTI rti) { 
    		throw new RuntimeException("Improper evaluation of an adjustment expression");
    	} 

		@Override
		public ASTExpression simplify() {
            if (arguments != null) {
            	arguments = arguments.simplify();
            }
            return this;
		}

    	@Override
		public void evaluate(Modification modification, String s, double[] width, boolean justCheck, int[] seedIndex, RTI rti) { 
            double[] modArgs = new double[6];
            int argcount = 0;
            
            if (arguments != null && arguments.type == ExpType.NumericType) {
                if (justCheck)
                    argcount = arguments.evaluate(null, 0, 0, null);
                else 
                    argcount = arguments.evaluate(modArgs, 0, 6, null);
            }
            
            int minCount = 1;
            int maxCount = 1;
            double[] arg = new double[6];
            for (int i = 0; i < argcount; ++i)
                arg[i] = Math.max(-1.0, Math.min(1.0, modArgs[i]));
            
            switch (modType) {
                case x: {
                    maxCount = 2;
                    if (justCheck) break;
                    if (argcount == 1) 
                        modArgs[1] = 0.0;
                    AffineTransform tr = AffineTransform.getTranslateInstance(modArgs[0], modArgs[1]);
//                    modification.transform.preConcatenate(tr);
                    break;
                }
                case y: {
                    if (justCheck) break;
                    AffineTransform tr = AffineTransform.getTranslateInstance(0.0, modArgs[0]);
//                    modification.transform.preConcatenate(tr);
                    break;
                }
                case z: {
                    if (justCheck) break;
                    //modification.z.premultiply(modArgs[0]);
                    break;
                }
                case time: {
                    minCount = maxCount = 2;
                    if (justCheck) break;
//                    modification.time.premultiply(modArgs[0], modArgs[1]);
                    break;
                }
                case timescale: {
                    if (justCheck) break;
//                  modification.time.premultiply(modArgs[0]);
                    break;
                }
                case transform: {
                    maxCount = 6;
                    if (argcount != 1 && argcount != 2 && argcount != 4 && argcount != 6)
                    	throw new RuntimeException("transform adjustment takes 1, 2, 4, or 6 parameters");
                    if (justCheck) break;
                    switch (argcount) {
                        case 2:
                        case 1: {
                            if (argcount == 1) 
                                modArgs[1] = 0.0;
                            AffineTransform tr = AffineTransform.getTranslateInstance(modArgs[0], modArgs[1]);
//                            modification.transform.preConcatenate(tr);
                            break;
                        }
                        case 4: {
                            AffineTransform sq = new AffineTransform();
                            double dx = modArgs[2] - modArgs[0];
                            double dy = modArgs[3] - modArgs[1];
                            sq.scale(Math.sqrt(dx * dx + dy * dy), Math.sqrt(dx * dx + dy * dy));
                            sq.rotate(Math.atan2(dy, dx));
                            sq.translate(modArgs[0], modArgs[1]);
//                            modification.transform.preConcatenate(sq);
                            break;
                        }
                        case 6: {
                            AffineTransform par = new AffineTransform();
                            //par.rect_to_parl(0.0, 0.0, 1.0, 1.0, modArgs);
//                            modification.transform.preConcatenate(par);
                            break;
                        }
                        default:
                            break;
                    }
                    break;
                }
                case size: {
                    maxCount = 2;
                    if (justCheck) break;
                    if (argcount == 1) 
                        modArgs[1] = modArgs[0];
                    AffineTransform sc = AffineTransform.getScaleInstance(modArgs[0], modArgs[1]);
//                    modification.transform.preConcatenate(sc);
                    break;
                }
                case zsize: {
                    if (justCheck) break;
//                    modification.z.premultiply(modArgs[0]);
                    break;
                }
                case rot: {
                    if (justCheck) break;
                    AffineTransform rot = AffineTransform.getRotateInstance(modArgs[0] * Math.PI / 180.0);
//                    modification.transform.preConcatenate(rot);
                    break;
                }
                case skew: {
                    minCount = maxCount = 2;
                    if (justCheck) break;
                    AffineTransform sk = AffineTransform.getShearInstance(modArgs[0] * Math.PI / 180.0, modArgs[1] * Math.PI / 180.0);
//                    modification.transform.preConcatenate(sk);
                    break;
                }
                case flip: {
                    if (justCheck) break;
                    AffineTransform ref = AffineTransform.getRotateInstance(modArgs[0] * Math.PI / 180.0);
                    ref.scale(-1, -1);
//                    modification.transform.preConcatenate(ref);
                    break;
                }
                case Entropy: {
                    minCount = maxCount = 0;
//                    modification.mRand48Seed.xorString(entString.c_str(), seedIndex);
                    break;
                }
/*                case hue: {
                    maxCount = 2;
                    if (justCheck) break;
                    if (argcount == 1) {
                        if (rti == 0 && m.m_Color.mUseTarget & HSBColor::HueTarget)
                            throw DeferUntilRuntime();
                        m.m_Color.h += modArgs[0];
                    } else {
                        if (rti == 0 && (m.m_Color.mUseTarget & HSBColor::HueTarget || 
                                         m.m_Color.h != 0.0))
                            throw DeferUntilRuntime();
                        m.m_Color.h = arg[0];
                        m.m_Color.mUseTarget |= HSBColor::HueTarget;
                        m.m_ColorTarget.h = modArgs[1];
                        m.m_ColorTarget.mUseTarget |= HSBColor::HueTarget;
                    }
                    break;
                }
                case sat: {
                    maxCount = 2;
                    if (justCheck) break;
                    if (argcount == 1) {
                        if (rti == 0 && (m.m_Color.mUseTarget & HSBColor::SaturationTarget || 
                                         m.m_Color.s != 0.0))
                            throw DeferUntilRuntime();
                        m.m_Color.s = arg[0];
                    } else {
                        if (rti == 0 && (m.m_Color.mUseTarget & HSBColor::SaturationTarget || 
                                         m.m_Color.s != 0.0))
                            throw DeferUntilRuntime();
                        m.m_Color.s = arg[0];
                        m.m_Color.mUseTarget |= HSBColor::SaturationTarget;
                        if (rti == 0 && m.m_ColorTarget.s != 0.0)
                            throw DeferUntilRuntime();
                        m.m_ColorTarget.s = arg[1];
                        m.m_ColorTarget.mUseTarget |= HSBColor::SaturationTarget;
                    }
                    break;
                }
                case bright: {
                    maxCount = 2;
                    if (justCheck) break;
                    if (argcount == 1) {
                        if (rti == 0 && (m.m_Color.mUseTarget & HSBColor::BrightnessTarget || 
                                         m.m_Color.b != 0.0))
                            throw DeferUntilRuntime();
                        m.m_Color.b = arg[0];
                    } else {
                        if (rti == 0 && (m.m_Color.mUseTarget & HSBColor::BrightnessTarget || 
                                         m.m_Color.b != 0.0))
                            throw DeferUntilRuntime();
                        m.m_Color.b = arg[0];
                        m.m_Color.mUseTarget |= HSBColor::BrightnessTarget;
                        if (rti == 0 && m.m_ColorTarget.b != 0.0)
                            throw DeferUntilRuntime();
                        m.m_ColorTarget.b = arg[1];
                        m.m_ColorTarget.mUseTarget |= HSBColor::BrightnessTarget;
                    }
                    break;
                }
                case alpha: {
                    maxCount = 2;
                    if (justCheck) break;
                    if (argcount == 1) {
                        if (rti == 0 && (m.m_Color.mUseTarget & HSBColor::AlphaTarget || 
                                         m.m_Color.a != 0.0))
                            throw DeferUntilRuntime();
                        m.m_Color.a = arg[0];
                    } else {
                        if (rti == 0 && (m.m_Color.mUseTarget & HSBColor::AlphaTarget || 
                                         m.m_Color.a != 0.0))
                            throw DeferUntilRuntime();
                        m.m_Color.a = arg[0];
                        m.m_Color.mUseTarget |= HSBColor::AlphaTarget;
                        if (rti == 0 && m.m_ColorTarget.a != 0.0)
                            throw DeferUntilRuntime();
                        m.m_ColorTarget.a = arg[1];
                        m.m_ColorTarget.mUseTarget |= HSBColor::AlphaTarget;
                    }
                    break;
                }
                case hueTarg: {
                    if (justCheck) break;
                    if (rti == 0 && (m.m_Color.mUseTarget & HSBColor::HueTarget || 
                                     m.m_Color.h != 0.0))
                        throw DeferUntilRuntime();
                    m.m_Color.h = arg[0];
                    m.m_Color.mUseTarget |= HSBColor::HueTarget;
                    break;
                }
                case satTarg: {
                    if (justCheck) break;
                    if (rti == 0 && (m.m_Color.mUseTarget & HSBColor::SaturationTarget || 
                                     m.m_Color.s != 0.0))
                        throw DeferUntilRuntime();
                    m.m_Color.s = arg[0];
                    m.m_Color.mUseTarget |= HSBColor::SaturationTarget;
                    break;
                }
                case brightTarg: {
                    if (justCheck) break;
                    if (rti == 0 && (m.m_Color.mUseTarget & HSBColor::BrightnessTarget || 
                                     m.m_Color.b != 0.0))
                        throw DeferUntilRuntime();
                    m.m_Color.b = arg[0];
                    m.m_Color.mUseTarget |= HSBColor::BrightnessTarget;
                    break;
                }
                case alphaTarg: {
                    if (justCheck) break;
                    if (rti == 0 && (m.m_Color.mUseTarget & HSBColor::AlphaTarget || 
                                     m.m_Color.a != 0.0))
                        throw DeferUntilRuntime();
                    m.m_Color.a = arg[0];
                    m.m_Color.mUseTarget |= HSBColor::AlphaTarget;
                    break;
                }
                case targHue: {
                    if (justCheck) break;
                    m.m_ColorTarget.h += modArgs[0];
                    break;
                }
                case targSat: {
                    if (justCheck) break;
                    if (rti == 0 && m.m_ColorTarget.s != 0.0)
                        throw DeferUntilRuntime();
                    m.m_ColorTarget.s = arg[0];
                    break;
                }
                case targBright: {
                    if (justCheck) break;
                    if (rti == 0 && m.m_ColorTarget.b != 0.0)
                        throw DeferUntilRuntime();
                    m.m_ColorTarget.b = arg[0];
                    break;
                }
                case targAlpha: {
                    if (justCheck) break;
                    if (rti == 0 && m.m_ColorTarget.a != 0.0)
                        throw DeferUntilRuntime();
                    m.m_ColorTarget.a = arg[0];
                    break;
                }
                case param: {
                    minCount = maxCount = 0;
                    if (!p) {
                        CfdgError::Error(where, "Cannot provide a parameter in this context");
                        break;
                    }
                    if (justCheck) break;
                    p->assign(entString);
                    break;
                }
                case stroke: {
                    if (!width) {
                        CfdgError::Error(where, "Cannot provide a stroke width in this context");
                        break;
                    }
                    if (justCheck) break;
                    *width = modArgs[0];
                    break;
                }*/
                case modification: {
                    minCount = maxCount = 0;
                    arguments.evaluate(modification, s, width, justCheck, seedIndex, rti);
                    break;
                }
                default:
                    break;
            }
            
            if (argcount < minCount)
            	throw new RuntimeException("Not enough adjustment parameters");
            if (argcount > maxCount)
            	throw new RuntimeException("Too many adjustment parameters");
    	}

		public String getEntropy() {
			return entropy;
		}

		public ModTypeEnum getModType() {
			return modType;
		}

		public ASTExpression getArguments() {
			return arguments;
		}

		public void setArguments(ASTCons arguments) {
			this.arguments = arguments;
		}
    }