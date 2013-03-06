package com.jgd.yabba.utilities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnect {
	
	public Connection getConnection(){
		Connection myConn = null;
		
		try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		myConn = DriverManager.getConnection("jdbc:mysql://arlia.computing.dundee.ac.uk/joannedowdall","JoanneDowdall","ac31004");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myConn;
		
	}
	
	public void closeConnection(Connection myConn){
		
		if(myConn != null){
		try {
			myConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

}
