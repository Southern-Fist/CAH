package com.onegirloneguy.service.impl;

import java.util.Calendar;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.onegirloneguy.controller.vo.SignOnVO;
import com.onegirloneguy.dao.UserDAO;
import com.onegirloneguy.domain.UserProfile;
import com.onegirloneguy.service.AuthenticationService;
import com.oneguyonegirl.exception.NoUserFoundException;
import com.oneguyonegirl.util.CAHUtil;
import com.oneguyonegirl.util.Constants;
import com.oneguyonegirl.util.PasswordContainer;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Inject
	private UserDAO dao;

	@Override
	public void checkUserExists(final SignOnVO vo) throws NoUserFoundException {

		// If the user doesn't exist a user not found exception is thrown
		// otherwise nada happens
		dao.signOn((UserProfile) vo.buildDomainObject());

	}

	@Override
	public boolean userActive(final UserProfile up) {
		// TODO Auto-generated method stub
		return Constants.YES_CHAR == up.getAccountActive();
	}

	@Override
	public SignOnVO authenticateUser(final SignOnVO vo) {

		final UserProfile prof = new UserProfile();

		try {
			
			checkUserExists(vo);
		} catch (final NoUserFoundException e) {
			
			vo.setErrorMessage("The credentials you entered are incorrect.  Please try again.");
			return vo;
		}

		System.out.println("[DEBUG] - ID: " + prof.getProfileId());

		// check to see if the account is active, if it isn't no need to
		// proceed.
		if (Constants.NO_CHAR == prof.getAccountActive()) {

			vo.setErrorMessage("Your account has been deactivated.  Please call customer service.");
			return vo;
		}

		// Set up password container
		final PasswordContainer pc = new PasswordContainer();

		pc.setPassword(vo.getPassword());
		pc.setHashedPassword(prof.getPassword());
		pc.setSalt(prof.getSaltValue());

		if (CAHUtil.getInstance().isPasswordValid(pc, CAHUtil.getInstance().STRETCHED_HASH_ALGO)) {
			System.out.println("Password Correct!!");
			vo.populate(prof);
			vo.setPassword("");
			prof.setLastLogin(Calendar.getInstance().getTime());

		} else {
			System.out.println("Password Wrong!!");
			Integer failed = prof.getFailedLoginCount() == null ? 0 : prof
					.getFailedLoginCount();
			failed += 1;
			vo.setErrorMessage("The credentials you prvided are incorrect, please try again.  You have "
					+ (Constants.MAX_LOGIN_RETRY - failed)
					+ "login attempts remaining.");
			if (failed >= Constants.MAX_LOGIN_RETRY)
				prof.setAccountActive(Constants.NO_CHAR);
			prof.setFailedLoginCount(failed);
		}
		dao.update(prof);

		return vo;
	}

}
