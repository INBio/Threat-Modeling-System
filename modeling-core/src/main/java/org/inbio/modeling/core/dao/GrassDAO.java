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
package org.inbio.modeling.core.dao;

import java.util.List;

/**
 * DAO to comunicate wit GRASS GIS
 * @author asanabria
 */
public interface GrassDAO extends BaseDAO {

	/**
	 * Configure the grassrc6 file that set the variables to executes scripts
	 * correctly
	 * @param location
	 * @param suffix
	 * @throws Exception
	 */
	public void configureEnvironment(String location
									, Long suffix)
									throws Exception;

	/**
	 * Import a layer from the file system into the grass database.
	 * @param layerName
	 * @param suffix
	 * @throws Exception
	 */
	public void importLayer(String shortOutputName
							, String uri
							, Long suffix)
							throws Exception;

	/**
	 * Convert a vectorial layer into a raster one
	 * @param layerName
	 * @param suffix
	 * @param column
	 * @throws Exception
	 */
	public void executeRasterization(String layerName
									, Long suffix
									, String column)
									throws Exception;

	/**
	 * Executes a weighted sum between two layers
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
	 * esport a raster map as an image
	 * @param suffix
	 * @param outputName
	 * @throws Exception
	 */
	public void exportAsImage(Long suffix
								, String outputName)
								throws Exception;

	/**
	 * Return the type of a layer according to the data store into the grass
	 * database.
	 * @param layerName
	 * @param suffix
	 * @return
	 * @throws Exception
	 */
	public String retrieveLayerType(String layerName
										, Long suffix)
										throws Exception;

	/**
	 * Return the type of a layer according to the data store into the grass
	 * database.
	 * @param layerName
	 * @param suffix
	 * @return minValue:maxValue
	 * @throws Exception
	 */
	public String retrieveMinMaxValues(String layerName
									, Long suffix)
									throws Exception;

	/**
	 * Return the categories o intervals asociated to a layer
	 * @param layerName
	 * @param layerType
	 * @param suffix
	 * @return
	 * @throws Exception
	 */
	public List<String> retrieveCategories(String layerName
												, String layerType
												, Long suffix)
												throws Exception;

	/**
	 * Reclassify a raster map with the file created by the
	 * org.inbio.modeling.core.manager.FileManager.writeReclassFile()
	 * @param layerName
	 * @param suffix
	 * @throws Exception
	 */
	public void executeReclassification(String layerName
										, Long suffix)
										throws Exception;

	/**
	 * Set the resolution to be used by grass in the maps.
	 * @param resolution
	 * @param suffix
	 * @throws Exception
	 */
	public void asingResolution(Double resolution
								, Long suffix)
								throws Exception;

	/**
	 * Asing color scale to the given layer, low values are green higher values
	 * are red.
	 * @param layerName
	 * @param suffix
	 * @throws Exception
	 */
	public void asingColorScale(String layerName
								, Long suffix)
								throws Exception;

	/**
	 * Delete a location
	 * @param suffix
	 */
	public void deleteGRASSLocation(Long suffix);

	/**
	 * Return the columns of the dbf associated to the layer
	 * @param layerName
	 * @param suffix
	 * @return
	 * @throws Exception
	 */
	public List<String> retrieveColumns(String layerName
										  , Long suffix)
										  throws Exception;

	/**
	 * Normalize the values of a vector layer and its dbf
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
	 * Generates buffers for line type layers.
	 * @param layerName
	 * @param distances
	 * @param suffix
	 * @throws Exception
	 */
	public void asingBuffers(String layerName
							, String distances
							, int magicNumber
							, boolean reverted
							, Long suffix)
							throws Exception;

	/**
	 * Rename a vector map from V_<layerBaseName>_suffix to V_<layerBaseName>_suffix_r
	 * @param layerName
	 * @param suffix
	 * @throws Exception
	 */
	public void rename(String layerName
						, Long suffix)
						throws Exception ;

    /**
     * Create a new Location within the grass database.
     * @param name
     * @throws Exception
     */
    public void createNewLocation(String name) throws Exception;

    /**
     * Configure the general region according to a specific layer.
     * @param limitLayerName
     * @param suffix
     * @throws Exception
     */
    public void setRegion(String limitLayerName, Long suffix) throws Exception;
}