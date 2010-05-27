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

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.inbio.modeling.core.manager.LayerManager;
import org.inbio.modeling.web.form.ListLayerForm;
import org.inbio.modeling.web.session.CurrentInstanceData;
import org.inbio.modeling.web.form.util.Layer;
import org.inbio.modeling.web.form.converter.FormDTOConverter;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

public class LayerController extends AbstractFormController {

    private LayerManager layerManager;

	@Override
	protected ModelAndView showForm(HttpServletRequest request
									, HttpServletResponse response
									, BindException errors)
									throws Exception {

		CurrentInstanceData currentInstanceData = null;
		ListLayerForm layerListForm = null;
		HttpSession session = null;
		ModelAndView model = null;

		// Create the object that will retain all the user information trought the process.
		currentInstanceData = new CurrentInstanceData();
		currentInstanceData.setUserSessionId(Calendar.getInstance().getTimeInMillis());

		// Asing the SessionInfo Object to the session
		session = request.getSession(true);
		session.setAttribute("CurrentSessionInfo", currentInstanceData);

		//creates the form to the page and upload it.
		layerListForm = new ListLayerForm();
		layerListForm.setLayerList(FormDTOConverter.convert(layerManager.getLayerList(), Layer.class));

		// Send the layer list to the JSP
		model = new ModelAndView();
		if(errors != null && errors.hasErrors())
			model.addAllObjects(errors.getModel());

		model.setViewName("layers");
		model.addObject("layersForm", layerListForm);

        return model;
	}

	/** default behavior for direct access (url) */
	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request
												, HttpServletResponse response
												, Object command
												, BindException errors)
												throws Exception {


		if(errors.hasErrors())
			return showForm(request, response, errors);
		else
			return new ModelAndView("forward:columns.html");

	}

	/* getters & setters */
	public LayerManager getLayerManager() {
		return layerManager;
	}

	public void setLayerManager(LayerManager layerManager) {
		this.layerManager = layerManager;
	}
}