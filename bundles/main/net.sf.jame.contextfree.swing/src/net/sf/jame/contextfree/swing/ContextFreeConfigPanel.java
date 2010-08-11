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
package net.sf.jame.contextfree.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.undo.UndoManager;

import net.sf.jame.contextfree.CFDGBuilder;
import net.sf.jame.contextfree.ContextFreeConfig;
import net.sf.jame.contextfree.parser.ContextFreeParser;
import net.sf.jame.contextfree.parser.ContextFreeParserException;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.core.swing.ViewContext;
import net.sf.jame.core.swing.extension.ExtensionListCellRenderer;
import net.sf.jame.core.swing.util.GUIFactory;
import net.sf.jame.core.swing.util.GUIUtil;
import net.sf.jame.core.swing.util.StackLayout;
import net.sf.jame.core.tree.NodeSession;
import net.sf.jame.core.tree.NodeSessionListener;
import net.sf.jame.core.util.DefaultThreadFactory;
import net.sf.jame.core.util.DoubleVector4D;
import net.sf.jame.core.util.RenderContext;
import net.sf.jame.core.util.Worker;
import net.sf.jame.twister.swing.TwisterConfigPanel;
import net.sf.jame.twister.swing.TwisterSwingResources;
import net.sf.jame.twister.swing.ViewPanel;
import net.sf.jame.twister.util.Speed;

