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

import org.inbio.modeling.web.form.util.Layer;
import java.util.ArrayList;
import java.util.List;
import org.inbio.modeling.core.dto.CategoryDTO;
import org.inbio.modeling.core.dto.GrassLayerDTO;
import org.inbio.modeling.web.form.util.Category;

/**
 *
 * @author asanabria
 */
public class FormDTOConverter {

	/**
	 * Convert from Layer to GrassLayerDTO
	 * @param layer
	 * @return
	 */
	public static GrassLayerDTO convert(Layer layer){

		if(layer == null)
			return null;

		GrassLayerDTO layerDTO = new GrassLayerDTO();

		layerDTO.setName(layer.getName());
		layerDTO.setDescription(layer.getDescription());
		layerDTO.setUri(layer.getUri());
		layerDTO.setColumns(layer.getColumns());
		layerDTO.setWeight(layer.getWeight());
		layerDTO.setType(layer.getType());
		layerDTO.setCategories(convert(layer.getCategories(), CategoryDTO.class));
		layerDTO.setReverted(layer.isReverted());

		return layerDTO;

	}

	/**
	 * Convert from GrassLayerDTO to Layer
	 * @param layer
	 * @return
	 */
	public static Layer convert(GrassLayerDTO layer){

		if(layer == null)
			return null;

		Layer layerForm = new Layer();

		layerForm.setName(layer.getName());
		layerForm.setDescription(layer.getDescription());
		layerForm.setUri(layer.getUri());
		layerForm.setColumns(layer.getColumns());
		layerForm.setWeight(layer.getWeight());
		layerForm.setType(layer.getType());
		layerForm.setReverted(layer.isReverted());

		layerForm.setCategories(
			convert(layer.getCategories(), Category.class));

		return layerForm;

	}

	/**
	 * convert from CategoryDTO to Category
	 * @param category
	 * @return
	 */
	public static Category convert(CategoryDTO category){

		if(category == null)
			return null;

		Category categoryForm = new Category();

		categoryForm.setInterval(category.isInterval());
		categoryForm.setValue(category.getValue());
		categoryForm.setDescription(category.getDescription());
		return categoryForm;
	}

	/**
	 * Convert from Category to Cateogry DTO
	 * @param category
	 * @return
	 */
	public static CategoryDTO convert(Category category){

		if(category == null)
			return null;

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
			if(destTypeClass == Layer.class){
				destList.add(destTypeClass.cast(convert((GrassLayerDTO)item)));
			}else if(destTypeClass == GrassLayerDTO.class){
				destList.add(destTypeClass.cast(convert((Layer)item)));
			}else if(destTypeClass == Category.class){
				destList.add(destTypeClass.cast(convert((CategoryDTO)item)));
			}else if(destTypeClass == CategoryDTO.class){
				destList.add(destTypeClass.cast(convert((Category)item)));
			}
		}

		return destList;
	}
}
