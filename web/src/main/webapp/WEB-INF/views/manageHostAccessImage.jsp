<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 6/6/2017
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <title>Hosts List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>
    ${success}
    <%--<div>--%>
    <%--<form:form method="POST" modelAttribute="newHostAccessImage" enctype="multipart/form-data">--%>
    <%--File to upload: <form:input path="image" id="hostCountry" class="form-control input-sm" type="file" name="file">--%>
    <%--<input type="submit" value="Upload"> Press here to upload the file!--%>
    <%--</form:form>--%>
    <%--</div>--%>

    <%--<div align="center">--%>
    <%--<h1>Spring MVC - Hibernate File Upload to Database Demo</h1>--%>
    <%--<form method="post" action="/uploadFile" enctype="multipart/form-data">--%>
    <%--<table border="0">--%>
    <%--<tr>--%>
    <%--<td>Pick file #1:</td>--%>
    <%--<td><input type="file" name="fileUpload" size="50" /></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td colspan="2" align="center"><input type="submit" value="Upload" /></td>--%>
    <%--</tr>--%>
    <%--</table>--%>
    <%--</form>--%>
    <%--</div>--%>

    <form:form method="POST" enctype="multipart/form-data" modelAttribute="newHostAccessFile" class="form-horizontal">
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="fileContent">File to upload</label>
                <div class="col-md-7">
                    <form:input type="file" path="fileContent" id="fileContent" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="fileContent" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-actions floatRight">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/host/hostList' />">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/host/hostList' />">Cancel</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Hosts </span></div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Host Name</th>
                    <th>Country</th>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                        <th width="100"></th>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <th width="100"></th>
                    </sec:authorize>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${hostAccessFiles}" var="accessFile">
                    <tr>
                        <td>${accessFile.id}</td>
                        <td>${accessFile.fileContent}</td>
                        <td>${accessFile.creationDate}</td>
                        <td><img src="/imageDisplay?id=${accessFile.id}"/></td>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                            <td><a href="<c:url value='/host/edit-host-access-${accessFile.id}' />" class="btn btn-success custom-width">edit</a></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <td><a href="<c:url value='/host/delete-host-access-${accessFile.id}' />" class="btn btn-danger custom-width">delete</a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </form:form>

    <%--<sec:authorize access="hasRole('ADMIN')">--%>
    <%--<div class="well">--%>
    <%--<a href="<c:url value='/newHost' />">Add New Host</a>--%>
    <%--</div>--%>
    <%--</sec:authorize>--%>
</div>
</body>
</html>
