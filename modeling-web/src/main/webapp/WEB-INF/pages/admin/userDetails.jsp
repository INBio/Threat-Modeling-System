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
			<form:form commandName="userForm" action="updateUser.html">
                            <form:hidden path="userId"/>
                            <table align="center">
                                <tr>
                                    <td style="text-align:right" >Nombre de Usuario</td>
                                    <td><form:input path="username"/></td>
                                </tr>
                                <tr>
                                    <td style="text-align:right">Nombre Completo</td>
                                    <td><form:input path="fullname" /></td>
                                </tr>
                                <tr>
                                    <td style="text-align:right">Contraseña</td>
                                    <td><form:password path="password1" /></td>
                                </tr>
                                <tr>
                                    <td style="text-align:right">Comprobar Contraseña</td>
                                    <td><form:password path="password2" /></td>
                                </tr>
                                <tr>
                                    <td style="text-align:right">Habilitado</td>
                                    <td style="text-align:center"><form:checkbox path="enabled" /></td>
                                </tr>
                                <tr>
                                    <td style="text-align:right">Administrador</td>
                                    <td style="text-align:center"><form:checkbox path="admin" /></td>
                                </tr>
                                <tr>
                                    <td style="text-align:right">Usuario</td>
                                    <td style="text-align:center"><form:checkbox path="user" /></td>
                                </tr>

                            </table>
				<input type="submit" value="<fmt:message key='common.save' />" />
			</form:form>
		</div>
		<div id="footer">
			<jsp:include page="/common/footer.jsp"/>
		</div>
	</body>
</html>
