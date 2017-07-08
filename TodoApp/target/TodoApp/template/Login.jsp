<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<form:form action="login" commandName="login">
		<table>
			<tr>
				<td>Email</td>
				<td><form:input path="email" /></td>
				<td align="left"><form:errors path="name" cssClass="error" /></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><form:password path="password" /></td>
				<td align="left"><form:errors path="name" cssClass="error" /></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Login" />
					<button formaction="registerView">Register</button></td>
			</tr>
		</table>
	</form:form>
</body>
</html>