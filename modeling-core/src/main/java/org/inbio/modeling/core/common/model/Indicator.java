/* Modeling - Application to model threats
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

package org.inbio.modeling.core.common.model;

/**
 *
 * @author esmata
 */
public class Indicator {

    /**
     * Constructor
     */
    public Indicator(){}

    /**
     * Atributes
     */
    private Long indicator_id;
    private String indicator_name;
    private String indicator_description;
    private Long indicator_applies_to_part;
    private Long indicator_ancestor_id;
    private String indicator_references;

    /**
     * @return the indicator_id
     */
    public Long getIndicator_id() {
        return indicator_id;
    }

    /**
     * @param indicator_id the indicator_id to set
     */
    public void setIndicator_id(Long indicator_id) {
        this.indicator_id = indicator_id;
    }

    /**
     * @return the indicator_name
     */
    public String getIndicator_name() {
        return indicator_name;
    }

    /**
     * @param indicator_name the indicator_name to set
     */
    public void setIndicator_name(String indicator_name) {
        this.indicator_name = indicator_name;
    }

    /**
     * @return the indicator_description
     */
    public String getIndicator_description() {
        return indicator_description;
    }

    /**
     * @param indicator_description the indicator_description to set
     */
    public void setIndicator_description(String indicator_description) {
        this.indicator_description = indicator_description;
    }

    /**
     * @return the indicator_applies_to_part
     */
    public Long getIndicator_applies_to_part() {
        return indicator_applies_to_part;
    }

    /**
     * @param indicator_applies_to_part the indicator_applies_to_part to set
     */
    public void setIndicator_applies_to_part(Long indicator_applies_to_part) {
        this.indicator_applies_to_part = indicator_applies_to_part;
    }

    /**
     * @return the indicator_ancestor_id
     */
    public Long getIndicator_ancestor_id() {
        return indicator_ancestor_id;
    }

    /**
     * @param indicator_ancestor_id the indicator_ancestor_id to set
     */
    public void setIndicator_ancestor_id(Long indicator_ancestor_id) {
        this.indicator_ancestor_id = indicator_ancestor_id;
    }

    /**
     * @return the indicator_references
     */
    public String getIndicator_references() {
        return indicator_references;
    }

    /**
     * @param indicator_references the indicator_references to set
     */
    public void setIndicator_references(String indicator_references) {
        this.indicator_references = indicator_references;
    }

}
