<%-- 
    Document   : userdetails
    Created on : 19/05/2010, 09:55:45 AM
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
                <h2><fmt:message key="layer.layerData" /></h2>
            <form:form  id="layerForm" commandName="layerForm" action="updateLayer.html">
                <form:hidden path="id" />
                <table width="50%" >
                    <tr >
                        <td>
                            <fmt:message key="layer.displayName" />
                        </td>
                        <td>
                            <form:input path="displayName" />
                        </td>
                    </tr>
                    <tr >
                        <td>
                            <fmt:message key="layer.layerName" />
                        </td>
                        <td>
                            <form:input path="name" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="layer.uri" />
                        </td>
                        <td>
                            <form:input path="uri" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="layer.source" />
                        </td>
                        <td>
                            <form:input path="source" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="layer.year" />
                        </td>
                        <td>
                            <form:input path="year" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="layer.vizScale" />
                        </td>
                        <td>
                            <form:input path="vizScale" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="layer.dataScale" />
                        </td>
                        <td>
                            <form:input path="dataScale" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="layer.generationProcedure" />
                        </td>
                        <td>
                            <form:textarea path="generationProcedure" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="layer.description" />
                        </td>
                        <td>
                            <form:textarea path="description" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="layer.speciesMap" />
                        </td>
                        <td>
                            <form:checkbox path="speciesMap" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input class="button-simple" type="submit" value="<fmt:message key='common.save' />" />

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
