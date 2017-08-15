<%@ page import="com.artinrayan.foodi.web.util.ImageResolver" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 5/26/2017
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Hosts List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Hosts </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Host Name</th>
                <th>Creation Date</th>
                <th>Enabled</th>
                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                    <th width="100"></th>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <th width="100"></th>
                </sec:authorize>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${hosts}" var="host">
                <tr>
                    <td>${host.hostName}</td>
                    <td>${host.creationDate}</td>
                    <%--<td><img alt="" src="/imageLocator?img=Abort.png"></td>--%>
                    <td>
                        <c:choose>
                            <c:when test="${host.enabled}">
                                <img src="<c:url value='/static/images/small/Yes.png'/>" />
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value='/static/images/small/No.png'/>" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <table width="100%">
                            <tr>
                                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                    <td><a href="<c:url value='/host/manage-attachment-${host.hostId}' />" class="btn btn-success custom-width">attachments</a></td>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                    <td><a href="<c:url value='/host/manage-products-${host.hostId}' />" class="btn btn-success custom-width">products</a></td>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                    <td><a href="<c:url value='/host/manage-category-${host.hostId}' />" class="btn btn-success custom-width">category</a></td>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                    <td><a href="<c:url value='/host/edit-host-${host.hostId}' />" class="btn btn-success custom-width">edit</a></td>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN')">
                                    <td><a href="<c:url value='/host/delete-host-${host.hostId}' />" class="btn btn-danger custom-width">delete</a></td>
                                </sec:authorize>
                            </tr>
                        </table>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <sec:authorize access="hasRole('ADMIN')">
        <div class="well">
            <a href="<c:url value='/host/newHost' />">Add New Host</a>
        </div>
    </sec:authorize>
</div>
</body>
</html>
