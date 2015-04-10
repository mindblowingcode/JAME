/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
package net.sf.jame.twister.swing;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.util.RenderContext;
import net.sf.jame.twister.TwisterConfig;
import net.sf.jame.twister.effect.EffectConfigElement;
import net.sf.jame.twister.effect.extension.EffectExtensionConfig;
import net.sf.jame.twister.frame.FrameConfigElement;
import net.sf.jame.twister.frameFilter.FrameFilterConfigElement;
import net.sf.jame.twister.frameFilter.extension.FrameFilterExtensionConfig;
import net.sf.jame.twister.image.ImageConfigElement;
import net.sf.jame.twister.image.extension.ImageExtensionConfig;
import net.sf.jame.twister.layer.GroupLayerConfigElement;
import net.sf.jame.twister.layer.ImageLayerConfigElement;
import net.sf.jame.twister.layerFilter.LayerFilterConfigElement;
import net.sf.jame.twister.layerFilter.extension.LayerFilterExtensionConfig;
import net.sf.jame.twister.swing.inputAdapter.DefaultInputAdapterRuntime;
import net.sf.jame.twister.swing.inputAdapter.extension.InputAdapterExtensionRuntime;
import net.sf.jame.twister.util.AdapterContext;

/**
 * @author Andrea Medeghini
 */
public class DefaultInputAdapter extends InputAdapter {
	private final RenderContext renderContext;
	private final TwisterConfig config;

	/**
	 * @param renderContext
	 * @param config
	 */
	public DefaultInputAdapter(final RenderContext renderContext, final TwisterConfig config) {
		this.renderContext = renderContext;
		this.config = config;
	}

