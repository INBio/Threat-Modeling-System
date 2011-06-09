
/**
 * Change the Object input  - retrieving the type using the selected index
 * of the supplied HTMLSelectElement.
 */
function changeTaxonInput() {
	
	//The new taxon TypeId
	var taxonTypeElement = document.getElementById('taxonTypeId');
	var taxonTypeId = taxonTypeElement.value;
	
	//Get the actual value of the taxonValue textField
	var taxonValueTextField = document.getElementById('taxonId');
	var taxonActualValue = taxonValueTextField.value;
	
	
	//get value span	
	var valueSpan = document.getElementById('newTaxonValue');
	removeElement(valueSpan, "INPUT");		
	removeElement(valueSpan, "DIV");		
	removeElement(valueSpan, "SCRIPT");

	//Add the new input field
	var newInput = document.createElement('INPUT');
	newInput.name = "taxonValue";
	newInput.id = "taxonId";
	newInput.value = taxonActualValue;
	valueSpan.appendChild(newInput);
	newInput.tabIndex="13";

	//autocomplete?
	var useAutoComplete = (taxonAutoCompleteUrls[taxonTypeId]!=null);
        //if this filter uses auto complete, setup required container divs
	if(useAutoComplete){
	  //alert("use auto complete");
	  var autoCompleteDiv = document.createElement('DIV');
	  autoCompleteDiv.id="taxonContainer";
	  autoCompleteDiv.className="yui-skin-m3s";
	  
	  //autoCompleteDiv.innerHTML ="";
	  //autoCompleteDiv.style.left="0px";
  	  valueSpan.appendChild(autoCompleteDiv);			
  	  setupAutoComplete(taxonAutoCompleteUrls[taxonTypeId],"taxonId", "taxonContainer");
	}
	
}


