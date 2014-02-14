package com.onegirloneguy.controller.vo;

import java.util.Calendar;

import com.onegirloneguy.domain.UserProfile;

public class SignOnVO extends BaseVO {

	private String username;
	private String password;
	private String lastLogin;
	private String loginToken;

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(final String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(final String loginToken) {
		this.loginToken = loginToken;
	}

	@Override
	public void populate(final Object obj) {

		final UserProfile prof = (UserProfile) obj;

		if (prof != null) {

			this.setUsername(prof.getUsername());
			//should not populate the passwords and send to the front end.
			//this.setPassword(prof.getPassword());
			this.setLastLogin(prof.getLastLogin() != null ? prof.getLastLogin().toString() : Calendar.getInstance().getTime().toString());
		}else {
			//this is terrible, but too lazy to sort out why the code is dead in the service.
			this.setErrorMessage("Username or Password entered was incorrect, please try again.");
		}

	}

	@Override
	public Object buildDomainObject() {

		final UserProfile prof = new UserProfile();
		prof.setUsername(getUsername());
		prof.setPassword(getPassword());
		return prof;
	}

}
