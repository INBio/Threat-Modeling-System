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

package org.inbio.modeling.core.manager.impl;

import java.util.List;
import org.inbio.modeling.core.dao.SpecimenDAO;
import org.inbio.modeling.core.manager.TaxonomyManager;
import org.inbio.modeling.core.model.AutocompleteNode;
import org.inbio.modeling.core.model.TaxonomicalRange;

/**
 * @author esmata
 */
public class TaxonomyManagerImpl implements TaxonomyManager {

	// DAO's injection
    private SpecimenDAO specimenDAO;

    /**
     * To get the elements for autocomplete by taxonomical range
     * @param value
     * @param range
     * @return
     */
    @Override
	public List<AutocompleteNode> getElementsByTaxonomicalRange(String value,int range) {
        if(range == TaxonomicalRange.KINGDOM.getId()){
            return specimenDAO.getElementsByRange(value,range,"kingdom");
        }
        else if(range == TaxonomicalRange.PHYLUM.getId()){
            return specimenDAO.getElementsByRange(value,range,"phylum");
        }
        else if(range == TaxonomicalRange.CLASS.getId()){
            return specimenDAO.getElementsByRange(value,range,"class");
        }
        else if(range == TaxonomicalRange.ORDER.getId()){
            return specimenDAO.getElementsByRange(value,range,"orders");
        }
        else if(range == TaxonomicalRange.FAMILY.getId()){
            return specimenDAO.getElementsByRange(value,range,"family");
        }
        else if(range == TaxonomicalRange.GENUS.getId()){
            return specimenDAO.getElementsByRange(value,range,"genus");
        }
        else if(range == TaxonomicalRange.SPECIFICEPITHET.getId()){
            return specimenDAO.getElementsByRange(value,range,"specificepithet");
        }
        else if(range == TaxonomicalRange.SCIENTIFICNAME.getId()){
            return specimenDAO.getElementsByRange(value,range,"scientificname");
        }
        else return null;
	}

    /**
     * @return the specimenDAO
     */
    public // DAO's
    SpecimenDAO getSpecimenDAO() {
        return specimenDAO;
    }

    /**
     * @param specimenDAO the specimenDAO to set
     */
    public void setSpecimenDAO(SpecimenDAO specimenDAO) {
        this.specimenDAO = specimenDAO;
    }

}

