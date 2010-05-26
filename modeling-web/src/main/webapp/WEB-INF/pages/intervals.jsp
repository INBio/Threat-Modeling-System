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
				<h2><fmt:message key="title.intervals"/></h2>
				<font color="red">
					<b><c:out value="${status.errorMessage}"/></b>
				</font>
			</div>
			<div id="intervalsFrame" >
				<div id="intervalsForm">
					<form:form method="post" action="showResultingMap.html" commandName="intervalsInfo">
						<c:forEach items="${intervalsInfo.layers}" var="layer"  varStatus="current">
							<form:hidden path="layers[${current.index}].name" />
							<form:hidden path="layers[${current.index}].weight" />
							<form:hidden path="layers[${current.index}].type" />

							<table class="interval_tbl">
								<tbody>
									<tr>
										<td width="50%">
											<input name="rbEditing"
												   type="radio"
												   id="${layer.name}"
												   onclick="edit(this)"
												   value="${current.index}" />

											<c:out value="${layer.name}" />

										</td>
										<td>
											<c:if test="${'AREA' ne layer.type}" >
												<form:checkbox path="layers[${current.index}].reverted" />
												<fmt:message key="common.reverted" />
											</c:if>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div id="${layer.name}_cats" style="display: none" class="${layer.type}">
												<hr />
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
										</td>
									</tr>
								</tbody>
							</table>
						</c:forEach>
						<div id="buttons">
							<input id="submitButton" type="submit" class="modeling_btn" value='<fmt:message key="common.acceptChanges"/>' />
							<input id="joinButton" type="button" class="modeling_btn" onclick="deleteCategory();" value='<fmt:message key="interval.deleteCategory"/>' />
							<input id="addButton" type="button" class="modeling_btn" onclick="addCategory();" value='<fmt:message key="interval.addCategory"/>' />
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>
