package com.onegirloneguy.service;

import java.util.Collection;

import com.onegirloneguy.domain.Card;
import com.onegirloneguy.domain.Player;

public interface DealerService {

	public void init();
	
	public void registerPlayer(Player player);
	public void removePlayer(Player player);
	public Player getPlayer(Integer position);
	
	public void shuffleDecks();
	public Card drawCard(Integer cardTypeId);
	public Collection<Card> drawCards(Integer cardTypeId, Integer numberOfCards);
	
	public void deal(Integer numberOfCards);
	public Card drawBlackCard();
	
}
