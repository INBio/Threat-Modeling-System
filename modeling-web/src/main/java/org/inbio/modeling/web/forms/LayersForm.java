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
package org.inbio.modeling.web.forms;

import java.util.List;

/**
 *
 * @author asanabria
 */
public class LayersForm {

	private List<String> selectedLayers;
	private List<String> selectedValues;
	private List<String> dataColumnList;

	public List<String> getSelectedLayers() {
		return selectedLayers;
	}

	public void setSelectedLayers(List<String> selectedLayers) {
		this.selectedLayers = selectedLayers;
	}

	public List<String> getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(List<String> selectedValues) {
		this.selectedValues = selectedValues;
	}

	public List<String> getDataColumnList() {
		return dataColumnList;
	}

	public void setDataColumnList(List<String> dataColumnList) {
		this.dataColumnList = dataColumnList;
	}
}
