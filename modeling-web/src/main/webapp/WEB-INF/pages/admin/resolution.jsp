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
        <%@ include file="/common/javascript" %>
    </head>
    <body onload="document.getElementById('userNameInput').focus();">
        <div id="Header">
            <jsp:include page="/common/header.jsp"/>
        </div>
        <div id="contenido" align="center">
            <h2><fmt:message key="title.resolution"/></h2>

            <div align="center">
                <form:form id="resolutionForm" commandName="resolutionForm" method="post" action="resolution.html" >
                    <fmt:message key="resolution.resolution" />
                    <form:input path="resolution" />
                    <fmt:message key="resolution.insertEquivalentMeters" />
                    <br />
                    <input type="submit" class="button-simple" value="<fmt:message key='common.save' />" />
                </form:form>
            </div>
        </div>
        <div id="footer">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </body>
</html>

