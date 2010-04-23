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


			function edit(radio){

				var radios = document.getElementsByName("rbEditing");
				var rad = undefined;

				for(var item in radios){
					rad = radios[item];
					if(rad.checked != true){
						document.getElementById(rad.id+"_categories").style.display="none";
					}else{
						document.getElementById(rad.id+"_categories").style.display="";
					}
				}
			}

			function addCategory(){

				var radio = undefined;
				var radios = document.getElementsByName("rbEditing");
				var categories = undefined;

				for(var item in radios){
					radio = radios[item];
					if(radio.checked == true)
						break;
				}
				categories = document.getElementById(radio.id+"_categories");

				categories.innerHTML = categories.innerHTML+
					"<div><input type='checkbox' name='"+radio.id+"'/>&nbsp;" +
					"<input  type='text' id='' value='0'  style='width: 150px' />&nbsp;"+
					"<input style='width: 150px' /><br /></div>";
			}

			function deleteCategory(){

				var radio = undefined;
				var categories = undefined;

				var radios = document.getElementsByName("rbEditing");

				for(var item in radios){
					radio = radios[item];
					if(radio.checked == true)
						break;
				}

				categories = document.getElementsByName(radio.id);

				for(var ckb = categories.length-1; ckb >= 0; ckb--){
					check = categories[ckb];
					if(check.checked == true){
						check.parentNode.parentNode.removeChild(check.parentNode);
					}
				}
			}
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
			<table border="3" >
				<form:form action="showResultingMap.html" method="post">
					<c:forEach items="${layers}" var="layer"  varStatus="current">
						<tr>
							<td>
								<input name="rbEditing"
									   type="radio"
									   id="<c:out value='${layer.name}' />"
									   onchange="edit(this)"
									   value="<c:out value='${layer.name}' />" />
								<label id="lbEditing">
									<c:out value="${layer.name}" />
								</label>
							</td>
							<td colspan="2">
								<fmt:message key="common.weight"/>:&nbsp;<c:out value="${layer.weight}" />
							</td>
						</tr>
						<tr>

							<td  id="<c:out value='${layer.name}' />_categories" colspan="3" style="display: none">
								<c:forEach items="${layer.intervals}" var="interval"  varStatus="currentInterval">
									<div>
										<input type="checkbox" name="${layer.name}"/>
										<form:input path="layerList.[${current.index}].intevals[${currentInterval}].min" />
										<form:input path="layerList.[${current.index}].intevals[${currentInterval}].description" />
										<br />
									</div>
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td align="right">
							<input id="submitButton" type="button" onclick="send('intevalsForm');" value='<fmt:message key="common.acceptChanges"/>' />
						</td>
						<td align="right">
							<input id="joinButton" type="button" onclick="deleteCategory();" value='<fmt:message key="interval.deleteCategory"/>' />
						</td>
						<td align="right">
							<input id="addButton" type="button" onclick="addCategory();" value='<fmt:message key="interval.addCategory"/>' />
						</td>
					</tr>
				</form:form>
			</table>
		</div>
	</body>
</html>
