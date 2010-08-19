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

/**
 * Execute final query from especific parameters
 */
function executeFinalQuery(selectedLayers,selectedTaxa,selectedIndicators,
                           layersShow,taxonsShow,treeShow)  {

    //Prepare URL for XHR request:
    var sUrl = "/modeling-web/ajax/finalQuery?layers="+selectedLayers+"&taxons="+selectedTaxa+"&indi="+selectedIndicators;

    //Prepare callback object
    var callback = {

        //If XHR call is successful
        success: function(oResponse) {
            //Root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;

            //Get total count data
            var total = xmlDoc.getElementsByTagName("total")[0].childNodes[0].nodeValue;

            //Show general result and the search criteria
            var criteria = "";
            if(taxonsShow.length>0){
                criteria += "<b>"+taxonomic+" </b>";
                for(var j = 0;j<taxonsShow.length;j++){
                    //criteria += taxonsShow[j]+"   ";
                    criteria += "<a class=\"criteria\">"+taxonsShow[j]+"</a>";
                }
                criteria += "<br>";
            }

            //Show the results
            var resultHTML = createReportHeader(criteria, total);
            document.getElementById('resultsPanel').innerHTML = resultHTML;
            //Close de "Loading image"
            YAHOO.example.container.wait.hide();
            //Calling the anchor to positionate the page focus on the results
            callAnchor('#anchorTop');
        },

        //If XHR call is not successful
        failure: function(oResponse) {
            YAHOO.log("Failed to process XHR transaction.", "info", "example");
        }
    };
    
    //Make our XHR call using Connection Manager's
    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
}

/**
 * This function creates the general report, so, return an html string
 * based on the search criteria and the general count of matches
 */
function createReportHeader(criteria,total){
    var result = '<div id="reportHeader">'+
    '<h3centered>'+searchCriteria+'</h3centered>'+criteria+
    '<h3>'+total+' '+speciesMatches+'</h3>'+
    '<input type="button" class="button-simple" id="viewDetail0" value="'+seeDetail+'" onclick="showDetailsFromHiddenData(\'viewDetail0\')" />'+
    '<input type="button" class="button-simple" id="showOnMap0" value="'+seeOnMap+'" onclick="showPointFromHiddenData(\'showOnMap0\')" />'+
    '<input type="submit" class="button-simple" id="newSearch" value="'+newSearch+'" /></div>'+
    '<div id="s0map"></div>';
    return result;
}

/**
 * To draw the specimen points into the map
 */
function showPointFromHiddenData(inputId){
    //Show loading
    YAHOO.example.container.wait.show();
    //Getting the query parameters
    var taxa = document.getElementById('hiddenTaxa').value;
    //Add div to show the map
    document.getElementById('s0map').innerHTML = '<div id="map"></div>';
    //Show the map
    document.getElementById('map').appendChild(map.viewPortDiv); //Adding the new viewPort parent
    map.render('map');
    //Drowing the points
    showSpecimenPoints("",taxa,"");
    //Change button title
    changeInputText(inputId,hideMap);
    //Change button acction
    goToHidePointsFromHidden('s0map',inputId);
}
// Changes the input (button) action to hide points
function goToHidePointsFromHidden(divId,inputId){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "hidePointFromHiddenData('"+divId+"','"+inputId+"');");*/
   document.getElementById(inputId).onclick = function(){
       hidePointFromHiddenData(divId,inputId);
   };
}

/**
 * To hide the map in simple detail
 */
function hidePointFromHiddenData(divId,inputId){
    //Hide the map
    document.getElementById(divId).innerHTML = "";
    //Change button title
    changeInputText(inputId,seeOnMap);
    //Change button  acction
    goToShowPointsFromHidden(inputId);
}
// Changes the input (button) action to show points
function goToShowPointsFromHidden(inputId){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "showPointFromHiddenData('"+inputId+"');");*/
   document.getElementById(inputId).onclick = function(){
       showPointFromHiddenData(inputId);
   };
}

/**
 * To get a detailed report of the query when there is one or two parameters
 * Return a List of specimens that match with the criteria
 */
