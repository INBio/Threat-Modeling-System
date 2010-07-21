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
	 * @param currentSessionId
	 * @throws Exception
	 */
	public void configureEnvironment( Long currentSessionId)
									throws Exception;

	/**
	 * Import a layer from the file system into the grass database.
	 * @param layerName
	 * @param currentSessionId
	 * @throws Exception
	 */
	public void importLayer(String shortOutputName
							, String uri
							, Long currentSessionId)
							throws Exception;

	/**
	 * Convert a vectorial layer into a raster one
	 * @param layerName
	 * @param currentSessionId
	 * @param column
	 * @throws Exception
	 */
	public void executeRasterization(String layerName
									, Long currentSessionId
									, String column)
									throws Exception;

	/**
	 * Executes a weighted sum between two layers
	 * @param layerName1
	 * @param weight1
	 * @param layerName2
	 * @param weight2
	 * @param currentSessionId
	 * @param outputName
	 * @throws Exception
	 */
	public void executeWeightedSum(String layerName1
									, Double weight1
									, String layerName2
									, Double weight2
									, Long currentSessionId
									, String outputName)
									throws Exception;

	/**
	 * esport a raster map as an image
	 * @param currentSessionId
	 * @param outputName
	 * @throws Exception
	 */
	public void exportAsImage(Long currentSessionId
								, String outputName)
								throws Exception;

	/**
	 * Return the type of a layer according to the data store into the grass
	 * database.
	 * @param layerName
	 * @param currentSessionId
	 * @return
	 * @throws Exception
	 */
	public String retrieveLayerType(String layerName
										, Long currentSessionId)
										throws Exception;

	/**
	 * Return the type of a layer according to the data store into the grass
	 * database.
	 * @param layerName
	 * @param currentSessionId
	 * @return minValue:maxValue
	 * @throws Exception
	 */
	public String retrieveMinMaxValues(String layerName
									, Long currentSessionId)
									throws Exception;

	/**
	 * Return the categories o intervals asociated to a layer
	 * @param layerName
	 * @param layerType
	 * @param currentSessionId
	 * @return
	 * @throws Exception
	 */
	public List<String> retrieveCategories(String layerName
												, String layerType
												, Long currentSessionId)
												throws Exception;

	/**
	 * Reclassify a raster map with the file created by the
	 * org.inbio.modeling.core.manager.FileManager.writeReclassFile()
	 * @param layerName
	 * @param currentSessionId
	 * @throws Exception
	 */
	public void executeReclassification(String layerName
										, Long currentSessionId)
										throws Exception;

	/**
	 * Set the resolution to be used by grass in the maps.
	 * @param resolution
	 * @param currentSessionId
	 * @throws Exception
	 */
	public void asingResolution(Double resolution
								, Long currentSessionId)
								throws Exception;

	/**
	 * Asing color scale to the given layer, low values are green higher values
	 * are red.
	 * @param layerName
	 * @param currentSessionId
	 * @throws Exception
	 */
	public void asingColorScale(String layerName
								, Long currentSessionId)
								throws Exception;

	/**
	 * Delete a location
	 * @param currentSessionId
	 */
	public void deleteGRASSLocation(Long currentSessionId);

	/**
	 * Return the columns of the dbf associated to the layer
	 * @param layerName
	 * @param currentSessionId
	 * @return
	 * @throws Exception
	 */
	public List<String> retrieveColumns(String layerName
										  , Long currentSessionId)
										  throws Exception;

	/**
	 * Normalize the values of a vector layer and its dbf
	 * @param layerName
	 * @param column
	 * @param currentSessionId
	 * @throws Exception
	 */
	public void executeVectorReclasification(String layerName
											, String column
											, Long currentSessionId)
											throws Exception;

	/**
	 * Generates buffers for line type layers.
	 * @param layerName
	 * @param distances
	 * @param currentSessionId
	 * @throws Exception
	 */
	public void asingBuffers(String layerName
							, String distances
							, int magicNumber
							, boolean reverted
							, Long currentSessionId)
							throws Exception;

	/**
	 * Rename a vector map from V_<layerBaseName>_currentSessionId to V_<layerBaseName>_currentSessionId_r
	 * @param layerName
	 * @param currentSessionId
	 * @throws Exception
	 */
	public void rename(String layerName
						, Long currentSessionId)
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
     * @param currentSessionId
     * @throws Exception
     */
<<<<<<< HEAD
<<<<<<< HEAD
    public void setRegion(String limitLayerName, Long currentSessionId) throws Exception;
=======
    public void setRegion(String limitLayerName, Long suffix) throws Exception;
>>>>>>> Add support to substract a layer to the final result
=======
    public void setRegion(String limitLayerName, Long currentSessionId) throws Exception;
>>>>>>> next

    /**
     * Species map - Threats map.
     * @param resmap
     * @param speciesMapName
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> next
     * @param currentSessionId
     * @throws Exception
     */
    public void mixSpeciesDistributionLayer(String resmap, String speciesMapName, Long currentSessionId) throws Exception;
<<<<<<< HEAD
=======
     * @param suffix
     * @throws Exception
     */
    public void mixSpeciesDistributionLayer(String resmap, String speciesMapName, Long suffix) throws Exception;
>>>>>>> Add support to substract a layer to the final result
=======
>>>>>>> next

}