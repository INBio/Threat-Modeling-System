<%-- 
    Document   : showResultingMap
    Created on : 24/03/2010, 08:59:36 AM
    Author     : asanabria
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/common/taglibs" %>

<html>
	<head>
		<%@ include file="/common/theme" %>
		<%@ include file="/common/javascript" %>
	</head>
	<body>
		<div id="Header">
			<!-- Header -->
			<jsp:include page="/common/header.jsp"/>
		</div>
		<div id="contenido">

			<form:errors path="*" />

			<div id="map">
				<img alt="<fmt:message key='maps.resultingMap' />" src="/resmaps/R_${fullSessionInfo.imageName}_${fullSessionInfo.userSessionId}_r.png" />
			</div>
			<div id="generalInfo">
				<table>
					<tbody>
						<!-- resolution
							 projection
							 layers:
									Categorías.
						  -->
						<tr>
							<td><fmt:message key="showMap.resolution" /></td>
							<td><c:out value="${fullSessionInfo.resolution}" /></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="categoryInfo">
				<table>
					<tbody>

						<c:forEach items="${fullSessionInfo.layerList}" var="layer" >
							<tr>
								<td><c:out value="${layer.name}" /><fmt:message key="showMap.categories" /></td>
							</tr>
							<tr>
								<td>valor</td>
								<td>Description</td>
							</tr>
							<c:forEach items="${layer.categories}" var="category">
								<tr>
									<td><c:out value="${category.value}" /></td>
									<td><c:out value="${category.description}" /></td>
								</tr>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>
