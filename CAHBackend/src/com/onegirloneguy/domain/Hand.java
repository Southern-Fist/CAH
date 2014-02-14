package com.onegirloneguy.domain;

import java.util.ArrayList;
import java.util.List;

//this may become an entity later on

public class Hand {

	
	private Integer handId;
	private List<Card> cards = new ArrayList<Card>();
	
	
	public Integer getHandId() {
		return handId;
	}

	public void setHandId(final Integer handId) {
		this.handId = handId;
	}

	public void addCard(final Card aCard){
		
		cards.add(aCard);
	}
	
	public Card removeCard(final Integer position){
		
		return cards.remove(position.intValue());
	}
	
	public List<Card> listHand(){
		
		return cards;
	}
	
	public void setHand(final ArrayList<Card> cards){
		
		this.cards = cards;
	}
}
