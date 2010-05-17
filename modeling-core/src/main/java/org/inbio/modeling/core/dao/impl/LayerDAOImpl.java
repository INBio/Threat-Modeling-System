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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.inbio.modeling.core.dao.LayerDAO;
import org.inbio.modeling.core.dto.LayerDTO;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class LayerDAOImpl extends BaseDAOImpl implements LayerDAO {

	private String table = "layers";

	@Override
	public void create(LayerDTO newLayer) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void deleteById(Long id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<String> findAll() {
		return super.findAllByTableName(this.table, new LayerRowMapper());
	}

    private static class LayerRowMapper implements ParameterizedRowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            String s = new String();
            s = rs.getString("name");
            return s;
        }
	}
}