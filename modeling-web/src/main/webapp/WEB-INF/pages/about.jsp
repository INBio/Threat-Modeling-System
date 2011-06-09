<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@include file="/common/taglibs" %>

<html>
	<head>
		<%@ include file="/common/theme" %>
	</head>
	<body onload="">
		<div id="Header">
			<!-- Header -->
			<jsp:include page="/common/header.jsp"/>
		</div>
		<div id="contenido" style="height: auto" >
			<h1><fmt:message key="common.about"/></h1>


			<div id="sponsors">
				<h2><fmt:message key="common.sponsors"/></h2>
				<a id="oas-logo" 	href="http://www.oas.org" target="_new"></a>&nbsp;
				<br />
				<br />
				<a id="gef-logo" 	href="http://www.thegef.org" target="_new"></a>&nbsp;&thinsp;
				<br />
				<br />
				<a id="worlbank-logo" href="http://www.worldbank.org" target="_new"></a>&nbsp;
				<br />
				<br />
				<a id="iabin-logo" 	href="http://www.iabin.net" target="_new"></a>&nbsp;
				<br />
				<br />
				<a id="inbio-logo" 	href="http://www.inbio.ac.cr" target="_new"></a>&nbsp;
			</div>
				<br />
				<br />
				<br />
			<div id="licenses">
				<h2><fmt:message key="common.license"/></h2>
				<br />
				<br />
				<h3><fmt:message key="common.softwareLicenseTitle"/></h3>
				<br />
				<p><fmt:message key="common.softwareLicenseText"/></p>
				<br />
				<br />
				<h3><fmt:message key="common.iconLicenseTitle"/></h3>
				<br />
				<p><fmt:message key="common.iconLicenseText"/></p>
			</div>
		</div>
		<div id="footer">
			<jsp:include page="/common/footer.jsp"/>
		</div>
	</body>
</html>