	/**
	 * @see net.sf.jame.twister.swing.InputAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(final KeyEvent e) {
		processEvent(e, new EventProcessor<KeyEvent>() {
			public void process(final InputAdapterExtensionRuntime adapterRuntime, final KeyEvent e) {
				adapterRuntime.processKeyPressed(e);
			}
		});
	}

	/**
	 * @see net.sf.jame.twister.swing.InputAdapter#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(final KeyEvent e) {
		processEvent(e, new EventProcessor<KeyEvent>() {
			public void process(final InputAdapterExtensionRuntime adapterRuntime, final KeyEvent e) {
				adapterRuntime.processKeyReleased(e);
			}
		});
	}

	/**
	 * @see net.sf.jame.twister.swing.InputAdapter#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(final KeyEvent e) {
		processEvent(e, new EventProcessor<KeyEvent>() {
			public void process(final InputAdapterExtensionRuntime adapterRuntime, final KeyEvent e) {
				adapterRuntime.processKeyTyped(e);
			}
		});
	}

	/**
	 * @see net.sf.jame.twister.swing.InputAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(final MouseEvent e) {
		processEvent(e, new EventProcessor<MouseEvent>() {
			public void process(final InputAdapterExtensionRuntime adapterRuntime, final MouseEvent e) {
				adapterRuntime.processMouseClicked(e);
			}
		});
	}

	/**
	 * @see net.sf.jame.twister.swing.InputAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(final MouseEvent e) {
		processEvent(e, new EventProcessor<MouseEvent>() {
			public void process(final InputAdapterExtensionRuntime adapterRuntime, final MouseEvent e) {
				adapterRuntime.processMouseDragged(e);
			}
		});
	}

	/**
	 * @see net.sf.jame.twister.swing.InputAdapter#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(final MouseEvent e) {
		processEvent(e, new EventProcessor<MouseEvent>() {
			public void process(final InputAdapterExtensionRuntime adapterRuntime, final MouseEvent e) {
				adapterRuntime.processMouseExited(e);
			}
		});
	}

	/**
	 * @see net.sf.jame.twister.swing.InputAdapter#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(final MouseEvent e) {
		processEvent(e, new EventProcessor<MouseEvent>() {
			public void process(final InputAdapterExtensionRuntime adapterRuntime, final MouseEvent e) {
				adapterRuntime.processMouseEntered(e);
			}
		});
	}

	/**
	 * @see net.sf.jame.twister.swing.InputAdapter#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(final MouseEvent e) {
		processEvent(e, new EventProcessor<MouseEvent>() {
			public void process(final InputAdapterExtensionRuntime adapterRuntime, final MouseEvent e) {
				adapterRuntime.processMouseMoved(e);
			}
		});
	}

	/**
	 * @see net.sf.jame.twister.swing.InputAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(final MouseEvent e) {
		processEvent(e, new EventProcessor<MouseEvent>() {
			public void process(final InputAdapterExtensionRuntime adapterRuntime, final MouseEvent e) {
				adapterRuntime.processMousePressed(e);
			}
		});
	}

	/**
	 * @see net.sf.jame.twister.swing.InputAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(final MouseEvent e) {
		processEvent(e, new EventProcessor<MouseEvent>() {
			public void process(final InputAdapterExtensionRuntime adapterRuntime, final MouseEvent e) {
				adapterRuntime.processMouseReleased(e);
			}
		});
	}

	/**
	 * @see net.sf.jame.twister.swing.InputAdapter#refresh()
	 */
	@Override
	public void refresh() {
		final FrameConfigElement frameElement = config.getFrameConfigElement();
		if (frameElement != null) {
			final int groupLayerCount = frameElement.getLayerConfigElementCount();
			for (int i = 0; i < groupLayerCount; i++) {
				final GroupLayerConfigElement groupLayerElement = frameElement.getLayerConfigElement(i);
				if (!groupLayerElement.isLocked() && groupLayerElement.isVisible()) {
					final int imageLayerCount = groupLayerElement.getLayerConfigElementCount();
					for (int j = 0; j < imageLayerCount; j++) {
						final ImageLayerConfigElement imageLayerElement = groupLayerElement.getLayerConfigElement(j);
						if (!imageLayerElement.isLocked() && imageLayerElement.isVisible()) {
							final ImageConfigElement imageElement = imageLayerElement.getImageConfigElement();
							final ImageExtensionConfig imageConfig = getExtensionConfig(imageElement);
							if (imageConfig != null) {
								final AdapterContext context = imageConfig.getInputAdapterContext();
								if (context != null) {
									getInputAdapterRuntime(context, imageConfig).refresh();
								}
							}
							final int filterCount = imageLayerElement.getFilterConfigElementCount();
							for (int k = 0; k < filterCount; k++) {
								final LayerFilterConfigElement filterElement = imageLayerElement.getFilterConfigElement(k);
								if (!filterElement.isLocked() && filterElement.isEnabled()) {
									final LayerFilterExtensionConfig filterConfig = getExtensionConfig(filterElement);
									if (filterConfig != null) {
										final AdapterContext context = filterConfig.getInputAdapterContext();
										if (context != null) {
											getInputAdapterRuntime(context, filterConfig).refresh();
										}
									}
								}
							}
						}
					}
					final int filterCount = groupLayerElement.getFilterConfigElementCount();
					for (int k = 0; k < filterCount; k++) {
						final LayerFilterConfigElement filterElement = groupLayerElement.getFilterConfigElement(k);
						if (!filterElement.isLocked() && filterElement.isEnabled()) {
							final LayerFilterExtensionConfig filterConfig = getExtensionConfig(filterElement);
							if (filterConfig != null) {
								final AdapterContext context = filterConfig.getInputAdapterContext();
								if (context != null) {
									getInputAdapterRuntime(context, filterConfig).refresh();
								}
							}
						}
					}
				}
			}
			final int filterCount = frameElement.getFilterConfigElementCount();
			for (int k = 0; k < filterCount; k++) {
				final FrameFilterConfigElement filterElement = frameElement.getFilterConfigElement(k);
				if (!filterElement.isLocked() && filterElement.isEnabled()) {
					final FrameFilterExtensionConfig filterConfig = getExtensionConfig(filterElement);
					if (filterConfig != null) {
						final AdapterContext context = filterConfig.getInputAdapterContext();
						if (context != null) {
							getInputAdapterRuntime(context, filterConfig).refresh();
						}
					}
				}
			}
		}
		final EffectConfigElement effectElement = config.getEffectConfigElement();
		if (effectElement != null) {
			if (!effectElement.isLocked() && effectElement.isEnabled()) {
				final EffectExtensionConfig effectConfig = getExtensionConfig(effectElement);
				if (effectConfig != null) {
					final AdapterContext context = effectConfig.getInputAdapterContext();
					if (context != null) {
						getInputAdapterRuntime(context, effectConfig).refresh();
					}
				}
			}
		}
	}

	private ImageExtensionConfig getExtensionConfig(final ImageConfigElement element) {
		if ((element.getReference() != null) && (element.getReference().getExtensionConfig() != null)) {
			return element.getReference().getExtensionConfig();
		}
		return null;
	}

	private LayerFilterExtensionConfig getExtensionConfig(final LayerFilterConfigElement element) {
		if ((element.getReference() != null) && (element.getReference().getExtensionConfig() != null)) {
			return element.getReference().getExtensionConfig();
		}
		return null;
	}

	private FrameFilterExtensionConfig getExtensionConfig(final FrameFilterConfigElement element) {
		if ((element.getReference() != null) && (element.getReference().getExtensionConfig() != null)) {
			return element.getReference().getExtensionConfig();
		}
		return null;
	}

