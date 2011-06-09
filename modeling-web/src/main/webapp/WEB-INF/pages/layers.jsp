<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file="/common/taglibs" %>

<html>
  <head>
    <%@ include file="/common/theme" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script>
    
    <script type="text/javascript">
      
      function calculateTotalImportanceValue(){
        var result = 0;
                   
        // Sumarize all the values of the checked layers.
        $('[type=checkbox]').filter(':checked').each(function(){
          result += parseInt($('#'+this.id.replace(':','\\:')+'_weight').val());      
        })
          
        // set the value at the end of the table.
        $('#totalImportanceValue').text(result);
          
        /* 
         * Change the color of the label of totalImportanceValue and enable/disable
         * the button
         */
          
        if(result != 100){
          $('#totalImportanceValue').addClass("big-red-label");
          $('#submitButton').prop('disabled', true);
          $('#submitButton').addClass("button-simple-disabled")
          $('#submitButton').removeClass("button-simple")
            
        }else{
            
          $('#totalImportanceValue').removeClass("big-red-label");
          $('#submitButton').prop('disabled', false);
          $('#submitButton').removeClass("button-simple-disabled")
          $('#submitButton').addClass("button-simple")
        }
      }
      
      // java script specific for this page
      $(document).ready(function(){
        
        //$('[type=checkbox]').filter(':checked').click(function(event){
        $('[type=checkbox]').click(function(){
          $('#'+this.id.replace(':','\\:')+'_weight').prop("disabled", false);
          
          // disable and put to 0 the unchecked layers.
          $('[type=checkbox]').not(':checked').each(function(){
            var check = $('#'+this.id.replace(':','\\:')+'_weight');
            $(check).val(0);
            $(check).prop('disabled', true);
          })
          
          calculateTotalImportanceValue();
        })
        
        // any key press change the final value.
        $('[type=text]').keyup(function(){
          calculateTotalImportanceValue()
        });
        
        initHelpDialog();
      })

      waitingDialogTitle = '<fmt:message key="common.loading"/>';

      window.location = "${pageContext.request.contextPath}/layers.html#";
    </script>

  </head>
  <body onload="" class="yui-ski-sam" >
    <div id="header-contenedor">
      <jsp:include page="/common/header.jsp"/>
    </div>
    <div id="contenido" style="height: auto;">
      <p class="titulo-principal" style="">
        <fmt:message key="title.layer"/>
      </p>
      <div id="help-box" class="loader" ></div>
      <spring:hasBindErrors name="layersForm">
        <div class="errors">
          <h3>
            <fmt:message key="errors.title"/>
          </h3>
          <p>
            <c:forEach items="${errors.allErrors}" var="error">
              <fmt:message key="${error.code}" />
            </c:forEach>
          </p>
        </div>
      </spring:hasBindErrors>

      <div id="formXD">
        <form:form id="layersForm" 
                   commandName="layersForm" 
                   method="post" 
                   action="layers.html" >
          <div id="layers" 
               style="border: 1px;  text-align: center">
            <div id="resolution"  
                 style="border: 1px; margin-bottom: 5px">
              <table id="layerTable" 
                     class="tabla-contenido"
                     width="70%" 
                     border="0" 
                     align="center" 
                     cellpadding="4" 
                     cellspacing="1" >
                <tr class="celda02">
                  <td colspan="2" 
                      style="text-align: left; ">
                    <span class="textosnegrita" 
                          style="text-align: left; font-size: 17px">
                      <fmt:message key="layer.generalConfiguration"/>
                    </span>
                  </td>
                </tr>
                <tr class="celda01">
                  <td width="465px">
                    <span class="textos">
                      <fmt:message key="common.resolution"/>
                      <div  class="link_help"  
                            onclick="showPanel('<fmt:message key="help.resolution.title" />','<fmt:message key="help.resolution.cont" />')" >&nbsp;</div>
                    </span>
                  </td>
                  <td>
                    <form:input title="" path="resolution" />&nbsp;m
                  </td>
                </tr>
              </table>
            </div>
            <div >
              <table id="layersTable" 
                     class="tabla-contenido" 
                     width="70%" 
                     border="0" 
                     align="center"
                     cellpadding="4" 
                     cellspacing="1" >
                <tr class="celda02">
                  <td width="465px">
                    <span class="textosnegrita"><fmt:message key="common.layers"/></span>
                    <div  class="link_help"  
                          onclick="showPanel('<fmt:message key="help.layerList.title" />','<fmt:message key="help.layerList.cont" />')" >&nbsp;</div>
                  </td>
                  <td>
                    <span class="textosnegrita">
                      <fmt:message key="layer.importanceValue"/>
                    </span>
                  </td>
                </tr>
              </table>
            </div>
            <div id="layerSelection" 
                 style="margin: auto; width: 70%" >
              <table id="layersTable" 
                     class="tabla-contenido" 
                     width="100%" 
                     border="0" 
                     align="center" 
                     cellpadding="4" 
                     cellspacing="1" >
                <c:forEach items="${layersForm.layerList}" var="layer"  varStatus="current">
                  <form:hidden path="layerList[${current.index}].name" />
                  <form:hidden path="layerList[${current.index}].uri" />
                  <form:hidden path="layerList[${current.index}].displayName" />
                  <tr class="celda01">
                  <div class="${layer.name}">
                    <td width="465px">
                      <span class="textos" 
                            title="<fmt:message key='layer.description' />: ${layer.description}">
                        <form:checkbox cssStyle="width: 5%" 
                                       id="${layer.name}" 
                                       path="layerList[${current.index}].selected"  />
                        <c:out value="${layer.displayName}" />
                      </span>
                    </td>
                    <td>
                      <span class="textos">
                        <form:input cssStyle="align: left;"
                                    id="${layer.name}_weight"
                                    disabled="true"
                                    path="layerList[${current.index}].weight"
                                    maxlength="2"
                                    onkeyup="" />&nbsp;%

                      </span>
                    </td>
                  </div>
                  </tr>
                </c:forEach>
              </table>
            </div>
            <div id="totalRelevance">
              <table id="layersTable" 
                     class="tabla-contenido" 
                     width="70%" 
                     border="0" 
                     align="center" 
                     cellpadding="4" 
                     cellspacing="1" >
                <tr class="celda02">
                  <td width="465px">
                    <span class="textos">
                      <div id="importance">
                        <fmt:message key="layer.importanceValueTotal"/>
                        <label id="totalImportanceValue"
                               class="big-red-label"
                               title="<fmt:message key="layer.importanceValueHint"/>" >
          								0
                        </label>
                  			%
                      </div>
                    </span>
                  </td>
                  <td>
                    <input class="button-simple-disabled" 
                           id="submitButton" 
                           disabled="true" 
                           type="submit" 
                           value='<fmt:message key="layer.nextStep"/>'  />
                  </td>
                <tr>
              </table>
            </div>
          </div>
        </div>
      </form:form>
    </div>
    <div id="footer">
      <jsp:include page="/common/footer.jsp"/>
    </div>
  </body>
</html>
