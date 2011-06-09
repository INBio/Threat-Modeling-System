<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file="/common/taglibs" %>

<html>
	<head>
		<%@ include file="/common/theme" %>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script type="text/javascript">
            
            // java script specific for this page
             $(document).ready(function(){
                 
                 // change the color of the begin button
                   $('#begin').hover(
                    function(){
                        
                        $(this).addClass('begin-off');
                    },
                    
                    function(){
                        $(this).removeClass('begin-off');
                    });
                    
                  // add a click event on the begin button  
                  $('#begin').click(
                    function(event){
                        // redirect to the layers page
                       window.location.pathname='<c:out value="${pageContext.request.contextPath}"/>'+'/layers.html'; 
                    });
             });
                 
			
		</script>
	</head>
	<body onload="" class="yui-skin-sam" >
		<div id="header-contenedor">
			<div style="-rave-layout: grid">
				<div id="lang" >
					<div id="title" style="float: left;"><fmt:message key="common.applicationName"/></div>
				</div>
				<div id="banner-contenedor">
					<a href="http://www.iabin.net">
						<div id="banner" ></div>
					</a>
				</div>
				<div id="spacer"> </div>
			</div>
		</div>
		<div id="contenido" style="text-align: center">
			<div id="div-logos">
				<a id="iabin-logo" href="http://iabin.net/"></a>
				<br />
				<br />
				<br />
				<br />
				<br />
				<a id="inbio-logo" style="float: right" href="http://www.inbio.ac.cr/"></a>
			</div>
			<div id="intro">
				<fmt:message key="common.intro" />
			</div>
			<div id="begin" class="begin-on" onclick="">
				<fmt:message key="common.begin" />
			</div>
		</div>

		<div id="footer">
			<jsp:include page="/common/footer.jsp"/>
		</div>
	</body>
</html>