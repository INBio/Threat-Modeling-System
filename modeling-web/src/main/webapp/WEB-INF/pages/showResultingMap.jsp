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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script>

    <link rel="stylesheet" type="text/css" href="http://openlayers.org/dev/theme/default/style.css"/>
    
    <script type="text/JavaScript" src="http://openlayers.org/api/OpenLayers.js"></script>
    <!-- localhost -->
    <!--script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAAPwVPh_9lvEdVGuSx9bULhhQEKpM7ZWcZRfz-UdsvSoIjER5D5RSUMkmSTMm54S-8s0HtiMTGujMn2A" type="text/javascript"></script-->

    <!-- Lucina -->
    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAAcXLIcswpc7kEfEXYxvvNThRbo2pJpNQIEpt8-nIdM7Qcnrb6GBQa3aXumTFilc--XaPZCMFekCNS1Q" type="text/javascript"></script>

    <script type="text/javascript" >

      //--------------------------------------------------------------------------
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
        //map
        var map;
        //Indicators tree
        var tree;
        var currentIconMode;
        //Getting a reference to the root node of indicators tree
        var rootNode;
        //Current selected indicators tree node
        var selectedNodeId;
        var selectedNodeName;
        var isLeaf;

        //Internacionalization of the report texts
        var searchResults,geographical,taxonomic,indicators,speciesMatches,
        seeOnMap,seeDetail,searchCriteria,speciesList,newSearch,criteriaText,
        speciesText,hideMap,hideDetail,catalog,latitude,longitute,scientificName,
        layerMatches,indicatorMatches,resultDetails,criteriaWithoutResults,occurrences,
        resultsGeo,resultsIndi;

        //Array that contains the id's of each <div> from multiple div results
        var divIds = new Array(),buttonIds = new Array(),
        ids = new Array(),types = new Array();
        //--------------------------------------------------------------------------

        //Use a proxy for GeoServer requesting
        OpenLayers.ProxyHost = "cgi-bin/proxy.cgi/?url=";

        //Prepare URL for XHR request:
        var sUrl = "cgi-bin/proxy.cgi/?url=http://216.75.53.105:80/geoserver_2_0_x/wms?request=getCapabilities";

        //Prepare callback object
        var callback = {

          //If XHR call is successful
          success: function(oResponse) {

            var xml;
            var parser;
            var xmlText = oResponse.responseText;

            if (window.DOMParser){
              parser=new DOMParser();
              xml=parser.parseFromString(xmlText,"text/xml");
            } else { // IE
              xml=new ActiveXObject("Microsoft.XMLDOM");
              xml.async="false";
              xml.loadXML(xmlText);
            }

            //Root element -> response
            //var xmlDoc = oResponse.responseXML.documentElement;
            var xmlDoc = xml.documentElement;

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
          var aux = addLayerWMS("${layer.displayName}", "${layer.name}", "${layer.uri}");//(name,id)
          map.addLayer(aux);
      </c:forEach>

          var aux = addLayerWMS("<fmt:message key='showMap.principal' />","${fullSessionInfo.limitLayerName}", '');//(name,id)
          map.addLayer(aux);


          //Make our XHR call using Connection Manager's
          YAHOO.util.Connect.asyncRequest('GET', sUrl, callback, null);

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
          showWaitingDialog();
          //Clear vector layer
          replaceVectorLayer();
          //Getting the parameter lists
          var taxonlist = document.getElementById('taxParameters');
          var treelist = document.getElementById('treeParameters');

          var indiAsText = '';

          //Arrays with parameters data (to show on the results table)
          var taxonsShow = new Array(),treeShow = new Array();
          //String with parameters data (to store in hidden field)
          //Validate that exist at least one search criteria
          if(taxonlist.childNodes.length==0||treelist.childNodes.length==0){
            alert(selectCriteriaE);
            simpleCleannig();
            hideWaitingDialog();
            return;
          }


          //Loop over taxonomic criteria
          var selectedTaxa = "";
          for (var j =0; j <taxonlist.childNodes.length; j++){
            if(document.all){
              selectedTaxa += taxonlist.childNodes[j].id+"|";
              taxonsShow.push(taxonlist.childNodes[j].innerText);
            }
            else{
              selectedTaxa += taxonlist.childNodes[j].id+"|";
              taxonsShow.push(taxonlist.childNodes[j].textContent);
            }
          }

          //Loop over indicators criteria
          var selectedIndicators = "";
          for (var k =0; k <treelist.childNodes.length; k++){
            selectedIndicators += treelist.childNodes[k].id+"|";
            if(document.all){
              treeShow.push(treelist.childNodes[k].innerText);
              indiAsText += treelist.childNodes[k].innerText+'|';
            }
            else{
              treeShow.push(treelist.childNodes[k].textContent);
              indiAsText += treelist.childNodes[k].textContent+'|';
            }
          }
          //Setting to hidden fields the query values.
          setHiddenValues(selectedTaxa,selectedIndicators,indiAsText);

          //Clean entry criteria lists
          cleanAfterRequest();

          showPointFromHiddenData();
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
          divIds = new Array();
          buttonIds = new Array();
          ids = new Array();
          types = new Array();
        }

        /*
         * Setting to hidden fields the query values. Those info are going to
         * be used to show specimens point into the map (not in use yet)
         */

        function setHiddenValues(selectedTaxa,selectedIndicators,indiAsText){
          document.getElementById('hiddenTaxa').value = selectedTaxa;
          document.getElementById('hiddenIndicators').value = selectedIndicators;
          document.getElementById('hiddenIndiToShow').value = indiAsText;
        }

        //Go to anchor
		    function internationalization(){
          selectCriteriaE = "<fmt:message key="showMap.selectCriteriaError"/>";
          catalog = "<fmt:message key="showMap.catalogNumber"/>";
          latitude = "<fmt:message key="showMap.latitude"/>";
          longitute = "<fmt:message key="showMap.longitude"/>";
          scientificName = "<fmt:message key="showMap.scientificName"/>";
          treeLeafE = "<fmt:message key="showMap.indicatorLeaf"/>";
        };

        function showOcurrencesSearchBox(){

          var mapInfo = document.getElementById('categoryInfo');
          var searchBox = document.getElementById('entryCriteria');

          if(mapInfo.style.display == "" && searchBox.style.display == "none"){
            mapInfo.style.display = "none" ;
            searchBox.style.display = "";
          }else{
            mapInfo.style.display = "" ;
            searchBox.style.display = "none";
          }
        }

        function init(){
          //Load messages content
          internationalization();
                
          //initialize map functionality
          initMap('map');
                
          //Init indicators tree
          initIndicators();

          // init the taxon information
          changeTaxonInput();

      <fmt:setBundle basename="config" var="config" />
      <fmt:message bundle="${config}" key="indicatorsSystem.integration" var="indiIntegration"/>
        };
        
        // javascript specific for this page
        $(document).ready(function(){
          initHelpDialog();
        })
        waitingDialogTitle = '<fmt:message key="common.loading"/>';
        
        window.location = "${pageContext.request.contextPath}/showResultingMap.html#";
    </script>

  </head>
  <body onload="init()" class="yui-skin-sam" >
    <div id="Header">
      <!-- Header -->
      <jsp:include page="/common/header.jsp"/>
    </div>
    <div id="contenido" style="width: 100%; height: auto;">

      <div id="help-box" class="loader" ></div>
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
        <div id="entryCriteria" style="display: none" class="categoryInfo"> <!-- Entry criteria div -->
          <div id="querysPanel">
            <!-- Taxonomy Panel -->
            <div id="queryPanel2" class="queryPanel">
              <div  class="link_help"  onclick="showPanel('<fmt:message key="help.taxonCriteria.title" />','<fmt:message key="help.taxonCriteria.cont" />')" >&nbsp;</div>
              <p class="criteria_title">
                <fmt:message key="showMap.taxonomicalCriteriaTitle"/></p>
              <p style="margin:2px"><a> <fmt:message key="showMap.taxonomyLevel"/>: </a></p>
              <select name="taxonType" id="taxonTypeId" class="componentSize" tabindex="12" onchange="javascript:changeTaxonInput();" onKeyUp="javascript:changeTaxonInput();">
                <c:forEach items="${taxonFilters}" var="taxonFilter">
                  <option value="<c:out value="${taxonFilter.id}"/>"<c:if test="${taxonFilter.id == taxonType}"> selected="selected"</c:if>>
                    <fmt:message key="${taxonFilter.displayName}"/> </option>
                  </c:forEach>
              </select>
              <p style="margin:2px"><a> <fmt:message key="showMap.taxonName"/>:</a></p>
              <span id="newTaxonValue">
                <input id="taxonId" tabindex="13" class="componentSize" type="text" name="taxonValue" value="<c:out value="${taxonValue}"/>"/>
                <div id="taxonContainer"></div>
              </span>
              <input type="button" class="button-simple" id="addToListButtonTax" value="<fmt:message key="showMap.addCriteria"/>" onclick="addTaxonParam()" />
              <span id="taxParameters" style="font-size:10px"></span>
            </div>
            <!-- Indicator Panel -->
            <div id="queryPanel3" class="queryPanel">
              <div  class="link_help"  onclick="showPanel('<fmt:message key="help.indicatorCriteria.title" />','<fmt:message key="help.indicatorCriteria.cont" />')" >&nbsp;</div>
              <p class="criteria_title">
                <fmt:message key="showMap.indicatorCriteriaTitle"/></p>
              <div id="treeDiv"></div>
              <input type="button" class="button-simple" id="addToListButtonIndi" value="<fmt:message key="showMap.addCriteria"/>" onclick="addIndicatorParam();" />
              <span id="treeParameters" style="font-size:10px"></span>
            </div>
            <div id="queryPanel3" class="queryPanel" style="text-align: center">
              <input type="button" class="button-simple" id="makeQueryButton" value="<fmt:message key="showMap.displayData"/>" onclick="makeQuery()" />
            </div>
          </div>
        </div>
        <div id="categoryInfo" class="categoryInfo">
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
            <table border="2" class="tabla-contenido">
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
                    <c:when test="${'LINE' eq layer.type}" >
                      <fmt:message key="showMap.intervals" />
                      <c:if test="${'true' eq layer.reverted}">
												(<fmt:message key="common.reverted" />)
                      </c:if>
                    </c:when>
                    <c:otherwise>
                      <fmt:message key="showMap.radius" />
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
          <c:if test="${'true' eq indiIntegration}" >
            <input id="showOccurrences" type="button" class="button-simple" value='<fmt:message key="showMap.showOccurrences"/>' onclick="showOcurrencesSearchBox()" />
          </c:if>
          <input class="link_help" type="button" style="float:none; border: none " onclick="showPanel('<fmt:message key="help.buttons.title" />','<fmt:message key="help.buttons.cont" />')" />
        </form:form>
      </div>
      <!-- Panel to show the result header (abstract) -->
      <div id="resultsPanel"></div>

      <!-- Panel to show the detailed result -->
      <div id="detailedResults"></div>
      <input type="hidden" id="hiddenTaxa" value="">
      <input type="hidden" id="hiddenIndicators" value="">
      <input type="hidden" id="hiddenIndiToShow" value="">
    </div>
    <div id="footer">
      <jsp:include page="/common/footer.jsp"/>
    </div>
  </body>
</html>

