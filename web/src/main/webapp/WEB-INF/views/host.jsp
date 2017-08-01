<%--
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Hosts List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyD9kOrSAog9xPLswbLnDaPB_djHHqF0EK8"></script>
    <link href="<c:url value='/static/css/mapStyle.css' />" rel="stylesheet"/>
    <script src="<c:url value="/static/js/hostMap.js"/>"></script>
    <script src="<c:url value="/static/js/lib/jquery-3.2.1.min.js"/>"></script>
</head>

<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>
    <div class="panel panel-default">
        <form:hidden id="host" path="host" value="${hostStr}" />
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Host</span></div>

            <div id="containerDiv">
                <table class="table table-hover" width="100%" style="height: 300px; border: none">
                    <tr>
                        <td>
                            <table style="border: none;border-collapse: collapse;">
                                <tr style="border: none;">
                                    <td style="border: none;">${host.hostName}</td>
                                </tr>
                                <tr style="border: none;">
                                    <td style="border: none;">${host.hostCountry}</td>
                                </tr>
                                <tr style="border: none;">
                                    <td style="border: none;">${host.hostCity}</td>
                                </tr>
                                <tr style="border: none;">
                                    <td style="border: none;">${host.hostAddress}</td>
                                </tr>
                                <tr style="border: none;">
                                    <td style="border: none;">${host.hostDetail}</td>
                                </tr>
                            </table>
                        </td>
                        <td width="50%">
                            <img width="100%" height="100%" src="/displayFileByHostId?id=${host.hostId}"/>
                        </td>
                    </tr>
                </table>
                <table class="table table-hover" width="100%">
                    <tr>
                        <div id="hostMap-canvas"/>
                    </tr>
                </table>
            </div>
        </div>
    </div>

</body>
</html>
