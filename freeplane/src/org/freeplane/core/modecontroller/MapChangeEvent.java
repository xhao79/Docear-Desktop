/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2008 Dimitry Polivaev
 *
 *  This file author is Dimitry Polivaev
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.core.modecontroller;

import java.awt.AWTEvent;

import org.freeplane.core.model.MapModel;

/**
 * @author Dimitry Polivaev 27.11.2008
 */
public class MapChangeEvent extends AWTEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final private Object newValue;
	final private Object oldValue;
	final private Object property;

	public MapChangeEvent(final MapModel map, final Object property, final Object oldValue, final Object newValue) {
		super(map, 0);
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.property = property;
	}

	public MapChangeEvent(final Object property, final Object oldValue, final Object newValue) {
		this(null, property, oldValue, newValue);
	}

	public MapModel getMap() {
		return (MapModel) getSource();
	}

	public Object getNewValue() {
		return newValue;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public Object getProperty() {
		return property;
	}
}
