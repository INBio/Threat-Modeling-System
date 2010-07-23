<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/common/taglibs" %>

<html>
	<head>
		<%@ include file="/common/theme" %>
		<%@ include file="/common/javascript" %>
	</head>
    <body onload="checkSize()">
		<div id="Header">
			<!-- Header -->
			<jsp:include page="/common/header.jsp"/>
		</div>
		<div id="contenido">
			<h2><fmt:message key="common.about"/></h2>
			<p><fmt:message key="common.aboutContent"/></p>
		</div>
		<div id="footer">
			<jsp:include page="/common/footer.jsp"/>
		</div>
	</body>
</html>
