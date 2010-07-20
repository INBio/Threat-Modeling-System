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
package org.inbio.modeling.core.manager;

import java.util.List;
import org.inbio.modeling.core.dto.GrassLayerDTO;
import org.inbio.modeling.core.dto.LayerDTO;

public interface LayerManager{

	/**
	 * Return a list with the basename of the layers available in the specified
	 * folder (in the properties file).
	 * @return a list of layers
	 */
    public List<GrassLayerDTO> getLayerList();

	/**
	 * find all the database stored layers
	 * @return
	 */
    public List<LayerDTO> getRegisteredLayers();

	/**
	 * create a new layer in the database
	 * @param newLayer
	 */
	public void createLayer(LayerDTO newLayer);

	/**
	 * update a layer in the database
	 * @param newLayer
	 */
	public void updateLayer(LayerDTO newLayer);

	/**
	 * Delete a layer by its id;
	 * @param layerId
	 */
	public void deleteLayer(Long layerId);

	/**
	 * return a layer identified by its id.
	 * @param id
	 * @return
	 */
	public LayerDTO getLayerById(Long id);

    /**
     * retrieve a list of the layers identified to be species distribution
     * maps.
     * @return
     */
    public List<GrassLayerDTO> getSpeciesDistributionLayerList() ;
}
