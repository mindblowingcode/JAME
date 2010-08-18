package net.sf.jame.contextfree.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sf.jame.contextfree.ContextFreeConfig;
import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.CFDGConfigElement;
import net.sf.jame.contextfree.cfdg.analysis.DepthFirstAdapter;
import net.sf.jame.contextfree.cfdg.figure.FigureConfigElement;
import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.cfdg.lexer.Lexer;
import net.sf.jame.contextfree.cfdg.lexer.LexerException;
import net.sf.jame.contextfree.cfdg.node.*;
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
import net.sf.jame.contextfree.extensions.pathReplacement.QuadRelPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.QuadToPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.SmoothCurveRelPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.SmoothCurveToPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.SmoothQuadRelPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.SmoothQuadToPathReplacementConfig;
import net.sf.jame.contextfree.extensions.pathReplacement.StrokePathReplacementConfig;
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
import net.sf.jame.contextfree.extensions.shapeAdjustment.TargetAlphaShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.TargetBrightnessShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.TargetHueShapeAdjustmentConfig;
import net.sf.jame.contextfree.extensions.shapeAdjustment.TargetSaturationShapeAdjustmentConfig;
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

public class ContextFreeParser {
	public ContextFreeConfig parseConfig(File file) throws ContextFreeParserException {
		try {
			return parseConfig(new File(file.getParent()), new FileReader(file));
		} catch (FileNotFoundException e) {
			throw new ContextFreeParserException(e);
		}
	}
	
	public ContextFreeConfig parseConfig(File baseDir, String text) throws ContextFreeParserException {
		return parseConfig(baseDir, new StringReader(text));
	}

