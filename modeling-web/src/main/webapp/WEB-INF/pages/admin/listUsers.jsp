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
			<h2><fmt:message key="title.users"/></h2>
			<div align="left" style="padding-left: 35%">
				<form:form  id="userForm" commandName="userForm" action="/listUsers.html">
					<table class="tabla-contenido">
						<c:forEach items="${users}" var="user">
							<tr class="celda01">
								<td>
									<form:radiobutton path="username" value="${user.username}" />
								</td>
								<td>
									<c:out value="${user.fullname}" />&nbsp;

								</td>
								<td>
									<c:out value="${user.username}" />&nbsp;

								</td>
								<td>
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

								</td>
							</tr>
						</c:forEach>
					</table>
					<br />
				</form:form>
			</div>

			<input type="button" value="<fmt:message key='common.edit' />" onclick="editUser()"/>
			<input type="button" value="<fmt:message key='common.delete' />" onclick="deleteUser()"/>
			<input type="button" value="<fmt:message key='common.new' />" onclick="newUser()"/>
		</div>
		<div id="footer">
			<jsp:include page="/common/footer.jsp"/>
		</div>
	</body>
</html>