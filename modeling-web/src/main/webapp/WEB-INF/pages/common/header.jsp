<%-- 
    Document   : header
    Created on : 12/01/2010, 03:51:55 PM
    Author     : esmata
--%>
<%@include file="taglibs" %>

<div style="-rave-layout: grid">
	<form:form>
		<div id="banner-rep">
			<div id="banner">
				<div id="title">
					<h1>
						<fmt:message key="common.applicationName"/>
					</h1>
				</div>
				<div id="lang">
					<a class="link_home" href="index.html" style="height: 24px; width: 46px"><fmt:message key="common.home"/></a>
					<a class="link_about" href="about.html" style="height: 24px; width: 46px"><fmt:message key="common.about"/></a>
				</div>
				<%@include file="userInfo.jsp" %>
			</div>
		</div>
	</form:form>
</div>
