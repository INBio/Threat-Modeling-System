<%--
    Document   : intervals
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
			<div id="intervalsTitle">
				<p class="titulo-principal"><fmt:message key="title.intervals"/></p>
			</div>

			<spring:hasBindErrors name="intervalsForm">
				<div class="errors">
					<h3><fmt:message key="errors.title"/></h3>
					<p>
						<c:forEach items="${errors.allErrors}" var="error">
							<fmt:message key="${error.code}" />
						</c:forEach>
					</p>
				</div>
			</spring:hasBindErrors>

			<div id="intervalsForm">
				<form:form method="post" action="intervals.html" commandName="intervalsForm">
					<table width="35%" border="0" align="center" cellpadding="4" cellspacing="1" class="tabla-contenido">
						<tr class="celda02">
							<td colspan="2" width="54%">
								<span class="textosnegrita">
									<fmt:message key="layer.layerName" />
								</span>
							</td>
							<td width="46%">
								<span class="textosnegrita">
									<fmt:message key="common.reverted" />
								</span>
							</td>
						</tr>
						<c:forEach items="${intervalsForm.layers}" var="layer"  varStatus="current">
							<form:hidden path="layers[${current.index}].name" />
							<form:hidden path="layers[${current.index}].weight" />
							<form:hidden path="layers[${current.index}].type" />
							<tr class="celda02">
								<td  colspan="2" width="50%" style="text-align:  left">
									<span class="textos" style="text-align:  left">
										<input name="rbEditing"
											   type="radio"
											   id="${layer.name}"
											   onclick="edit(this)"
											   value="${current.index}" />

										<c:out value="${layer.name}" />
									</span>
								</td>
								<td style="text-align:  center" >
									<span class="textos">
										<c:if test="${'AREA' ne layer.type}" >
											<form:checkbox path="layers[${current.index}].reverted" />
										</c:if>
										&nbsp;
									</span>
								</td>
							</tr>
							<tr class="celda01">
								<td colspan="3">
									<span class="textos">
										<div id="${layer.name}_cats" style="display: none" class="${layer.type}">
											<span class="textosnegrita">
												<fmt:message key="interval.categories"/>
											</span>
											<br />
											<c:forEach items="${layer.categories}" var="category"  varStatus="currentCategory">
												<c:choose>
													<c:when test="${'AREA' eq layer.type}" >
														<div id="category_${currentCategory.index}" >
															<input type="checkbox" name="${layer.name}"/>
															<form:input cssClass="intervals_txt" path="layers[${current.index}].categories[${currentCategory.index}].value" />
															<form:input cssClass="intervals_txt" path="layers[${current.index}].categories[${currentCategory.index}].description" />
															<br />
														</div>
													</c:when>
													<c:otherwise>
														<div id="category_${currentCategory.index}">
															<input type="checkbox" name="${layer.name}"/>
															<form:input cssClass="intervals_txt" path="layers[${current.index}].categories[${currentCategory.index}].value" />
															<br />
														</div>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</div>
									</span>
								</td>
							</tr>
						</c:forEach>
							<tr class="celda01">
							<td>
								<input id="submitButton" type="submit" class="modeling_btn" value='<fmt:message key="layer.finalStep"/>' />
							</td>
							<td>
								<input id="joinButton" type="button" class="modeling_btn" onclick="deleteCategory();" value='<fmt:message key="interval.deleteCategory"/>' />
							</td>
							<td>
								<input id="addButton" type="button" class="modeling_btn" onclick="addCategory();" value='<fmt:message key="interval.addCategory"/>' />
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
