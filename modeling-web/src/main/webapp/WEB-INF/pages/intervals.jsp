<%--
    Document   : intervals
    Created on : 24/03/2010, 08:59:36 AM
    Author     : asanabria
--%>

<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglibs" %>

<html>
    <head>
        <%@ include file="/common/theme" %>
        <%@ include file="/common/javascript" %>
        <script type="text/javascript">

//Using to show the loading panel
            YAHOO.namespace("example.container");

            var loadingImage = "<img src='${pageContext.request.contextPath}/themes/default/images/ajax-loader.gif' ></img>";
            var loadingText = "<fmt:message key="common.loading"/>";

            /*
             * Initialize a panel to show the loading image
             */
            function initLoadingPanel(){
                if (!YAHOO.example.container.wait) {
                   YAHOO.example.container.wait =
                        new YAHOO.widget.Panel("wait",
                    {
                        width:"300px",
                        fixedcenter:true,
                        close:false,
                        draggable:false,
                        zindex:999,
                        modal:true,
                        visible:false
                    }
                );
                    YAHOO.example.container.wait.setHeader(loadingText);
                    YAHOO.example.container.wait.setBody(loadingImage);
                    YAHOO.example.container.wait.render(document.getElementById('contenido'));
                }
            }

            /*
             * Initialize a panel to show the descriptions
             */
            function initHelpPanel(){
                if (!YAHOO.example.help) {
                   YAHOO.example.help =
                        new YAHOO.widget.Panel("help",
                    {
                        width:"500px",
                        fixedcenter:true,
                        close:true,
                        draggable:true,
                        zindex:999,
                        modal:false,
                        visible:false
                    });
                }
            }

            //Show description panel
            function showPanel(title,description){
                YAHOO.example.help.setHeader(title);
                YAHOO.example.help.setBody("<p align='left'>"+description+"</p>");
                YAHOO.example.help.render(document.getElementById('help-box'));
                YAHOO.example.help.show();
            }

            window.location = "${pageContext.request.contextPath}/intervals.html#";
        </script>
    </head>
    <body onload="initHelpPanel();initLoadingPanel()" class="yui-skin-sam">
        <div id="Header">
            <jsp:include page="/common/header.jsp"/>
        </div>
        <div id="contenido" style="width: 100%; height: 350px">
            <div id="intervalsTitle">
                <p class="titulo-principal"><fmt:message key="title.intervals"/></p>
            </div>

            <div id="help-box" ></div>
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
            <div id="interaction" style="text-align:center; width:1000px; margin:0 auto 0 auto">
                <form:form method="post" action="intervals.html" commandName="intervalsForm">
                    <div class="content_div">
                        <div id="layer_names" class="content_div">
                            <table >
                                <tr>
                                    <td colspan="1" width="5%">
                                        &nbsp;
                                    </td>
                                    <td colspan="1" width=" 60%">
                                        <div id="name" class="headers_label">
                                            <span class="textosnegrita">
                                                <fmt:message key="layer.displayName" />
                                            </span>
                                        </div>
                                    </td>
                                    <td colspan="1">
                                        <div id="inverse" class="headers_input">
                                            <span class="textosnegrita">
                                                <fmt:message key="common.reverted" />
                                            </span>
                                        </div>
                                    </td>
                                </tr>
                                <c:forEach items="${intervalsForm.layers}" var="layer"  varStatus="current">
                                    <form:hidden path="layers[${current.index}].name" />
                                    <form:hidden path="layers[${current.index}].displayName" />
                                    <form:hidden path="layers[${current.index}].weight" />
                                    <form:hidden path="layers[${current.index}].type" />
                                    <tr>
                                        <td width="5%">
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
                                        </td>
                                        <td>
                                            <div class="layer_names2">
                                                <span class="textos" style="text-align:  left">

                                                    <label><c:out value="${layer.displayName}" /></label>
                                                </span>
                                            </div>
                                        </td>
                                        <td width="">
                                            <div class="headers_input">
                                                <c:if test="${'LINE' eq layer.type}" >
                                                    <form:checkbox path="layers[${current.index}].reverted" onclick="inverseValues('${layer.name}')" />
                                                </c:if>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                    <div class="content_div" style="background-color:#E3FCBC; padding: 5px 5px 5px 5px">
                        <div id="categorys_layer" class="content_div_categorys">
                            <c:forEach items="${intervalsForm.layers}" var="layer"  varStatus="current">

                                <div id="${layer.name}_cats" style="display: none" class="${layer.type}">
                                    <c:choose>
                                        <c:when test="${'AREA' eq layer.type}" >
                                <div  class="link_help"  onclick="showPanel('<fmt:message key="help.intervalsArea.title" />','<fmt:message key="help.intervalsArea.cont" />')" >&nbsp;</div>
                                            <div id="title_${layer.name}" style="text-align:center;">
                                                <br />
                                                <span class="textosnegrita">
                                                    <fmt:message key="interval.categories"/>
                                                </span>
                                            </div><br />
                                        </c:when>
                                        <c:when test="${'LINE' eq layer.type}" >
                                <div  class="link_help"  onclick="showPanel('<fmt:message key="help.intervalsLine.title" />','<fmt:message key="help.intervalsLine.cont" />')" >&nbsp;</div>
                                            <div id="title_${layer.name}" style="text-align:center;">
                                                <br />
                                                <span class="textosnegrita">
                                                    <fmt:message key="showMap.intervals"/>
                                                </span>
                                                <br />
                                                <br />
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                <div  class="link_help"  onclick="showPanel('<fmt:message key="help.intervalsPoint.title" />','<fmt:message key="help.intervalsPoint.cont" />')" >&nbsp;</div>
                                            <div id="title_${layer.name}" style="text-align:center;">
                                                <br />
                                                <span class="textosnegrita">
                                                    <fmt:message key="showMap.radius"/>
                                                </span>
                                                <br />
                                                <br />
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach items="${layer.categories}" var="category"  varStatus="currentCategory">
                                        <c:choose>
                                            <c:when test="${'AREA' eq layer.type}" >
                                                <div id="category_${currentCategory.index}" >
                                                    <input type="checkbox" name="${layer.name}"/>
                                                    <form:input readonly="true" cssClass="intervals_value" path="layers[${current.index}].categories[${currentCategory.index}].value" />
                                                    <form:input readonly="true" cssClass="intervals_text" path="layers[${current.index}].categories[${currentCategory.index}].description" />
                                                    <br />
                                                </div>
                                            </c:when>
                                            <c:when test="${'LINE' eq layer.type}" >
                                                <div id="category_${currentCategory.index}">
                                                    <input type="checkbox" name="${layer.name}"/>
                                                    <form:input cssClass="intervals_txt" path="layers[${current.index}].categories[${currentCategory.index}].value" />
                                                    <br />
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div id="category_${currentCategory.index}">
                                                    <fmt:message key="showMap.radius" />
                                                    <form:input cssClass="intervals_txt" path="layers[${current.index}].categories[${currentCategory.index}].value" />
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

                        <input id="topPriority" class="button-up" onclick="topPriorityCategorys();"   value='<fmt:message key="interval.pullUpCategory"/>' type="button">
                        <input id="lowPriority" class="button-down" onclick="lowPriorityCategorys();"   value='<fmt:message key="interval.pullDownCategory"/>' type="button">
                        <br/>
                        <input id="addButton" type="button" class="button-simple" onclick="addCategory();" value='<fmt:message key="interval.addCategory"/>' />
                        <input id="groupButton" class="button-simple" onclick="groupCategorys();" value="<fmt:message key="interval.groupCategory"/>" type="button">
                        <input id="joinButton" type="button" class="button-simple" onclick="deleteCategory();" value='<fmt:message key="interval.deleteCategory"/>' />
                        <br/>
                        <input id="submitButton" type="submit" class="button-simple" value='<fmt:message key="layer.finalStep"/>' onclick="YAHOO.example.container.wait.show();"   /><br />
                    </div>
                </form:form>
            </div>
        </div>
        <br />
        <br />
        <div id="footer" >
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </body>
</html>
