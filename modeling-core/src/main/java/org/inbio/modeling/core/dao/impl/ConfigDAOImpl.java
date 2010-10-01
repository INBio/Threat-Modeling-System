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

import org.inbio.modeling.core.dao.ConfigDAO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class ConfigDAOImpl extends BaseDAOImpl implements ConfigDAO {

	private String table = "config";

    @Override
    public String findParameter(String parameterName) {
		String sqlStatement = null;
        String parameterValue = null;
		MapSqlParameterSource args = null;

		sqlStatement = "SELECT value FROM "+this.table+" " +
			" WHERE name = :parameter_name" ;

		args = new MapSqlParameterSource();
		args.addValue("parameter_name", parameterName);

		parameterValue = getSimpleJdbcTemplate().
			queryForObject(sqlStatement, String.class, args);


		return parameterValue;
    }

    @Override
    public void updateParameter(String parameterName, String value) {
		String createStatement = null;
		MapSqlParameterSource args = null;

		createStatement = "UPDATE "+this.table+" " +
			" SET value = :value " +
			" WHERE name = :parameter_name" ;

		args = new MapSqlParameterSource();
		args.addValue("parameter_name", parameterName);
		args.addValue("value", value);

		getSimpleJdbcTemplate().update(createStatement, args);
    }
}