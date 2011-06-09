<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@include file="/common/taglibs" %>

<html>
	<head>
		<%@ include file="/common/theme" %>
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
                    <li><a href="listLayers.html"><fmt:message key="common.layers" /></a></li>
					<li><a href="listUsers.html"><fmt:message key="common.users" /></a></li>
					<li><a href="resolution.html"><fmt:message key="common.resolution" /></a></li>
				</ol>
			</div>
		</div>
		<div id="footer">
			<jsp:include page="/common/footer.jsp"/>
		</div>
	</body>
</html>
