package com.onegirloneguy.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onegirloneguy.domain.Card;
import com.onegirloneguy.domain.CardType;
import com.onegirloneguy.domain.Deck;


@Service
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CardDAO {

	@PersistenceContext(unitName = "CAHBackend")
	EntityManager em;
	public static final String DAO_NAME = "CardDAOLocalBean";
		
	
	
	@Transactional
	public void addCard(String cardValue, Integer cardTypeId) {
		
		CardType type = new CardType();
		type.setCardTypeId(cardTypeId);
		Card deck = new Card();
		deck.setCardString(cardValue);
		deck.setCardType(type);
		
		em.persist(deck);
	}
	
	public Deck getDeck(Integer cardTypeId){
		
		Query qry = em.createNamedQuery(Card.FIND_BY_TYPE);
		CardType type = new CardType();
		type.setCardTypeId(cardTypeId);
		
		qry.setParameter("typeId", type);
		
		String deckName = cardTypeId == 1 ? "Black Deck" : "White Deck";
		
		ArrayList<Card> retval = new ArrayList<Card>(qry.getResultList());
		Deck deck = new Deck(retval, deckName);
		
		return deck;
	}

}
