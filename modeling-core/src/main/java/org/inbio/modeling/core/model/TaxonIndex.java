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
 * @author esmata
 */
public class TaxonIndex {

    //Costructor
    public TaxonIndex(){}

    //Atributes
    private Long taxon_id;
    private String taxon_name;
    private Long taxon_range;

    /**
     * @return the taxon_id
     */
    public Long getTaxon_id() {
        return taxon_id;
    }

    /**
     * @param taxon_id the taxon_id to set
     */
    public void setTaxon_id(Long taxon_id) {
        this.taxon_id = taxon_id;
    }

    /**
     * @return the taxon_name
     */
    public String getTaxon_name() {
        return taxon_name;
    }

    /**
     * @param taxon_name the taxon_name to set
     */
    public void setTaxon_name(String taxon_name) {
        this.taxon_name = taxon_name;
    }

    /**
     * @return the taxon_range
     */
    public Long getTaxon_range() {
        return taxon_range;
    }

    /**
     * @param taxon_range the taxon_range to set
     */
    public void setTaxon_range(Long taxon_range) {
        this.taxon_range = taxon_range;
    }

}
