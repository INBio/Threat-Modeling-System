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
import org.inbio.modeling.web.form.ChooseLimitLayerForm;
import org.inbio.modeling.web.form.converter.FormDTOConverter;
import org.inbio.modeling.web.form.util.Layer;
import org.inbio.modeling.web.session.CurrentInstanceData;
import org.inbio.modeling.web.session.SessionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;


/**
 *
 * @author asanabria
 */
public class LimitController extends AbstractFormController {

	/** manager to comunicate with grass gis software */
	private GrassManager grassManagerImpl;
    private ColumnController columnController;

	@Override
	protected ModelAndView handleInvalidSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return super.handleInvalidSubmit(request, response);
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
		, BindException errors) {

		CurrentInstanceData currentInstanceData = null;
		ChooseLimitLayerForm lForm = null;
		HttpSession session = null;
		ModelAndView model = null;

		// retrieve the session Information.
		session = request.getSession();

		currentInstanceData = SessionUtils.isSessionAlive(session);

		// asing the columns to the jsp.
		lForm = new ChooseLimitLayerForm();
		lForm.setLayers(currentInstanceData.getLayerList());

		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("limit");
		model.addObject("limitForm", lForm);
		if (errors != null && errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
		}
		return model;
	}




	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request
		, HttpServletResponse response
		, Object command
		, BindException errors) {

		CurrentInstanceData currentInstanceData = null;
		Long currentSessionId = null;
		HttpSession session = null;
		ChooseLimitLayerForm lForm = null;
		Double resolution = null;
		List<Layer> layers = null;

		if(errors.hasErrors())
			return showForm(request, response, errors);

		// get the information of the form.
		lForm = (ChooseLimitLayerForm)command;

		// retrieve the session Information.
		session = request.getSession();

		currentInstanceData = SessionUtils.isSessionAlive(session);
        currentSessionId = currentInstanceData.getUserSessionId();


		if(currentInstanceData != null && errors != null && !errors.hasErrors()){

			currentSessionId = currentInstanceData.getUserSessionId();
			resolution = currentInstanceData.getResolution();
			layers = currentInstanceData.getLayerList();

			try {

				//Import the layers
				this.importLayers(resolution, layers, currentSessionId);

			} catch (Exception ex) {
				Logger.getLogger(ColumnController.class.getName()).log(Level.SEVERE, null, ex);
				errors.reject(ex.getMessage());
                return showForm(request, response, errors);
			}
		
            try {

                this.grassManagerImpl.setRegion(lForm.getSelectedLayerName(), currentSessionId);
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


		// Set the new information to the session info 
        currentInstanceData.setLimitLayerName(lForm.getSelectedLayerName());
		session.setAttribute("CurrentSessionInfo", currentInstanceData);

        return columnController.showForm(request, response, errors);
	}

	//TODO: make this better
	/**
	 * Import a layer from a specific file system path to the grass database.
	 * @param resolution
	 * @param layerList
	 * @param currentSessionId
	 */
	private void importLayers(Double resolution , List<Layer> layerList , Long currentSessionId) throws Exception{

        // 1. Crear la nueva Locacion copiando la default a un nuevo lugar.
		try {
			// configure the location of grass
            this.grassManagerImpl.createNewLocation(currentSessionId);
		} catch (Exception ex) {
			throw new Exception("errors.cantCreateLocation", ex);
		}
        // 2. Configurar Grass.
		try {
			// configure the location of grass
            this.grassManagerImpl.configureEnvironment(currentSessionId);
		} catch (Exception ex) {
			throw new Exception("errors.cantConfigureGrass", ex);
		}

		try {
            // 3. Asignar la resolucion.
			this.grassManagerImpl.setResolution(resolution, currentSessionId);
		} catch (Exception ex) {
			throw new Exception("errors.cantSetResolution",ex);
		}

        // 4. Recorrer e iniciar importaciones.
		for (Layer layer: layerList){
			try {
                this.grassManagerImpl.importLayer(FormDTOConverter.convert(layer), currentSessionId);
			} catch (Exception ex) {
                throw new Exception("errors.cantImportLayer",ex);
			}
        }
	}


	/* Getters y Setters */
	public GrassManager getGrassManagerImpl() {
		return grassManagerImpl;
	}

	public void setGrassManagerImpl(GrassManager grassManagerImpl) {
		this.grassManagerImpl = grassManagerImpl;
	}

    public ColumnController getColumnController() {
        return columnController;
    }

    public void setColumnController(ColumnController columnController) {
        this.columnController = columnController;
    }
}