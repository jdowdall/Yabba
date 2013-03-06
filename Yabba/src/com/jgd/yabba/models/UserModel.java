package com.jgd.yabba.models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.*;
import org.apache.commons.codec.binary.Base64;

import com.jgd.yabba.store.UserStore;
import com.jgd.yabba.utilities.DBConnect;


public class UserModel {
	
	public List<UserStore> getUsers(int userID){
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
			int user = userID;
			PreparedStatement ps = myConn.prepareStatement("SELECT * FROM users WHERE idusers = ? ");
			
			ps.setInt(1, user);
			
			//rs = st.executeQuery("SELECT * FROM users WHERE idusers=user");
			
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			
			
			while (rs.next() ){
				System.out.println(rs.getString("username"));
				userStore.setUsername(rs.getString("username"));
				
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
	
	public void addUser(String username, String password, String email){
		
		String salt = generateSalt();
		
		String encryptedPW = encryptPW(password,salt);
		
		DBConnect myDBCon = new DBConnect();
		
		Connection myConn = myDBCon.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			ps = myConn.prepareStatement("INSERT INTO users(username,password,email,salt) VALUES(?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, encryptedPW);
			ps.setString(3, email);
			ps.setString(4, salt);
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PreparedStatement psGetId = null;
		ResultSet rs = null;
		
		int userid = 0;
		
		try {
			psGetId = myConn.prepareStatement("SELECT idusers FROM users WHERE username=? AND password=? AND email=? AND salt=?");
			psGetId.setString(1, username);
			psGetId.setString(2, encryptedPW);
			psGetId.setString(3, email);
			psGetId.setString(4, salt);
			
			rs = psGetId.executeQuery();
			
			while(rs.next()){
				
				userid = rs.getInt("idusers");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FollowModel followModel = new FollowModel();
		
		followModel.addFollower(userid, userid);
		
		
		
		
		
		
		myDBCon.closeConnection(myConn);
	}
	
	public UserStore login(String email, String password){
		DBConnect myDBCon = new DBConnect();
		
		Connection myConn = myDBCon.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = myConn.prepareStatement("SELECT * FROM users WHERE email = ?");
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("There are not users registered with that email address");
			
			e.printStackTrace();
			
			
			
			
		}
		
		try {
			if (!rs.isBeforeFirst() ) {    
				 System.out.println("There are no users registered with that email address"); 
				 return null;
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String storedPW = null;
		String salt = null;
		UserStore userStore = new UserStore();
		
		try {
			
			while (rs.next() ){
				System.out.println(rs.getString("username"));
				userStore.setUsername(rs.getString("username"));
				userStore.setUserID(rs.getInt("idusers"));
				userStore.setEmail(rs.getString("email"));
				
				storedPW = rs.getString("password");
				salt = rs.getString("salt");
				
			 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Stored Password: "+storedPW);
		System.out.println("Salt: "+salt);
		System.out.println("Password: "+password);
		
		String attemptedPW = encryptPW(password,salt);
		
		
		if(storedPW.equals(attemptedPW)){
			
			return userStore;
			
		}
		else{
			System.out.println("PASSWORD INCORRECT");
		}
		
		
		
		myDBCon.closeConnection(myConn);
		
		return null;
	}
	
	/*
	 * Adapted from examples found at http://www.quicklyjava.com/salt-in-java/ and http://stackoverflow.com/questions/3077196/messagedigest-hashes-differently-on-different-machines
	 * 23/02/2013
	 */
	private String encryptPW(String password, String salt) {
		
		MessageDigest messageDigest=null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update((password+salt).getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] hashedByteArray = messageDigest.digest();
        
        String encryptedPassword = Base64.encodeBase64URLSafeString(hashedByteArray);
        System.out.println("Encrypted Password: " + encryptedPassword);
		
		
		return encryptedPassword;
	}

	private String generateSalt(){
		
		Random r = new Random();
		
		String salt=null;

	    String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";
	    for (int i = 0; i < 32; i++) {
	        salt += Character.toString(alphabet.charAt(r.nextInt(alphabet.length())));
	        
	        
	    }
		
		return salt;
	
}

	public UserStore getCurrentUserInfo(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateUser(String newUsername, String newPassword, String newEmail, UserStore oldDetails) {
		DBConnect myDBCon = new DBConnect();
		
		Connection myConn = myDBCon.getConnection();
		
		int userid = oldDetails.getUserID();
		
		if(newUsername.length()>0){
			
			PreparedStatement ps = null;
			
			try {
				ps = myConn.prepareStatement("UPDATE users SET username=? WHERE idusers=?");
				ps.setString(1, newUsername);
				ps.setInt(2, userid);
				
				ps.execute();
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println("Error updating username");
			}
			
			
		}
		
		if(newEmail.length()>0){
			
			PreparedStatement ps = null;
			
			try {
				ps = myConn.prepareStatement("UPDATE users SET email=? WHERE idusers=?");
				ps.setString(1, newEmail);
				ps.setInt(2, userid);
				
				ps.execute();
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println("Error updating email address");
			}
			
			
		}
		
		if(newPassword.length()>0){
			
			PreparedStatement ps = null;
			PreparedStatement psSalt = null;
			ResultSet rs = null;
			String salt = null;
			
			try{
				psSalt = myConn.prepareStatement("SELECT salt FROM users WHERE idusers=?");
				rs = psSalt.executeQuery();
				
				salt = rs.getString(salt);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println("Error retrieving salt");
			}
			
			String encryptedPW = encryptPW(newPassword,salt);
			
			
			try {
				ps = myConn.prepareStatement("UPDATE users SET password=? WHERE idusers=?");
				ps.setString(1, encryptedPW);
				ps.setInt(2, userid);
				
				ps.execute();
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println("Error updating password");
			}
			
			
		}
		
		myDBCon.closeConnection(myConn);
		
	}
}
