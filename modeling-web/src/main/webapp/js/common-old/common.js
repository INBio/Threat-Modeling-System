/**
 * listLayers.jsp
 */

function newLayer(){
	var form = document.getElementById("layerForm");
	form.action="newLayer.html";
	form.submit();
}

function deleteLayer(){
	var form = document.getElementById("layerForm");
	form.action="deleteLayer.html";
	form.submit();
}

function editLayer(){
	var form = document.getElementById("layerForm");
	form.action="editLayer.html";
	form.submit();
}

/**
 * layers.jsp
 */

function calculateValues(){

//	var layerList   = document.getElementById("layers").childNodes;
	var layerList   = document.forms['layersForm'].getElementsByTagName("DIV");
	var totalLabel = document.getElementById("totalImportanceValue");
	var submitButtom = document.getElementById("submitButton");

	var totalImportanceValue = 0;

	var checkbox = undefined;
	var textfield = undefined;

	for (var layerDiv = 0; layerDiv<layerList.length; layerDiv++){

		if(layerList[layerDiv].nodeName == "DIV" ){
            if(layerList[layerDiv].className != ""){
                checkbox = document.getElementById(layerList[layerDiv].className);
                textfield = document.getElementById(layerList[layerDiv].className+"_weight");
                if(checkbox != null && checkbox.checked == true){
                    totalImportanceValue += (+textfield.value);
                    textfield.disabled = false;
                }
            }
		}
	}

	totalLabel.firstChild.nodeValue = totalImportanceValue;

	if(totalImportanceValue != 100 ){
		totalLabel.style.color = "red";
		totalLabel.style.fontSize = "24";
		submitButtom.disabled = true;
		submitButtom.style.color = "gray";

	}else{
		totalLabel.style.color = "black";
		totalLabel.style.fontSize = "16";
		submitButtom.disabled = false;
		submitButtom.style.color = "black";
	}

	return;
}

function setValueToZero(selected){

	var selectedValue = document.getElementById(selected.id+"_weight");

	if(selected.checked == true)
		selectedValue.disabled = false;
	else
		selectedValue.disabled = true;

	calculateValues();

	return;
}

/**
 * intervals.jsp
 */


function edit(radio){

	var radios = document.getElementsByName("rbEditing");
	var rad = undefined;
	var displayStyle = undefined;
	var categoryDiv =  undefined;

	for(var item = 0; item < radios.length; item++){

		if(item == "item")
			break;

		rad = radios[item];
		displayStyle = document.getElementById(rad.id+"_cats").style.display;
		categoryDiv = document.getElementById(rad.id+"_cats");

		if(rad.checked == true && displayStyle == "none"){
			categoryDiv.style.display="";
		}else{
			categoryDiv.style.display="none";
		}
	}
}

function addCategory(){

	var radio = undefined;
	var radios = document.getElementsByName("rbEditing");
	var categories = undefined;
	var currentLayer = undefined;
	var currentCategory = undefined;

	for(var item = 0; item < radios.length; item++){
		radio = radios[item];
		if(radio.checked == true)
			break;
	}
	categories = document.getElementById(radio.id+"_cats");
	currentCategory = categories.childElementCount;
	currentLayer = radio.value;

	if(categories.className == "AREA"){
		categories.innerHTML = categories.innerHTML+
			"<div id='category_"+currentCategory+"'><input type='checkbox' name='"+radio.id+"'/>&nbsp;" +
			"<input type='text' id='layers"+currentLayer+".categories"+currentCategory+".value' name='layers["+currentLayer+"].categories["+currentCategory+"].value'  class='intervals_value' />&nbsp;"+
			"<input class='intervals_text' id='layers"+currentLayer+".categories"+currentCategory+".description' name='layers["+currentLayer+"].categories["+currentCategory+"].description' /><br /></div>";
	}else{
		categories.innerHTML = categories.innerHTML+
			"<div id='category_"+currentCategory+"'><input type='checkbox' name='"+radio.id+"'/>&nbsp;" +
			"<input  type='text' id='layers"+currentLayer+".categories"+currentCategory+".value' name='layers["+currentLayer+"].categories["+currentCategory+"].value' value='0'  class='intervals_txt' />&nbsp;<br /></div>";
	}
}

