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

package org.inbio.modeling.web.common.controller.ajax;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.modeling.core.common.manager.QueryManager;
import org.inbio.modeling.web.common.controller.utils.Node;
import org.inbio.modeling.web.common.controller.utils.TaxonInfoIndexColums;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author esmata
 */
public class QueryController implements Controller{

    private QueryManager queryManager;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		String paramLayer = request.getParameter("layers");
        String paramTaxon = request.getParameter("taxons");
        String paramIndi = request.getParameter("indi");
        String limitPolygons = request.getParameter("limit");
		String errorMsj = "Error con los parámetros: "+paramLayer+" "+paramTaxon+" "+paramIndi+" "+limitPolygons;

		try {
            //Arrays that contains the parameters data
            String[] layerArray = paramLayer.split("\\|");
            String[] taxonArray = paramTaxon.split("\\|");
            String[] indiArray = paramIndi.split("\\|");
            String[] limitArray = limitPolygons.split("\\|");

            /*If there is just one criteria category or especifically these categories
            (geographical-taxonomical or taxonomical-indicator). It means type 0 on xml*/
            //------------------------------------------------------------------
            if(isOneGeoTaxOrIndiTax(layerArray,taxonArray,indiArray)){
                //Total of matches in the limit polygons
                Long totalLimitMatches = queryManager.countByCriteria
                        (limitArray, taxonArray, indiArray,TaxonInfoIndexColums.SPECIES.getName());
                //Total of matches
                Long totalMatches = queryManager.countByCriteria
                        (layerArray, taxonArray, indiArray,TaxonInfoIndexColums.SPECIES.getName());
                //Percentage that represents the total of retrived results
                Long totalPercentage = 0L;
                if(totalLimitMatches>0L){
                    totalPercentage = (totalMatches*100)/totalLimitMatches;
                }
                return writeReponse0(request,response,totalMatches,totalPercentage);
            }
            //If there are three criteria categories. It means type 1 on xml
            else{
                //Total of matches in the limit polygons
                Long totalLimitMatches = queryManager.countByCriteria
                        (limitArray, taxonArray, indiArray,TaxonInfoIndexColums.SPECIES.getName());
                //Total of matches
                Long totalMatches = queryManager.countByCriteria
                        (layerArray, taxonArray, indiArray,TaxonInfoIndexColums.SPECIES.getName());
                //Percentage that represents the total of retrived results
                Long totalPercentage = 0L;
                if(totalLimitMatches>0L){
                    totalPercentage = (totalMatches*100)/totalLimitMatches;
                }
                //Total matches by polygon
                List<Node> matchesByPolygon = new ArrayList<Node>();
                for(int i=0;i<layerArray.length;i++){
                    String[] thePolygon = {layerArray[i]};
                    Long countAbs = queryManager.countByCriteria
                        (thePolygon, taxonArray, indiArray,TaxonInfoIndexColums.SPECIES.getName());
                    Long percentage = 0L;
                    if(totalLimitMatches>0){
                        percentage = (countAbs*100)/totalLimitMatches;
                    }
                    Node aux = new Node(); //Absulute count,percentage
                    aux.setValue1(countAbs);
                    aux.setValue2(percentage);
                    matchesByPolygon.add(aux);
                }
                //Total matches by indicator
                List<Node> matchesByIndicator = new ArrayList<Node>();
                for(int i=0;i<indiArray.length;i++){
                    String[] theIndicator = {indiArray[i]};
                    Long countAbs = queryManager.countByCriteria
                        (layerArray, taxonArray, theIndicator,TaxonInfoIndexColums.SPECIES.getName());
                    Long percentage = 0L;
                    if(totalLimitMatches>0){
                        percentage = (countAbs*100)/totalLimitMatches;
                    }
                    Node aux = new Node(); //Absulute count,percentage
                    aux.setValue1(countAbs);
                    aux.setValue2(percentage);
                    matchesByIndicator.add(aux);
                }
                return writeReponse1(request,response,matchesByPolygon,matchesByIndicator,totalMatches,totalPercentage);
            }

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
    }

    /**
     * Method to determine if (a) there is one criteria category or these
     * two (geographical and taxonomical)
     * Return false when there are three different criteria categories or there is nothing at all
     * Return true if (a)
     */
    private boolean isOneGeoTaxOrIndiTax(String[] l, String[] t, String[] i) {
        int ll = myLength(l);
        int tl = myLength(t);
        int il = myLength(i);

        if(ll != 0 && tl != 0 && il == 0){ //Geo-Tax
            return true;
        }
        else if(ll == 0 && tl != 0 && il != 0){ //Indi-Tax
            return true;
        }
        else if((ll!=0&&tl==0&&il==0)||(ll==0&&tl!=0&&il==0)||(ll==0&&tl==0&&il!=0)){ //Just one
            return true;
        }
        else{
            return false;
        }
    }
    private int myLength(String[] array){
        if(array==null){
            return 0;
        }
        else{
            if(array[0].equals("")){
                array = new String[0];
            }
            return array.length;
        }
    }

    /**
     * Write the XML to be parsed on the analysis view
     * Case 0: If there is just one criteria category or especifically these categories
     * (geographical-taxonomical or or taxonomical-indicator). It means type 0 on xml
     * @param request
     * @param response
     * @param totalMatch
     * @param matchesByPolygon
     * @return
     * @throws java.lang.Exception
     */
	private ModelAndView writeReponse0(HttpServletRequest request,
			HttpServletResponse response, Long totalMatch,Long totalPercentage) throws Exception {

		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("text/xml");
		ServletOutputStream out = response.getOutputStream();

        StringBuilder result = new StringBuilder();
        result.append("<?xml version='1.0' encoding='ISO-8859-1'?><response>");
        result.append("<type>0</type>");
        result.append("<total>"+totalMatch+"</total>");
        result.append("<totalp>"+totalPercentage+"</totalp></response>");

        out.println(result.toString());
		out.flush();
		out.close();

		return null;
	}

    /**
     * Write the XML to be parsed on the analysis view
     * Case: three parameters (1)
     * @param request
     * @param response
     * @param totalMatch
     * @param matchesByPolygon
     * @return
     * @throws java.lang.Exception
     */
	private ModelAndView writeReponse1(HttpServletRequest request,
			HttpServletResponse response, List<Node> matchesByPolygon,
            List<Node> matchesByIndicator,Long totalMatches,Long totalPercentage) throws Exception {

		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("text/xml");
		ServletOutputStream out = response.getOutputStream();

        StringBuilder result = new StringBuilder();
        result.append("<?xml version='1.0' encoding='ISO-8859-1'?><response>");
        result.append("<type>1</type>");
        result.append("<total>"+totalMatches+"</total>");
        result.append("<totalp>"+totalPercentage+"</totalp>");
        for(Node mp : matchesByPolygon){
            result.append("<bypolygon>");
            result.append("<abs>"+mp.getValue1()+"</abs>");
            result.append("<per>"+mp.getValue2()+"</per>");
            result.append("</bypolygon>");
        }
        for(Node mi : matchesByIndicator){
            result.append("<byindicator>");
            result.append("<abs>"+mi.getValue1()+"</abs>");
            result.append("<per>"+mi.getValue2()+"</per>");
            result.append("</byindicator>");
        }
        result.append("</response>");

        out.println(result.toString());
		out.flush();
		out.close();

		return null;
	}

    /**
     * @return the queryManager
     */
    public QueryManager getQueryManager() {
        return queryManager;
    }

    /**
     * @param queryManager the queryManager to set
     */
    public void setQueryManager(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

}
