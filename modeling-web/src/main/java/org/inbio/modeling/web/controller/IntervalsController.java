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
import org.inbio.modeling.core.dto.CategoryDTO;
import org.inbio.modeling.core.dto.GrassLayerDTO;
import org.inbio.modeling.core.layer.LayerType;
import org.inbio.modeling.core.manager.ConfigManager;
import org.inbio.modeling.core.manager.FileManager;
import org.inbio.modeling.core.manager.GrassManager;
import org.inbio.modeling.web.form.EditIntervalForm;
import org.inbio.modeling.web.form.converter.FormDTOConverter;
import org.inbio.modeling.web.session.CurrentInstanceData;
import org.inbio.modeling.web.session.SessionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 * @author asanabria
 */
public class IntervalsController extends AbstractFormController {

	// manager to comunicate with the grass gis software
	private GrassManager grassManagerImpl;
    // manager to read or write to the File System
	private FileManager fileManagerImpl;
    // Show the final map and the information related  to it
    private ShowMapController showMapController;
    private ConfigManager configManager;

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request
		, HttpServletResponse response
		, Object command
		, BindException errors) {

		GrassLayerDTO resultLayer = null;
		Long currentSessionId = null;
		HttpSession session = null;
		ModelAndView model = null;
        Double resolution = null;

		if(errors.hasErrors())
			return showForm(request, response, errors);


		EditIntervalForm form = (EditIntervalForm)command;


		List<GrassLayerDTO> selectedLayers = null;
		selectedLayers = FormDTOConverter.convert(form.getLayers(), GrassLayerDTO.class);

		// retrieve the session Information.
		CurrentInstanceData currentInstanceData = null;

		// retrieve the session Information.
		session = request.getSession();
		currentInstanceData =
			(CurrentInstanceData)session.getAttribute("CurrentSessionInfo");

		currentInstanceData = SessionUtils.isSessionAlive(session);

		if(currentInstanceData != null){

			currentSessionId = currentInstanceData.getUserSessionId();
            resolution = Double.valueOf(currentInstanceData.getResolution());

			try {

                int maxIntervalQuantity = 0;
				// Reclassification
                for(GrassLayerDTO layer : selectedLayers){
                    if(!layer.getType().equals(LayerType.POINT)){

                        int intervalQuantity = this.countCategories(layer.getCategories());

                        if(intervalQuantity > maxIntervalQuantity)
                            maxIntervalQuantity = intervalQuantity;


                    }
                }

				// Reclassification
				for(GrassLayerDTO layer : selectedLayers){

					if(LayerType.AREA == layer.getType()){
						this.writeFile(layer, currentSessionId);
						// trigger the reclassification script.
						this.reclass(layer, currentSessionId);
					}else if(LayerType.LINE== layer.getType()){
						// create the buffers.
						this.createBuffers(layer, currentSessionId);
					}else{
						// create the buffers.
						this.calculateDensity(layer, resolution , String.valueOf(maxIntervalQuantity), currentSessionId);
					}
				}

				resultLayer = this.weightedSum(selectedLayers, currentSessionId);

                this.applyMainLayer(currentInstanceData.getLimitLayerName(), resultLayer.getName(), currentSessionId);
				this.setColorScale(resultLayer, currentSessionId);
				this.createImage(resultLayer, currentSessionId);

			} catch (Exception ex) {
				Logger.getLogger(IntervalsController.class.getName()).log(Level.SEVERE, null, ex);
				errors.reject(ex.getMessage());

				return showForm(request, response, errors);
			}
		}else{

			Exception ex = new Exception("errors.noSession");
			Logger.getLogger(ColumnController.class.getName()).log(Level.SEVERE, null, ex);
			errors.reject(ex.getMessage());

			return showForm(request, response, errors);
		}
		//save session
		currentInstanceData.setImageName(resultLayer.getName());
		currentInstanceData.setLayerList(form.getLayers());
		session.setAttribute("CurrentSessionInfo", currentInstanceData);

        return showMapController.showForm(request, response, errors);
	}

    private int countCategories(List<CategoryDTO> cats){

        int result = 0;

        for(CategoryDTO cat : cats)
            if(cat != null)
                result++;

        return result;

    }

    private void applyMainLayer(String mainLayerName, String resultLayerName, Long currentSessionId) throws Exception{
        try{
            this.grassManagerImpl.applyMainLayer(mainLayerName, resultLayerName, currentSessionId);
        } catch (Exception ex) {
            throw new Exception("errors.cantExecuteWeightedSum", ex);
        }
    }

	/** default behavior for direct access */
	@Override
	protected ModelAndView showForm(HttpServletRequest request
		, HttpServletResponse response
		, BindException errors){

		CurrentInstanceData currentInstanceData = null;
		EditIntervalForm iForm = null;
		HttpSession session = null;
		ModelAndView model = null;

		// retrieve the session Information.
		session = request.getSession();
		currentInstanceData = SessionUtils.isSessionAlive(session);

		if(currentInstanceData != null){
			iForm = new EditIntervalForm();
			iForm.setLayers(currentInstanceData.getLayerList());
		}else{
			Exception ex = new Exception("errors.noSession");
			Logger.getLogger(ColumnController.class.getName()).log(Level.SEVERE, null, ex);
			errors.reject(ex.getMessage());
		}


		model = new ModelAndView();
		model.setViewName("intervals");
		model.addObject("intervalsForm", iForm);

		if(errors != null && errors.hasErrors())
			model.addAllObjects(errors.getModel());

		return model;
	}

	/**
	 * Create a rules file to reclassificate a raster map. (change the cateogires).
	 * @param layer
	 * @param currentSessionId
	 * @throws Exception
	 */
	private void writeFile(GrassLayerDTO layer, Long currentSessionId) throws Exception{
		try {
			// write the categories file.
			this.fileManagerImpl.writeReclasFile(layer, currentSessionId);
		} catch (Exception ex) {
			throw new Exception("errors.cantWriteFile", ex);
		}

	}

	/**
	 * Change the clasification of a vector map.
	 * @param layer
	 * @param currentSessionId
	 * @throws Exception
	 */
	private void reclass(GrassLayerDTO layer, Long currentSessionId) throws Exception{
		try {
			this.grassManagerImpl.advanceReclasification(layer, currentSessionId);
		} catch (Exception ex) {
			throw new Exception("errors.cantExecuteReclass", ex);
		}

	}

	/**
	 * Create buffers in a line type vector file
	 * @param layer
	 * @param currentSessionId
	 * @throws Exception
	 */
	private void createBuffers(GrassLayerDTO layer, Long currentSessionId) throws Exception{
		try {
			this.grassManagerImpl.asingBuffers(layer, currentSessionId);
		} catch (Exception ex) {
			throw new Exception("errors.cantCreateBuffers", ex);
		}

	}

	/**
	 * executes a weighted Sum in a list of layers
	 * @param selectedLayers
	 * @param currentSessionId
	 * @return
	 * @throws Exception
	 */
	private GrassLayerDTO weightedSum(List<GrassLayerDTO> selectedLayers, Long currentSessionId) throws Exception{

		GrassLayerDTO layer1 = null;
		GrassLayerDTO layer2 = null;
		GrassLayerDTO layer3 = null;
		try {

			if(selectedLayers.size() >= 2){
				layer1 = selectedLayers.get(0);
				layer2 = selectedLayers.get(1);
				layer3 = new GrassLayerDTO("Res1", 100);
				this.grassManagerImpl.executeWeightedSum(layer1, layer2, layer3, currentSessionId);
			}

			for(int i = 2; i<selectedLayers.size(); i++){

				layer1 = layer3;
				layer2 = selectedLayers.get(i);
				layer3  = new GrassLayerDTO("Res"+i, 100);

				this.grassManagerImpl.executeWeightedSum(layer1
					, layer2
					, layer3
					, currentSessionId);
			}

		} catch (Exception ex) {
			throw new Exception("errors.cantExecuteWeightedSum", ex);
		}
		return layer3;
	}


	/**
	 * Set the color scale of the map, the default color scale is gyr.
	 * @param layer
	 * @param currentSessionId
	 * @throws Exception
	 */
	private void setColorScale(GrassLayerDTO layer, Long currentSessionId) throws Exception{
		try {
			this.grassManagerImpl.asingColorScale(layer, currentSessionId);
		} catch (Exception ex) {
			throw new Exception("errors.cantSetColorScale", ex);
		}

	}

	/**
	 * Create an image file from the specified layer.
	 * @param layer
	 * @param currentSessionId
	 * @throws Exception
	 */
	private void createImage(GrassLayerDTO layer, Long currentSessionId) throws Exception{
		try {
			this.grassManagerImpl.exportLayer2Image(layer, currentSessionId);
		} catch (Exception ex) {
			throw new Exception("errors.cantCreateImage", ex);
		}
	}

    private void calculateDensity(GrassLayerDTO layer, Double resolution, String maxIntervalQuantity, Long currentSessionId) throws Exception{

        Double radioInMeters = Double.valueOf(layer.getCategories().get(0).getValue());
        Double conversionRate = Double.parseDouble(configManager.retrieveResolution());

        Double cellNumber = (radioInMeters * conversionRate )/ resolution;

        if((cellNumber % 2) == 0)
            cellNumber++;

        layer.getCategories().get(0).setValue(cellNumber.toString());

		try {
            this.grassManagerImpl.calculateDensity(layer, radioInMeters.toString(), maxIntervalQuantity, currentSessionId);
		} catch (Exception ex) {
			throw new Exception("errors.cantCreateImage", ex);
		}
    }

	/* Getters and Setters */
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

    public ShowMapController getShowMapController() {
        return showMapController;
    }

    public void setShowMapController(ShowMapController showMapController) {
        this.showMapController = showMapController;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }

}