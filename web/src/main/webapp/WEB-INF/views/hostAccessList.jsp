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
    <div>
        <form:form method="POST" modelAttribute="newHostAccess" class="form-horizontal">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="hostCountry">hostCountry</label>
                    <div class="col-md-7">
                        <form:input type="text" path="hostCountry" id="hostCountry" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="hostCountry" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="hostState">hostState</label>
                    <div class="col-md-7">
                        <form:input type="text" path="hostState" id="HostState" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="hostState" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="hostCity">hostCity</label>
                    <div class="col-md-7">
                        <form:input type="text" path="hostCity" id="hostCity" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="hostCity" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="hostWebSite">hostWebSite</label>
                    <div class="col-md-7">
                        <c:choose>
                            <c:when test="${edit}">
                                <form:input type="text" path="hostWebSite" id="hostWebSite" class="form-control input-sm" disabled="true"/>
                            </c:when>
                            <c:otherwise>
                                <form:input type="text" path="hostWebSite" id="hostWebSite" class="form-control input-sm" />
                                <div class="has-error">
                                    <form:errors path="hostWebSite" class="help-inline"/>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="hostAddress">hostAddress</label>
                    <div class="col-md-7">
                        <form:input type="hostAddress" path="hostAddress" id="hostAddress" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="hostAddress" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="hostPhoneNumber">hostAddress</label>
                    <div class="col-md-7">
                        <form:input type="hostAddress" path="hostPhoneNumber" id="hostPhoneNumber" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="hostPhoneNumber" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="latitude">latitude</label>
                    <div class="col-md-7">
                        <form:input type="text" path="latitude" id="latitude" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="latitude" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="longitude">longitude</label>
                    <div class="col-md-7">
                        <form:input type="text" path="longitude" id="longitude" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="longitude" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-actions floatRight">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <input href="/host/newHostAccess" type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Hosts </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Host Name</th>
                <th>Country</th>
                <th>State</th>
                <th>City</th>
                <th>Address</th>
                <th>latitude</th>
                <th>longitude</th>
                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                    <th width="100"></th>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <th width="100"></th>
                </sec:authorize>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${hostAccessList}" var="hostAccess">
                <tr>
                    <td>${hostAccess.host.hostName}</td>
                    <td>${hostAccess.hostCountry}</td>
                    <td>${hostAccess.hostState}</td>
                    <td>${hostAccess.hostCity}</td>
                    <td>${hostAccess.hostAddress}</td>
                    <td>${hostAccess.latitude}</td>
                    <td>${hostAccess.longitude}</td>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                        <td><a href="<c:url value='/host/edit-host-access-${hostAccess.hostAccessId}' />" class="btn btn-success custom-width">edit</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                        <td><a href="<c:url value='/host/manage-attachment-${hostAccess.hostAccessId}' />" class="btn btn-success custom-width">images</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td><a href="<c:url value='/host/delete-host-access-${hostAccess.hostAccessId}-${hostAccess.host.hostId}' />" class="btn btn-danger custom-width">delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <sec:authorize access="hasRole('ADMIN')">
        <div class="well">
            <a href="<c:url value='/newHost' />">Add New Host</a>
        </div>
    </sec:authorize>
</div>
</body>
</html>
