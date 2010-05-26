<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs" %>

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
			<h2><fmt:message key="title.layer"/></h2>
			<font color="red">
				<b><c:out value="${status.errorMessage}"/></b>
			</font>
			<div id="formXD">
				<form:form id="layersForm" commandName="layersForm" method="post" action="columns.html" >
					<div id="layerListTitle">
					</div>
					<div id="layerFrame">
						<div id="resolution">
							<fmt:message key="common.resolution"/>
							<form:input title="" path="resolution" />
							<fmt:message key="layer.resolutionDecimalDegrees" />
						</div>
						<div id="layers" >
							<table>
								<thead>
									<tr>
										<td> <fmt:message key="layer.layerName"/> </td>
										<td> <fmt:message key="layer.importanceValue"/> </td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${layersForm.layerList}" var="layer"  varStatus="current">
										<form:hidden path="layerList[${current.index}].name" />
										<form:hidden path="layerList[${current.index}].uri" />
										<tr>
											<div class="${layer.name}">
												<td>
													<form:checkbox id="${layer.name}" path="layerList[${current.index}].selected" onclick="setValueToZero(this);" />
													<c:out value="${layer.name}" />
												</td>
												<td>
													<form:input cssStyle="align: left;" disabled="true" id="${layer.name}_weight"  path="layerList[${current.index}].weight" maxlength="2" onkeyup="calculateValues(this);" />
												</td>
											</div>
										</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<div id="importance">
							<fmt:message key="layer.importanceValueTotal"/>
							<label id="totalImportanceValue" title="<fmt:message key="layer.importanceValueHint"/>" >
								0
							</label>
								%
						</div>
						<input class="modeling_btn" id="submitButton" disabled="true" type="submit" value='<fmt:message key="layer.importanceValue"/>' />
					</div>
				</form:form>
			</div>
		</div>
	</body>
</html>
