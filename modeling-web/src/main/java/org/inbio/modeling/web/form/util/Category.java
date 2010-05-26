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
package org.inbio.modeling.web.form.util;

/**
 * Category information
 * @author asanabria
 */
public class Category {

	private boolean interval;
	private String value;
	private String description;

	public Category() {
	}

	public Category(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isInterval() {
		return interval;
	}

	public void setInterval(boolean interval) {
		this.interval = interval;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}