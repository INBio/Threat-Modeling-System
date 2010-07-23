<%-- 
    Document   : showResultingMap
    Created on : 24/03/2010, 08:59:36 AM
    Author     : asanabria
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/common/taglibs" %>

<html>
    <head>
        <%@ include file="/common/theme" %>
        <%@ include file="/common/javascript" %>
    </head>
    <body onload="checkSize()">
        <div id="Header">
            <!-- Header -->
            <jsp:include page="/common/header.jsp"/>
        </div>
        <div id="contenido" style="overflow: auto; width: 100%">

            <spring:hasBindErrors name="intervalsForm">
                <div class="errors">
                    <h3><fmt:message key="errors.title"/></h3>
                    <p>
                        <c:forEach items="${errors.allErrors}" var="error">
                            <fmt:message key="${error.code}" />
                        </c:forEach>
                    </p>
                </div>
            </spring:hasBindErrors>
            <div id="results" >
                <div id="categoryInfo">
                    <table border="2" class="tabla-contenido">
                        <tr class="celda02"  >
                            <td colspan="2" style="width:350px; font-weight:bold;max-width: 350px; overflow: hidden;"><fmt:message key="showMap.leyend" /></td>
                        </tr>
                        <tr class="celda01"  >
                            <td width="50%" align="left"><span class="textosnegrita"><fmt:message key="showMap.resolution" /></span></td>
                            <td align="center"><span class="textos"><c:out value="${fullSessionInfo.resolution}" /></span></td>
                        </tr>
                    </table>
                    <c:forEach items="${fullSessionInfo.layerList}" var="layer" >
                        <br />
                        <table border="5" class="tabla-contenido">
                            <tr class="celda02">
                                <td colspan="2" style="width:350px; font-weight:bold;max-width: 350px; overflow: hidden;"><c:out value="${layer.name}" /></td>
                            </tr>
                            <tr class="celda02">
                            <span class="textosnegrita">
                                <td class="textos" colspan="2" style="font-style:italic;">
                                    <c:choose>
                                        <c:when test="${'AREA' eq layer.type}" >
                                            <fmt:message key="showMap.categories" />
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="showMap.intervals" />
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </span>
                            </tr>
                            <c:forEach items="${layer.categories}" var="category">
                                <c:if test="${category ne null}">
                                    <c:choose>

                                        <c:when test="${'AREA' eq layer.type}" >
                                            <tr class="celda01">
                                                <td style="max-width: 170px; overflow:hidden">
                                                    <span class="textos"><c:out value="${category.value}" /></span>
                                                </td>
                                                <td style="max-width: 170px; overflow:hidden">
                                                    <span class="textos"><c:out value="${category.description}" /></span>
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr class="celda01" style="text-align:center;">
                                                <td style="max-width: 170px; overflow:hidden" colspan="2">
                                                    <span class="textos" ><c:out value="${category.value}" /></span>
                                                </td>
                                            </tr>
                                        </c:otherwise>

                                    </c:choose>
                                </c:if>
                            </c:forEach>
                        </table>
                    </c:forEach>
                </div>
                <div id="mapResults" >
                    <table  style="height:450px">
                        <tr>
                            <td class="mapTable">
                                <div id="map" >
                                    <img alt="<fmt:message key='maps.resultingMap' />" src="/resmaps/${fullSessionInfo.imageName}_T_${fullSessionInfo.userSessionId}.png" />
                                </div>
                            </td>
                            <td class="threat_image_text">
                                <fmt:message key="showMap.highThreat" />
                                <br />
                                <div id="scale_image" ></div>
                                <fmt:message key="showMap.lowThreat" />
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div id="footer">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </body>
</html>

