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

package org.inbio.modeling.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.inbio.modeling.core.dao.TaxonIndexDAO;
import org.inbio.modeling.core.model.TaxonIndex;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 * Implementing TaxonIndexDAO methods
 * @author esmata
 */
public class TaxonIndexDAOImpl extends SimpleJdbcDaoSupport implements TaxonIndexDAO{


    /**
     * Get id,range and name based on a specific taxon name
     */
    @Override
    public TaxonIndex getTaxonIndexByName(String name) {
        TaxonIndex tIndex = new TaxonIndex();
        try {
            String query = "Select * from ait.taxon_index where taxon_name = '"+ name +"';";
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

}
