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
	<body>
		<div id="Header">
			<jsp:include page="/common/header.jsp"/>
		</div>
		<div id="contenido">
			<form:form commandName="userForm" action="updateUser.html">
				<form:hidden path="userId"/>
				<form:input path="username"/>
				<form:input path="fullname" />
				<form:password path="password1" />
				<form:password path="password2" />
				<form:checkbox path="enabled" />
				<form:checkbox path="admin" />
				<form:checkbox path="user" />
				<input type="submit" value="<fmt:message key='common.save' />" />
			</form:form>
		</div>
		<div id="footer">
			<jsp:include page="/common/footer.jsp"/>
		</div>
	</body>
</html>
