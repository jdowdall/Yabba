<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <jsp:useBean
	id="messageBean"
	scope="request"
	class="com.jgd.yabba.store.MessageStore">
	<jsp:setProperty name="messageBean" property="*"/>
 	</jsp:useBean>
 	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<%@ include file="css/mainYabba.css"  %>
</style>
<title>Yabba</title>
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
	<tr>
		<td>
		<jsp:getProperty property="username" name="messageBean"/>
		</td>
		<td>
		<jsp:getProperty property="message" name="messageBean"/>
		</td>
		<td>
		<form action="Message" method="post" >
		<input type="hidden" name="messageid" value='<jsp:getProperty property="idmessages" name="messageBean"/>'/>
		<input type="hidden" name="userid" value='<jsp:getProperty property="user" name="messageBean"/>'/>
		<button type="submit" name="delete">Delete</button>
	</form>
		</td>
		</tr>
</body>
</html>