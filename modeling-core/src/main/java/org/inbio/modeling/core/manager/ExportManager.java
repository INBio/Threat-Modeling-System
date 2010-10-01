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

import com.lowagie.text.Document;
import java.io.FileInputStream;
import java.util.List;
import java.util.Locale;
import org.inbio.modeling.core.dto.GrassLayerDTO;

/**
 * Handle FileSystem actions
 * @author asanabria
 */
public interface ExportManager{

    /**
     * Export an image from a raster layer
     * @param layerName
     * @param suffix
     * @return
     */
    public FileInputStream exportImage(String layerName, long suffix) throws Exception;

    public FileInputStream exportShapefile(String layerName, long suffix) throws Exception;

    public Document exportPDF(Document document, Double resolution, String imageName, String limitLayerName, List<GrassLayerDTO>  layerList, long currentSessionId, Locale locale) throws Exception;

}
