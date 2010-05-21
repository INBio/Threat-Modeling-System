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
import java.util.List;
import org.inbio.modeling.core.dao.SystemUserDAO;
import org.inbio.modeling.core.user.SystemUser;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * @author jgutierrez
 *
 */
public class SystemUserDAOImpl extends BaseDAOImpl implements SystemUserDAO {

	private final String table  = "users";

	/* (non-Javadoc)
	 * @see org.inbio.modeling.core.dao.SystemUserDAO#findByUsername(String)
	 */
	@Override
	public SystemUser findByUsername(final String username) {

		String sqlStatement = null;
		MapSqlParameterSource args = null;
		SystemUser systemUser = null;

        try{
            sqlStatement =
				"Select * from "+this.table+" where username = :username";

			args = new MapSqlParameterSource();
			args.addValue("username", username);

            systemUser = 
				getSimpleJdbcTemplate()
					.queryForObject(sqlStatement, new SystemUserRowMapper(), args);

        }catch(Exception e){
			e.printStackTrace();
		}

		return systemUser;
	}

	@Override
	public void createUser(SystemUser newUser) {
		String createStatement = null;
		MapSqlParameterSource args = null;

        try{
            createStatement = "INSERT INTO "+this.table+
				" ( fullname, username, \"password\", enabled, roles)" +
		    "VALUES (:fullname, :username, :passwd, :enabled, :roles) ";

			args = new MapSqlParameterSource();
			args.addValue("fullname", newUser.getFullname());
			args.addValue("username", newUser.getUsername());
			args.addValue("passwd", newUser.getPassword());
			args.addValue("enabled", newUser.isEnabled());
			args.addValue("roles", newUser.getRoles());

            getSimpleJdbcTemplate().update(createStatement, args);

        }catch(Exception e){
			e.printStackTrace();
		}
    }

	@Override
	public void deleteUserByUsername(String userName) {
		String sqlStatement = null;
		MapSqlParameterSource args = null;

        try{
            sqlStatement = "DELETE FROM "+this.table+" WHERE username = :username ";

			args = new MapSqlParameterSource();
			args.addValue("username", userName);

            getSimpleJdbcTemplate().update(sqlStatement, args);

        }catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(SystemUser user) {
		String sqlStatement = null;
		MapSqlParameterSource args = null;

        try{
            sqlStatement = "UPDATE "+this.table+
				"SET fullname = :fullname "+
				"SET password = :passwd " +
				"SET enabled = :enabled " +
				"SET roles = :roles "+
				" WHERE username = :username ";


			args = new MapSqlParameterSource();
			args.addValue("username", user.getUsername());
			args.addValue("fullname", user.getFullname());
			args.addValue("passwd", user.getPassword());
			args.addValue("enabled", user.isEnabled());
			args.addValue("roles", user.getRoles());

            getSimpleJdbcTemplate().update(sqlStatement, args);

        }catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<SystemUser> findAll() {

		List<SystemUser> systemUsers = null;

        try{
			systemUsers = this.findAllByTableName(this.table, new SystemUserRowMapper());
        }catch(Exception e){
			e.printStackTrace();
		}



		return systemUsers;
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
