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
 * Species entity
 * @author esmata
 */
public class Species {

    /**
     * Empty constructor
     */
    public Species(){}

    /**
     * Class atributes
     */
    private String globaluniqueidentifier;
    private String scientificname;
    private String institutioncode;
    private Date datelastmodified;
    private String taxonrecordid;
    private String language;
    private String creators;
    private String distribution;
    private String abstract1;
    private String kingdomtaxon;
    private String phylumtaxon;
    private String classtaxon;
    private String ordertaxon;
    private String familytaxon;
    private String genustaxon;
    private String synonyms;
    private String authoryearofscientificname;
    private String speciespublicationreference;
    private String commonnames;
    private String typification;
    private String contributors;
    private Date datecreated;
    private String habit;
    private String lifecycle;
    private String reproduction;
    private String annualcycle;
    private String scientificdescription;
    private String briefdescription;
    private String feeding;
    private String behavior;
    private String interactions;
    private String chromosomicnumbern;
    private String moleculardata;
    private String populationbiology;
    private String threatstatus;
    private String legislation;
    private String habitat;
    private String territory;
    private String endemicity;
    private String theuses;
    private String themanagement;
    private String folklore;
    private String thereferences;
    private String unstructureddocumentation;
    private String otherinformationsources;
    private String papers;
    private String identificationkeys;
    private String migratorydata;
    private String ecologicalsignificance;
    private String unstructurednaturalhistory;
    private String invasivenessdata;
    private String targetaudiences;
    private String version;
    private String urlimage1;
    private String captionimage1;
    private String urlimage2;
    private String captionimage2;
    private String urlimage3;
    private String captionimage3;

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
     * @return the taxonrecordid
     */
    public String getTaxonrecordid() {
        return taxonrecordid;
    }

    /**
     * @param taxonrecordid the taxonrecordid to set
     */
    public void setTaxonrecordid(String taxonrecordid) {
        this.taxonrecordid = taxonrecordid;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the creators
     */
    public String getCreators() {
        return creators;
    }

    /**
     * @param creators the creators to set
     */
    public void setCreators(String creators) {
        this.creators = creators;
    }

    /**
     * @return the distribution
     */
    public String getDistribution() {
        return distribution;
    }

    /**
     * @param distribution the distribution to set
     */
    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    /**
     * @return the abstract1
     */
    public String getAbstract1() {
        return abstract1;
    }

    /**
     * @param abstract1 the abstract1 to set
     */
    public void setAbstract1(String abstract1) {
        this.abstract1 = abstract1;
    }

    /**
     * @return the kingdomtaxon
     */
    public String getKingdomtaxon() {
        return kingdomtaxon;
    }

    /**
     * @param kingdomtaxon the kingdomtaxon to set
     */
    public void setKingdomtaxon(String kingdomtaxon) {
        this.kingdomtaxon = kingdomtaxon;
    }

    /**
     * @return the phylumtaxon
     */
    public String getPhylumtaxon() {
        return phylumtaxon;
    }

    /**
     * @param phylumtaxon the phylumtaxon to set
     */
    public void setPhylumtaxon(String phylumtaxon) {
        this.phylumtaxon = phylumtaxon;
    }

    /**
     * @return the classtaxon
     */
    public String getClasstaxon() {
        return classtaxon;
    }

    /**
     * @param classtaxon the classtaxon to set
     */
    public void setClasstaxon(String classtaxon) {
        this.classtaxon = classtaxon;
    }

    /**
     * @return the ordertaxon
     */
    public String getOrdertaxon() {
        return ordertaxon;
    }

    /**
     * @param ordertaxon the ordertaxon to set
     */
    public void setOrdertaxon(String ordertaxon) {
        this.ordertaxon = ordertaxon;
    }

    /**
     * @return the familytaxon
     */
    public String getFamilytaxon() {
        return familytaxon;
    }

    /**
     * @param familytaxon the familytaxon to set
     */
    public void setFamilytaxon(String familytaxon) {
        this.familytaxon = familytaxon;
    }

    /**
     * @return the genustaxon
     */
    public String getGenustaxon() {
        return genustaxon;
    }

    /**
     * @param genustaxon the genustaxon to set
     */
    public void setGenustaxon(String genustaxon) {
        this.genustaxon = genustaxon;
    }

    /**
     * @return the synonyms
     */
    public String getSynonyms() {
        return synonyms;
    }

    /**
     * @param synonyms the synonyms to set
     */
    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
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
     * @return the speciespublicationreference
     */
    public String getSpeciespublicationreference() {
        return speciespublicationreference;
    }

    /**
     * @param speciespublicationreference the speciespublicationreference to set
     */
    public void setSpeciespublicationreference(String speciespublicationreference) {
        this.speciespublicationreference = speciespublicationreference;
    }

    /**
     * @return the commonnames
     */
    public String getCommonnames() {
        return commonnames;
    }

    /**
     * @param commonnames the commonnames to set
     */
    public void setCommonnames(String commonnames) {
        this.commonnames = commonnames;
    }

    /**
     * @return the typification
     */
    public String getTypification() {
        return typification;
    }

    /**
     * @param typification the typification to set
     */
    public void setTypification(String typification) {
        this.typification = typification;
    }

    /**
     * @return the contributors
     */
    public String getContributors() {
        return contributors;
    }

    /**
     * @param contributors the contributors to set
     */
    public void setContributors(String contributors) {
        this.contributors = contributors;
    }

    /**
     * @return the datecreated
     */
    public Date getDatecreated() {
        return datecreated;
    }

    /**
     * @param datecreated the datecreated to set
     */
    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    /**
     * @return the habit
     */
    public String getHabit() {
        return habit;
    }

    /**
     * @param habit the habit to set
     */
    public void setHabit(String habit) {
        this.habit = habit;
    }

    /**
     * @return the lifecycle
     */
    public String getLifecycle() {
        return lifecycle;
    }

    /**
     * @param lifecycle the lifecycle to set
     */
    public void setLifecycle(String lifecycle) {
        this.lifecycle = lifecycle;
    }

    /**
     * @return the reproduction
     */
    public String getReproduction() {
        return reproduction;
    }

    /**
     * @param reproduction the reproduction to set
     */
    public void setReproduction(String reproduction) {
        this.reproduction = reproduction;
    }

    /**
     * @return the annualcycle
     */
    public String getAnnualcycle() {
        return annualcycle;
    }

    /**
     * @param annualcycle the annualcycle to set
     */
    public void setAnnualcycle(String annualcycle) {
        this.annualcycle = annualcycle;
    }

    /**
     * @return the scientificdescription
     */
    public String getScientificdescription() {
        return scientificdescription;
    }

    /**
     * @param scientificdescription the scientificdescription to set
     */
    public void setScientificdescription(String scientificdescription) {
        this.scientificdescription = scientificdescription;
    }

    /**
     * @return the briefdescription
     */
    public String getBriefdescription() {
        return briefdescription;
    }

    /**
     * @param briefdescription the briefdescription to set
     */
    public void setBriefdescription(String briefdescription) {
        this.briefdescription = briefdescription;
    }

    /**
     * @return the feeding
     */
    public String getFeeding() {
        return feeding;
    }

    /**
     * @param feeding the feeding to set
     */
    public void setFeeding(String feeding) {
        this.feeding = feeding;
    }

    /**
     * @return the behavior
     */
    public String getBehavior() {
        return behavior;
    }

    /**
     * @param behavior the behavior to set
     */
    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    /**
     * @return the interactions
     */
    public String getInteractions() {
        return interactions;
    }

    /**
     * @param interactions the interactions to set
     */
    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }

