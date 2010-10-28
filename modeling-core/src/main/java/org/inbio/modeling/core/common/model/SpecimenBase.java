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

import java.util.Date;

/**
 *
 * @author esmata
 */
public class SpecimenBase {

    /**
     * Constructor
     */
    public SpecimenBase(){}

    /**
     * Atributes
     */
    private String globaluniqueidentifier;
    private Date datelastmodified;
    private String institutioncode;
    private String collectioncode;
    private String catalognumber;
    private String scientificname;
    private String basisofrecord;
    private String kingdom;
    private String phylum;
    private String class1;
    private String orders;
    private String family;
    private String genus;
    private String specificepithet;
    private String decimallongitude;
    private String decimallatitude;

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
     * @return the datelastmodified
     */
    public Date getDatelastmodified() {
        return datelastmodified;
    }

    /**
     * @param datelastmodified the datelastmodified to set
     */
    public void setDatelastmodified(Date datelastmodified) {
        this.datelastmodified = datelastmodified;
    }

    /**
     * @return the institutioncode
     */
    public String getInstitutioncode() {
        return institutioncode;
    }

    /**
     * @param institutioncode the institutioncode to set
     */
    public void setInstitutioncode(String institutioncode) {
        this.institutioncode = institutioncode;
    }

    /**
     * @return the collectioncode
     */
    public String getCollectioncode() {
        return collectioncode;
    }

    /**
     * @param collectioncode the collectioncode to set
     */
    public void setCollectioncode(String collectioncode) {
        this.collectioncode = collectioncode;
    }

    /**
     * @return the catalognumber
     */
    public String getCatalognumber() {
        return catalognumber;
    }

    /**
     * @param catalognumber the catalognumber to set
     */
    public void setCatalognumber(String catalognumber) {
        this.catalognumber = catalognumber;
    }

    /**
     * @return the scientificname
     */
    public String getScientificname() {
        return scientificname;
    }

    /**
     * @param scientificname the scientificname to set
     */
    public void setScientificname(String scientificname) {
        this.scientificname = scientificname;
    }

    /**
     * @return the basisofrecord
     */
    public String getBasisofrecord() {
        return basisofrecord;
    }

    /**
     * @param basisofrecord the basisofrecord to set
     */
    public void setBasisofrecord(String basisofrecord) {
        this.basisofrecord = basisofrecord;
    }

    /**
     * @return the kingdom
     */
    public String getKingdom() {
        return kingdom;
    }

    /**
     * @param kingdom the kingdom to set
     */
    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    /**
     * @return the phylum
     */
    public String getPhylum() {
        return phylum;
    }

    /**
     * @param phylum the phylum to set
     */
    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }

    /**
     * @return the class1
     */
    public String getClass1() {
        return class1;
    }

    /**
     * @param class1 the class1 to set
     */
    public void setClass1(String class1) {
        this.class1 = class1;
    }

    /**
     * @return the orders
     */
    public String getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(String orders) {
        this.orders = orders;
    }

    /**
     * @return the family
     */
    public String getFamily() {
        return family;
    }

    /**
     * @param family the family to set
     */
    public void setFamily(String family) {
        this.family = family;
    }

    /**
     * @return the genus
     */
    public String getGenus() {
        return genus;
    }

    /**
     * @param genus the genus to set
     */
    public void setGenus(String genus) {
        this.genus = genus;
    }

    /**
     * @return the specificepithet
     */
    public String getSpecificepithet() {
        return specificepithet;
    }

    /**
     * @param specificepithet the specificepithet to set
     */
    public void setSpecificepithet(String specificepithet) {
        this.specificepithet = specificepithet;
    }

    /**
     * @return the decimallongitude
     */
    public String getDecimallongitude() {
        return decimallongitude;
    }

    /**
     * @param decimallongitude the decimallongitude to set
     */
    public void setDecimallongitude(String decimallongitude) {
        this.decimallongitude = decimallongitude;
    }

    /**
     * @return the decimallatitude
     */
    public String getDecimallatitude() {
        return decimallatitude;
    }

    /**
     * @param decimallatitude the decimallatitude to set
     */
    public void setDecimallatitude(String decimallatitude) {
        this.decimallatitude = decimallatitude;
    }
}
