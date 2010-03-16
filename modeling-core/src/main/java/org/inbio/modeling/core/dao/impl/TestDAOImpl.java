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
