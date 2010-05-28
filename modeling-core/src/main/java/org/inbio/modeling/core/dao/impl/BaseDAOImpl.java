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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.modeling.core.dao.BaseDAO;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class BaseDAOImpl extends SimpleJdbcDaoSupport implements BaseDAO{

	protected final Log logger = LogFactory.getLog(this.getClass());

    public BaseDAOImpl(){
        super();
    }

	@Override
	public <T> List<T> findAllByTableName(String tableName, ParameterizedRowMapper<T> mapper) {

        List<T> names = new ArrayList<T>();
            String query = "Select * from "+tableName+" ;";
            names = getSimpleJdbcTemplate().query(query, mapper);

        return names;
    }

}

