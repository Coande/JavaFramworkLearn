<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<title><spring:message code="tip.userList"></spring:message></title>
</head>
<body>

	<div class="container">
		<div class="text-right">
			<a
				href="${pageContext.request.contextPath }/user/changeLang?langType=zh">中文</a>
			<a
				href="${pageContext.request.contextPath }/user/changeLang?langType=en">English</a>
		</div>
		<h1 class="text-center">
			<spring:message code="tip.userList"></spring:message>
		</h1>
		<form:form action="${pageContext.request.contextPath }/user/userList"
			method="post" class="form-horizontal">
			<div class="input-group">
				<input type="text" name="keyword" class="form-control"
					placeholder="<spring:message code="tip.username"></spring:message>">
				<span class="input-group-btn"> <input type="submit"
					value="<spring:message code="button.search"></spring:message>"
					class="btn btn-default">
				</span>
			</div>
		</form:form>
		<br>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th><spring:message code="user.username"></spring:message></th>
					<th><spring:message code="user.password"></spring:message></th>
					<th><spring:message code="user.email"></spring:message></th>
					<th><spring:message code="user.age"></spring:message></th>
					<th><spring:message code="user.sex"></spring:message></th>
					<th><spring:message code="tip.operation"></spring:message></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ users}" var="user">
					<tr>
						<td>${user.value.username}</td>
						<td>${user.value.password }</td>
						<td>${user.value.email }</td>
						<td>${user.value.age }</td>
						<td>${user.value.sex }</td>
						<td><a
							href="${pageContext.request.contextPath }/user/delete/${user.value.username }"
							class="btn btn-danger"><spring:message code="button.delete"></spring:message></a>
							&nbsp; <a
							href="${pageContext.request.contextPath }/user/edit/${user.value.username }"
							class="btn btn-info"><spring:message code="button.edit"></spring:message></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="${pageContext.request.contextPath }/user/add"
			class="btn btn-info form-control"><spring:message
				code="button.add"></spring:message></a>
	</div>
</body>
</html>