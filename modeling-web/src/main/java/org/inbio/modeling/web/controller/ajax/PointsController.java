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

package org.inbio.modeling.web.controller.ajax;

import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.modeling.core.manager.PointsManager;
import org.inbio.modeling.core.model.Specimen;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author esmata
 */
public class PointsController implements Controller{

    private PointsManager pointsManager;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String paramLayer = request.getParameter("layers");
        String paramTaxon = request.getParameter("taxons");
        String paramIndi = request.getParameter("indi");
		String errorMsj = "Error con los parámetros: "+
                paramLayer+" "+paramTaxon+" "+paramIndi;

		try {

            //Arrays that contains the parameters data
            String[] layerArray = paramLayer.split("\\|");
            String[] taxonArray = paramTaxon.split("\\|");
            String[] indiArray = paramIndi.split("\\|");

            //Specimens that match with the search criteria
            List<Specimen> specimens = pointsManager.specimensByCriteria
                    (layerArray, taxonArray, indiArray);

			return writeReponse(request,response,specimens);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
    }

	private ModelAndView writeReponse(HttpServletRequest request,
			HttpServletResponse response,
            List<Specimen> specimens) throws Exception {

		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("text/xml");
		ServletOutputStream out = response.getOutputStream();

        StringBuilder result = new StringBuilder();
        result.append("<?xml version='1.0' encoding='ISO-8859-1'?><response><specimens>");

        for(Specimen s : specimens){
            result.append("<specimen><scientificname>"+s.getScientificname()+"</scientificname>" +
                    "<longitude>"+s.getDecimallongitude()+"</longitude>"+
                    "<catalog>"+s.getCatalognumber()+"</catalog>"+
                    "<latitude>"+s.getDecimallatitude()+"</latitude></specimen>");
        }
        result.append("</specimens></response>");

        out.println(result.toString());
		out.flush();
		out.close();

		return null;
	}

    /**
     * @return the pointsManager
     */
    public PointsManager getPointsManager() {
        return pointsManager;
    }

    /**
     * @param pointsManager the pointsManager to set
     */
    public void setPointsManager(PointsManager pointsManager) {
        this.pointsManager = pointsManager;
    }

}
