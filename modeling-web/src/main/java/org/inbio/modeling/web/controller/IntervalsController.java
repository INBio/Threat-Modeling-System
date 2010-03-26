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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.web.forms.LayersForm;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 *
 * @author asanabria
 */
public class IntervalsController extends AbstractFormController {


	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

		LayersForm layers = (LayersForm)command;

		List<LayerDTO> list = new ArrayList<LayerDTO>();
		LayerDTO layerDTO = null;
		int index = 0;
		String weight = null;

		for(String layer : layers.getSelectedLayers()){
			layerDTO = new LayerDTO();
			layerDTO.setName(layer);
			list.add(layerDTO);
		}

		for(LayerDTO layer : list){
			weight = layers.getSelectedValues().get(index);
			layer.setWeight(Integer.parseInt(weight));
		}
		
		return new ModelAndView("intervals", "layers", list);
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		return new ModelAndView("index");
	}
}
