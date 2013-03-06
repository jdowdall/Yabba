<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<%@ include file="css/mainYabba.css"  %>
</style>
<title>Login</title>
</head>
<body>
	<img alt="Yabba"
		src="images/YabbaSmall.png"
		height="100px">
	<form action="Login" method="post">
		<label for="email">Email Address</label>
		<input type="text" name="email"/>
		<label for="password">Password</label>
		<input type="password" name="password"/>
		<button type="submit">Login</button>
	</form>
	<p>
	Not yet a Yabba user? Sign up now for free!
	</p>
	<FORM action="Register.jsp">
		<button type="submit">Register</button>
	</FORM>
</body>
</html>