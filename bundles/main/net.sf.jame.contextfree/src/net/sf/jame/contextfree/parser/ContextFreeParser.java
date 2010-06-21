package net.sf.jame.contextfree.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;

import net.sf.jame.contextfree.ContextFreeConfig;
import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.CFDGConfigElement;
import net.sf.jame.contextfree.cfdg.analysis.DepthFirstAdapter;
import net.sf.jame.contextfree.cfdg.figure.FigureConfigElement;
import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.cfdg.lexer.Lexer;
import net.sf.jame.contextfree.cfdg.lexer.LexerException;
import net.sf.jame.contextfree.cfdg.node.AAlphaBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.AAlphaCurrentColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.AAlphaTargetColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.AArg0Function;
import net.sf.jame.contextfree.cfdg.node.AArg1Function;
import net.sf.jame.contextfree.cfdg.node.AArg2Function;
import net.sf.jame.contextfree.cfdg.node.AArrowOperator;
import net.sf.jame.contextfree.cfdg.node.ABackgroundDeclaration;
import net.sf.jame.contextfree.cfdg.node.ABasicPathCommand;
import net.sf.jame.contextfree.cfdg.node.ABasicPathReplacementBlock;
import net.sf.jame.contextfree.cfdg.node.ABasicShapeReplacement;
import net.sf.jame.contextfree.cfdg.node.ABasicShapeReplacementBlock;
import net.sf.jame.contextfree.cfdg.node.ABrightnessBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.ABrightnessCurrentColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.ABrightnessTargetColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.ACfdg;
import net.sf.jame.contextfree.cfdg.node.AColorCommandParameter;
import net.sf.jame.contextfree.cfdg.node.AColorShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.ACommandPathReplacement;
import net.sf.jame.contextfree.cfdg.node.AComposedExtendedExpression;
import net.sf.jame.contextfree.cfdg.node.ACurrentColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.AFirstExpression;
import net.sf.jame.contextfree.cfdg.node.AFlipGeometryAdjustment;
import net.sf.jame.contextfree.cfdg.node.AFunctionExpression;
import net.sf.jame.contextfree.cfdg.node.AFunctionExtendedExpression;
import net.sf.jame.contextfree.cfdg.node.AGeometryCommandParameter;
import net.sf.jame.contextfree.cfdg.node.AGeometryShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AHueBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.AHueCurrentColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.AHueTargetColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.AIncludeDeclaration;
import net.sf.jame.contextfree.cfdg.node.AListPathReplacementBlock;
import net.sf.jame.contextfree.cfdg.node.AListShapeReplacementBlock;
import net.sf.jame.contextfree.cfdg.node.AMinusOperator;
import net.sf.jame.contextfree.cfdg.node.AMultiPathReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.AMultiShapeReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.ANestedExpression;
import net.sf.jame.contextfree.cfdg.node.ANestedExtendedExpression;
import net.sf.jame.contextfree.cfdg.node.ANumberExpression;
import net.sf.jame.contextfree.cfdg.node.ANumberExtendedExpression;
import net.sf.jame.contextfree.cfdg.node.AOperationPathReplacement;
import net.sf.jame.contextfree.cfdg.node.AOrderedPathCommand;
import net.sf.jame.contextfree.cfdg.node.AOrderedShapeReplacement;
import net.sf.jame.contextfree.cfdg.node.AParametersCommandParameter;
import net.sf.jame.contextfree.cfdg.node.AParametersOperationParameter;
import net.sf.jame.contextfree.cfdg.node.APathDeclaration;
import net.sf.jame.contextfree.cfdg.node.APathOperation;
import net.sf.jame.contextfree.cfdg.node.APlusOperator;
import net.sf.jame.contextfree.cfdg.node.ARotateGeometryAdjustment;
import net.sf.jame.contextfree.cfdg.node.ARuleDeclaration;
import net.sf.jame.contextfree.cfdg.node.ASaturationBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.ASaturationCurrentColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.ASaturationTargetColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.ASecondExpression;
import net.sf.jame.contextfree.cfdg.node.ASinglePathReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.ASingleShapeReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.ASize2GeometryAdjustment;
import net.sf.jame.contextfree.cfdg.node.ASize3ShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.ASizeDeclaration;
import net.sf.jame.contextfree.cfdg.node.ASizeGeometryAdjustment;
import net.sf.jame.contextfree.cfdg.node.ASizeSizeAdjustment;
import net.sf.jame.contextfree.cfdg.node.ASkewGeometryAdjustment;
import net.sf.jame.contextfree.cfdg.node.ASlashOperator;
import net.sf.jame.contextfree.cfdg.node.AStarOperator;
import net.sf.jame.contextfree.cfdg.node.AStartshapeDeclaration;
import net.sf.jame.contextfree.cfdg.node.AStrokeCommandParameter;
import net.sf.jame.contextfree.cfdg.node.ATargetColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.ATileAdjustment;
import net.sf.jame.contextfree.cfdg.node.ATileDeclaration;
import net.sf.jame.contextfree.cfdg.node.AXGeometryAdjustment;
import net.sf.jame.contextfree.cfdg.node.AXSizeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AYGeometryAdjustment;
import net.sf.jame.contextfree.cfdg.node.AYSizeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AZShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.PBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.PColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.PCommandParameter;
import net.sf.jame.contextfree.cfdg.node.PCurrentColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.PExpression;
import net.sf.jame.contextfree.cfdg.node.PExtendedExpression;
import net.sf.jame.contextfree.cfdg.node.PFirstExpression;
import net.sf.jame.contextfree.cfdg.node.PFunction;
import net.sf.jame.contextfree.cfdg.node.PGeometryAdjustment;
import net.sf.jame.contextfree.cfdg.node.POperator;
import net.sf.jame.contextfree.cfdg.node.PPathCommand;
import net.sf.jame.contextfree.cfdg.node.PPathOperation;
import net.sf.jame.contextfree.cfdg.node.PPathReplacement;
import net.sf.jame.contextfree.cfdg.node.PPathReplacementBlock;
import net.sf.jame.contextfree.cfdg.node.PPathReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.PSecondExpression;
import net.sf.jame.contextfree.cfdg.node.PShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.PShapeReplacement;
import net.sf.jame.contextfree.cfdg.node.PShapeReplacementBlock;
import net.sf.jame.contextfree.cfdg.node.PShapeReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.PSizeAdjustment;
import net.sf.jame.contextfree.cfdg.node.PTargetColorAdjustment;
import net.sf.jame.contextfree.cfdg.node.PTileAdjustment;
import net.sf.jame.contextfree.cfdg.parser.Parser;
import net.sf.jame.contextfree.cfdg.parser.ParserException;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.extension.PathAdjustmentExtensionConfig;
import net.sf.jame.contextfree.cfdg.pathAdjustment.extension.PathAdjustmentExtensionRuntime;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionConfig;
import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionRuntime;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionConfig;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionRuntime;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionConfig;
import net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionRuntime;
import net.sf.jame.contextfree.extensions.figure.PathFigureConfig;
import net.sf.jame.contextfree.extensions.figure.RuleFigureConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.CurrentAlphaPathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.CurrentBrightnessPathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.CurrentHuePathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.CurrentSaturationPathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.FlipPathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.RotatePathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.Size2PathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.SizePathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.SkewPathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.TargetAlphaPathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.TargetBrightnessPathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.TargetHuePathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.TargetSaturationPathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.XPathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathAdjustment.YPathAdjustmentConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.ArcRelPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.ArcToPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.ClosePolyPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.CurveRelPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.CurveToPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.FillPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.LineRelPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.LineToPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.MoveRelPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.MoveToPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.MultiPathReplacementConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.CurrentAlphaShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.CurrentBrightnessShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.CurrentHueShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.CurrentSaturationShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.FlipShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.RotateShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.Size2ShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.Size3ShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.SizeShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.SkewShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.TargetHueShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.XShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.YShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.ZShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeReplacement.MultiShapeReplacementConfig;
import net.sf.jame.contextfree.extensions.shapeReplacement.SingleShapeReplacementConfig;
import net.sf.jame.core.extension.ConfigurableExtension;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.util.Color32bit;
import net.sf.jame.core.util.Colors;

