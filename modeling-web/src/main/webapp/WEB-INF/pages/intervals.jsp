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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/intervals.js"></script>

    <script type="text/javascript">
      
      // javascript specific for this page
      $(document).ready(function(){
        
        // 1.  Allow you to show or hide the categories of a specified layer.
        $('[type=radio]').click(function(){
          hideUnselectedLayers();
          showSelectedLayer(this.id);
        })
        
        // 2. Add add-category-function to addButton
        $('#addButton').click(function(){
          addCategoryAction();
        })

        // 3. add delete-selected-category to the deleteButton
        $('#deleteButton').click(function(){
          deleteCategoriesAction();
        })
       
        // 4. add group-selected-categories to group
        $('#groupButton').click(function(){
          groupCategoriesAction();
        })
        
        // 5. add upgrade-selected-categories to upbutton
        $('#upButton').click(function(){
          raiseCategoryValue();
        })
        
        // 6. add downgrade-selected-categories to upbutton
        $('#downButton').click(function(){
          lowerCategoryValue();
        })
        
        // 7. Reverse categories
        $('#toolbar > div > input').click(function(){
          
          var current = $(this).parent().parent().parent();
          var categoryList = $(current).contents().not('#toolbar').filter('div');
            
          $(categoryList).each(function(){
            var last = $(categoryList).last()[0]
            if(this == last)
              return
            $(this).insertAfter(last);
          })
        })
        initHelpDialog();
      });
      
      
      function showIntervalsPanel(layerType){
        initHelpDialog();
        
        switch(layerType){
          case 'AREA':
            $('#help-box').dialog('option', 'title', '<fmt:message key="help.intervalsArea.title" />');
            $('#help-box').prop('innerHTML', '<fmt:message key="help.intervalsArea.cont" />');
            break;
          case 'LINE':
            $('#help-box').dialog('option', 'title', '<fmt:message key="help.intervalsLine.title" />');
            $('#help-box').prop('innerHTML', '<fmt:message key="help.intervalsLine.cont" />');
            break;
          case 'POINT':
            $('#help-box').dialog('option', 'title', '<fmt:message key="help.intervalsPoint.title" />');
            $('#help-box').prop('innerHTML', '<fmt:message key="help.intervalsPoint.cont" />')
            break;
          }
        
          showHelpDialog();
        }
    
        waitingDialogTitle = '<fmt:message key="common.loading"/>';
        window.location = "${pageContext.request.contextPath}/intervals.html#";
      
    </script>


  </head>
  <body class="yui-skin-sam">
    <div id="Header">
      <jsp:include page="/common/header.jsp"/>
    </div>
    <div id="contenido" 
         style="width: 100%; 
         height: 350px">

      <div id="intervalsTitle">
        <p class="titulo-principal">
          <fmt:message key="title.intervals"/>
        </p>
      </div>

      <div id="help-box" class="loader" ></div>

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

      <div id="data-content">
        <form:form method="post" 
                   action="intervals.html" 
                   commandName="intervalsForm">
          <div id="layer_names" class="content_div">
            <div class="titulo-principal">
              <fmt:message key="common.layers" />
            </div>
            <div id="layer-list">

              <c:forEach items="${intervalsForm.layers}"
                         var="layer"  
                         varStatus="current">

                <div style="text-align: left">
                  <form:hidden path="layers[${current.index}].name" />
                  <form:hidden path="layers[${current.index}].displayName" />
                  <form:hidden path="layers[${current.index}].weight" />
                  <form:hidden path="layers[${current.index}].type" />

                  <input name="rbEditing"
                         type="radio"
                         id="${layer.name}"
                         onclick="enableButtons('${layer.type}');"
                         value="${current.index}" />

                  <label><c:out value="${layer.displayName}" /></label>
                </div>

              </c:forEach>

            </div>
          </div>

          <div id="categorys_layer" class="content_div_categorys">
            <div class="titulo-principal">
              <fmt:message key="showMap.categories" />
            </div>

            <div id="category-list">
              <c:forEach items="${intervalsForm.layers}"
                         var="layer"  
                         varStatus="current">

                <div id="${layer.name}_div" class="${layer.type}" style="display: none">
                  <div id="toolbar" class="toolbar">

                    <div id="revertedOption">
                      <c:if test="${'LINE' eq layer.type}" >
                        <label>
                          <fmt:message key="common.reverted" />
                        </label> 
                        <form:hidden path="layers[${current.index}].reverted" />
                        <input type="checkbox" />
                      </c:if>
                    </div>

                    <div  class="link_help" onClick="showIntervalsPanel('${layer.type}')" >&nbsp;</div>

                  </div>
                  <c:forEach items="${layer.categories}" 
                             var="category"  
                             varStatus="currentCategory">
                    <c:if test="${'AREA' eq layer.type}" >
                      <div>
                        <input type="checkbox" 
                               name="${layer.name}"/>
                        <label class="intervals_value">
                          <c:out value="${currentCategory.index+1}" />
                        </label>
                        <form:hidden path="layers[${current.index}].categories[${currentCategory.index}].value" />
                        <form:input readonly="true" 
                                    cssClass="intervals_text"
                                    path="layers[${current.index}].categories[${currentCategory.index}].description" />
                        <br />
                      </div>
                    </c:if>

                    <c:if test="${'LINE' eq layer.type}" >
                      <div>
                        <input type="checkbox" 
                               name="${layer.name}"/>
                        <label class="intervals_value">
                          <c:out value="${currentCategory.index+2}" />
                        </label>

                        <form:input readonly="true" 
                                    cssClass="intervals_text"
                                    path="layers[${current.index}].categories[${currentCategory.index}].value" />
                        <br />
                      </div>
                    </c:if>
                    <c:if test="${'POINT' eq layer.type}" >
                      <div>
                        <fmt:message key="showMap.radius" />
                        <form:input cssClass="intervals_txt" 
                                    path="layers[${current.index}].categories[${currentCategory.index}].value" />
                        <br />
                      </div>
                    </c:if>
                  </c:forEach>
                </div>
              </c:forEach>
            </div>
          </div>

          <div id="toolbar" class="buttons">
            <input id="upButton" 
                   class="button-up" 
                   value='<fmt:message key="interval.pullUpCategory"/>' 
                   type="button">
            <input id="downButton" 
                   class="button-down" 
                   value='<fmt:message key="interval.pullDownCategory"/>' 
                   type="button">
            <br/>
            <input id="addButton" 
                   type="button"
                   class="button-simple" 
                   value='<fmt:message key="interval.addCategory"/>' />

            <input id="groupButton" 
                   type="button"
                   class="button-simple" 
                   value="<fmt:message key="interval.groupCategory"/>" />

            <input id="deleteButton" 
                   type="button" 
                   class="button-simple" 
                   value='<fmt:message key="interval.deleteCategory"/>' />
            <br/>
            <input id="submitButton" 
                   type="submit" 
                   class="button-simple"
                   onclick="showWaitingDialog()"
                   value='<fmt:message key="layer.finalStep"/>' />
            <br />
          </div>

        </form:form>
      </div>
      <div id="footer" >
        <jsp:include page="/common/footer.jsp"/>
      </div>
  </body>
</html>
