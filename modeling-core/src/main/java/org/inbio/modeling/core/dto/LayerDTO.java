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

package org.inbio.modeling.core.dto;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author asanabria
 */
public class LayerDTO {

	private long   weight;
	private String name;
	private String description;
	private HashMap<String,String>	dataColumnList;
	private List<IntervalDTO>		intervals;

	public LayerDTO() { }

	public LayerDTO(String name, long weight ) {
		this.weight = weight;
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<IntervalDTO> getIntervals() {
		return intervals;
	}

	public void setIntervals(List<IntervalDTO> intervals) {
		this.intervals = intervals;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public HashMap<String, String> getDataColumnList() {
		return dataColumnList;
	}

	public void setDataColumnList(HashMap<String, String> dataColumnList) {
		this.dataColumnList = dataColumnList;
	}
}
