<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="common/taglibs" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="common/theme" %>
		<%@ include file="common/javascript" %>
		<script  type="text/javascript">
			//<!--

			function send(formName){

				document.forms[formName].submit();
				return true;
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
			<h1><fmt:message key="title.columns"/></h1>
			<font color="red">
				<b><c:out value="${status.errorMessage}"/></b>
			</font>
			<div id="contenido" >
				<div id="title">

				</div>
				<div id="columnsFrame">
					<div id="columnsTitle" >
						<fmt:message key="common.layers"/>
						<fmt:message key="common.weight"/>
						<fmt:message key="interval.column"/>
					</div>
					<div id="columnsList">
						<form:form action="intervals.html" id="columnsForm" commandName="currentStatus">
							<c:forEach items="${currentStatus.layers}" var="layer"  varStatus="current">
								<c:out value="${layer.name}" />
								<c:out value="${layer.weight}" />
								<form:hidden  path="layers[${current.index}].name" />
								<form:hidden path="layers[${current.index}].weight"/>
								<form:select path="layers[${current.index}].columns" multiple="false" >
									<c:forEach items="${layer.columns}"  var="column">
										<form:option  value="${column.key}:${column.value}">
											<c:out value="${column.key}" />&nbsp;&rArr;&nbsp;<c:out value="${column.value}" />
										</form:option>
									</c:forEach>
								</form:select>
							</c:forEach>
						</form:form>
					</div>
					<div id="buttons">
						<input id="submitButton" type="button" onclick="send('columnsForm');" value='<fmt:message key="common.acceptChanges"/>' />
					</div>
				</div>
			</div>
		</div>
	</body>
</html>