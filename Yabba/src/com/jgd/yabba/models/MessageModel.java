package com.jgd.yabba.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import com.jgd.yabba.store.MessageStore;
import com.jgd.yabba.store.UserStore;
import com.jgd.yabba.utilities.DBConnect;


public class MessageModel {
	
	public List<MessageStore> getMessages(int loggedInUser){
		List<MessageStore> messages = new LinkedList<MessageStore>();		
		
		
		DBConnect myDBCon = new DBConnect();
		
		Connection myConn = myDBCon.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = myConn.prepareStatement("SELECT messages.user, messages.message FROM messages INNER JOIN followers ON messages.user=followers.followed WHERE followers.follower=? ORDER BY dateTime DESC");
			ps.setInt(1, loggedInUser);
			
			rs = ps.executeQuery();
			
			while(rs.next()){			
				
				MessageStore messageStore = new MessageStore();
				
				messageStore.setMessage(rs.getString("message"));
				messageStore.setUser(rs.getInt("user"));
				
				messageStore.setUsername(getUsername(messageStore.getUser()));
				
				System.out.println(messageStore.getUsername());
				System.out.println(messageStore.getMessage());

				messages.add(messageStore);
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		myDBCon.closeConnection(myConn);
		
		return messages;
		
	}
	
	public List<MessageStore> getMessage(int msgID){
		List<MessageStore> messages = new LinkedList<MessageStore>();	
		MessageStore msgStore = new MessageStore();
		
		DBConnect myDBCon = new DBConnect();
		
		Connection myConn = myDBCon.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = myConn.prepareStatement("SELECT * FROM messages WHERE idmessages=?");
			ps.setInt(1, msgID);
			
			rs = ps.executeQuery();
			
			while(rs.next()){			
				
				MessageStore messageStore = new MessageStore();
				
				messageStore.setMessage(rs.getString("message"));
				messageStore.setUser(rs.getInt("user"));
				messageStore.setIdmessages(rs.getInt("idmessages"));
				
				messageStore.setUsername(getUsername(messageStore.getUser()));
				
				
				messages.add(messageStore);
				
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			System.out.println("Problem accessing message "+msgID+" from database");
		}
		
	
		
		myDBCon.closeConnection(myConn);
		
		
		return messages;
		
	}
	
	public List<MessageStore> getUserMessages(String username){
		List<MessageStore> messages = new LinkedList<MessageStore>();	
		MessageStore msgStore = new MessageStore();
		
		DBConnect myDBCon = new DBConnect();
		
		Connection myConn = myDBCon.getConnection();
		
		PreparedStatement psU = null;
		ResultSet rsU = null;
		int userID=-1;
		
		
		try {
			psU = myConn.prepareStatement("SELECT idusers FROM users WHERE username=?");
			psU.setString(1, username);
			
			rsU = psU.executeQuery();
			while(rsU.next()){			
				
				userID = rsU.getInt("idusers");
				
				
			}
			
			
			
			rsU.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			System.out.println("Problem accessing user "+username+" from database");
		}
		
		PreparedStatement psM = null;
		ResultSet rsM = null;
		
		try {
			psM = myConn.prepareStatement("SELECT * FROM messages WHERE user=?");		
			psM.setInt(1, userID);
			
			rsM = psM.executeQuery();
			
			while(rsM.next()){			
				
				MessageStore messageStore = new MessageStore();
				
				messageStore.setMessage(rsM.getString("message"));
				messageStore.setUser(rsM.getInt("user"));
				
				messageStore.setUsername(getUsername(messageStore.getUser()));
				
				
				messages.add(messageStore);
				
				
			}
			
			rsM.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			System.out.println("Problem accessing messages for user "+userID+" from database");
		}
		
		
		myDBCon.closeConnection(myConn);
		
		
		return messages;
		
	}

	private String getUsername(int userid) {
		List<UserStore> Users = new LinkedList<UserStore>();
		UserModel userModel = new UserModel();
		
		Users = userModel.getUsers(userid);
		
		UserStore userBean = new UserStore();
		userBean = Users.get(0);
		
		String name = userBean.getUsername();
		
		return name;
		
		
	}
	
	public void addMessage(String message, int userid){
		
		/*//example from http://www.coderanch.com/t/304851/JDBC/databases/Java-date-MySQL-date-conversion (accessed 06/03/13)
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);*/
		
		java.util.Date today = new java.util.Date();
		Timestamp now = new Timestamp(today.getTime());
		
		DBConnect myDBCon = new DBConnect();
		
		Connection myConn = myDBCon.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			ps = myConn.prepareStatement("INSERT INTO messages(message,user,dateTime) VALUES(?,?,?)");
			ps.setString(1, message);
			ps.setInt(2, userid);
			ps.setTimestamp(3, now);
			
			ps.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		myDBCon.closeConnection(myConn);
	}
	
	public void deleteMessage(int messageid){
		DBConnect myDBCon = new DBConnect();
		
		Connection myConn = myDBCon.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			ps = myConn.prepareStatement("DELETE FROM messages WHERE idmessages=?");
			ps.setInt(1, messageid);
			
			ps.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		myDBCon.closeConnection(myConn);
	}
	}


