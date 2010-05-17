<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="common/taglibs" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="common/theme" %>
		<%@ include file="common/javascript" %>

		<script  type="text/javascript">
			//<!--
			//-->
		</script>

	</head>
	<body>
		<div id="Header">
			<!-- Header -->
			<jsp:include page="common/header.jsp"/>
		</div>

		<div id="contenido">
			<h1><fmt:message key="title.admin"/></h1>
			<ol>
				<li><a href="users.html">Capas</a></li>
				<li><a href="layers.html">Administración</a></li>
			</ol>
		</div>
	</body>
</html>
