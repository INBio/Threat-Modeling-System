<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="common/taglibs" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="common/theme" %>
		<%@ include file="common/javascript" %>
	</head>
	<body>
		<div id="contenido">
			<h1><fmt:message key="welcome"/></h1>
			<table>
				<tr>
					<td>Nombre de la colecci&oacute;n</td>
				</tr>
				<c:forEach items="${collections}" var="collection">
					<tr>
						<td><c:out value="${collection}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>
