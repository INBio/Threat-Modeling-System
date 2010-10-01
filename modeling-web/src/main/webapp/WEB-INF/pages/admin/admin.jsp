<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@include file="/common/taglibs" %>

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
			<h2><fmt:message key="title.admin"/></h2>
			<div style="padding-left: 40%; text-align: left">
				<ol>
					<!--li><a href="listUsers.html">Usuarios</a></li-->
					<li><a href="listLayers.html">Capas</a></li>
					<li><a href="listUsers.html">Usuarios</a></li>
					<li><a href="resolution.html">Resoluci�n</a></li>
				</ol>
			</div>
		</div>
		<div id="footer">
			<jsp:include page="/common/footer.jsp"/>
		</div>
	</body>
</html>
