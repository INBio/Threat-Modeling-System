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
 * When a node is expanded, the children nodes are retrived via XHTML Request
 * Then those children are drown in the indicators tree
 * Note: This function is based on YUI Tree example from YUI Library
 */
function loadNodeData(node, fnLoadComplete)  {

    //Getting the label of the selected node
    var nodeId = encodeURI(node.data);

    //Prepare URL for XHR request:
    var sUrl = "/modeling-web/ajax/nodeInfo?query=" + nodeId;

    //Prepare our callback object
    var callback = {

        //if our XHR call is successful, we want to make use
        //of the returned data and create child nodes.
        success: function(oResponse) {
            //root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;
            //child node (nodes)
            var rootChildNodes = xmlDoc.getElementsByTagName("node");
            //indicate that the node is a leaf
            if(rootChildNodes.length==0){
                node.isLeaf = true;
                isLeaf = "true";
            }
            else{
                isLeaf = "false";
            }
            //Loop over children
            for(var i=0; i< rootChildNodes.length; i++){
                 var basicElement = rootChildNodes[i];  //node
                 var id =  basicElement.getElementsByTagName("id")[0].childNodes[0].nodeValue; //id
                 var name =  basicElement.getElementsByTagName("name")[0].childNodes[0].nodeValue; //name

                 var tempNode = new YAHOO.widget.TextNode(name, node, false);
                 tempNode.data = id;
            }

            //When we're done creating child nodes, we execute the node's
            //loadComplete callback method which comes in via the argument
            //in the response object (we could also access it at node.loadComplete,
            //if necessary):
            oResponse.argument.fnLoadComplete();

        },

        //if our XHR call is not successful, we want to
        //fire the TreeView callback and let the Tree
        //proceed with its business.
        failure: function(oResponse) {
            YAHOO.log("Failed to process XHR transaction.", "info", "example");
            oResponse.argument.fnLoadComplete();
        },

        //our handlers for the XHR response will need the same
        //argument information we got to loadNodeData, so
        //we'll pass those along:
        argument: {
            "node": node,
            "fnLoadComplete": fnLoadComplete
        },

        //timeout -- if more than 7 seconds go by, we'll abort
        //the transaction and assume there are no children:
        timeout: 7000
    };

    //With our callback object ready, it's now time to
    //make our XHR call using Connection Manager's
    //asyncRequest method:
    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
}

/*
 * Initialazing the indicators tree
 */
function initIndicators(){
    //Creating the tree
    tree = new YAHOO.widget.TreeView("treeDiv");
    //Turn dynamic loading on for entire tree
    tree.setDynamicLoad(loadNodeData, currentIconMode);
    //Getting a reference to the root element
    rootNode = tree.getRoot();
    //Add the root element
    var tempNode = new YAHOO.widget.TextNode('Atributos Taxonómicos', rootNode, false);
    tempNode.data = 0;
    //Render the tree
    tree.draw();
    //Suscribing the event to get the selected node
    tree.subscribe('clickEvent',function(oArgs) {
        selectedNodeId = oArgs.node.data;
        selectedNodeName = oArgs.node.label;
        if(oArgs.node.isLeaf){
            isLeaf = "true";
        }
        else{
            isLeaf = "false";
        }
    });
}