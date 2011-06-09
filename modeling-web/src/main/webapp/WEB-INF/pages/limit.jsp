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
    
    <script type="text/javascript">

      // javascript specific for this page
      $(document).ready(function(){
        initHelpDialog();
      })
  
      waitingDialogTitle = '<fmt:message key="common.loading"/>';

      window.location = "${pageContext.request.contextPath}/limit.html#";
    </script>


  </head>

  <body onload="" class="yui-skin-sam">
    <div id="Header">
      <jsp:include page="/common/header.jsp"/>
    </div>
    <div id="contenido">
      <div id="limitTitle">
        <p class="titulo-principal"><fmt:message key="title.limit"/></p>
      </div>

      <div id="help-box" class="loader" ></div>
      <spring:hasBindErrors name="limitForm">
        <div class="errors">
          <h3><fmt:message key="errors.title"/></h3>
          <p>
            <c:forEach items="${errors.allErrors}" var="error">
              <fmt:message key="${error.code}" />
            </c:forEach>
          </p>
        </div>
      </spring:hasBindErrors>

      <div id="limitForm">
        <form:form method="post" action="limit.html" commandName="limitForm">
          <table width="60%" border="0" align="center" cellpadding="4" cellspacing="1" class="tabla-contenido">
            <tr class="celda02" >
              <td colspan="2" style="text-align: left; ">
                <span class="textosnegrita" style="text-align: left; font-size: 17px">
                  <fmt:message key="title.limit"/>
                </span>
                <div  class="link_help"  onclick="showPanel('<fmt:message key="help.limit.title" />','<fmt:message key="help.limit.cont" />')" >&nbsp;</div>
              </td>
            </tr>
            <tr class="celda01" style="height: 100px">
              <td width="20%">
                <span class="textosnegrita">
                  <fmt:message key="layer.layer" />
                </span>
              </td>
              <td align="center">
                <form:select path="selectedLayerName">
                  <form:options items="${limitForm.layers}" itemValue="name" itemLabel="displayName" />
                </form:select>
              </td>
            </tr>
            <tr class="celda02">
              <td colspan="2">
                <input id="submitButton" 
                       type="submit" 
                       class="button-simple" 
                       value='<fmt:message key="layer.nextStep"/>' 
                       onclick="showWaitingDialog()" />
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
