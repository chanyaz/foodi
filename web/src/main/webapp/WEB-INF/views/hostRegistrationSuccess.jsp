<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <title>Registration Confirmation Page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>
<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>

    <div class="alert alert-success lead">
        ${success}
    </div>

		<span class="well floatRight">
			Go to <a href="<c:url value='/host/hostList' />">Hosts List</a>
		</span>
</div>
</body>

</html>