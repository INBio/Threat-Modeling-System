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
package org.inbio.modeling.core.manager;

import java.util.List;
import java.util.Map;
import org.inbio.modeling.core.dto.CategoryDTO;
import org.inbio.modeling.core.dto.GrassLayerDTO;
import org.inbio.modeling.core.layer.LayerType;

public interface GrassManager{

	/**
	 * Configure the grassrc6 file to use the grass gis software
	 * @param location
	 * @param suffix
	 * @throws Exception
	 */
	public void configureEnvironment(String location, Long suffix)
		throws Exception;

	/**
	 * Import a layer from the file system to the grass database
	 * @param layerName
	 * @param suffix
	 * @throws Exception
	 */
	public void importLayer(GrassLayerDTO layer, Long suffix)
		throws Exception;

	/**
	 * Transform a vectorial layer into a raster one
	 * @param layerName
	 * @param suffix
	 * @param column
	 * @throws Exception
	 */
	public void convertLayer2Raster(GrassLayerDTO layer, Long suffix) throws Exception;

	/**
	 * Executes a weighted sum with the provided layers and weights
	 * @param layerName1
	 * @param weight1
	 * @param layerName2
	 * @param weight2
	 * @param suffix
	 * @param outputName
	 * @throws Exception
	 */
	public void executeWeightedSum(GrassLayerDTO layer1
									, GrassLayerDTO layer2
									, GrassLayerDTO outpututName
									, Long suffix)
									throws Exception;

	/**
	 * Export a raster layer as an png image
	 * @param suffix
	 * @param layerName
	 * @throws Exception
	 */
	public void exportLayer2Image(GrassLayerDTO layer, Long suffix)
		throws Exception;

	/**
	 * return the minimun and the maximun value of a raster layer
	 * @param layerName
	 * @param suffix
	 * @throws Exception
	 */
	public void getMinMaxValuesFromLayer(GrassLayerDTO layer, Long suffix)
		throws Exception;

	/**
	 * Return the type (area, line, point) of a layer.
	 * @param layerName
	 * @param suffix
	 * @return
	 * @throws Exception
	 */
	public LayerType retrieveLayerType(GrassLayerDTO layer, Long suffix)
		throws Exception;

	/**
	 * Return a list with the categories of a specific layer
	 * @param layerName
	 * @param layerType
	 * @param suffix
	 * @return
	 * @throws Exception
	 */
	public List<CategoryDTO> getLayerCategories(GrassLayerDTO layer
												, String dataType
												, Long suffix)
		throws Exception;

	/**
	 * execute a raster reclasification base on the information writen into a
	 * rules file /tmp/rules-<suffix>.rcl
	 * @param layerName
	 * @param suffix
	 * @throws Exception
	 */
	public void advanceReclasification(GrassLayerDTO layer, Long suffix)
		throws Exception;

	/**
	 * Executes a normalization of the values of a vectorial layer
	 * @param layerName
	 * @param column
	 * @param suffix
	 * @throws Exception
	 */
	public void executeVectorReclasification(GrassLayerDTO layer , Long suffix)
		throws Exception;

	/**
	 * Change the resolution of a grass environment.
	 * @param resolution
	 * @param suffix
	 * @throws Exception
	 */
	public void setResolution(Double resolution, Long suffix) throws Exception;

	public Map<String,String> retrieveAvailableColumns(GrassLayerDTO layer
															, Long suffix)
															throws Exception;

	/**
	 * Delete the location to free some space from the grass database.
	 * @param suffix
	 */
	public void deleteGRASSLocation(Long suffix);

	/**
	 * Asing buffers
	 * @param layerName
	 * @param distances
	 * @param suffix
	 * @throws Exception
	 */
	public void asingBuffers(GrassLayerDTO layer, Long suffix)
		throws Exception;

	/**
	 * rename the name of a layer from V_<layerBasename>_sufix to
	 * V_<layerBasename>_sufix_r
	 * @param layerName
	 * @param suffix
	 * @throws Exception
	 */
	public void renameFile (GrassLayerDTO layer, Long suffix) throws Exception;

	/**
	 * Asing color scale to the given layer, low values are green higher values
	 * are red.
	 * @param layerName
	 * @param suffix
	 * @throws Exception
	 */
	public void asingColorScale(GrassLayerDTO layer , Long suffix) throws Exception;

    /**
     * Create a new location within the grass database.
     * @param newLocationName
     * @throws Exception
     */
    public void createNewLocation(String newLocationName) throws Exception;


    /**
     * Sets a region in a grass location.
     * @param limitLayerName
     * @param suffix
     */
    public void setRegion(String limitLayerName, Long suffix) throws Exception;
}