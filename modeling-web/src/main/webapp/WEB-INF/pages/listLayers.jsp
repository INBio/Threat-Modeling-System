<%-- 
    Document   : listUsers
    Created on : 19/05/2010, 09:56:00 AM
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

			function newLayer(){

				var form = document.getElementById("layerForm");
				form.action="newLayer.html";
				form.submit();
			}

			function deleteLayer(){
				var form = document.getElementById("layerForm");
				form.action="deleteLayer.html";
				form.submit();
			}

			function editLayer(){
				var form = document.getElementById("layerForm");
				form.action="editLayer.html";
				form.submit();
			}

			//-->
		</script>
	</head>
	<body>
		<div id="Header">
			<!-- Header -->
			<jsp:include page="common/header.jsp"/>
		</div>

		<div id="contenido">
			<form:form  id="layerForm" commandName="layerForm" action="/listLayers.html">
				<c:forEach items="${layers}" var="layer">
					<form:radiobutton value="${layer.id}" path="id" />
					<c:out value="${layer.name}" />
					<c:out value="${layer.year}" />
					<c:out value="${layer.scale}" />
					<c:out value="${layer.description}" />
					<br />
				</c:forEach>
					<input type="button" value="<fmt:message key='common.edit' />" onclick="editLayer()"/>
					<input type="button" value="<fmt:message key='common.delete' />" onclick="deleteLayer()"/>
					<input type="button" value="<fmt:message key='common.new' />" onclick="newLayer()"/>

			</form:form>
		</div>
	</body>
</html>