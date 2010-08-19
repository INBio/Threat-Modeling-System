<%-- 
    Document   : showResultingMap
    Created on : 24/03/2010, 08:59:36 AM
    Author     : asanabria
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/common/taglibs" %>

<html>
    <head>
        <%@ include file="/common/theme" %>
        <%@ include file="/common/javascript" %>


        <link rel="stylesheet" type="text/css" href="http://openlayers.org/theme/default/style.css"/>
        <script type="text/JavaScript" src="http://openlayers.org/api/OpenLayers.js"></script>
        <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAAGtIHQJm1-pS3ci26k9D7hRQgngloALHesSZLYd1j0z536uH2MxQmIpE6xoh3_0LA7MpmysupPTjnvg" type="text/javascript"></script>

        <script type="text/javascript" >

            //Using to show the loading panel
            YAHOO.namespace("example.container");

            //--------------------------------------------------------------------------
            var loadingImage = "<img src='${pageContext.request.contextPath}/themes/default/images/ajax-loader.gif' ></img>";
            var loadingText = "<fmt:message key="common.loading"/>";
            var taxonAutoCompleteUrls = new Array(${ fn:length(taxonFilters)});

            <c:forEach items="${taxonFilters}" var="taxonFilter" varStatus="filterStatus" begin="0">
              <c:if test="${not empty taxonFilter.autoCompleteUrl}">
                taxonAutoCompleteUrls[${taxonFilter.id}] = "${pageContext.request.contextPath}/${taxonFilter.autoCompleteUrl}";
              </c:if>
            </c:forEach>

            var bbox = undefined;
            //Layer to show specimens points
            var vectorLayer;
            //Control to manage pop ups on the map
            var selectControl;
            //Current selected especimen point into the map
            var selectedFeature;
            //To create a new atribute for each specimen point
            var attributes;

            //Internacionalization of the report texts
            var searchResults,geographical,taxonomic,indicators,speciesMatches,
            seeOnMap,seeDetail,searchCriteria,speciesList,newSearch,criteriaText,
            speciesText,hideMap,hideDetail,catalog,latitude,longitute,scientificName,
            layerMatches,indicatorMatches,resultDetails,criteriaWithoutResults,occurrences,
            resultsGeo,resultsIndi;
            //--------------------------------------------------------------------------

           //Use a proxy for GeoServer requesting
            OpenLayers.ProxyHost = "cgi-bin/proxy.cgi/?url=";

            //Prepare URL for XHR request:
            var sUrl = "cgi-bin/proxy.cgi/?url=http://216.75.53.105:80/geoserver/wms?request=getCapabilities";

            //Prepare callback object
            var callback = {

                //If XHR call is successful
                success: function(oResponse) {


                    //Root element -> response
                    var xmlDoc = oResponse.responseXML.documentElement;
                    //Get the list of specimens
                    var layerList = xmlDoc.getElementsByTagName("Layer");

                    //Add all the specimen point
                    for(var i = 0;i<layerList.length;i++){
                        var node = layerList[i];
                        var name = node.getElementsByTagName("Name")[0].childNodes[0].nodeValue;
                        if(name == "${fullSessionInfo.limitLayerName}"){
                                var llAttributes = node.getElementsByTagName("BoundingBox")[0].attributes;
                                var bbox =  new OpenLayers.Bounds();
                                bbox.extend(new OpenLayers.LonLat( llAttributes.minx.value, llAttributes.miny.value));
                                bbox.extend(new OpenLayers.LonLat( llAttributes.maxx.value, llAttributes.maxy.value));
                                    var img = new OpenLayers.Layer.Image("<fmt:message key='showMap.threats' />",
                                                        "/resmaps/${fullSessionInfo.imageName}_T_${fullSessionInfo.userSessionId}.png",
                                                        bbox,
                                                        500,
                                                        {isBaseLayer: false, transparent: true, opacity: 0.75 , singleTile: true, ratio: 1,bgcolor: 'transparent' });
                                    map.addLayer(img);
                                    map.panTo(bbox.getCenterLonLat());
                                    map.zoomToExtent(bbox);
                                    map.raiseLayer(img, map.getNumLayers()*-1);
                        }
                    }
                },

                //If XHR call is not successful
                failure: function(oResponse) {
                    YAHOO.log("Failed to process XHR transaction.", "info", "example");
                }
            };

            /*
            * Initialazing the gis functionality
            */
            function initMap(divId){


                var initialbounds = new OpenLayers.Bounds(
                    -85.954, 8.04,
                    -82.553, 11.22);

                var options = {
                    controls: [],
                    maxResolution: 0.09776171875,
                    projection: "EPSG:900913",
                    units: 'm'
                };

                var myMapDiv = document.getElementById(divId);
                map = new OpenLayers.Map(divId, options);
                map.render(myMapDiv);

                //------------------------------ Layers ------------------------------------
                //Base layer
                googleLayer  = new OpenLayers.Layer.Google('Google Hybrid', {type: G_SATELLITE_MAP });
                map.addLayer(googleLayer);

                <c:forEach items="${speciesLayers}" var="layer">
                    var aux = addLayerWMS("${layer.displayName}", "${layer.name}");//(name,id)
                    map.addLayer(aux);
                </c:forEach>

                var aux = addLayerWMS("<fmt:message key='showMap.principal' />","${fullSessionInfo.limitLayerName}");//(name,id)
                map.addLayer(aux);

                //Make our XHR call using Connection Manager's
                YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);


                //Build up all controls
                map.zoomToExtent(initialbounds);
                map.addControl(new OpenLayers.Control.PanZoomBar({
                    position: new OpenLayers.Pixel(2, 15)
                }));
                map.addControl(new OpenLayers.Control.LayerSwitcher({'ascending':false},{'position':OpenLayers.Control}));
                map.addControl(new OpenLayers.Control.Navigation());
                map.addControl(new OpenLayers.Control.Scale($('scale')));
                map.addControl(new OpenLayers.Control.MousePosition({element: $('location')}));
            }

            /*
             * This function calls another function that is on charge to make the
             * final query and show the result to the user
             */
            function makeQuery(){
                //Show loading
                YAHOO.example.container.wait.show();
                //Clear vector layer
                replaceVectorLayer();
                //Getting the parameter lists
                var taxonlist = document.getElementById('taxParameters');
                //Arrays with parameters data (to show on the results table)
                var taxonsShow = new Array();
                //String with parameters data (to store in hidden field)
                //Validate that exist at least one search criteria
                if(taxonlist.childNodes.length==0){
                    alert(selectCriteriaE);
                    simpleCleannig();
                    YAHOO.example.container.wait.hide();
                    return;
                }
                //Loop over taxonomic criteria
                var selectedTaxa = "";
                for (var j =0; j <taxonlist.childNodes.length; j++){
                    if(document.all){
                        selectedTaxa += taxonlist.childNodes[j].innerText+"|";
                        taxonsShow.push(taxonlist.childNodes[j].innerText);
                    }
                    else{
                        selectedTaxa += taxonlist.childNodes[j].textContent+"|";
                        taxonsShow.push(taxonlist.childNodes[j].textContent);
                    }
                }

                //Setting to hidden fields the query values.
                setHiddenValues(selectedTaxa);

                //Clean entry criteria lists
                cleanAfterRequest();

                //To unregister the function to introduce map info to the query criteria
                //map.events.unregister('click', map, addMapListener);

                //Call the function that returns the result (xml) asincronically
                executeFinalQuery("",selectedTaxa,"", "",taxonsShow,"");
            };

            function simpleCleannig(){
                document.getElementById('resultsPanel').innerHTML = "";
                document.getElementById('detailedResults').innerHTML = "";
                document.getElementById("detailedResults").className = "";
            }

            /*
             * Clean the page after request a new report
             */
            function cleanAfterRequest(){
                document.getElementById('entryCriteria').innerHTML = "";
                divIds = new Array();
                buttonIds = new Array();
                ids = new Array();
                types = new Array();
            }

            /*
             * Setting to hidden fields the query values. Those info are going to
             * be used to show specimens point into the map (not in use yet)
             */
            function setHiddenValues(selectedTaxa){
                document.getElementById('hiddenTaxa').value = selectedTaxa;
            }

            /*
             * Sets the HTML provided into the nodelist element from
             * the maps response
             */
            function setHTML(response){
                //Obtain the selected polygon(s), value set on currentPolygonId var
                parseHTML(response.responseText);
                //Verify if the list is null
                if(polygonsList==null){
                    return;
                }
                //Verify if the polygon is unique
                if(polygonsList.length!=1){
                    alert(selectOnePolygonE);
                    return;
                }
                //Add the polygon to the geografical criteria list
                currentPolygonId = polygonsList[0][0];
                currentPolygonName = polygonsList[0][1];
                addLayerParam(currentPolygonId,layerId,currentPolygonName,layerName);
                //Clean the Loading status
                document.getElementById('info').innerHTML = "";
            }

            //Go to anchor
            function callAnchor(anchor){
            document.location.href = anchor;
            }

            function internationalization(){
                layerText =  "<fmt:message key="layers"/>";
                loadingText = "<fmt:message key="loading"/>";
                selectCriteriaE = "<fmt:message key="select_criteria_error"/>";
                selectOnePolygonE = "<fmt:message key="select_one_polygon"/>";
                invalidPolygonE = "<fmt:message key="not_valid_polygon"/>";
                selectLayerPolyE = "<fmt:message key="select_layer_and_poly"/>";
                alreadyAddedE = "<fmt:message key="already_criteria"/>";
                specifyTaxonE = "<fmt:message key="taxon_name_error"/>";
                selectIndicatorFirstE = "<fmt:message key="first_select_indicator"/>";
                treeLeafE = "<fmt:message key="indicator_leaf"/>";
                loadingImage = "<img src=\"${pageContext.request.contextPath}/themes/default/images/ajax-loader.gif\" ></img>";
                searchResults = "<fmt:message key="search_results"/>";
                geographical = "<fmt:message key="geographical"/>";
                taxonomic = "<fmt:message key="taxonomic"/>";
                indicators = "<fmt:message key="indicators"/>";
                speciesMatches = "<fmt:message key="species_matches"/>";
                seeOnMap = "<fmt:message key="see_on_map"/>";
                seeDetail = "<fmt:message key="see_detail"/>";
                searchCriteria = "<fmt:message key="search_criteria"/>";
                speciesList = "<fmt:message key="species_list"/>";
                newSearch = "<fmt:message key="new_search"/>";
                criteriaText = "<fmt:message key="criteria"/>";
                speciesText = "<fmt:message key="species"/>";
                hideMap = "<fmt:message key="hide_map"/>";
                hideDetail = "<fmt:message key="hide_detail"/>";
                catalog = "<fmt:message key="catalog_number"/>";
                latitude = "<fmt:message key="latitude"/>";
                longitute = "<fmt:message key="longitude"/>";
                scientificName = "<fmt:message key="scientificname"/>";
                layerMatches = "<fmt:message key="layer_matches"/>";
                indicatorMatches = "<fmt:message key="indicator_matches"/>";
                resultDetails = "<fmt:message key="result_details"/>";
                criteriaWithoutResults = "<fmt:message key="criteria_without_results"/>";
                occurrences = "<fmt:message key="occurrences"/>";
                resultsGeo = "<fmt:message key="results_geo"/>";
                resultsIndi = "<fmt:message key="results_indi"/>";
            };

        </script>

    </head>
    <body onload="initMap('map');initLoadingPanel();internationalization();changeTaxonInput()" >
        <div id="Header">
            <!-- Header -->
            <jsp:include page="/common/header.jsp"/>
        </div>
        <div id="contenido" style="width: 100%">

            <spring:hasBindErrors name="intervalsForm">
                <div class="errors">
                    <h3><fmt:message key="errors.title"/></h3>
                    <p>
                        <c:forEach items="${errors.allErrors}" var="error">
                            <fmt:message key="${error.code}" />
                        </c:forEach>
                    </p>
                </div>
            </spring:hasBindErrors>
            <div id="results" >
                <div id="categoryInfo">
                    <table border="2" class="tabla-contenido">
                        <tr class="celda02"  >
                            <td colspan="2" style="width:350px; font-weight:bold;max-width: 350px; overflow: hidden;"><fmt:message key="showMap.leyend" /></td>
                        </tr>
                        <tr class="celda01"  >
                            <td width="50%" align="left"><span class="textosnegrita"><fmt:message key="showMap.resolution" /></span></td>
                            <td align="center"><span class="textos"><c:out value="${fullSessionInfo.resolution}" /></span></td>
                        </tr>
                    </table>
                    <c:forEach items="${fullSessionInfo.layerList}" var="layer" >
                        <br />
                        <table border="5" class="tabremoveElementla-contenido">
                            <tr class="celda02">
                                <td colspan="2" style="width:350px; font-weight:bold;max-width: 350px; overflow: hidden;"><c:out value="${layer.displayName}" /></td>
                            </tr>
                            <tr class="celda02">
                            <span class="textosnegrita">
                                <td class="textos" colspan="2" style="font-style:italic;">
                                    <c:choose>
                                        <c:when test="${'AREA' eq layer.type}" >
                                            <fmt:message key="showMap.categories" />
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="showMap.intervals" />
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </span>
                            </tr>
                            <c:forEach items="${layer.categories}" var="category">
                                <c:if test="${category ne null}">
                                    <c:choose>

                                        <c:when test="${'AREA' eq layer.type}" >
                                            <tr class="celda01">
                                                <td style="max-width: 170px; overflow:hidden">
                                                    <span class="textos"><c:out value="${category.value}" /></span>
                                                </td>
                                                <td style="max-width: 170px; overflow:hidden">
                                                    <span class="textos"><c:out value="${category.description}" /></span>
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr class="celda01" style="text-align:center;">
                                                <td style="max-width: 170px; overflow:hidden" colspan="2">
                                                    <span class="textos" ><c:out value="${category.value}" /></span>
                                                </td>
                                            </tr>
                                        </c:otherwise>

                                    </c:choose>
                                </c:if>
                            </c:forEach>
                        </table>
                    </c:forEach>
                </div>
                <div id="mapResults" >
                    <table  style="height:450px">
                        <tr>
                            <td class="mapTable">
                                <!--
                                <div id="mapImage" >
                                    <img alt="<fmt:message key='maps.resultingMap' />" src="/resmaps/${fullSessionInfo.imageName}_T_${fullSessionInfo.userSessionId}.png" />
                                </div>
                                -->
                                <div id="map" style="border:  1px solid black;"></div>
                            </td>
                            <td class="threat_image_text">
                                <fmt:message key="showMap.highThreat" />
                                <br />
                                <div id="scale_image" ></div>
                                <fmt:message key="showMap.lowThreat" />
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="actions">
                <form:form action="export.html" commandName="exportForm">
                    <form:hidden id="outputType" path="type"  />
                    <input id="exportPNGButton" type="submit" class="button-simple" value='<fmt:message key="showMap.exportPNG"/>' onclick="exportResult('PNG');" />
                    <input id="exportSHPButton" type="submit" class="button-simple" value='<fmt:message key="showMap.exportSHP"/>' onclick="exportResult('SHP');" />
                    <input id="exportPDFButton" type="submit" class="button-simple" value='<fmt:message key="showMap.exportPDF"/>' onclick="exportResult('PDF');" />
                    <input id="showOccurrences" type="button" class="button-simple" value='<fmt:message key="showMap.showOccurrences"/>' onclick="showOcurrencesSearchBox()" />
                </form:form>
            </div>
            <div id="entryCriteria"> <!-- Entry criteria div -->
                <div id="querysPanel">
                    <!-- Taxonomy Panel -->
                    <div id="queryPanel2" class="queryPanel">
                        <p class="criteria_title">
                            <fmt:message key="taxonomical_criteria_title"/></p>
                        <p style="margin:2px"><a> <fmt:message key="taxonomy_level"/>: </a></p>
                        <select name="taxonType" id="taxonTypeId" class="componentSize" tabindex="12" onchange="javascript:changeTaxonInput();" onKeyUp="javascript:changeTaxonInput();">
                            <c:forEach items="${taxonFilters}" var="taxonFilter">
                                <option value="<c:out value="${taxonFilter.id}"/>"<c:if test="${taxonFilter.id == taxonType}"> selected="selected"</c:if>>
                                    <fmt:message key="${taxonFilter.displayName}"/>
                                </option>
                            </c:forEach>
                        </select>
                        <p style="margin:2px"><a> <fmt:message key="taxon_name"/>: </a></p>
                        <span id="newTaxonValue">
                            <input id="taxonId" tabindex="13" class="componentSize" type="text" name="taxonValue" value="<c:out value="${taxonValue}"/>"/>
                            <div id="taxonContainer"></div>
                        </span>
                        <input type="button" class="button-simple" id="addToListButtonTax" value="<fmt:message key="add_criteria"/>" onclick="addTaxonParam()" />
                        <input type="button" class="button-simple" id="makeQueryButton" value="<fmt:message key="consult"/>" onclick="makeQuery()" />

                        <span id="taxParameters" style="font-size:10px"></span>
                    </div>
                </div>
            </div>
            <!-- Panel to show the result header (abstract) -->
            <div id="resultsPanel"></div>

            <!-- Panel to show the detailed result -->
            <div id="detailedResults"></div>
            <input type="hidden" id="hiddenTaxa" value="">
        </div>
        <div id="footer">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </body>
</html>

