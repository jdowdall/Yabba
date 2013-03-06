package com.jgd.yabba.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.jgd.yabba.store.UserStore;
import com.jgd.yabba.utilities.DBConnect;

public class FollowModel {

	public List<UserStore> findUsers(String username) {
		List<UserStore> Users = new LinkedList<UserStore>();
		UserStore userStore = new UserStore();
		
		DBConnect myDBCon = new DBConnect();
		
		Connection myConn = myDBCon.getConnection();
		
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = myConn.createStatement();
		} catch (SQLException e) {
			System.out.println("Error creating statment");
			e.printStackTrace();
			
		}
		try {
			PreparedStatement ps = myConn.prepareStatement("SELECT * FROM users WHERE username = ? ");
			
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			
			
			while (rs.next() ){
				//System.out.println(rs.getString("username"));
				userStore.setUsername(rs.getString("username"));
				userStore.setUserID(rs.getInt("idusers"));
				userStore.setEmail(rs.getString("email"));
				
				Users.add(userStore);
				
			 }
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		myDBCon.closeConnection(myConn);
		
		return Users;
	}

	public void addFollower(int followerid, int followUser) {
		
		
		
		DBConnect myDBCon = new DBConnect();
		
		Connection myConn = myDBCon.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			ps = myConn.prepareStatement("INSERT INTO followers(follower,followed) VALUES(?,?)");
			ps.setInt(1, followerid);
			ps.setInt(2, followUser);
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myDBCon.closeConnection(myConn);
		
	}

	public void unfollow(UserStore follower, int followeduser) {
		
		int followerid = follower.getUserID();
		
		DBConnect myDBCon = new DBConnect();
		
		Connection myConn = myDBCon.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			//"DELETE FROM messages WHERE idmessages=?"
			ps = myConn.prepareStatement("DELETE FROM followers WHERE follower=? AND followed=?");
			ps.setInt(1, followerid);
			ps.setInt(2, followeduser);
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myDBCon.closeConnection(myConn);
		
	}
		
	}


