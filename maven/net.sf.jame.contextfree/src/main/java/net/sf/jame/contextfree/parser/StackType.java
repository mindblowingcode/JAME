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
package net.sf.jame.contextfree.parser;

import java.util.List;

class StackType {
	private double number;
	private StackRule rule;
	private StackRule ruleHeader;
	private List<ASTParameter> typeInfo;

	public StackType(double number) {
		// TODO Auto-generated constructor stub
	}

	public double getNumber() {
		return number;
	}

	public StackRule getRule() {
		return rule;
	}

	public StackRule getRuleHeader() {
		return ruleHeader;
	}

	public List<ASTParameter> getTypeInfo() {
		return typeInfo;
	}

	public void evalArgs(RTI rti, ASTExpression arguments, List<ASTParameter> parameters, boolean sequential) {
		// TODO Auto-generated method stub

	}

	public double[] getArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public Modification modification() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addNumber(double value) {
		this.number += value;
	}

	public void setNumber(double value) {
		// TODO Auto-generated method stub
		
	}

	public void setModification(Modification modification) {
		// TODO Auto-generated method stub
		
	}

	public void setRule(StackRule rule) {
		// TODO Auto-generated method stub
		
	}
}