import org.apache.log4j.Logger;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeConfigPanel extends ViewPanel {
	private static final Logger logger = Logger.getLogger(ContextFreeConfigPanel.class);
	private static final long serialVersionUID = 1L;
	private final ContextFreeImagePanel imagePanel;
	private final ViewContext viewContext;
	private final RenderContext context;
	private final NodeSession session;
	private final ValueChangeListener configListener;
	private final ContextFreeConfig config;
	private final Color oddColor;
	private final Color evenColor;
	private NodeSessionListener sessionListener;
	private Worker worker = new Worker(new DefaultThreadFactory("ContextFree Parser", true, Thread.NORM_PRIORITY - 1));

	/**
	 * @param config
	 * @param viewContext
	 * @param context
	 * @param session
	 */
	public ContextFreeConfigPanel(final ContextFreeConfig config, final ViewContext viewContext, final RenderContext context, final NodeSession session) {
		this.viewContext = viewContext;
		this.context = context;
		this.session = session;
		this.config = config;
		oddColor = getBackground().brighter();
		evenColor = getBackground().brighter();
		imagePanel = new ContextFreeImagePanel(config);
		setLayout(new StackLayout());
		add(imagePanel);
		setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.DARK_GRAY));
		sessionListener = new NodeSessionListener() {
			public void fireSessionChanged() {
			}
			
			public void fireSessionCancelled() {
				imagePanel.refreshCFDG();
			}
			
			public void fireSessionAccepted() {
				imagePanel.refreshCFDG();
			}
		};
		session.addSessionListener(sessionListener);
		configListener = new ValueChangeListener() {
			public void valueChanged(final ValueChangeEvent e) {
				switch (e.getEventType()) {
					case ValueConfigElement.VALUE_CHANGED: {
						viewContext.setComponent(ContextFreeConfigPanel.this);
						break;
					}
					default: {
						break;
					}
				}
			}
		};
		config.getCFDGSingleElement().addChangeListener(configListener);
		worker.start();
	}

	@Override
	public void dispose() {
		worker.stop();
		config.getCFDGSingleElement().removeChangeListener(configListener);
		session.removeSessionListener(sessionListener);
		imagePanel.dispose();
	}

	private static JCheckBox createIconCheckBox(final String key, final String iconKey, final int width, final int height) {
		final JCheckBox checkbox = GUIFactory.createCheckBox((String) null, ContextFreeSwingResources.getInstance().getString("tooltip." + key));
		try {
			checkbox.setIcon(new ImageIcon(ImageIO.read(ContextFreeConfigPanel.class.getResourceAsStream("/icons/" + iconKey + "-icon.png"))));
			checkbox.setSelectedIcon(new ImageIcon(ImageIO.read(ContextFreeConfigPanel.class.getResourceAsStream("/icons/" + iconKey + "-selected-icon.png"))));
		}
		catch (final Exception e) {
			System.out.println("key = " + key + ", iconKey = " + iconKey);
			e.printStackTrace();
		}
		checkbox.setOpaque(false);
		checkbox.setBorderPainted(false);
		checkbox.setPreferredSize(new Dimension(width, height));
		checkbox.setMinimumSize(new Dimension(width, height));
		checkbox.setMaximumSize(new Dimension(width, height));
		return checkbox;
	}

	private static JButton createIconButton(final String key, final String iconKey, final int width, final int height) {
		final JButton button = GUIFactory.createButton((String) null, ContextFreeSwingResources.getInstance().getString("tooltip." + key));
		try {
			button.setIcon(new ImageIcon(ImageIO.read(ContextFreeConfigPanel.class.getResourceAsStream("/icons/" + iconKey + "-icon.png"))));
			button.setPressedIcon(new ImageIcon(ImageIO.read(ContextFreeConfigPanel.class.getResourceAsStream("/icons/" + iconKey + "-pressed-icon.png"))));
		}
		catch (final Exception e) {
			System.out.println("key = " + key + ", iconKey = " + iconKey);
			e.printStackTrace();
		}
		button.setOpaque(false);
		button.setPreferredSize(new Dimension(width, height));
		button.setMinimumSize(new Dimension(width, height));
		button.setMaximumSize(new Dimension(width, height));
		return button;
	}

	// private static JCheckBox createSelectionCheckBox() {
	// final JCheckBox checkbox = GUIFactory.createSmallCheckBox((String) null, (String) null);
	// checkbox.setOpaque(false);
	// checkbox.setPreferredSize(new Dimension(20, 20));
	// checkbox.setMinimumSize(new Dimension(20, 20));
	// checkbox.setMaximumSize(new Dimension(20, 20));
	// return checkbox;
	// }
	private static JCheckBox createCheckBox() {
		final JCheckBox checkbox = GUIFactory.createSmallCheckBox((String) null, (String) null);
		checkbox.setOpaque(false);
		return checkbox;
	}

	// private static JCheckBox createTextCheckBox(final String key, final int width, final int height) {
	// final JCheckBox checkbox = GUIFactory.createCheckBox(ContextFreeSwingResources.getInstance().getString("label." + key), ContextFreeSwingResources.getInstance().getString("tooltip." + key));
	// // final FontMetrics fm = checkbox.getFontMetrics(checkbox.getFont());
	// // int width = fm.stringWidth(checkbox.getText()) + 20;
	// checkbox.setPreferredSize(new Dimension(width, height));
	// checkbox.setMinimumSize(new Dimension(width, height));
	// checkbox.setMaximumSize(new Dimension(width, height));
	// checkbox.setOpaque(false);
	// return checkbox;
	// }
	private static JButton createTextButton(final int width, final int height) {
		final JButton button = GUIFactory.createSmallButton((String) null, (String) null);
		button.setPreferredSize(new Dimension(width, height));
		button.setMinimumSize(new Dimension(width, height));
		button.setMaximumSize(new Dimension(width, height));
		button.setOpaque(false);
		return button;
	}

	// private static JButton createTextButton(final String key, final int width, final int height) {
	// final JButton button = GUIFactory.createSmallButton(ContextFreeSwingResources.getInstance().getString("label." + key), ContextFreeSwingResources.getInstance().getString("tooltip." + key));
	// // final FontMetrics fm = button.getFontMetrics(button.getFont());
	// // int width = fm.stringWidth(button.getText());
	// button.setPreferredSize(new Dimension(width, height));
	// button.setMinimumSize(new Dimension(width, height));
	// button.setMaximumSize(new Dimension(width, height));
	// button.setOpaque(false);
	// return button;
	// }
	private static JButton createIconTextButton(final String key, final String iconKey, final int width, final int height) {
		final JButton button = GUIFactory.createSmallButton(TwisterSwingResources.getInstance().getString("label." + key), TwisterSwingResources.getInstance().getString("tooltip." + key));
		// final FontMetrics fm = button.getFontMetrics(button.getFont());
		// int width = fm.stringWidth(button.getText());
		try {
			button.setIcon(new ImageIcon(ImageIO.read(TwisterConfigPanel.class.getResourceAsStream("/icons/" + iconKey + "-icon.png"))));
			// button.setPressedIcon(new ImageIcon(ImageIO.read(TwisterConfigPanel.class.getResourceAsStream("/icons/" + iconKey + "-pressed-icon.png"))));
		}
		catch (final Exception e) {
			System.out.println("key = " + key + ", iconKey = " + iconKey);
			e.printStackTrace();
		}
		button.setPreferredSize(new Dimension(width, height));
		button.setMinimumSize(new Dimension(width, height));
		button.setMaximumSize(new Dimension(width, height));
		button.setOpaque(false);
		return button;
	}

	private static JSpinner createSpinner(final int min, final int max, final int step) {
		final JSpinner spinner = GUIFactory.createSpinner(new SpinnerNumberModel(0, min, max, step), (String) null);
		// spinner.setPreferredSize(new Dimension(60, GUIFactory.DEFAULT_HEIGHT));
		spinner.setMaximumSize(new Dimension(60, GUIFactory.DEFAULT_HEIGHT));
		spinner.setMinimumSize(new Dimension(60, GUIFactory.DEFAULT_HEIGHT));
		// if (!"Mac OS X".equals(System.getProperty("os.name")) || !UIManager.getLookAndFeel().isNativeLookAndFeel()) {
		// spinner.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, Color.DARK_GRAY));
		// }
		return spinner;
	}

	private static JComboBox createExtensionComboBox(final ComboBoxModel model, final int width, final int height) {
		final JComboBox extensionComboBox = GUIFactory.createSmallComboBox(model, (String) null);
		extensionComboBox.setRenderer(new ExtensionListCellRenderer());
		extensionComboBox.setPreferredSize(new Dimension(width, height));
		extensionComboBox.setMaximumSize(new Dimension(width, height));
		extensionComboBox.setMinimumSize(new Dimension(width, height));
		extensionComboBox.setOpaque(false);
		return extensionComboBox;
	}

	private static JLabel createTextLabel(final String key, final int alignment, final int width, final int height) {
		final JLabel label = GUIFactory.createSmallLabel(ContextFreeSwingResources.getInstance().getString("label." + key), SwingConstants.LEFT);
		label.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
		label.setPreferredSize(new Dimension(width, height));
		label.setMinimumSize(new Dimension(width, height));
		label.setMaximumSize(new Dimension(width, height));
		return label;
	}

	// private static JLabel createLabel(final String text) {
	// final JLabel label = GUIFactory.createSmallLabel(text, SwingConstants.LEFT);
	// label.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
	// return label;
	// }
	private static JTextField createTextField(final String text, final int width, final int height) {
		final JTextField textfield = GUIFactory.createTextField(text, null);
		textfield.setPreferredSize(new Dimension(width, height));
		textfield.setMinimumSize(new Dimension(width, height));
		textfield.setMaximumSize(new Dimension(width, height));
		// if (!"Mac OS X".equals(System.getProperty("os.name")) || !UIManager.getLookAndFeel().isNativeLookAndFeel()) {
		// textfield.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, Color.DARK_GRAY));
		// }
		return textfield;
	}

	private static JPanel createPanel(final LayoutManager layoutManager, final boolean opaque) {
		final JPanel panel = new JPanel(layoutManager);
		panel.setOpaque(opaque);
		return panel;
	}

	private static Box createHorizontalBox(final boolean opaque) {
		final Box box = Box.createHorizontalBox();
		box.setOpaque(opaque);
		return box;
	}

	private static Box createVerticalBox(final boolean opaque) {
		final Box box = Box.createVerticalBox();
		box.setOpaque(opaque);
		return box;
	}

	private static Component createSpace() {
		final Component box = Box.createHorizontalStrut(4);
		return box;
	}

	private class ContextFreeImagePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private final ValueChangeListener speedListener;
		private final ContextFreeConfig config;
		private UndoManager undoManager = new UndoManager();
		private JEditorPane editorPane;
