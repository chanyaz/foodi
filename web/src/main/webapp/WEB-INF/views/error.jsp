<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>foodi</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>
    <div class="generic-container">
        <%@include file="authheader.jsp" %>

        <div class="well lead">An error accured</div>


            <c:if test="${not empty errCode}">
                <div style="font-size: 11px">${errCode}</div>
            </c:if>

            <c:if test="${not empty errMsg}">
                <div style="font-size: 11px">${errMsg}</div>
            </c:if>

        </div>

    </div>

</body>
</html>