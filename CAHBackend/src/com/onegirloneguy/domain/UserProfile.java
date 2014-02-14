package com.onegirloneguy.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table( name = "userprofile" )
@NamedQueries({

	@NamedQuery(name = "User.findAll", query = "select up from UserProfile up"),
	@NamedQuery(name = "User.findByNameAndPassword", 
		query = "select up from UserProfile up " +
				"	where up.username = :user " +
				"	and up.password = :password"),
	@NamedQuery(name = "User.findByUsername", 
	query = "select up from UserProfile up " +
			"	where up.username = :user "),
	@NamedQuery(name = "User.findById", query = "select up from UserProfile up where up.profileId = :profileId") })

public class UserProfile {

	public static final String FIND_ALL = "User.findAll";
	public static final String FIND_BY_ID = "User.findById";
	public static final String FIND_BY_NAME_AND_PASSWORD = "User.findByNameAndPassword";
	public static final String FIND_BY_USERNAME = "User.findByUsername";
	
	public static final String USER_NAME = "user";
	public static final String PASSWORD = "password";
	
	private Integer profileId;
	private String username;
	private String password;
	private byte[] saltValue;
	//private Role role;
	private Date lastLogin;
	private Integer failedLoginCount;
	private String imgPath;
	private char accountActive;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name="increment", strategy = "increment")
	public Integer getProfileId() {
		return profileId;
	}
	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}*/
	
	public byte[] getSaltValue() {
		return saltValue;
	}
	public void setSaltValue(byte[] saltValue) {
		this.saltValue = saltValue;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastLogin")
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public Integer getFailedLoginCount() {
		return failedLoginCount;
	}
	public void setFailedLoginCount(Integer failedLoginCount) {
		this.failedLoginCount = failedLoginCount;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public char getAccountActive() {
		return accountActive;
	}
	public void setAccountActive(char accountActive) {
		this.accountActive = accountActive;
	}
	
	
}
