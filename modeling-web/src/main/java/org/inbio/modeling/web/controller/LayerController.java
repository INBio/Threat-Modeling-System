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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.inbio.modeling.core.manager.ConfigManager;
import org.inbio.modeling.core.manager.LayerManager;
import org.inbio.modeling.web.form.ListLayerForm;
import org.inbio.modeling.web.session.CurrentInstanceData;
import org.inbio.modeling.web.form.util.Layer;
import org.inbio.modeling.web.form.converter.FormDTOConverter;
import org.inbio.modeling.web.session.SessionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

public class LayerController extends AbstractFormController {

    private LayerManager layerManager;
    private ConfigManager configManager;
    private LimitController limitController;

    @Override
    protected ModelAndView showForm(HttpServletRequest request
            , HttpServletResponse response
            , BindException errors) {

        CurrentInstanceData currentInstanceData = null;
        ListLayerForm layerListForm = null;
        HttpSession session = null;
        ModelAndView model = null;

        // Create the object that will retain all the user information trought the process.
        currentInstanceData = new CurrentInstanceData();
        currentInstanceData.setUserSessionId(Calendar.getInstance().getTimeInMillis());

        // Asing the SessionInfo Object to the session
        session = request.getSession(true);

        //validate the new Session
        if( !session.isNew() ){
            session.invalidate();
            session = request.getSession(true);
        }

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


    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request
            , HttpServletResponse response
            , Object command
            , BindException errors) {
        List<Layer> layers = null;
        HttpSession session = null;
        Double resolution = null;

        Double resInMeters = null;
        Double resInDegrees = null;
        Double meterDegrees = null;

        if(errors.hasErrors())
            return showForm(request, response, errors);

        CurrentInstanceData currentInstanceData = null;
        ListLayerForm layerListForm = null;

        layerListForm = (ListLayerForm)command;

        // retrieve the session Information.
        session = request.getSession();
        currentInstanceData = SessionUtils.isSessionAlive(session);

        if(currentInstanceData != null){

            // retrieve the resolution.
            meterDegrees = Double.parseDouble(configManager.retrieveResolution());
            resInMeters = layerListForm.getResolution();
            resInDegrees = resInMeters * meterDegrees;

            currentInstanceData.setResolution(resInDegrees);

            layers = new ArrayList<Layer>();
            layers.addAll(layerListForm.getLayerList());

            // Gets the layers and its weights
            for(Layer layer : layerListForm.getLayerList()){
                if(layer.isSelected() == false){
                    layers.remove(layer);
                }
            }

            // Set the new information to the session info // selected layers and its weights
            currentInstanceData.setLayerList(layers);
            session.setAttribute("CurrentSessionInfo", currentInstanceData);

        }else{
            Exception ex = new Exception("errors.noSession");
            Logger.getLogger(ColumnController.class.getName()).log(Level.SEVERE, null, ex);
            errors.reject(ex.getMessage());
            return showForm(request, response, errors);
        }


        return limitController.showForm(request, response, errors);
    }

    /* getters & setters */
    public LayerManager getLayerManager() {
        return layerManager;
    }

    public void setLayerManager(LayerManager layerManager) {
        this.layerManager = layerManager;
    }

    public LimitController getLimitController() {
        return limitController;
    }

    public void setLimitController(LimitController limitController) {
        this.limitController = limitController;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }
}