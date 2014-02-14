package com.onegirloneguy.service;

import com.onegirloneguy.domain.Deck;

public interface CardService {

	public void seedCards();
	public Deck buildBlackDeck();
	public Deck buildWhiteDeck();
	//public void shuffleDecks();
	//public Card drawCard(Integer cardTypeId);
	//public Collection<Card> drawCards(Integer cardTypeId, Integer numberOfCards);
	
}
