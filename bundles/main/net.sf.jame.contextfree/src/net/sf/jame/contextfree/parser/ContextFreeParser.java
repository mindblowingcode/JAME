package net.sf.jame.contextfree.parser;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
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
import net.sf.jame.contextfree.cfdg.node.AAlphaBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.AArg0Function;
import net.sf.jame.contextfree.cfdg.node.AArg1Function;
import net.sf.jame.contextfree.cfdg.node.AArg2Function;
import net.sf.jame.contextfree.cfdg.node.ABackgroundDeclaration;
import net.sf.jame.contextfree.cfdg.node.ABasicPathCommand;
import net.sf.jame.contextfree.cfdg.node.ABasicPathReplacementBlock;
import net.sf.jame.contextfree.cfdg.node.ABrightnessBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.ACfdg;
import net.sf.jame.contextfree.cfdg.node.AColorPathAdjustment;
import net.sf.jame.contextfree.cfdg.node.AColorShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.ACommandPathReplacement;
import net.sf.jame.contextfree.cfdg.node.AComposedExpression2;
import net.sf.jame.contextfree.cfdg.node.AFirstExpression;
import net.sf.jame.contextfree.cfdg.node.AFunctionExpression;
import net.sf.jame.contextfree.cfdg.node.AFunctionExpression2;
import net.sf.jame.contextfree.cfdg.node.AGeometryPathAdjustment;
import net.sf.jame.contextfree.cfdg.node.AGeometryShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AHueBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.AIncludeDeclaration;
import net.sf.jame.contextfree.cfdg.node.AListPathReplacementBlock;
import net.sf.jame.contextfree.cfdg.node.AMultiPathReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.AMultiShapeReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.ANestedExpression;
import net.sf.jame.contextfree.cfdg.node.ANestedExpression2;
import net.sf.jame.contextfree.cfdg.node.ANumberExpression;
import net.sf.jame.contextfree.cfdg.node.ANumberExpression2;
import net.sf.jame.contextfree.cfdg.node.AOperationPathReplacement;
import net.sf.jame.contextfree.cfdg.node.AOrderedPathCommand;
import net.sf.jame.contextfree.cfdg.node.AParametersPathAdjustment;
import net.sf.jame.contextfree.cfdg.node.APathDeclaration;
import net.sf.jame.contextfree.cfdg.node.APathOperation;
import net.sf.jame.contextfree.cfdg.node.ARuleDeclaration;
import net.sf.jame.contextfree.cfdg.node.ASaturationBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.ASecondExpression;
import net.sf.jame.contextfree.cfdg.node.ASinglePathReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.ASingleShapeReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.ASize3ShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.ASizeDeclaration;
import net.sf.jame.contextfree.cfdg.node.ASizeSizeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AStartshapeDeclaration;
import net.sf.jame.contextfree.cfdg.node.AStrokePathAdjustment;
import net.sf.jame.contextfree.cfdg.node.ATileAdjustment;
import net.sf.jame.contextfree.cfdg.node.ATileDeclaration;
import net.sf.jame.contextfree.cfdg.node.AXSizeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AYSizeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AZShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.PBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.PExpression;
import net.sf.jame.contextfree.cfdg.node.PExpression2;
import net.sf.jame.contextfree.cfdg.node.PFirstExpression;
import net.sf.jame.contextfree.cfdg.node.PPathCommand;
import net.sf.jame.contextfree.cfdg.node.PPathOperation;
import net.sf.jame.contextfree.cfdg.node.PPathPoints;
import net.sf.jame.contextfree.cfdg.node.PPathReplacement;
import net.sf.jame.contextfree.cfdg.node.PPathReplacementBlock;
import net.sf.jame.contextfree.cfdg.node.PPathReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.PSecondExpression;
import net.sf.jame.contextfree.cfdg.node.PShapeReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.PSizeAdjustment;
import net.sf.jame.contextfree.cfdg.node.PTileAdjustment;
import net.sf.jame.contextfree.cfdg.parser.Parser;
import net.sf.jame.contextfree.cfdg.parser.ParserException;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.extensions.figure.PathFigureConfig;
import net.sf.jame.contextfree.extensions.figure.RuleFigureConfig;
import net.sf.jame.core.extension.ConfigurableExtension;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.util.Color32bit;
import net.sf.jame.core.util.Colors;

