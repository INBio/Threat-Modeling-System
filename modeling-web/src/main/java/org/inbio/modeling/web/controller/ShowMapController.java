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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.inbio.modeling.core.manager.FileManager;
import org.inbio.modeling.core.manager.GrassManager;
import org.inbio.modeling.core.manager.LayerManager;
import org.inbio.modeling.web.form.ExportData;
import org.inbio.modeling.web.session.CurrentInstanceData;
import org.inbio.modeling.web.session.SessionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;


/**
 *
 * @author asanabria
 */
public class ShowMapController extends AbstractFormController {

	private GrassManager grassManagerImpl;
	private LayerManager layerManagerImpl;
	private FileManager fileManagerImpl;
    private SpeciesDistributionController speciesDistributionControllerImpl;

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request
		, HttpServletResponse response
		, Object command
		, BindException errors){

        return speciesDistributionControllerImpl.showForm(request, response, errors);
	}


	/** Default behavior on direct access */
	@Override
	protected ModelAndView showForm(HttpServletRequest request
		, HttpServletResponse response
		, BindException errors){

		HttpSession session = null;
		ModelAndView model = null;

		// retrieve the session Information.
		CurrentInstanceData currentInstanceData = null;


		// retrieve the session Information.
		session = request.getSession();
		currentInstanceData = SessionUtils.isSessionAlive(session);

		if(currentInstanceData == null){
			Exception ex = new Exception("errors.noSession");
			Logger.getLogger(ColumnController.class.getName()).log(Level.SEVERE, null, ex);
			errors.reject(ex.getMessage());
		}

		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("showResultingMap");
		model.addObject("exportForm", new ExportData());
		model.addObject("fullSessionInfo", currentInstanceData);
		model.addObject("speciesLayers", layerManagerImpl.getSpeciesDistributionLayerList());
		model.addObject("mainLayer", currentInstanceData.getLimitLayerName());

		if(errors != null && errors.hasErrors())
			model.addAllObjects(errors.getModel());

		return model;
	}

	// Getters & Setters
	public GrassManager getGrassManagerImpl() {
		return grassManagerImpl;
	}

	public void setGrassManagerImpl(GrassManager grassManagerImpl) {
		this.grassManagerImpl = grassManagerImpl;
	}

	public FileManager getFileManagerImpl() {
		return fileManagerImpl;
	}

	public void setFileManagerImpl(FileManager fileManagerImpl) {
		this.fileManagerImpl = fileManagerImpl;
	}

    public SpeciesDistributionController getSpeciesDistributionControllerImpl() {
        return speciesDistributionControllerImpl;
    }

    public void setSpeciesDistributionControllerImpl(SpeciesDistributionController speciesDistributionControllerImpl) {
        this.speciesDistributionControllerImpl = speciesDistributionControllerImpl;
    }

    public LayerManager getLayerManagerImpl() {
        return layerManagerImpl;
    }

    public void setLayerManagerImpl(LayerManager layerManagerImpl) {
        this.layerManagerImpl = layerManagerImpl;
    }
}
