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

package org.inbio.modeling.core.common.model;

import java.util.Date;

/**
 * @author esmata
 */
public class Specimen extends SpecimenBase{

    /**
     * Constructor
     */
    public Specimen(){}

    /**
     * Atributes
     */
    private Long catalognumbernumeric;
    private String informationwithheld;
    private Long kingdomid;
    private Long phylumId;
    private Long classId;
    private Long ordersId;
    private Long familyId;
    private Long genusId;
    private Long specificepithetId;
    private Long infraspecificepithetId;
    private String highertaxon;
    private String infraspecificepithet;
    private String infraspecificrank;
    private String authoryearofscientificname;
    private String nomenclaturalcode;
    private String identificationqualifier;
    private String identifiedby;
    private Date dateidentified;
    private String typestatus;
    private String collectingmethod;
    private String validdistributionflag;
    private String collectornumber;
    private String fieldnumber;
    private String collector;
    private Date earliestdatecollected;
    private Date latestdatecollected;
    private String verbatimcollectingdate;
    private Long dayofyear;
    private String fieldnotes;
    private String highergeography;
    private String continent;
    private String waterbody;
    private String islandgroup;
    private String island;
    private String country;
    private String stateprovince;
    private String county;
    private String locality;
    private String verbatimlongitude;
    private String verbatimlatitude;
    private String geodeticdatum;
    private String verbatimcoordinatesystem;
    private String georeferenceprotocol;
    private String coordinateuncertaintyinmeters;
    private String georeferenceremarks;
    private String footprintwkt;
    private Double minimumelevationinmeters;
    private Double maximumelevationinmeters;
    private Double verbatimelevation;
    private Double minimumdepthinmeters;
    private Double maximumdepthinmeters;
    private String sex;
    private String lifestage;
    private String preparations;
    private Long individualcount;
    private String genbanknum;
    private String othercatalognumbers;
    private String relatedcatalogitems;
    private String remarks;
    private String attributes;
    private String imageurl;
    private String relatedinformation;
    private String disposition;
    private Long pointradiusspatialfit;
    private Long footprintspatialfit;
    private String verbatimcoordinates;
    private String georeferencesources;
    private String georeferenceverificationstatus;

    /**
     * @return the catalognumbernumeric
     */
    public Long getCatalognumbernumeric() {
        return catalognumbernumeric;
    }

    /**
     * @param catalognumbernumeric the catalognumbernumeric to set
     */
    public void setCatalognumbernumeric(Long catalognumbernumeric) {
        this.catalognumbernumeric = catalognumbernumeric;
    }

    /**
     * @return the informationwithheld
     */
    public String getInformationwithheld() {
        return informationwithheld;
    }

    /**
     * @param informationwithheld the informationwithheld to set
     */
    public void setInformationwithheld(String informationwithheld) {
        this.informationwithheld = informationwithheld;
    }

    /**
     * @return the kingdomid
     */
    public Long getKingdomid() {
        return kingdomid;
    }

    /**
     * @param kingdomid the kingdomid to set
     */
    public void setKingdomid(Long kingdomid) {
        this.kingdomid = kingdomid;
    }

    /**
     * @return the phylumId
     */
    public Long getPhylumId() {
        return phylumId;
    }

    /**
     * @param phylumId the phylumId to set
     */
    public void setPhylumId(Long phylumId) {
        this.phylumId = phylumId;
    }

    /**
     * @return the classId
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * @param classId the classId to set
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * @return the ordersId
     */
    public Long getOrdersId() {
        return ordersId;
    }

    /**
     * @param ordersId the ordersId to set
     */
    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    /**
     * @return the familyId
     */
    public Long getFamilyId() {
        return familyId;
    }

    /**
     * @param familyId the familyId to set
     */
    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    /**
     * @return the genusId
     */
    public Long getGenusId() {
        return genusId;
    }

    /**
     * @param genusId the genusId to set
     */
    public void setGenusId(Long genusId) {
        this.genusId = genusId;
    }

    /**
     * @return the specificepithetId
     */
    public Long getSpecificepithetId() {
        return specificepithetId;
    }

