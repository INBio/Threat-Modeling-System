/* AIT - Analysis of taxonomic indicators
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

package org.inbio.modeling.core.model;

/**
 * @author esmata
 */
public class TaxonInfoIndex {

    //Constructor
    public TaxonInfoIndex(){}

    //Class atributes
    private String globaluniqueidentifier;
    private Long kingdom_id;
    private Long phylum_id;
    private Long class_id;
    private Long order_id;
    private Long family_id;
    private Long genus_id;
    private Long specific_epithet_id;
    private Long scientific_name_id;
    private Long indicator_id;
    private Long layer_id;
    private Long polygom_id;

    /**
     * @return the globaluniqueidentifier
     */
    public String getGlobaluniqueidentifier() {
        return globaluniqueidentifier;
    }

    /**
     * @param globaluniqueidentifier the globaluniqueidentifier to set
     */
    public void setGlobaluniqueidentifier(String globaluniqueidentifier) {
        this.globaluniqueidentifier = globaluniqueidentifier;
    }

    /**
     * @return the kingdom_id
     */
    public Long getKingdom_id() {
        return kingdom_id;
    }

    /**
     * @param kingdom_id the kingdom_id to set
     */
    public void setKingdom_id(Long kingdom_id) {
        this.kingdom_id = kingdom_id;
    }

    /**
     * @return the phylum_id
     */
    public Long getPhylum_id() {
        return phylum_id;
    }

    /**
     * @param phylum_id the phylum_id to set
     */
    public void setPhylum_id(Long phylum_id) {
        this.phylum_id = phylum_id;
    }

    /**
     * @return the class_id
     */
    public Long getClass_id() {
        return class_id;
    }

    /**
     * @param class_id the class_id to set
     */
    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    /**
     * @return the order_id
     */
    public Long getOrder_id() {
        return order_id;
    }

    /**
     * @param order_id the order_id to set
     */
    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    /**
     * @return the family_id
     */
    public Long getFamily_id() {
        return family_id;
    }

    /**
     * @param family_id the family_id to set
     */
    public void setFamily_id(Long family_id) {
        this.family_id = family_id;
    }

    /**
     * @return the genus_id
     */
    public Long getGenus_id() {
        return genus_id;
    }

    /**
     * @param genus_id the genus_id to set
     */
    public void setGenus_id(Long genus_id) {
        this.genus_id = genus_id;
    }

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
     * @return the layer_id
     */
    public Long getLayer_id() {
        return layer_id;
    }

    /**
     * @param layer_id the layer_id to set
     */
    public void setLayer_id(Long layer_id) {
        this.layer_id = layer_id;
    }

    /**
     * @return the polygom_id
     */
    public Long getPolygom_id() {
        return polygom_id;
    }

    /**
     * @param polygom_id the polygom_id to set
     */
    public void setPolygom_id(Long polygom_id) {
        this.polygom_id = polygom_id;
    }

    /**
     * @return the specific_epithet_id
     */
    public Long getSpecific_epithet_id() {
        return specific_epithet_id;
    }

    /**
     * @param specific_epithet_id the specific_epithet_id to set
     */
    public void setSpecific_epithet_id(Long specific_epithet_id) {
        this.specific_epithet_id = specific_epithet_id;
    }

    /**
     * @return the scientific_name_id
     */
    public Long getScientific_name_id() {
        return scientific_name_id;
    }

    /**
     * @param scientific_name_id the scientific_name_id to set
     */
    public void setScientific_name_id(Long scientific_name_id) {
        this.scientific_name_id = scientific_name_id;
    }

}
