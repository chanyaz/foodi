<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 5/26/2017
  Time: 9:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>

<c:if test="${not empty errCode}">
    <h1>${errCode} : System Errors</h1>
</c:if>

<c:if test="${empty errCode}">
    <h1>System Errors</h1>
</c:if>

<c:if test="${not empty errMsg}">
    <h2>${errMsg}</h2>
</c:if>

</body>
</html>