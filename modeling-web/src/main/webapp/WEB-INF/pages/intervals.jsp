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
		<div id="contenido" style="height:350px;">
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
                        <div id="interaction" style="text-align:center; width:950px; margin:0 auto 0 auto">
                        <form:form method="post" action="intervals.html" commandName="intervalsForm">
                            <div id="intervalsForm" class="content_div">


                                        <div id="layer_names" class="content_div">

                                            <div id="headers">
                                                <div id="name" class="layer_names">
                                                    <span class="textosnegrita">
                                                        <fmt:message key="layer.layerName" />
                                                    </span>
                                                </div>
                                                <div id="inverse" class="layer_names">
                                                    <span class="textosnegrita">
                                                        <fmt:message key="common.reverted" />
                                                    </span>
                                                </div>
                                            </div>

                                            <div class="clear"></div>

                                            <div id="layers_content" >
                                                <c:forEach items="${intervalsForm.layers}" var="layer"  varStatus="current">
                                                    <form:hidden path="layers[${current.index}].name" />
                                                    <form:hidden path="layers[${current.index}].weight" />
                                                    <form:hidden path="layers[${current.index}].type" />
                                                    <div class="layer_names2">
                                                        <span class="textos" style="text-align:  left">
                                                            <c:choose>
                                                                <c:when test="${'AREA' eq layer.type}" >
                                                                    <input name="rbEditing"
                                                                           type="radio"
                                                                           id="${layer.name}"
                                                                           onclick="activateButtons();edit(this);"
                                                                           value="${current.index}" />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input name="rbEditing"
                                                                           type="radio"
                                                                           id="${layer.name}"
                                                                           onclick="deActivateButtons();edit(this);"
                                                                           value="${current.index}" />
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <c:out value="${layer.name}" />
                                                        </span>
                                                    </div>

                                                    <div class="layer_names">
                                                        <span class="textos">
                                                            <c:if test="${'AREA' ne layer.type}" >
                                                                <form:checkbox path="layers[${current.index}].reverted" onclick="inverseValues('${layer.name}')" />
                                                            </c:if>
                                                            &nbsp;
                                                        </span>
                                                    </div>
                                                    <div class="clear"></div>
                                                </c:forEach>
                                            </div>
                                        </div>

                            </div>
                            <div class="content_div" style="background-color:#E3FCBC; padding: 5px 5px 5px 5px">
                                <div style="text-align:center;">
                                    <span class="textosnegrita">
                                        <fmt:message key="interval.categories"/>
                                    </span>
                                </div><br />
                                <div id="categorys_layer" class="content_div_categorys">
                                    <c:forEach items="${intervalsForm.layers}" var="layer"  varStatus="current">
                                       
                                            <div id="${layer.name}_cats" style="display: none" class="${layer.type}">
                                                
                                                    <c:forEach items="${layer.categories}" var="category"  varStatus="currentCategory">
                                                        <c:choose>
                                                            <c:when test="${'AREA' eq layer.type}" >
                                                                <div id="category_${currentCategory.index}" >
                                                                    <input type="checkbox" name="${layer.name}"/>
                                                                    <form:input readonly="true" cssClass="intervals_txt" path="layers[${current.index}].categories[${currentCategory.index}].value" />
                                                                    <form:input readonly="true" cssClass="intervals_txt" path="layers[${current.index}].categories[${currentCategory.index}].description" />
                                                                    <br />
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div id="category_${currentCategory.index}">
                                                                    <input type="checkbox" name="${layer.name}"/>
                                                                    <form:input readonly="true" cssClass="intervals_txt" path="layers[${current.index}].categories[${currentCategory.index}].value" />
                                                                    <br />
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                            </div>
                                        
                                    </c:forEach>
                                </div>
                            </div>
                        
                            <div id="buttons" class="buttons">

                                <input id="topPriority" class="my_Button_up" onclick="topPriorityCategorys();"  type="button"><br />
                                <input id="lowPriority" class="my_Button_down" onclick="lowPriorityCategorys();"  type="button"><br /><br />


                                <input id="addButton" type="button" class="my_Button" onclick="addCategory();" value='<fmt:message key="interval.addCategory"/>' /><br />

                                <input id="groupButton" class="my_Button" onclick="groupCategorys();" value="<fmt:message key="interval.groupCategory"/>" type="button"><br />

                                <input id="joinButton" type="button" class="my_Button" onclick="deleteCategory();" value='<fmt:message key="interval.deleteCategory"/>' /><br />

                                <br /><br /><br /><br /><br /><br /><br />
                                <input id="submitButton" type="submit" class="my_Button" value='<fmt:message key="layer.finalStep"/>' /><br />
                                
                                

                                
                                

                            </div>
                                </form:form>
                        </div>
                </div>
                
                <div id="footer">
			<jsp:include page="/common/footer.jsp"/>
		</div>
	</body>
</html>
