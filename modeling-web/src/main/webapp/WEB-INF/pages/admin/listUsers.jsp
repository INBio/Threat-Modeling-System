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
                      <div style="width:700px; margin: 10px auto 10px auto">
                        
                        <div style="height: 350px; overflow: auto; width: 600px; float: left" >
				<form:form  id="userForm" commandName="userForm" action="/listUsers.html">
					<table  border="5" class="tabla-contenido">
                                                    <tr class="celda02">
								<td>

								</td>
                                                                <td style="width:300px;overflow:hidden">
                                                                    <label class="header-text"> <fmt:message key="user.fullname" /> </label>

								</td>
                                                                <td style="width:100px;overflow:hidden">
                                                                    <label class="header-text"> <fmt:message key="user.username" /> </label>

								</td>
								<td style="width:100px;overflow:hidden">
                                                                    <label class="header-text"> <fmt:message key="user.rol" /> </label>

								</td>
							</tr>
						<c:forEach items="${users}" var="user">
							<tr class="celda01">
								<td>
                                                                    <label class="normal-text"> <form:radiobutton path="username" value="${user.username}" /> </label>
								</td>
								<td style="width:300px;overflow:hidden">
                                                                    <label style="width:200px;overflow:hidden;display:block" class="normal-text"> <c:out value="${user.fullname}" />&nbsp;</label>

								</td>
								<td style="width:100px;overflow:hidden">
                                                                    <label style="width:100px;overflow:hidden;display:block" class="normal-text"> <c:out value="${user.username}" />&nbsp; </label>

								</td>
								<td style="width:100px;overflow:hidden">
                                                                    <label  style="width:100px;overflow:hidden;display:block" class="normal-text">
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
                                                                    </label>
								</td>
							</tr>
						</c:forEach>
					</table>
					<br />
				</form:form>
			</div>

                        <div class="buttons">
                            <input class="button-simple" type="button" value="<fmt:message key='common.new' />" onclick="newUser()"/><br />
                            <input class="button-simple" type="button" value="<fmt:message key='common.edit' />" onclick="editUser()"/><br />
                            <input class="button-simple" type="button" value="<fmt:message key='common.delete' />" onclick="deleteUser()"/>
                        </div>

			<div class="clear">
                    </div>
                </div>
		<div id="footer">
			<jsp:include page="/common/footer.jsp"/>
		</div>
	</body>
</html>