    /**
     * @return the chromosomicnumbern
     */
    public String getChromosomicnumbern() {
        return chromosomicnumbern;
    }

    /**
     * @param chromosomicnumbern the chromosomicnumbern to set
     */
    public void setChromosomicnumbern(String chromosomicnumbern) {
        this.chromosomicnumbern = chromosomicnumbern;
    }

    /**
     * @return the moleculardata
     */
    public String getMoleculardata() {
        return moleculardata;
    }

    /**
     * @param moleculardata the moleculardata to set
     */
    public void setMoleculardata(String moleculardata) {
        this.moleculardata = moleculardata;
    }

    /**
     * @return the populationbiology
     */
    public String getPopulationbiology() {
        return populationbiology;
    }

    /**
     * @param populationbiology the populationbiology to set
     */
    public void setPopulationbiology(String populationbiology) {
        this.populationbiology = populationbiology;
    }

    /**
     * @return the threatstatus
     */
    public String getThreatstatus() {
        return threatstatus;
    }

    /**
     * @param threatstatus the threatstatus to set
     */
    public void setThreatstatus(String threatstatus) {
        this.threatstatus = threatstatus;
    }

    /**
     * @return the legislation
     */
    public String getLegislation() {
        return legislation;
    }

    /**
     * @param legislation the legislation to set
     */
    public void setLegislation(String legislation) {
        this.legislation = legislation;
    }

    /**
     * @return the habitat
     */
    public String getHabitat() {
        return habitat;
    }

    /**
     * @param habitat the habitat to set
     */
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    /**
     * @return the territory
     */
    public String getTerritory() {
        return territory;
    }

    /**
     * @param territory the territory to set
     */
    public void setTerritory(String territory) {
        this.territory = territory;
    }

    /**
     * @return the endemicity
     */
    public String getEndemicity() {
        return endemicity;
    }

    /**
     * @param endemicity the endemicity to set
     */
    public void setEndemicity(String endemicity) {
        this.endemicity = endemicity;
    }

    /**
     * @return the theuses
     */
    public String getTheuses() {
        return theuses;
    }

    /**
     * @param theuses the theuses to set
     */
    public void setTheuses(String theuses) {
        this.theuses = theuses;
    }

    /**
     * @return the themanagement
     */
    public String getThemanagement() {
        return themanagement;
    }

