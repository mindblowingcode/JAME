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
package net.sf.jame.media;

import java.util.LinkedList;
import java.util.List;

abstract class AbstractRenderedImage extends AbstractImage implements Animate {
	private List<AnimationListener> listeners = new LinkedList<AnimationListener>();
	private int frames;
	private int frame;

	protected AbstractRenderedImage(int frames) {
		this.frames = frames;
	}

	public final void setFrame(int frame) {
		this.frame = frame;
		fireFrameChanged(frame);
	}

	public final void nextFrame() {
		frame = (frame + 1) % frames;
		fireFrameChanged(frame);
	}

	public final void prevFrame() {
		frame = (frame - 1) % frames;
		fireFrameChanged(frame);
	}

	public final int getFrame() {
		return frame;
	}

	public final int getFrames() {
		return frames;
	}

	public final void addAnimationListener(AnimationListener listener) {
		listeners.add(listener);
	}

	public final void removeAnimationListener(AnimationListener listener) {
		listeners.remove(listener);
	}

	protected final void fireFrameChanged(int frame) {
		for (AnimationListener listener : listeners) {
			listener.frameChanged(frame);
		}
	}

	@Override
	abstract void build(final Controller controller, final Movie parent, final Layer layer, final Sequence sequence);

	@Override
	abstract void init();

	@Override
	abstract void kill();

	@Override
	abstract void reset();

	abstract void render();

	abstract void update();
}
