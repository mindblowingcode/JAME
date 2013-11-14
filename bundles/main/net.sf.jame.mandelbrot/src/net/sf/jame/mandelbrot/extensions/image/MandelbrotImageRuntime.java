/*
 * JAME 6.1 
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2010 Andrea Medeghini
 * http://andreamedeghini.users.sourceforge.net
 *
 * This file is part of JAME.
 *
 * JAME is an application for creating fractals and other graphics artifacts.
 *
 * JAME is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JAME is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAME.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.sf.jame.mandelbrot.extensions.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jame.core.math.Complex;
import net.sf.jame.core.util.DoubleVector2D;
import net.sf.jame.core.util.IntegerVector2D;
import net.sf.jame.core.util.Rectangle;
import net.sf.jame.core.util.Tile;
import net.sf.jame.mandelbrot.MandelbrotManager;
import net.sf.jame.mandelbrot.MandelbrotRuntime;
import net.sf.jame.mandelbrot.fractal.MandelbrotFractalRuntimeElement;
import net.sf.jame.mandelbrot.renderer.BestXaosMandelbrotRenderer;
import net.sf.jame.mandelbrot.renderer.FastXaosMandelbrotRenderer;
import net.sf.jame.mandelbrot.renderer.RenderedPoint;
import net.sf.jame.mandelbrot.renderer.SimpleMandelbrotRenderer;
import net.sf.jame.mandelbrot.renderingFormula.RenderingFormulaRuntimeElement;
import net.sf.jame.twister.image.extension.ImageExtensionRuntime;
import net.sf.jame.twister.renderer.TwisterRenderer;
import net.sf.jame.twister.renderer.TwisterRenderingHints;
import net.sf.jame.twister.util.View;

/**
 * @author Andrea Medeghini
 */
