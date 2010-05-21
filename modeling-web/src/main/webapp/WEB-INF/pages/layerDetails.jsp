<%-- 
    Document   : userdetails
    Created on : 19/05/2010, 09:55:45 AM
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
			//-->
		</script>
	</head>
	<body>
		<div id="Header">
			<!-- Header -->
			<jsp:include page="common/header.jsp"/>
		</div>

		<div id="contenido">
			<form:form  id="layerForm" commandName="layerForm" action="updateLayer.html">
				<form:hidden path="id" />
				<form:input path="name" />
				<form:input path="uri" />
				<form:input size="4" path="year" />
				<form:input path="scale" />
				<form:textarea path="description" />
				<br />
				<input type="submit" value="<fmt:message key='common.save' />" />
			</form:form>
		</div>
	</body>
</html>
