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

package org.inbio.modeling.core.common.manager.impl;

import java.util.List;
import org.inbio.modeling.core.common.dao.sys.TaxonIndexDAO;
import org.inbio.modeling.core.common.manager.TaxonomyManager;
import org.inbio.modeling.core.common.model.AutocompleteNode;

/**
 * @author esmata
 */
public class TaxonomyManagerImpl implements TaxonomyManager {

	// DAO's injection
    private TaxonIndexDAO taxonIndexDAO;

    /**
     * To get the elements for autocomplete by taxonomical range
     * @param value
     * @param range
     * @return
     */
    @Override
	public List<AutocompleteNode> getElementsByTaxonomicalRange(String value,int range) {
        //View TaxonomicalRange enum
        switch (range) {
            case 1: //Kingdom
                return taxonIndexDAO.getElementsByRange(value,range);
            case 2: //phylum
                return taxonIndexDAO.getElementsByRange(value,range);
            case 3: //class
                return taxonIndexDAO.getElementsByRange(value,range);
            case 4: //orders
                return taxonIndexDAO.getElementsByRange(value,range);
            case 5: //family
                return taxonIndexDAO.getElementsByRange(value,range);
            case 6: //genus
                return taxonIndexDAO.getElementsByRange(value,range);
            case 7: //specificepithet
                return taxonIndexDAO.getElementsByRange(value,range);
            case 8: //scientificname
                return taxonIndexDAO.getElementsByRange(value,range);
           default:
               return null;
        }
        /*if(range == TaxonomicalRange.KINGDOM.getId()){
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
        else return null;*/
	}

    /**
     * @return the taxonIndexDAO
     */
    public TaxonIndexDAO getTaxonIndexDAO() {
        return taxonIndexDAO;
    }

    /**
     * @param taxonIndexDAO the taxonIndexDAO to set
     */
    public void setTaxonIndexDAO(TaxonIndexDAO taxonIndexDAO) {
        this.taxonIndexDAO = taxonIndexDAO;
    }

}