public class ContextFreeParser {
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
			config.setCFDG(new CFDGConfigElement());
		}

		/**
		 * 
		 */
		@Override
		public void inARuleDeclaration(ARuleDeclaration node) {
			System.out.println("RuleFigureDeclaration " + node);
			try {
				FigureConfigElement figureElement = createRuleFigureElement(node);
				config.getCFDG().appendFigureConfigElement(figureElement);
			} catch (ContextFreeParserException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 
		 */
		@Override
		public void inAPathDeclaration(APathDeclaration node) {
			System.out.println("PathFigureDeclaration " + node);
			try {
				FigureConfigElement figureElement = createPathFigureElement(node);
				config.getCFDG().appendFigureConfigElement(figureElement);
			} catch (ContextFreeParserException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 
		 */
		@Override
		public void inAStartshapeDeclaration(AStartshapeDeclaration node) {
			System.out.println("StartshapeDeclaration " + node);
			config.getCFDG().setStartshape(node.getStartshape().getText());
		}

		/**
		 * 
		 */
		@Override
		public void inAIncludeDeclaration(AIncludeDeclaration node) {
			System.out.println("IncludeDeclaration " + node);
			try {
				ContextFreeConfig tmpConfig = parseConfig(new File(node.getFilename().getText()));
				System.out.println(tmpConfig);//TODO merge config 
			} catch (ContextFreeParserException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 
		 */
		@Override
		public void inABackgroundDeclaration(ABackgroundDeclaration node) {
			System.out.println("BackgroundDeclaration " + node);
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
			System.out.println("TileDeclaration " + node);
			for (PTileAdjustment adjustment : node.getTileAdjustment()) {
				if (adjustment instanceof ATileAdjustment) {
					PFirstExpression firstExpression = ((ATileAdjustment) adjustment).getFirstExpression();
					if (firstExpression != null && firstExpression instanceof AFirstExpression) {
						float value = evaluateExpression(((AFirstExpression) firstExpression).getExpression2());
						System.out.println("size x = " + value);//TODO set tile size x
					}
					PSecondExpression secondExpression = ((ATileAdjustment) adjustment).getSecondExpression();
					if (secondExpression != null && secondExpression instanceof ASecondExpression) {
						float value = evaluateExpression(((ASecondExpression) secondExpression).getExpression2());
						System.out.println("size y = " + value);//TODO set tile size y
					}
				}
			}
		}

		/**
		 * 
		 */
		@Override
		public void inASizeDeclaration(ASizeDeclaration node) {
			System.out.println("SizeDeclaration " + node);
			for (PSizeAdjustment adjustment : node.getSizeAdjustment()) {
				if (adjustment instanceof ASizeSizeAdjustment) {
					PFirstExpression firstExpression = ((ASizeSizeAdjustment) adjustment).getFirstExpression();
					if (firstExpression instanceof AFirstExpression) {
						float value = evaluateExpression(((AFirstExpression) firstExpression).getExpression2());
						System.out.println("size x = " + value);//TODO set size x
					}
					PSecondExpression secondExpression = ((ASizeSizeAdjustment) adjustment).getSecondExpression();
					if (secondExpression instanceof ASecondExpression) {
						float value = evaluateExpression(((ASecondExpression) secondExpression).getExpression2());
						System.out.println("size y = " + value);//TODO set size y
					}
				} else if (adjustment instanceof AXSizeAdjustment) {
					float value = evaluateExpression(((AXSizeAdjustment) adjustment).getExpression());
					System.out.println("x = " + value);//TODO set x
				} else if (adjustment instanceof AYSizeAdjustment) {
					float value = evaluateExpression(((AYSizeAdjustment) adjustment).getExpression());
					System.out.println("y = " + value);//TODO set y
				}
			}
		}

		/**
		 * 
		 */
		@Override
		public void inAColorShapeAdjustment(AColorShapeAdjustment node) {
			System.out.println("ColorShapeAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAGeometryShapeAdjustment(AGeometryShapeAdjustment node) {
			System.out.println("GeometryShapeAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAZShapeAdjustment(AZShapeAdjustment node) {
			System.out.println("ZShapeAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inASize3ShapeAdjustment(ASize3ShapeAdjustment node) {
			System.out.println("Size3ShapeAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAColorPathAdjustment(AColorPathAdjustment node) {
			System.out.println("ColorPathAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAGeometryPathAdjustment(AGeometryPathAdjustment node) {
			System.out.println("GeometryPathAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAStrokePathAdjustment(AStrokePathAdjustment node) {
			System.out.println("StrokePathAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAParametersPathAdjustment(AParametersPathAdjustment node) {
			System.out.println("ParametersPathAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAArg0Function(AArg0Function node) {
			System.out.println("Arg0Function " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAArg1Function(AArg1Function node) {
			System.out.println("Arg1Function " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAArg2Function(AArg2Function node) {
			System.out.println("Arg2Function " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inANumberExpression(ANumberExpression node) {
			System.out.println("NumberExpression " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inANestedExpression(ANestedExpression node) {
			System.out.println("NestedExpression " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAFunctionExpression(AFunctionExpression node) {
			System.out.println("FunctionExpression " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inANumberExpression2(ANumberExpression2 node) {
			System.out.println("NumberExpression2 " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inANestedExpression2(ANestedExpression2 node) {
			System.out.println("NestedExpression2 " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAFunctionExpression2(AFunctionExpression2 node) {
			System.out.println("FunctionExpression2 " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAComposedExpression2(AComposedExpression2 node) {
			System.out.println("ComposedExpression2 " + node);
		}
		
		public float evaluateExpression(PExpression expression) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		public float evaluateExpression(PExpression2 expression) {
			// TODO Auto-generated method stub
			return 0;
		}

		private FigureConfigElement createPathFigureElement(APathDeclaration pathDeclaration) throws ContextFreeParserException {
			PathFigureConfig pathConfig = new PathFigureConfig();
			pathConfig.setName(pathDeclaration.getString().getText());
			for (PPathReplacementDeclaration pathReplacementDeclaration : pathDeclaration.getPathReplacementDeclaration()) {
				PathReplacementConfigElement pathReplacementElement = createPathReplacementElement(pathReplacementDeclaration);
				if (pathReplacementElement != null) {
					pathConfig.appendPathReplacementConfigElement(pathReplacementElement);
				}
			}
			try {
				ConfigurableExtension<FigureExtensionRuntime<?>, FigureExtensionConfig> extension = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.path");
				ConfigurableExtensionReference<FigureExtensionConfig> reference = extension.createConfigurableExtensionReference(pathConfig);
				FigureConfigElement figureElement = new FigureConfigElement();
				figureElement.setExtensionReference(reference);
				return figureElement;
			}
			catch (Exception e) {
				throw new ContextFreeParserException(e);
			}
		}

		private PathReplacementConfigElement createPathReplacementElement(PPathReplacementDeclaration pathReplacementDeclaration) {
			if (pathReplacementDeclaration instanceof ASinglePathReplacementDeclaration) {
				PPathReplacement pathReplacement = ((ASinglePathReplacementDeclaration) pathReplacementDeclaration).getPathReplacement();
				if (pathReplacement instanceof AOperationPathReplacement) {
					PPathOperation pathOperation = ((AOperationPathReplacement) pathReplacement).getPathOperation();
					if (pathOperation instanceof APathOperation) {
						String pathop = ((APathOperation) pathOperation).getPathop().getText();
						List<Point2D.Float> points = createPoints(((APathOperation) pathOperation).getPathPoints());
						//TODO creare operation
					}
				} else if (pathReplacement instanceof ACommandPathReplacement) {
					PPathCommand pathCommand = ((ACommandPathReplacement) pathReplacement).getPathCommand();
					if (pathCommand instanceof ABasicPathCommand) {
						
					} else if (pathCommand instanceof AOrderedPathCommand) {
						
					}
				}
			} else if (pathReplacementDeclaration instanceof AMultiPathReplacementDeclaration) {
				PPathReplacementBlock pathReplacementBlock = ((AMultiPathReplacementDeclaration) pathReplacementDeclaration).getPathReplacementBlock();
				if (pathReplacementBlock instanceof ABasicPathReplacementBlock) {
					
				} else if (pathReplacementBlock instanceof AListPathReplacementBlock) {
					
				}
			}
			return null;
		}
		
		private List<Point2D.Float> createPoints(LinkedList<PPathPoints> pathPoints) {
			// TODO Auto-generated method stub
			return null;
		}

		private FigureConfigElement createRuleFigureElement(ARuleDeclaration ruleDeclaration) throws ContextFreeParserException {
			RuleFigureConfig ruleConfig = new RuleFigureConfig();
			ruleConfig.setName(ruleDeclaration.getString().getText());
			ruleConfig.setProbability(Float.valueOf(ruleDeclaration.getNumber().getText()));
			for (PShapeReplacementDeclaration shapeReplacementDeclaration : ruleDeclaration.getShapeReplacementDeclaration()) {
				ShapeReplacementConfigElement shapeReplacementElement = createShapeReplacementElement(shapeReplacementDeclaration);
				if (shapeReplacementElement != null) {
					ruleConfig.appendShapeReplacementConfigElement(shapeReplacementElement);
				}
			}
			try {
				ConfigurableExtension<FigureExtensionRuntime<?>, FigureExtensionConfig> extension = ContextFreeRegistry.getInstance().getFigureExtension("contextfree.figure.rule");
				ConfigurableExtensionReference<FigureExtensionConfig> reference = extension.createConfigurableExtensionReference(ruleConfig);
				FigureConfigElement figureElement = new FigureConfigElement();
				figureElement.setExtensionReference(reference);
				return figureElement;
			}
			catch (Exception e) {
				throw new ContextFreeParserException(e);
			}
		}

		private ShapeReplacementConfigElement createShapeReplacementElement(PShapeReplacementDeclaration ruleReplacementDeclaration) {
			if (ruleReplacementDeclaration instanceof ASingleShapeReplacementDeclaration) {
				
			} else if (ruleReplacementDeclaration instanceof AMultiShapeReplacementDeclaration) {
				
			}
			return null;
		}
	}
}
