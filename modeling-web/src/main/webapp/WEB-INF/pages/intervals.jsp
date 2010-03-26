<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="common/taglibs" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="common/theme" %>
		<%@ include file="common/javascript" %>
		<script  type="text/javascript">
			//<!--

			function send(){

				document.forms["intervalsForm"].submit();
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
			<h1><fmt:message key="title.intervals"/></h1>
			<font color="red">
				<b><c:out value="${status.errorMessage}"/></b>
			</font>
			<table>
				<tr>
					<td><fmt:message key="common.layers"/></td>
				</tr>
				<form method="post" action="showResultingMap.html" id="intevalsForm">
					<c:forEach items="${layers}" var="layer"  varStatus="current">
						<tr>
							<td>
								<c:out value="${layer.name}" />
							</td>
							<td>
								<c:out value="${layer.weight}" />
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td align="right">
							<input id="submitButton" type="button" onclick="send();" value='<fmt:message key="common.acceptChanges"/>' />
						</td>
						<td>
							<input id="submitTesing" type="submit" onclick="send();" value='Submit' />
						</td>
					</tr>
				</form>
			</table>
		</div>
	</body>
</html>
