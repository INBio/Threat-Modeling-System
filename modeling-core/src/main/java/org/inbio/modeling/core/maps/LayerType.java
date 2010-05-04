/* Modeling - Application to model threats.
 *
 * Copyright (C) 2010  INBio ( Instituto Nacional de Biodiversidad )
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.inbio.modeling.core.maps;

/**
 *
 * @author asanabria
 */
public enum LayerType {

	AREA(1,"areas"),		// When a layer contains only polygons geometry information
	LINE(2,"lines"),		// When a layer contains only lines geometry information
	POINT(3,"points");	// When a layer contains only points geometry iformation

	private int	   id;
	private String name;

	LayerType(int id, String name){
		this.id = id;
		this.name = name;
	}

	public final boolean match(String value){

		boolean result = false;

		if(this.name.equals(value))
			result = true;

		return result;
	}

	@Override
	public final String toString(){
		return this.name;
	}
}
