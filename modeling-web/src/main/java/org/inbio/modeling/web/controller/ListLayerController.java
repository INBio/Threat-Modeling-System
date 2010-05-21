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

public class ListLayerController extends AbstractFormController {

	private LayerManager layerManager;

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request
												, HttpServletResponse response
												, Object command
												, BindException errors)
												throws Exception {
		return new ModelAndView("index");
	}

	/** default behavior for direct access (url) */
	@Override
	protected ModelAndView showForm(HttpServletRequest request
									, HttpServletResponse response
									, BindException errors)
									throws Exception {

		List<LayerDTO> layers = this.layerManager.getRegisteredLayers();


		ModelAndView model = null;

		// Send the layer list to the JSP
		model = new ModelAndView();
		model.addObject("layers", layers);
		model.addObject("layerForm", new LayerDTO());
		model.setViewName("admin/listLayers");

        return model;
	}


	/* getters & setters */
	public LayerManager getLayerManager() {
		return layerManager;
	}

	public void setLayerManager(LayerManager layerManager) {
		this.layerManager = layerManager;
	}
}