function showDetailsFromHiddenData(inputId){
    //Show loading
    YAHOO.example.container.wait.show();
    //Getting the query parameters
    var layers = document.getElementById('hiddenLayers').value;
    var taxa = document.getElementById('hiddenTaxa').value;
    var indi = document.getElementById('hiddenIndicators').value;
    var lToShow = document.getElementById('hidLayersToShow').value;
    //Showing the details
    viewSimpleDetail(layers,taxa,indi,lToShow);
    //Change button title
    changeInputText(inputId,hideDetail);
    //Change button acction
    goToHideDetailFromHidden('detailedResults',inputId);
}
// Changes the input (button) action to hide details
function goToHideDetailFromHidden(divId,inputId){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "hideDetailFromHiddenData('"+divId+"','"+inputId+"');");*/
   document.getElementById(inputId).onclick = function(){
       hideDetailFromHiddenData(divId,inputId);
   };
}

/**
 * To hide details in simple detail
 */
function hideDetailFromHiddenData(divId,inputId){
    //Hide the detailed table
    document.getElementById(divId).innerHTML = "";
    //Change button title
    changeInputText(inputId,seeDetail);
    //Change button  acction
    goToShowDetailFromHidden(inputId);
}
// Changes the input (button) action to show detail
function goToShowDetailFromHidden(inputId){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "showDetailsFromHiddenData('"+inputId+"');");*/
   document.getElementById(inputId).onclick = function(){
       showDetailsFromHiddenData(inputId);
   };
}

/**
 * To draw the specimen points into the map
 * id = polygon id or indicator id
 * type p = polygon or i = indicator
 */
function showPoints(id,type){
    var taxa = document.getElementById('hiddenTaxa').value;
    if(type=='t'){ //All search criteria
        //Show loading
        YAHOO.example.container.wait.show();
        //Indicate de current selected
        indicateCurrent('t'+id,'map');
        //Show map
        map.render('map');
        //Drowing the points
        showSpecimenPoints("",taxa,"");
        //Change button title
        changeInputText('showOnMapt'+id,hideMap); //button,text
        //Change button acction
        goToHideMap(type+id+'map','showOnMapt'+id,id,type); //div,button
    }
    else if(type=='p'){ //By polygon
        //Show loading
        YAHOO.example.container.wait.show();
        //Getting the query parameters
        var layersArray = layers.split('|');
        //Indicate de current selected
        indicateCurrent('p'+id,'map');
        //Show map
        map.render('map');
        //Drowing the points
        showSpecimenPoints(layersArray[id]+'|',taxa,indi);
        //Change button title
        changeInputText('showOnMapp'+id,hideMap); //button,text
        //Change button acction
        goToHideMap(type+id+'map','showOnMapp'+id,id,type); //div,button
    }
        else{ //By indicator
            //Show loading
            YAHOO.example.container.wait.show();
            //Getting the query parameters
            var indiArray = indi.split('|');
            //Indicate de current selected
            indicateCurrent('i'+id,'map');
            //Show map
            map.render('map');
            //Drowing the points
            showSpecimenPoints(layers,taxa,indiArray[id]+'|');
            //Change button title
            changeInputText('showOnMapi'+id,hideMap); //button,text
            //Change button acction
            goToHideMap(type+id+'map','showOnMapi'+id,id,type); //div,button,id,type
        }
}
// Changes the input (button) action to hide map
function goToHideMap(divId,inputId,id,type){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "hidePoints('"+divId+"','"+inputId+"','"+id+"','"+type+"');");*/
   document.getElementById(inputId).onclick = function(){
       hidePoints(divId,inputId,id,type);
   };
}

/**
 * To hide the map in multiple detailed results
 */
function hidePoints(divId,inputId,id,type){
    //Hide the map
    document.getElementById(divId).innerHTML = "";
    //Change button title
    changeInputText(inputId,seeOnMap);
    //Change button  acction
    goToShowMap(inputId,id,type);
}
// Changes the input (button) action to show map
function goToShowMap(inputId,id,type){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "showPoints('"+id+"','"+type+"');");*/
   document.getElementById(inputId).onclick = function(){
       showPoints(id,type);
   };
}

