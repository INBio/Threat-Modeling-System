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

package org.inbio.modeling.core.common.manager;

import java.util.List;
import org.inbio.modeling.core.common.model.AutocompleteNode;


/**
 * @author esmata
 */
public interface TaxonomyManager {

    /**
     * To get the elements for autocomplete by taxonomical range
     * @param value
     * @param range
     * @return
     */
	public List<AutocompleteNode> getElementsByTaxonomicalRange(String value, int range);
}