	public ContextFreeConfig parseConfig(File baseDir, Reader reader) throws ContextFreeParserException {
		try {
			Parser parser = new Parser(new Lexer(new PushbackReader(reader)));
			ContextFreeConfig config = new ContextFreeConfig();
			CFInterpreter interpreter = new CFInterpreter(baseDir, config);
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
		private boolean startshapeFound;
		private boolean backgroundFound;
		private boolean tileFound;
		private boolean sizeFound;
		private File baseDir;
		
		public CFInterpreter(File baseDir, ContextFreeConfig config) {
			this.baseDir = baseDir;
			this.config = config;
		}

		/**
		 * 
		 */
		@Override
		public void inACfdg(ACfdg node) {
			super.inACfdg(node);
			CFDGConfigElement cfdgElement = new CFDGConfigElement();
			try {
				FigureConfigElement triangleFigureElement = new FigureConfigElement();
				cfdgElement.appendFigureConfigElement(triangleFigureElement);
				ConfigurableExtensionReference<FigureExtensionConfig> triangleReference = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.triangle").createConfigurableExtensionReference();
				triangleFigureElement.setExtensionReference(triangleReference);
				FigureConfigElement squareFigureElement = new FigureConfigElement();
				cfdgElement.appendFigureConfigElement(squareFigureElement);
				ConfigurableExtensionReference<FigureExtensionConfig> squareReference = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.square").createConfigurableExtensionReference();
				squareFigureElement.setExtensionReference(squareReference);
				FigureConfigElement circleFigureElement = new FigureConfigElement();
				cfdgElement.appendFigureConfigElement(circleFigureElement);
				ConfigurableExtensionReference<FigureExtensionConfig> circleReference = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.circle").createConfigurableExtensionReference();
				circleFigureElement.setExtensionReference(circleReference);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			config.setCFDG(cfdgElement);
		}

		/**
		 * 
		 */
		@Override
		public void inARuleDeclaration(ARuleDeclaration node) {
			super.inARuleDeclaration(node);
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
			if (!startshapeFound) {
				config.getCFDG().setStartshape(node.getString().getText());
				startshapeFound = true;
			}
		}

		/**
		 * 
		 */
		@Override
		public void inAIncludeDeclaration(AIncludeDeclaration node) {
			super.inAIncludeDeclaration(node);
			try {
				String path = node.getFilename().getText();
				if (path.startsWith("\"") && path.endsWith("\"")) {
					path = path.substring(1, path.length() - 1);
				}
				ContextFreeConfig tmpConfig = parseConfig(path.startsWith("/") ? new File(path) : new File(baseDir, path));
				for (int i = 0; i < tmpConfig.getCFDG().getFigureConfigElementCount(); i++) {
					config.getCFDG().appendFigureConfigElement(tmpConfig.getCFDG().getFigureConfigElement(i));
				}
//				if (!startshapeFound) {
//					if (tmpConfig.getCFDG().getStartshape() != null) {
//						config.getCFDG().setStartshape(tmpConfig.getCFDG().getStartshape());
//						startshapeFound = true;
//					}
//				}
				tmpConfig.dispose();
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
			if (!backgroundFound) {
//				config.getCFDG().setBackground(new Color32bit(0xFFFFFFFF));
				for (PBackgroundAdjustment adjustment : node.getBackgroundAdjustment()) {
					if (adjustment instanceof AHueBackgroundAdjustment) {
						float[] hsbvals = new float[3];
						Colors.toHSB(config.getCFDG().getBackground().getARGB(), hsbvals);
						float value = evaluateExpression(((AHueBackgroundAdjustment) adjustment).getExpression()) / 360;
						hsbvals[0] += value;
						if (hsbvals[0] < 0) hsbvals[0] = 0; 
						if (hsbvals[0] > 1) hsbvals[0] = 1; 
						config.getCFDG().setBackground(new Color32bit(Colors.toRGB(config.getCFDG().getBackground().getAlpha(), hsbvals)));
					} else if (adjustment instanceof ASaturationBackgroundAdjustment) {
						float[] hsbvals = new float[3];
						Colors.toHSB(config.getCFDG().getBackground().getARGB(), hsbvals);
						float value = evaluateExpression(((ASaturationBackgroundAdjustment) adjustment).getExpression());
						hsbvals[1] += value;
						if (hsbvals[1] < 0) hsbvals[1] = 0; 
						if (hsbvals[1] > 1) hsbvals[1] = 1; 
						config.getCFDG().setBackground(new Color32bit(Colors.toRGB(config.getCFDG().getBackground().getAlpha(), hsbvals)));
					} else if (adjustment instanceof ABrightnessBackgroundAdjustment) {
						float[] hsbvals = new float[3];
						Colors.toHSB(config.getCFDG().getBackground().getARGB(), hsbvals);
						float value = evaluateExpression(((ABrightnessBackgroundAdjustment) adjustment).getExpression());
						hsbvals[2] += value;
						if (hsbvals[2] < 0) hsbvals[2] = 0; 
						if (hsbvals[2] > 1) hsbvals[2] = 1; 
						config.getCFDG().setBackground(new Color32bit(Colors.toRGB(config.getCFDG().getBackground().getAlpha(), hsbvals)));
					} else if (adjustment instanceof AAlphaBackgroundAdjustment) {
						float[] hsbvals = new float[4];
						Colors.toHSB(config.getCFDG().getBackground().getARGB(), hsbvals);
						hsbvals[3] = config.getCFDG().getBackground().getAlpha() / 255f;
						float value = evaluateExpression(((AAlphaBackgroundAdjustment) adjustment).getExpression());
						hsbvals[3] += value;
						if (hsbvals[3] < 0) hsbvals[3] = 0; 
						if (hsbvals[3] > 1) hsbvals[3] = 1; 
						config.getCFDG().setBackground(new Color32bit(Colors.toRGB((int) Math.rint(hsbvals[3] * 255), hsbvals)));
					}
				}
				backgroundFound = true;
			}
		}

		/**
		 * 
		 */
		@Override
		public void inATileDeclaration(ATileDeclaration node) {
			super.inATileDeclaration(node);
			if (!tileFound) {
				config.getCFDG().setUseTile(true);
				for (PTileAdjustment adjustment : node.getTileAdjustment()) {
					if (adjustment instanceof ATileAdjustment) {
						PFirstExpression firstExpression = ((ATileAdjustment) adjustment).getFirstExpression();
						if (firstExpression != null && firstExpression instanceof AFirstExpression) {
							float value = evaluateExpression(((AFirstExpression) firstExpression).getExtendedExpression());
							config.getCFDG().setTileWidth(value);
						}
						PSecondExpression secondExpression = ((ATileAdjustment) adjustment).getSecondExpression();
						if (secondExpression != null && secondExpression instanceof ASecondExpression) {
							float value = evaluateExpression(((ASecondExpression) secondExpression).getExtendedExpression());
							config.getCFDG().setTileHeight(value);
						}
					}
				}
				tileFound = true;
			}
		}

		/**
		 * 
		 */
		@Override
		public void inASizeDeclaration(ASizeDeclaration node) {
			super.inASizeDeclaration(node);
			if (!sizeFound) {
				config.getCFDG().setUseSize(true);
				for (PSizeAdjustment adjustment : node.getSizeAdjustment()) {
					if (adjustment instanceof ASizeSizeAdjustment) {
						PFirstExpression firstExpression = ((ASizeSizeAdjustment) adjustment).getFirstExpression();
						if (firstExpression instanceof AFirstExpression) {
							float value = evaluateExpression(((AFirstExpression) firstExpression).getExtendedExpression());
							config.getCFDG().setWidth(value);
						}
						PSecondExpression secondExpression = ((ASizeSizeAdjustment) adjustment).getSecondExpression();
						if (secondExpression instanceof ASecondExpression) {
							float value = evaluateExpression(((ASecondExpression) secondExpression).getExtendedExpression());
							config.getCFDG().setHeight(value);
						}
					} else if (adjustment instanceof AXSizeAdjustment) {
						float value = evaluateExpression(((AXSizeAdjustment) adjustment).getExpression());
						config.getCFDG().setX(value);
					} else if (adjustment instanceof AYSizeAdjustment) {
						float value = evaluateExpression(((AYSizeAdjustment) adjustment).getExpression());
						config.getCFDG().setY(value);
					}
				}
				sizeFound = true;
			}
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
		
		public float evaluateExpression(PFirstExpression expression) {
			if (expression instanceof AFirstExpression) {
				return evaluateExpression(((AFirstExpression) expression).getExtendedExpression());
			}
			return 0;
		}
		
		public float evaluateExpression(PSecondExpression expression) {
			if (expression instanceof ASecondExpression) {
				return evaluateExpression(((ASecondExpression) expression).getExtendedExpression());
			}
			return 0;
		}
		
		public float evaluateExpression(PThirdExpression expression) {
			if (expression instanceof AThirdExpression) {
				return evaluateExpression(((AThirdExpression) expression).getExtendedExpression());
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
//			PathReplacementConfigElement pathReplacementElement = createFlushPathReplacementElement();
//			config.appendPathReplacementConfigElement(pathReplacementElement);
			ConfigurableExtension<FigureExtensionRuntime<?>, FigureExtensionConfig> extension = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.path");
			ConfigurableExtensionReference<FigureExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			FigureConfigElement figureElement = new FigureConfigElement();
			figureElement.setExtensionReference(reference);
			return figureElement;
		}

//		private PathReplacementConfigElement createFlushPathReplacementElement() throws ExtensionNotFoundException {
//			FlushPathReplacementConfig config = new FlushPathReplacementConfig();
//			PathReplacementConfigElement pathReplacementElement = new PathReplacementConfigElement();
//			ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.command.flush");
//			ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
//			pathReplacementElement.setExtensionReference(reference);
//			return pathReplacementElement;
//		}

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
			for (PPathAdjustment pathAdjustment : pathReplacementDeclaration.getPathAdjustment()) {
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

		private PathAdjustmentConfigElement createPathAdjustmentElement(PPathAdjustment pathAdjustment) throws ExtensionNotFoundException {
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = null;
			if (pathAdjustment instanceof AFlipPathAdjustment) {
				reference = getPathAdjustmentExtensionReference((AFlipPathAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof ARotatePathAdjustment) {
				reference = getPathAdjustmentExtensionReference((ARotatePathAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof ASize2PathAdjustment) {
				reference = getPathAdjustmentExtensionReference((ASize2PathAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof ASizePathAdjustment) {
				reference = getPathAdjustmentExtensionReference((ASizePathAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof ASkewPathAdjustment) {
				reference = getPathAdjustmentExtensionReference((ASkewPathAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof AXPathAdjustment) {
				reference = getPathAdjustmentExtensionReference((AXPathAdjustment) pathAdjustment);
			} else if (pathAdjustment instanceof AYPathAdjustment) {
				reference = getPathAdjustmentExtensionReference((AYPathAdjustment) pathAdjustment);
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
			CurrentAlphaPathAdjustmentConfig config = new CurrentAlphaPathAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()));
			config.setTarget(colorAdjustment.getBar() != null);
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.currentAlpha");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AAlphaTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetAlphaPathAdjustmentConfig config = new TargetAlphaPathAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()));
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.targetAlpha");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ABrightnessCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentBrightnessPathAdjustmentConfig config = new CurrentBrightnessPathAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()));
			config.setTarget(colorAdjustment.getBar() != null);
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.currentBrightness");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ABrightnessTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetBrightnessPathAdjustmentConfig config = new TargetBrightnessPathAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()));
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.targetBrightness");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ASaturationCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentSaturationPathAdjustmentConfig config = new CurrentSaturationPathAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()));
			config.setTarget(colorAdjustment.getBar() != null);
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.currentSaturation");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ASaturationTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetSaturationPathAdjustmentConfig config = new TargetSaturationPathAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()));
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.targetSaturation");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AHueCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentHuePathAdjustmentConfig config = new CurrentHuePathAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()) / 360);
			config.setTarget(colorAdjustment.getBar() != null);
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.currentHue");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AHueTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetHuePathAdjustmentConfig config = new TargetHuePathAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()) / 360);
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.color.targetHue");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AXPathAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			XPathAdjustmentConfig config = new XPathAdjustmentConfig();
			config.setValue(evaluateExpression(geometryAdjustment.getExpression()));
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.x");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AYPathAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			YPathAdjustmentConfig config = new YPathAdjustmentConfig();
			config.setValue(evaluateExpression(geometryAdjustment.getExpression()));
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.y");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ASizePathAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			SizePathAdjustmentConfig config = new SizePathAdjustmentConfig();
			config.setScale(evaluateExpression(geometryAdjustment.getExpression()));
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.size");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ASize2PathAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			Size2PathAdjustmentConfig config = new Size2PathAdjustmentConfig();
			config.setScaleX(evaluateExpression(geometryAdjustment.getFirstExpression()));
			config.setScaleY(evaluateExpression(geometryAdjustment.getSecondExpression()));
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.size2");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ASkewPathAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			SkewPathAdjustmentConfig config = new SkewPathAdjustmentConfig();
			config.setShearX((float) (Math.PI * evaluateExpression(geometryAdjustment.getFirstExpression())) / 180f);
			config.setShearY((float) (Math.PI * evaluateExpression(geometryAdjustment.getSecondExpression())) / 180f);
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.skew");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(AFlipPathAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			FlipPathAdjustmentConfig config = new FlipPathAdjustmentConfig();
			config.setAngle((float) (Math.PI * evaluateExpression(geometryAdjustment.getExpression())) / 180f);
			ConfigurableExtension<PathAdjustmentExtensionRuntime<?>, PathAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathAdjustmentExtension("contextfree.path.adjustment.geometry.flip");
			ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getPathAdjustmentExtensionReference(ARotatePathAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			RotatePathAdjustmentConfig config = new RotatePathAdjustmentConfig();
			config.setAngle((float) (Math.PI * evaluateExpression(geometryAdjustment.getExpression())) / 180f);
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
				for (POperationParameter parameter : pathOperation.getOperationParameter()) {
					if (parameter instanceof AXOperationParameter) {
						config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof AYOperationParameter) {
						config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
					}
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.lineto");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("ARCTO".equals(operation)) {
				boolean elliptical = false;
				for (POperationParameter parameter : pathOperation.getOperationParameter()) {
					if (parameter instanceof ARxOperationParameter) {
						elliptical = true;
					} else if (parameter instanceof ARyOperationParameter) {
						elliptical = true;
					}
				}
				ArcToPathReplacementConfig config = new ArcToPathReplacementConfig();
				if (!elliptical) {
					config.setR(1f);
				}
				for (POperationParameter parameter : pathOperation.getOperationParameter()) {
					if (parameter instanceof AXOperationParameter) {
						config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof AYOperationParameter) {
						config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof ARotateOperationParameter) {
						config.setR(evaluateExpression(((ARotateOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof ARxOperationParameter) {
						config.setRx(evaluateExpression(((ARxOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof ARyOperationParameter) {
						config.setRy(evaluateExpression(((ARyOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof AParametersOperationParameter) {
						String param = ((AParametersOperationParameter) parameter).getString().getText();
						if ("cw".equals(param)) {
							config.setSweep(true);
						} else if ("large".equals(param)) {
							config.setLarge(true);
						}
					}
				}
				if (!elliptical) {
					config.setRx(config.getR());
					config.setRy(config.getR());
					config.setR(0f);
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.arcto");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("CURVETO".equals(operation)) {
				boolean cubic = false;
				boolean smooth = true;
				for (POperationParameter parameter : pathOperation.getOperationParameter()) {
					if (parameter instanceof AX1OperationParameter) {
						smooth = false;
					} else if (parameter instanceof AY1OperationParameter) {
						smooth = false;
					} else if (parameter instanceof AX2OperationParameter) {
						cubic = true;
					} else if (parameter instanceof AY2OperationParameter) {
						cubic = true;
					}
				}
				if (cubic) {
					if (smooth) {
						SmoothCurveToPathReplacementConfig config = new SmoothCurveToPathReplacementConfig();
						for (POperationParameter parameter : pathOperation.getOperationParameter()) {
							if (parameter instanceof AXOperationParameter) {
								config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AYOperationParameter) {
								config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AX2OperationParameter) {
								config.setX2(evaluateExpression(((AX2OperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AY2OperationParameter) {
								config.setY2(evaluateExpression(((AY2OperationParameter) parameter).getExpression()));
							}
						}
						ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.smoothcurveto");
						ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
						return reference;
					} else {
						CurveToPathReplacementConfig config = new CurveToPathReplacementConfig();
						for (POperationParameter parameter : pathOperation.getOperationParameter()) {
							if (parameter instanceof AXOperationParameter) {
								config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AYOperationParameter) {
								config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AX1OperationParameter) {
								config.setX1(evaluateExpression(((AX1OperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AY1OperationParameter) {
								config.setY1(evaluateExpression(((AY1OperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AX2OperationParameter) {
								config.setX2(evaluateExpression(((AX2OperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AY2OperationParameter) {
								config.setY2(evaluateExpression(((AY2OperationParameter) parameter).getExpression()));
							}
						}
						ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.curveto");
						ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
						return reference;
					}
				} else {
					if (smooth) {
						SmoothQuadToPathReplacementConfig config = new SmoothQuadToPathReplacementConfig();
						for (POperationParameter parameter : pathOperation.getOperationParameter()) {
							if (parameter instanceof AXOperationParameter) {
								config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AYOperationParameter) {
								config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
							}
						}
						ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.smoothquadto");
						ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
						return reference;
					} else {
						QuadToPathReplacementConfig config = new QuadToPathReplacementConfig();
						for (POperationParameter parameter : pathOperation.getOperationParameter()) {
							if (parameter instanceof AXOperationParameter) {
								config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AYOperationParameter) {
								config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AX1OperationParameter) {
								config.setX1(evaluateExpression(((AX1OperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AY1OperationParameter) {
								config.setY1(evaluateExpression(((AY1OperationParameter) parameter).getExpression()));
							}
						}
						ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.quadto");
						ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
						return reference;
					}
				}
			} else if ("MOVETO".equals(operation)) {
				MoveToPathReplacementConfig config = new MoveToPathReplacementConfig();
				for (POperationParameter parameter : pathOperation.getOperationParameter()) {
					if (parameter instanceof AXOperationParameter) {
						config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof AYOperationParameter) {
						config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
					}
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.moveto");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("LINEREL".equals(operation)) {
				LineRelPathReplacementConfig config = new LineRelPathReplacementConfig();
				for (POperationParameter parameter : pathOperation.getOperationParameter()) {
					if (parameter instanceof AXOperationParameter) {
						config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof AYOperationParameter) {
						config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
					}
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.linerel");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("ARCREL".equals(operation)) {
				boolean elliptical = false;
				for (POperationParameter parameter : pathOperation.getOperationParameter()) {
					if (parameter instanceof ARxOperationParameter) {
						elliptical = true;
					} else if (parameter instanceof ARyOperationParameter) {
						elliptical = true;
					}
				}
				ArcToPathReplacementConfig config = new ArcToPathReplacementConfig();
				if (!elliptical) {
					config.setR(1f);
				}
				for (POperationParameter parameter : pathOperation.getOperationParameter()) {
					if (parameter instanceof AXOperationParameter) {
						config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof AYOperationParameter) {
						config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof ARotateOperationParameter) {
						config.setR(evaluateExpression(((ARotateOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof ARxOperationParameter) {
						config.setRx(evaluateExpression(((ARxOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof ARyOperationParameter) {
						config.setRy(evaluateExpression(((ARyOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof AParametersOperationParameter) {
						String param = ((AParametersOperationParameter) parameter).getString().getText();
						if ("cw".equals(param)) {
							config.setSweep(true);
						} else if ("large".equals(param)) {
							config.setLarge(true);
						}
					}
				}
				if (!elliptical) {
					config.setRx(config.getR());
					config.setRy(config.getR());
					config.setR(0f);
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.arcrel");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("CURVEREL".equals(operation)) {
				boolean cubic = false;
				boolean smooth = true;
				for (POperationParameter parameter : pathOperation.getOperationParameter()) {
					if (parameter instanceof AX1OperationParameter) {
						smooth = false;
					} else if (parameter instanceof AY1OperationParameter) {
						smooth = false;
					} else if (parameter instanceof AX2OperationParameter) {
						cubic = true;
					} else if (parameter instanceof AY2OperationParameter) {
						cubic = true;
					}
				}
				if (cubic) {
					if (smooth) {
						SmoothCurveRelPathReplacementConfig config = new SmoothCurveRelPathReplacementConfig();
						for (POperationParameter parameter : pathOperation.getOperationParameter()) {
							if (parameter instanceof AXOperationParameter) {
								config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AYOperationParameter) {
								config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AX2OperationParameter) {
								config.setX2(evaluateExpression(((AX2OperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AY2OperationParameter) {
								config.setY2(evaluateExpression(((AY2OperationParameter) parameter).getExpression()));
							}
						}
						ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.smoothcurverel");
						ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
						return reference;
					} else {
						CurveRelPathReplacementConfig config = new CurveRelPathReplacementConfig();
						for (POperationParameter parameter : pathOperation.getOperationParameter()) {
							if (parameter instanceof AXOperationParameter) {
								config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AYOperationParameter) {
								config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AX1OperationParameter) {
								config.setX1(evaluateExpression(((AX1OperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AY1OperationParameter) {
								config.setY1(evaluateExpression(((AY1OperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AX2OperationParameter) {
								config.setX2(evaluateExpression(((AX2OperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AY2OperationParameter) {
								config.setY2(evaluateExpression(((AY2OperationParameter) parameter).getExpression()));
							}
						}
						ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.curverel");
						ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
						return reference;
					}
				} else {
					if (smooth) {
						SmoothQuadRelPathReplacementConfig config = new SmoothQuadRelPathReplacementConfig();
						for (POperationParameter parameter : pathOperation.getOperationParameter()) {
							if (parameter instanceof AXOperationParameter) {
								config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AYOperationParameter) {
								config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
							}
						}
						ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.smoothquadrel");
						ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
						return reference;
					} else {
						QuadRelPathReplacementConfig config = new QuadRelPathReplacementConfig();
						for (POperationParameter parameter : pathOperation.getOperationParameter()) {
							if (parameter instanceof AXOperationParameter) {
								config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AYOperationParameter) {
								config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AX1OperationParameter) {
								config.setX1(evaluateExpression(((AX1OperationParameter) parameter).getExpression()));
							} else if (parameter instanceof AY1OperationParameter) {
								config.setY1(evaluateExpression(((AY1OperationParameter) parameter).getExpression()));
							}
						}
						ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.quadrel");
						ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
						return reference;
					}
				}
			} else if ("MOVEREL".equals(operation)) {
				MoveRelPathReplacementConfig config = new MoveRelPathReplacementConfig();
				for (POperationParameter parameter : pathOperation.getOperationParameter()) {
					if (parameter instanceof AXOperationParameter) {
						config.setX(evaluateExpression(((AXOperationParameter) parameter).getExpression()));
					} else if (parameter instanceof AYOperationParameter) {
						config.setY(evaluateExpression(((AYOperationParameter) parameter).getExpression()));
					}
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.operation.moverel");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("CLOSEPOLY".equals(operation)) {
				ClosePolyPathReplacementConfig config = new ClosePolyPathReplacementConfig();
				for (POperationParameter parameter : pathOperation.getOperationParameter()) {
					if (parameter instanceof AParametersOperationParameter) {
						String param = ((AParametersOperationParameter) parameter).getString().getText();
						if ("align".equals(param)) {
							config.setAlign(true);
						}
					}
				}
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
						PathAdjustmentConfigElement pathAdjustmentElement = createPathAdjustmentElement(((AColorCommandParameter) commadParameter).getColorAdjustment());
						if (pathAdjustmentElement != null) {
							config.appendPathAdjustmentConfigElement(pathAdjustmentElement);
						}
					} else if (commadParameter instanceof AGeometryCommandParameter) {
						PathAdjustmentConfigElement pathAdjustmentElement = createPathAdjustmentElement(((AGeometryCommandParameter) commadParameter).getPathAdjustment());
						if (pathAdjustmentElement != null) {
							config.appendPathAdjustmentConfigElement(pathAdjustmentElement);
						}
					} else if (commadParameter instanceof AParametersCommandParameter) {
						String param = ((AParametersCommandParameter) commadParameter).getString().getText();
						if ("evenodd".equals(param)) {
							config.setRule("even-odd");
						} else {
							config.setRule("non-zero");
						}
					}
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.command.fill");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("STROKE".equals(command)) {
				StrokePathReplacementConfig config = new StrokePathReplacementConfig();
				for (PCommandParameter commadParameter : pathCommand.getCommandParameter()) {
					if (commadParameter instanceof AColorCommandParameter) {
						PathAdjustmentConfigElement pathAdjustmentElement = createPathAdjustmentElement(((AColorCommandParameter) commadParameter).getColorAdjustment());
						if (pathAdjustmentElement != null) {
							config.appendPathAdjustmentConfigElement(pathAdjustmentElement);
						}
					} else if (commadParameter instanceof AGeometryCommandParameter) {
						PathAdjustmentConfigElement pathAdjustmentElement = createPathAdjustmentElement(((AGeometryCommandParameter) commadParameter).getPathAdjustment());
						if (pathAdjustmentElement != null) {
							config.appendPathAdjustmentConfigElement(pathAdjustmentElement);
						}
					} else if (commadParameter instanceof AParametersCommandParameter) {
						String param = ((AParametersCommandParameter) commadParameter).getString().getText();
						if (param.equals("buttcap")) {
							config.setCap("buttcap");
						} else if (param.equals("roundcap")) {
							config.setCap("roundcap");
						} else if (param.equals("squarecap")) {
							config.setCap("roundcap");
						} else if (param.equals("miterjoin")) {
							config.setJoin("miterjoin");
						} else if (param.equals("roundjoin")) {
							config.setJoin("roundjoin");
						} else if (param.equals("beveljoin")) {
							config.setJoin("beveljoin");
						}
					} else if (commadParameter instanceof AStrokeCommandParameter) {
						config.setWidth(evaluateExpression(((AStrokeCommandParameter) commadParameter).getExpression()));
					}
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.command.stroke");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			}
			return null;
		}

		private void dropSameParameters(List<PCommandParameter> commandParameters) {
			AFlipPathAdjustment lastAFlipPathAdjustment = null;
			ARotatePathAdjustment lastARotatePathAdjustment = null;
			ASize2PathAdjustment lastASize2PathAdjustment = null;
			ASizePathAdjustment lastASizePathAdjustment = null;
			ASkewPathAdjustment lastASkewPathAdjustment = null;
			AXPathAdjustment lastAXPathAdjustment = null;
			AYPathAdjustment lastAYPathAdjustment = null;
			for (int i = commandParameters.size() - 1; i >= 0; i--) {
				PCommandParameter commandParameter = commandParameters.get(i);
				if (commandParameter instanceof AGeometryCommandParameter) {
					PPathAdjustment pathAdjustment = ((AGeometryCommandParameter) commandParameter).getPathAdjustment();
					if (pathAdjustment instanceof AFlipPathAdjustment) {
						if (lastAFlipPathAdjustment != null) {
							commandParameters.remove(commandParameter);
						} else {
							lastAFlipPathAdjustment = (AFlipPathAdjustment) pathAdjustment;
						}
					} else if (pathAdjustment instanceof ARotatePathAdjustment) {
						if (lastARotatePathAdjustment != null) {
							commandParameters.remove(commandParameter);
						} else {
							lastARotatePathAdjustment = (ARotatePathAdjustment) pathAdjustment;
						}
					} else if (pathAdjustment instanceof ASize2PathAdjustment) {
						if (lastASize2PathAdjustment != null) {
							commandParameters.remove(commandParameter);
						} else {
							lastASize2PathAdjustment = (ASize2PathAdjustment) pathAdjustment;
						}
					} else if (pathAdjustment instanceof ASizePathAdjustment) {
						if (lastASizePathAdjustment != null) {
							commandParameters.remove(commandParameter);
						} else {
							lastASizePathAdjustment = (ASizePathAdjustment) pathAdjustment;
						}
					} else if (pathAdjustment instanceof ASkewPathAdjustment) {
						if (lastASkewPathAdjustment != null) {
							commandParameters.remove(commandParameter);
						} else {
							lastASkewPathAdjustment = (ASkewPathAdjustment) pathAdjustment;
						}
					} else if (pathAdjustment instanceof AXPathAdjustment) {
						if (lastAXPathAdjustment != null) {
							commandParameters.remove(commandParameter);
						} else {
							lastAXPathAdjustment = (AXPathAdjustment) pathAdjustment;
						}
					} else if (pathAdjustment instanceof AYPathAdjustment) {
						if (lastAYPathAdjustment != null) {
							commandParameters.remove(commandParameter);
						} else {
							lastAYPathAdjustment = (AYPathAdjustment) pathAdjustment;
						}
					}
				}
			}
		}

		private ConfigurableExtensionReference<PathReplacementExtensionConfig> createPathReplacementExtensionReference(AOrderedPathCommand pathCommand) throws ExtensionNotFoundException {
			String command = pathCommand.getCommand().getText();
			dropSameParameters(pathCommand.getCommandParameter());
			Collections.sort(pathCommand.getCommandParameter(), new PCommandParameterComparator());
			if ("FILL".equals(command)) {
				FillPathReplacementConfig config = new FillPathReplacementConfig();
				for (PCommandParameter commadParameter : pathCommand.getCommandParameter()) {
					if (commadParameter instanceof AColorCommandParameter) {
						PathAdjustmentConfigElement pathAdjustmentElement = createPathAdjustmentElement(((AColorCommandParameter) commadParameter).getColorAdjustment());
						if (pathAdjustmentElement != null) {
							config.appendPathAdjustmentConfigElement(pathAdjustmentElement);
						}
					} else if (commadParameter instanceof AGeometryCommandParameter) {
						PathAdjustmentConfigElement pathAdjustmentElement = createPathAdjustmentElement(((AGeometryCommandParameter) commadParameter).getPathAdjustment());
						if (pathAdjustmentElement != null) {
							config.appendPathAdjustmentConfigElement(pathAdjustmentElement);
						}
					} else if (commadParameter instanceof AParametersCommandParameter) {
						config.setRule(((AParametersCommandParameter) commadParameter).getString().getText());
					}
				}
				ConfigurableExtension<PathReplacementExtensionRuntime<?>, PathReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getPathReplacementExtension("contextfree.path.replacement.command.fill");
				ConfigurableExtensionReference<PathReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
				return reference; 
			} else if ("STROKE".equals(command)) {
				StrokePathReplacementConfig config = new StrokePathReplacementConfig();
				for (PCommandParameter commadParameter : pathCommand.getCommandParameter()) {
					if (commadParameter instanceof AColorCommandParameter) {
						PathAdjustmentConfigElement pathAdjustmentElement = createPathAdjustmentElement(((AColorCommandParameter) commadParameter).getColorAdjustment());
						if (pathAdjustmentElement != null) {
							config.appendPathAdjustmentConfigElement(pathAdjustmentElement);
						}
					} else if (commadParameter instanceof AGeometryCommandParameter) {
						PathAdjustmentConfigElement pathAdjustmentElement = createPathAdjustmentElement(((AGeometryCommandParameter) commadParameter).getPathAdjustment());
						if (pathAdjustmentElement != null) {
							config.appendPathAdjustmentConfigElement(pathAdjustmentElement);
						}
					} else if (commadParameter instanceof AParametersCommandParameter) {
						String param = ((AParametersCommandParameter) commadParameter).getString().getText();
						if (param.equals("buttcap")) {
							config.setCap("buttcap");
						} else if (param.equals("roundcap")) {
							config.setCap("roundcap");
						} else if (param.equals("squarecap")) {
							config.setCap("roundcap");
						} else if (param.equals("miterjoin")) {
							config.setJoin("miterjoin");
						} else if (param.equals("roundjoin")) {
							config.setJoin("roundjoin");
						} else if (param.equals("beveljoin")) {
							config.setJoin("beveljoin");
						}
					} else if (commadParameter instanceof AStrokeCommandParameter) {
						config.setWidth(evaluateExpression(((AStrokeCommandParameter) commadParameter).getExpression()));
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
			if (ruleDeclaration.getNumber() != null) {
				config.setProbability(Float.valueOf(ruleDeclaration.getNumber().getText()));
			}
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
			for (PShapeAdjustment shapeAdjustment : shapeReplacement.getShapeAdjustment()) {
				ShapeAdjustmentConfigElement shapeAdjustmentElement = createShapeAdjustmentElement(shapeAdjustment);
				config.appendShapeAdjustmentConfigElement(shapeAdjustmentElement);
			}
			ConfigurableExtension<ShapeReplacementExtensionRuntime<?>, ShapeReplacementExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeReplacementExtension("contextfree.shape.replacement.single");
			ConfigurableExtensionReference<ShapeReplacementExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private void dropSameAdjustments(List<PShapeAdjustment> shapeAdjustments) {
			AFlipGeometryAdjustment lastAFlipGeometryAdjustment = null;
			ARotateGeometryAdjustment lastARotateGeometryAdjustment = null;
			ASize2GeometryAdjustment lastASize2GeometryAdjustment = null;
			ASizeGeometryAdjustment lastASizeGeometryAdjustment = null;
			ASkewGeometryAdjustment lastASkewGeometryAdjustment = null;
			AXGeometryAdjustment lastAXGeometryAdjustment = null;
			AYGeometryAdjustment lastAYGeometryAdjustment = null;
			for (int i = shapeAdjustments.size() - 1; i >= 0; i--) {
				PShapeAdjustment shapeAdjustment = shapeAdjustments.get(i);
				if (shapeAdjustment instanceof AGeometryShapeAdjustment) {
					PGeometryAdjustment geometryAdjustment = ((AGeometryShapeAdjustment) shapeAdjustment).getGeometryAdjustment();
					if (geometryAdjustment instanceof AFlipGeometryAdjustment) {
						if (lastAFlipGeometryAdjustment != null) {
							shapeAdjustments.remove(shapeAdjustment);
						} else {
							lastAFlipGeometryAdjustment = (AFlipGeometryAdjustment) geometryAdjustment;
						}
					} else if (geometryAdjustment instanceof ARotateGeometryAdjustment) {
						if (lastARotateGeometryAdjustment != null) {
							shapeAdjustments.remove(shapeAdjustment);
						} else {
							lastARotateGeometryAdjustment = (ARotateGeometryAdjustment) geometryAdjustment;
						}
					} else if (geometryAdjustment instanceof ASize2GeometryAdjustment) {
						if (lastASize2GeometryAdjustment != null) {
							shapeAdjustments.remove(shapeAdjustment);
						} else {
							lastASize2GeometryAdjustment = (ASize2GeometryAdjustment) geometryAdjustment;
						}
					} else if (geometryAdjustment instanceof ASizeGeometryAdjustment) {
						if (lastASizeGeometryAdjustment != null) {
							shapeAdjustments.remove(shapeAdjustment);
						} else {
							lastASizeGeometryAdjustment = (ASizeGeometryAdjustment) geometryAdjustment;
						}
					} else if (geometryAdjustment instanceof ASkewGeometryAdjustment) {
						if (lastASkewGeometryAdjustment != null) {
							shapeAdjustments.remove(shapeAdjustment);
						} else {
							lastASkewGeometryAdjustment = (ASkewGeometryAdjustment) geometryAdjustment;
						}
					} else if (geometryAdjustment instanceof AXGeometryAdjustment) {
						if (lastAXGeometryAdjustment != null) {
							shapeAdjustments.remove(shapeAdjustment);
						} else {
							lastAXGeometryAdjustment = (AXGeometryAdjustment) geometryAdjustment;
						}
					} else if (geometryAdjustment instanceof AYGeometryAdjustment) {
						if (lastAYGeometryAdjustment != null) {
							shapeAdjustments.remove(shapeAdjustment);
						} else {
							lastAYGeometryAdjustment = (AYGeometryAdjustment) geometryAdjustment;
						}
					}
				}
			}
		}

		private ConfigurableExtensionReference<ShapeReplacementExtensionConfig> createShapeReplacementExtensionReference(AOrderedShapeReplacement shapeReplacement)	throws ExtensionNotFoundException {
			SingleShapeReplacementConfig config = new SingleShapeReplacementConfig();
			dropSameAdjustments(shapeReplacement.getShapeAdjustment());
			Collections.sort(shapeReplacement.getShapeAdjustment(), new PShapeAdjustmentComparator());
			config.setShape(((AOrderedShapeReplacement) shapeReplacement).getString().getText());
			for (PShapeAdjustment shapeAdjustment : shapeReplacement.getShapeAdjustment()) {
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
			} else if (geometryAdjustment instanceof ASize3GeometryAdjustment) {
				return getShapeAdjustmentExtensionReference((ASize3GeometryAdjustment) geometryAdjustment);
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
			} else if (geometryAdjustment instanceof AZGeometryAdjustment) {
				return getShapeAdjustmentExtensionReference((AZGeometryAdjustment) geometryAdjustment);
			} else {
				return null;
			}
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AAlphaCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentAlphaShapeAdjustmentConfig config = new CurrentAlphaShapeAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()));
			config.setTarget(colorAdjustment.getBar() != null);
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.currentAlpha");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AAlphaTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetAlphaShapeAdjustmentConfig config = new TargetAlphaShapeAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()));
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.targetAlpha");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ABrightnessCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentBrightnessShapeAdjustmentConfig config = new CurrentBrightnessShapeAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()));
			config.setTarget(colorAdjustment.getBar() != null);
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.currentBrightness");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ABrightnessTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetBrightnessShapeAdjustmentConfig config = new TargetBrightnessShapeAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()));
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.targetBrightness");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ASaturationCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentSaturationShapeAdjustmentConfig config = new CurrentSaturationShapeAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()));
			config.setTarget(colorAdjustment.getBar() != null);
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.currentSaturation");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ASaturationTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetSaturationShapeAdjustmentConfig config = new TargetSaturationShapeAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()));
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.targetSaturation");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AHueCurrentColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			CurrentHueShapeAdjustmentConfig config = new CurrentHueShapeAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()) / 360);
			config.setTarget(colorAdjustment.getBar() != null);
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.currentHue");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AHueTargetColorAdjustment colorAdjustment) throws ExtensionNotFoundException {
			TargetHueShapeAdjustmentConfig config = new TargetHueShapeAdjustmentConfig();
			config.setValue(evaluateExpression(colorAdjustment.getExpression()) / 360);
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.color.targetHue");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AXGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			XShapeAdjustmentConfig config = new XShapeAdjustmentConfig();
			config.setValue(evaluateExpression(geometryAdjustment.getExpression()));
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.x");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AYGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			YShapeAdjustmentConfig config = new YShapeAdjustmentConfig();
			config.setValue(evaluateExpression(geometryAdjustment.getExpression()));
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.y");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AZGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			ZShapeAdjustmentConfig config = new ZShapeAdjustmentConfig();
			config.setValue(evaluateExpression(geometryAdjustment.getExpression()));
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.z");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}

		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ASizeGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			SizeShapeAdjustmentConfig config = new SizeShapeAdjustmentConfig();
			config.setScale(evaluateExpression(geometryAdjustment.getExpression()));
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.size");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ASize2GeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			Size2ShapeAdjustmentConfig config = new Size2ShapeAdjustmentConfig();
			config.setScaleX(evaluateExpression(geometryAdjustment.getFirstExpression()));
			config.setScaleY(evaluateExpression(geometryAdjustment.getSecondExpression()));
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.size2");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ASize3GeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			Size3ShapeAdjustmentConfig config = new Size3ShapeAdjustmentConfig();
			config.setScaleX(evaluateExpression(geometryAdjustment.getFirstExpression()));
			config.setScaleY(evaluateExpression(geometryAdjustment.getSecondExpression()));
			config.setScaleZ(evaluateExpression(geometryAdjustment.getThirdExpression()));
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.size3");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ASkewGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			SkewShapeAdjustmentConfig config = new SkewShapeAdjustmentConfig();
			config.setShearX((float) (Math.PI * evaluateExpression(geometryAdjustment.getFirstExpression())) / 180f);
			config.setShearY((float) (Math.PI * evaluateExpression(geometryAdjustment.getSecondExpression())) / 180f);
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.skew");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(AFlipGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			FlipShapeAdjustmentConfig config = new FlipShapeAdjustmentConfig();
			config.setAngle((float) (Math.PI * evaluateExpression(geometryAdjustment.getExpression())) / 180f);
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.flip");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> getShapeAdjustmentExtensionReference(ARotateGeometryAdjustment geometryAdjustment) throws ExtensionNotFoundException {
			RotateShapeAdjustmentConfig config = new RotateShapeAdjustmentConfig();
			config.setAngle((float) (Math.PI * evaluateExpression(geometryAdjustment.getExpression())) / 180f);
			ConfigurableExtension<ShapeAdjustmentExtensionRuntime<?>, ShapeAdjustmentExtensionConfig> extension = ContextFreeRegistry.getInstance().getShapeAdjustmentExtension("contextfree.shape.adjustment.geometry.rotate");
			ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> reference = extension.createConfigurableExtensionReference(config);
			return reference;
		}
		
		private class PCommandParameterComparator implements Comparator<PCommandParameter> {
			public int compare(PCommandParameter o1, PCommandParameter o2) {
				int op1 = 0;
				int op2 = 0;
				if (o1 instanceof AColorCommandParameter) {
					op1 = 1;
				} else if (o1 instanceof AGeometryCommandParameter) {
					op1 = 2;
				}
				if (o2 instanceof AColorCommandParameter) {
					op2 = 1;
				} else if (o2 instanceof AGeometryCommandParameter) {
					op2 = 2;
				}
				if (op1 == op2) {
					if (o1 instanceof AColorCommandParameter) {
						PColorAdjustment ca1 = ((AColorCommandParameter) o1).getColorAdjustment();
						PColorAdjustment ca2 = ((AColorCommandParameter) o2).getColorAdjustment();
						int cap1 = 0;
						int cap2 = 0;
						if (ca1 instanceof ACurrentColorAdjustment) {
							cap1 = 1; 
						} else if (ca1 instanceof ATargetColorAdjustment) {
							cap1 = 2; 
						} 
						if (ca2 instanceof ACurrentColorAdjustment) {
							cap2 = 1; 
						} else if (ca2 instanceof ATargetColorAdjustment) {
							cap2 = 2; 
						} 
						if (cap1 == cap2) {
							if (ca1 instanceof ACurrentColorAdjustment) {
								PCurrentColorAdjustment cca1 = ((ACurrentColorAdjustment) ca1).getCurrentColorAdjustment();
								PCurrentColorAdjustment cca2 = ((ACurrentColorAdjustment) ca2).getCurrentColorAdjustment();
								int ccap1 = 0;
								int ccap2 = 0;
								if (cca1 instanceof AHueCurrentColorAdjustment) {
									ccap1 = 1;
								} else if (cca1 instanceof ABrightnessCurrentColorAdjustment) {
									ccap1 = 2;
								} else if (cca1 instanceof ASaturationCurrentColorAdjustment) {
									ccap1 = 3;
								} else if (cca1 instanceof AAlphaCurrentColorAdjustment) {
									ccap1 = 4;
								}
								if (cca2 instanceof AHueCurrentColorAdjustment) {
									ccap2 = 1;
								} else if (cca1 instanceof ABrightnessCurrentColorAdjustment) {
									ccap2 = 2;
								} else if (cca1 instanceof ASaturationCurrentColorAdjustment) {
									ccap2 = 3;
								} else if (cca1 instanceof AAlphaCurrentColorAdjustment) {
									ccap2 = 4;
								}
								return ccap1 - ccap2; 
							} else if (ca1 instanceof ATargetColorAdjustment) {
								PTargetColorAdjustment tca1 = ((ATargetColorAdjustment) ca1).getTargetColorAdjustment();
								PTargetColorAdjustment tca2 = ((ATargetColorAdjustment) ca2).getTargetColorAdjustment();
								int tcap1 = 0;
								int tcap2 = 0;
								if (tca1 instanceof AHueTargetColorAdjustment) {
									tcap1 = 1;
								} else if (tca1 instanceof ABrightnessTargetColorAdjustment) {
									tcap1 = 2;
								} else if (tca1 instanceof ASaturationTargetColorAdjustment) {
									tcap1 = 3;
								} else if (tca1 instanceof AAlphaTargetColorAdjustment) {
									tcap1 = 4;
								}
								if (tca2 instanceof AHueTargetColorAdjustment) {
									tcap2 = 1;
								} else if (tca1 instanceof ABrightnessTargetColorAdjustment) {
									tcap2 = 2;
								} else if (tca1 instanceof ASaturationTargetColorAdjustment) {
									tcap2 = 3;
								} else if (tca1 instanceof AAlphaTargetColorAdjustment) {
									tcap2 = 4;
								}
								return tcap1 - tcap2; 
							}
							return 0;
						}
						return cap1 - cap2; 
					} else if (o1 instanceof AGeometryCommandParameter) {
						PPathAdjustment ga1 = ((AGeometryCommandParameter) o1).getPathAdjustment();
						PPathAdjustment ga2 = ((AGeometryCommandParameter) o2).getPathAdjustment();
						int gap1 = 0;
						int gap2 = 0;
						if (ga1 instanceof AXPathAdjustment) {
							gap1 = 1; 
						} else if (ga1 instanceof AYPathAdjustment) {
							gap1 = 2; 
						} else if (ga1 instanceof ARotatePathAdjustment) {
							gap1 = 3; 
						} else if (ga1 instanceof ASizePathAdjustment) {
							gap1 = 4; 
						} else if (ga1 instanceof ASize2PathAdjustment) {
							gap1 = 5; 
						} else if (ga1 instanceof ASkewPathAdjustment) {
							gap1 = 6; 
						} else if (ga1 instanceof AFlipPathAdjustment) {
							gap1 = 7; 
						} 
						if (ga2 instanceof AXPathAdjustment) {
							gap2 = 1; 
						} else if (ga2 instanceof AYPathAdjustment) {
							gap2 = 2; 
						} else if (ga2 instanceof ARotatePathAdjustment) {
							gap2 = 3; 
						} else if (ga2 instanceof ASizePathAdjustment) {
							gap2 = 4; 
						} else if (ga2 instanceof ASize2PathAdjustment) {
							gap2 = 5; 
						} else if (ga2 instanceof ASkewPathAdjustment) {
							gap2 = 6; 
						} else if (ga2 instanceof AFlipPathAdjustment) {
							gap2 = 7; 
						} 
						return gap1 - gap2;
					}
					return 0;
				}
				return op1 - op2;
			}
		}
	
		private class PShapeAdjustmentComparator implements Comparator<PShapeAdjustment> {
			public int compare(PShapeAdjustment o1, PShapeAdjustment o2) {
				int op1 = 0;
				int op2 = 0;
				if (o1 instanceof AColorShapeAdjustment) {
					op1 = 1;
				} else if (o1 instanceof AGeometryShapeAdjustment) {
					op1 = 2;
				}
				if (o2 instanceof AColorShapeAdjustment) {
					op2 = 1;
				} else if (o2 instanceof AGeometryShapeAdjustment) {
					op2 = 2;
				}
				if (op1 == op2) {
					if (o1 instanceof AColorShapeAdjustment) {
						PColorAdjustment ca1 = ((AColorShapeAdjustment) o1).getColorAdjustment();
						PColorAdjustment ca2 = ((AColorShapeAdjustment) o2).getColorAdjustment();
						int cap1 = 0;
						int cap2 = 0;
						if (ca1 instanceof ACurrentColorAdjustment) {
							cap1 = 1; 
						} else if (ca1 instanceof ATargetColorAdjustment) {
							cap1 = 2; 
						} 
						if (ca2 instanceof ACurrentColorAdjustment) {
							cap2 = 1; 
						} else if (ca2 instanceof ATargetColorAdjustment) {
							cap2 = 2; 
						} 
						if (cap1 == cap2) {
							if (ca1 instanceof ACurrentColorAdjustment) {
								PCurrentColorAdjustment cca1 = ((ACurrentColorAdjustment) ca1).getCurrentColorAdjustment();
								PCurrentColorAdjustment cca2 = ((ACurrentColorAdjustment) ca2).getCurrentColorAdjustment();
								int ccap1 = 0;
								int ccap2 = 0;
								if (cca1 instanceof AHueCurrentColorAdjustment) {
									ccap1 = 1;
								} else if (cca1 instanceof ABrightnessCurrentColorAdjustment) {
									ccap1 = 2;
								} else if (cca1 instanceof ASaturationCurrentColorAdjustment) {
									ccap1 = 3;
								} else if (cca1 instanceof AAlphaCurrentColorAdjustment) {
									ccap1 = 4;
								}
								if (cca2 instanceof AHueCurrentColorAdjustment) {
									ccap2 = 1;
								} else if (cca1 instanceof ABrightnessCurrentColorAdjustment) {
									ccap2 = 2;
								} else if (cca1 instanceof ASaturationCurrentColorAdjustment) {
									ccap2 = 3;
								} else if (cca1 instanceof AAlphaCurrentColorAdjustment) {
									ccap2 = 4;
								}
								return ccap1 - ccap2; 
							} else if (ca1 instanceof ATargetColorAdjustment) {
								PTargetColorAdjustment tca1 = ((ATargetColorAdjustment) ca1).getTargetColorAdjustment();
								PTargetColorAdjustment tca2 = ((ATargetColorAdjustment) ca2).getTargetColorAdjustment();
								int tcap1 = 0;
								int tcap2 = 0;
								if (tca1 instanceof AHueTargetColorAdjustment) {
									tcap1 = 1;
								} else if (tca1 instanceof ABrightnessTargetColorAdjustment) {
									tcap1 = 2;
								} else if (tca1 instanceof ASaturationTargetColorAdjustment) {
									tcap1 = 3;
								} else if (tca1 instanceof AAlphaTargetColorAdjustment) {
									tcap1 = 4;
								}
								if (tca2 instanceof AHueTargetColorAdjustment) {
									tcap2 = 1;
								} else if (tca1 instanceof ABrightnessTargetColorAdjustment) {
									tcap2 = 2;
								} else if (tca1 instanceof ASaturationTargetColorAdjustment) {
									tcap2 = 3;
								} else if (tca1 instanceof AAlphaTargetColorAdjustment) {
									tcap2 = 4;
								}
								return tcap1 - tcap2; 
							}
							return 0;
						}
						return cap2 - cap1; 
					} else if (o1 instanceof AGeometryShapeAdjustment) {
						PGeometryAdjustment ga1 = ((AGeometryShapeAdjustment) o1).getGeometryAdjustment();
						PGeometryAdjustment ga2 = ((AGeometryShapeAdjustment) o2).getGeometryAdjustment();
						int gap1 = 0;
						int gap2 = 0;
						if (ga1 instanceof AXGeometryAdjustment) {
							gap1 = 1; 
						} else if (ga1 instanceof AYGeometryAdjustment) {
							gap1 = 2; 
						} else if (ga1 instanceof ARotateGeometryAdjustment) {
							gap1 = 3; 
						} else if (ga1 instanceof ASizeGeometryAdjustment) {
							gap1 = 4; 
						} else if (ga1 instanceof ASize2GeometryAdjustment) {
							gap1 = 5; 
						} else if (ga1 instanceof ASkewGeometryAdjustment) {
							gap1 = 6; 
						} else if (ga1 instanceof AFlipGeometryAdjustment) {
							gap1 = 7; 
						} 
						if (ga2 instanceof AXGeometryAdjustment) {
							gap2 = 1; 
						} else if (ga2 instanceof AYGeometryAdjustment) {
							gap2 = 2; 
						} else if (ga2 instanceof ARotateGeometryAdjustment) {
							gap2 = 3; 
						} else if (ga2 instanceof ASizeGeometryAdjustment) {
							gap2 = 4; 
						} else if (ga2 instanceof ASize2GeometryAdjustment) {
							gap2 = 5; 
						} else if (ga2 instanceof ASkewGeometryAdjustment) {
							gap2 = 6; 
						} else if (ga2 instanceof AFlipGeometryAdjustment) {
							gap2 = 7; 
						} 
						return gap1 - gap2;
					}
					return 0;
				}
				return op1 - op2;
			}
		}
	}
}
