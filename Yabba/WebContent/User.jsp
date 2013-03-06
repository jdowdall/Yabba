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
<title>User</title>
</head>
<body>
	<font size="10"><%="User page" %></font>
	<p>Users: 

<jsp:getProperty name="userBean" property="username" />
 
 
</body>
</html>