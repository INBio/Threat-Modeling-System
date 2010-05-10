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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.modeling.core.dao.GrassDAO;
import org.inbio.modeling.core.dto.CategoryDTO;
import org.inbio.modeling.core.dto.LayerDTO;
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
	public void advanceReclasification(LayerDTO layer, Long suffix)
		throws Exception {

		this.grassDAOImpl.executeReclassification(layer.getName() , suffix);
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
	public void convertLayer2Raster(LayerDTO layer , Long suffix)
		throws Exception {

		String column = layer.getColumns().get("selected");

		this.grassDAOImpl.executeRasterization(layer.getName(), suffix, column);
	}


	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#executeWeightedSum(String layerName1 , Double weight1 , String layerName2 , Double weight2 , Long suffix , String outputName)
	 */
	public void executeWeightedSum(LayerDTO layer1
									, LayerDTO layer2
									, LayerDTO output
									, Long suffix)
									throws Exception {

		// convert from 1 - 100 percentage value to 0.0 - 1.0 relative value.
		Double w1 = layer1.getWeight()/100D;
		Double w2 = layer2.getWeight()/100D;

		this.grassDAOImpl.executeWeightedSum(layer1.getName()
											, w1 // double value of the weight1
											, layer2.getName()
											, w2 // double value of the weight2
											, suffix
											, output.getName());
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#exportLayer2Image( Long suffix, String layerName)
	 */
	public void exportLayer2Image( LayerDTO layer, Long suffix)
		throws Exception {

		this.grassDAOImpl.exportAsImage(suffix, layer.getName());
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#getLayerCategories(String layerName , String layerType , Long suffix)
	 */
	public List<CategoryDTO> getLayerCategories(LayerDTO layer
												, String dataType
												, Long suffix)
												throws Exception {

		String sTemp = null;
		String[] tarray = null;
		CategoryDTO category = null;
		List<String> categories = null;
		List<CategoryDTO> categoryList = null;

		double dTemp = 0D;
		double minValue = 0D;
		double maxValue = 0D;
		double intervalSize = 0D;


		categoryList = new ArrayList<CategoryDTO>();
		if(dataType.equals("CHARACTER")){
			// get the categories of the raster version of the map.
			categories = this.grassDAOImpl.retrieveCategories(layer.getName(), "RAST", suffix);

			for(String stringCategory : categories ){

				tarray = stringCategory.split(":");
				category = new CategoryDTO();
				category.setValue(tarray[0]);
				category.setDescription(tarray[1]);
				categoryList.add(category);
			}
		}else if(layer.getType() == LayerType.AREA){
			// get the categories of the raster version of the map.
			sTemp = this.grassDAOImpl.retrieveMinMaxValues(layer.getName(), suffix);
			tarray = sTemp.split(":");
			minValue = Double.parseDouble(tarray[0]);
			maxValue = Double.parseDouble(tarray[1]);

			intervalSize = (maxValue - minValue) / 10D;

			dTemp = minValue;
			for(int i = 0; dTemp < maxValue; i++){
				category = new CategoryDTO();
				category.setValue(String.format("%10.4f", dTemp)+"-"+String.format("%10.4f",(dTemp+intervalSize)));
				category.setDescription("Interval "+(i+1));
				categoryList.add(category);

				dTemp = dTemp+intervalSize;
			}
		}else{
	
			for(int i = 0; i< 5;i++)
				categoryList.add(new CategoryDTO(String.valueOf((i+1)*500)));

		}

		return categoryList;
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#retrieveLayerType(String layerName, Long suffix)
	 */
	public LayerType retrieveLayerType(LayerDTO layer, Long suffix)
		throws Exception {

		LayerType mapType = null;
		String type = this.grassDAOImpl.retrieveLayerType(layer.getName(), suffix);

		if(LayerType.AREA.match(type))
			mapType = LayerType.AREA;
		else if(LayerType.LINE.match(type))
			mapType = LayerType.LINE;
		else
			mapType = LayerType.POINT;

		return mapType;
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#getMinMaxValuesFromLayer(String layerName, Long suffix)
	 */
	public void getMinMaxValuesFromLayer(LayerDTO layer, Long suffix)
		throws Exception {

		this.grassDAOImpl.retrieveMinMaxValues(layer.getName(), suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#importLayer(String layerName, Long suffix)
	 */
	public void importLayer(LayerDTO layer, Long suffix)
		throws Exception {

		this.grassDAOImpl.importLayer(layer.getName(), suffix);
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
	public Map<String,String> retrieveAvailableColumns(LayerDTO layer
														, Long suffix)
														throws Exception {
		List<String> stringColumns = null;
		Map<String,String> columns = null;
		String[] splittedStringColumn = null;

		stringColumns = this.grassDAOImpl.retrieveColumns(layer.getName(), suffix);

		columns = new HashMap<String, String>();

		for(String stringColumn : stringColumns){
			splittedStringColumn = stringColumn.split(":");
			columns.put(splittedStringColumn[0], splittedStringColumn[1]);

		}
		return columns;
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#executeVectorReclasification(String layerName
	 */
	public void executeVectorReclasification(LayerDTO layer, Long suffix)
		throws Exception {

		String column = layer.getColumns().get("selected");

		this.grassDAOImpl.
			executeVectorReclasification(layer.getName() , column , suffix);
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
	public void asingBuffers(LayerDTO layer, Long suffix)
		throws Exception{

		List<CategoryDTO> categoryList = null;
		String distances = null;
		int	listLenght = -1;


				categoryList = layer.getCategories();
				listLenght = categoryList.size();
				distances = "";

				for(CategoryDTO cat :layer.getCategories()){
					if(listLenght > 1)
						distances += cat.getValue() + ",";
					else
						distances += cat.getValue();
					listLenght--;
				}


		this.grassDAOImpl.asingBuffers(layer.getName(), distances, suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#asingColorScale(LayerDTO layer, Long suffix)
	 */
	public void asingColorScale(LayerDTO layer , Long suffix) throws Exception{
		this.grassDAOImpl.asingColorScale(layer.getName(), suffix);
	}

	@Override
	/**
	 * @see org.inbio.modeling.core.manager.GrassManager#renameFile (String layerName, Long suffix)
	 */
	public void renameFile (LayerDTO layer, Long suffix)
		throws Exception{

		this.grassDAOImpl.rename(layer.getName(), suffix);
	}

	/* getters and setters */
	public GrassDAO getGrassDAOImpl() {
		return grassDAOImpl;
	}

	public void setGrassDAOImpl(GrassDAO grassDAOImpl) {
		this.grassDAOImpl = grassDAOImpl;
	}

}