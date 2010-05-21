<%-- 
    Document   : listUsers
    Created on : 19/05/2010, 09:56:00 AM
    Author     : asanabria
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="common/taglibs" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="common/theme" %>
		<%@ include file="common/javascript" %>
		<script  type="text/javascript">
			//<!--

			function newUser(){

				var form = document.getElementById("userForm");
				form.action="newUser.html";
				form.submit();
			}

			function deleteUser(){
				var form = document.getElementById("userForm");
				form.action="deleteUser.html";
				form.submit();
			}

			function editUser(){
				var form = document.getElementById("userForm");
				form.action="editUser.html";
				form.submit();
			}

			//-->
		</script>
	</head>
	<body>
		<div id="Header">
			<!-- Header -->
			<jsp:include page="common/header.jsp"/>
		</div>

		<div id="contenido">
			<form:form  id="userForm" commandName="userForm" action="/listUsers.html">
				<c:forEach items="${users}" var="user">
					<form:radiobutton path="username" value="${user.username}" />
					<c:out value="${user.fullname}" />&nbsp;
					<c:out value="${user.username}" />&nbsp;
					<c:forEach items="${user.authorities}" var="authority">
						<c:choose>
							<c:when test="${authority.authority == 'ROLE_USER'}" >
								<fmt:message key="common.user" />
							</c:when>
							<c:otherwise>
								<fmt:message key="common.admin" />
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<br />
				</c:forEach>
					<input type="button" value="<fmt:message key='common.edit' />" onclick="editUser()"/>
					<input type="button" value="<fmt:message key='common.delete' />" onclick="deleteUser()"/>
					<input type="button" value="<fmt:message key='common.new' />" onclick="newUser()"/>

			</form:form>
		</div>
	</body>
</html>