<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<link rel="stylesheet" type="text/css"
	href="/css/login.css">	
<link rel="stylesheet" type="text/css"
	href="/bower_components/bootstrap/dist/css/bootstrap.min.css">		
	
</head>
<body>
	<!-- <%-- <form:form action="login" commandName="login">
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
	</form:form> --%> -->



	<!-- <div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>#</th>
					<th>Firstname</th>
					<th>Lastname</th>
					<th>Age</th>
					<th>City</th>
					<th>Country</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Anna</td>
					<td>Pitt</td>
					<td>35</td>
					<td>New York</td>
					<td>USA</td>
				</tr>
			</tbody>
		</table>
	</div>
	</div> -->




	<div class="wrapper">
		<form class="form-signin">
			
			
			<h2 class="form-signin-heading">Please login</h2>
			
			
			<input type="text" class="form-control" name="username"
				placeholder="Email Address" required="" autofocus="" /> 
				
				
				
				<input type="password" class="form-control" name="password"
				placeholder="Password" required="" /> 
				
				<label class="checkbox">
				<input type="checkbox" value="remember-me" id="rememberMe"
				name="rememberMe"> Remember me
			</label>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
		</form>
	</div>



</body>
</html>