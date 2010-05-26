<%--
    Document   : columns
    Created on : 24/03/2010, 08:59:36 AM
    Author     : asanabria
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs" %>

<html>
	<head>
		<%@ include file="/common/theme" %>
		<%@ include file="/common/javascript" %>
	</head>
	<body>
		<div id="Header">
			<jsp:include page="/common/header.jsp"/>
		</div>

		<div id="contenido">
			<h2><fmt:message key="title.columns"/></h2>
			<font color="red">
				<b><c:out value="${status.errorMessage}"/></b>
			</font>
			<div id="columns">
				<form:form action="intervals.html" id="columnsForm" commandName="columnsForm">
					<table>
						<thead>
							<tr>
								<td> <fmt:message key="common.layers"/> </td>
								<td> <fmt:message key="common.weight"/> </td>
								<td> <fmt:message key="interval.column"/> </td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${columnsForm.layers}" var="layer"  varStatus="current">
								<tr>
									<td>
										<c:out value="${layer.name}" />
									</td>
									<td>
										<c:out value="${layer.weight}" />
									</td>
									<form:hidden  path="layers[${current.index}].name" />
									<form:hidden path="layers[${current.index}].weight"/>
									<form:hidden path="layers[${current.index}].type"/>
									<td>
										<c:choose>
											<c:when test="${layer.type eq 'AREA'}">
												<form:select path="layers[${current.index}].columns['selected']" multiple="false" >
													<c:forEach items="${layer.columns}" var="column">
														<form:option value="${column.key}:${column.value}">
															<c:out value="${column.key}" />
														</form:option>
													</c:forEach>
												</form:select>
											</c:when>
											<c:otherwise>
												<fmt:message key="common.notAreaLayerType" />
											</c:otherwise>
										</c:choose>
									</td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
					<input id="submitButton" type="submit" class="modeling_btn" value='<fmt:message key="common.acceptChanges"/>' />
				</form:form>
			</div>
		</div>
	</body>
</html>
