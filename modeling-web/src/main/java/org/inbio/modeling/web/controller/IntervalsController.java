/* Modeling - Application to model threats.
 *
 * Copyright (C) 2010  INBio (Instituto Nacional de Biodiversidad)
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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.inbio.modeling.core.dto.IntervalDTO;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.core.manager.GrassManager;
import org.inbio.modeling.web.forms.LayersForm;
import org.inbio.modeling.web.session.SessionInfo;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 *
 * @author asanabria
 */
public class IntervalsController extends AbstractFormController {

	private GrassManager grassManagerImpl;

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {


		HashMap<String, String> selectedColumn = null;
		LayersForm selectedLayers = null;
		List<LayerDTO> layerList =  null;
		SessionInfo sessionInfo = null;
		Long currentSessionId = null;
		HttpSession session = null;
		ModelAndView model = null;
		LayerDTO layerDTO = null;

		List<IntervalDTO> intervals = null;
		String[] columnElements = null;

		layerList = new ArrayList<LayerDTO>();

		// get the information of the form.
		selectedLayers = (LayersForm)command;
		List<String> dataColumns = selectedLayers.getDataColumnList();

		// retrieve the session Information.
		session = request.getSession();
		sessionInfo = (SessionInfo)session.getAttribute("CurrentSessionInfo");
		currentSessionId = sessionInfo.getCurrentSessionId();


		// extract and format the information of the dataColumn to use.
		for(String dataColumn : dataColumns){

			layerDTO = new LayerDTO();

			// split the information that comes from the Form
			columnElements = dataColumn.split(":");

			// set the name of the layer for future recognition
			layerDTO.setName(columnElements[0]);

			// convert the array to a HashMap
			selectedColumn = new HashMap<String,String>();
			selectedColumn.put(columnElements[1], columnElements[2]);

			//set the selectedColumn
			layerDTO.setDataColumnList(selectedColumn);

			// if the selected column is of type CHARACTER the layer has to be recategorize
			if(columnElements[2].equals("CHARACTER")){
				this.grassManagerImpl.simpleReclasification(layerDTO.getName(), columnElements[1], currentSessionId);
			}

			//convert the layer to a raster format
			this.layer2Raster(layerDTO.getName(), currentSessionId);

			layerList.add(layerDTO);
		}

		// combine the new information with the existing one
		layerList = this.mergeData(sessionInfo.getSelectedLayerList() , layerList);

		// retrieve the categories by layer.
		layerList = this.setIntervals2Layers(currentSessionId, layerList);


		// assing layerList to the session
		sessionInfo.setSelectedLayerList(layerList);
		session.setAttribute("CurrentSessionInfo", sessionInfo);

		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("intervals");
		model.addObject("layers", layerList);

        return model;
	}

	private List<LayerDTO> mergeData(List<LayerDTO> oldList, List<LayerDTO> newList){


		for(LayerDTO oldLayer : oldList ){
			for(LayerDTO newLayer : newList){
				if(oldLayer.getName().equals(newLayer.getName())){
					oldLayer.setDataColumnList(newLayer.getDataColumnList());
				}
			}
		}

		return oldList;
	}

	private List<LayerDTO> setIntervals2Layers(Long currentSessionId, List<LayerDTO> layerList){

		List<IntervalDTO> intervals = null;

		for(LayerDTO layer : layerList){

			try {
				// retrieve an asing the layer categories
				intervals = this.grassManagerImpl.getLayerCategories(layer.getName(), "RAST", currentSessionId);
				layer.setIntervals(intervals);
			} catch (Exception ex) {
				logger.fatal(ex);
			}
		}


		return layerList;
	}


	private void layer2Raster(String layerName, Long currentSessionId){
		try {
			// convert the vectorial layer to another in raster format
			this.grassManagerImpl.convertLayer2Raster(layerName, currentSessionId, true);
		} catch (Exception ex) {
			logger.fatal(ex);
		}

	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		return new ModelAndView("index");
	}

	/* Getters and Setters */
	public GrassManager getGrassManagerImpl() {
		return grassManagerImpl;
	}

	public void setGrassManagerImpl(GrassManager grassManagerImpl) {
		this.grassManagerImpl = grassManagerImpl;
	}
}
