<%@ include file="taglibs"%>

<div id="user-info">
 <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
  <fmt:message key="common.welcome"/>: <sec:authentication property="principal.username"/> |
  <a href="<c:url value="/j_spring_security_logout"/>">
	  <fmt:message key='common.logout'/>
  </a>
 </sec:authorize>  

 <sec:authorize ifNotGranted="ROLE_ADMIN,ROLE_USER">
    <a href="<c:out value="${pageContext.request.contextPath}"/>/login.html"
	   title="<fmt:message key='common.login'/>"><fmt:message key='common.login'/>
    </a> | 
	<a href="<c:url value="/j_spring_security_logout"/>">
		<fmt:message key='common.logout'/>
    </a>
  </sec:authorize>  
</div>

