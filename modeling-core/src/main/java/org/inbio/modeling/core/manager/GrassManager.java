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

import java.util.HashMap;
import java.util.List;
import org.inbio.modeling.core.dto.CategoryDTO;
import org.inbio.modeling.core.maps.LayerType;

public interface GrassManager{

	public void configureEnvironment(String location, Long suffix) throws Exception;

	public void importLayer(String layerName, Long suffix) throws Exception;

	public void convertLayer2Raster(String layerName, Long suffix, String column) throws Exception;

	public void executeWeightedSum(String layerName1, Double weight1, String layerName2, Double weight2, Long suffix, String outputName) throws Exception;

	public void exportLayer2Image(Long suffix, String layerName ) throws Exception;

	public void getMinMaxValuesFromLayer(String layerName, Long suffix) throws Exception;

	public LayerType retrieveLayerType(String layerName, Long suffix) throws Exception;

	public List<CategoryDTO> getLayerCategories(String layerName, String layerType, Long suffix) throws Exception;

	public void advanceReclasification(String layerName, Long suffix) throws Exception;

	public void executeVectorReclasification(String layerName, String column, Long suffix) throws Exception;

	public void setResolution(Double resolution, Long suffix) throws Exception;

	public HashMap<String,String> retrieveAvailableColumns(String layerName, Long suffix) throws Exception;

	public void deleteGRASSLocation(Long suffix);

	public void asingBuffers(String layerName, String distances, Long suffix) throws Exception;

	public void renameFile (String layerName, Long suffix) throws Exception;
}
