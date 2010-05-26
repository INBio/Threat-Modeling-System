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

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.inbio.modeling.core.dto.CategoryDTO;
import org.inbio.modeling.core.manager.GrassManager;
import org.inbio.modeling.web.form.ChooseColumnForm;
import org.inbio.modeling.web.form.EditIntervalForm;
import org.inbio.modeling.web.form.util.Category;
import org.inbio.modeling.web.form.util.Layer;
import org.inbio.modeling.web.form.converter.FormDTOConverter;
import org.inbio.modeling.web.session.CurrentInstanceData;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 * @author asanabria
 */
public class IntervalsController extends AbstractFormController {

	/** manager to comunicate with the grass gis software */
	private GrassManager grassManagerImpl;

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request
												, HttpServletResponse response
												, Object command
												, BindException errors)
												throws Exception {

		CurrentInstanceData currentInstanceData = null;
		HashMap<String, String> column = null;
		String[] columnElements = null;
		ChooseColumnForm cForm = null;
		Long currentSessionId = null;
		HttpSession session = null;
		ModelAndView model = null;


		// get the information of the form.
		cForm = (ChooseColumnForm)command;

		// retrieve the session Information.
		session = request.getSession();
		currentInstanceData =
			(CurrentInstanceData)session.getAttribute("CurrentSessionInfo");

		currentSessionId = currentInstanceData.getUserSessionId();


		// extract and format the information of the dataColumn to use.
		for(Layer layer : cForm.getLayers()){

			// split the information that comes from the Form
			columnElements = layer.getColumns().get("selected").split(":");

			// convert the array to a HashMap
			column = new HashMap<String,String>();
			column.put("selected", columnElements[0]);
			layer.setColumns(column);

			if(columnElements.length > 1 && columnElements[1].equals("CHARACTER")){
				// vectorial reclasification
				this.vectorialReclassification(layer, currentSessionId);
			}else{
				this.grassManagerImpl.renameFile(FormDTOConverter.convert(layer)
												, currentSessionId);
			}

			//convert the layer to a raster format
			this.layer2Raster(layer, currentSessionId);

			if( columnElements.length > 1 ){

				//asign the categories
				this.asignCategories2Layer(layer
											, columnElements[1]
											, currentSessionId);
			}else{
				//asign the categories
				this.asignCategories2Layer(layer
											, "cat"
											, currentSessionId);
			}

			System.out.println("");
		}


		// Set the new information to the session info // selected layers and its weights
		currentInstanceData.setLayerList(cForm.getLayers());
		session.setAttribute("CurrentSessionInfo", currentInstanceData);

		EditIntervalForm iForm = new EditIntervalForm();
		iForm.setLayers(cForm.getLayers());

		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("intervals");
		model.addObject("intervalsInfo", iForm);

        return model;
	}

	/**
	 * Execute normalization of the values in a vectorial format map.
	 * @param layerName
	 * @param column
	 * @param currentSessionId
	 */
	private void vectorialReclassification(Layer layer
											, Long currentSessionId){
		try {
			this.grassManagerImpl.
				executeVectorReclasification(FormDTOConverter.convert(layer)
											, currentSessionId);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * convert a vectorial layer into a raster one
	 * @param layerName
	 * @param currentSessionId
	 * @param column
	 */
	private void layer2Raster(Layer layer, Long currentSessionId){

		try {
			// convert the vectorial layer to another in raster format
			this.grassManagerImpl.
				convertLayer2Raster(FormDTOConverter.convert(layer)
									, currentSessionId);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}


	/**
	 *
	 * populate category information of the layer
	 * @param layer
	 * @param currentSessionId
	 */
	private void asignCategories2Layer(Layer layer
										, String dataType
										, Long currentSessionId){

		List<CategoryDTO> categories = null;

		try {
			// retrieve an asing the layer categories
			categories = 
				this.grassManagerImpl.
					getLayerCategories(FormDTOConverter.convert(layer)
										,dataType
										,currentSessionId);

			layer.setCategories(
				FormDTOConverter.convert(categories, Category.class));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	/** default behavior for direct access */
	@Override
	protected ModelAndView showForm(HttpServletRequest request
									, HttpServletResponse response
									, BindException errors)
									throws Exception {

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