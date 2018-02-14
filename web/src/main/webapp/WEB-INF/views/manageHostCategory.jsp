<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 8/14/2017
  Time: 5:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <title>Host Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>

    <div class="well lead">Host Registration Form</div>
    <form:form method="POST" modelAttribute="host" class="form-horizontal">

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="categoryTitle">categoryTitle</label>
                <div class="col-md-7">
                    <form:input type="text" path="categoryTitle" id="categoryTitle" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="categoryTitle" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="categoryDescription">Host Description</label>
                <div class="col-md-7">
                    <form:textarea type="text" path="categoryDescription" id="categoryDescription" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="categoryDescription" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

                    <form:input type="text" path="latitude" id="latitude" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="latitude" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

<div class="panel-heading"><span class="lead">List of Hosts </span></div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Host Name</th>
        <th>Creation Date</th>
        <th>Enabled</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${categories}" var="cat">
        <tr>
            <td>${cat.categoryTitle}</td>
            <td>${cat.categoryDescription}</td>
            <td>${cat.creationDate}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>
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
    </form:form>
</div>
</body>
</html>