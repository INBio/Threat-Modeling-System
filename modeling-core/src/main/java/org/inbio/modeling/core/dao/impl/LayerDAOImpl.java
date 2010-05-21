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
import java.util.Calendar;
import java.util.List;
import org.inbio.modeling.core.dao.LayerDAO;
import org.inbio.modeling.core.model.Layer;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class LayerDAOImpl extends BaseDAOImpl implements LayerDAO {

	private String table = "layers";

	@Override
	public void create(Layer newLayer) {

		String createStatement = null;
		MapSqlParameterSource args = null;

        try{
            createStatement = "INSERT INTO "+this.table+"(" +
            "\"name\", description, uri,  scale, \"year\", last_update)"+
		    "VALUES (:name, :description, :uri, :scale, :year, :last_update);";

			args = new MapSqlParameterSource();
			args.addValue("name", newLayer.getName());
			args.addValue("description", newLayer.getDescription());
			args.addValue("uri", newLayer.getUri());
			args.addValue("scale", newLayer.getScale());
			args.addValue("year", newLayer.getYear());
			args.addValue("last_update", Calendar.getInstance().getTime());

            getSimpleJdbcTemplate().update(createStatement, args);

        }catch(Exception e){
			e.printStackTrace();
		}
    }

	@Override
	public void update(Layer updatedLayer) {
		String createStatement = null;
		MapSqlParameterSource args = null;

        try{
            createStatement = "UPDATE "+this.table+" " +
				" SET name = :name, " +
				" description = :description, " +
				" uri = :uri, " +
				" scale = :scale, " +
				" year = :year, " +
				" last_update = :last_update " +
				" WHERE id = :layer_id" ;

			args = new MapSqlParameterSource();
			args.addValue("name", updatedLayer.getName());
			args.addValue("description", updatedLayer.getDescription());
			args.addValue("uri", updatedLayer.getUri());
			args.addValue("scale", updatedLayer.getScale());
			args.addValue("year", updatedLayer.getYear());
			args.addValue("last_update", Calendar.getInstance().getTime());
			args.addValue("layer_id", updatedLayer.getId());

            getSimpleJdbcTemplate().update(createStatement, args);

        }catch(Exception e){
			e.printStackTrace();
		}

	}



	@Override
	public void deleteById(Long id) {
		String deleteStatement = null;
		MapSqlParameterSource args = null;

        try{
            deleteStatement = "DELETE FROM "+this.table+
				" WHERE id = :identification ";

			args = new MapSqlParameterSource();
			args.addValue("identification", id);

            getSimpleJdbcTemplate().update(deleteStatement, args);

        }catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Layer findById(Long id) {
		String sqlStatement = null;
		MapSqlParameterSource args = null;
		Layer layer = null;

        try{
            sqlStatement = "SELECT * FROM "+this.table+" " +
				" WHERE id = :layer_id" ;

			args = new MapSqlParameterSource();
			args.addValue("layer_id", id);

            layer = getSimpleJdbcTemplate().
						queryForObject(sqlStatement, new LayerRowMapper(), args);

        }catch(Exception e){
			e.printStackTrace();
		}

		return layer;
	}

	@Override
	public List<Layer> findAll() {
		return super.findAllByTableName(this.table, new LayerRowMapper());
	}

    private static class LayerRowMapper implements ParameterizedRowMapper<Layer> {

        @Override
        public Layer mapRow(ResultSet rs, int rowNum) throws SQLException {
           Layer layer = new Layer();
		   layer.setId(rs.getLong("id"));
		   layer.setName(rs.getString("name"));
		   layer.setDescription(rs.getString("description"));
		   layer.setUri(rs.getString("uri"));
		   layer.setScale(rs.getString("scale"));
		   layer.setYear(rs.getString("year"));
           return layer;
        }
	}
}