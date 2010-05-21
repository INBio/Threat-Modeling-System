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

import java.io.IOException;
import java.util.List;
import org.inbio.modeling.core.dto.GrassLayerDTO;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Handle FileSystem actions
 * @author asanabria
 */
public interface FileManager{

	/**
	 * Return a list of the names (without extension) of the .shp files whitin a
	 * folder in the file system.
	 * @return a list with the names of the available layers
	 */
    public List<String> listLayerHomeFolder() throws EmptyResultDataAccessException;

	/**
	 * Write a file to be used as rules file by grass gis on the
	 * reclassification step
	 * @param layer
	 * @param suffix
	 */
	public void writeReclasFile(GrassLayerDTO layer, Long suffix) throws IOException;

}
