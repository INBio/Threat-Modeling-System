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

import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.modeling.core.dao.GrassDAO;
import org.inbio.modeling.core.dto.CategoryDTO;
import org.inbio.modeling.core.manager.GrassManager;
import org.inbio.modeling.core.layer.LayerType;

/**
 *
 * @author asanabria
 */
public class GrassManagerImpl implements GrassManager {

	protected final Log logger = LogFactory.getLog(getClass());

	private GrassDAO grassDAOImpl;


	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#advanceReclasification(String layerName, Long suffix)
	 */
	public void advanceReclasification(String layerName, Long suffix) 
		throws Exception {

		this.grassDAOImpl.executeReclassification(layerName, suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#configureEnvironment(String location, Long suffix)
	 */
	public void configureEnvironment(String location, Long suffix)
		throws Exception {

		this.grassDAOImpl.configureEnvironment(location, suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#convertLayer2Raster(String layerName, Long suffix, String column)
	 */
	public void convertLayer2Raster(String layerName
									, Long suffix
									, String column)
									throws Exception {

		this.grassDAOImpl.executeRasterization(layerName, suffix, column);
	}


	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#executeWeightedSum(String layerName1 , Double weight1 , String layerName2 , Double weight2 , Long suffix , String outputName)
	 */
	public void executeWeightedSum(String layerName1
									, Double weight1
									, String layerName2
									, Double weight2
									, Long suffix
									, String outputName)
									throws Exception {

		this.grassDAOImpl.executeWeightedSum(layerName1, weight1, layerName2, weight2, suffix, outputName);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#exportLayer2Image( Long suffix, String layerName)
	 */
	public void exportLayer2Image( Long suffix
									, String layerName)
		throws Exception {

		this.grassDAOImpl.exportAsImage(suffix, layerName);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#getLayerCategories(String layerName , String layerType , Long suffix)
	 */
	public List<CategoryDTO> getLayerCategories(String layerName
												, String layerType
												, Long suffix)
												throws Exception {

		return this.grassDAOImpl.retrieveCategories(layerName, layerType, suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#retrieveLayerType(String layerName, Long suffix)
	 */
	public LayerType retrieveLayerType(String layerName, Long suffix) 
		throws Exception {

		return this.grassDAOImpl.retrieveLayerType(layerName, suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#getMinMaxValuesFromLayer(String layerName, Long suffix)
	 */
	public void getMinMaxValuesFromLayer(String layerName, Long suffix) 
		throws Exception {

		this.grassDAOImpl.retrieveMinMaxValues(layerName, suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#importLayer(String layerName, Long suffix)
	 */
	public void importLayer(String layerName, Long suffix) 
		throws Exception {

		this.grassDAOImpl.importLayer(layerName, suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#setResolution(Double resolution, Long suffix)
	 */
	public void setResolution(Double resolution, Long suffix)
		throws Exception {

		this.grassDAOImpl.asingResolution(resolution, suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#<String,String> retrieveAvailableColumns(String layerName
	 */
	public HashMap<String,String> retrieveAvailableColumns(String layerName
															, Long suffix)
															throws Exception {

		return this.grassDAOImpl.retrieveColumns(layerName, suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#executeVectorReclasification(String layerName
	 */
	public void executeVectorReclasification(String layerName
												, String column
												, Long suffix)
												throws Exception {

		this.grassDAOImpl.executeVectorReclasification(layerName, column, suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#deleteGRASSLocation(Long suffix) {
	 */
	public void deleteGRASSLocation(Long suffix) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#asingBuffers(String layerName, String distances, Long suffix)
	 */
	public void asingBuffers(String layerName, String distances, Long suffix)
		throws Exception{

		this.grassDAOImpl.asingBuffers(layerName, distances, suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#renameFile (String layerName, Long suffix)
	 */
	public void renameFile (String layerName, Long suffix)
		throws Exception{

		this.grassDAOImpl.rename(layerName, suffix);
	}

	/* getters and setters */
	public GrassDAO getGrassDAOImpl() {
		return grassDAOImpl;
	}

	public void setGrassDAOImpl(GrassDAO grassDAOImpl) {
		this.grassDAOImpl = grassDAOImpl;
	}

}