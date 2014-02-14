package com.onegirloneguy.service;

import com.onegirloneguy.controller.vo.SignOnVO;
import com.onegirloneguy.domain.UserProfile;

public interface UserService {

	public SignOnVO signOn(SignOnVO vo);
	public SignOnVO registerUser(SignOnVO vo);
	
}
