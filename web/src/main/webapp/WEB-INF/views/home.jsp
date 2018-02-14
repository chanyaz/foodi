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
<%@taglib prefix="f" uri="/WEB-INF/tlds/fImage" %>


<html>

<head>
    <title>foodi</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyD9kOrSAog9xPLswbLnDaPB_djHHqF0EK8"></script>
    <link href="<c:url value='/static/css/mapStyle.css' />" rel="stylesheet"/>
    <script src="<c:url value="/static/js/mainMap.js"/>"></script>
    <script src="<c:url value="/static/js/lib/jquery-3.2.1.min.js"/>"></script>

</head>


<body>
<div class="generic-container">

    <%@include file="authheader.jsp" %>
    <div class="panel panel-default">
        <form:hidden id="hosts" path="hosts" value="${hostsStr}" />
        <div id="containerDiv">
            <table class="table table-hover" width="100%">
                <tr>
                    <td width="70%">
                        <div id="mainMap-canvas"/>
                    </td>
                    <td width="25%">
                        <div style="height: 600px; overflow-y:scroll;">
                            <table class="table table-hover">
                                <tbody>
                                    <c:forEach items="${hosts}" var="host">
                                        <tr>
                                            <td><img width="150px" height="100px" src="<f:showImage ownerId="${host.hostId}"/>"></td>

                                            <td><a href="#" onclick="jumpToLocation(${host.hostId},
                                                ${host.latitude}, ${host.longitude})">${host.hostName}</a></td>
                                            <%--<td><a target="_blank" href="<c:url value='/host/hostDetail-${host.hostId}' />">${host.hostName}</a></td></td>--%>
                                            <td>${host.hostCity}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
