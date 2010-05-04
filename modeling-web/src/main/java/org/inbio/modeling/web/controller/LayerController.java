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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.core.manager.LayerManager;
import org.inbio.modeling.web.forms.GenericForm;
import org.inbio.modeling.web.session.SessionInfo;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

public class LayerController extends AbstractFormController {

    protected final Log logger = LogFactory.getLog(getClass());

    private LayerManager layerManager;

	@Override
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {

		SessionInfo sessionInfo = null;
		HttpSession session = null;
		ModelAndView model = null;

		// Generates the Session SessionInfo object
		sessionInfo = new SessionInfo();

		// Generates the user id Number.
		sessionInfo.setCurrentSessionId(Calendar.getInstance().getTimeInMillis());
		//sessionInfo.setCurrentSessionId(1271784714875L);

		// Gets the list of available layers
		List <LayerDTO> layers = layerManager.getLayerList();
		GenericForm systemInfo = new GenericForm();
		systemInfo.setLayers(layers);

		// Asing the SessionInfo Object to the session
		session = request.getSession(true);
		session.setAttribute("CurrentSessionInfo", sessionInfo);


		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("layers");
		model.addObject("systemInfo", systemInfo);

        return model;
	}

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		return new ModelAndView("index");
	}

	public LayerManager getLayerManager() {
		return layerManager;
	}

	public void setLayerManager(LayerManager layerManager) {
		this.layerManager = layerManager;
	}
}
