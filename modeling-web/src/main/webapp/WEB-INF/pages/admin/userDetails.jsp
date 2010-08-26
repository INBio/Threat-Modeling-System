<%-- 
    Document   : userdetails
    Created on : 19/05/2010, 09:55:45 AM
    Author     : asanabria
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/common/theme" %>
        <%@ include file="/common/javascript" %>
    </head>
    <body onload="checkSize()">
        <div id="Header">
            <jsp:include page="/common/header.jsp"/>
        </div>
        <div id="contenido">
            <spring:hasBindErrors name="userForm">
                <div class="errors">
                    <h3><fmt:message key="errors.title"/></h3>
                    <p>
                        <c:forEach items="${errors.allErrors}" var="error">
                            <fmt:message key="${error.code}" />
                        </c:forEach>
                    </p>
                </div>
            </spring:hasBindErrors>

            <div align="center">
                <h2><fmt:message key="user.userData" /></h2>
                <form:form commandName="userForm" action="updateUser.html">
                    <form:hidden path="userId"/>
                    <table width="50%">
                        <tr>
                            <td>Nombre de Usuario</td>
                            <td><form:input path="username"/></td>
                        </tr>
                        <tr>
                            <td>Nombre Completo</td>
                            <td><form:input path="fullname" /></td>
                        </tr>
                        <tr>
                            <td>Contraseña</td>
                            <td><form:password path="password1" /></td>
                        </tr>
                        <tr>
                            <td>Comprobar Contraseña</td>
                            <td><form:password path="password2" /></td>
                        </tr>
                        <tr>
                            <td>Habilitado</td>
                            <td><form:checkbox path="enabled" /></td>
                        </tr>
                        <tr>
                            <td>Administrador</td>
                            <td><form:checkbox path="admin" /></td>
                        </tr>
                        <tr>
                            <td>Usuario</td>
                            <td><form:checkbox path="user" /></td>
                        </tr>

                    </table>
                    <input class="button-simple" type="submit" value="<fmt:message key='common.save' />" />
                </form:form>
            </div>
        </div>
        <div id="footer">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </body>
</html>
