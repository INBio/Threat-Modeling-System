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

	var layerList   = document.getElementById("layers").childNodes;
	var totalLabel = document.getElementById("totalImportanceValue");
	var submitButtom = document.getElementById("submitButton");

	var totalImportanceValue = 0;

	var checkbox = undefined;
	var textfield = undefined;

	for each(var layerDiv in layerList){

		if(layerDiv.nodeName == "DIV"){
			checkbox = document.getElementById(layerDiv.className);
			textfield = document.getElementById(layerDiv.className+"_weight");
			if(checkbox.checked == true){
				totalImportanceValue += (+textfield.value);
				textfield.disabled = false;
			}
		}
	}

	totalLabel.firstChild.nodeValue = totalImportanceValue;

	if(totalImportanceValue != 100 ){
		totalLabel.style.color = "red";
		totalLabel.style.fontSize = "24";
		submitButtom.disabled = true;

	}else{
		totalLabel.style.color = "black";
		totalLabel.style.fontSize = "16";
		submitButtom.disabled = false;
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

	for(var item in radios){

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

	for(var item in radios){
		radio = radios[item];
		if(radio.checked == true)
			break;
	}
	categories = document.getElementById(radio.id+"_cats");
	currentCategory = categories.childElementCount;
	currentLayer = radio.value;

	if(categories.className == "AREA"){
		categories.innerHTML = categories.innerHTML+
			"<div><input type='checkbox' name='"+radio.id+"'/>&nbsp;" +
			"<input type='text' name='layers["+currentLayer+"].categories["+currentCategory+"].value'  class='intervals_txt' />&nbsp;"+
			"<input class='intervals_txt' name='layers["+currentLayer+"].categories["+currentCategory+"].description' /><br /></div>";
	}else{
		categories.innerHTML = categories.innerHTML+
			"<div><input type='checkbox' name='"+radio.id+"'/>&nbsp;" +
			"<input  type='text' name='layers["+currentLayer+"].categories["+currentCategory+"].value' value='0'  class='intervals_txt' />&nbsp;<br /></div>";
	}

}

function deleteCategory(){

	var radio = undefined;
	var categories = undefined;

	var radios = document.getElementsByName("rbEditing");

	for(var item in radios){
		radio = radios[item];
		if(radio.checked == true)
			break;
	}

	categories = document.getElementsByName(radio.id);

	for(var ckb = categories.length-1; ckb >= 0; ckb--){
		check = categories[ckb];
		if(check.checked == true){
			check.parentNode.parentNode.removeChild(check.parentNode);
		}
	}
}

/*Inverse the order of the cathegorys*/
function inverseValues(layerId){

	var categories = undefined;
        categories = document.getElementsByName(layerId);

        var newHTML = "";
        var count = 0;
	for(var ckb = categories.length-1; ckb >= 0; ckb--){
            check = categories[ckb];

            newHTML += "<div id=\"category_" + count + "\">" +
                check.parentNode.innerHTML + "</div>";
            count++;
	}
        document.getElementById(layerId + "_cats").innerHTML = newHTML;
}

function activateButtons(){
    document.getElementById("groupButton").disabled = false;
    document.getElementById("topPriority").disabled = false;
    document.getElementById("lowPriority").disabled = false;
}

function deActivateButtons(){
    document.getElementById("groupButton").disabled = true;
    document.getElementById("topPriority").disabled = true;
    document.getElementById("lowPriority").disabled = true;

}

function groupCategorys()
{

	var radio = undefined;
	var categories = undefined;

	var radios = document.getElementsByName("rbEditing");

	for(var item in radios)
	{
		radio = radios[item];
		if(radio.checked == true)
			break;
	4}

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
                    values = ","+tags[1].value + values;
                    descriptions = ","+tags[2].value + descriptions;
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

	for(var item in radios){
            radio = radios[item];
            if(radio.checked == true)
                    break;
	}

	categories = document.getElementsByName(radio.id);

        var tagsPrevoius;
        var tagsNews;
        var valuesIdPrevious;
        var valuesDescriptionPrevious;
        var valuesIdNew;
        var valuesDescriptionNew;
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

                    tagsPrevoius = categories[ckb - 1].parentNode.getElementsByTagName("input");
                    valuesIdPrevious = tagsPrevoius[1].value;
                    valuesDescriptionPrevious = tagsPrevoius[2].value;


                    //set the values
                    categories[ckb - 1].checked = true;
                    categories[ckb].checked = false;
                    tagsNews[1].value = valuesIdPrevious;
                    tagsNews[2].value = valuesDescriptionPrevious;
                    tagsPrevoius[1].value = valuesIdNew;
                    tagsPrevoius[2].value = valuesDescriptionNew;
                }
            }
	}
}

/**NEW* se debe agregar un boton para subir y para bajar*/
function lowPriorityCategorys(){
	var radio = undefined;
	var categories = undefined;

	var radios = document.getElementsByName("rbEditing");

	for(var item in radios){
            radio = radios[item];
            if(radio.checked == true)
                    break;
	}

	categories = document.getElementsByName(radio.id);

        var tagsPrevoius;
        var tagsNews;
        var valuesIdPrevious;
        var valuesDescriptionPrevious;
        var valuesIdNew;
        var valuesDescriptionNew;
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

                    tagsPrevoius = categories[ckb + 1].parentNode.getElementsByTagName("input");
                    valuesIdPrevious = tagsPrevoius[1].value;
                    valuesDescriptionPrevious = tagsPrevoius[2].value;


                    //set the values
                    categories[ckb + 1].checked = true;
                    categories[ckb].checked = false;
                    tagsNews[1].value = valuesIdPrevious;
                    tagsNews[2].value = valuesDescriptionPrevious;
                    tagsPrevoius[1].value = valuesIdNew;
                    tagsPrevoius[2].value = valuesDescriptionNew;

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

/**
 *
 */
