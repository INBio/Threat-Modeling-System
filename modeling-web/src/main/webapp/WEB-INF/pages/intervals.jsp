<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="common/taglibs" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="common/theme" %>
		<%@ include file="common/javascript" %>
		<script  type="text/javascript">
			//<!--

			function edit(radio){

				var radios = document.getElementsByName("rbEditing");
				var rad = undefined;

				for(var item in radios){
					rad = radios[item];
					if(rad.checked != true){
						document.getElementById(rad.id+"_cats").style.display="none";
					}else{
						document.getElementById(rad.id+"_cats").style.display="";
					}
				}
			}

			function addCategory(){

				var radio = undefined;
				var radios = document.getElementsByName("rbEditing");
				var categories = undefined;
				var currentLayer = undefined;
				var currentCategory = undefined;

				for(var item in radios){
					radio = radios[item];
					if(radio.checked == true)
						break;
				}
				categories = document.getElementById(radio.id+"_cats");
				currentCategory = categories.childElementCount;
				currentLayer = radio.value;

				categories.innerHTML = categories.innerHTML+
					"<div><input type='checkbox' name='"+radio.id+"'/>&nbsp;" +
					"<input  type='text' name='layers["+currentLayer+"].categories["+currentCategory+"].value' value='0'  style='width: 150px' />&nbsp;"+
					"<input style='width: 150px' name='layers["+currentLayer+"].categories["+currentCategory+"].description' value='Category "+(currentCategory+1)+"' /><br /></div>";
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
			<div id="intervalsTitle">
				<h1><fmt:message key="title.intervals"/></h1>
				<font color="red">
					<b><c:out value="${status.errorMessage}"/></b>
				</font>
			</div>
			<div id="intervalsFrame" >
				<div id="intervalsForm">
					<form:form method="post" action="showResultingMap.html" commandName="currentInfo">
						<c:forEach items="${currentInfo.layers}" var="layer"  varStatus="current">
							<input name="rbEditing"
								   type="radio"
								   id="${layer.name}"
								   onclick="edit(this)"
								   value="${current.index}" />

							<label id="lbEditing">
								<c:out value="${layer.name}" />
							</label>
							<fmt:message key="common.weight"/>:&nbsp;<c:out value="${layer.weight}" />
							<div id="${layer.name}_cats" >
								<c:forEach items="${layer.categories}" var="category"  varStatus="currentCategory">
									<div id="category_${currentCategory.index}">
										<input type="checkbox" name="${layer.name}"/>
										<form:input path="layers[${current.index}].categories[${currentCategory.index}].value" />
										<form:input path="layers[${current.index}].categories[${currentCategory.index}].description" />
										<br />
									</div>
								</c:forEach>
							</div>
						</c:forEach>
							<div id="buttons">
								<input id="submitButton" type="submit" value='<fmt:message key="common.acceptChanges"/>' />
								<input id="joinButton" type="button" onclick="deleteCategory();" value='<fmt:message key="interval.deleteCategory"/>' />
								<input id="addButton" type="button" onclick="addCategory();" value='<fmt:message key="interval.addCategory"/>' />
							</div>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>