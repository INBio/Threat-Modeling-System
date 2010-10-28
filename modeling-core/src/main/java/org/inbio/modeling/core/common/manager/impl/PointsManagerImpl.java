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
import org.inbio.modeling.core.common.dao.sys.SpecimenDAO;
import org.inbio.modeling.core.common.dao.sys.TaxonIndexDAO;
import org.inbio.modeling.core.common.dao.sys.TaxonInfoIndexDAO;
import org.inbio.modeling.core.common.manager.PointsManager;
import org.inbio.modeling.core.common.manager.QueryManager;
import org.inbio.modeling.core.common.model.Specimen;
import org.inbio.modeling.core.common.util.TaxonIndicatorRegionality;

/**
 *
 * @author esmata
 */
public class PointsManagerImpl implements PointsManager{

    //Dependency injections
    private TaxonInfoIndexDAO taxonInfoIndexDAO;
    private TaxonIndexDAO taxonIndexDAO;
    private SpecimenDAO specimenDAO;
    private QueryManager queryManager;

    @Override
    public List<Specimen> specimensByCriteria(String[] layerList, String[] taxonList, String[] indicList) {
        String colum = "globaluniqueidentifier";
        //If taxonList is empty the system is going to include all the kingdoms by default
        if(taxonList[0].equals("")){
            taxonList = this.taxonIndexDAO.getFormatedKingdoms().toArray(taxonList);
        }
        //Query variable
        String query = new String();
        //Getting a list of detailed information for taxon-indicator data
        List<TaxonIndicatorRegionality> regList = queryManager.getRegionalityList(taxonList, indicList);
        //List<TaxonIndicatorRegionality> regList = null;
        //If doesn't exist the taxon-indicator relation (do it as usual)
        if(regList==null || regList.isEmpty()){
            query = queryManager.elementsByCriteriaSql(layerList, taxonList, indicList, colum);
        }
        else{ //If there is taxon-indicator relations
            query = queryManager.elementsByCriteriaRegSql(layerList, colum, regList);
        }
        //Get globaluniqueidentifiers
        List<String> tiiList = getTaxonInfoIndexDAO().
                getGlobalUniqueIdentifiers(query.toString());
        //Get specimens by their globaluniqueidentifier
        StringBuilder specimenQuery = new StringBuilder();
        specimenQuery.append("Select * from ait.darwin_core as s where ");
        for(int i = 0;i<tiiList.size();i++){
            if(i==tiiList.size()-1){ //last element
                specimenQuery.append("s.globaluniqueidentifier = '"+tiiList.get(i)+"';");
            }
            else{
                specimenQuery.append("s.globaluniqueidentifier = '"+tiiList.get(i)+"' or ");
            }
        }
        //Execute the query
        return specimenDAO.getSpecimenListByQuery(specimenQuery.toString());
    }

    /**
     * @return the taxonInfoIndexDAO
     */
    public TaxonInfoIndexDAO getTaxonInfoIndexDAO() {
        return taxonInfoIndexDAO;
    }

    /**
     * @param taxonInfoIndexDAO the taxonInfoIndexDAO to set
     */
    public void setTaxonInfoIndexDAO(TaxonInfoIndexDAO taxonInfoIndexDAO) {
        this.taxonInfoIndexDAO = taxonInfoIndexDAO;
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

    /**
     * @return the specimenDAO
     */
    public SpecimenDAO getSpecimenDAO() {
        return specimenDAO;
    }

    /**
     * @param specimenDAO the specimenDAO to set
     */
    public void setSpecimenDAO(SpecimenDAO specimenDAO) {
        this.specimenDAO = specimenDAO;
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
