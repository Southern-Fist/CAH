package com.onegirloneguy.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.onegirloneguy.controller.vo.SignOnVO;
import com.onegirloneguy.dao.UserDAO;
import com.onegirloneguy.domain.UserProfile;
import com.onegirloneguy.service.AuthenticationService;
import com.onegirloneguy.service.UserService;
import com.oneguyonegirl.exception.NoUserFoundException;
import com.oneguyonegirl.util.CAHUtil;
import com.oneguyonegirl.util.Constants;
import com.oneguyonegirl.util.PasswordContainer;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	UserDAO dao;
	
	@Inject
	AuthenticationService authService;

	@Override
	public SignOnVO signOn(SignOnVO vo) {
		
		vo = authService.authenticateUser(vo);
		return vo;
	}

	/** TODO: this method could be cleaner, the hashing should be encapsulated in the util object **/
	@Override
	public SignOnVO registerUser(SignOnVO vo) {

		boolean userAlreadyRegistered = true;
		//Using exceptions here feels wrong but I'll leave it for now
		try {
			authService.checkUserExists(vo);
		} catch (NoUserFoundException e) {
			userAlreadyRegistered = false;
		}
		
		if (userAlreadyRegistered){
			vo.setErrorMessage("The user you have registered already exists in our records.  Please use a different username.");
			return vo;
		}
		
		PasswordContainer pc = new PasswordContainer();
		pc.setPassword(vo.getPassword());
		
		pc = CAHUtil.getInstance().generateHash(pc,CAHUtil.STRETCHED_HASH_ALGO);

		UserProfile prof = (UserProfile) vo.buildDomainObject();
		
		prof.setPassword(pc.getHashedPassword());
		prof.setSaltValue(pc.getSalt());
		prof.setAccountActive(Constants.YES_CHAR);
		dao.registerUser(prof);

		System.out.println("[DEBUG] - ID: " + prof.getProfileId());
		vo.populate(prof);
		return vo;
	}

}