    /**
     * @param specificepithetId the specificepithetId to set
     */
    public void setSpecificepithetId(Long specificepithetId) {
        this.specificepithetId = specificepithetId;
    }

    /**
     * @return the infraspecificepithetId
     */
    public Long getInfraspecificepithetId() {
        return infraspecificepithetId;
    }

    /**
     * @param infraspecificepithetId the infraspecificepithetId to set
     */
    public void setInfraspecificepithetId(Long infraspecificepithetId) {
        this.infraspecificepithetId = infraspecificepithetId;
    }

    /**
     * @return the highertaxon
     */
    public String getHighertaxon() {
        return highertaxon;
    }

    /**
     * @param highertaxon the highertaxon to set
     */
    public void setHighertaxon(String highertaxon) {
        this.highertaxon = highertaxon;
    }

    /**
     * @return the infraspecificepithet
     */
    public String getInfraspecificepithet() {
        return infraspecificepithet;
    }

    /**
     * @param infraspecificepithet the infraspecificepithet to set
     */
    public void setInfraspecificepithet(String infraspecificepithet) {
        this.infraspecificepithet = infraspecificepithet;
    }

    /**
     * @return the infraspecificrank
     */
    public String getInfraspecificrank() {
        return infraspecificrank;
    }

    /**
     * @param infraspecificrank the infraspecificrank to set
     */
    public void setInfraspecificrank(String infraspecificrank) {
        this.infraspecificrank = infraspecificrank;
    }

    /**
     * @return the authoryearofscientificname
     */
    public String getAuthoryearofscientificname() {
        return authoryearofscientificname;
    }

    /**
     * @param authoryearofscientificname the authoryearofscientificname to set
     */
    public void setAuthoryearofscientificname(String authoryearofscientificname) {
        this.authoryearofscientificname = authoryearofscientificname;
    }

    /**
     * @return the nomenclaturalcode
     */
    public String getNomenclaturalcode() {
        return nomenclaturalcode;
    }

    /**
     * @param nomenclaturalcode the nomenclaturalcode to set
     */
    public void setNomenclaturalcode(String nomenclaturalcode) {
        this.nomenclaturalcode = nomenclaturalcode;
    }

    /**
     * @return the identificationqualifier
     */
    public String getIdentificationqualifier() {
        return identificationqualifier;
    }

    /**
     * @param identificationqualifier the identificationqualifier to set
     */
    public void setIdentificationqualifier(String identificationqualifier) {
        this.identificationqualifier = identificationqualifier;
    }

    /**
     * @return the identifiedby
     */
    public String getIdentifiedby() {
        return identifiedby;
    }

    /**
     * @param identifiedby the identifiedby to set
     */
    public void setIdentifiedby(String identifiedby) {
        this.identifiedby = identifiedby;
    }

    /**
     * @return the dateidentified
     */
    public Date getDateidentified() {
        return dateidentified;
    }

    /**
     * @param dateidentified the dateidentified to set
     */
    public void setDateidentified(Date dateidentified) {
        this.dateidentified = dateidentified;
    }

    /**
     * @return the typestatus
     */
    public String getTypestatus() {
        return typestatus;
    }

    /**
     * @param typestatus the typestatus to set
     */
    public void setTypestatus(String typestatus) {
        this.typestatus = typestatus;
    }

    /**
     * @return the collectingmethod
     */
    public String getCollectingmethod() {
        return collectingmethod;
    }

    /**
     * @param collectingmethod the collectingmethod to set
     */
    public void setCollectingmethod(String collectingmethod) {
        this.collectingmethod = collectingmethod;
    }

    /**
     * @return the validdistributionflag
     */
    public String getValiddistributionflag() {
        return validdistributionflag;
    }

    /**
     * @param validdistributionflag the validdistributionflag to set
     */
    public void setValiddistributionflag(String validdistributionflag) {
        this.validdistributionflag = validdistributionflag;
    }

    /**
     * @return the collectornumber
     */
    public String getCollectornumber() {
        return collectornumber;
    }

    /**
     * @param collectornumber the collectornumber to set
     */
    public void setCollectornumber(String collectornumber) {
        this.collectornumber = collectornumber;
    }

