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
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.core.manager.GrassManager;
import org.inbio.modeling.core.layer.LayerType;
import org.inbio.modeling.web.form.GenericForm;
import org.inbio.modeling.web.form.LayerForm;
import org.inbio.modeling.web.form.converter.FormDTOConverter;
import org.inbio.modeling.web.session.SessionInfo;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 *
 * @author asanabria
 */
public class ColumnController extends AbstractFormController {

	/** manager to comunicate with grass gis software */
	private GrassManager grassManagerImpl;

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request
												, HttpServletResponse response
												, Object command
												, BindException errors) {

		List<LayerForm> layersForm = null;
		GenericForm currentStatus = null;
		SessionInfo sessionInfo = null;
		Long currentSessionId = null;
		HttpSession session = null;
		ModelAndView model = null;
		Double resolution = null;

		currentStatus = (GenericForm)command;
		
		// retrieve the session Information.
		session = request.getSession();
		sessionInfo = (SessionInfo)session.getAttribute("CurrentSessionInfo");
		currentSessionId = sessionInfo.getCurrentSessionId();
		resolution = Double.parseDouble(currentStatus.getResolution());
		
		try{


			layersForm = new ArrayList<LayerForm>();
			layersForm.addAll(currentStatus.getLayers());

			// Gets the layers and its weights
			for(LayerForm layer : currentStatus.getLayers()){
				if(layer.isSelected() == false){
					layersForm.remove(layer);
				}
			}


			//Import the layers
			this.importLayers(resolution, layersForm, currentSessionId);

			// Asign the type to the layer.
			layersForm = this.asingType2Layer(layersForm, currentSessionId);

			// retrieve dataColumns
			layersForm = this.retrieveColumns(layersForm , currentSessionId);


		}catch(Exception e){
			e.printStackTrace();
		}

		currentStatus.setLayers(layersForm);

		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("columns");
		model.addObject("currentStatus", currentStatus);

        return model;
	}

	/**
	 * Asign the type (line, area, points) to a layer.
	 * @param layers
	 * @param currentSessionId
	 * @return list of layers with the field layer.type populated.
	 */
	private List<LayerForm> asingType2Layer(List<LayerForm> layers
											, Long currentSessionId){

		LayerType layerType = null;

		List<LayerForm> list = new ArrayList<LayerForm>();

		for(LayerForm layer : layers){
			try {
				layerType = this.grassManagerImpl.
									retrieveLayerType(FormDTOConverter.convert(layer), currentSessionId);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			layer.setType(layerType);
			list.add(layer);
		}

		return list;
	}


	/**
	 * Return the name and the type of the columns of the dbf asociated to the
	 * layer
	 * @param layerList
	 * @param currentSessionId
	 * @return a list of layers with the columns information populated.
	 */
	private List<LayerForm> retrieveColumns(List<LayerForm> layerList
											, Long currentSessionId){

		Map<String,String> columns = null;

		for (LayerForm layerForm: layerList){
			if(layerForm.getType() == LayerType.AREA){
				try {
					// Retrive data columns
					columns = grassManagerImpl.
						retrieveAvailableColumns(FormDTOConverter.convert(layerForm), currentSessionId);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				columns.remove("cat");
				layerForm.setColumns(columns);
			}
		}

		return layerList;
	}

	//TODO: make this better
	/**
	 * Import a layer from a specific file system path to the grass database.
	 * @param resolution
	 * @param layerList
	 * @param currentSessionId
	 */
	private void importLayers(Double resolution
								, List<LayerForm> layerList
								, Long currentSessionId){

		int counter = 0;

		try {

			// change the resolution
			this.grassManagerImpl.setResolution(resolution, currentSessionId);

			// configure grass to the default location.
			this.grassManagerImpl.configureEnvironment("Default", currentSessionId);

			for (LayerForm layer: layerList){
				if(counter++ == 1){
					this.grassManagerImpl.
						configureEnvironment("LOC_"+currentSessionId, currentSessionId);

					this.grassManagerImpl.
						setResolution(resolution, currentSessionId);
				}

				this.grassManagerImpl.importLayer(FormDTOConverter.convert(layer), currentSessionId);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * If hit the /columns.html address directly you will be redirected to index
	 * @param request
	 * @param response
	 * @param errors
	 * @return
	 * @throws Exception
	 */
	@Override
	protected ModelAndView showForm(HttpServletRequest request
									, HttpServletResponse response
									, BindException errors)
									throws Exception {
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