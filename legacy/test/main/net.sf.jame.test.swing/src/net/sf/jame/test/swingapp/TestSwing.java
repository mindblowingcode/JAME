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
package net.sf.jame.test.swingapp;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TestSwing {
	public static final int RESTART = 1;
	private final Object monitor = new Object();
	private volatile boolean running;
	private volatile int result = 0;
	private JFrame frame;

	public void run() throws Exception {
		try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					frame = new JFrame();
					frame.setTitle("SwingTest");
					frame.setSize(new Dimension(400, 300));
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							stop();
						}
					});
					frame.setVisible(true);
				}
			});
			running = true;
			synchronized (monitor) {
				while (running) {
					monitor.wait(30000);
				}
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					if (frame != null) {
						frame.dispose();
					}
				}
			});
		} catch (InterruptedException e) {
		}
	}

	public void stop() {
		synchronized (monitor) {
			running = false;
			monitor.notify();
		}
	}

	public int getResult() {
		return result;
	}
}
