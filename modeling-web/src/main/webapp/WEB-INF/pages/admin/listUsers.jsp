<%-- 
    Document   : listUsers
    Created on : 19/05/2010, 09:56:00 AM
    Author     : asanabria
--%>

<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglibs" %>

<html>
    <head>
        <%@ include file="/common/theme" %>
        <%@ include file="/common/javascript" %>
    </head>
    <body onload="checkSize()">
        <div id="Header">
            <jsp:include page="/common/header.jsp"/>
        </div>

        <div id="contenido">
            <h2><fmt:message key="title.user"/></h2>
            <div style="width:700px; margin: 10px auto 10px auto">

                <div >
                    <form:form  id="userForm" commandName="userForm" action="/listUsers.html">
                        <table  id="layerListTable" cellspacing="0" cellpadding="3px"  >
                            <tr style="font-weight: bold; width: auto;" >
                                <td class="layerListHeader" width="4%">
                                    &nbsp;
                                </td>
                                <td class="layerListHeader" width="20%"  >
                                    <fmt:message key="user.username" />
                                </td>
                                <td class="layerListHeader">
                                    <fmt:message key="user.fullname" />
                                </td>
                                <td class="layerListHeader" >
                                    <fmt:message key="user.rol" />
                                </td>
                            </tr>
                            <c:forEach items="${users}" var="user">
                                <tr >
                                    <td>
                                        <form:radiobutton path="username" value="${user.username}" />
                                    </td>
                                    <td >
                                        <c:out value="${user.username}" />&nbsp;
                                    </td>
                                    <td >
                                        <c:out value="${user.fullname}" />&nbsp;

                                    </td>
                                    <td >
                                        <c:forEach items="${user.authorities}" var="authority">
                                            <c:choose>
                                                <c:when test="${authority.authority == 'ROLE_USER'}" >
                                                    <fmt:message key="common.user" />,
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="common.admin" />,
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <br />
                    </form:form>
                </div>

                <div class="">
                    <input class="button-simple" type="button" value="<fmt:message key='common.new' />" onclick="newUser()"/>
                    <input class="button-simple" type="button" value="<fmt:message key='common.edit' />" onclick="editUser()"/>
                    <input class="button-simple" type="button" value="<fmt:message key='common.delete' />" onclick="deleteUser()"/>
                </div>

                <div class="clear">
                </div>
            </div>
        </div>
        <div id="footer">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </body>
</html>