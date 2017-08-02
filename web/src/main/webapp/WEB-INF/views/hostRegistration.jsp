<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Host Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>

    <div class="well lead">Host Registration Form</div>
    <form:form method="POST" modelAttribute="host" class="form-horizontal">
        <form:input type="hidden" path="hostId" hostId="hostId"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="hostName">Host Name</label>
                <div class="col-md-7">
                    <form:input type="text" path="hostName" id="hostName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="hostName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="hostDetail">Host Description</label>
                <div class="col-md-7">
                    <form:textarea type="text" path="hostDetail" id="hostDetail" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="hostDetail" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="hostCountry">hostCountry</label>
                <div class="col-md-7">
                    <form:input type="text" path="hostCountry" id="hostCountry" class="form-control input-sm" />
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
                    <form:input type="text" path="hostState" id="hostState" class="form-control input-sm" />
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
                <label class="col-md-3 control-lable" for="hostAddress">hostAddress</label>
                <div class="col-md-7">
                    <form:input type="text" path="hostAddress" id="hostAddress" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="hostAddress" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="hostPhoneNumber">hostPhoneNumber</label>
                <div class="col-md-7">
                    <form:input type="text" path="hostPhoneNumber" id="hostPhoneNumber" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="hostPhoneNumber" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="hostWebSite">hostWebSite</label>
                <div class="col-md-7">
                    <form:input type="text" path="hostWebSite" id="hostWebSite" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="hostWebSite" class="help-inline"/>
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

        <c:choose>
            <c:when test="${edit}">
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable">creationDate</label>
                        <div class="col-md-7">
                            <c:out value="${host.creationDate}"/>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>


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