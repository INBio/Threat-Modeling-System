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
import org.inbio.modeling.core.dto.GrassLayerDTO;
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
    public List<GrassLayerDTO> getSpeciesDistributionLayerList() {

		List<Layer> registeredLayers = null;
		List<GrassLayerDTO> resultList = null;
		GrassLayerDTO layerDTO = null;

		registeredLayers = this.layerDAOImpl.findAllSpeciesLayers();

		resultList = new ArrayList<GrassLayerDTO>();

		// Convert the registered layers in layerDTOs
		for(Layer layer : registeredLayers){
			layerDTO = new GrassLayerDTO();
			layerDTO.setName(layer.getName());
			layerDTO.setDisplayName(layer.getDisplayName());
			layerDTO.setDescription(layer.getDescription());
			layerDTO.setUri(layer.getUri());
			resultList.add(layerDTO);
		}

		return resultList;
    }


    @Override
	/**
	 * @see org.inbio.modeling.core.manager.LayerManager#getLayerList()
	 */
    public List<GrassLayerDTO> getLayerList() {

		List<String> unregisteredLayers = null;
		List<Layer> registeredLayers = null;
		List<GrassLayerDTO> resultList = null;
		GrassLayerDTO layerDTO = null;

		unregisteredLayers = this.fileManagerImpl.listLayerHomeFolder();
		registeredLayers = this.layerDAOImpl.findAll();

		resultList = new ArrayList<GrassLayerDTO>();

		// Convert the unregistered layers in layerDTOs
		for(String layerName : unregisteredLayers)
			resultList.add(new GrassLayerDTO(layerName, 0));

		// Convert the registered layers in layerDTOs
		for(Layer layer : registeredLayers){
			layerDTO = new GrassLayerDTO();
			layerDTO.setName(layer.getName());
			layerDTO.setDisplayName(layer.getDisplayName());
			layerDTO.setDescription(layer.getDescription());
			layerDTO.setUri(layer.getUri());
			resultList.add(layerDTO);
		}

		return resultList;
    }

    @Override
	/**
	 * @see org.inbio.modeling.core.manager.LayerManager#getRegisteredLayers()
	 */
    public List<LayerDTO> getRegisteredLayers() {

		List<Layer> registeredLayers = null;
		List<LayerDTO> resultList = null;
		LayerDTO layerDTO = null;

		registeredLayers = this.layerDAOImpl.findAll();

		resultList = new ArrayList<LayerDTO>();

		// Convert the registered layers in layerDTOs
		for(Layer layer : registeredLayers){
			layerDTO = new LayerDTO();
			layerDTO.setId(layer.getId());
			layerDTO.setName(layer.getName());
			layerDTO.setDescription(layer.getDescription());
			layerDTO.setDisplayName(layer.getDisplayName());
			layerDTO.setUri(layer.getUri());
			layerDTO.setYear(layer.getYear());
            layerDTO.setDataScale(layer.getDataScale());
            layerDTO.setVizScale(layer.getVizScale());
            layerDTO.setSource(layer.getSource());
            layerDTO.setGenerationProcedure(layer.getGenerationProcedure());

			resultList.add(layerDTO);
		}

		return resultList;
    }


	@Override
	public void createLayer(LayerDTO newLayer){

		Layer layer = new Layer();

        layer.setName(newLayer.getName());
        layer.setDisplayName(newLayer.getDisplayName());
        layer.setDescription(newLayer.getDescription());
        layer.setUri(newLayer.getUri());
        layer.setYear(newLayer.getYear());
        layer.setSpeciesMap(newLayer.isSpeciesMap());
        layer.setDataScale(newLayer.getDataScale());
        layer.setVizScale(newLayer.getVizScale());
        layer.setSource(newLayer.getSource());
        layer.setGenerationProcedure(newLayer.getGenerationProcedure());

		this.layerDAOImpl.create(layer);
	}

	@Override
	public void updateLayer(LayerDTO newLayer){

		Layer layer = layerDAOImpl.findById(newLayer.getId());

        layer.setName(newLayer.getName());
        layer.setDescription(newLayer.getDescription());
        layer.setDisplayName(newLayer.getDisplayName());
        layer.setUri(newLayer.getUri());
        layer.setYear(newLayer.getYear());
        layer.setSpeciesMap(newLayer.isSpeciesMap());
        layer.setDataScale(newLayer.getDataScale());
        layer.setVizScale(newLayer.getVizScale());
        layer.setSource(newLayer.getSource());
        layer.setGenerationProcedure(newLayer.getGenerationProcedure());

		this.layerDAOImpl.update(layer);
	}

	@Override
	public LayerDTO getLayerById(Long id){

		LayerDTO resultLayer = null;
		Layer layer = null;

		layer = this.layerDAOImpl.findById(id);

		resultLayer = new LayerDTO();

		resultLayer.setId(layer.getId());
		resultLayer.setName(layer.getName());
		resultLayer.setDescription(layer.getDescription());
        resultLayer.setDisplayName(layer.getDisplayName());
		resultLayer.setUri(layer.getUri());
        resultLayer.setSpeciesMap(layer.isSpeciesMap());
        resultLayer.setYear(layer.getYear());
        resultLayer.setDataScale(layer.getDataScale());
        resultLayer.setVizScale(layer.getVizScale());
        resultLayer.setSource(layer.getSource());
        resultLayer.setGenerationProcedure(layer.getGenerationProcedure());

		return resultLayer;
	}

	@Override
	public LayerDTO getLayerByName(String name){

		LayerDTO resultLayer = null;
		Layer layer = null;

		layer = this.layerDAOImpl.findByName(name);

		resultLayer = new LayerDTO();

		resultLayer.setId(layer.getId());
		resultLayer.setName(layer.getName());
		resultLayer.setDescription(layer.getDescription());
        resultLayer.setDisplayName(layer.getDisplayName());
		resultLayer.setUri(layer.getUri());
        resultLayer.setSpeciesMap(layer.isSpeciesMap());
        resultLayer.setYear(layer.getYear());
        resultLayer.setDataScale(layer.getDataScale());
        resultLayer.setVizScale(layer.getVizScale());
        resultLayer.setSource(layer.getSource());
        resultLayer.setGenerationProcedure(layer.getGenerationProcedure());

		return resultLayer;
	}

	@Override
	public void deleteLayer(Long layerId){
		this.layerDAOImpl.deleteById(layerId);
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
