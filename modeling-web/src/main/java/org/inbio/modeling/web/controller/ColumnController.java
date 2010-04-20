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
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.core.manager.GrassManager;
import org.inbio.modeling.web.forms.GenericForm;
import org.inbio.modeling.web.session.SessionInfo;
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

		GenericForm selectedLayers = null;
		List<LayerDTO> layerList =  null;
		SessionInfo sessionInfo = null;
		Long currentSessionId = null;
		HttpSession session = null;
		ModelAndView model = null;
		LayerDTO layerDTO = null;
		Double resolution = null;
		String weight = null;
		int index = 0;


		selectedLayers = (GenericForm)command;
		layerList = new ArrayList<LayerDTO>();

		// retrieve the session Information.
		session = request.getSession();
		sessionInfo = (SessionInfo)session.getAttribute("CurrentSessionInfo");
		currentSessionId = sessionInfo.getCurrentSessionId();
		resolution = Double.parseDouble(selectedLayers.getResolution());

		try{

			// Gets the layers and its weights
			for(String layer : selectedLayers.getSelectedLayers()){

				// completes the available information of the layers.
				layerDTO = new LayerDTO();
				// set the name (its the filename without extension)
				layerDTO.setName(layer);
				// set the weight of the layer.
				weight = selectedLayers.getSelectedValues().get(index++);
				layerDTO.setWeight(Long.parseLong(weight));

				// add the new LayerDTO to the list.
				layerList.add(layerDTO);
			}

			// Change the resolution

			//Import the layers
			this.importLayers(resolution, layerList, currentSessionId);

			// retrieve dataColumns
			layerList = this.retrieveColumns(layerList, currentSessionId);


		}catch(Exception e){
			e.printStackTrace();
		}

		// assing layerList to the session
		sessionInfo.setSelectedLayerList(layerList);
		session.setAttribute("CurrentSessionInfo", sessionInfo);

		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("columns");
		model.addObject("layers", layerList);

        return model;
	}

	private void setResolution(Double resolution, Long currentSessionId){
		try {
			this.grassManagerImpl.setResolution(resolution, currentSessionId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}


	private List<LayerDTO> retrieveColumns(List<LayerDTO> layerList, Long currentSessionId){

		HashMap<String,String> columns = null;

		for (LayerDTO layerDTO: layerList){
			try {
				// Retrive data columns
				columns = grassManagerImpl.retrieveAvailableColumns(layerDTO.getName(), currentSessionId);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			columns.remove("cat");
			layerDTO.setDataColumnList(columns);
		}

		return layerList;
	}


	//TODO: make this better
	private void importLayers(Double resolution, List<LayerDTO> layerList, Long currentSessionId){

		int counter = 0;

		try {

			// change the resolution
			this.grassManagerImpl.setResolution(resolution, currentSessionId);

			// configure grass to the default location.
			this.grassManagerImpl.configureEnvironment("Default", currentSessionId);

			for (LayerDTO layerDTO: layerList){
				if(counter++ == 1){
					this.grassManagerImpl.configureEnvironment("LOC_"+currentSessionId, currentSessionId);
					this.grassManagerImpl.setResolution(resolution, currentSessionId);
				}

				this.grassManagerImpl.importLayer(layerDTO.getName(), currentSessionId);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
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