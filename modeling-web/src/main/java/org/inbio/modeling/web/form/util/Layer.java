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

package org.inbio.modeling.web.form.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.map.LazyMap;
import org.inbio.modeling.core.layer.LayerType;

/**
 *
 * @author asanabria
 */
public class Layer {

	private boolean	selected = false;
	private boolean	reverted;
	private long	weight;
	private String	uri;
	private String	name;
	private String	description;

	private LayerType type;

	//TODO: delete this.
	// this is to allow dinamic binding with the web interface
	private Map<String,String>	columns =  
		LazyMap.decorate( new HashMap<String, String>(),
						  FactoryUtils.instantiateFactory(String.class));

	//TODO: delete this.
	// this is to allow dinamic binding with the web interface
	private List<Category>	categories =
		LazyList.decorate( new ArrayList(),
						   FactoryUtils.instantiateFactory(Category.class));

	public Layer() { }

	public Layer(String name, long weight ) {
		this.weight = weight;
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Map<String, String> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, String> columns) {
		this.columns = columns;
	}

	public LayerType getType() {
		return type;
	}

	public void setType(LayerType type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public boolean isReverted() {
		return reverted;
	}

	public void setReverted(boolean reverted) {
		this.reverted = reverted;
	}
}