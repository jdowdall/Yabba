<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.jgd.yabba.store.UserStore" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<%@ include file="css/mainYabba.css"  %>
</style>
<title>Yabba Users</title>
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
	<%

	List<UserStore> messages = (List<UserStore>)request.getAttribute("users"); 

	ListIterator iterator = messages.listIterator();

	%>
	<table>
	<%
	while(iterator.hasNext())
	{
		UserStore user =new UserStore();
		user = (UserStore)iterator.next();

		String username = user.getUsername();
		String email = user.getEmail();
		int userid = user.getUserID();

		%>
		<tr>
		<td>
		<%out.print(username); %>
		</td>
		<td>
		<%out.print(email); %>
		</td>
		<td>
		<form action="Follow" method="post" >
		<input type="hidden" name="action" value="follow"/>
		<input type="hidden" name="userid" value='<%=userid %>'/>
		<button type="submit" name="follow">Follow</button>
		
		</form>
		
		<form action="Unfollow" method="post" >
		<input type="hidden" name="action" value="follow"/>
		<input type="hidden" name="userid" value='<%=userid %>'/>
		<button type="submit" name="unfollow">UnFollow</button>
		</form>
		
		</td>
		
		</tr>
		
	<%
	}
	%>
	</table>
</body>
</html>