    /**
     * @return the fieldnumber
     */
    public String getFieldnumber() {
        return fieldnumber;
    }

    /**
     * @param fieldnumber the fieldnumber to set
     */
    public void setFieldnumber(String fieldnumber) {
        this.fieldnumber = fieldnumber;
    }

    /**
     * @return the collector
     */
    public String getCollector() {
        return collector;
    }

    /**
     * @param collector the collector to set
     */
    public void setCollector(String collector) {
        this.collector = collector;
    }

    /**
     * @return the earliestdatecollected
     */
    public Date getEarliestdatecollected() {
        return earliestdatecollected;
    }

    /**
     * @param earliestdatecollected the earliestdatecollected to set
     */
    public void setEarliestdatecollected(Date earliestdatecollected) {
        this.earliestdatecollected = earliestdatecollected;
    }

    /**
     * @return the latestdatecollected
     */
    public Date getLatestdatecollected() {
        return latestdatecollected;
    }

    /**
     * @param latestdatecollected the latestdatecollected to set
     */
    public void setLatestdatecollected(Date latestdatecollected) {
        this.latestdatecollected = latestdatecollected;
    }

    /**
     * @return the verbatimcollectingdate
     */
    public String getVerbatimcollectingdate() {
        return verbatimcollectingdate;
    }

    /**
     * @param verbatimcollectingdate the verbatimcollectingdate to set
     */
    public void setVerbatimcollectingdate(String verbatimcollectingdate) {
        this.verbatimcollectingdate = verbatimcollectingdate;
    }

    /**
     * @return the dayofyear
     */
    public Long getDayofyear() {
        return dayofyear;
    }

    /**
     * @param dayofyear the dayofyear to set
     */
    public void setDayofyear(Long dayofyear) {
        this.dayofyear = dayofyear;
    }

    /**
     * @return the fieldnotes
     */
    public String getFieldnotes() {
        return fieldnotes;
    }

    /**
     * @param fieldnotes the fieldnotes to set
     */
    public void setFieldnotes(String fieldnotes) {
        this.fieldnotes = fieldnotes;
    }

    /**
     * @return the highergeography
     */
    public String getHighergeography() {
        return highergeography;
    }

    /**
     * @param highergeography the highergeography to set
     */
    public void setHighergeography(String highergeography) {
        this.highergeography = highergeography;
    }

    /**
     * @return the continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * @param continent the continent to set
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * @return the waterbody
     */
    public String getWaterbody() {
        return waterbody;
    }

    /**
     * @param waterbody the waterbody to set
     */
    public void setWaterbody(String waterbody) {
        this.waterbody = waterbody;
    }

    /**
     * @return the islandgroup
     */
    public String getIslandgroup() {
        return islandgroup;
    }

    /**
     * @param islandgroup the islandgroup to set
     */
    public void setIslandgroup(String islandgroup) {
        this.islandgroup = islandgroup;
    }

    /**
     * @return the island
     */
    public String getIsland() {
        return island;
    }