function deleteCategory(){

	var radio = undefined;
	var categories = undefined;

	var radios = document.getElementsByName("rbEditing");

	for(var item = 0; item < radios.length; item++){
		radio = radios[item];
		if(radio.checked == true)
			break;
	}

	categories = document.getElementsByName(radio.id);

	for(var ckb = categories.length-1; ckb >= 0; ckb--){
		check = categories[ckb];
		if(check.checked == true && check.parentNode.id != ""){
			check.parentNode.parentNode.removeChild(check.parentNode);
		}
	}
}


function activateButtons(){
    document.getElementById("groupButton").disabled = false;
    document.getElementById("topPriority").disabled = false;
    document.getElementById("lowPriority").disabled = false;
    document.getElementById("groupButton").style.color = "black";
    document.getElementById("topPriority").style.color = "black";
    document.getElementById("lowPriority").style.color = "black";
}

function deActivateButtons(){
    document.getElementById("groupButton").disabled = true;
    document.getElementById("topPriority").disabled = true;
    document.getElementById("lowPriority").disabled = true;
    document.getElementById("groupButton").style.color = "white";
    document.getElementById("topPriority").style.color = "white";
    document.getElementById("lowPriority").style.color = "white";

}

function groupCategorys()
{

	var radio = undefined;
	var categories = undefined;

	var radios = document.getElementsByName("rbEditing");

	for(var item = 0; item < radios.length; item++){
		radio = radios[item];
		if(radio.checked == true)
			break;
	}

	categories = document.getElementsByName(radio.id);

	var firstId;

    var tags;
	var valuesId;
	var descriptionsId;
    var firstCheck;
	var values = "";
	var descriptions = "";
    //for #1. Selecciona el id de la primera categoria y los ids de los text fields en esta
    for(var ckb = 0; ckb <= categories.length -1; ckb++){
		check = categories[ckb];
		if(check.checked == true){
            tags = check.parentNode.getElementsByTagName("input");
            firstCheck = tags[0];
            valuesId = tags[1].id;
            descriptionsId = tags[2].id;
            //values = tags[1].value;
            //descriptions = tags[2].value;
            firstId = check.parentNode.id;
            break;
		}
	}

	//for # 2. Elimina todas las categorias seleccionadas a agrupar menos la inicial (la seleccionada en el for anterior)
    for(var i = categories.length-1; i >= 0; i--){

		check = categories[i];
		if(check.checked == true && firstId != check.parentNode.id)
		{
            tags = check.parentNode.getElementsByTagName("input");
            values = ", "+tags[1].value + values;
            descriptions = ", "+tags[2].value + descriptions;
            check.parentNode.parentNode.removeChild(check.parentNode);

		}
	}
    //llena el text field encontrados en el for #1 y quita el check
    document.getElementById(valuesId).value = document.getElementById(valuesId).value + values;
    document.getElementById(descriptionsId).value = document.getElementById(descriptionsId).value + descriptions;
    firstCheck.checked = false;
}

function topPriorityCategorys(){
	var radio = undefined;
	var categories = undefined;

	var radios = document.getElementsByName("rbEditing");

	for(var item = 0; item < radios.length; item++){
        radio = radios[item];
        if(radio.checked == true)
            break;
	}

	categories = document.getElementsByName(radio.id);

    var tagsPrevoius;
    var tagsNews;
    var valuesIdPrevious;
    var valuesDescriptionPrevious;
    var readOnlyPrevoius;
    var valuesIdNew;
    var valuesDescriptionNew;
    var readOnlyNew;
	//for(var ckb = categories.length-1; ckb >= 0; ckb--){
    for(var ckb = 0; ckb <= categories.length-1; ckb++){
        check = categories[ckb];
        if(check.checked == true && (ckb -1) >= 0)
        {
            if(categories[ckb - 1].checked != true)
            {
                //get the values
                tagsNews = check.parentNode.getElementsByTagName("input");
                valuesIdNew = tagsNews[1].value;
                valuesDescriptionNew = tagsNews[2].value;
                readOnlyNew = tagsNews[1].readOnly;

                tagsPrevoius = categories[ckb - 1].parentNode.getElementsByTagName("input");
                valuesIdPrevious = tagsPrevoius[1].value;
                valuesDescriptionPrevious = tagsPrevoius[2].value;
                readOnlyPrevoius = tagsPrevoius[1].readOnly;

                //set the values
                categories[ckb - 1].checked = true;
                categories[ckb].checked = false;
                tagsNews[1].value = valuesIdPrevious;
                tagsNews[2].value = valuesDescriptionPrevious;
                tagsNews[1].readOnly = readOnlyPrevoius;
                tagsNews[2].readOnly = readOnlyPrevoius;
                    
                tagsPrevoius[1].value = valuesIdNew;
                tagsPrevoius[2].value = valuesDescriptionNew;
                tagsPrevoius[1].readOnly = readOnlyNew;
                tagsPrevoius[2].readOnly = readOnlyNew;
            }
        }
	}
}

