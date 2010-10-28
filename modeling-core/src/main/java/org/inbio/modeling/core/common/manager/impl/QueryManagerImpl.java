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

import java.util.ArrayList;
import java.util.List;
import org.inbio.modeling.core.common.dao.sys.TaxonIndexDAO;
import org.inbio.modeling.core.common.dao.sys.TaxonInfoIndexDAO;
import org.inbio.modeling.core.common.manager.QueryManager;
import org.inbio.modeling.core.common.model.TaxonIndex;
import org.inbio.modeling.core.common.model.TaxonInfoIndex;
import org.inbio.modeling.core.common.model.TaxonomicalRange;
import org.inbio.modeling.core.common.util.TaxonIndicatorRegionality;

/**
 * @author esmata
 */
public class QueryManagerImpl implements QueryManager{

    //Dependency injections
    private TaxonInfoIndexDAO taxonInfoIndexDAO;
    private TaxonIndexDAO taxonIndexDAO;

    @Override
    public List<TaxonInfoIndex> getallTaxonInfo() {
        return taxonInfoIndexDAO.getallTaxonInfo();
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
     * Count all disctint species from taxonInfoIndex table that match
     * with the specified query criteria
     * @param x represents the first search criteria corresponding to the data
     * from x axis on the chart
     * @param y tha same as x but with the y axis
     * @param xType it could be "geo","indi" or "taxo" see ChartCriteria.java enum
     * @param yType it could be "geo","indi" or "taxo" see ChartCriteria.java enum
     * @return
     */
    @Override
    public Long countByCriteria(String x,String y,int xType,int yType){
        String taxon = "",layer = "",indi = "";
        //Match with correct data type, x and y always gonna be of different type
        switch(xType){
            case 1: //Taxonomical criteria
                taxon = x;
                break;
            case 2: //Geographical criteria
                layer = x;
                break;
            case 3: //Indicators criteria
                indi = x;
                break;
        }
        switch(yType){
            case 1: //Taxonomical criteria
                taxon = y;
                break;
            case 2: //Geographical criteria
                layer = y;
                break;
            case 3: //Indicators criteria
                indi =y;
                break;
        }
        String[] taxonList = {taxon};
        String[] layerList = {layer};
        String[] indiList = {indi};
        //Execute query
        return this.countByCriteria(layerList, taxonList, indiList, "scientific_name_id");
    }

    /**
     * Get a list of TaxonIndicatorRegionality that contains the details for each
     * taxon-indicator regionality relation.
     * Each node of this list has the name of the taxon, the id of the indicator and
     * a list of countries related with those taxon-indicator
     * @param taxonList
     * @param indicList
     * @return
     */
    @Override
    public List<TaxonIndicatorRegionality> getRegionalityList(String[] taxonList,
            String[] indicList){
        List<TaxonIndicatorRegionality> result = new ArrayList<TaxonIndicatorRegionality>();
        //Loop over taxon list
        for(int i = 0;i<taxonList.length;i++){
            if(!taxonList[i].equals("")){
               String taxon = taxonList[i];
                //Loop over indicators
                for(int j = 0;j<indicList.length;j++){
                    if(!indicList[j].equals("")){
                        String indi = indicList[j];
                        //Get a list of scientific names by taxon-indicator
                        String[] layerListAux = {""};
                        String[] taxonListAux = {taxon};
                        String[] indicListAux = {indi};
                        List<String> scientificNames = this.speciesByTaxonIndi(layerListAux,taxonListAux, indicListAux);
                        //Loop over scientific names
                        for(String sn : scientificNames){
                            TaxonIndicatorRegionality node = new TaxonIndicatorRegionality();
                            node.setIndicator(indi);
                            node.setTaxon(sn+"~"+TaxonomicalRange.SCIENTIFICNAME.getId());
                            String sql = this.getCountriesByTaxonIndi(sn, TaxonomicalRange.SCIENTIFICNAME.getId(), indi);
                            node.setCountries(this.taxonIndexDAO.getCountriesByTaxonIndi(sql));
                            result.add(node);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Method to build the necesary sql query to get a list of country names
     * related with a specific taxon-indicator relation
     * @param taxon name of taxon
     * @param indicator id of indicator
     * @return
     */
    private String getCountriesByTaxonIndi(String taxon,int range,String indicator){
        String rangeName = ""; //Name of range
        StringBuilder query = new StringBuilder();
        query.append("Select distinct tic.country_id ");
        query.append("from ait.taxon_indicator_country tic,ait.darwin_core dc where ");
        //Determine the taxonomical level of the taxon
        switch (range) {
            case 1:
                rangeName = TaxonomicalRange.KINGDOM.getDwcFieldName();
                break;
            case 2:
                rangeName = TaxonomicalRange.PHYLUM.getDwcFieldName();
                break;
            case 3:
                rangeName = TaxonomicalRange.CLASS.getDwcFieldName();
                break;
            case 4:
                rangeName = TaxonomicalRange.ORDER.getDwcFieldName();
                break;
            case 5:
                rangeName = TaxonomicalRange.FAMILY.getDwcFieldName();
                break;
            case 6:
                rangeName = TaxonomicalRange.GENUS.getDwcFieldName();
                break;
            case 7:
                rangeName = TaxonomicalRange.SPECIFICEPITHET.getDwcFieldName();
                break;
            default:
                rangeName = TaxonomicalRange.SCIENTIFICNAME.getDwcFieldName();
                break;
        }
        query.append("dc."+rangeName+" = '"+taxon+"' and ");
        query.append("dc.scientificname = tic.taxon_scientific_name and ");
        query.append("tic.indicator_id = "+indicator);
        //System.out.println(query);
        return query.toString();
    }

    /**
     * Counts all registers (indicate in last param) from taxon info index table
     * that match the specified species and indicator
     * @param species
     * @param indicator
     * @param colunm = 'globaluniqueidentifier'
     * @return
     */
    @Override
    public Long countByIndicator(String species,String indicator,String colunm,String polygon){
        String[] taxonList = {species+"~"+TaxonomicalRange.SCIENTIFICNAME.getId()}; //"adding the taxonomical level"
        String[] indicList = {indicator};
        String[] layerList = {polygon};
        //Execute query
        return this.countByCriteria(layerList, taxonList, indicList, colunm);
    }

    /**
     * Counts all registers (indicate in last param) from taxon info index table
     * that match the specified species and polygon
     * @param species
     * @param indicator
     * @param colunm = 'globaluniqueidentifier'
     * @return
     */
    @Override
    public Long countByPolygon(String species,String polygon,String colunm,String indicator){
        String[] taxonList = {species+"~"+TaxonomicalRange.SCIENTIFICNAME.getId()}; //"adding the taxonomical level"
        String[] indicList = {indicator};
        String[] layerList = {polygon};
        //Execute query
        return this.countByCriteria(layerList, taxonList, indicList, colunm);
    }

    /**
     * Counts all registers (indicate in last param) from taxon info index table
     * @param colum indicates the type of search (specimens or species)
     * @return
     */
    @Override
    public Long countByCriteria(String[] layerList, String[] taxonList,
            String[] indicList,String colum) {
        //If taxonList is empty the system is going to include all the kingdoms by default
        if(taxonList[0].equals("")){
            taxonList = this.taxonIndexDAO.getFormatedKingdoms().toArray(taxonList);
        }
        //Query variable
        String query = new String();
        //Getting a list of detailed information for taxon-indicator data
        List<TaxonIndicatorRegionality> regList = this.getRegionalityList(taxonList, indicList);
        //If doesn't exist the taxon-indicator relation (do it as usual)
        if(regList==null || regList.size()==0){
            query = this.countByCriteriaSql(layerList, taxonList, indicList, colum);
        }
        else{ //If there is taxon-indicator relations
            query = this.countByCriteriaRegSql(layerList, colum, regList);
        }
        //Execute query
        return taxonInfoIndexDAO.countTaxonsByQuery(query);
    }

    /**
     * Returns the sql to make a query based on geo-taxo-indi data
     * This method doesn't ignore the taxon-indicator regionality,so, this is gonna be
     * used only if the query have taxon-indicator data
     * Reg means regionality
     */
    @Override
    public String countByCriteriaRegSql(String[] layerList,String colum,
            List<TaxonIndicatorRegionality> regList){
        //Build the query string base on indicated parameters
        StringBuilder query = new StringBuilder();
        query.append("Select count(distinct "+colum+") from ait.taxon_info_index where ");

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

        //Then proccess the list of taxon-indicator-countries relation
        if(regList.size()>0){
            if(layerList.length>0 && !layerList[0].equals("")){
                query.append(" and (");
            }
            else{
                query.append("(");
            }
            for (int i = 0; i < regList.size(); i++) {
                TaxonIndicatorRegionality current = regList.get(i);
                //Get the name and taxonomical level of the specified taxon
                String[] aux = current.getTaxon().split("~");
                String tName = aux[0];
                String tRange = aux[1];
                TaxonIndex ti = taxonIndexDAO.getTaxonIndexByName(tName, tRange);
                if (ti.getTaxon_id() != null) {
                    //To search in the specified taxonomyField
                    String levelColum; //Taxonomical level name
                    Long taxonId = ti.getTaxon_id();
                    String indiId = current.getIndicator();
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
                    //Loop over regionalities to take in count all the posible combinations
                    if (i == regList.size() - 1) { //last element
                        //If doesn't have countries
                        if(current.getCountries().size()==0){
                            query.append("(" + levelColum + " = " + taxonId + " and indicator_id = "+indiId+")");
                        }
                        //If there is asociated countries
                        else{
                            for(int j = 0; j < current.getCountries().size(); j++){
                                if(j==current.getCountries().size()-1){ //last element
                                    Long cAux = current.getCountries().get(j);
                                    query.append("(" + levelColum + " = " + taxonId + " and indicator_id = "+indiId+" and country = "+cAux+")");
                                }
                                else{
                                    Long cAux = current.getCountries().get(j);
                                    query.append("(" + levelColum + " = " + taxonId + " and indicator_id = "+indiId+" and country = "+cAux+") or ");
                                }
                            }                            
                        }
                    }
                    //The rest of elements
                    else {
                        //If doesn't have countries
                        if(current.getCountries().size()==0){
                            query.append("(" + levelColum + " = " + taxonId + " and indicator_id = "+indiId+") or ");
                        }
                        //If there is asociated countries
                        else{
                            for (int j = 0; j < current.getCountries().size(); j++) {
                                Long cAux = current.getCountries().get(j);
                                query.append("(" + levelColum + " = " + taxonId + " and indicator_id = " + indiId + " and country = "+cAux+") or ");
                            }
                        }
                    }
                }
                //If the taxon doesn't exist on data base
                else {
                    String levelColum = TaxonomicalRange.KINGDOM.getFieldName();
                    if (i == regList.size() - 1) { //last element
                        query.append("(" + levelColum + " = " + -1 + ")");
                    } else {
                        query.append("(" + levelColum + " = " + -1 + ") or ");
                    }
                }
            }
            query.append(")");
        }

        //Return the sql
        //System.out.println("countByCriteriaRegSql:\n"+query);
        return query.toString();
    }

    /**
     * Returns the sql to make a query based on geo-taxo-indi data
     * This method ignores the taxon-indicator regionality,so, this is gonna be
     * used only if the query doesn't have taxon-indicator data Ej:
     * - Just geographical criteria
     * - Just taxonomical criteria
     * - geographical + taxonomical criteria
     */
    @Override
    public String countByCriteriaSql(String[] layerList, String[] taxonList,
            String[] indicList,String colum){
        //Build the query string base on parameters
        StringBuilder query = new StringBuilder();
        query.append("Select count(distinct "+colum+") from ait.taxon_info_index where ");

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
                String[] aux = taxonList[i].split("~");
                String tName = aux[0];
                String tRange = aux[1];
                TaxonIndex ti = taxonIndexDAO.getTaxonIndexByName(tName,tRange);
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
        //System.out.println("countByCriteriaSql:\n"+query);
        return query.toString();
    }

    /**
     * Returns the sql to make a query based on geo-taxo-indi data
     * This method doesn't ignore the taxon-indicator regionality,so, this is gonna be
     * used only if the query have taxon-indicator data
     * Reg means regionality
     */
    @Override
    public String elementsByCriteriaRegSql(String[] layerList,String colum,
            List<TaxonIndicatorRegionality> regList){
        //Build the query string base on indicated parameters
        StringBuilder query = new StringBuilder();
        query.append("Select distinct "+colum+" from ait.taxon_info_index where ");

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

        //Then proccess the list of taxon-indicator-countries relation
        if(regList.size()>0){
            if(layerList.length>0 && !layerList[0].equals("")){
                query.append(" and (");
            }
            else{
                query.append("(");
            }
            for (int i = 0; i < regList.size(); i++) {
                TaxonIndicatorRegionality current = regList.get(i);
                //Get the name and taxonomical level of the specified taxon
                String[] aux = current.getTaxon().split("~");
                String tName = aux[0];
                String tRange = aux[1];
                TaxonIndex ti = taxonIndexDAO.getTaxonIndexByName(tName, tRange);
                if (ti.getTaxon_id() != null) {
                    //To search in the specified taxonomyField
                    String levelColum; //Taxonomical level name
                    Long taxonId = ti.getTaxon_id();
                    String indiId = current.getIndicator();
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
                    //Loop over regionalities to take in count all the posible combinations
                    if (i == regList.size() - 1) { //last element
                        //If doesn't have countries
                        if(current.getCountries().size()==0){
                            query.append("(" + levelColum + " = " + taxonId + " and indicator_id = "+indiId+")");
                        }
                        //If there is asociated countries
                        else{
                            for(int j = 0; j < current.getCountries().size(); j++){
                                if(j==current.getCountries().size()-1){ //last element
                                    Long cAux = current.getCountries().get(j);
                                    query.append("(" + levelColum + " = " + taxonId + " and indicator_id = "+indiId+" and country = "+cAux+")");
                                }
                                else{
                                    Long cAux = current.getCountries().get(j);
                                    query.append("(" + levelColum + " = " + taxonId + " and indicator_id = "+indiId+" and country = "+cAux+") or ");
                                }
                            }
                        }
                    }
                    //The rest of elements
                    else {
                        //If doesn't have countries
                        if(current.getCountries().size()==0){
                            query.append("(" + levelColum + " = " + taxonId + " and indicator_id = "+indiId+") or ");
                        }
                        //If there is asociated countries
                        else{
                            for (int j = 0; j < current.getCountries().size(); j++) {
                                Long cAux = current.getCountries().get(j);
                                query.append("(" + levelColum + " = " + taxonId + " and indicator_id = " + indiId + " and country = "+cAux+") or ");
                            }
                        }
                    }
                }
                //If the taxon doesn't exist on data base
                else {
                    String levelColum = TaxonomicalRange.KINGDOM.getFieldName();
                    if (i == regList.size() - 1) { //last element
                        query.append("(" + levelColum + " = " + -1 + ")");
                    } else {
                        query.append("(" + levelColum + " = " + -1 + ") or ");
                    }
                }
            }
            query.append(")");
        }

        //Return the sql
        //System.out.println("elementsByCriteriaRegSql:\n"+query);
        return query.toString();
    }

    /**
     * Returns the sql to make a query based on geo-taxo-indi data
     * This method ignores the taxon-indicator regionality,so, this is gonna be
     * used only if the query doesn't have taxon-indicator data Ej:
     * - Just geographical criteria
     * - Just taxonomical criteria
     * - geographical + taxonomical criteria
     */
    @Override
    public String elementsByCriteriaSql(String[] layerList, String[] taxonList,
            String[] indicList,String colum){
        //Build the query string base on parameters
        StringBuilder query = new StringBuilder();
        query.append("Select distinct "+colum+" from ait.taxon_info_index tii where ");

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
                String[] aux = taxonList[i].split("~");
                String tName = aux[0];
                String tRange = aux[1];
                TaxonIndex ti = taxonIndexDAO.getTaxonIndexByName(tName,tRange);
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
        //System.out.println("elementsByCriteriaSql:\n"+query);
        return query.toString();
    }

    /**
     * To get a list of diferent species from specimen repository
     * Without regionality
     * @param layerList
     * @param taxonList
     * @param indicList
     * @return
     */
    private List<String> speciesByTaxonIndi(String[] layerList, String[] taxonList, String[] indicList) {
        String query = queryByCriteria(layerList,taxonList,indicList);        

        //get a list of scientific name
        List<String> tiiList = getTaxonInfoIndexDAO().
                getScientificNamesAsText(query.toString());

        return tiiList;
    }
    /**
     * Make the query string wihtout regionality
     */
    private String queryByCriteria(String[] layerList, String[] taxonList, String[] indicList) {
        String colum = "(Select taxon_name from ait.taxon_index where taxon_id = tii.scientific_name_id) as scientific_name ";
        //If taxonList is empty the system is going to include all the kingdoms by default
        if(taxonList[0].equals("")){
            taxonList = this.taxonIndexDAO.getFormatedKingdoms().toArray(taxonList);
        }
        //Query variable
        String query = new String();
        query = this.elementsByCriteriaSql(layerList, taxonList, indicList, colum);
        //Return sql query
        return query;
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

}