public class MandelbrotImageRuntime extends ImageExtensionRuntime<MandelbrotImageConfig> {
	// private static final Logger logger = Logger.getLogger(MandelbrotImageRuntime.class);
	private Map<Object, Object> hints = new HashMap<Object, Object>();
	private MandelbrotRuntime mandelbrotRuntime;
	private RendererStrategy rendererStrategy;
	private int lastStatus;
	private Tile tile;

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		mandelbrotRuntime = new MandelbrotRuntime(getConfig().getMandelbrotConfig());
		if (rendererStrategy != null) {
			rendererStrategy.dispose();
			rendererStrategy = null;
		}
	}

	/**
	 * @see net.sf.jame.twister.image.extension.ImageExtensionRuntime#startRenderer()
	 */
	@Override
	public void startRenderer() {
		if (rendererStrategy != null) {
			rendererStrategy.startRenderer();
		}
	}

	/**
	 * @see net.sf.jame.twister.image.extension.ImageExtensionRuntime#abortRenderer()
	 */
	@Override
	public void abortRenderer() {
		if (rendererStrategy != null) {
			rendererStrategy.abortRenderer();
		}
	}

	/**
	 * @see net.sf.jame.twister.image.extension.ImageExtensionRuntime#joinRenderer()
	 */
	@Override
	public void joinRenderer() throws InterruptedException {
		if (rendererStrategy != null) {
			rendererStrategy.joinRenderer();
		}
	}

	/**
	 * @see net.sf.jame.twister.image.extension.ImageExtensionRuntime#getRenderingStatus()
	 */
	@Override
	public int getRenderingStatus() {
		if (rendererStrategy != null) {
			return rendererStrategy.getRenderingStatus();
		}
		return 0;
	}

	/**
	 * @see net.sf.jame.twister.image.extension.ImageExtensionRuntime#isDynamic()
	 */
	@Override
	public boolean isDynamic() {
		if (rendererStrategy != null) {
			return rendererStrategy.isDynamic();
		}
		return false;
	}

	/**
	 * @see net.sf.jame.twister.image.extension.ImageExtensionRuntime#prepareImage(boolean)
	 */
	@Override
	public void prepareImage(final boolean isDynamicRequired) {
		if (rendererStrategy != null) {
			rendererStrategy.prepareImage(isDynamicRequired);
		}
	}

	/**
	 * @see net.sf.jame.twister.image.extension.ImageExtensionRuntime#drawImage(java.awt.Graphics2D)
	 */
	@Override
	public void drawImage(final Graphics2D g2d) {
		if (rendererStrategy != null) {
			rendererStrategy.drawImage(g2d);
		}
	}

	/**
	 * @see net.sf.jame.twister.image.extension.ImageExtensionRuntime#drawImage(java.awt.Graphics2D, int, int)
	 */
	@Override
	public void drawImage(final Graphics2D g2d, final int x, final int y) {
		if (rendererStrategy != null) {
			rendererStrategy.drawImage(g2d, x, y);
		}
	}

	/**
	 * @see net.sf.jame.twister.image.extension.ImageExtensionRuntime#drawImage(java.awt.Graphics2D, int, int, int, int)
	 */
	@Override
	public void drawImage(final Graphics2D g2d, final int x, final int y, final int w, final int h) {
		if (rendererStrategy != null) {
			rendererStrategy.drawImage(g2d, x, y, w, h);
		}
	}

	/**
	 * @see net.sf.jame.twister.image.extension.ImageExtensionRuntime#getImageSize()
	 */
	@Override
	public IntegerVector2D getImageSize() {
		return tile.getImageSize();
	}

	/**
	 * @see net.sf.jame.twister.image.extension.ImageExtensionRuntime#setTile(net.sf.jame.core.util.Tile)
	 */
	@Override
	public void setTile(final Tile tile) {
		if (this.tile != tile) {
			this.tile = tile;
			if (hints.get(TwisterRenderingHints.KEY_TYPE) == TwisterRenderingHints.TYPE_OVERLAY) {
				rendererStrategy = new OverlayRendererStrategy(tile);
			}
			else {
				rendererStrategy = new DefaultRendererStrategy(tile);
			}
		}
	}

	/**
	 * @see net.sf.jame.twister.image.extension.ImageExtensionRuntime#setRenderingHints(java.util.Map)
	 */
	@Override
	public void setRenderingHints(final Map<Object, Object> hints) {
		this.hints = hints;
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionRuntime#dispose()
	 */
	@Override
	public void dispose() {
		if (rendererStrategy != null) {
			rendererStrategy.dispose();
			rendererStrategy = null;
		}
		if (mandelbrotRuntime != null) {
			mandelbrotRuntime.dispose();
			mandelbrotRuntime = null;
		}
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean isChanged = (mandelbrotRuntime != null) && ((mandelbrotRuntime.changeCount() > 0) || mandelbrotRuntime.getViewChanged());
		final int status = (rendererStrategy != null) ? rendererStrategy.getRenderingStatus() : -1;
		isChanged |= (status == TwisterRenderer.STATUS_RENDERING) || (lastStatus == TwisterRenderer.STATUS_RENDERING);
		lastStatus = status;
		return super.isChanged() || isChanged;
	}

	private interface RendererStrategy {
		/**
		 * 
		 */
		public void dispose();

		/**
		 * @param isDynamicRequired
		 */
		public void prepareImage(boolean isDynamicRequired);

		/**
		 * @param g2d
		 */
		public void drawImage(final Graphics2D g2d);

		/**
		 * @param g2d
		 * @param x
		 * @param y
		 */
		public void drawImage(Graphics2D g2d, int x, int y);

		/**
		 * @param g2d
		 * @param x
		 * @param y
		 * @param w
		 * @param h
		 */
		public void drawImage(Graphics2D g2d, int x, int y, int w, int h);

		/**
		 * @return
		 */
		public int getRenderingStatus();

		/**
		 * 
		 */
		public void startRenderer();

		/**
		 * 
		 */
		public void abortRenderer();

		/**
		 * @throws InterruptedException
		 */
		public void joinRenderer() throws InterruptedException;

		/**
		 * @return
		 */
		public boolean isDynamic();
	}

	private class DefaultRendererStrategy implements RendererStrategy {
		private MandelbrotManager manager;
		private int dynamicCount = 0;
		public boolean dirty;

		/**
		 * @param tile
		 */
		public DefaultRendererStrategy(final Tile tile) {
			if (hints.get(TwisterRenderingHints.KEY_QUALITY) == TwisterRenderingHints.QUALITY_REALTIME) {
				if (hints.get(TwisterRenderingHints.KEY_TYPE) == TwisterRenderingHints.TYPE_PREVIEW) {
					if (hints.get(TwisterRenderingHints.KEY_MEMORY) == TwisterRenderingHints.MEMORY_LOW) {
						manager = new MandelbrotManager(new FastXaosMandelbrotRenderer(Thread.MIN_PRIORITY));
					}
					else {
						manager = new MandelbrotManager(new BestXaosMandelbrotRenderer(Thread.MIN_PRIORITY));
					}
				}
				else {
					if (hints.get(TwisterRenderingHints.KEY_MEMORY) == TwisterRenderingHints.MEMORY_LOW) {
						manager = new MandelbrotManager(new FastXaosMandelbrotRenderer(Thread.MIN_PRIORITY + 2));
					}
					else {
						manager = new MandelbrotManager(new BestXaosMandelbrotRenderer(Thread.MIN_PRIORITY + 2));
					}
				}
			}
			else {
				manager = new MandelbrotManager(new SimpleMandelbrotRenderer(Thread.MIN_PRIORITY + 1));
			}
			manager.setRenderingHints(hints);
			manager.setRuntime(mandelbrotRuntime);
			loadConfig();
			manager.setTile(tile);
			manager.start();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#startRenderer()
		 */
		public void startRenderer() {
			manager.startRenderer();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#abortRenderer()
		 */
		public void abortRenderer() {
			manager.abortRenderer();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#joinRenderer()
		 */
		public void joinRenderer() throws InterruptedException {
			manager.joinRenderer();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#getRenderingStatus()
		 */
		public int getRenderingStatus() {
			return manager.getRenderingStatus();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#drawImage(java.awt.Graphics2D)
		 */
		public void drawImage(final Graphics2D g2d) {
			if (tile != null) {
				manager.drawImage(g2d);
				if (dirty) {
					dirty = false;
					manager.asyncStart();
					// try {
					// fractalManager.joinRenderer();
					// fractalManager.startRenderer();
					// }
					// catch (final InterruptedException e) {
					// Thread.currentThread().interrupt();
					// }
				}
			}
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#drawImage(java.awt.Graphics2D, int, int)
		 */
		public void drawImage(final Graphics2D g2d, final int x, final int y) {
			if (tile != null) {
				manager.drawImage(g2d, x, y);
				if (dirty) {
					dirty = false;
					manager.asyncStart();
					// try {
					// fractalManager.joinRenderer();
					// fractalManager.startRenderer();
					// }
					// catch (final InterruptedException e) {
					// Thread.currentThread().interrupt();
					// }
				}
			}
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#drawImage(java.awt.Graphics2D, int, int, int, int)
		 */
		public void drawImage(final Graphics2D g2d, final int x, final int y, final int w, final int h) {
			if (tile != null) {
				manager.drawImage(g2d, x, y, w, h);
				if (dirty) {
					dirty = false;
					manager.asyncStart();
					// try {
					// fractalManager.joinRenderer();
					// fractalManager.startRenderer();
					// }
					// catch (final InterruptedException e) {
					// Thread.currentThread().interrupt();
					// }
				}
			}
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#prepareImage(boolean)
		 */
		public void prepareImage(final boolean isDynamicRequired) {
			if (isDynamicRequired) {
				boolean isChanged = mandelbrotRuntime.isChanged();
				isChanged |= mandelbrotRuntime.isViewChanged();
				if (isChanged || (dynamicCount >= 1)) {
					dynamicCount = 0;
					loadConfig();
					dirty = true;
					manager.asyncStop();
				}
			}
			else {
				loadConfig();
			}
			if (manager.isDynamic()) {
				dynamicCount += 1;
			}
			else {
				dynamicCount = 0;
			}
		}

		private void loadConfig() {
			manager.setView(getConfig().getMandelbrotConfig().getView(), getConfig().getMandelbrotConfig().getConstant(), getConfig().getMandelbrotConfig().getImageMode());
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#dispose()
		 */
		public void dispose() {
			if (manager != null) {
				manager.stop();
				manager.dispose();
				manager = null;
			}
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#isDynamic()
		 */
		public boolean isDynamic() {
			return dynamicCount > 0;
		}
	}

	private class OverlayRendererStrategy implements RendererStrategy {
		private MandelbrotManager manager;
		private boolean suspended;
		private boolean dirty;

		/**
		 * @param tile
		 */
		public OverlayRendererStrategy(final Tile tile) {
			if (hints.get(TwisterRenderingHints.KEY_QUALITY) == TwisterRenderingHints.QUALITY_REALTIME) {
				if (hints.get(TwisterRenderingHints.KEY_TYPE) == TwisterRenderingHints.TYPE_PREVIEW) {
					if (hints.get(TwisterRenderingHints.KEY_MEMORY) == TwisterRenderingHints.MEMORY_LOW) {
						manager = new MandelbrotManager(new FastXaosMandelbrotRenderer(Thread.MIN_PRIORITY));
					}
					else {
						manager = new MandelbrotManager(new BestXaosMandelbrotRenderer(Thread.MIN_PRIORITY));
					}
				}
				else {
					if (hints.get(TwisterRenderingHints.KEY_MEMORY) == TwisterRenderingHints.MEMORY_LOW) {
						manager = new MandelbrotManager(new FastXaosMandelbrotRenderer(Thread.MIN_PRIORITY + 2));
					}
					else {
						manager = new MandelbrotManager(new BestXaosMandelbrotRenderer(Thread.MIN_PRIORITY + 2));
					}
				}
			}
			else {
				manager = new MandelbrotManager(new SimpleMandelbrotRenderer(Thread.MIN_PRIORITY + 1));
			}
			manager.setRenderingHints(hints);
			manager.setRuntime(mandelbrotRuntime);
			loadConfig();
			final Rectangle previewArea = getConfig().getMandelbrotConfig().getPreviewArea();
			final int px = (int) Math.rint(tile.getImageSize().getX() * previewArea.getX());
			final int py = (int) Math.rint(tile.getImageSize().getY() * previewArea.getY());
			final int pw = (int) Math.rint(tile.getImageSize().getX() * previewArea.getW());
			final int ph = (int) Math.rint(tile.getImageSize().getY() * previewArea.getH());
			final int tx = (int) Math.rint(tile.getTileOffset().getX());
			final int ty = (int) Math.rint(tile.getTileOffset().getY());
			final int tw = (int) Math.rint(tile.getTileSize().getX());
			final int th = (int) Math.rint(tile.getTileSize().getY());
			// final int iw = (int) Math.rint(tile.getImageSize().getX());
			// final int ih = (int) Math.rint(tile.getImageSize().getY());
			suspended = true;
			if ((px < tx + tw) && (px + pw > tx)) {
				if ((py < ty + th) && (py + ph > ty)) {
					int ox = 0;
					int oy = 0;
					int ow = pw;
					int oh = ph;
					if (tx > px) {
						ox = tx - px;
					}
					if (ty > py) {
						oy = ty - py;
					}
					if (ox + ow > pw) {
						ow = pw - ox;
					}
					if (oy + oh > ph) {
						oh = ph - oy;
					}
					if ((ow > 0) && (oh > 0) && (ox >= 0) && (oy >= 0)) {
						manager.setTile(new Tile(new IntegerVector2D(pw, ph), new IntegerVector2D(ow, oh), new IntegerVector2D(ox, oy), new IntegerVector2D(0, 0)));
						suspended = false;
					}
				}
			}
			manager.start();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#startRenderer()
		 */
		public void startRenderer() {
			if (!suspended) {
				manager.startRenderer();
			}
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#abortRenderer()
		 */
		public void abortRenderer() {
			if (!suspended) {
				manager.abortRenderer();
			}
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#joinRenderer()
		 */
		public void joinRenderer() throws InterruptedException {
			if (!suspended) {
				manager.joinRenderer();
			}
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#getRenderingStatus()
		 */
		public int getRenderingStatus() {
			return manager.getRenderingStatus();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#drawImage(java.awt.Graphics2D)
		 */
		public void drawImage(final Graphics2D g2d) {
			if (tile != null) {
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
				if (getConfig().getMandelbrotConfig().getShowPreview() && !suspended) {
					final Rectangle previewArea = getConfig().getMandelbrotConfig().getPreviewArea();
					final int px = (int) Math.rint(tile.getImageSize().getX() * previewArea.getX());
					final int py = (int) Math.rint(tile.getImageSize().getY() * previewArea.getY());
					final int pw = (int) Math.rint(tile.getImageSize().getX() * previewArea.getW());
					final int ph = (int) Math.rint(tile.getImageSize().getY() * previewArea.getH());
					final int tx = (int) Math.rint(tile.getTileOffset().getX());
					final int ty = (int) Math.rint(tile.getTileOffset().getY());
					final int tw = (int) Math.rint(tile.getTileSize().getX());
					final int th = (int) Math.rint(tile.getTileSize().getY());
					if ((px < tx + tw) && (px + pw > tx)) {
						if ((py < ty + th) && (py + ph > ty)) {
							int ox = 0;
							int oy = 0;
							int ow = pw;
							int oh = ph;
							if (tx > px) {
								ox = tx - px;
							}
							if (ty > py) {
								oy = ty - py;
							}
							if (ox + ow > pw) {
								ow = pw - ox;
							}
							if (oy + oh > ph) {
								oh = ph - oy;
							}
							if ((ow > 0) && (oh > 0) && (ox >= 0) && (oy >= 0)) {
								g2d.setColor(Color.RED);
								g2d.setClip(tx, ty, tw, th);
								manager.drawImage(g2d, px - tx + ox, py - ty + oy, ow, oh);
								g2d.drawRect(px - tx + ox - 1, py - ty + oy - 1, ow, oh);
								final MandelbrotFractalRuntimeElement fractal = mandelbrotRuntime.getMandelbrotFractal();
								if (fractal != null) {
									final RenderingFormulaRuntimeElement formula = fractal.getRenderingFormula();
									if (formula != null) {
										if (formula.getFormulaRuntime() != null) {
											final DoubleVector2D scale = formula.getFormulaRuntime().getScale();
											final DoubleVector2D center = formula.getFormulaRuntime().getCenter();
											final DoubleVector2D constant = getConfig().getMandelbrotConfig().getConstant();
											final View view = getConfig().getMandelbrotConfig().getView();
											final double x = view.getPosition().getX();
											final double y = view.getPosition().getY();
											final double z = view.getPosition().getZ();
											final double a = view.getRotation().getZ();
											final double qx = (constant.getX() - center.getX() - x) / (z * scale.getX());
											final double qy = (constant.getY() - center.getY() - y) / (z * scale.getY());
											final double cx = (Math.cos(-a) * qx) + (Math.sin(-a) * qy);
											final double cy = (Math.cos(-a) * qy) - (Math.sin(-a) * qx);
											final int dx = (int) Math.rint(cx * tile.getImageSize().getX());
											final int dy = (int) Math.rint(cy * tile.getImageSize().getX());
											g2d.drawRect(dx + tile.getImageSize().getX() / 2 - tile.getTileOffset().getX() - 2, dy + tile.getImageSize().getY() / 2 - tile.getTileOffset().getY() - 2, 4, 4);
										}
									}
								}
							}
						}
					}
				}
				if (getConfig().getMandelbrotConfig().getShowOrbit()) {
					g2d.setColor(Color.YELLOW);
					final View view = getConfig().getMandelbrotConfig().getView();
					final double x = view.getPosition().getX();
					final double y = view.getPosition().getY();
					final double z = view.getPosition().getZ();
					final double a = view.getRotation().getZ();
					final int tw = tile.getImageSize().getX();
					final int th = tile.getImageSize().getY();
					final MandelbrotFractalRuntimeElement fractal = mandelbrotRuntime.getMandelbrotFractal();
					if (fractal != null) {
						final RenderingFormulaRuntimeElement formula = fractal.getRenderingFormula();
						if (formula != null) {
							if (formula.getFormulaRuntime() != null) {
								formula.getFormulaRuntime().prepareForRendering(fractal.getProcessingFormula().getFormulaRuntime(), fractal.getOrbitTrap().getOrbitTrapRuntime());
								if (fractal.getOrbitTrap().getOrbitTrapRuntime() != null) {
									fractal.getOrbitTrap().getOrbitTrapRuntime().prepareForProcessing(fractal.getOrbitTrap().getCenter());
								}
								final DoubleVector2D center = formula.getFormulaRuntime().getCenter();
								final DoubleVector2D scale = formula.getFormulaRuntime().getScale();
								final RenderedPoint rp = new RenderedPoint();
								rp.xr = formula.getFormulaRuntime().getInitialPoint().r;
								rp.xi = formula.getFormulaRuntime().getInitialPoint().i;
								rp.wr = getConfig().getMandelbrotConfig().getConstantElement().getValue().getX();
								rp.wi = getConfig().getMandelbrotConfig().getConstantElement().getValue().getY();
								final List<Complex> orbit = formula.getFormulaRuntime().renderOrbit(rp);
								g2d.setClip(0, 0, tw, th);
								if (orbit.size() > 1) {
									Complex pa = orbit.get(0);
									double zx = scale.getX() * z;
									double zy = scale.getY() * z;
									double tx = ((pa.r - center.getX() - x) / zx) * tw;
									double ty = ((pa.i - center.getY() - y) / zy) * tw;
									double ca = Math.cos(a);
									double sa = Math.sin(a);
									int px0 = (int) Math.rint((ca * tx) - (sa * ty)) + tw / 2 - tile.getTileOffset().getX();
									int py0 = (int) Math.rint((ca * ty) + (sa * tx)) + th / 2 - tile.getTileOffset().getY();
									for (int i = 1; i < orbit.size(); i++) {
										pa = orbit.get(i);
										tx = ((pa.r - center.getX() - x) / zx) * tw;
										ty = ((pa.i - center.getY() - y) / zy) * tw;
										int px1 = (int) Math.rint((ca * tx) - (sa * ty)) + tw / 2 - tile.getTileOffset().getX();
										int py1 = (int) Math.rint((ca * ty) + (sa * tx)) + th / 2 - tile.getTileOffset().getY();
										g2d.drawLine(px0, py0, px1, py1);
										px0 = px1;
										py0 = py1;
									}
								}
								g2d.setClip(null);
							}
						}
					}
				}
				if (getConfig().getMandelbrotConfig().getShowOrbitTrap()) {
					g2d.setColor(Color.RED);
					final View view = getConfig().getMandelbrotConfig().getView();
					final double x = view.getPosition().getX();
					final double y = view.getPosition().getY();
					final double z = view.getPosition().getZ();
					final double a = view.getRotation().getZ();
					final int tw = tile.getImageSize().getX();
					final int th = tile.getImageSize().getY();
					final MandelbrotFractalRuntimeElement fractal = mandelbrotRuntime.getMandelbrotFractal();
					if (fractal != null) {
						final RenderingFormulaRuntimeElement formula = fractal.getRenderingFormula();
						if (formula != null) {
							if (formula.getFormulaRuntime() != null) {
								formula.getFormulaRuntime().prepareForRendering(fractal.getProcessingFormula().getFormulaRuntime(), fractal.getOrbitTrap().getOrbitTrapRuntime());
								if (fractal.getOrbitTrap().getOrbitTrapRuntime() != null) {
									fractal.getOrbitTrap().getOrbitTrapRuntime().prepareForProcessing(fractal.getOrbitTrap().getCenter());
									final DoubleVector2D center = formula.getFormulaRuntime().getCenter();
									final DoubleVector2D scale = formula.getFormulaRuntime().getScale();
									double zx = scale.getX() * z;
									double zy = scale.getY() * z;
									Shape orbit = fractal.getOrbitTrap().getOrbitTrapRuntime().renderOrbitTrap(tw / zx, tw / zy, -a);
									if (orbit != null) {
										final DoubleVector2D c = fractal.getOrbitTrap().getCenter();
										double tx = ((+c.getX() - center.getX() - x) * tw) / zx;
										double ty = ((-c.getY() - center.getY() - y) * tw) / zy;
										double ca = Math.cos(a);
										double sa = Math.sin(a);
										double px = (int) Math.rint((ca * tx) - (sa * ty)) + tw / 2 - tile.getTileOffset().getX();
										double py = (int) Math.rint((ca * ty) + (sa * tx)) + th / 2 - tile.getTileOffset().getY();
										AffineTransform tmpTransform = g2d.getTransform();
										AffineTransform transform = AffineTransform.getTranslateInstance(px, py);
										g2d.setClip(0, 0, tw, th);
										g2d.setTransform(transform);
										g2d.draw(orbit);
										g2d.setClip(null);
										g2d.setTransform(tmpTransform);
									}
								}
							}
						}
					}
				}
				if (dirty) {
					dirty = false;
					manager.asyncStart();
					// try {
					// fractalManager.joinRenderer();
					// fractalManager.startRenderer();
					// }
					// catch (final InterruptedException e) {
					// Thread.currentThread().interrupt();
					// }
				}
			}
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#drawImage(java.awt.Graphics2D, int, int)
		 */
		public void drawImage(final Graphics2D g2d, final int x, final int y) {
			this.drawImage(g2d);
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#drawImage(java.awt.Graphics2D, int, int, int, int)
		 */
		public void drawImage(final Graphics2D g2d, final int x, final int y, final int w, final int h) {
			this.drawImage(g2d);
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#prepareImage(boolean)
		 */
		public void prepareImage(final boolean isDynamicRequired) {
			if (isDynamicRequired) {
				if (!suspended) {
					boolean isChanged = mandelbrotRuntime.isChanged();
					isChanged |= mandelbrotRuntime.isViewChanged();
					if (isChanged) {
						loadConfig();
						dirty = true;
						manager.asyncStop();
					}
				}
			}
			else {
				loadConfig();
			}
		}

		private void loadConfig() {
			if (getConfig().getMandelbrotConfig().getImageMode() == 0) {
				manager.setMandelbrotMode(1);
			}
			else {
				manager.setMandelbrotMode(0);
			}
			manager.setConstant(getConfig().getMandelbrotConfig().getConstant());
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#dispose()
		 */
		public void dispose() {
			if (manager != null) {
				manager.stop();
				manager.dispose();
				manager = null;
			}
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.image.MandelbrotImageRuntime.RendererStrategy#isDynamic()
		 */
		public boolean isDynamic() {
			return false;
		}
	}
}
