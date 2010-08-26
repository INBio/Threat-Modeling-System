
/*
 * Add a new taxonomic filter
 */
function addTaxonParam() {
    //Get the text field value
    var txTaxon = document.getElementById('taxonId');
    var text = txTaxon.value;
    //Validate null values
    if(text==null||text==''){
        alert(specifyTaxonE);
        txTaxon.value = '';
        return
    }
    //Validate repeated values
    var aux_exist = document.getElementById(text);
    if(aux_exist!=null){
        alert(alreadyAddedE);
        txTaxon.value = '';
        return;
    }
    //Add the search criteria
    var taxonlist = document.getElementById('taxParameters');
    var newdiv = document.createElement('div');
    newdiv.setAttribute("id",text);
    newdiv.innerHTML =
        "<a class=\"criteria\" href=\"javascript:\" onclick=\"removeTaxonParamElement(\'"+text+"\')\">"+text+"</a>";
    taxonlist.appendChild(newdiv);
    txTaxon.value = '';
}

/*
 * Deletes an element by it's id
 */
function removeTaxonParamElement(divNum) {
  var d = document.getElementById('taxParameters');
  var olddiv = document.getElementById(divNum);
  d.removeChild(olddiv);
}

/*
 * Add new indicators filter
 */
function addIndicatorParam(){
    //Validate that a node was selected
    if(selectedNodeId==null||selectedNodeName==null){
        alert(selectIndicatorFirstE);
        return;
    }
    //Validate if it is a leaf
    if(isLeaf=="false"){
        alert(treeLeafE);
        return;
    }
    //Validate that the selected indicator was not repeated
    var aux_exist = document.getElementById(selectedNodeId);
    if(aux_exist!=null){
        alert(alreadyAddedE);
        return;
    }
    //Add the criteria to the list
    var indicatorslist = document.getElementById('treeParameters');
    var newdiv = document.createElement('div');
    newdiv.setAttribute("id",selectedNodeId);
    newdiv.innerHTML =
        "<a class=\"criteria\" href=\"javascript:\" onclick=\"removeTreeParamElement(\'"+selectedNodeId+"\')\">"+selectedNodeName+"</a>";
    indicatorslist.appendChild(newdiv);
}

/*
 * Deletes an element by it's id
 */
function removeTreeParamElement(divNum) {
  var d = document.getElementById('treeParameters');
  var olddiv = document.getElementById(divNum);
  d.removeChild(olddiv);
}

/*
 * Set the initial value to the geografic variables
 */
/*function clearGeograficVars(){
    currentPolygonId = null;
    currentPolygonName = null;

    layerId = layersList[1][0];
    layerIndex = 1;
    layerName = layersList[1][1];
    polygonsList = null;
}*/

