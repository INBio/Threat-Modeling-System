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
			"<input  type='text' name='layers["+currentLayer+"].categories["+currentCategory+"].value' value='"+(currentCategory+1)+"'  class='intervals_txt' />&nbsp;"+
			"<input class='intervals_txt' name='layers["+currentLayer+"].categories["+currentCategory+"].description' value='Category "+(currentCategory+1)+"' /><br /></div>";
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
