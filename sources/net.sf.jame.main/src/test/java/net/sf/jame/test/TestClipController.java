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
package net.sf.jame.test;

import java.util.List;

import net.sf.jame.core.DefaultTree;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.DefaultConfigContext;
import net.sf.jame.core.tree.DefaultNodeSession;
import net.sf.jame.core.tree.NodeAction;
import net.sf.jame.core.tree.NodeActionValue;
import net.sf.jame.twister.ControllerListener;
import net.sf.jame.twister.TwisterClip;
import net.sf.jame.twister.TwisterClipController;
import net.sf.jame.twister.TwisterConfig;
import net.sf.jame.twister.TwisterConfigBuilder;
import net.sf.jame.twister.TwisterConfigNodeBuilder;
import net.sf.jame.twister.TwisterSequence;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class TestClipController {
	@Test
	public void testUndoRedoAction() throws Exception {
		final TwisterConfigBuilder configBuilder = new TwisterConfigBuilder();
		final ConfigContext context = new DefaultConfigContext();
		final TwisterConfig config = configBuilder.createDefaultConfig();
		final TwisterConfigNodeBuilder builder = new TwisterConfigNodeBuilder(config);
		config.setContext(context);
		DefaultTree tree = null;
		TwisterSequence sequence = null;
		List<NodeAction> actions = null;
		TwisterClipController controller = null;
		long refTimestamp = 0;
		long time = 0;
		final TwisterClip clip = new TwisterClip();
		sequence = new TwisterSequence();
		sequence.setInitialConfig(config.clone());
		sequence.setFinalConfig(config.clone());
		time = System.currentTimeMillis();
		time = System.currentTimeMillis() - time;
		sequence.setDuration(time);
		clip.addSequence(sequence);
		tree = new DefaultTree();
		builder.createNodes(tree.getRootNode());
		tree.getRootNode().setContext(context);
		tree.getRootNode().setSession(new DefaultNodeSession("clip"));
		time = System.currentTimeMillis();
		sequence = new TwisterSequence();
		sequence.setInitialConfig(config.clone());
		refTimestamp = time;
		Thread.sleep(500);
		context.updateTimestamp();
		config.getFrameConfigElement().getLayerConfigElement(0).setVisible(false);
		Thread.sleep(500);
		context.updateTimestamp();
		config.getFrameConfigElement().getLayerConfigElement(0).setVisible(true);
		Thread.sleep(500);
		config.getFrameConfigElement().getLayerConfigElement(0).setVisible(false);
		Thread.sleep(500);
		context.updateTimestamp();
		config.getFrameConfigElement().getLayerConfigElement(0).setVisible(true);
		Thread.sleep(1000);
		sequence.setFinalConfig(config.clone());
		time = System.currentTimeMillis() - time;
		sequence.setDuration(time);
		clip.addSequence(sequence);
		actions = tree.getRootNode().getSession().getActions();
		for (NodeAction action : actions) {
			final NodeActionValue value = action.toActionValue();
			value.setTimestamp(value.getTimestamp() - refTimestamp);
			action = new NodeAction(value);
			sequence.addAction(action);
			System.out.println(action);
		}
		tree = new DefaultTree();
		builder.createNodes(tree.getRootNode());
		tree.getRootNode().setContext(context);
		tree.getRootNode().setSession(new DefaultNodeSession("clip"));
		time = System.currentTimeMillis();
		sequence = new TwisterSequence();
		sequence.setInitialConfig(config.clone());
		refTimestamp = time;
		Thread.sleep(500);
		context.updateTimestamp();
		config.getFrameConfigElement().getLayerConfigElement(0).setVisible(false);
		Thread.sleep(500);
		context.updateTimestamp();
		config.getFrameConfigElement().getLayerConfigElement(0).setVisible(true);
		Thread.sleep(500);
		context.updateTimestamp();
		config.getFrameConfigElement().getLayerConfigElement(0).setVisible(false);
		Thread.sleep(500);
		config.getFrameConfigElement().getLayerConfigElement(0).setVisible(true);
		Thread.sleep(1000);
		sequence.setFinalConfig(config.clone());
		time = System.currentTimeMillis() - time;
		sequence.setDuration(time);
		clip.addSequence(sequence);
		actions = tree.getRootNode().getSession().getActions();
		for (NodeAction action : actions) {
			final NodeActionValue value = action.toActionValue();
			value.setTimestamp(value.getTimestamp() - refTimestamp);
			action = new NodeAction(value);
			sequence.addAction(action);
			System.out.println(action);
		}
		sequence = new TwisterSequence();
		sequence.setInitialConfig(config.clone());
		sequence.setFinalConfig(config.clone());
		time = System.currentTimeMillis();
		time = System.currentTimeMillis() - time;
		sequence.setDuration(time);
		clip.addSequence(sequence);
		controller = new DebugClipConroller(clip);
		controller.init();
		System.out.println("controller.redoAction(true)");
		time = System.currentTimeMillis();
		for (int i = 0; i < 12; i++) {
			redoAction(controller, time, true);
		}
		controller.redoAll();
		System.out.println("controller.undoAction(true)");
		time = System.currentTimeMillis();
		for (int i = 0; i < 12; i++) {
			undoAction(controller, time, true);
		}
		controller.init();
		System.out.println("controller.redoAction(false)");
		time = System.currentTimeMillis();
		for (int i = 0; i < 12; i++) {
			redoAction(controller, time, false);
		}
		controller.redoAll();
		System.out.println("controller.undoAction(false)");
		time = System.currentTimeMillis();
		for (int i = 0; i < 12; i++) {
			undoAction(controller, time, false);
		}
		controller.init();
		System.out.println("controller.redoActionAndSleep()");
		time = System.currentTimeMillis();
		for (int i = 0; i < 12; i++) {
			redoActionAndSleep(controller, time);
		}
		controller.redoAll();
		System.out.println("controller.undoActionAndSleep()");
		time = System.currentTimeMillis();
		for (int i = 0; i < 12; i++) {
			undoActionAndSleep(controller, time);
		}
		controller.init();
		System.out.println("controller.redoAction(500)");
		time = System.currentTimeMillis();
		for (int i = 0; i < 12; i++) {
			redoAction(controller, time, 500);
		}
		controller.redoAll();
		System.out.println("controller.undoAction(500)");
		time = System.currentTimeMillis();
		for (int i = 0; i < 12; i++) {
			undoAction(controller, time, 500);
		}
		// controller.init();
		// System.out.println("controller.undoAll()");
		// controller.undoAll();
		// System.out.println("controller.redoAll()");
		// controller.redoAll();
	}

	private void print(final TwisterClipController controller, final long time, final long tvalue, final boolean value) {
		System.out.println((System.currentTimeMillis() - time) + ": " + (value ? tvalue : "-"));
	}

	private void redoAction(final TwisterClipController controller, final long time, final long timestamp) {
		final boolean value = controller.redoAction(timestamp, true);
		final long tvalue = controller.getTime();
		print(controller, time, tvalue, value);
	}

	private void undoAction(final TwisterClipController controller, final long time, final long timestamp) {
		final boolean value = controller.undoAction(timestamp, true);
		final long tvalue = controller.getTime();
		print(controller, time, tvalue, value);
	}

	private void redoActionAndSleep(final TwisterClipController controller, final long time) {
		final boolean value = controller.redoActionAndSleep();
		final long tvalue = controller.getTime();
		print(controller, time, tvalue, value);
	}

	private void undoActionAndSleep(final TwisterClipController controller, final long time) {
		final boolean value = controller.undoActionAndSleep();
		final long tvalue = controller.getTime();
		print(controller, time, tvalue, value);
	}

	private void redoAction(final TwisterClipController controller, final long time, final boolean sameTimestamp) {
		final boolean value = controller.redoAction(sameTimestamp);
		final long tvalue = controller.getTime();
		print(controller, time, tvalue, value);
	}

	private void undoAction(final TwisterClipController controller, final long time, final boolean sameTimestamp) {
		final boolean value = controller.undoAction(sameTimestamp);
		final long tvalue = controller.getTime();
		print(controller, time, tvalue, value);
	}

	private class DebugClipConroller extends TwisterClipController {
		public DebugClipConroller(final TwisterClip clip) {
			super(clip);
			addControllerListener(new ControllerListener() {
				public void actionRedone(final NodeAction action) {
					System.out.println("Redo: " + action);
				}

				public void actionUndone(final NodeAction action) {
					System.out.println("Undo: " + action);
				}

				public void configChanged() {
					System.out.println("Config changed");
				}
			});
		}
	}
}
