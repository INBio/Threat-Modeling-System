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

package org.inbio.modeling.core.common.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.inbio.modeling.core.common.dao.TaxonInfoIndexDAO;
import org.inbio.modeling.core.common.model.TaxonInfoIndex;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 * Implementing TaxonInfoIndexDAO methods
 * @author esmata
 */
public class TaxonInfoIndexDAOImpl extends SimpleJdbcDaoSupport implements TaxonInfoIndexDAO {

    @Override
    public List<TaxonInfoIndex> getallTaxonInfo() {
        List<TaxonInfoIndex> tInfo = new ArrayList<TaxonInfoIndex>();
        try {
            String query = "Select * from ait.taxon_info_index order by globaluniqueidentifier limit 10 offset 0;";
            tInfo = getSimpleJdbcTemplate().query(query,
                    new tInfoMapper());
        } catch (Exception e) {
            return tInfo;
        }
        return tInfo;
    }

    /**
     * Execute any query that returns a list of TaxonInfoIndex model object
     * @param q
     * @return
     */
    @Override
    public List<TaxonInfoIndex> getTaxonsByQuery(String q) {
        List<TaxonInfoIndex> tInfo = new ArrayList<TaxonInfoIndex>();
        try {
            String query = q;
            tInfo = getSimpleJdbcTemplate().query(query,
                    new tInfoMapper());
        } catch (Exception e) {
            return tInfo;
        }
        return tInfo;
    }

    /**
     * Execute any query that returns a list of TaxonInfoIndex model object
     * just with the globalUniqueIdentifier
     * @param q
     * @return
     */
    @Override
    public List<String> getGlobalUniqueIdentifiers(String q) {
        List<String> tInfo = new ArrayList<String>();
        try {
            String query = q;
            tInfo = getSimpleJdbcTemplate().query(query,
                    new tGUIMapper());
        } catch (Exception e) {
            return tInfo;
        }
        return tInfo;
    }

    /**
     * Execute any query that returns a list of TaxonInfoIndex model object
     * just with the scientific name
     * @param q
     * @return
     */
    @Override
    public List<String> getScientificNames(String q) {
        List<String> tInfo = new ArrayList<String>();
        try {
            String query = q;
            tInfo = getSimpleJdbcTemplate().query(query,
                    new tSNMapper());
        } catch (Exception e) {
            return tInfo;
        }
        return tInfo;
    }

    /**
     * Execute any count query of TaxonInfoIndex model object
     * @param q
     * @return
     */
    @Override
    public Long countTaxonsByQuery(String q) {
        Long result = 0L;
        try {
            result = getSimpleJdbcTemplate().queryForLong(q);
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    /**
     * Indexation for the taxon_info_index table that contains all the indexed
     * information prepared specifically for quering
     * @return if the proccess was successfully completed or not
     */
    @Override
    public boolean taxonInfoIndex(String layer) throws Exception{
        boolean result = true;
        try {
            String sqlUpdate = "Insert into ait.taxon_info_index (globaluniqueidentifier, kingdom_id, phylum_id, " +
                    "class_id, order_id, family_id, genus_id, specific_epithet_id, scientific_name_id, indicator_id, " +
                    "polygom_id, layer_table) select dc.globaluniqueidentifier,k.taxon_id as kingdom_id,p.taxon_id " +
                    "as phylum_id,c.taxon_id as class_id,o.taxon_id as order_id,f.taxon_id as family_id,g.taxon_id " +
                    "as genus_id,s.taxon_id as specific_epithet_id,sn.taxon_id as scientific_name_id,ti.indicator_id " +
                    "as indicator_id,(Select gid from "+layer+" where " +
                    "ST_Contains(the_geom, 'POINT(' || dc.decimallongitude || ' ' ||  dc.decimallatitude || ')')) " +
                    "as polygom_id,'"+layer+"' as layer_table from ait.darwin_core dc,ait.taxon_index k,ait.taxon_index p," +
                    "ait.taxon_index c,ait.taxon_index o,ait.taxon_index f,ait.taxon_index g,ait.taxon_index s,ait.taxon_index sn," +
                    "ait.taxon_indicator ti where k.taxon_name = dc.kingdom and p.taxon_name = dc.phylum and c.taxon_name = " +
                    "dc.class and o.taxon_name = dc.orders and f.taxon_name = dc.family and g.taxon_name = dc.genus and s.taxon_name = " +
                    "dc.specificepithet and sn.taxon_name = dc.scientificname and ti.taxon_scientific_name = dc.scientificname;";
            int affected = getSimpleJdbcTemplate().update(sqlUpdate);
            System.out.println("Affected rows - taxon_info_index -"+affected);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
        return result;
    }

    /**
     * Deletes all info from ait.taxon_info_index table
     * @throws java.lang.Exception
     */
    @Override
    public boolean deleteAllTaxonInfoIndex()  throws Exception{
        try {
            String query = "Delete from ait.taxon_info_index";
            getSimpleJdbcTemplate().update(query);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }
    
    private static class tInfoMapper implements ParameterizedRowMapper<TaxonInfoIndex> {

        @Override
        public TaxonInfoIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
            TaxonInfoIndex ti = new TaxonInfoIndex();
            ti.setGlobaluniqueidentifier(rs.getString("globaluniqueidentifier"));
            ti.setClass_id(rs.getLong("class_id"));
            ti.setFamily_id(rs.getLong("family_id"));
            ti.setGenus_id(rs.getLong("genus_id"));
            ti.setIndicator_id(rs.getLong("indicator_id"));
            ti.setKingdom_id(rs.getLong("kingdom_id"));
            ti.setLayer_id(rs.getLong("layer_id"));
            ti.setOrder_id(rs.getLong("order_id"));
            ti.setPhylum_id(rs.getLong("phylum_id"));
            ti.setPolygom_id(rs.getLong("polygom_id"));
            ti.setScientific_name_id(rs.getLong("scientific_name_id"));
            ti.setSpecific_epithet_id(rs.getLong("specific_epithet_id"));
            return ti;
        }
    }

    private static class tGUIMapper implements ParameterizedRowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            String result = rs.getString("globaluniqueidentifier");
            return result;
        }
    }

    private static class tSNMapper implements ParameterizedRowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long result = rs.getLong("scientific_name_id");
            if(result==null)
                return null;
            else
                return result.toString();
        }
    }

}
