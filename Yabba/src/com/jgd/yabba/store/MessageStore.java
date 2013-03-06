package com.jgd.yabba.store;

public class MessageStore {
	
	private int idmessages;
	private String message;
	private int user;
	private String username;
	
	public int getIdmessages() {
		return idmessages;
	}
	public void setIdmessages(int idmessages) {
		this.idmessages = idmessages;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
