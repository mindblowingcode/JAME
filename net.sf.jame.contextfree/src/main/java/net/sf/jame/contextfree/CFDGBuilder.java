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
package net.sf.jame.contextfree;

import java.util.Stack;

public class CFDGBuilder {
	private StringBuilder builder = new StringBuilder();
	private Stack<String> tabs = new Stack<String>();
	
	public CFDGBuilder() {
		tabs.push("");
	}
	
	public void addTab() {
		tabs.push(tabs.peek() + "\t");
	}

	public void removeTab() {
		if (tabs.size() > 1) {
			tabs.pop(); 
		}
	}

	public void append(String m) {
		builder.append(m);
	}

	public void append(Integer value) {
		builder.append(value);
	}

	public void append(Float value) {
		builder.append(value);
	}

	public void append(Double value) {
		builder.append(value);
	}
	
	public void appendTabs() {
		builder.append(tabs.peek());
	}
	
	@Override
	public String toString() {
		return builder.toString();
	}
}
