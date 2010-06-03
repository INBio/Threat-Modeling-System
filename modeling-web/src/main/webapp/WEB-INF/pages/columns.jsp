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
			<p class="titulo-principal"><fmt:message key="title.columns"/></p>

			<spring:hasBindErrors name="columnsForm">
				<div class="errors">
					<h3><fmt:message key="errors.title"/></h3>
					<p>
						<c:forEach items="${errors.allErrors}" var="error">
							<fmt:message key="${error.code}" />
						</c:forEach>
					</p>
				</div>
			</spring:hasBindErrors>

			<div id="columns">
				<form:form action="columns.html" id="columnsForm" commandName="columnsForm">
					<table width="42%" border="0" align="center" cellpadding="4" cellspacing="1" class="tabla-contenido">
						<tr class="celda02">
							<td width="24%"><span class="textosnegrita"> <fmt:message key="common.layers"/> </span> </td>
							<td width="35%" class="textosnegrita"><fmt:message key="common.weight"/> </td>
							<td width="41%"><span class="textosnegrita"><fmt:message key="interval.column"/></span> </td>
						</tr>
						<tr>
							<c:forEach items="${columnsForm.layers}" var="layer"  varStatus="current">
								<form:hidden  path="layers[${current.index}].name" />
								<form:hidden path="layers[${current.index}].weight"/>
								<form:hidden path="layers[${current.index}].type"/>
							<tr class="celda01">
								<td>
									<span class="textos">
										<c:out value="${layer.name}" />
									</span>
								</td>
								<td class="textos"><div align="center">
										<c:out value="${layer.weight}" />
									</div>
								</td>
								<td class="textos">
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
						</tr>
						<tr class="celda01">
							<td colspan="3">
								<div align="center">
									<input id="submitButton" type="submit" class="modeling_btn" value='<fmt:message key="layer.nextStep"/>' />
								</div>
							</td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<div id="footer">
			<jsp:include page="/common/footer.jsp"/>
		</div>
	</body>
</html>
