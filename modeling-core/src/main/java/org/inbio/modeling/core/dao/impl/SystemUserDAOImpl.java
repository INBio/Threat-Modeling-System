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
package org.inbio.modeling.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.inbio.modeling.core.dao.SystemUserDAO;
import org.inbio.modeling.core.user.SystemUser;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * @author jgutierrez
 *
 */
public class SystemUserDAOImpl extends BaseDAOImpl implements SystemUserDAO {

	/* (non-Javadoc)
	 * @see org.inbio.modeling.core.dao.SystemUserDAO#findByUsername(String)
	 */
	@Override
	public SystemUser findByUsername(final String username) {

		SystemUser systemUser = null;
        try{
            String query = 
				"Select * from users where username = '" + username +"'";

            systemUser = getSimpleJdbcTemplate().queryForObject(query, new SystemUserRowMapper());
        }catch(Exception e){
			e.printStackTrace();
		}

		return systemUser;
	}

	private static class SystemUserRowMapper implements ParameterizedRowMapper<SystemUser> {

		@Override
		public SystemUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			SystemUser su = new SystemUser();
			su.setEnabled(rs.getBoolean("enabled"));
			su.setFullname(rs.getString("fullname"));
			su.setPassword(rs.getString("password"));
			su.setRoles(rs.getString("roles"));
			su.setUserId(rs.getInt("user_id"));
			su.setUsername(rs.getString("username"));

			return su;
		}
	}
}
