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
import javax.servlet.http.HttpSession;
import org.inbio.modeling.core.dto.GrassLayerDTO;
import org.inbio.modeling.core.layer.LayerType;
import org.inbio.modeling.core.manager.FileManager;
import org.inbio.modeling.core.manager.GrassManager;
import org.inbio.modeling.web.form.EditIntervalForm;
import org.inbio.modeling.web.form.converter.FormDTOConverter;
import org.inbio.modeling.web.session.CurrentInstanceData;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;


/**
 *
 * @author asanabria
 */
public class ShowMapController extends AbstractFormController {

	private GrassManager grassManagerImpl;
	private FileManager fileManagerImpl;

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request
												, HttpServletResponse response
												, Object command
												, BindException errors)
												throws Exception {
		Long currentSessionId = null;
		HttpSession session = null;
		ModelAndView model = null;

		EditIntervalForm form = (EditIntervalForm)command;


		List<GrassLayerDTO> selectedLayers = null;
		selectedLayers = FormDTOConverter.convert(form.getLayers(), GrassLayerDTO.class);

		// retrieve the session Information.
		CurrentInstanceData currentInstanceData = null;


		// retrieve the session Information.
		session = request.getSession();
		currentInstanceData =
			(CurrentInstanceData)session.getAttribute("CurrentSessionInfo");

		currentSessionId = currentInstanceData.getUserSessionId();


		// Reclassification
		for(GrassLayerDTO layer : selectedLayers){

			if(LayerType.AREA == layer.getType()){
				// write the categories file.
				this.fileManagerImpl.writeReclasFile(layer, currentSessionId);
				// trigger the reclassification script.
				this.grassManagerImpl.
					advanceReclasification(layer, currentSessionId);
			}else{

				this.grassManagerImpl.asingBuffers(layer, currentSessionId);
			}
		}

		GrassLayerDTO layer1 = null;
		GrassLayerDTO layer2 = null;
		GrassLayerDTO layer3 = null;

		if(selectedLayers.size() >= 2){

			layer1 = selectedLayers.get(0);
			layer2 = selectedLayers.get(1);
			layer3  = new GrassLayerDTO("Res1", 100);

			this.grassManagerImpl.executeWeightedSum(layer1
													, layer2
													, layer3
													, currentSessionId);
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

		this.grassManagerImpl.asingColorScale(layer3, currentSessionId);
		this.grassManagerImpl.exportLayer2Image(layer3, currentSessionId);

		//save session
		currentInstanceData.setLayerList(form.getLayers());
		session.setAttribute("CurrentSessionInfo", currentInstanceData);

		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("showResultingMap");
		model.addObject("layer", layer3.getName());
		model.addObject("suffix", currentSessionId);
		model.addObject("fullSessionInfo", currentInstanceData);

		return model;
	}


	/** Default behavior on direct access */
	@Override
	protected ModelAndView showForm(HttpServletRequest request
									, HttpServletResponse response
									, BindException errors)
									throws Exception {
		return new ModelAndView("index");
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
}