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
package org.inbio.modeling.core.manager.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.modeling.core.dao.LayerDAO;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.core.dto.LayerDTOFactory;
import org.inbio.modeling.core.manager.LayerManager;

public class LayerManagerImpl implements LayerManager {

    protected final Log logger = LogFactory.getLog(getClass());

	private LayerDAO layerDAO;
	private LayerDTOFactory layerDTOFactory;

    @Override
    public List<LayerDTO> getLayerList() {
		return layerDTOFactory.createDTOList(layerDAO.getAvailableLayers());
    }

	public LayerDAO getLayerDAO() {
		return layerDAO;
	}

	public void setLayerDAO(LayerDAO layerDAO) {
		this.layerDAO = layerDAO;
	}

	public LayerDTOFactory getLayerDTOFactory() {
		return layerDTOFactory;
	}

	public void setLayerDTOFactory(LayerDTOFactory layerDTOFactory) {
		this.layerDTOFactory = layerDTOFactory;
	}
}
