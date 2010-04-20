<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="common/taglibs" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="common/theme" %>
		<%@ include file="common/javascript" %>
		<script  type="text/javascript">
			//<!--

			function calculateValues(){

				var selectedElements = document.getElementsByName("selectedLayers");
				var totalLabel = document.getElementById("totalImportanceValue");
				var submitButtom = document.getElementById("submitButton");

				var selectedValue =  undefined;
				var totalImportanceValue = 0;

				for each(var selected in selectedElements){
					if ( selected.checked == true ){

						selectedValue = document.getElementById("layer_"+selected.value);

						if(selected.value != undefined &&
								selectedValue.value != undefined){
							totalImportanceValue += (+selectedValue.value);
						}
					}
				}

				if(totalImportanceValue != 100 ){
					totalLabel.style.color = "red";
					totalLabel.style.fontSize = "24";
					submitButtom.disabled = true;

				}else{
					totalLabel.style.color = "black";
					totalLabel.style.fontSize = "16";
					submitButtom.disabled = false;
				}


				totalLabel.firstChild.nodeValue = totalImportanceValue;

				return;
			}

			function setValueToZero(selected){

				var selectedValue = document.getElementById("layer_"+selected.value);

				if(selected.checked == true){
					selectedValue.disabled = false;
					selectedValue.value = 0;
				}else{
					selectedValue.disabled = true;
					selectedValue.value = "";
				}

				calculateValues();

				return;
			}

			function send(){

				document.forms["layersForm"].submit();
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
			<h1><fmt:message key="title.layer"/></h1>
			<font color="red">
				<b><c:out value="${status.errorMessage}"/></b>
			</font>
			<form method="post" action="columns.html" id="layersForm">
				<table>
					<tr>
						<td><fmt:message key="common.resolution"/></td>
						<td colspan="2">
							<input id="resolution" name="resolution" type="text" />
							<br />
							<fmt:message key="layer.resolutionDecimalDegrees" />
						</td>
					</tr>
					<tr>
						<td><fmt:message key="common.selected"/></td>
						<td><fmt:message key="layer.layerName"/></td>
						<td><fmt:message key="layer.importanceValue"/></td>
					</tr>
					<c:forEach items="${layers}" var="layer"  varStatus="current">
						<tr>
							<td>
								<input type="checkbox" name="selectedLayers" value="${layer.name}" onclick="setValueToZero(this);"/>
							</td>
							<td>
								<c:out value="${layer.name}" />
							</td>
							<td>
								<input  type="text" disabled="true" maxlength="2" id="layer_${layer.name}" name="selectedValues" onkeyup="calculateValues();">
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="1" align="left">
							<fmt:message key="layer.importanceValueTotal"/>
							<label id="totalImportanceValue" style="font-size: xx-large; color: red" title="<fmt:message key="layer.importanceValueHint"/>" >
								0
							</label>
								%

						</td>
						<td colspan="2" align="right">
							<input id="submitButton" type="button" onclick="calculateValues();send();" value='<fmt:message key="layer.importanceValue"/>' />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