    /**
     * @param island the island to set
     */
    public void setIsland(String island) {
        this.island = island;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the stateprovince
     */
    public String getStateprovince() {
        return stateprovince;
    }

    /**
     * @param stateprovince the stateprovince to set
     */
    public void setStateprovince(String stateprovince) {
        this.stateprovince = stateprovince;
    }

    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * @return the locality
     */
    public String getLocality() {
        return locality;
    }

    /**
     * @param locality the locality to set
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }

    /**
     * @return the verbatimlongitude
     */
    public String getVerbatimlongitude() {
        return verbatimlongitude;
    }

    /**
     * @param verbatimlongitude the verbatimlongitude to set
     */
    public void setVerbatimlongitude(String verbatimlongitude) {
        this.verbatimlongitude = verbatimlongitude;
    }

    /**
     * @return the verbatimlatitude
     */
    public String getVerbatimlatitude() {
        return verbatimlatitude;
    }

    /**
     * @param verbatimlatitude the verbatimlatitude to set
     */
    public void setVerbatimlatitude(String verbatimlatitude) {
        this.verbatimlatitude = verbatimlatitude;
    }

    /**
     * @return the geodeticdatum
     */
    public String getGeodeticdatum() {
        return geodeticdatum;
    }

    /**
     * @param geodeticdatum the geodeticdatum to set
     */
    public void setGeodeticdatum(String geodeticdatum) {
        this.geodeticdatum = geodeticdatum;
    }

    /**
     * @return the verbatimcoordinatesystem
     */
    public String getVerbatimcoordinatesystem() {
        return verbatimcoordinatesystem;
    }

    /**
     * @param verbatimcoordinatesystem the verbatimcoordinatesystem to set
     */
    public void setVerbatimcoordinatesystem(String verbatimcoordinatesystem) {
        this.verbatimcoordinatesystem = verbatimcoordinatesystem;
    }

    /**
     * @return the georeferenceprotocol
     */
    public String getGeoreferenceprotocol() {
        return georeferenceprotocol;
    }

    /**
     * @param georeferenceprotocol the georeferenceprotocol to set
     */
    public void setGeoreferenceprotocol(String georeferenceprotocol) {
        this.georeferenceprotocol = georeferenceprotocol;
    }

    /**
     * @return the coordinateuncertaintyinmeters
     */
    public String getCoordinateuncertaintyinmeters() {
        return coordinateuncertaintyinmeters;
    }

    /**
     * @param coordinateuncertaintyinmeters the coordinateuncertaintyinmeters to set
     */
    public void setCoordinateuncertaintyinmeters(String coordinateuncertaintyinmeters) {
        this.coordinateuncertaintyinmeters = coordinateuncertaintyinmeters;
    }

    /**
     * @return the georeferenceremarks
     */
    public String getGeoreferenceremarks() {
        return georeferenceremarks;
    }

    /**
     * @param georeferenceremarks the georeferenceremarks to set
     */
    public void setGeoreferenceremarks(String georeferenceremarks) {
        this.georeferenceremarks = georeferenceremarks;
    }

    /**
     * @return the footprintwkt
     */
    public String getFootprintwkt() {
        return footprintwkt;
    }

    /**
     * @param footprintwkt the footprintwkt to set
     */
    public void setFootprintwkt(String footprintwkt) {
        this.footprintwkt = footprintwkt;
    }

    /**
     * @return the minimumelevationinmeters
     */
    public Double getMinimumelevationinmeters() {
        return minimumelevationinmeters;
    }

    /**
     * @param minimumelevationinmeters the minimumelevationinmeters to set
     */
    public void setMinimumelevationinmeters(Double minimumelevationinmeters) {
        this.minimumelevationinmeters = minimumelevationinmeters;
    }

    /**
     * @return the maximumelevationinmeters
     */
    public Double getMaximumelevationinmeters() {
        return maximumelevationinmeters;
    }

    /**
     * @param maximumelevationinmeters the maximumelevationinmeters to set
     */
    public void setMaximumelevationinmeters(Double maximumelevationinmeters) {
        this.maximumelevationinmeters = maximumelevationinmeters;
    }

    /**
     * @return the verbatimelevation
     */
    public Double getVerbatimelevation() {
        return verbatimelevation;
    }

    /**
     * @param verbatimelevation the verbatimelevation to set
     */
    public void setVerbatimelevation(Double verbatimelevation) {
        this.verbatimelevation = verbatimelevation;
    }

    /**
     * @return the minimumdepthinmeters
     */
    public Double getMinimumdepthinmeters() {
        return minimumdepthinmeters;
    }

    /**
     * @param minimumdepthinmeters the minimumdepthinmeters to set
     */
    public void setMinimumdepthinmeters(Double minimumdepthinmeters) {
        this.minimumdepthinmeters = minimumdepthinmeters;
    }

    /**
     * @return the maximumdepthinmeters
     */
    public Double getMaximumdepthinmeters() {
        return maximumdepthinmeters;
    }

    /**
     * @param maximumdepthinmeters the maximumdepthinmeters to set
     */
    public void setMaximumdepthinmeters(Double maximumdepthinmeters) {
        this.maximumdepthinmeters = maximumdepthinmeters;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the lifestage
     */
    public String getLifestage() {
        return lifestage;
    }

    /**
     * @param lifestage the lifestage to set
     */
    public void setLifestage(String lifestage) {
        this.lifestage = lifestage;
    }

    /**
     * @return the preparations
     */
    public String getPreparations() {
        return preparations;
    }

    /**
     * @param preparations the preparations to set
     */
    public void setPreparations(String preparations) {
        this.preparations = preparations;
    }

    /**
     * @return the individualcount
     */
    public Long getIndividualcount() {
        return individualcount;
    }

    /**
     * @param individualcount the individualcount to set
     */
    public void setIndividualcount(Long individualcount) {
        this.individualcount = individualcount;
    }

    /**
     * @return the genbanknum
     */
    public String getGenbanknum() {
        return genbanknum;
    }

    /**
     * @param genbanknum the genbanknum to set
     */
    public void setGenbanknum(String genbanknum) {
        this.genbanknum = genbanknum;
    }

    /**
     * @return the othercatalognumbers
     */
    public String getOthercatalognumbers() {
        return othercatalognumbers;
    }

    /**
     * @param othercatalognumbers the othercatalognumbers to set
     */
    public void setOthercatalognumbers(String othercatalognumbers) {
        this.othercatalognumbers = othercatalognumbers;
    }

    /**
     * @return the relatedcatalogitems
     */
    public String getRelatedcatalogitems() {
        return relatedcatalogitems;
    }

    /**
     * @param relatedcatalogitems the relatedcatalogitems to set
     */
    public void setRelatedcatalogitems(String relatedcatalogitems) {
        this.relatedcatalogitems = relatedcatalogitems;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return the attributes
     */
    public String getAttributes() {
        return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    /**
     * @return the imageurl
     */
    public String getImageurl() {
        return imageurl;
    }

    /**
     * @param imageurl the imageurl to set
     */
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    /**
     * @return the relatedinformation
     */
    public String getRelatedinformation() {
        return relatedinformation;
    }

    /**
     * @param relatedinformation the relatedinformation to set
     */
    public void setRelatedinformation(String relatedinformation) {
        this.relatedinformation = relatedinformation;
    }

    /**
     * @return the disposition
     */
    public String getDisposition() {
        return disposition;
    }

    /**
     * @param disposition the disposition to set
     */
    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    /**
     * @return the pointradiusspatialfit
     */
    public Long getPointradiusspatialfit() {
        return pointradiusspatialfit;
    }

    /**
     * @param pointradiusspatialfit the pointradiusspatialfit to set
     */
    public void setPointradiusspatialfit(Long pointradiusspatialfit) {
        this.pointradiusspatialfit = pointradiusspatialfit;
    }

    /**
     * @return the footprintspatialfit
     */
    public Long getFootprintspatialfit() {
        return footprintspatialfit;
    }

    /**
     * @param footprintspatialfit the footprintspatialfit to set
     */
    public void setFootprintspatialfit(Long footprintspatialfit) {
        this.footprintspatialfit = footprintspatialfit;
    }

    /**
     * @return the verbatimcoordinates
     */
    public String getVerbatimcoordinates() {
        return verbatimcoordinates;
    }

    /**
     * @param verbatimcoordinates the verbatimcoordinates to set
     */
    public void setVerbatimcoordinates(String verbatimcoordinates) {
        this.verbatimcoordinates = verbatimcoordinates;
    }

    /**
     * @return the georeferencesources
     */
    public String getGeoreferencesources() {
        return georeferencesources;
    }

    /**
     * @param georeferencesources the georeferencesources to set
     */
    public void setGeoreferencesources(String georeferencesources) {
        this.georeferencesources = georeferencesources;
    }

    /**
     * @return the georeferenceverificationstatus
     */
    public String getGeoreferenceverificationstatus() {
        return georeferenceverificationstatus;
    }

    /**
     * @param georeferenceverificationstatus the georeferenceverificationstatus to set
     */
    public void setGeoreferenceverificationstatus(String georeferenceverificationstatus) {
        this.georeferenceverificationstatus = georeferenceverificationstatus;
    }

}