/**NEW* se debe agregar un boton para subir y para bajar*/
function lowPriorityCategorys(){
	var radio = undefined;
	var categories = undefined;

	var radios = document.getElementsByName("rbEditing");

	for(var item = 0; item < radios.length; item++){
        radio = radios[item];
        if(radio.checked == true)
            break;
	}

	categories = document.getElementsByName(radio.id);

    var tagsPrevoius;
    var tagsNews;
    var valuesIdPrevious;
    var valuesDescriptionPrevious;
    var readOnlyPrevoius;
    var valuesIdNew;
    var valuesDescriptionNew;
    var readOnlyNew;
	//for(var ckb = categories.length-1; ckb >= 0; ckb--){
    for(var ckb = categories.length-1; ckb >= 0; ckb--){
        check = categories[ckb];
        if(check.checked == true && (ckb +1) <= categories.length-1)
        {
            if(categories[ckb + 1].checked != true)
            {
                //get the values
                tagsNews = check.parentNode.getElementsByTagName("input");
                valuesIdNew = tagsNews[1].value;
                valuesDescriptionNew = tagsNews[2].value;
                readOnlyNew = tagsNews[1].readOnly;

                tagsPrevoius = categories[ckb + 1].parentNode.getElementsByTagName("input");
                valuesIdPrevious = tagsPrevoius[1].value;
                valuesDescriptionPrevious = tagsPrevoius[2].value;
                readOnlyPrevoius = tagsPrevoius[1].readOnly;


                //set the values
                categories[ckb + 1].checked = true;
                categories[ckb].checked = false;
                tagsNews[1].value = valuesIdPrevious;
                tagsNews[2].value = valuesDescriptionPrevious;
                tagsNews[1].readOnly = readOnlyPrevoius;
                tagsNews[2].readOnly = readOnlyPrevoius;
                    
                tagsPrevoius[1].value = valuesIdNew;
                tagsPrevoius[2].value = valuesDescriptionNew;
                tagsPrevoius[1].readOnly = readOnlyNew;
                tagsPrevoius[2].readOnly = readOnlyNew;

            }
        }
	}
}
/**
 * listUsers.jsp
 */

function newUser(){

	var form = document.getElementById("userForm");
	form.action="newUser.html";
	form.submit();
}

function deleteUser(){
	var form = document.getElementById("userForm");
	form.action="deleteUser.html";
	form.submit();
}

function editUser(){
	var form = document.getElementById("userForm");
	form.action="editUser.html";
	form.submit();
}

/*
 * showResultingMap.jsp
 */

/*
 * Function to create new Layers
 */
function addLayerWMS(name, layer) {

    return new OpenLayers.Layer.WMS( name, "http://216.75.53.105:80/geoserver_2_0_x/wms",
    {layers: layer,
        transparent: "true",
        height: '478',
        width: '512'}, {isBaseLayer: false,singleTile: true, ratio: 1, opacity: 0.50, 'buffer': 0, visibility: false});
}

/*
 * Function to create new Layers
 */
function addLayerWFS(name, serverNameLayer) {
    return new OpenLayers.Layer.WFS(
    name,
    'http://216.75.53.105:80/geoserver/wfs',
    {typename: serverNameLayer});
}

/*Inverse the order of the cathegorys*/
function inverseValues(layerId){

    var titleDiv = document.getElementById("title_"+layerId);
    var categoryDiv = document.getElementById(layerId + "_cats");
    var categories = categoryDiv.childNodes;
    var check = new Array();

    document.getElementById(layerId + "_cats").appendChild(titleDiv);
    for(var ckb = categories.length-1; ckb >= 0; ckb--){
        check = categories[ckb];
        document.getElementById(layerId + "_cats").appendChild(check);
    }
}

/***************** Cowboy code *************************/

function exportResult( type ){
    var outputType = document.getElementById('outputType');
    outputType.value = type;

    return true;
}
