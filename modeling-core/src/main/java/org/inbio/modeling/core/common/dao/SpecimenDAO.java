/* Modeling - Application to model threats.
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

package org.inbio.modeling.core.common.dao;

import java.util.List;
import org.inbio.modeling.core.common.model.AutocompleteNode;
import org.inbio.modeling.core.common.model.Specimen;
import org.inbio.modeling.core.common.model.SpecimenBase;

/**
 * Data access object interface for the Specimen model class
 * @author esmata
 */
public interface SpecimenDAO {

    /**
     * Getting a list of all specimens from data base via jdbc
     * @return
     */
    public List<Specimen> getSpecimenList();

    /**
     * Return all disctint elements for classes,phylums,kingdoms ...
     * @param partialName
     * @param range
     * @param atributeName
     * @return
     */
    public List<AutocompleteNode> getElementsByRange
            (String partialName,int range,String atributeName);

    /**
     * Get a specimen list by a especific query
     * @param q
     * @return
     */
    public List<Specimen> getSpecimenListByQuery(String q);

    public boolean deleteAllSpecimens();

    public int InsertSpecimen(SpecimenBase sp);

}
