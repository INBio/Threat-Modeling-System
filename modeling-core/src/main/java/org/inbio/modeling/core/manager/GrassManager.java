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
import org.inbio.modeling.core.dto.IntervalDTO;

public interface GrassManager{

	public void configureEnvironment(String location, Long suffix) throws Exception;

	public void importLayer(String layerName, Long suffix) throws Exception;

	public void convertLayer2Raster(String layerName, Long suffix, boolean reclasified) throws Exception;

	public void executeWeightedSum(String layerName1, Long weight1, String layerName2, Long weight2, Long suffix) throws Exception;

	public void exportLayer2Image(Long suffix) throws Exception;

	public void getMinMaxValuesFromLayer(String layerName, Long suffix) throws Exception;

	public void simpleReclasification(String layerName, String column, Long suffix) throws Exception;

	public List<IntervalDTO> getLayerCategories(String layerName, String layerType, Long suffix) throws Exception;

	public void advanceReclasification(String layerName, Long suffix) throws Exception;

	public void setResolution(Double resolution, Long suffix) throws Exception;

	public HashMap<String,String> retrieveAvailableColumns(String layerName, Long suffix) throws Exception;

	public void deleteGRASSLocation(Long suffix);
}
