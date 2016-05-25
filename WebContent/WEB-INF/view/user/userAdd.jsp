<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
			alert("<spring:message code='tip.addSuccess'></spring:message>");
			$(form).ajaxSubmit();
		}
	});
	$().ready(function() {
		$("#userForm").validate();
	});
</script>
<style type="text/css">
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
<title><spring:message code="tip.userAdd"></spring:message></title>
</head>
<body>
	<div class="container">
		<h1 class="text-center">
			<spring:message code="tip.userAdd"></spring:message>
		</h1>
		<form:form action="${pageContext.request.contextPath }/user/add"
			method="post" modelAttribute="user" class="form-horizontal"
			role="form" id="userForm">

			<div class="form-group">
				<label class="col-sm-1 control-label"> <spring:message
						code="user.username"></spring:message>
				</label>
				<div class="col-sm-11">
					<input type="text" name="username" class="form-control" required
						minlength="6">
					<form:errors path="username"></form:errors>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label"><spring:message
						code="user.password"></spring:message></label>
				<div class="col-sm-11">
					<input type="password" name="password" class="form-control"
						required minlength="6">
					<form:errors path="password"></form:errors>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label"> <spring:message
						code="user.email"></spring:message></label>
				<div class="col-sm-11">
					<input type="email" name="email" class="form-control">
					<form:errors path="email"></form:errors>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label"> <spring:message
						code="user.age"></spring:message></label>
				<div class="col-sm-11">
					<input type="number" name="age" class="form-control"
						range="[0,120]">
					<form:errors path="age"></form:errors>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label"> <spring:message
						code="user.sex"></spring:message></label>
				<div class="col-sm-11">
					<label class="radio-inline"><input type="radio" name="sex"
						value="男" checked> <spring:message code="tip.sexMale"></spring:message></label>
					<label class="radio-inline"><input type="radio" name="sex"
						value="女"> <spring:message code="tip.sexFemale"></spring:message></label>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-11">
					<input type="submit"
						value="<spring:message code="button.add"></spring:message>"
						class="btn btn-primary form-control">
				</div>
			</div>

		</form:form>
		<div class="form-group text-right">
			<a href="${pageContext.request.contextPath }/user/userList"
				class="btn btn-default"><spring:message code="button.back"></spring:message></a>
		</div>
	</div>
</body>
</html>