import org.apache.log4j.Logger;

public class ContextFreeParser {
	private static final Logger logger = Logger.getLogger(ContextFreeParser.class);
	
	public ContextFreeConfig parseConfig(String text) throws ContextFreeParserException {
		return parseConfig(new StringReader(text));
	}

	public ContextFreeConfig parseConfig(File file) throws ContextFreeParserException {
		try {
			return parseConfig(new FileReader(file));
		} catch (FileNotFoundException e) {
			throw new ContextFreeParserException(e);
		}
	}

	public ContextFreeConfig parseConfig(Reader reader) throws ContextFreeParserException {
		try {
			Parser parser = new Parser(new Lexer(new PushbackReader(reader)));
			ContextFreeConfig config = new ContextFreeConfig();
			CFInterpreter interpreter = new CFInterpreter(config);
			parser.parse().apply(interpreter);
			return config;
		}
		catch (ParserException e) {
			throw new ContextFreeParserException(e);
		}
		catch (LexerException e) {
			throw new ContextFreeParserException(e);
		}
		catch (IOException e) {
			throw new ContextFreeParserException(e);
		}
	}

	public class CFInterpreter extends DepthFirstAdapter {
		private ContextFreeConfig config;
		
		public CFInterpreter(ContextFreeConfig config) {
			this.config = config;
		}

		/**
		 * 
		 */
		@Override
		public void inACfdg(ACfdg node) {
			super.inACfdg(node);
			config.setCFDG(new CFDGConfigElement());
		}

