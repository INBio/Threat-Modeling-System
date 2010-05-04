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
	public void importLayer(String layerName, Long suffix)
		throws Exception;

	/**
	 * Transform a vectorial layer into a raster one
	 * @param layerName
	 * @param suffix
	 * @param column
	 * @throws Exception
	 */
	public void convertLayer2Raster(String layerName, Long suffix, String column)
		throws Exception;

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
	public void executeWeightedSum(String layerName1
									, Double weight1
									, String layerName2
									, Double weight2
									, Long suffix
									, String outputName)
									throws Exception;

	/**
	 * Export a raster layer as an png image
	 * @param suffix
	 * @param layerName
	 * @throws Exception
	 */
	public void exportLayer2Image(Long suffix, String layerName )
		throws Exception;

	/**
	 * return the minimun and the maximun value of a raster layer
	 * @param layerName
	 * @param suffix
	 * @throws Exception
	 */
	public void getMinMaxValuesFromLayer(String layerName, Long suffix)
		throws Exception;

	/**
	 * Return the type (area, line, point) of a layer.
	 * @param layerName
	 * @param suffix
	 * @return
	 * @throws Exception
	 */
	public LayerType retrieveLayerType(String layerName, Long suffix)
		throws Exception;

	/**
	 * Return a list with the categories of a specific layer
	 * @param layerName
	 * @param layerType
	 * @param suffix
	 * @return
	 * @throws Exception
	 */
	public List<CategoryDTO> getLayerCategories(String layerName
		, String layerType
		, Long suffix)
		throws Exception;

	/**
	 * execute a raster reclasification base on the information writen into a
	 * rules file /tmp/rules-<suffix>.rcl
	 * @param layerName
	 * @param suffix
	 * @throws Exception
	 */
	public void advanceReclasification(String layerName	, Long suffix)
		throws Exception;

	/**
	 * Executes a normalization of the values of a vectorial layer
	 * @param layerName
	 * @param column
	 * @param suffix
	 * @throws Exception
	 */
	public void executeVectorReclasification(String layerName
											, String column
											, Long suffix)
											throws Exception;

	/**
	 * Change the resolution of a grass environment.
	 * @param resolution
	 * @param suffix
	 * @throws Exception
	 */
	public void setResolution(Double resolution, Long suffix) throws Exception;

	public Map<String,String> retrieveAvailableColumns(String layerName
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
	public void asingBuffers(String layerName, String distances, Long suffix)
		throws Exception;

	/**
	 * rename the name of a layer from V_<layerBasename>_sufix to
	 * V_<layerBasename>_sufix_r
	 * @param layerName
	 * @param suffix
	 * @throws Exception
	 */
	public void renameFile (String layerName, Long suffix) throws Exception;
}