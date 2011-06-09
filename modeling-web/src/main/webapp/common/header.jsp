<%-- 
    Document   : header
    Created on : 12/01/2010, 03:51:55 PM
    Author     : esmata
--%>
<%@include file="taglibs" %>

<div style="-rave-layout: grid">
	<div id="lang">
		<a class="link_home" href="${pageContext.request.contextPath}/index.html" style="height: 24px; width: 46px"><fmt:message key="common.home"/></a>
		<a class="link_admin" href="${pageContext.request.contextPath}/admin/administration.html" style="height: 24px; width: 46px"><fmt:message key="common.admin"/></a>
		<a class="link_wiki" target="_new" href="http://pulsatrix.inbio.ac.cr/projects/modeling/" style="height: 24px; width: 46px"><fmt:message key="common.wiki"/></a>
		<a class="link_about" href="${pageContext.request.contextPath}/about.html" style="height: 24px; width: 46px"><fmt:message key="common.about"/></a>
		<div id="title"><fmt:message key="common.applicationName"/></div>
	</div>
	<div id="banner-contenedor">
		<a href="http://www.iabin.net">
			<div id="banner" ></div>
		</a>
	</div>
	<div id="spacer"> </div>
</div>