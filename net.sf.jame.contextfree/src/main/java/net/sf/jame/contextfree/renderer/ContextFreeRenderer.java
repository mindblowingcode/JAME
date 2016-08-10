/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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
package net.sf.jame.contextfree.renderer;

import java.awt.Graphics2D;
import java.util.Map;

import net.sf.jame.contextfree.ContextFreeRuntime;
import net.sf.jame.core.util.Tile;
import net.sf.jame.twister.util.View;

/**
 * @author Andrea Medeghini
 */
public interface ContextFreeRenderer {
	/**
	 * 
	 */
	public static final int MODE_CALCULATE = 0x01;
	/**
	 * 
	 */
	public static final int MODE_REFRESH = 0x02;

	/**
	 * @param g the destination.
	 */
	public void drawImage(Graphics2D g);

	/**
	 * @param g
	 * @param x
	 * @param y
	 */
	public void drawImage(Graphics2D g, int x, int y);

	/**
	 * @param g
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawImage(Graphics2D g, int x, int y, int w, int h);

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
	 * @return true if is interrupted.
	 */
	public boolean isInterrupted();

	/**
	 * @return the status.
	 */
	public int getRenderingStatus();

	/**
	 * @param mode
	 */
	public void setMode(int mode);

	/**
	 * @return
	 */
	public int getMode();

	/**
	 * @return the runtime.
	 */
	public ContextFreeRuntime getRuntime();

	/**
	 * @param runtime the runtime.
	 */
	public void setRuntime(ContextFreeRuntime runtime);

	/**
	 * @param hints
	 */
	public void setRenderingHints(Map<Object, Object> hints);

	/**
	 * @param view
	 */
	public void setView(View view);

	/**
	 * @param tile
	 */
	public void setTile(Tile tile);

	/**
	 * @return
	 */
	public Tile getTile();

	/**
	 * @return
	 */
	public boolean isDynamic();

	/**
	 * @return
	 */
	public boolean isViewChanged();

	/**
	 * 
	 */
	public void dispose();

	/**
	 * 
	 */
	public void start();

	/**
	 * 
	 */
	public void stop();

	/**
	 * 
	 */
	public void asyncStart();

	/**
	 * 
	 */
	public void asyncStop();
}
