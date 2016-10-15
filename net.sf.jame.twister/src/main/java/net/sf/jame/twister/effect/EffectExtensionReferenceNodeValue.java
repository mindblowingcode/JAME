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
package net.sf.jame.twister.effect;

import net.sf.jame.core.common.ExtensionReferenceElementNodeValue;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.twister.effect.extension.EffectExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class EffectExtensionReferenceNodeValue extends ExtensionReferenceElementNodeValue<ConfigurableExtensionReference<EffectExtensionConfig>> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public EffectExtensionReferenceNodeValue(final ConfigurableExtensionReference<EffectExtensionConfig> value) {
		super(value);
	}
}
