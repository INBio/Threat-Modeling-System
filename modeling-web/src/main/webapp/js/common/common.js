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
function addLayerWMS(name, layer, uri) {

  var server = '';
  
  if(uri == '' ) 
    server = "http://216.75.53.105:80/geoserver_2_0_x/wms"
  else{
  
    var URI_parser = new RegExp('.[^?]+','g');
  
    server = URI_parser.exec(uri);
    server =String(server).replace('ows','wms');
  }
  
  return new OpenLayers.Layer.WMS( name, server,
  {
    layers: layer,
    transparent: "true",
    height: '478',
    width: '512'
  }, {
    isBaseLayer: false,
    singleTile: true, 
    ratio: 1, 
    opacity: 0.50, 
    'buffer': 0, 
    visibility: false
  });
}

/*
 * Function to create new Layers
 */
function addLayerWFS(name, serverNameLayer) {
  return new OpenLayers.Layer.WFS(
    name,
    'http://216.75.53.105:80/geoserver/wfs',
    {
      typename: serverNameLayer
    });
}

/***************** Cowboy code *************************/

function exportResult( type ){
  var outputType = document.getElementById('outputType');
  outputType.value = type;

  return true;
}


/* dialog */
function initHelpDialog(){
  $("#help-box").removeClass('loader');
    
  $('#help-box').dialog({
    autoOpen: false,
    show: "blind",
    hide: "explode",
    resizable: true,
    minWidth: 600,
    maxHeight: 400
  });
}

function showHelpDialog(){
  $( "#help-box" ).dialog("open");
}

function hideHelpDialog(){
  $( "#help-box" ).dialog("close");
}

function showPanel(title,description){
  initHelpDialog();
  $('#help-box').dialog('option', 'title', title);
  $('#help-box').prop('innerHTML', description);
  showHelpDialog();
}

function showWaitingDialog(){
  initHelpDialog();
  $('#help-box').dialog('option', 'title', waitingDialogTitle);
  $('#help-box').dialog('option', 'height', '100');
  $('#help-box').dialog('option', 'width', '100');
          
  $('#help-box').prop('innerHTML', '<div id="progress"></div>');
  
  $( "#progress" ).progressbar({
    value: 100
  });
  showHelpDialog();
}


function hideWaitingDialog(){
  hideHelpDialog();
}