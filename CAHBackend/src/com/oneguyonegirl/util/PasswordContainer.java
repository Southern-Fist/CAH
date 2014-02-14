package com.oneguyonegirl.util;

public class PasswordContainer {
	
	private String password;
	private String hashedPassword;
	private byte[] salt;
	
	
	public String getSaltedPassword(){
		
		return password + salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	
	
}
