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
public class IntervalsController extends AbstractFormController {

	private GrassManager grassManagerImpl;

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {


		HashMap<String, String> column = null;
		GenericForm layersInformation = null;
		SessionInfo sessionInfo = null;
		Long currentSessionId = null;
		HttpSession session = null;
		ModelAndView model = null;

		List<CategoryDTO> intervals = null;
		String[] columnElements = null;


		// get the information of the form.
		layersInformation = (GenericForm)command;

		// retrieve the session Information.
		session = request.getSession();
		sessionInfo = (SessionInfo)session.getAttribute("CurrentSessionInfo");
		currentSessionId = sessionInfo.getCurrentSessionId();


		// extract and format the information of the dataColumn to use.
		for(LayerDTO layer : layersInformation.getLayers()){

			// split the information that comes from the Form
			columnElements = layer.getColumns().get("selected").split(":");

			// convert the array to a HashMap
			column = new HashMap<String,String>();
			column.put(columnElements[0], columnElements[1]);
			layer.setColumns(column);

			// vectorial reclasification
			this.vectorialReclassification(layer.getName(), columnElements[0], currentSessionId);

			//convert the layer to a raster format
			this.layer2Raster(layer.getName(), currentSessionId, columnElements[0]);

			//asign the categories
			this.asignCategories2Layer(layer, currentSessionId);


			System.out.println("");
		}

		// assing layerList to the session
		sessionInfo.setSelectedLayerList(layersInformation.getLayers());
		session.setAttribute("CurrentSessionInfo", sessionInfo);

		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("intervals");
		model.addObject("currentInfo", layersInformation);

        return model;
	}


	private void vectorialReclassification(String layerName, String column, Long currentSessionId){
		try {
			this.grassManagerImpl.executeVectorReclasification(layerName, column, currentSessionId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	private void layer2Raster(String layerName, Long currentSessionId, String column){
		try {
			// convert the vectorial layer to another in raster format
			this.grassManagerImpl.convertLayer2Raster(layerName, currentSessionId, column);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}


	private void asignCategories2Layer(LayerDTO layer, Long currentSessionId){

		List<CategoryDTO> categories = null;

		try {
			// retrieve an asing the layer categories
			categories = this.grassManagerImpl.getLayerCategories(layer.getName(), "RAST", currentSessionId);
			layer.setCategories(categories);
		} catch (Exception ex) {
			ex.printStackTrace();
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
