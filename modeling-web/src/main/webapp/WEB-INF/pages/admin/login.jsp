<%--
    Document   : listUsers
    Created on : 19/05/2010, 09:56:00 AM
    Author     : asanabria
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
		<div id="contenido">
			<h2><fmt:message key="title.login"/></h2>

			<form method="post" accept-charset="UTF-8" action="j_spring_security_check">

				<c:if test="${not empty param.error}">
					<font color="red">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</font>
				</c:if>

				<%--UserName --%>
				<label>
					<b><fmt:message key="login.username"/>:</b>
					<input type="text" id="userNameInput" name="j_username" />
				</label>
				<br/>

				<%--Password --%>
				<label>
					<b><fmt:message key="login.password"/>:</b>
					<input type="password" name="j_password"/>
				</label>
				<br/>

				<input type="submit" value="<fmt:message key="common.accept"/>" />
				<input type="reset" name="reset"  value="<fmt:message key='common.reset'/>" />

			</form>

		</div>
	</body>
</html>