		/**
		 * 
		 */
		@Override
		public void inARuleDeclaration(ARuleDeclaration node) {
			super.inARuleDeclaration(node);
			logger.info("RuleDeclaration " + node);
			try {
				FigureConfigElement figureElement = createRuleFigureElement(node);
				config.getCFDG().appendFigureConfigElement(figureElement);
			} catch (ExtensionNotFoundException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 
		 */
		@Override
		public void inAPathDeclaration(APathDeclaration node) {
			super.inAPathDeclaration(node);
			logger.info("PathDeclaration " + node);
			try {
				FigureConfigElement figureElement = createPathFigureElement(node);
				config.getCFDG().appendFigureConfigElement(figureElement);
			} catch (ExtensionNotFoundException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 
		 */
		@Override
		public void inAStartshapeDeclaration(AStartshapeDeclaration node) {
			super.inAStartshapeDeclaration(node);
			logger.info("StartshapeDeclaration " + node);
			config.getCFDG().setStartshape(node.getStartshape().getText());
		}

		/**
		 * 
		 */
		@Override
		public void inAIncludeDeclaration(AIncludeDeclaration node) {
			super.inAIncludeDeclaration(node);
			logger.info("IncludeDeclaration " + node);
			try {
				ContextFreeConfig tmpConfig = parseConfig(new File(node.getFilename().getText()));
				logger.info(tmpConfig);//TODO merge config 
			} catch (ContextFreeParserException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 
		 */
		@Override
		public void inABackgroundDeclaration(ABackgroundDeclaration node) {
			super.inABackgroundDeclaration(node);
			logger.info("BackgroundDeclaration " + node);
			config.getCFDG().setBackground(new Color32bit(0xFFFFFFFF));
			for (PBackgroundAdjustment adjustment : node.getBackgroundAdjustment()) {
				if (adjustment instanceof AHueBackgroundAdjustment) {
					float value = evaluateExpression(((AHueBackgroundAdjustment) adjustment).getExpression());
					float[] hsbvals = new float[3];
					Colors.toHSB(config.getCFDG().getBackground().getARGB(), hsbvals);
					hsbvals[0] = value;
					config.getCFDG().setBackground(new Color32bit(Colors.toRGB(config.getCFDG().getBackground().getAlpha(), hsbvals)));
				} else if (adjustment instanceof ABrightnessBackgroundAdjustment) {
					float value = evaluateExpression(((ABrightnessBackgroundAdjustment) adjustment).getExpression());
					float[] hsbvals = new float[3];
					Colors.toHSB(config.getCFDG().getBackground().getARGB(), hsbvals);
					hsbvals[1] = value;
					config.getCFDG().setBackground(new Color32bit(Colors.toRGB(config.getCFDG().getBackground().getAlpha(), hsbvals)));
				} else if (adjustment instanceof ASaturationBackgroundAdjustment) {
					float value = evaluateExpression(((ASaturationBackgroundAdjustment) adjustment).getExpression());
					float[] hsbvals = new float[3];
					Colors.toHSB(config.getCFDG().getBackground().getARGB(), hsbvals);
					hsbvals[2] = value;
					config.getCFDG().setBackground(new Color32bit(Colors.toRGB(config.getCFDG().getBackground().getAlpha(), hsbvals)));
				} else if (adjustment instanceof AAlphaBackgroundAdjustment) {
					float value = evaluateExpression(((AAlphaBackgroundAdjustment) adjustment).getExpression());
					float[] hsbvals = new float[3];
					Colors.toHSB(config.getCFDG().getBackground().getARGB(), hsbvals);
					config.getCFDG().setBackground(new Color32bit(Colors.toRGB((int) Math.rint(value * 255), hsbvals)));
				}
			}
		}

		/**
		 * 
		 */
		@Override
		public void inATileDeclaration(ATileDeclaration node) {
			super.inATileDeclaration(node);
			logger.info("TileDeclaration " + node);
			for (PTileAdjustment adjustment : node.getTileAdjustment()) {
				if (adjustment instanceof ATileAdjustment) {
					PFirstExpression firstExpression = ((ATileAdjustment) adjustment).getFirstExpression();
					if (firstExpression != null && firstExpression instanceof AFirstExpression) {
						float value = evaluateExpression(((AFirstExpression) firstExpression).getExtendedExpression());
						logger.info("size x = " + value);//TODO set tile size x
					}
					PSecondExpression secondExpression = ((ATileAdjustment) adjustment).getSecondExpression();
					if (secondExpression != null && secondExpression instanceof ASecondExpression) {
						float value = evaluateExpression(((ASecondExpression) secondExpression).getExtendedExpression());
						logger.info("size y = " + value);//TODO set tile size y
					}
				}
			}
		}

		/**
		 * 
		 */
		@Override
		public void inASizeDeclaration(ASizeDeclaration node) {
			super.inASizeDeclaration(node);
			logger.info("SizeDeclaration " + node);
			for (PSizeAdjustment adjustment : node.getSizeAdjustment()) {
				if (adjustment instanceof ASizeSizeAdjustment) {
					PFirstExpression firstExpression = ((ASizeSizeAdjustment) adjustment).getFirstExpression();
					if (firstExpression instanceof AFirstExpression) {
						float value = evaluateExpression(((AFirstExpression) firstExpression).getExtendedExpression());
						logger.info("size x = " + value);//TODO set size x
					}
					PSecondExpression secondExpression = ((ASizeSizeAdjustment) adjustment).getSecondExpression();
					if (secondExpression instanceof ASecondExpression) {
						float value = evaluateExpression(((ASecondExpression) secondExpression).getExtendedExpression());
						logger.info("size y = " + value);//TODO set size y
					}
				} else if (adjustment instanceof AXSizeAdjustment) {
					float value = evaluateExpression(((AXSizeAdjustment) adjustment).getExpression());
					logger.info("x = " + value);//TODO set x
				} else if (adjustment instanceof AYSizeAdjustment) {
					float value = evaluateExpression(((AYSizeAdjustment) adjustment).getExpression());
					logger.info("y = " + value);//TODO set y
				}
			}
		}

		/**
		 * 
		 */
		@Override
	    public void inAMultiShapeReplacementDeclaration(AMultiShapeReplacementDeclaration node) {
			super.inAMultiShapeReplacementDeclaration(node);
	    	logger.info("MultiShapeReplacementDeclaration " + node);
	    }

		/**
		 * 
		 */
		@Override
	    public void inASingleShapeReplacementDeclaration(ASingleShapeReplacementDeclaration node) {
			super.inASingleShapeReplacementDeclaration(node);
	    	logger.info("SingleShapeReplacementDeclaration " + node);
	    }

		/**
		 * 
		 */
		@Override
	    public void inAMultiPathReplacementDeclaration(AMultiPathReplacementDeclaration node) {
			super.inAMultiPathReplacementDeclaration(node);
	    	logger.info("MultiPathReplacementDeclaration " + node);
	    }

		/**
		 * 
		 */
		@Override
	    public void inASinglePathReplacementDeclaration(ASinglePathReplacementDeclaration node) {
			super.inASinglePathReplacementDeclaration(node);
	    	logger.info("SinglePathReplacementDeclaration " + node);
	    }

		/**
		 * 
		 */
		@Override
		public void inAOperationPathReplacement(AOperationPathReplacement node) {
			super.inAOperationPathReplacement(node);
			logger.info("OperationPathReplacement " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inACommandPathReplacement(ACommandPathReplacement node) {
			super.inACommandPathReplacement(node);
			logger.info("CommandPathReplacement " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAColorShapeAdjustment(AColorShapeAdjustment node) {
			super.inAColorShapeAdjustment(node);
			logger.info("ColorShapeAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAGeometryShapeAdjustment(AGeometryShapeAdjustment node) {
			super.inAGeometryShapeAdjustment(node);
			logger.info("GeometryShapeAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAZShapeAdjustment(AZShapeAdjustment node) {
			super.inAZShapeAdjustment(node);
			logger.info("ZShapeAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inASize3ShapeAdjustment(ASize3ShapeAdjustment node) {
			super.inASize3ShapeAdjustment(node);
			logger.info("Size3ShapeAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAColorCommandParameter(AColorCommandParameter node) {
			super.inAColorCommandParameter(node);
			logger.info("ColorCommandParameter " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAGeometryCommandParameter(AGeometryCommandParameter node) {
			super.inAGeometryCommandParameter(node);
			logger.info("GeometryCommandParameter " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAStrokeCommandParameter(AStrokeCommandParameter node) {
			super.inAStrokeCommandParameter(node);
			logger.info("StrokeCommandParameter " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAParametersOperationParameter(AParametersOperationParameter node) {
			super.inAParametersOperationParameter(node);
			logger.info("ParametersOperationParameter " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAParametersCommandParameter(AParametersCommandParameter node) {
			super.inAParametersCommandParameter(node);
			logger.info("ParametersCommandParameter " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAArg0Function(AArg0Function node) {
			super.inAArg0Function(node);
			logger.info("Arg0Function " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAArg1Function(AArg1Function node) {
			super.inAArg1Function(node);
			logger.info("Arg1Function " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAArg2Function(AArg2Function node) {
			super.inAArg2Function(node);
			logger.info("Arg2Function " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inANumberExpression(ANumberExpression node) {
			super.inANumberExpression(node);
			logger.info("NumberExpression " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inANestedExpression(ANestedExpression node) {
			super.inANestedExpression(node);
			logger.info("NestedExpression " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAFunctionExpression(AFunctionExpression node) {
			super.inAFunctionExpression(node);
			logger.info("FunctionExpression " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inANumberExtendedExpression(ANumberExtendedExpression node) {
			super.inANumberExtendedExpression(node);
			logger.info("NumberExtendedExpression " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inANestedExtendedExpression(ANestedExtendedExpression node) {
			super.inANestedExtendedExpression(node);
			logger.info("NestedExtendedExpression " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAFunctionExtendedExpression(AFunctionExtendedExpression node) {
			super.inAFunctionExtendedExpression(node);
			logger.info("FunctionExtendedExpression " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAComposedExtendedExpression(AComposedExtendedExpression node) {
			super.inAComposedExtendedExpression(node);
			logger.info("ComposedExtendedExpression " + node);
		}
		
		public float evaluateExpression(PExpression expression) {
			if (expression instanceof AFunctionExpression) {
				return evaluateExpression((AFunctionExpression) expression);
			} else if (expression instanceof ANestedExpression) {
				return evaluateExpression((ANestedExpression) expression);
			} else if (expression instanceof ANumberExpression) {
				return evaluateExpression((ANumberExpression) expression);
			}
			return 0;
		}

		public float evaluateExpression(AFunctionExpression expression) {
			return evaluateFunction(expression.getFunction());
		}
		
		public float evaluateExpression(ANestedExpression expression) {
			return evaluateExpression(expression.getExtendedExpression());
		}

		public float evaluateExpression(ANumberExpression expression) {
			return Float.valueOf(expression.getNumber().getText());
		}
		
		public float evaluateExpression(PExtendedExpression expression) {
			if (expression instanceof AFunctionExtendedExpression) {
				return evaluateExpression((AFunctionExtendedExpression) expression);
			} else if (expression instanceof ANestedExtendedExpression) {
				return evaluateExpression((ANestedExtendedExpression) expression);
			} else if (expression instanceof ANumberExtendedExpression) {
				return evaluateExpression((ANumberExtendedExpression) expression);
			} else if (expression instanceof AComposedExtendedExpression) {
				return evaluateExpression((AComposedExtendedExpression) expression);
			}
			return 0;
		}

		public float evaluateExpression(AFunctionExtendedExpression expression) {
			return evaluateFunction(expression.getFunction());
		}
		
		public float evaluateExpression(ANestedExtendedExpression expression) {
			return evaluateExpression(expression.getExtendedExpression());
		}

		public float evaluateExpression(ANumberExtendedExpression expression) {
			return Float.valueOf(expression.getNumber().getText());
		}

		public float evaluateExpression(AComposedExtendedExpression expression) {
			float value1 = evaluateExpression(expression.getExpression());
			float value2 = evaluateExpression(expression.getExtendedExpression());
			POperator operator = expression.getOperator();
			if (operator instanceof APlusOperator) {
				return value1 + value2;
			} else if (operator instanceof AMinusOperator) {
				return value1 - value2;
			} else if (operator instanceof AStarOperator) {
				return value1 * value2;
			} else if (operator instanceof ASlashOperator) {
				return value1 / value2;
			} else if (operator instanceof AArrowOperator) {
				return (float) Math.pow(value1, value2);
			}
			return 0;
		}

		public float evaluateFunction(PFunction function) {
			if (function instanceof AArg0Function) {
				return evaluateFunction((AArg0Function) function); 
			} else if (function instanceof AArg1Function) {
				return evaluateFunction((AArg1Function) function); 
			} else if (function instanceof AArg2Function) {
				return evaluateFunction((AArg2Function) function); 
			}
			return 0;
		}

		public float evaluateFunction(AArg0Function function) {
			String functionName = function.getFunctionArg0().getText();
			if ("rnd".equals(functionName)) {
				return (float) Math.random();
			}
			return 0;
		}

		public float evaluateFunction(AArg1Function function) {
			PFirstExpression firstExpression = function.getFirstExpression();
			if (firstExpression instanceof AFirstExpression) {
				float value = evaluateExpression(((AFirstExpression) firstExpression).getExtendedExpression());
				String functionName = function.getFunctionArg1().getText();
				if ("sin".equals(functionName)) {
					return (float) Math.sin(value);
				} else if ("cos".equals(functionName)) {
					return (float) Math.cos(value);
				} else if ("tan".equals(functionName)) {
					return (float) Math.tan(value);
				} else if ("sinh".equals(functionName)) {
					return (float) Math.sinh(value);
				} else if ("cosh".equals(functionName)) {
					return (float) Math.cosh(value);
				} else if ("tanh".equals(functionName)) {
					return (float) Math.tanh(value);
				} else if ("asin".equals(functionName)) {
					return (float) Math.asin(value);
				} else if ("acos".equals(functionName)) {
					return (float) Math.acos(value);
				} else if ("atan".equals(functionName)) {
					return (float) Math.atan(value);
				} else if ("abs".equals(functionName)) {
					return (float) Math.abs(value);
				} else if ("exp".equals(functionName)) {
					return (float) Math.exp(value);
				} else if ("log".equals(functionName)) {
					return (float) Math.log(value);
				} else if ("sqrt".equals(functionName)) {
					return (float) Math.sqrt(value);
				}
			}
			return 0;
		}

		public float evaluateFunction(AArg2Function function) {
			PFirstExpression firstExpression = function.getFirstExpression();
			PSecondExpression secondExpression = function.getSecondExpression();
			if (firstExpression instanceof AFirstExpression && secondExpression instanceof ASecondExpression) {
				float value1 = evaluateExpression(((AFirstExpression) firstExpression).getExtendedExpression());
				float value2 = evaluateExpression(((ASecondExpression) secondExpression).getExtendedExpression());
				String functionName = function.getFunctionArg2().getText();
				if ("min".equals(functionName)) {
					return (float) Math.min(value1, value2);
				} else if ("max".equals(functionName)) {
					return (float) Math.max(value1, value2);
				} else if ("pow".equals(functionName)) {
					return (float) Math.pow(value1, value2);
				} else if ("hypot".equals(functionName)) {
					return (float) Math.hypot(value1, value2);
				} else if ("atan2".equals(functionName)) {
					return (float) Math.atan2(value1, value2);
				}
			}
			return 0;
		}

		private FigureConfigElement createPathFigureElement(APathDeclaration pathDeclaration) throws ExtensionNotFoundException {
			PathFigureConfig config = new PathFigureConfig();
			config.setName(pathDeclaration.getString().getText());
			for (PPathReplacementDeclaration pathReplacementDeclaration : pathDeclaration.getPathReplacementDeclaration()) {
				PathReplacementConfigElement pathReplacementElement = createPathReplacementElement(pathReplacementDeclaration);
				if (pathReplacementElement != null) {
					config.appendPathReplacementConfigElement(pathReplacementElement);
				}
			}
			ConfigurableExtension<FigureExtensionRuntime<?>, FigureExtensionConfig> extension = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.path");
			ConfigurableExtensionReference<FigureExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			FigureConfigElement figureElement = new FigureConfigElement();
			figureElement.setExtensionReference(reference);
			return figureElement;
		}

		private PathReplacementConfigElement createPathReplacementElement(PPathReplacementDeclaration pathReplacementDeclaration) throws ExtensionNotFoundException {
			if (pathReplacementDeclaration instanceof ASinglePathReplacementDeclaration) {
				return createPathReplacementElement((ASinglePathReplacementDeclaration) pathReplacementDeclaration);
			} else if (pathReplacementDeclaration instanceof AMultiPathReplacementDeclaration) {
				return createPathReplacementElement((AMultiPathReplacementDeclaration) pathReplacementDeclaration);
			}
			return null;
		}

		private PathReplacementConfigElement createPathReplacementElement(ASinglePathReplacementDeclaration pathReplacementDeclaration) throws ExtensionNotFoundException {
			return createPathReplacementElement(pathReplacementDeclaration.getPathReplacement());
		}
		
		private PathReplacementConfigElement createPathReplacementElement(AMultiPathReplacementDeclaration pathReplacementDeclaration) throws ExtensionNotFoundException {
			MultiPathReplacementConfig config = new MultiPathReplacementConfig();
			config.setTimes(Integer.valueOf(pathReplacementDeclaration.getNumber().getText()));
			for (PGeometryAdjustment pathAdjustment : pathReplacementDeclaration.getGeometryAdjustment()) {
				PathAdjustmentConfigElement pathAdjustmentElement = createPathAdjustmentElement(pathAdjustment);
				if (pathAdjustmentElement != null) {
					config.appendPathAdjustmentConfigElement(pathAdjustmentElement);
				}
			}
			PPathReplacementBlock pathReplacementBlock = pathReplacementDeclaration.getPathReplacementBlock();
			if (pathReplacementBlock instanceof ABasicPathReplacementBlock) {
				PPathReplacement pathReplacement = ((ABasicPathReplacementBlock) pathReplacementBlock).getPathReplacement();
				PathReplacementConfigElement pathReplacementElement = createPathReplacementElement(pathReplacement);
				if (pathReplacementElement != null) {
					config.appendPathReplacementConfigElement(pathReplacementElement);
				}
			} else if (pathReplacementBlock instanceof AListPathReplacementBlock) {
				for (PPathReplacementDeclaration pathReplacement : ((AListPathReplacementBlock) pathReplacementBlock).getPathReplacementDeclaration()) {
					PathReplacementConfigElement pathReplacementElement = createPathReplacementElement(pathReplacement);
					if (pathReplacementElement != null) {
						config.appendPathReplacementConfigElement(pathReplacementElement);
					}
				}
			}
			return null;
		}

		private PathReplacementConfigElement createPathReplacementElement(PPathReplacement pathReplacement) throws ExtensionNotFoundException {
			ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = null;
			if (pathReplacement instanceof AOperationPathReplacement) {
				reference = createPathReplacementExtensionReference((AOperationPathReplacement) pathReplacement);
			} else if (pathReplacement instanceof ACommandPathReplacement) {
				reference = createPathReplacementExtensionReference((ACommandPathReplacement) pathReplacement);
			}
			PathReplacementConfigElement pathReplacementElement = new PathReplacementConfigElement();
			pathReplacementElement.setExtensionReference(reference);
			return pathReplacementElement;
		}

		private PathAdjustmentConfigElement createPathAdjustmentElement(PColorAdjustment pathAdjustment) throws ExtensionNotFoundException {
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = null;
			if (pathAdjustment instanceof ACurrentColorAdjustment) {
				reference = getPathAdjustmentExtensionReference((ACurrentColorAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof ATargetColorAdjustment) {
				reference = getPathAdjustmentExtensionReference((ATargetColorAdjustment) pathAdjustment);
			}
			PathAdjustmentConfigElement pathAdjustmentElement = new PathAdjustmentConfigElement();
			pathAdjustmentElement.setExtensionReference(reference);
			return pathAdjustmentElement;
		}

		private PathAdjustmentConfigElement createPathAdjustmentElement(PGeometryAdjustment pathAdjustment) throws ExtensionNotFoundException {
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = null;
			if (pathAdjustment instanceof AFlipGeometryAdjustment) {
				reference = getPathAdjustmentExtensionReference((AFlipGeometryAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof ARotateGeometryAdjustment) {
				reference = getPathAdjustmentExtensionReference((ARotateGeometryAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof ASize2GeometryAdjustment) {
				reference = getPathAdjustmentExtensionReference((ASize2GeometryAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof ASizeGeometryAdjustment) {
				reference = getPathAdjustmentExtensionReference((ASizeGeometryAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof ASkewGeometryAdjustment) {
				reference = getPathAdjustmentExtensionReference((ASkewGeometryAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof AXGeometryAdjustment) {
				reference = getPathAdjustmentExtensionReference((AXGeometryAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof AYGeometryAdjustment) {
				reference = getPathAdjustmentExtensionReference((AYGeometryAdjustment) pathAdjustment);
			}
			PathAdjustmentConfigElement pathAdjustmentElement = new PathAdjustmentConfigElement();
			pathAdjustmentElement.setExtensionReference(reference);
			return pathAdjustmentElement;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ACurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			PCurrentColorAdjustment currentColorAdjustment = colorAdjustment.getCurrentColorAdjustment();
			if (currentColorAdjustment instanceof AAlphaCurrentColorAdjustment) {
				return getPathAdjustmentExtensionReference((AAlphaCurrentColorAdjustment) currentColorAdjustment);
			} else if (currentColorAdjustment instanceof ABrightnessCurrentColorAdjustment) {
				return getPathAdjustmentExtensionReference((ABrightnessCurrentColorAdjustment) currentColorAdjustment);
			} else if (currentColorAdjustment instanceof ASaturationCurrentColorAdjustment) {
				return getPathAdjustmentExtensionReference((ASaturationCurrentColorAdjustment) currentColorAdjustment);
			} else if (currentColorAdjustment instanceof AHueCurrentColorAdjustment) {
				return getPathAdjustmentExtensionReference((AHueCurrentColorAdjustment) currentColorAdjustment);
			} else {
				return null;
			}
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ATargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			PTargetColorAdjustment targetColorAdjustment = colorAdjustment.getTargetColorAdjustment();
			if (targetColorAdjustment instanceof AAlphaTargetColorAdjustment) {
				return getPathAdjustmentExtensionReference((AAlphaTargetColorAdjustment) targetColorAdjustment);
			} else if (targetColorAdjustment instanceof ABrightnessTargetColorAdjustment) {
				return getPathAdjustmentExtensionReference((ABrightnessTargetColorAdjustment) targetColorAdjustment);
			} else if (targetColorAdjustment instanceof ASaturationTargetColorAdjustment) {
				return getPathAdjustmentExtensionReference((ASaturationTargetColorAdjustment) targetColorAdjustment);
			} else if (targetColorAdjustment instanceof AHueTargetColorAdjustment) {
				return getPathAdjustmentExtensionReference((AHueTargetColorAdjustment) targetColorAdjustment);
			} else {
				return null;
			}
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AAlphaCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentAlphaPathAdjustmentConfig config = new CurrentAlphaPathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.currentAlpha");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AAlphaTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetAlphaPathAdjustmentConfig config = new TargetAlphaPathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.targetAlpha");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ABrightnessCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentBrightnessPathAdjustmentConfig config = new CurrentBrightnessPathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.currentBrightness");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ABrightnessTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetBrightnessPathAdjustmentConfig config = new TargetBrightnessPathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.targetBrightness");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ASaturationCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentSaturationPathAdjustmentConfig config = new CurrentSaturationPathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.currentSaturation");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ASaturationTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetSaturationPathAdjustmentConfig config = new TargetSaturationPathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.targetSaturation");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AHueCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentHuePathAdjustmentConfig config = new CurrentHuePathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.currentHue");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AHueTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetHuePathAdjustmentConfig config = new TargetHuePathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.targetHue");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AXGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			XPathAdjustmentConfig config = new XPathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.x");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AYGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			YPathAdjustmentConfig config = new YPathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.y");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ASizeGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			SizePathAdjustmentConfig config = new SizePathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.size");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ASize2GeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			Size2PathAdjustmentConfig config = new Size2PathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.size2");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ASkewGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			SkewPathAdjustmentConfig config = new SkewPathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.skew");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AFlipGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			FlipPathAdjustmentConfig config = new FlipPathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.flip");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ARotateGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			RotatePathAdjustmentConfig config = new RotatePathAdjustmentConfig(); //TODO
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.rotate");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathReplacementExtensionConfig> createPathReplacementExtensionReference(AOperationPathReplacement pathReplacement) throws ExtensionNotFoundException {
			PPathOperation pathOperation = pathReplacement.getPathOperation();
			if (pathOperation instanceof APathOperation) {
				return createPathReplacementExtensionReference((APathOperation) pathOperation);
			}
			return null;
		}

		private ConfigurableExtensionReference<PathReplacementExtensionConfig> createPathReplacementExtensionReference(APathOperation pathOperation) throws ExtensionNotFoundException {
			String operation = pathOperation.getOperation().getText();
			if ("LINETO".equals(operation)) {
				LineToPathReplacementConfig config = new LineToPathReplacementConfig();
				//TODO parameters
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.lineto");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("ARCTO".equals(operation)) {
				ArcToPathReplacementConfig config = new ArcToPathReplacementConfig();
				//TODO parameters
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.arcto");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("CURVETO".equals(operation)) {
				CurveToPathReplacementConfig config = new CurveToPathReplacementConfig();
				//TODO parameters
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.curveto");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("MOVETO".equals(operation)) {
				MoveToPathReplacementConfig config = new MoveToPathReplacementConfig();
				//TODO parameters
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.moveto");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("LINEREL".equals(operation)) {
				LineRelPathReplacementConfig config = new LineRelPathReplacementConfig();
				//TODO parameters
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.linerel");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("ARCREL".equals(operation)) {
				ArcRelPathReplacementConfig config = new ArcRelPathReplacementConfig();
				//TODO parameters
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.arcrel");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("CURVEREL".equals(operation)) {
				CurveRelPathReplacementConfig config = new CurveRelPathReplacementConfig();
				//TODO parameters
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.curverel");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("MOVEREL".equals(operation)) {
				MoveRelPathReplacementConfig config = new MoveRelPathReplacementConfig();
				//TODO parameters
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.moverel");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("CLOSEPOLY".equals(operation)) {
				ClosePolyPathReplacementConfig config = new ClosePolyPathReplacementConfig();
				//TODO parameters
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.closepoly");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			}
			return null;
		}

		private ConfigurableExtensionReference<PathReplacementExtensionConfig> createPathReplacementExtensionReference(ACommandPathReplacement pathReplacement) throws ExtensionNotFoundException {
			PPathCommand pathCommand = pathReplacement.getPathCommand();
			if (pathCommand instanceof ABasicPathCommand) {
				return createPathReplacementExtensionReference((ABasicPathCommand) pathCommand);
			} else if (pathCommand instanceof AOrderedPathCommand) {
				return createPathReplacementExtensionReference((AOrderedPathCommand) pathCommand);
			}
			return null;
		}
		
		private ConfigurableExtensionReference<PathReplacementExtensionConfig> createPathReplacementExtensionReference(ABasicPathCommand pathCommand) throws ExtensionNotFoundException {
			String command = pathCommand.getCommand().getText();
			if ("FILL".equals(command)) {
				FillPathReplacementConfig config = new FillPathReplacementConfig();
				for (PCommandParameter commadParameter : pathCommand.getCommandParameter()) {
					if (commadParameter instanceof AColorCommandParameter) {
						createPathAdjustmentElement(((AColorCommandParameter) commadParameter).getColorAdjustment());//TODO
					} else if (commadParameter instanceof AGeometryCommandParameter) {
						createPathAdjustmentElement(((AGeometryCommandParameter) commadParameter).getGeometryAdjustment());//TODO
					} else if (commadParameter instanceof AParametersCommandParameter) {
						//TODO 
					} else if (commadParameter instanceof AStrokeCommandParameter) {
						//TODO 
					}
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.command.fill");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("STROKE".equals(command)) {
				FillPathReplacementConfig config = new FillPathReplacementConfig();
				for (PCommandParameter commadParameter : pathCommand.getCommandParameter()) {
					if (commadParameter instanceof AColorCommandParameter) {
						createPathAdjustmentElement(((AColorCommandParameter) commadParameter).getColorAdjustment());//TODO
					} else if (commadParameter instanceof AGeometryCommandParameter) {
						createPathAdjustmentElement(((AGeometryCommandParameter) commadParameter).getGeometryAdjustment());//TODO
					} else if (commadParameter instanceof AParametersCommandParameter) {
						//TODO 
					} else if (commadParameter instanceof AStrokeCommandParameter) {
						//TODO 
					}
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.command.stroke");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			}
			return null;
		}

		private ConfigurableExtensionReference<PathReplacementExtensionConfig> createPathReplacementExtensionReference(AOrderedPathCommand pathCommand) throws ExtensionNotFoundException {
			String command = pathCommand.getCommand().getText();
			//TODO order
			if ("FILL".equals(command)) {
				FillPathReplacementConfig config = new FillPathReplacementConfig();
				for (PCommandParameter commadParameter : pathCommand.getCommandParameter()) {
					if (commadParameter instanceof AColorCommandParameter) {
						createPathAdjustmentElement(((AColorCommandParameter) commadParameter).getColorAdjustment());//TODO
					} else if (commadParameter instanceof AGeometryCommandParameter) {
						createPathAdjustmentElement(((AGeometryCommandParameter) commadParameter).getGeometryAdjustment());//TODO
					} else if (commadParameter instanceof AParametersCommandParameter) {
						//TODO 
					} else if (commadParameter instanceof AStrokeCommandParameter) {
						//TODO 
					}
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.command.fill");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("STROKE".equals(command)) {
				FillPathReplacementConfig config = new FillPathReplacementConfig();
				for (PCommandParameter commadParameter : pathCommand.getCommandParameter()) {
					if (commadParameter instanceof AColorCommandParameter) {
						createPathAdjustmentElement(((AColorCommandParameter) commadParameter).getColorAdjustment());//TODO
					} else if (commadParameter instanceof AGeometryCommandParameter) {
						createPathAdjustmentElement(((AGeometryCommandParameter) commadParameter).getGeometryAdjustment());//TODO
					} else if (commadParameter instanceof AParametersCommandParameter) {
						//TODO 
					} else if (commadParameter instanceof AStrokeCommandParameter) {
						//TODO 
					}
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.command.stroke");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			}
			return null;
		}

		private FigureConfigElement createRuleFigureElement(ARuleDeclaration ruleDeclaration) throws ExtensionNotFoundException {
			RuleFigureConfig config = new RuleFigureConfig();
			config.setName(ruleDeclaration.getString().getText());
			config.setProbability(Float.valueOf(ruleDeclaration.getNumber().getText()));
			for (PShapeReplacementDeclaration shapeReplacementDeclaration : ruleDeclaration.getShapeReplacementDeclaration()) {
				ShapeReplacementConfigElement shapeReplacementElement = createShapeReplacementElement(shapeReplacementDeclaration);
				if (shapeReplacementElement != null) {
					config.appendShapeReplacementConfigElement(shapeReplacementElement);
				}
			}
			ConfigurableExtension<FigureExtensionRuntime<?>, FigureExtensionConfig> extension = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.rule");
			ConfigurableExtensionReference<FigureExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			FigureConfigElement figureElement = new FigureConfigElement();
			figureElement.setExtensionReference(reference);
			return figureElement;
		}

		private ShapeReplacementConfigElement createShapeReplacementElement(PShapeReplacementDeclaration shapeReplacementDeclaration) throws ExtensionNotFoundException {
			if (shapeReplacementDeclaration instanceof ASingleShapeReplacementDeclaration) {
				return createShapeReplacementElement((ASingleShapeReplacementDeclaration) shapeReplacementDeclaration);
			} else if (shapeReplacementDeclaration instanceof AMultiShapeReplacementDeclaration) {
				return createShapeReplacementElement((AMultiShapeReplacementDeclaration) shapeReplacementDeclaration);
			}
			return null;
		}

		private ShapeReplacementConfigElement createShapeReplacementElement(ASingleShapeReplacementDeclaration shapeReplacementDeclaration) throws ExtensionNotFoundException {
			return createShapeReplacementElement(shapeReplacementDeclaration.getShapeReplacement());
		}

		private ShapeReplacementConfigElement createShapeReplacementElement(AMultiShapeReplacementDeclaration shapeReplacementDeclaration) throws ExtensionNotFoundException {
			MultiShapeReplacementConfig config = new MultiShapeReplacementConfig();
			config.setTimes(Integer.valueOf(shapeReplacementDeclaration.getNumber().getText()));
			for (PShapeAdjustment shapeAdjustment : shapeReplacementDeclaration.getShapeAdjustment()) {
				ShapeAdjustmentConfigElement shapeAdjustmentElement = createShapeAdjustmentElement(shapeAdjustment);
				if (shapeAdjustmentElement != null) {
					config.appendShapeAdjustmentConfigElement(shapeAdjustmentElement);
				}
			}
			PShapeReplacementBlock shapeReplacementBlock = shapeReplacementDeclaration.getShapeReplacementBlock();
			if (shapeReplacementBlock instanceof ABasicShapeReplacementBlock) {
				PShapeReplacement shapeReplacement = ((ABasicShapeReplacementBlock) shapeReplacementBlock).getShapeReplacement();
				ShapeReplacementConfigElement shapeReplacementElement = createShapeReplacementElement(shapeReplacement);
				if (shapeReplacementElement != null) {
					config.appendShapeReplacementConfigElement(shapeReplacementElement);
				}
			} else if (shapeReplacementBlock instanceof AListShapeReplacementBlock) {
				for (PShapeReplacementDeclaration shapeReplacement : ((AListShapeReplacementBlock) shapeReplacementBlock).getShapeReplacementDeclaration()) {
					ShapeReplacementConfigElement shapeReplacementElement = createShapeReplacementElement(shapeReplacement);
					if (shapeReplacementElement != null) {
						config.appendShapeReplacementConfigElement(shapeReplacementElement);
					}
				}
			}
			ConfigurableExtension<ShapeReplacementExtensionRuntime<?>, ShapeReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeReplacementExtension("contextfree.shape.replacement.multi");
			ConfigurableExtensionReference<ShapeReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			ShapeReplacementConfigElement shapeReplacementElement = new ShapeReplacementConfigElement();
			shapeReplacementElement.setExtensionReference(reference);
			return shapeReplacementElement;
		}

		private ShapeReplacementConfigElement createShapeReplacementElement(PShapeReplacement shapeReplacement)	throws ExtensionNotFoundException {
			ConfigurableExtensionReference<ShapeReplacementExtensionConfig> reference = null;
			if (shapeReplacement instanceof ABasicShapeReplacement) {
				reference = createShapeReplacementExtensionReference((ABasicShapeReplacement) shapeReplacement);
			} else if (shapeReplacement instanceof AOrderedShapeReplacement) {
				reference = createShapeReplacementExtensionReference((AOrderedShapeReplacement) shapeReplacement);
			}
			ShapeReplacementConfigElement shapeReplacementElement = new ShapeReplacementConfigElement();
			shapeReplacementElement.setExtensionReference(reference);
			return shapeReplacementElement;
		}

		private ConfigurableExtensionReference<ShapeReplacementExtensionConfig> createShapeReplacementExtensionReference(ABasicShapeReplacement shapeReplacement)	throws ExtensionNotFoundException {
			SingleShapeReplacementConfig config = new SingleShapeReplacementConfig();
			config.setShape(((ABasicShapeReplacement) shapeReplacement).getString().getText());
			for (PShapeAdjustment shapeAdjustment : ((ABasicShapeReplacement) shapeReplacement).getShapeAdjustment()) {
				ShapeAdjustmentConfigElement shapeAdjustmentElement = createShapeAdjustmentElement(shapeAdjustment);
				config.appendShapeAdjustmentConfigElement(shapeAdjustmentElement);
			}
			ConfigurableExtension<ShapeReplacementExtensionRuntime<?>, ShapeReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeReplacementExtension("contextfree.shape.replacement.single");
			ConfigurableExtensionReference<ShapeReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeReplacementExtensionConfig> createShapeReplacementExtensionReference(AOrderedShapeReplacement shapeReplacement)	throws ExtensionNotFoundException {
			SingleShapeReplacementConfig config = new SingleShapeReplacementConfig();
			//TODO order
			config.setShape(((AOrderedShapeReplacement) shapeReplacement).getString().getText());
			for (PShapeAdjustment shapeAdjustment : ((AOrderedShapeReplacement) shapeReplacement).getShapeAdjustment()) {
				ShapeAdjustmentConfigElement shapeAdjustmentElement = createShapeAdjustmentElement(shapeAdjustment);
				config.appendShapeAdjustmentConfigElement(shapeAdjustmentElement);
			}
			ConfigurableExtension<ShapeReplacementExtensionRuntime<?>, ShapeReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeReplacementExtension("contextfree.shape.replacement.single");
			ConfigurableExtensionReference<ShapeReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ShapeAdjustmentConfigElement createShapeAdjustmentElement(PShapeAdjustment shapeAdjustment) throws ExtensionNotFoundException {
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = null;
			if (shapeAdjustment instanceof AColorShapeAdjustment) {
				reference = getShapeAdjustmentExtensionReference((AColorShapeAdjustment) shapeAdjustment);
			} else if (shapeAdjustment instanceof AGeometryShapeAdjustment) {
				reference = getShapeAdjustmentExtensionReference((AGeometryShapeAdjustment) shapeAdjustment);
			} else if (shapeAdjustment instanceof ASize3ShapeAdjustment) {
				reference = getShapeAdjustmentExtensionReference((ASize3ShapeAdjustment) shapeAdjustment);
			} else if (shapeAdjustment instanceof AZShapeAdjustment) {
				reference = getShapeAdjustmentExtensionReference((AZShapeAdjustment) shapeAdjustment);
			}
			ShapeAdjustmentConfigElement shapeAdjustmentElement = new ShapeAdjustmentConfigElement();
			shapeAdjustmentElement.setExtensionReference(reference);
			return shapeAdjustmentElement;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AColorShapeAdjustment shapeAdjustment) throws ExtensionNotFoundException {
			PColorAdjustment colorAdjustment = shapeAdjustment.getColorAdjustment();
			if (colorAdjustment instanceof ACurrentColorAdjustment) {
				return getShapeAdjustmentExtensionReference((ACurrentColorAdjustment) colorAdjustment);
			} else if (colorAdjustment instanceof ATargetColorAdjustment) {
				return getShapeAdjustmentExtensionReference((ATargetColorAdjustment) colorAdjustment);
			} else {
				return null;
			}
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ACurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			PCurrentColorAdjustment currentColorAdjustment = colorAdjustment.getCurrentColorAdjustment();
			if (currentColorAdjustment instanceof AAlphaCurrentColorAdjustment) {
				return getShapeAdjustmentExtensionReference((AAlphaCurrentColorAdjustment) currentColorAdjustment);
			} else if (currentColorAdjustment instanceof ABrightnessCurrentColorAdjustment) {
				return getShapeAdjustmentExtensionReference((ABrightnessCurrentColorAdjustment) currentColorAdjustment);
			} else if (currentColorAdjustment instanceof ASaturationCurrentColorAdjustment) {
				return getShapeAdjustmentExtensionReference((ASaturationCurrentColorAdjustment) currentColorAdjustment);
			} else if (currentColorAdjustment instanceof AHueCurrentColorAdjustment) {
				return getShapeAdjustmentExtensionReference((AHueCurrentColorAdjustment) currentColorAdjustment);
			} else {
				return null;
			}
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ATargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			PTargetColorAdjustment targetColorAdjustment = colorAdjustment.getTargetColorAdjustment();
			if (targetColorAdjustment instanceof AAlphaTargetColorAdjustment) {
				return getShapeAdjustmentExtensionReference((AAlphaTargetColorAdjustment) targetColorAdjustment);
			} else if (targetColorAdjustment instanceof ABrightnessTargetColorAdjustment) {
				return getShapeAdjustmentExtensionReference((ABrightnessTargetColorAdjustment) targetColorAdjustment);
			} else if (targetColorAdjustment instanceof ASaturationTargetColorAdjustment) {
				return getShapeAdjustmentExtensionReference((ASaturationTargetColorAdjustment) targetColorAdjustment);
			} else if (targetColorAdjustment instanceof AHueTargetColorAdjustment) {
				return getShapeAdjustmentExtensionReference((AHueTargetColorAdjustment) targetColorAdjustment);
			} else {
				return null;
			}
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AGeometryShapeAdjustment shapeAdjustment) throws ExtensionNotFoundException {
			PGeometryAdjustment geometryAdjustment = shapeAdjustment.getGeometryAdjustment();
			if (geometryAdjustment instanceof AFlipGeometryAdjustment) {
				return getShapeAdjustmentExtensionReference((AFlipGeometryAdjustment) geometryAdjustment);
			} else if (geometryAdjustment instanceof ARotateGeometryAdjustment) {
				return getShapeAdjustmentExtensionReference((ARotateGeometryAdjustment) geometryAdjustment);
			} else if (geometryAdjustment instanceof ASize2GeometryAdjustment) {
				return getShapeAdjustmentExtensionReference((ASize2GeometryAdjustment) geometryAdjustment);
			} else if (geometryAdjustment instanceof ASizeGeometryAdjustment) {
				return getShapeAdjustmentExtensionReference((ASizeGeometryAdjustment) geometryAdjustment);
			} else if (geometryAdjustment instanceof ASkewGeometryAdjustment) {
				return getShapeAdjustmentExtensionReference((ASkewGeometryAdjustment) geometryAdjustment);
			} else if (geometryAdjustment instanceof AXGeometryAdjustment) {
				return getShapeAdjustmentExtensionReference((AXGeometryAdjustment) geometryAdjustment);
			} else if (geometryAdjustment instanceof AYGeometryAdjustment) {
				return getShapeAdjustmentExtensionReference((AYGeometryAdjustment) geometryAdjustment);
			} else {
				return null;
			}
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AAlphaCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentAlphaShapeAdjustmentConfig config = new CurrentAlphaShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.currentAlpha");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AAlphaTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetHueShapeAdjustmentConfig config = new TargetHueShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.targetAlpha");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ABrightnessCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentBrightnessShapeAdjustmentConfig config = new CurrentBrightnessShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.currentBrightness");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ABrightnessTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetHueShapeAdjustmentConfig config = new TargetHueShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.targetBrightness");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ASaturationCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentSaturationShapeAdjustmentConfig config = new CurrentSaturationShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.currentSaturation");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ASaturationTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetHueShapeAdjustmentConfig config = new TargetHueShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.targetSaturation");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AHueCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentHueShapeAdjustmentConfig config = new CurrentHueShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.currentHue");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AHueTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetHueShapeAdjustmentConfig config = new TargetHueShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.targetHue");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AXGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			XShapeAdjustmentConfig config = new XShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.x");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AYGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			YShapeAdjustmentConfig config = new YShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.y");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ASizeGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			SizeShapeAdjustmentConfig config = new SizeShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.size");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ASize2GeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			Size2ShapeAdjustmentConfig config = new Size2ShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.size2");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ASkewGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			SkewShapeAdjustmentConfig config = new SkewShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.skew");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AFlipGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			FlipShapeAdjustmentConfig config = new FlipShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.flip");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ARotateGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			RotateShapeAdjustmentConfig config = new RotateShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.rotate");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ASize3ShapeAdjustment shapeAdjustment) throws ExtensionNotFoundException {
			Size3ShapeAdjustmentConfig config = new Size3ShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.size3");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AZShapeAdjustment shapeAdjustment) throws ExtensionNotFoundException {
			ZShapeAdjustmentConfig config = new ZShapeAdjustmentConfig(); //TODO
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.z");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
	}
}
