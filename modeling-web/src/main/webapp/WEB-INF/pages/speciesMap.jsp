<%--
    Document   : intervals
    Created on : 24/03/2010, 08:59:36 AM
    Author     : asanabria
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs" %>

<html>
    <head>
        <%@ include file="/common/theme" %>
        <%@ include file="/common/javascript" %>
    </head>
    <body onload="checkSize()">
        <div id="Header">
            <jsp:include page="/common/header.jsp"/>
        </div>
        <div id="contenido">
            <div id="limitTitle">
                <p class="titulo-principal"><fmt:message key="title.limit"/></p>
            </div>

            <spring:hasBindErrors name="limitForm">
                <div class="errors">
                    <h3><fmt:message key="errors.title"/></h3>
                    <p>
                        <c:forEach items="${errors.allErrors}" var="error">
                            <fmt:message key="${error.code}" />
                        </c:forEach>
                    </p>
                </div>
            </spring:hasBindErrors>

            <div id="limitForm">
                <form:form method="post" action="chooseSpeciesMap.html" commandName="chooseSpeciesMapForm">
                    <table width="35%" border="0" align="center" cellpadding="4" cellspacing="1" class="tabla-contenido">
                        <tr class="celda02">
                            <td>
                                <span class="textosnegrita">
                                    <fmt:message key="layer.layerName" />
                                </span>
                            </td>
                            <td>
                                <form:select path="selectedLayerName">
                                    <form:options items="${chooseSpeciesMapForm.layers}" itemValue="uri" itemLabel="name" />
                                </form:select>
                            </td>
                        </tr>
                        <tr class="celda02">
                            <td colspan="2">
                                <input id="submitButton" type="submit" class="modeling_btn" value='<fmt:message key="layer.nextStep"/>' />
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
