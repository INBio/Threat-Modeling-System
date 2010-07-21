<%-- 
    Document   : listUsers
    Created on : 19/05/2010, 09:56:00 AM
    Author     : asanabria
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs" %>

<html>
    <head>
        <%@ include file="/common/theme" %>
        <%@ include file="/common/javascript" %>
    </head>
    <body>
        <div id="Header">
            <jsp:include page="/common/header.jsp"/>
        </div>
        <div id="contenido">
            <div align="center">
                <form:form  id="layerForm" commandName="layerForm" action="/listLayers.html">
                    <table width="60%" style="border: 1px">
                            <tr style="font-weight: bold">
                                <td width="5%">
                                    &nbsp;
                                </td>
                                <td width="40%">
                                    <fmt:message key="layer.layerName" />
                                </td>
                                <td width="5%">
                                    <fmt:message key="layer.year" />
                                </td>
                                <td width="15%">
                                    <fmt:message key="layer.scale"/>
                                </td>
                                <td width="35%">
                                    <fmt:message key="layer.description" />
                                </td>
                            </tr>
                        <c:forEach items="${layers}" var="layer">
                            <tr  >
                                <td>
                                    <form:radiobutton value="${layer.id}" path="id" /> &nbsp;
                                </td>
                                <td>
                                    <c:out value="${layer.name}" />&nbsp;
                                </td>
                                <td>
                                    <c:out value="${layer.year}" />&nbsp;
                                </td>
                                <td>
                                    <c:out value="${layer.scale}" />&nbsp;
                                </td>
                                <td>
                                    <c:out value="${layer.description}" />&nbsp;
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </form:form>
                <input type="button" value="<fmt:message key='common.edit' />" onclick="editLayer()"/>
                <input type="button" value="<fmt:message key='common.delete' />" onclick="deleteLayer()"/>
                <input type="button" value="<fmt:message key='common.new' />" onclick="newLayer()"/>
            </div>
        </div>
        <div id="footer">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </body>
</html>