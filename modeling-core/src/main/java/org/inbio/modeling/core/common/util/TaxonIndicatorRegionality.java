/* Modeling - Application to model threats
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

package org.inbio.modeling.core.common.util;

import java.util.List;

/**
 *
 * @author esmata
 * Class to determine the list of regionalities by taxon indicator relation
 */
public class TaxonIndicatorRegionality {

    //Atributes
    private String taxon;
    private String indicator;
    private List<Long> countries;

    //Constructor
    public TaxonIndicatorRegionality(){}

    /**
     * @return the taxon
     */
    public String getTaxon() {
        return taxon;
    }

    /**
     * @param taxon the taxon to set
     */
    public void setTaxon(String taxon) {
        this.taxon = taxon;
    }

    /**
     * @return the indicator
     */
    public String getIndicator() {
        return indicator;
    }

    /**
     * @param indicator the indicator to set
     */
    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    /**
     * @return the countries
     */
    public List<Long> getCountries() {
        return countries;
    }

    /**
     * @param countries the countries to set
     */
    public void setCountries(List<Long> countries) {
        this.countries = countries;
    }

}