    /**
     * @param themanagement the themanagement to set
     */
    public void setThemanagement(String themanagement) {
        this.themanagement = themanagement;
    }

    /**
     * @return the folklore
     */
    public String getFolklore() {
        return folklore;
    }

    /**
     * @param folklore the folklore to set
     */
    public void setFolklore(String folklore) {
        this.folklore = folklore;
    }

    /**
     * @return the thereferences
     */
    public String getThereferences() {
        return thereferences;
    }

    /**
     * @param thereferences the thereferences to set
     */
    public void setThereferences(String thereferences) {
        this.thereferences = thereferences;
    }

    /**
     * @return the unstructureddocumentation
     */
    public String getUnstructureddocumentation() {
        return unstructureddocumentation;
    }

    /**
     * @param unstructureddocumentation the unstructureddocumentation to set
     */
    public void setUnstructureddocumentation(String unstructureddocumentation) {
        this.unstructureddocumentation = unstructureddocumentation;
    }

    /**
     * @return the otherinformationsources
     */
    public String getOtherinformationsources() {
        return otherinformationsources;
    }

    /**
     * @param otherinformationsources the otherinformationsources to set
     */
    public void setOtherinformationsources(String otherinformationsources) {
        this.otherinformationsources = otherinformationsources;
    }

    /**
     * @return the papers
     */
    public String getPapers() {
        return papers;
    }

    /**
     * @param papers the papers to set
     */
    public void setPapers(String papers) {
        this.papers = papers;
    }

    /**
     * @return the identificationkeys
     */
    public String getIdentificationkeys() {
        return identificationkeys;
    }

    /**
     * @param identificationkeys the identificationkeys to set
     */
    public void setIdentificationkeys(String identificationkeys) {
        this.identificationkeys = identificationkeys;
    }

    /**
     * @return the migratorydata
     */
    public String getMigratorydata() {
        return migratorydata;
    }

    /**
     * @param migratorydata the migratorydata to set
     */
    public void setMigratorydata(String migratorydata) {
        this.migratorydata = migratorydata;
    }

    /**
     * @return the ecologicalsignificance
     */
    public String getEcologicalsignificance() {
        return ecologicalsignificance;
    }

    /**
     * @param ecologicalsignificance the ecologicalsignificance to set
     */
    public void setEcologicalsignificance(String ecologicalsignificance) {
        this.ecologicalsignificance = ecologicalsignificance;
    }

    /**
     * @return the unstructurednaturalhistory
     */
    public String getUnstructurednaturalhistory() {
        return unstructurednaturalhistory;
    }

    /**
     * @param unstructurednaturalhistory the unstructurednaturalhistory to set
     */
    public void setUnstructurednaturalhistory(String unstructurednaturalhistory) {
        this.unstructurednaturalhistory = unstructurednaturalhistory;
    }

    /**
     * @return the invasivenessdata
     */
    public String getInvasivenessdata() {
        return invasivenessdata;
    }

    /**
     * @param invasivenessdata the invasivenessdata to set
     */
    public void setInvasivenessdata(String invasivenessdata) {
        this.invasivenessdata = invasivenessdata;
    }

    /**
     * @return the targetaudiences
     */
    public String getTargetaudiences() {
        return targetaudiences;
    }

    /**
     * @param targetaudiences the targetaudiences to set
     */
    public void setTargetaudiences(String targetaudiences) {
        this.targetaudiences = targetaudiences;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the urlimage1
     */
    public String getUrlimage1() {
        return urlimage1;
    }

    /**
     * @param urlimage1 the urlimage1 to set
     */
    public void setUrlimage1(String urlimage1) {
        this.urlimage1 = urlimage1;
    }

    /**
     * @return the captionimage1
     */
    public String getCaptionimage1() {
        return captionimage1;
    }

    /**
     * @param captionimage1 the captionimage1 to set
     */
    public void setCaptionimage1(String captionimage1) {
        this.captionimage1 = captionimage1;
    }

    /**
     * @return the urlimage2
     */
    public String getUrlimage2() {
        return urlimage2;
    }

    /**
     * @param urlimage2 the urlimage2 to set
     */
    public void setUrlimage2(String urlimage2) {
        this.urlimage2 = urlimage2;
    }

    /**
     * @return the captionimage2
     */
    public String getCaptionimage2() {
        return captionimage2;
    }

    /**
     * @param captionimage2 the captionimage2 to set
     */
    public void setCaptionimage2(String captionimage2) {
        this.captionimage2 = captionimage2;
    }

    /**
     * @return the urlimage3
     */
    public String getUrlimage3() {
        return urlimage3;
    }

    /**
     * @param urlimage3 the urlimage3 to set
     */
    public void setUrlimage3(String urlimage3) {
        this.urlimage3 = urlimage3;
    }

    /**
     * @return the captionimage3
     */
    public String getCaptionimage3() {
        return captionimage3;
    }

    /**
     * @param captionimage3 the captionimage3 to set
     */
    public void setCaptionimage3(String captionimage3) {
        this.captionimage3 = captionimage3;
    }

}