	private EffectExtensionConfig getExtensionConfig(final EffectConfigElement element) {
		if ((element.getReference() != null) && (element.getReference().getExtensionConfig() != null)) {
			return element.getReference().getExtensionConfig();
		}
		return null;
	}
	
	private InputAdapterExtensionRuntime getInputAdapterRuntime(final AdapterContext context, final ExtensionConfig config) {
		InputAdapterExtensionRuntime runtime = (InputAdapterExtensionRuntime) context.getAttribute("inputAdapterRuntime");
		try {
			if (runtime == null) {
				final Extension<InputAdapterExtensionRuntime> extension = TwisterSwingRegistry.getInstance().getInputAdapterExtension(config.getExtensionId());
				runtime = extension.createExtensionRuntime();
				runtime.init(renderContext, config);
				context.setAttribute("inputAdapterRuntime", runtime);
			}
		}
		catch (final ExtensionException x) {
			runtime = new DefaultInputAdapterRuntime();
			context.setAttribute("inputAdapterRuntime", runtime);
		}
		return runtime;
	}

	private <E> void processEvent(final E e, final EventProcessor<E> p) {
		final FrameConfigElement frameElement = config.getFrameConfigElement();
		if (frameElement != null) {
			final int groupLayerCount = frameElement.getLayerConfigElementCount();
			for (int i = 0; i < groupLayerCount; i++) {
				final GroupLayerConfigElement groupLayerElement = frameElement.getLayerConfigElement(i);
				if (!groupLayerElement.isLocked() && groupLayerElement.isVisible()) {
					final int imageLayerCount = groupLayerElement.getLayerConfigElementCount();
					for (int j = 0; j < imageLayerCount; j++) {
						final ImageLayerConfigElement imageLayerElement = groupLayerElement.getLayerConfigElement(j);
						if (!imageLayerElement.isLocked() && imageLayerElement.isVisible()) {
							final ImageConfigElement imageElement = imageLayerElement.getImageConfigElement();
							final ImageExtensionConfig imageConfig = getExtensionConfig(imageElement);
							if (imageConfig != null) {
								final AdapterContext context = imageConfig.getInputAdapterContext();
								if (context != null) {
									p.process(getInputAdapterRuntime(context, imageConfig), e);
								}
							}
							final int filterCount = imageLayerElement.getFilterConfigElementCount();
							for (int k = 0; k < filterCount; k++) {
								final LayerFilterConfigElement filterElement = imageLayerElement.getFilterConfigElement(k);
								if (!filterElement.isLocked() && filterElement.isEnabled()) {
									final LayerFilterExtensionConfig filterConfig = getExtensionConfig(filterElement);
									if (filterConfig != null) {
										final AdapterContext context = filterConfig.getInputAdapterContext();
										if (context != null) {
											p.process(getInputAdapterRuntime(context, filterConfig), e);
										}
									}
								}
							}
						}
					}
					final int filterCount = groupLayerElement.getFilterConfigElementCount();
					for (int k = 0; k < filterCount; k++) {
						final LayerFilterConfigElement filterElement = groupLayerElement.getFilterConfigElement(k);
						if (!filterElement.isLocked() && filterElement.isEnabled()) {
							final LayerFilterExtensionConfig filterConfig = getExtensionConfig(filterElement);
							if (filterConfig != null) {
								final AdapterContext context = filterConfig.getInputAdapterContext();
								if (context != null) {
									p.process(getInputAdapterRuntime(context, filterConfig), e);
								}
							}
						}
					}
				}
			}
			final int filterCount = frameElement.getFilterConfigElementCount();
			for (int k = 0; k < filterCount; k++) {
				final FrameFilterConfigElement filterElement = frameElement.getFilterConfigElement(k);
				if (!filterElement.isLocked() && filterElement.isEnabled()) {
					final FrameFilterExtensionConfig filterConfig = getExtensionConfig(filterElement);
					if (filterConfig != null) {
						final AdapterContext context = filterConfig.getInputAdapterContext();
						if (context != null) {
							p.process(getInputAdapterRuntime(context, filterConfig), e);
						}
					}
				}
			}
		}
		final EffectConfigElement effectElement = config.getEffectConfigElement();
		if (effectElement != null) {
			if (!effectElement.isLocked() && effectElement.isEnabled()) {
				final EffectExtensionConfig effectConfig = getExtensionConfig(effectElement);
				if (effectConfig != null) {
					final AdapterContext context = effectConfig.getInputAdapterContext();
					if (context != null) {
						p.process(getInputAdapterRuntime(context, effectConfig), e);
					}
				}
			}
		}
	}

	private interface EventProcessor<E> {
		public void process(InputAdapterExtensionRuntime adapterRuntime, E e);
	}
}
