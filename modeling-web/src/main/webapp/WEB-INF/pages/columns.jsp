<%--
    Document   : columns
    Created on : 24/03/2010, 08:59:36 AM
    Author     : asanabria
--%>

<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglibs" %>

<html>
    <head>
        <%@ include file="/common/theme" %>
        <%@ include file="/common/javascript" %>
        <script type="text/javascript">

            //Using to show the loading panel
            YAHOO.namespace("example.container");

            var loadingImage = "<img src='${pageContext.request.contextPath}/themes/default/images/ajax-loader.gif' ></img>";
            var loadingText = "<fmt:message key="common.loading"/>";

            /*
             * Initialize a panel to show the loading image
             */
            function initLoadingPanel(){
                if (!YAHOO.example.container.wait) {
                   YAHOO.example.container.wait =
                        new YAHOO.widget.Panel("wait",
                    {
                        width:"300px",
                        fixedcenter:true,
                        close:false,
                        draggable:false,
                        zindex:999,
                        modal:true,
                        visible:false
                    }
                );
                    YAHOO.example.container.wait.setHeader(loadingText);
                    YAHOO.example.container.wait.setBody(loadingImage);
                    YAHOO.example.container.wait.render(document.getElementById('contenido'));
                }
            }

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
            window.location = "${pageContext.request.contextPath}/columns.html#";
        </script>


    </head>
    <body onload="initHelpPanel();initLoadingPanel()" class="yui-skin-sam">
        <div id="Header">
            <jsp:include page="/common/header.jsp"/>
        </div>

        <div id="contenido">
            <p class="titulo-principal"><fmt:message key="title.columns"/></p>

            <div id="help-box" ></div>
            <spring:hasBindErrors name="columnsForm">
                <div class="errors">
                    <h3><fmt:message key="errors.title"/></h3>
                    <p>
                        <c:forEach items="${errors.allErrors}" var="error">
                            <fmt:message key="${error.code}" />
                        </c:forEach>
                    </p>
                </div>
            </spring:hasBindErrors>

            <div id="columns">
                <form:form action="columns.html" id="columnsForm" commandName="columnsForm">
                    <table width="60%" border="0" align="center" cellpadding="4" cellspacing="1" class="tabla-contenido">
                        <tr class="celda02">
                            <td  width="24%" style="text-align: left; "><span class="textosnegrita" style="text-align: left; font-size: 17px"><fmt:message key="common.layers"/></span></td>
                            <td  width="35%" style="text-align: left; "><span class="textosnegrita" style="text-align: left; font-size: 17px"><fmt:message key="common.weight"/></span></td>
                            <td width="41%" style="text-align: left; "><span class="textosnegrita" style="text-align: left; font-size: 17px"><fmt:message key="interval.column"/></span></td>
                        </tr>
                        <tr>
                            <c:forEach items="${columnsForm.layers}" var="layer"  varStatus="current">
                                <form:hidden  path="layers[${current.index}].name" />
                                <form:hidden  path="layers[${current.index}].displayName" />
                                <form:hidden path="layers[${current.index}].weight"/>
                                <form:hidden path="layers[${current.index}].type"/>
                            <tr class="celda01" style="height: 40px">
                                <td>
                                    <span class="textosnegrita">
                                        <c:out value="${layer.displayName}" />
                                    </span>
                                </td>
                                <td class="textos"><div align="center">
                                        <c:out value="${layer.weight}" />%
                                    </div>
                                </td>
                                <td class="textos">
                                    <c:choose>
                                        <c:when test="${layer.type eq 'AREA'}">
                                            <form:select path="layers[${current.index}].columns['selected']" multiple="false" >
                                                <c:forEach items="${layer.columns}" var="column">
                                                    <form:option value="${column.key}:${column.value}">
                                                        <c:out value="${column.key}" />
                                                    </form:option>
                                                </c:forEach>
                                            </form:select>
                                            <div  class="link_help"  onclick="showPanel('<fmt:message key="help.columnsArea.title" />','<fmt:message key="help.columnsArea.cont" />')" >&nbsp;</div>
                                        </c:when>
                                        <c:when test="${layer.type eq 'POINT'}">
                                            <fmt:message key="common.pointLayerType" />
                                            <div  class="link_help"  onclick="showPanel('<fmt:message key="help.columnsPoint.title" />','<fmt:message key="help.columnsPoint.cont" />')" >&nbsp;</div>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="common.lineLayerType" />
                                            <div  class="link_help"  onclick="showPanel('<fmt:message key="help.columnsLine.title" />','<fmt:message key="help.columnsLine.cont" />')" >&nbsp;</div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </c:forEach>
                        </tr>
                        </tr>
                        <tr class="celda02">
                            <td colspan="3">
                                <div align="center">
                                    <input id="submitButton" type="submit" class="button-simple" value='<fmt:message key="layer.nextStep"/>' onclick="YAHOO.example.container.wait.show();"  />
                                </div>
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>
        <div id="footer">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </body>
</html>
