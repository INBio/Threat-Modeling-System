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

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.core.manager.LayerManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

public class UpdateLayerInfoController extends AbstractFormController {

    private LayerManager layerManager;


	/** default behavior for direct access (url) */
	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request
												, HttpServletResponse response
												, Object command
												, BindException errors)
												throws Exception {

		List<LayerDTO> layers = null;
		LayerDTO layerDTO = (LayerDTO)command;
		String uri = request.getRequestURI();

		if(uri.matches(".*deleteLayer.*")){
			this.deleteLayer(layerDTO);
		}else if(uri.matches(".*updateLayer.*")){

			if ( layerDTO.getId() == null)
				this.newLayer(layerDTO);
			else
				this.updateLayer(layerDTO);
		}



		ModelAndView model = null;

		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("redirect:listLayers.html");

        return model;
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request
									, HttpServletResponse response
									, BindException errors)
									throws Exception {

		return new ModelAndView("index");
	}

	private void deleteLayer(LayerDTO layer){
		this.layerManager.deleteLayer(layer.getId());
	}

	//TODO: FIXME:
	private void updateLayer(LayerDTO layer){

		this.layerManager.updateLayer(layer);
	}

	private void newLayer(LayerDTO layer){
		this.layerManager.createLayer(layer);
	}

	/* getters & setters */
	public LayerManager getLayerManager() {
		return layerManager;
	}

	public void setLayerManager(LayerManager layerManager) {
		this.layerManager = layerManager;
	}
}
