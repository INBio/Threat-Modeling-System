
function swapProperty(elem1, elem2, prop){
          
  var temp = $(elem1).prop(prop);
          
  $(elem1).prop(prop, $(elem2).prop(prop));
  $(elem2).prop(prop, temp);
                    
}
      
function swapCategoryDivs(div1, div2){
            
  //move up the check
  div2.contents().filter('[type=checkbox]').prop('checked', true);
  div1.contents().filter('[type=checkbox]').prop('checked', false);
            
  //raise value
  var hidden1 = div2.contents().filter('[type=hidden]');
  var hidden2 = div1.contents().filter('[type=hidden]');
  swapProperty(hidden1, hidden2, 'value');
            
  // raise text
  var text1 = div2.contents().filter('[type=text]');
  var text2 = div1.contents().filter('[type=text]');
  swapProperty(text1, text2, 'value');
        
/*
              //raise label.
              var label1 = previous.contents().filter('label');
              var label2 = current.contents().filter('label');
              swapProperty(label1, label2, 'innerHTML');
         */
  
}

/* javascript functions */
function hideUnselectedLayers(){
  // Hide all non selected layers
  $('[type=radio]').not(':checked').each(function(){
    $('#'+this.id.replace(':','\\:')+'_div').css('display','none');
  })
}

function showSelectedLayer(id){
  // Show the selected layer.          
  $('#'+id.replace(':','\\:')+'_div').css('display','block')
}

function getSelectedLayer(){
  return $('[type=radio]').filter(':checked');
}
      
function getSelectedLayerInfo(info){
  var returnValue = null;
        
  switch(info){
          
    case 'id':
      returnValue = $('[type=radio]').filter(':checked').prop('id').replace(':','\\:');
      break;
    case 'value':
      returnValue = $('[type=radio]').filter(':checked').prop('value');
      break;
    case 'div':
      var id = getSelectedLayerInfo('id');
      returnValue = $('#'+id+'_div');
      break;
    case 'categoryCount':
      var id = getSelectedLayerInfo('id');
      var div = $('#'+id+'_div');
      returnValue =  $(div).contents().filter('div').length-1;
      break;
    case 'type':
      var div = getSelectedLayerInfo('id');
      returnValue = $(div).prop('className');
      break;
  }
        
  return returnValue;
}
      
function getSelectedCategories(){
  var layerDiv = getSelectedLayerInfo('div');
  var selectedCategories = $(layerDiv).contents().find('[type=checkbox]').filter(':checked');
  return selectedCategories;
}
      
        
function addCategoryAction(){
  // get the selected layer
  var layerDiv            = getSelectedLayerInfo('div');
  var layerType           = getSelectedLayerInfo('type');
  var selectedLayerId     = getSelectedLayerInfo('id');
  var currentLayerNumber  = getSelectedLayerInfo('value');
  var categoryCount       = getSelectedLayerInfo('categoryCount');
          
  // create the new category div
  var newCategory = '';
           
  if(layerType == 'AREA' ){
    newCategory = "<div><input type='checkbox' name='"+selectedLayerId+"'/>&nbsp;" +
    "<label class='intervals_value'>"+(categoryCount+1)+"</label>" +
    "<input type='hidden' id='layers"+currentLayerNumber+".categories"+categoryCount+".value' name='layers["+currentLayerNumber+"].categories["+categoryCount+"].value' class='intervals_value' value='"+categoryCount+"' />&nbsp;"+
    "<input class='intervals_text' id='layers"+currentLayerNumber+".categories"+categoryCount+".description' name='layers["+currentLayerNumber+"].categories["+categoryCount+"].description' /><br /></div>";
  }else{
    newCategory = "<div><input type='checkbox' name='"+selectedLayerId+"'/>&nbsp;" +
    "<label class='intervals_value'>"+(categoryCount+2)+"</label>&nbsp;" +
    "<input class='intervals_text' id='layers"+currentLayerNumber+".categories"+categoryCount+".value' name='layers["+currentLayerNumber+"].categories["+categoryCount+"].value' /><br /></div>";   
  }
  //add the new div to the list.        
  $(layerDiv).append(newCategory);
}
  

function deleteCategoriesAction(){
  // get the selected categories of the selected layer          
  var selectedCategories = getSelectedCategories();
          
  $(selectedCategories).each(function(){
    $(this).parent().remove();
  })
}

 
function groupCategoriesAction(){
  // get the selected categories of the selected layer
  var selectedCategories = getSelectedCategories();
          
  var firstSelectedCategory = $(selectedCategories).first();
          
  var values = '';
  var descriptions = '';
          
  // go throught the selected checkboxes and process the fields to put toguether 
  // the texts.
  $(selectedCategories).each(function(index){
            
    values +=  $(this).parent().contents().filter('[type=hidden]').prop('value') + ' ';
    descriptions +=  $(this).parent().contents().filter('[type=text]').prop('value') + ',';
            
    $(this).prop('checked', false);
            
    if(index > 0)
      $(this).parent().remove();
            
  })
          
  firstSelectedCategory.parent().contents().filter('[type=hidden]').prop('value', values);
  firstSelectedCategory.parent().contents().filter('[type=text]').prop('value', descriptions);
}
        
 
function raiseCategoryValue(){
  // get the selected categories of the selected layer
  var selectedCategories = getSelectedCategories();
          
  $(selectedCategories).each(function(){
            
    var current = $(this).parent();
    var prev = $(this).parent().prev();
            
    if ($(prev).prop('id') == $('#toolbar').prop('id'))
      return;
            
    swapCategoryDivs(current, prev);
            
  })
}

     
function lowerCategoryValue(){
  var selectedCategories = getSelectedCategories();
          
  var reversed = $(selectedCategories).toArray().reverse();
            
  jQuery.each(reversed, function(index, value){
                        
    var current = $(value).parent();
    var next = $(value).parent().next();
            
    if ($(next).prop('id') == $('#toolbar').prop('id'))
      return;
            
    swapCategoryDivs(current, next);
            
  })
}
function enableButtons(layerType){
  if(layerType == 'AREA')
    activateButtons();
  else
    deActivateButtons();
}


function activateButtons(){
  $('#groupButton').css('disabled',false)
  $('#upButton').css('disabled',false)
  $('#downButton').css('disabled',false)
  
  $('#groupButton').css('color','black')
  $('#upButton').css('color','black')
  $('#downButton').css('color','black')
}

function deActivateButtons(){
  
  $('#groupButton').css('disabled',true)
  $('#upButton').css('disabled',true)
  $('#downButton').css('disabled',true)
  
  $('#groupButton').css('color','white')
  $('#upButton').css('color','white')
  $('#downButton').css('color','white')
}