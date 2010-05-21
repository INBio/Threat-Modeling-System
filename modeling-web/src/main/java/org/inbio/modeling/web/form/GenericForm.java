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
package org.inbio.modeling.web.form;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

/**
 * Form used to move and retrieve data from the web interface
 * @author asanabria
 */
public class GenericForm {

	private String			resolution;
	// this is a LazyList to allow dinamic binding with the web interface.
	private List<GrassLayerForm>  layers = LazyList.decorate(
		new ArrayList(),
		FactoryUtils.instantiateFactory(GrassLayerForm.class));


	public GenericForm() {
	}

	public List<GrassLayerForm> getLayers() {
		return layers;
	}

	public void setLayers(List<GrassLayerForm> layers) {
		this.layers = layers;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
}