/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.modeling.core.dao.impl;

import java.lang.String;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.inbio.modeling.core.dao.TestDAO;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class TestDAOImpl extends BaseDAOImpl implements TestDAO {

	private String holax;

    public TestDAOImpl(){
        super();
    }

    @Override
    public List<String> getTestList() {


        List<String> names = new ArrayList<String>();
        try{
            String query = "Select * from test ;";
            names = getSimpleJdbcTemplate().query(query,
                    new TestRowMapper());
        }catch(Exception e){
			e.printStackTrace();
		}

        return names;
    }

    private static class TestRowMapper implements ParameterizedRowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            String s = new String();
            s = rs.getString("name");
            return s;
        }
    }

	public void setHolax(String h){
		this.holax = h;
	}
	
	public String getHolax(){
		return this.holax;
	}
}
