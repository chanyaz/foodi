<%@ page import="com.artinrayan.foodi.web.util.UserUtil" %>
<div class="authbar">
		<span>Dear <strong><%=UserUtil.getCurrentUser().getFullName()%></strong>, Welcome to Foodi</span>
			<span class="floatRight">
				<a href="<c:url value="/user/userList" />">Users</a>
				<a href="<c:url value="/user/angularMap" />">angularMap</a>
				<a href="<c:url value="/user/home" />">home</a>
				<a href="<c:url value="/host/hostList" />">Hosts</a>
				<a href="<c:url value="/logout" />">Logout</a>
			</span>
	</div>
