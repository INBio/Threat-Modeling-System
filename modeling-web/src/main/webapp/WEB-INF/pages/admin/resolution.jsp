<%--
    Document   : listUsers
    Created on : 19/05/2010, 09:56:00 AM
    Author     : asanabria
--%>
<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file="/common/taglibs" %>

<html>
    <head>
        <%@ include file="/common/theme" %>
        <script language="javascript" type="text/javascript">

            //Using to show the loading panel
            YAHOO.namespace("example.container");

            /*
             * Initialize a panel to show the descriptions
             */
            function initHelpPanel(){
                if (!YAHOO.example.help) {
                   YAHOO.example.help =
                        new YAHOO.widget.Panel("help",
                    {
                        width:"500px",
                        fixedcenter:true,
                        close:true,
                        draggable:true,
                        zindex:999,
                        modal:false,
                        visible:false
                    });
                }
            }

            //Show description panel
            function showPanel(title,description){
                YAHOO.example.help.setHeader(title);
                YAHOO.example.help.setBody("<p align='left'>"+description+"</p>");
                YAHOO.example.help.render(document.getElementById('help-box'));
                YAHOO.example.help.show();
            }

        </script>
    </head>
    <body onload="initHelpPanel();" class="yui-skin-sam" >
        <div id="Header">
            <jsp:include page="/common/header.jsp"/>
        </div>
        <div id="contenido" align="center">
            <h2><fmt:message key="title.resolution"/></h2>
            <br />
            <br />

            <div align="center">
                <form:form id="resolutionForm" commandName="resolutionForm" method="post" action="resolution.html" >
                    <div style="width: 50%" >
                        <table border="0px">
                            <tr>
                                <td>
                                    <fmt:message key="resolution.resolution" />:&nbsp;&nbsp;
                                </td>
                                <td>
                                    <form:input path="resolution" />&deg; = 1 m.
                                </td>
                                <td>
                                <div  class="link_help"  onclick="showPanel('<fmt:message key="help.resolution.title" />','<fmt:message key="resolution.insertEquivalentMeters" />')" >&nbsp;</div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br />
                    <div id="help-box" ></div>
                    <input type="submit" class="button-simple" value="<fmt:message key='common.save' />" />
                </form:form>
            </div>
        </div>
        <div id="footer">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </body>
</html>

