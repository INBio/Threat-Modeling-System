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
    <body onload="checkSize()">
        <div id="Header">
            <jsp:include page="/common/header.jsp"/>
        </div>
        <div id="contenido">
            <div align="center">
                <form:form  id="layerForm" commandName="layerForm" action="/listLayers.html">
                    <table id="layerListTable" cellspacing="0" cellpadding="3px" >
                        <tr style="font-weight: bold; width: auto; ">
                                <td class="layerListHeader" width="4%">
                                    &nbsp;
                                </td>
                                <td class="layerListHeader" width="30%" style="" >
                                    <fmt:message key="layer.displayName" />
                                </td>
                                <td class="layerListHeader" >
                                    <fmt:message key="layer.description" />
                                </td>
                            </tr>
                        <c:forEach items="${layers}" var="layer">
                            <tr>
                                <td>
                                    <form:radiobutton value="${layer.id}" path="id" /> &nbsp;
                                </td>
                                <td>
                                    <c:out value="${layer.displayName}" />&nbsp;
                                </td>
                                <td>
                                    <c:out value="${layer.description}" />&nbsp;
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </form:form>
                <input class="button-simple" type="button" value="<fmt:message key='common.edit' />" onclick="editLayer()"/>
                <input class="button-simple" type="button" value="<fmt:message key='common.delete' />" onclick="deleteLayer()"/>
                <input class="button-simple" type="button" value="<fmt:message key='common.new' />" onclick="newLayer()"/>
            </div>
        </div>
        <div id="footer">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </body>
</html>