<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page
	import="ua.kpi.epam.transport.commands.route.RouteCommandFactory"%>
<%@page
	import="ua.kpi.epam.transport.commands.user.AutentificateUserCommand"%>
<%@page import="ua.kpi.epam.transport.commands.CommandFactory"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./style.css">
<c:set var="FIND_ALL_ROUTE_AND_STOPS_COMMAND"
	value="<%=RouteCommandFactory.FIND_ALL_ROUTE_AND_STOPS_COMMAND%>" />
<c:set var="AUTENTIFICATION_COMMAND"
	value="<%=CommandFactory.AUTENTIFICATION_COMMAND%>" />
<c:set var="CHANGE_LOCALE_COMMAND"
	value="<%=CommandFactory.CHANGE_LOCALE_COMMAND%>" />

<c:set var="LOGIN_ATTRIBUTE"
	value="<%=AutentificateUserCommand.LOGIN_ATTRIBUTE%>" />
<c:set var="PASSWORD_ATTRIBUTE"
	value="<%=AutentificateUserCommand.PASSWORD_ATTRIBUTE%>" />
</head>
<body>
	<c:if test="${sessionScope.userLocale != null}">
		<fmt:setLocale value="${sessionScope.userLocale}" scope="session" />
	</c:if>
	<fmt:bundle basename="ua.kpi.epam.transport.properties.labels">
		<div style="display: block; margin: 20px;">
			<a class="my_link" href="./index.jsp"><fmt:message
					key="RouteSeach" /></a> <a class="my_link"
				href="./TransportServlet?command=${FIND_ALL_ROUTE_AND_STOPS_COMMAND}"><fmt:message
					key="RouteSchemes" /></a> <a class="my_link"
				href="./autentification.jsp"><fmt:message key="EnterSystem" /></a> <a
				class="my_link" href="./admin/transport.jsp"><fmt:message
					key="AdminPage" /></a> <a class="my_link"
				href="./TransportServlet?command=${CHANGE_LOCALE_COMMAND}"><fmt:message
					key="ChangeLocale" /></a>
		</div>

		<div class="inlineDiv">
			<form method="get" name="registration" action="./TransportServlet">
				<fieldset>
					<legend>
						<fmt:message key="EnterSystem" />
					</legend>
					<fmt:message key="Login" />
					<br /> <input type="text" name="${LOGIN_ATTRIBUTE}" size="40"
						required="required"> <br />
					<fmt:message key="Password" />
					<br /> <input type="password" name="${PASSWORD_ATTRIBUTE}"
						size="40" required="required"> <br />
					<button type="submit" name="command"
						value="${AUTENTIFICATION_COMMAND}">
						<fmt:message key="EnterSystem" />
					</button>
					<br /> <a href="./registration.jsp"><fmt:message
							key="MakeRegistration" /></a>
				</fieldset>
			</form>
		</div>
		<div style="color: red;">
			<c:out value="${result}" />
		</div>
	</fmt:bundle>
</body>
</html>