//		private JButton editButton;
		private JButton loadButton;
		private JButton renderButton;
		private JFileChooser chooser;
//		private JFrame editFrame;

		public ContextFreeImagePanel(final ContextFreeConfig config) {
			this.config = config;
			final JLabel zoomSpeedLabel = createTextLabel("zoomSpeed", SwingConstants.LEFT, 200, GUIFactory.DEFAULT_HEIGHT);
			final JLabel shiftSpeedLabel = createTextLabel("shiftSpeed", SwingConstants.LEFT, 200, GUIFactory.DEFAULT_HEIGHT);
			final JLabel rotationSpeedLabel = createTextLabel("rotationSpeed", SwingConstants.LEFT, 200, GUIFactory.DEFAULT_HEIGHT);
			final JTextField zoomSpeedTextfield = createTextField(String.valueOf(config.getSpeed().getPosition().getZ()), 200, GUIFactory.DEFAULT_HEIGHT);
			final JTextField shiftSpeedTextfield = createTextField(String.valueOf(config.getSpeed().getPosition().getW()), 200, GUIFactory.DEFAULT_HEIGHT);
			final JTextField rotationSpeedTextfield = createTextField(String.valueOf(config.getSpeed().getRotation().getZ()), 200, GUIFactory.DEFAULT_HEIGHT);
			final Box tmpPanel4 = createHorizontalBox(false);
			tmpPanel4.add(zoomSpeedLabel);
			tmpPanel4.add(createSpace());
			tmpPanel4.add(zoomSpeedTextfield);
			tmpPanel4.add(Box.createHorizontalGlue());
			final Box tmpPanel5 = createHorizontalBox(false);
			tmpPanel5.add(rotationSpeedLabel);
			tmpPanel5.add(createSpace());
			tmpPanel5.add(rotationSpeedTextfield);
			tmpPanel5.add(Box.createHorizontalGlue());
			final Box tmpPanel6 = createHorizontalBox(false);
			tmpPanel6.add(shiftSpeedLabel);
			tmpPanel6.add(createSpace());
			tmpPanel6.add(shiftSpeedTextfield);
			tmpPanel6.add(Box.createHorizontalGlue());
//			editFrame = new JFrame();
//			editFrame.setTitle(ContextFreeSwingResources.getInstance().getString("message.edit"));
			editorPane = new JEditorPane();
			editorPane.getDocument().addUndoableEditListener(undoManager);
			JScrollPane scrollPane = new JScrollPane(editorPane);
			Dimension preferredSize = new Dimension(550, 170);
			scrollPane.setPreferredSize(preferredSize);
			scrollPane.setMinimumSize(preferredSize);
			scrollPane.setMaximumSize(preferredSize);
//			editFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
//			editFrame.setSize(600, 400);
//			GUIUtil.centerWindow(editFrame);
//			editorPane.setContentType("text/plain");
//			editButton = createTextButton(80, GUIFactory.DEFAULT_HEIGHT);
//			editButton.setToolTipText(ContextFreeSwingResources.getInstance().getString("tooltip.edit"));
//			editButton.setText(ContextFreeSwingResources.getInstance().getString("action.edit"));
			loadButton = createTextButton(80, GUIFactory.DEFAULT_HEIGHT);
			loadButton.setToolTipText(ContextFreeSwingResources.getInstance().getString("tooltip.load"));
			loadButton.setText(ContextFreeSwingResources.getInstance().getString("action.load"));
			renderButton = createTextButton(80, GUIFactory.DEFAULT_HEIGHT);
			renderButton.setToolTipText(ContextFreeSwingResources.getInstance().getString("tooltip.render"));
			renderButton.setText(ContextFreeSwingResources.getInstance().getString("action.render"));
//			final Box tmpPanel1 = createHorizontalBox(false);
//			tmpPanel1.add(createTextLabel("edit", JLabel.LEFT, 200, GUIFactory.DEFAULT_HEIGHT));
//			tmpPanel1.add(createSpace());
//			tmpPanel1.add(editButton);
//			tmpPanel1.add(Box.createHorizontalGlue());
//			final Box tmpPanel2 = createHorizontalBox(false);
//			tmpPanel2.add(createTextLabel("load", JLabel.LEFT, 200, GUIFactory.DEFAULT_HEIGHT));
//			tmpPanel2.add(createSpace());
//			tmpPanel2.add(loadButton);
//			tmpPanel2.add(Box.createHorizontalGlue());
//			final Box tmpPanel3 = createHorizontalBox(false);
//			tmpPanel3.add(createTextLabel("render", JLabel.LEFT, 200, GUIFactory.DEFAULT_HEIGHT));
//			tmpPanel3.add(createSpace());
//			tmpPanel3.add(renderButton);
//			tmpPanel3.add(Box.createHorizontalGlue());
			JLabel editLabel = createTextLabel("drawing", JLabel.LEFT, 200, GUIFactory.DEFAULT_HEIGHT);
			final Box tmp3Panel = createVerticalBox(false);
			tmp3Panel.add(editLabel);
			tmp3Panel.add(Box.createVerticalGlue());
			final Box tmpPanel1 = createHorizontalBox(false);
			tmpPanel1.add(tmp3Panel);
			tmpPanel1.add(createSpace());
			tmpPanel1.add(scrollPane);
			tmpPanel1.add(Box.createHorizontalGlue());
			final Box tmpPanel2 = createHorizontalBox(false);
			tmpPanel2.add(Box.createHorizontalStrut(200));
			tmpPanel2.add(createSpace());
			tmpPanel2.add(loadButton);
			tmpPanel2.add(createSpace());
			tmpPanel2.add(renderButton);
			tmpPanel2.add(Box.createHorizontalGlue());
			final Box tmpPanel = createVerticalBox(false);
			tmpPanel.add(Box.createVerticalStrut(8));
			tmpPanel.add(tmpPanel1);
			tmpPanel.add(Box.createVerticalStrut(8));
			tmpPanel.add(tmpPanel2);
//			tmpPanel.add(Box.createVerticalStrut(8));
//			tmpPanel.add(tmpPanel3);
//			tmpPanel.add(Box.createVerticalStrut(8));
//			tmpPanel.add(tmpPanel4);
//			tmpPanel.add(Box.createVerticalStrut(8));
//			tmpPanel.add(tmpPanel6);
//			tmpPanel.add(Box.createVerticalStrut(8));
//			tmpPanel.add(tmpPanel5);
			tmpPanel.add(Box.createVerticalStrut(8));
			setLayout(new BorderLayout());
			add(tmpPanel, BorderLayout.SOUTH);
			setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));
			setOpaque(false);
			final ActionListener zoomSpeedActionListener = new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					try {
						context.acquire();
						config.getContext().updateTimestamp();
						Speed speed = config.getSpeed();
						Speed newSpeed = new Speed(new DoubleVector4D(0, 0, Double.valueOf(zoomSpeedTextfield.getText()), speed.getPosition().getW()), speed.getRotation());
						config.setSpeed(newSpeed);
						context.release();
						context.refresh();
					}
					catch (InterruptedException x) {
						Thread.currentThread().interrupt();
					}
				}
			};
			zoomSpeedTextfield.addActionListener(zoomSpeedActionListener);
			final ActionListener shiftSpeedActionListener = new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					try {
						context.acquire();
						config.getContext().updateTimestamp();
						Speed speed = config.getSpeed();
						Speed newSpeed = new Speed(new DoubleVector4D(0, 0, speed.getPosition().getZ(), Double.valueOf(shiftSpeedTextfield.getText())), speed.getRotation());
						config.setSpeed(newSpeed);
						context.release();
						context.refresh();
					}
					catch (InterruptedException x) {
						Thread.currentThread().interrupt();
					}
				}
			};
			shiftSpeedTextfield.addActionListener(shiftSpeedActionListener);
			final ActionListener rotationSpeedActionListener = new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					try {
						context.acquire();
						config.getContext().updateTimestamp();
						Speed speed = config.getSpeed();
						Speed newSpeed = new Speed(speed.getPosition(), new DoubleVector4D(0, 0, Double.valueOf(rotationSpeedTextfield.getText()), 0));
						config.setSpeed(newSpeed);
						context.release();
						context.refresh();
					}
					catch (InterruptedException x) {
						Thread.currentThread().interrupt();
					}
				}
			};
			rotationSpeedTextfield.addActionListener(rotationSpeedActionListener);
			final ActionListener loadActionListener = new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					if (chooser == null) {
						chooser = new JFileChooser();
						chooser.setDialogTitle(ContextFreeSwingResources.getInstance().getString("label.selectFile"));
						chooser.setMultiSelectionEnabled(false);
					}
					if (chooser.showOpenDialog(ContextFreeConfigPanel.this) == JFileChooser.APPROVE_OPTION) {
						File file = chooser.getSelectedFile();
						if (file != null) {
							loadConfig(config, file);
						}
					}
				}
			};
			loadButton.addActionListener(loadActionListener);
			final ActionListener renderActionListener = new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					renderConfig(config, editorPane.getText());
				}
			};
			renderButton.addActionListener(renderActionListener);
