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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.inbio.modeling.core.manager.GrassManager;
import org.inbio.modeling.core.manager.LayerManager;
import org.inbio.modeling.web.form.ChooseLimitLayerForm;
import org.inbio.modeling.web.session.CurrentInstanceData;
import org.inbio.modeling.web.form.util.Layer;
import org.inbio.modeling.web.form.converter.FormDTOConverter;
import org.inbio.modeling.web.session.SessionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

public class SpeciesDistributionController extends AbstractFormController {

    private LayerManager layerManager;
	private GrassManager grassManagerImpl;
	private ShowMapController showMapController;

    @Override
    protected ModelAndView showForm(HttpServletRequest request
            , HttpServletResponse response
            , BindException errors) {

        ModelAndView model = null;
        HttpSession session = null;
        ChooseLimitLayerForm lform = null;
		CurrentInstanceData currentInstanceData = null;

		// retrieve the session Information.
        session = request.getSession();


        //creates the form to the page and upload it.
        lform = new ChooseLimitLayerForm();
        lform.setLayers(FormDTOConverter.convert(layerManager.getSpeciesDistributionLayerList(), Layer.class));

        // Send the layer list to the JSP
        model = new ModelAndView();
        if(errors != null && errors.hasErrors())
            model.addAllObjects(errors.getModel());

        model.setViewName("speciesMap");
        model.addObject("chooseSpeciesMapForm", lform);

        return model;
    }


    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request
            , HttpServletResponse response
            , Object command
            , BindException errors) {
        List<Layer> layers = null;
        HttpSession session = null;
        Double resolution = null;

		CurrentInstanceData currentInstanceData = null;
		Long currentSessionId = null;
		ChooseLimitLayerForm lForm = null;

		if(errors.hasErrors())
			return showForm(request, response, errors);


		// get the information of the form.
		lForm = (ChooseLimitLayerForm)command;

		// retrieve the session Information.
		session = request.getSession();

		currentInstanceData = SessionUtils.isSessionAlive(session);
        currentSessionId = currentInstanceData.getUserSessionId();


		if(currentInstanceData != null && errors != null && !errors.hasErrors()){


            try {

                currentInstanceData.setImageName("Final");

            } catch (Exception ex) {
                ex = new Exception("errors.noRegion");
                Logger.getLogger(LimitController.class.getName()).log(Level.SEVERE, null, ex);
                errors.reject(ex.getMessage());
                return showForm(request, response, errors);
            }

		}else{

			Exception ex = new Exception("errors.noSession");
			Logger.getLogger(LimitController.class.getName()).log(Level.SEVERE, null, ex);
			errors.reject(ex.getMessage());
			return showForm(request, response, errors);
		}

		session.setAttribute("CurrentSessionInfo", currentInstanceData);

        return showMapController.showForm(request, response, errors);
    }

    /* getters & setters */
    public LayerManager getLayerManager() {
        return layerManager;
    }

    public void setLayerManager(LayerManager layerManager) {
        this.layerManager = layerManager;
    }

    public GrassManager getGrassManagerImpl() {
        return grassManagerImpl;
    }

    public void setGrassManagerImpl(GrassManager grassManagerImpl) {
        this.grassManagerImpl = grassManagerImpl;
    }

    public ShowMapController getShowMapController() {
        return showMapController;
    }

    public void setShowMapController(ShowMapController showMapController) {
        this.showMapController = showMapController;
    }
}