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
package org.inbio.modeling.core.common.manager.impl;

import java.util.List;
import org.inbio.modeling.core.common.dao.SpecimenDAO;
import org.inbio.modeling.core.common.dao.TaxonIndexDAO;
import org.inbio.modeling.core.common.dao.TaxonInfoIndexDAO;
import org.inbio.modeling.core.common.manager.PointsManager;
import org.inbio.modeling.core.common.model.Specimen;
import org.inbio.modeling.core.common.model.TaxonIndex;
import org.inbio.modeling.core.common.model.TaxonomicalRange;

/**
 *
 * @author esmata
 */
public class PointsManagerImpl implements PointsManager{

    //Dependency injections
    private TaxonInfoIndexDAO taxonInfoIndexDAO;
    private TaxonIndexDAO taxonIndexDAO;
    private SpecimenDAO specimenDAO;

    @Override
    public List<Specimen> specimensByCriteria(String[] layerList, String[] taxonList, String[] indicList) {
        //Build the query string base on parameters
        StringBuilder query = new StringBuilder();
        query.append("Select distinct globaluniqueidentifier from ait.taxon_info_index where ");

        //If there is geografical criteria
        if(layerList.length>0 && !layerList[0].equals("")){
            query.append("(");
            for(int i = 0;i<layerList.length;i++){
                String[] aux = layerList[i].split("~");
                String layer = aux[0];
                String polygon = aux[1];
                if(i==layerList.length-1){ //last element
                    query.append("(layer_table = '"+layer+"' and polygom_id = "+polygon+")");
                }
                else{
                    query.append("(layer_table = '"+layer+"' and polygom_id = "+polygon+") or ");
                }
            }
            query.append(")");
        }

        //If there is taxonomy criteria
        if(taxonList.length>0 && !taxonList[0].equals("")){
            if(layerList.length>0 && !layerList[0].equals("")){
                query.append(" and (");
            }
            else{
                query.append("(");
            }
            for(int i = 0;i<taxonList.length;i++){
                //Get the name and taxonomical level of the specified taxon
                TaxonIndex ti = getTaxonIndexDAO().getTaxonIndexByName(taxonList[i]);
                if(ti.getTaxon_id()!=null){
                    //To search in the specified taxonomyField
                    String levelColum;
                    switch (ti.getTaxon_range().intValue()) {
                        case 1:
                            levelColum = TaxonomicalRange.KINGDOM.getFieldName();
                            break;
                        case 2:
                            levelColum = TaxonomicalRange.PHYLUM.getFieldName();
                            break;
                        case 3:
                            levelColum = TaxonomicalRange.CLASS.getFieldName();
                            break;
                        case 4:
                            levelColum = TaxonomicalRange.ORDER.getFieldName();
                            break;
                        case 5:
                            levelColum = TaxonomicalRange.FAMILY.getFieldName();
                            break;
                        case 6:
                            levelColum = TaxonomicalRange.GENUS.getFieldName();
                            break;
                        case 7:
                            levelColum = TaxonomicalRange.SPECIFICEPITHET.getFieldName();
                            break;
                        default:
                            levelColum = TaxonomicalRange.SCIENTIFICNAME.getFieldName();
                            break;
                    }
                    if(i==taxonList.length-1){ //last element
                        query.append("("+levelColum+" = "+ti.getTaxon_id()+")");
                    }
                    else{
                        query.append("("+levelColum+" = "+ti.getTaxon_id()+") or ");
                    }
                }
                else{ //If the taxon doesn't exist on data base
                    String levelColum = TaxonomicalRange.KINGDOM.getFieldName();
                    if(i==taxonList.length-1){ //last element
                        query.append("("+levelColum+" = "+-1+")");
                    }
                    else{
                        query.append("("+levelColum+" = "+-1+") or ");
                    }
                }

            }
            query.append(")");
        }

        //If there is indicators criteria
        if(indicList.length>0 && !indicList[0].equals("")){
            if((taxonList.length>0 && !taxonList[0].equals(""))||(layerList.length>0 && !layerList[0].equals(""))){
                query.append(" and (");
            }
            else{
                query.append("(");
            }
            for(int i = 0;i<indicList.length;i++){
                if(i==indicList.length-1){ //last element
                    query.append("(indicator_id = "+indicList[i]+")");
                }
                else{
                    query.append("(indicator_id = "+indicList[i]+") or ");
                }
            }
            query.append(")");
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

        //System.out.println(query.toString());

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

}
