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
package org.inbio.modeling.core.manager.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.modeling.core.dao.LayerDAO;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.core.manager.FileManager;
import org.inbio.modeling.core.manager.LayerManager;
import org.inbio.modeling.core.model.Layer;

public class LayerManagerImpl implements LayerManager {

    protected final Log logger = LogFactory.getLog(getClass());

	private FileManager fileManagerImpl;
	private LayerDAO layerDAOImpl;

    @Override
	/**
	 * @see org.inbio.modeling.core.manager.LayerManager#getLayerList()
	 */
    public List<LayerDTO> getLayerList() {

		List<String> unregisteredLayers = null;
		List<Layer> registeredLayers = null;
		List<LayerDTO> resultList = null;
		LayerDTO layerDTO = null;

		unregisteredLayers = this.fileManagerImpl.listLayerHomeFolder();
		registeredLayers = this.layerDAOImpl.findAll();

		resultList = new ArrayList<LayerDTO>();

		// Convert the unregistered layers in layerDTOs
		for(String layerName : unregisteredLayers)
			resultList.add(new LayerDTO(layerName, 0));

		// Convert the registered layers in layerDTOs
		for(Layer layer : registeredLayers){
			layerDTO = new LayerDTO();
			layerDTO.setName(layer.getName());
			layerDTO.setDescription(layer.getDescription());
			layerDTO.setUri(layer.getUri());
			resultList.add(layerDTO);
		}

		return resultList;
    }

	public FileManager getFileManagerImpl() {
		return fileManagerImpl;
	}

	public void setFileManagerImpl(FileManager fileManagerImpl) {
		this.fileManagerImpl = fileManagerImpl;
	}

	public LayerDAO getLayerDAOImpl() {
		return layerDAOImpl;
	}

	public void setLayerDAOImpl(LayerDAO layerDAOImpl) {
		this.layerDAOImpl = layerDAOImpl;
	}
}
