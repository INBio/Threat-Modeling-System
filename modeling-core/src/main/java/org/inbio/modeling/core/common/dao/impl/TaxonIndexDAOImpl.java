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

package org.inbio.modeling.core.common.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.inbio.modeling.core.common.dao.sys.TaxonIndexDAO;
import org.inbio.modeling.core.common.model.AutocompleteNode;
import org.inbio.modeling.core.common.model.TaxonIndex;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 * Implementing TaxonIndexDAO methods
 * @author esmata
 */
public class TaxonIndexDAOImpl extends SimpleJdbcDaoSupport implements TaxonIndexDAO{


    /**
     * Gets the list of countries linked with a specific taxon-indicator
     * @param sql
     * @return
     */
    @Override
    public List<Long> getCountriesByTaxonIndi(String sql){
        List<Long> result = new ArrayList<Long>();
        try {
            result = getSimpleJdbcTemplate().query(sql,new countriesMapper());
        } catch (Exception e) {
            //System.out.println(e);
            return result;
        }
        return result;
    }

    /**
     * Get id,range and name based on a specific taxon name and range
     */
    @Override
    public TaxonIndex getTaxonIndexByName(String name,String range) {
        TaxonIndex tIndex = new TaxonIndex();
        try {
            String query = "Select * from ait.taxon_index where taxon_name = '"+
                    name +"' and taxon_range = "+range+";";
            tIndex = getSimpleJdbcTemplate().queryForObject(query,new tIndexMapper());
        } catch (Exception e) {
            return tIndex;
        }
        return tIndex;
    }

    /**
     * Get id,range and name based on a specific taxon id
     */
    @Override
    public TaxonIndex getTaxonIndexById(String id) {
        TaxonIndex tIndex = new TaxonIndex();
        try {
            String query = "Select * from ait.taxon_index where taxon_id = '"+ id +"';";
            tIndex = getSimpleJdbcTemplate().queryForObject(query,new tIndexMapper());
        } catch (Exception e) {
            return tIndex;
        }
        return tIndex;
    }

    /**
     * Indexation for the taxon_index table that contains all the names from
     * the taxonomical hierarchy
     * @param rangeId 1 = reino, 2 = filo ...
     * @param rangeName reino, filo ...
     * @return number of affected rows
     */
    @Override
    public boolean taxonIndexByRange(int rangeId,String rangeName) throws Exception{
        boolean result = true;
        try {
            String sqlUpdate = "Insert into ait.taxon_index (taxon_name,taxon_range) " +
                    "select "+rangeName+" as taxon_name , "+rangeId+" as taxon_range " +
                    "from ait.darwin_core where "+rangeName+" is not null " +
                    "group by taxon_name;";
            int affected = getSimpleJdbcTemplate().update(sqlUpdate);
            System.out.println("Affected rows - taxon_index - "+rangeName+": "+affected);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * Delete all taxon_index registers
     * @throws java.lang.Exception
     */
    @Override
    public boolean deleteAllTaxonIndex()  throws Exception{
        try {
            String query = "Delete from ait.taxon_index";
            getSimpleJdbcTemplate().update(query);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    /**
     * Returns a list of kingdom with the format: kingdom~1 where kingdom
     * represents the name and ~1 the range
     */
    @Override
    public List<String> getFormatedKingdoms(){
        List<String> result = new ArrayList<String>();
        try {
            String query = "Select taxon_name from ait.taxon_index where taxon_range = 1";
            result = getSimpleJdbcTemplate().query(query,new kingdomsMapper());
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    /**
     * Return all disctint elements for classes,phylums,kingdoms ...
     * @param partialName
     * @param range
     * @return
     */
    @Override
    public List<AutocompleteNode> getElementsByRange(String partialName, int range) {
        List<AutocompleteNode> nodes = new ArrayList<AutocompleteNode>();
        try {
            String query = "Select DISTINCT taxon_name from ait.taxon_index " +
                    "where taxon_name like '%" + partialName + "%' and taxon_range = "+range+" limit 3;";

            nodes = getSimpleJdbcTemplate().query(query,
                    new AutocompleteMapper(range));
        } catch (Exception e) {
            return nodes;
        }
        return nodes;
    }

    private static class AutocompleteMapper implements ParameterizedRowMapper<AutocompleteNode> {

        //To determine witch range is been used
        private int range;

        //Constructor
        public AutocompleteMapper(int r){
            this.range = r;
        }

        public AutocompleteNode mapRow(ResultSet rs, int rowNum) throws SQLException {

            AutocompleteNode acn = new AutocompleteNode();
            acn.setItemName(rs.getString("taxon_name"));
            acn.setItemId(rs.getString("taxon_name"));
            return acn;
        }
    }

    private static class tIndexMapper implements ParameterizedRowMapper<TaxonIndex> {

        @Override
        public TaxonIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
            TaxonIndex ti = new TaxonIndex();
            ti.setTaxon_id(rs.getLong("taxon_id"));
            ti.setTaxon_name(rs.getString("taxon_name"));
            ti.setTaxon_range(rs.getLong("taxon_range"));
            return ti;
        }
    }

    private static class countriesMapper implements ParameterizedRowMapper<Long> {
        @Override
        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getLong("country_id");
        }
    }

    private static class kingdomsMapper implements ParameterizedRowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            String r = new String();
            r = rs.getString("taxon_name")+"~1";
            return r;
        }
    }

}
