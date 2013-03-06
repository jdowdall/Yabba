<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.jgd.yabba.store.MessageStore" %>
    
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<%@ include file="css/mainYabba.css"  %>
</style>
<title>Messages</title>


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
	
	<div id="newMessageForm">
	<img alt="Yabba"
		src="images/YabbaSmall.png"
		height="100px">
	<form action="Message" method="post">
		<input type="text" name="newMessage" size=100 height=16 id="newMessage"/>
		<button type="submit">Yabba</button>
	</form>
	</div>
	
	 <!-- <script type="text/javascript" src="delete.js"></script> -->
	 
	
	

 	<%

	List<MessageStore> messages = (List<MessageStore>)request.getAttribute("messages"); 

	ListIterator iterator = messages.listIterator();

	%>
	<table>
	<%
	while(iterator.hasNext())
	{
		MessageStore message =new MessageStore();
		message = (MessageStore)iterator.next();

		String username = message.getUsername();
		String text = message.getMessage();

		%>
		<tr>
		<td align="right">
		<%out.print(username + " : "); %>
		</td>
		<td>
		<%out.print(text); %>
		</td>
		</tr>
		
	<%
	}
	%>
	</table>

	
</body>
</html>