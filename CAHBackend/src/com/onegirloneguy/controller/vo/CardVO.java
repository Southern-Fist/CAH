package com.onegirloneguy.controller.vo;

import com.onegirloneguy.domain.Card;

public class CardVO extends BaseVO{

	private String cardId;
	private String cardString;
	private boolean flipped;
	
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardString() {
		return cardString;
	}

	public void setCardString(String cardString) {
		this.cardString = cardString;
	}

	public boolean isFlipped() {
		return flipped;
	}

	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}

	@Override
	public void populate(Object obj) {
		
		Card card = (Card) obj;
		this.setCardId(card.getCardId().toString());
		this.setCardString(card.getCardString());
	}

	@Override
	public Object buildDomainObject() {
		
		return null;
	}

	
}
