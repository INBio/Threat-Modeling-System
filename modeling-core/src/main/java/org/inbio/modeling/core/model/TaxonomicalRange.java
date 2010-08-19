/* AIT - Analysis of taxonomic indicators
 *
 * Copyright (C) 2010  INBio (Instituto Nacional de Biodiversidad)
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

package org.inbio.modeling.core.model;

/**
 * Enum to describe the taxonomical range
 * @author esmata
 */

public enum TaxonomicalRange {

	ROOT(0, "Raíz","N/A"),
	KINGDOM(1,"Reino","kingdom_id"),
	PHYLUM(2, "Filo","phylum_id"),
	CLASS(3, "Clase","class_id"),
	ORDER(4,"Orden","order_id"),
	FAMILY(5,"Familia","family_id"),
	GENUS(6,"Género","genus_id"),
	SPECIFICEPITHET(7,"Especie","specific_epithet_id"),
    SCIENTIFICNAME(8,"NombreC","scientific_name_id");


	private int id;
	private String name;
    private String fieldName; //In taxon_info_index table

	/**
	 * @param id
	 * @param name
	 */
	private TaxonomicalRange(int id, String name,String fieldName) {
		this.id = id;
		this.name = name;
        this.fieldName = fieldName;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}

