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
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script
	src="<spring:message code='url.validate'></spring:message>" ></script>
<script type="text/javascript">
	$.validator.setDefaults({
		submitHandler : function(form) {
			alert("<spring:message code='tip.editSuccess'></spring:message>");
			$(form).ajaxSubmit();
		}
	});
	$().ready(function() {
		$("#userForm").validate();
	});
</script>
<style>
input.error {
	border: 1px solid red;
}

label.error {
	padding-left: 16px;
	padding-bottom: 2px;
	font-weight: bold;
	color: #EA5200;
}
</style>
<title><spring:message code="tip.userEdit"></spring:message></title>
</head>
<body>
	<div class="container">
		<h1 class="text-center">
			<spring:message code="tip.userEdit"></spring:message>
		</h1>
		<form:form
			action="${pageContext.request.contextPath }/user/edit/${user.username}"
			method="post" modelAttribute="user" role="form"
			cssClass="form-horizontal" id="userForm">
			<div class="form-group">
				<label class="col-sm-1 control-label"><spring:message
						code="user.username"></spring:message></label>
				<div class="col-sm-11">
					<input type="text" name="username" value="${user.username }"
						class="form-control" disabled> <input type="text"
						name="username" value="${user.username }" class="sr-only" require
						minlength="6">
					<form:errors path="username"></form:errors>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label"> <spring:message
						code="user.password"></spring:message></label>
				<div class="col-sm-11">
					<input type="password" name="password" value="${user.password }"
						class="form-control" require minlength="6">
					<form:errors path="password"></form:errors>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label"> <spring:message
						code="user.email"></spring:message></label>
				<div class="col-sm-11">
					<input type="email" name="email" value="${user.email }"
						class="form-control">
					<form:errors path="email"></form:errors>
				</div>

			</div>


			<div class="form-group">
				<label class="col-sm-1 control-label"> <spring:message
						code="user.age"></spring:message></label>
				<div class="col-sm-11">
					<input type="number" name="age" value="${user.age }"
						class="form-control" range="[0,120]">
					<form:errors path="age"></form:errors>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label"> <spring:message
						code="user.sex"></spring:message></label>


				<div class="col-sm-11">
					<c:choose>
						<c:when test="${user.sex=='男' }">
							<label class="radio-inline"><input type="radio"
								name="sex" value="男" checked> <spring:message
									code="tip.sexMale"></spring:message></label>
							<label class="radio-inline"><input type="radio"
								name="sex" value="女"> <spring:message
									code="tip.sexFemale"></spring:message></label>
						</c:when>
						<c:when test="${user.sex=='女' }">
							<label class="radio-inline"><input type="radio"
								name="sex" value="男"> <spring:message code="tip.sexMale"></spring:message></label>
							<label class="radio-inline"><input type="radio"
								name="sex" value="女" checked> <spring:message
									code="tip.sexFemale"></spring:message></label>
						</c:when>
						<c:otherwise>
							<label class="radio-inline"><input type="radio"
								name="sex" value="男"> <spring:message code="tip.sexMale"></spring:message></label>
							<label class="radio-inline"><input type="radio"
								name="sex" value="女"> <spring:message
									code="tip.sexFemale"></spring:message></label>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-11">
					<input type="submit"
						value="<spring:message code="button.save"></spring:message>"
						class="btn btn-primary form-control">
				</div>
			</div>

		</form:form>
		<div class="text-right">
			<a href="${pageContext.request.contextPath }/user/userList"
				class="btn btn-default"><spring:message code="button.back"></spring:message></a>
		</div>
</body>
</html>