/**
 * To get a detailed report of the query when there is one or two parameters
 * Return a List of specimens that match with the criteria
 * id = polygon id or indicator id
 * type p = polygon or i = indicator
 * toShow polygons or indicators to show in the final matrix
 */
function showDetails(id,type,toShow){
    var layers = document.getElementById('hiddenLayers').value;
    var taxa = document.getElementById('hiddenTaxa').value;
    var indi = document.getElementById('hiddenIndicators').value;
    if(type=='p'){
        //Show loading
        YAHOO.example.container.wait.show();
        //Getting the query parameters
        var layersArray = layers.split('|');
        //Indicate de current selected
        indicateCurrent('p'+id,'detail');
        //Drowing the points
        detailedTable(layersArray[id]+'|',taxa,indi,toShow,type,id);
        //Change button title
        changeInputText('viewDetailp'+id,hideDetail); //button,text
        //Change button acction
        goToHideDetail(type+id+'detail','viewDetailp'+id,id,type,toShow); //div,button
    }
    else{
        //Show loading
        YAHOO.example.container.wait.show();
        //Getting the query parameters
        var indiArray = indi.split('|');
        //Indicate de current selected
        indicateCurrent('i'+id,'detail');
        //Drowing the points
        detailedTable(layers,taxa,indiArray[id]+'|',toShow,type,id);
        //Change button title
        changeInputText('viewDetaili'+id,hideDetail); //button,text
        //Change button acction
        goToHideDetail(type+id+'detail','viewDetaili'+id,id,type,toShow); //div,button
    }
}
// Changes the input (button) action to hide detail
function goToHideDetail(divId,inputId,id,type,toShow){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "hideDetails('"+divId+"','"+inputId+"','"+id+"','"+type+"','"+toShow+"');");*/
   document.getElementById(inputId).onclick = function(){
       hideDetails(divId,inputId,id,type,toShow);
   };
}

/**
 * To hide a detail in multiple detailed results
 */
function hideDetails(divId,inputId,id,type,toShow){
    //Hide the detail
    document.getElementById(divId).innerHTML = "";
    //Change button title
    changeInputText(inputId,seeDetail);
    //Change button  acction
    goToShowDetail(inputId,id,type,toShow);
}
// Changes the input (button) action to show detail
function goToShowDetail(inputId,id,type,toShow){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "showDetails('"+id+"','"+type+"','"+toShow+"');");*/
   document.getElementById(inputId).onclick = function(){
       showDetails(id,type,toShow);
   };
}

/**
 * Changes the input (button) title
 * Generic method
 */
function changeInputText(inputId,text){
    document.getElementById(inputId).value = text;
}

/**
 * Indicates de current selected <div> from resultsPanel
 * type could be map or detail
 * id = div identificator
 */
function indicateCurrent(id,type){
    if(type=='map'){ //Display on map
        for(var i = 0;i<divIds.length;i++){
            if(id==divIds[i]){ //Indicate
                document.getElementById(divIds[i]).className = 'current_Selected';
                document.getElementById(divIds[i]+'map').innerHTML = '<div id="map"></div>';
            }
            else{ //No indicate
                document.getElementById(divIds[i]).className = 'detailed_results';
                document.getElementById(divIds[i]+'map').innerHTML = "";
                if(document.getElementById(buttonIds[i]) != null){
                    //Change button title
                    changeInputText(buttonIds[i],seeOnMap);
                    //Change button  acction
                    goToShowMap(buttonIds[i],ids[i],types[i]);
                }
            }
        }
    }
    else{ //Show detail
        for(var j = 0;j<divIds.length;j++){
            if(id==divIds[j]){ //Indicate
                document.getElementById(divIds[j]).className = 'current_Selected';
            }
            else{ //No indicate
                document.getElementById(divIds[j]).className = 'detailed_results';
            }
        }
    }
}
function getDivParameter(id,rest){
    var result = new Array();
    result.push(id+rest)
    return result;
}

//Array to string
function arrayToString(array){
    var result = '';
    for(var i = 0 ;i<array.length ;i++){
        result += array[i]+"|";
    }
    return result;
}