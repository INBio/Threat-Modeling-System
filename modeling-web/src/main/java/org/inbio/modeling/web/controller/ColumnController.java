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
package org.inbio.modeling.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.core.manager.GrassManager;
import org.inbio.modeling.web.forms.LayersForm;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 *
 * @author asanabria
 */
public class ColumnController extends AbstractFormController {

	private GrassManager grassManagerImpl;

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {

		LayersForm selectedLayers = (LayersForm)command;

		List<LayerDTO> list = new ArrayList<LayerDTO>();
		HashMap<String,String> columns = null;
		LayerDTO layerDTO = null;
		int index = 0;
		String weight = null;
		Long suffix = Calendar.getInstance().getTimeInMillis();
		suffix = new Long(1271111604483L);
		boolean second = false;

		try{
			this.grassManagerImpl.configureEnvironment("Default", suffix);

			// Gets the layers and its weights
			for(String layer : selectedLayers.getSelectedLayers()){
				//creates a new layer
				layerDTO = new LayerDTO();
				//set the name of the layer
				layerDTO.setName(layer);
				//set the weight of the layer
				weight = selectedLayers.getSelectedValues().get(index++);
				layerDTO.setWeight(Long.parseLong(weight));

				// layer importation
				if(second){
					this.grassManagerImpl.configureEnvironment("LOC_"+suffix, suffix);
				}else{
					second = true;
				}
			//	this.grassManagerImpl.importLayer(layerDTO.getName(), suffix);
				this.grassManagerImpl.configureEnvironment("LOC_"+suffix, suffix);
				// Retrive data columns
				columns = grassManagerImpl.retrieveAvailableColumns(layer, suffix);

				layerDTO.setDataColumnList(columns);

				list.add(layerDTO);
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		// Gets the category information.
		//grassManagerImpl.getLayerCategories(layerDTO.getName(), "RAST", new Long(layerDTO.getWeight()));





		return new ModelAndView("columns", "layers", list);
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		return new ModelAndView("index");
	}

	/* Getters y Setters */
	public GrassManager getGrassManagerImpl() {
		return grassManagerImpl;
	}

	public void setGrassManagerImpl(GrassManager grassManagerImpl) {
		this.grassManagerImpl = grassManagerImpl;
	}
}