<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="taglibs" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="theme" %>
		<%@ include file="javascript" %>
	</head>
	<body>
		<div id="Header">
			<!-- Header -->
			<jsp:include page="header.jsp"/>
		</div>
		<div id="contenido">
			<h2><fmt:message key="common.about"/></h2>
			<p><fmt:message key="common.aboutContent"/></p>
		</div>
	</body>
</html>