//			final ActionListener editActionListener = new ActionListener() {
//				public void actionPerformed(final ActionEvent e) {
//					editFrame.setVisible(true);
//				}
//			};
//			editButton.addActionListener(editActionListener);
			final KeyListener keyListener = new KeyListener() {
				public void keyTyped(KeyEvent e) {
				}
				
				public void keyPressed(KeyEvent e) {
				}
				
				public void keyReleased(KeyEvent e) {
					if ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0 || (e.getModifiersEx() & KeyEvent.META_DOWN_MASK) != 0) {
						if (e.getKeyCode() == KeyEvent.VK_Z) {
							if (undoManager.canUndo()) {
								undoManager.undo();
							}
						} else if (e.getKeyCode() == KeyEvent.VK_Y) {
							if (undoManager.canRedo()) {
								undoManager.redo();
							}
						}
					}
				}
			};
			editorPane.addKeyListener(keyListener);
			speedListener = new ValueChangeListener() {
				public void valueChanged(final ValueChangeEvent e) {
					zoomSpeedTextfield.removeActionListener(zoomSpeedActionListener);
					shiftSpeedTextfield.removeActionListener(shiftSpeedActionListener);
					rotationSpeedTextfield.removeActionListener(rotationSpeedActionListener);
					zoomSpeedTextfield.setText(String.valueOf(config.getSpeed().getPosition().getZ()));
					shiftSpeedTextfield.setText(String.valueOf(config.getSpeed().getPosition().getW()));
					rotationSpeedTextfield.setText(String.valueOf(config.getSpeed().getRotation().getZ()));
					zoomSpeedTextfield.addActionListener(zoomSpeedActionListener);
					shiftSpeedTextfield.addActionListener(shiftSpeedActionListener);
					rotationSpeedTextfield.addActionListener(rotationSpeedActionListener);
				}
			};
			config.getSpeedElement().addChangeListener(speedListener);
			refreshCFDG();
		}

		public void enableButtons() {
			loadButton.setEnabled(true);
			renderButton.setEnabled(true);
		}

		public void disableButtons() {
			loadButton.setEnabled(false);
			renderButton.setEnabled(false);
		}

		public void dispose() {
			config.getSpeedElement().removeChangeListener(speedListener);
		}

		public void refreshCFDG() {
			GUIUtil.executeTask(new Runnable() {
				public void run() {
					disableButtons();
				}
			}, false);
			worker.addTask(new Runnable() {
				public void run() {
					final CFDGBuilder builder = new CFDGBuilder();
					try {
						context.acquire();
						config.getCFDG().toCFDG(builder);
						context.release();
					}
					catch (InterruptedException x) {
						Thread.currentThread().interrupt();
					} finally {
						GUIUtil.executeTask(new Runnable() {
							public void run() {
								editorPane.setText(builder.toString());
								enableButtons();
							}
						}, false);
					}
				}
			});
		}

		private void renderConfig(final ContextFreeConfig config, final String text) {
			GUIUtil.executeTask(new Runnable() {
				public void run() {
					disableButtons();
				}
			}, false);
			worker.addTask(new Runnable() {
				public void run() {
					try {
						context.acquire();
						session.removeSessionListener(sessionListener);
						config.getContext().updateTimestamp();
						loadConfig(config, text);
						session.addSessionListener(sessionListener);
						context.release();
						context.refresh();
					}
					catch (InterruptedException x) {
						Thread.currentThread().interrupt();
					} finally {
						GUIUtil.executeTask(new Runnable() {
							public void run() {
								enableButtons();
							}
						}, false);
					}
				}
			});
		}

		private void loadConfig(final ContextFreeConfig config, final File file) {
			GUIUtil.executeTask(new Runnable() {
				public void run() {
					disableButtons();
				}
			}, false);
			worker.addTask(new Runnable() {
				public void run() {
					final StringBuilder builder = new StringBuilder();
					BufferedReader reader = null;
					try {
						reader = new BufferedReader(new FileReader(file));
						String line = null;
						while ((line = reader.readLine()) != null) {
							builder.append(line);
							builder.append("\n");
						}
						context.acquire();
						session.removeSessionListener(sessionListener);
						config.getContext().updateTimestamp();
						loadConfig(config, builder.toString());
						session.addSessionListener(sessionListener);
						context.release();
						context.refresh();
					}
					catch (InterruptedException x) {
						Thread.currentThread().interrupt();
					} 
					catch (final Exception x) {
						GUIUtil.executeTask(new Runnable() {
							public void run() {
								JOptionPane.showMessageDialog(ContextFreeImagePanel.this, x.getMessage(), ContextFreeSwingResources.getInstance().getString("message.readerError"), JOptionPane.ERROR_MESSAGE);
							}
						}, false);
					} 
					finally {
						if (reader != null) {
							try {
								reader.close();
							} catch (IOException e1) {
							}
						}
						GUIUtil.executeTask(new Runnable() {
							public void run() {
								editorPane.setText(builder.toString());
								enableButtons();
							}
						}, false);
					}
				}
			});
		}

		private void loadConfig(final ContextFreeConfig config, String text) throws InterruptedException {
			try {
				ContextFreeParser parser = new ContextFreeParser();
				ContextFreeConfig newConfig = parser.parseConfig(text);
				config.setCFDG(newConfig.getCFDG());
			} catch (ContextFreeParserException x) {
				logger.error(x);
				JTextArea textArea = new JTextArea();
				textArea.setText(x.getMessage());
				JOptionPane.showMessageDialog(ContextFreeImagePanel.this, textArea, ContextFreeSwingResources.getInstance().getString("message.parserError"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
