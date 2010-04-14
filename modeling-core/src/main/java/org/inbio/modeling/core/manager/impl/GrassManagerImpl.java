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
import org.inbio.modeling.core.dto.IntervalDTO;
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
		this.grassDAOImpl.advanceReclasification(layerName, suffix);
	}

	@Override
	public void configureEnvironment(String location, Long suffix) throws Exception {
		this.grassDAOImpl.configureEnvironment(location, suffix);
	}

	@Override
	public void convertLayer2Raster(String layerName, Long suffix, boolean reclasified) throws Exception {
		this.grassDAOImpl.convertLayer2Raster(layerName, suffix, reclasified);
	}

	@Override
	public void simpleReclasification(String layerName, String column, Long suffix) throws Exception {
		this.grassDAOImpl.doSimpleReclasification(layerName, column, suffix);
	}

	@Override
	public void executeWeightedSum(String layerName1, Long weight1, String layerName2, Long weight2, Long suffix, String outputName) throws Exception {
		this.grassDAOImpl.executeWeightedSum(layerName1, weight1, layerName2, weight2, suffix, outputName);
	}

	@Override
	public void exportLayer2Image( Long suffix, String layerName) throws Exception {
		this.grassDAOImpl.exportLayer2Image(suffix, layerName);
	}

	@Override
	public List<IntervalDTO> getLayerCategories(String layerName, String layerType, Long suffix) throws Exception {
		return this.grassDAOImpl.getLayerCategories(layerName, layerType, suffix);
	}

	@Override
	public void getMinMaxValuesFromLayer(String layerName, Long suffix) throws Exception {
		this.grassDAOImpl.getMinMaxValuesFromLayer(layerName, suffix);
	}

	@Override
	public void importLayer(String layerName, Long suffix) throws Exception {
		this.grassDAOImpl.importLayer(layerName, suffix);
	}

	@Override
	public void setResolution(Double resolution, Long suffix) throws Exception {
		this.grassDAOImpl.setResolution(resolution, suffix);
	}

	@Override
	public HashMap<String,String> retrieveAvailableColumns(String layerName, Long suffix) throws Exception {
		return this.grassDAOImpl.retrieveAvailableColumns(layerName, suffix);
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
