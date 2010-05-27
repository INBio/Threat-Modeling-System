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
import org.inbio.modeling.core.manager.GrassManager;
import org.inbio.modeling.core.layer.LayerType;
import org.inbio.modeling.web.form.ChooseColumnForm;
import org.inbio.modeling.web.form.ListLayerForm;
import org.inbio.modeling.web.form.util.Layer;
import org.inbio.modeling.web.form.converter.FormDTOConverter;
import org.inbio.modeling.web.session.CurrentInstanceData;
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
	protected ModelAndView handleInvalidSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return super.handleInvalidSubmit(request, response);
	}



	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request
												, HttpServletResponse response
												, Object command
												, BindException errors) {

		List<Layer> layers = null;
		ChooseColumnForm cForm = null;
		Long currentSessionId = null;
		HttpSession session = null;
		ModelAndView model = null;
		Double resolution = null;


		CurrentInstanceData currentInstanceData = null;
		ListLayerForm layerListForm = null;

		layerListForm = (ListLayerForm)command;
		
		// retrieve the session Information.
		session = request.getSession();
		currentInstanceData =
			(CurrentInstanceData)session.getAttribute("CurrentSessionInfo");

		currentSessionId = currentInstanceData.getUserSessionId();

		// retrieve the resolution.
		resolution = layerListForm.getResolution();
		currentInstanceData.setResolution(resolution);

		layers = new ArrayList<Layer>();
		layers.addAll(layerListForm.getLayerList());

		// Gets the layers and its weights
		for(Layer layer : layerListForm.getLayerList()){
			if(layer.isSelected() == false){
				layers.remove(layer);
			}
		}


		//Import the layers
		this.importLayers(resolution, layers, currentSessionId);

		//TODO
		// Asign the type to the layer.
		layers = this.asingType2Layer(layers, currentSessionId);

		//TODO
		// retrieve dataColumns
		layers = this.retrieveColumns(layers , currentSessionId);

		// Set the new information to the session info // selected layers and its weights
		currentInstanceData.setLayerList(layers);
		session.setAttribute("CurrentSessionInfo", currentInstanceData);

		// asing the columns to the jsp.
		cForm = new ChooseColumnForm();
		cForm.setLayers(layers);


		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("columns");
		model.addObject("columnsForm", cForm);

        return model;
	}

	/**
	 * Asign the type (line, area, points) to a layer.
	 * @param layers
	 * @param currentSessionId
	 * @return list of layers with the field layer.type populated.
	 */
	private List<Layer> asingType2Layer(List<Layer> layers
											, Long currentSessionId){

		LayerType layerType = null;

		List<Layer> list = new ArrayList<Layer>();

		for(Layer layer : layers){
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
	private List<Layer> retrieveColumns(List<Layer> layerList
											, Long currentSessionId){

		Map<String,String> columns = null;

		for (Layer layerForm: layerList){
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
								, List<Layer> layerList
								, Long currentSessionId){

		int counter = 0;

		try {

			// change the resolution
			this.grassManagerImpl.setResolution(resolution, currentSessionId);

			// configure grass to the default location.
			this.grassManagerImpl.configureEnvironment("Default", currentSessionId);

			for (Layer layer: layerList){
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