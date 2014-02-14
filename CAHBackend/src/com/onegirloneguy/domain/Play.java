package com.onegirloneguy.domain;

import com.onegirloneguy.controller.vo.CardVO;

public class Play {

	private String player;
	private CardVO firstCard;
	private CardVO secondCard;
	
	
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public CardVO getFirstCard() {
		return firstCard;
	}
	public void setFirstCard(CardVO firstCard) {
		this.firstCard = firstCard;
	}
	public CardVO getSecondCard() {
		return secondCard;
	}
	public void setSecondCard(CardVO secondCard) {
		this.secondCard = secondCard;
	}
}
