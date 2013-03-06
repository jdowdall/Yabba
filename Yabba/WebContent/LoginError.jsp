<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<%@ include file="css/mainYabba.css"  %>
</style>
<title>Error</title>
</head>
<body>
	
	<img alt="Yabba"
		src="images/YabbaSmall.png"
		height="100px">
	<p>
	You must login to access this page
	
	</p>
	
	<FORM action="/Yabba/Login" method="get">
		<button type="submit">Login Now</button>
	</FORM>
</body>
</html>