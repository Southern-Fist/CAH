package com.onegirloneguy.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onegirloneguy.domain.UserProfile;
import com.oneguyonegirl.exception.NoUserFoundException;

@Service
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserDAO {

	@PersistenceContext(unitName = "CAHBackend")
	EntityManager em;

	@Transactional
	public UserProfile registerUser(UserProfile profile) {

		em.persist(profile);

		return profile;
	}
	
	@Transactional
	public UserProfile update(UserProfile profile) {

		em.merge(profile);
		//em.persist(profile);

		return profile;
	}

	public UserProfile signOn(UserProfile profile) throws NoUserFoundException{

		Query qry = em.createNamedQuery(UserProfile.FIND_BY_USERNAME);
		qry.setParameter(UserProfile.USER_NAME, profile.getUsername());
		
		try{
			profile = (UserProfile) qry.getSingleResult();
		}catch (NoResultException nre){
			nre.printStackTrace();
			throw new NoUserFoundException("The credentials entered were inccorect.  Please try again.");
		}
		

		return profile;
	}

	/*
	 * public UserProfile signOn(UserProfile profile) {
	 * 
	 * Query qry = em.createNamedQuery(UserProfile.FIND_BY_NAME_AND_PASSWORD);
	 * qry.setParameter(UserProfile.USER_NAME, profile.getUsername());
	 * qry.setParameter(UserProfile.PASSWORD, profile.getPassword()); profile =
	 * (UserProfile) qry.getSingleResult();
	 * 
	 * return profile; }
	 */
}
