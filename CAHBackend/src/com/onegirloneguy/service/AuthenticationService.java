package com.onegirloneguy.service;

import com.onegirloneguy.controller.vo.SignOnVO;
import com.onegirloneguy.domain.UserProfile;
import com.oneguyonegirl.exception.NoUserFoundException;

public interface AuthenticationService {

	public void checkUserExists(SignOnVO vo) throws NoUserFoundException ;
	public boolean userActive(UserProfile vo);
	public SignOnVO authenticateUser(SignOnVO vo);
}
