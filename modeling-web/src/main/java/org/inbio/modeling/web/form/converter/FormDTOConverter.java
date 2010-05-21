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
package org.inbio.modeling.web.form.converter;

import java.util.ArrayList;
import java.util.List;
import org.inbio.modeling.core.dto.CategoryDTO;
import org.inbio.modeling.core.dto.GrassLayerDTO;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.web.form.CategoryForm;
import org.inbio.modeling.web.form.GrassLayerForm;

/**
 *
 * @author asanabria
 */
public class FormDTOConverter {

	/**
	 * Convert from GrassLayerForm to GrassLayerDTO
	 * @param layer
	 * @return
	 */
	public static GrassLayerDTO convert(GrassLayerForm layer){

		GrassLayerDTO layerDTO = new GrassLayerDTO();

		layerDTO.setName(layer.getName());
		layerDTO.setDescription(layer.getDescription());
		layerDTO.setUri(layer.getUri());
		layerDTO.setColumns(layer.getColumns());
		layerDTO.setWeight(layer.getWeight());
		layerDTO.setType(layer.getType());
		layerDTO.setCategories(convert(layer.getCategories(), CategoryDTO.class));

		return layerDTO;

	}

	/**
	 * Convert from GrassLayerDTO to GrassLayerForm
	 * @param layer
	 * @return
	 */
	public static GrassLayerForm convert(GrassLayerDTO layer){

		GrassLayerForm layerForm = new GrassLayerForm();

		layerForm.setName(layer.getName());
		layerForm.setDescription(layer.getDescription());
		layerForm.setUri(layer.getUri());
		layerForm.setColumns(layer.getColumns());
		layerForm.setWeight(layer.getWeight());
		layerForm.setType(layer.getType());

		layerForm.setCategories(
			convert(layer.getCategories(), CategoryForm.class));

		return layerForm;

	}

	/**
	 * convert from CategoryDTO to CategoryForm
	 * @param category
	 * @return
	 */
	public static CategoryForm convert(CategoryDTO category){

		CategoryForm categoryForm = new CategoryForm();

		categoryForm.setInterval(category.isInterval());
		categoryForm.setValue(category.getValue());
		categoryForm.setDescription(category.getDescription());
		return categoryForm;
	}

	/**
	 * Convert from CategoryForm to Cateogry DTO
	 * @param category
	 * @return
	 */
	public static CategoryDTO convert(CategoryForm category){

		CategoryDTO categoryDTO = new CategoryDTO();

		categoryDTO.setInterval(category.isInterval());
		categoryDTO.setValue(category.getValue());
		categoryDTO.setDescription(category.getDescription());
		return categoryDTO;
	}


	/**
	 *
	 * Convert from a list of type <S> to a list of type <D>
	 * S: stand for Source
	 * D: stand for Destity
	 * @param list
	 * @param destTypeClass
	 * @return
	 */
	public static <D, S> List<D> convert(List<S> list, Class<D> destTypeClass){

		List<D> destList = new ArrayList<D>();

		if(list == null || list.isEmpty())
			return null;

		for(S item : list){
			if(destTypeClass == GrassLayerForm.class){
				destList.add(destTypeClass.cast(convert((GrassLayerDTO)item)));
			}else if(destTypeClass == GrassLayerDTO.class){
				destList.add(destTypeClass.cast(convert((GrassLayerForm)item)));
			}else if(destTypeClass == CategoryForm.class){
				destList.add(destTypeClass.cast(convert((CategoryDTO)item)));
			}else if(destTypeClass == CategoryDTO.class){
				destList.add(destTypeClass.cast(convert((CategoryForm)item)));
			}
		}

		return destList;
	}
}
