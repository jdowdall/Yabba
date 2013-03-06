<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <jsp:useBean
	id="userBean"
	scope="request"
	class="com.jgd.yabba.store.UserStore">
	<jsp:setProperty name="userBean" property="*"/>
 	</jsp:useBean>
 	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<%@ include file="css/mainYabba.css"  %>
</style>
<title>Profile</title>
</head>
<body>
	<div id="logoutbtn">
		<FORM action="Logout">
			<button type="submit">Logout</button>
		</FORM>
	</div>
	
	<div id="friendbtns">
		<FORM action="Follow">
			<button type="submit">Find Friends</button>
		</FORM>
		<FORM action="Unfollow">
			<button type="submit">Unfollow Friends</button>
		</FORM>
	</div>
	
	<img alt="Yabba"
		src="images/YabbaSmall.png"
		height="100px">

	<jsp:getProperty property="username" name="userBean"/>
	<jsp:getProperty property="email" name="userBean"/>
	<form action="Profile" method="post" >
		<label for="username">Username</label>
		<input type="text" name="username"/>
		<label for="email">Email Address</label>
		<input type="text" name="email"/>
		<label for="password">Password</label>
		<input type="text" name="password"/>
		<button type="submit">Update</button>
	</form>

</body>
</html>