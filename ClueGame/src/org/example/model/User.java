package org.example.model;
//The user class will have the name and session ID
public class User {
	
	//They are public because I was just testing, 
	public String name;
	public String sessionId;
	
	public User(String name, String sessionId){
		this.name = name;
		this.sessionId = sessionId;
	}

}
