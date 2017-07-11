<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<form:form action="register" method="post" commandName="register">
		<table>
			<tr>
				<td>Name</td>
				<td><form:input path="name" /></td>
				<td align="left"><form:errors path="name" cssClass="error" /></td>
			</tr>

			<tr>
				<td>Email</td>
				<td><form:input path="email" /></td>
				<td align="left"><form:errors path="email" cssClass="error" /></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><form:password path="password" /></td>
				<td align="left"><modi:errors path="password" cssClass="error" /></td>
			</tr>

			<tr>
				<td>Confirm Password</td>
				<td><input type="password" name="confirmPassword" /></td>
			</tr>

			<tr>
				<td>Mobile Number</td>
				<td><form:input path="phone" /></td>
				<td align="left"><form:errors path="phone" cssClass="error" /></td>
			</tr>

			<tr>
				<!-- <td></td> -->
				<td><input type="submit" />
					<button formaction="loginView">Login</button></td>
			</tr>
		</table>
	</form:form>
</body>
</html>