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

package org.inbio.modeling.core.dto;

/**
 *
 * @author asanabria
 */
public class LayerDTO {

	private Long id;
	private String name;
	private String displayName;
	private String uri;
    private String source;
	private String year;
	private String vizScale;
	private String dataScale;
    private String generationProcedure;
    private boolean speciesMap;
	private String description;

	public LayerDTO() { }

	public LayerDTO(String name, long weight ) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public boolean isSpeciesMap() {
        return speciesMap;
    }

    public void setSpeciesMap(boolean speciesMap) {
        this.speciesMap = speciesMap;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDataScale() {
        return dataScale;
    }

    public void setDataScale(String dataScale) {
        this.dataScale = dataScale;
    }

    public String getGenerationProcedure() {
        return generationProcedure;
    }

    public void setGenerationProcedure(String generationProcedure) {
        this.generationProcedure = generationProcedure;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVizScale() {
        return vizScale;
    }

    public void setVizScale(String vizScale) {
        this.vizScale = vizScale;
    }
}