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

		createStatement = "INSERT INTO "+this.table+"( display_name, " +
			"\"name\", description, uri, \"year\", last_update, is_species_map, source, visualization_scale, data_scale, generation_procedure)"+
			"VALUES (:display_name, :name, :description, :uri, :year, :last_update, :is_species_map, :source, :viz_scale, :data_scale, :generation_procedure);";

		args = new MapSqlParameterSource();
		args.addValue("name", newLayer.getName());
		args.addValue("display_name", newLayer.getDisplayName());
		args.addValue("uri", newLayer.getUri());
        args.addValue("source", newLayer.getSource());
		args.addValue("year", newLayer.getYear());
		args.addValue("viz_scale", newLayer.getVizScale());
		args.addValue("data_scale", newLayer.getDataScale());
        args.addValue("generation_procedure", newLayer.getGenerationProcedure());
		args.addValue("last_update", Calendar.getInstance().getTime());
		args.addValue("description", newLayer.getDescription());
        args.addValue("is_species_map", newLayer.isSpeciesMap());

		getSimpleJdbcTemplate().update(createStatement, args);
	}

	@Override
	public void update(Layer updatedLayer) {
		String createStatement = null;
		MapSqlParameterSource args = null;

		createStatement = "UPDATE "+this.table+" " +
			" SET name = :name, " +
            " display_name = :display_name, " +
			" description = :description, " +
			" uri = :uri, " +
            " source = :source, " +
			" year = :year, " +
            " data_scale = :data_scale, "+
            " visualization_scale = :viz_scale, " +
			" last_update = :last_update, " +
            " generation_procedure = :generation_procedure, " +
			" is_species_map= :is_species_map " +
			" WHERE id = :layer_id" ;

		args = new MapSqlParameterSource();
		args.addValue("name", updatedLayer.getName());
		args.addValue("display_name", updatedLayer.getDisplayName());
		args.addValue("uri", updatedLayer.getUri());
        args.addValue("source", updatedLayer.getSource());
		args.addValue("year", updatedLayer.getYear());
		args.addValue("viz_scale", updatedLayer.getVizScale());
		args.addValue("data_scale", updatedLayer.getDataScale());
        args.addValue("generation_procedure", updatedLayer.getGenerationProcedure());
		args.addValue("last_update", Calendar.getInstance().getTime());
		args.addValue("description", updatedLayer.getDescription());
        args.addValue("is_species_map", updatedLayer.isSpeciesMap());
        args.addValue("layer_id", updatedLayer.getId());

		getSimpleJdbcTemplate().update(createStatement, args);

	}



	@Override
	public void deleteById(Long id) {
		String deleteStatement = null;
		MapSqlParameterSource args = null;

		deleteStatement = "DELETE FROM "+this.table+
			" WHERE id = :identification ";

		args = new MapSqlParameterSource();
		args.addValue("identification", id);

		getSimpleJdbcTemplate().update(deleteStatement, args);

	}

	@Override
	public Layer findById(Long id) {
		String sqlStatement = null;
		MapSqlParameterSource args = null;
		Layer layer = null;

		sqlStatement = "SELECT * FROM "+this.table+" " +
			" WHERE id = :layer_id" ;

		args = new MapSqlParameterSource();
		args.addValue("layer_id", id);

		layer = getSimpleJdbcTemplate().
			queryForObject(sqlStatement, new LayerRowMapper(), args);


		return layer;
	}

	@Override
	public Layer findByName(String name) {
		String sqlStatement = null;
		MapSqlParameterSource args = null;
		Layer layer = null;

		sqlStatement = "SELECT * FROM "+this.table+" " +
			" WHERE name = :name limit 1 ";

		args = new MapSqlParameterSource();
		args.addValue("name", name);

		layer = getSimpleJdbcTemplate().
			queryForObject(sqlStatement, new LayerRowMapper(), args);


		return layer;
	}

	@Override
	public List<Layer> findAllSpeciesLayers() {
		String sqlStatement = null;
		MapSqlParameterSource args = null;
		List<Layer> layers = null;

		sqlStatement = "SELECT * FROM "+this.table+" " +
			" WHERE is_species_map = :isSpecies" ;

		args = new MapSqlParameterSource();
		args.addValue("isSpecies", true);

		layers = getSimpleJdbcTemplate().query(sqlStatement, new LayerRowMapper(), args);

		return layers;
	}

	@Override
	public List<Layer> findAll() {

        String sqlStatement = null;
        MapSqlParameterSource args = null;
        List<Layer> layers = null;

        sqlStatement = "SELECT * FROM "+this.table+" " +"order by display_name ";

        layers = getSimpleJdbcTemplate().query(sqlStatement, new LayerRowMapper());

        return layers;

	}

	private static class LayerRowMapper implements ParameterizedRowMapper<Layer> {

		@Override
		public Layer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Layer layer = new Layer();
			layer.setId(rs.getLong("id"));
			layer.setName(rs.getString("name"));
            layer.setDisplayName(rs.getString("display_name"));
			layer.setUri(rs.getString("uri"));
            layer.setSource(rs.getString("source"));
			layer.setYear(rs.getString("year"));
            layer.setVizScale(rs.getString("visualization_scale"));
            layer.setDataScale(rs.getString("data_scale"));
            layer.setGenerationProcedure(rs.getString("generation_procedure"));
            layer.setSpeciesMap(rs.getBoolean("is_species_map"));
			layer.setDescription(rs.getString("description"));

			return layer;

		}
	}
}