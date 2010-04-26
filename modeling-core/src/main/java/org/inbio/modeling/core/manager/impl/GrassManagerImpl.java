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

/**
 *
 * @author asanabria
 */
public class GrassManagerImpl implements GrassManager {

	protected final Log logger = LogFactory.getLog(getClass());

	private GrassDAO grassDAOImpl;


	@Override
	public void advanceReclasification(String layerName, Long suffix) throws Exception {
		this.grassDAOImpl.executeReclassification(layerName, suffix);
	}

	@Override
	public void configureEnvironment(String location, Long suffix) throws Exception {
		this.grassDAOImpl.configureEnvironment(location, suffix);
	}

	@Override
	public void convertLayer2Raster(String layerName, Long suffix, String column) throws Exception {
		this.grassDAOImpl.executeRasterization(layerName, suffix, column);
	}


	@Override
	public void executeWeightedSum(String layerName1, Double weight1, String layerName2, Double weight2, Long suffix, String outputName) throws Exception {
		this.grassDAOImpl.executeWeightedSum(layerName1, weight1, layerName2, weight2, suffix, outputName);
	}

	@Override
	public void exportLayer2Image( Long suffix, String layerName) throws Exception {
		this.grassDAOImpl.exportAsImage(suffix, layerName);
	}

	@Override
	public List<CategoryDTO> getLayerCategories(String layerName, String layerType, Long suffix) throws Exception {
		return this.grassDAOImpl.retrieveCategories(layerName, layerType, suffix);
	}

	@Override
	public void getMinMaxValuesFromLayer(String layerName, Long suffix) throws Exception {
		this.grassDAOImpl.retrieveMinMaxValues(layerName, suffix);
	}

	@Override
	public void importLayer(String layerName, Long suffix) throws Exception {
		this.grassDAOImpl.importLayer(layerName, suffix);
	}

	@Override
	public void setResolution(Double resolution, Long suffix) throws Exception {
		this.grassDAOImpl.asingResolution(resolution, suffix);
	}

	@Override
	public HashMap<String,String> retrieveAvailableColumns(String layerName, Long suffix) throws Exception {
		return this.grassDAOImpl.retrieveColumns(layerName, suffix);
	}

	@Override
	public void executeVectorReclasification(String layerName, String column, Long suffix) throws Exception {
		this.grassDAOImpl.executeVectorReclasification(layerName, column, suffix);
	}

	@Override
	public void deleteGRASSLocation(Long suffix) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/* getters and setters */
	public GrassDAO getGrassDAOImpl() {
		return grassDAOImpl;
	}

	public void setGrassDAOImpl(GrassDAO grassDAOImpl) {
		this.grassDAOImpl = grassDAOImpl;
	}
}
