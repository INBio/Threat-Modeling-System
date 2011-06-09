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
 * To draw the specimen points into the map
 */
function showPointFromHiddenData(){
    //Show loading
    YAHOO.example.container.wait.show();
    //Getting the query parameters
    var taxa = document.getElementById('hiddenTaxa').value;
    var indic = document.getElementById('hiddenIndicators').value;
    //Drowing the points
    showSpecimenPoints("",taxa,indic);
}

/**
 * To draw the specimen points into the map
 * id = polygon id or indicator id
 * type p = polygon or i = indicator
 */
function showPoints(id,type){
    var taxa = document.getElementById('hiddenTaxa').value;

    //Show loading
    YAHOO.example.container.wait.show();
    //Indicate de current selected
    indicateCurrent('t'+id,'map');
    //Show map
    map.render('map');
    //Drowing the points
    showSpecimenPoints("",taxa,"");
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